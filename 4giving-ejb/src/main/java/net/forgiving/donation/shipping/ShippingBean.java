/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.shipping;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import net.forgiving.common.donation.Donation;

/**
 * productor de missatges JMS
 *
 * @author xavier.verges
 */
@Stateless
public class ShippingBean {

    private static final Logger LOG = Logger.getLogger(ShippingBean.class.getName());

    @Inject
    @JMSConnectionFactory("jms/4givingConnectionFactory")
    private JMSContext jmsContext;

    @Resource(name = "jms/4givingDelivery")
    private Destination dest;

    public void processShipping(Donation d) {
        //enviem a la cua d'enviaments
        ObjectMessage om = jmsContext.createObjectMessage(new ShippingInfo(d));

        boolean urgent = d.getId() % 2 == 0;
        boolean international = d.getId() % 3 == 0;

        try {
            om.setBooleanProperty("international", international);
            om.setBooleanProperty("urgent", urgent);
        } catch (JMSException ex) {
            LOG.log(Level.WARNING, "", ex);
        }

        JMSProducer producer = jmsContext.createProducer();
        producer.send(dest, om);

    }

}
