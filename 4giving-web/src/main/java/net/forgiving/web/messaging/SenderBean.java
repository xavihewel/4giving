/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.web.messaging;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Queue;

/**
 *
 * @author xavier.verges
 */
@JMSDestinationDefinition(
        name = "java:comp/jms/webappMessagingQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "messagingQueueDestination"
)
@Named
@RequestScoped
public class SenderBean {

    private static final Logger LOG = Logger.getLogger(SenderBean.class.getName());

    @Inject
    private JMSContext jmsContext;

    @Resource(name = "java:comp/jms/webappMessagingQueue")
    private Queue queue;

    private String messageText; 
    private int priority;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    

    public void sendMessage() {
        LOG.log(Level.INFO, "Sending message {0}", messageText);

        jmsContext.createProducer().setPriority(priority).send(queue, messageText);
        
        FacesMessage facesMessage = new FacesMessage("Rebut: " + messageText);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
