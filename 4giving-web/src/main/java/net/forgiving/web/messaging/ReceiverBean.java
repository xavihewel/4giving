/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.web.messaging;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author xavier.verges
 */
@Named
@RequestScoped
public class ReceiverBean {
    
    @Inject
    private JMSContext jmsContext;
    
    @Resource(name = "java:comp/jms/webappMessagingQueue")
    private Queue queue;
    
    public void getMessage() {
        String text = null;

        //rebre el missatge de la cua amb crida síncrona
        JMSConsumer receiver = jmsContext.createConsumer(queue);
        
        text = receiver.receiveBody(String.class, 1000);
        
        FacesMessage facesMessage = new FacesMessage("Rebut: " + text);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
