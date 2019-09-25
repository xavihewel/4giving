/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.admin;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import net.forgiving.donation.shipping.ShippingInfo;

/**
 *
 * @author xavier.verges
 */
@Stateless
@Path("admin")
//TODO securitzar
public class AdminManagementBean {

    @Inject
    @JMSConnectionFactory("jms/4givingConnectionFactory")
    private JMSContext jmsContext;

    @Inject
    @Resource(name = "jms/4givingNotifications")
    private Destination dest;

    @Resource(lookup = "jms/4ginvingDelivery")
    private Queue queue;

    @GET
    @Path("/pendingShipments")
    public List<ShippingMessage> getPendingShipmentMessages() throws JMSException {
        QueueBrowser browser = jmsContext.createBrowser(queue);

        Enumeration<Message> messages = browser.getEnumeration();
        return null;
        
//        return Collections.list(messages).stream()
//                .map((Message m) - >
//        {
//            try {
//                return new ShippingMessage(m);
//            } catch (JMSException ex) {
//                return null;
//            }
//        }).collect(Collectors.toList());
}

    @POST
        public void notifyMantenianceShutdown(@QueryParam("h") int hores) {
        //publiquem a la cua un missatge. Incorpora el temps previst de desconnexió

        try {
//            ObjectMessage m = jmsContext.createObjectMessage();
//            m.setObject(hores);

            Message m = jmsContext.createMessage();
            m.setIntProperty("hores", hores);
            m.setJMSPriority(9);

            jmsContext.createProducer()
                    .setTimeToLive(1000L * 60 * 60 * hores)
                    .setDeliveryDelay(1000)
                    .setPriority(9)
                    .send(dest, m);
        } catch (JMSException ex) {
            Logger.getLogger(AdminManagementBean

.class  



.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public class ShippingMessage {

    private ShippingInfo info;
    private int retries;
    private int priority;
    private long timestamp;

    public ShippingMessage(Message m) throws JMSException {
        this.retries = m.getIntProperty("JMSXdeliveryCount");
        this.priority = m.getJMSPriority();
        this.timestamp = m.getJMSTimestamp();

        ObjectMessage om = (ObjectMessage) m;
        this.info = om.getBody(ShippingInfo.class);
    }

    public ShippingInfo getInfo() {
        return info;
    }

    public void setInfo(ShippingInfo info) {
        this.info = info;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
}
