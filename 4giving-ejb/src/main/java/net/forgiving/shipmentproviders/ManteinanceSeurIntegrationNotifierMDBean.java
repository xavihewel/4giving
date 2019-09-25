/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.shipmentproviders;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author xavier.verges
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
            propertyName = "destinationType",
            propertyValue = "javax.jms.Topic"
    ),
    @ActivationConfigProperty(
            propertyName = "destinationLookup",
            propertyValue = "jms/4givingNotifications"
    )
})
public class ManteinanceSeurIntegrationNotifierMDBean implements MessageListener {
    
    private static final Logger LOG = Logger.getLogger(ManteinanceSeurIntegrationNotifierMDBean.class.getName());
    
    @Inject
    private MessageDrivenContext mdctx;
    
    @Override
    public void onMessage(Message msg) {
        try {
            LOG.log(Level.INFO, "Comunicant al connector amb SEUR "
                    + "que apagarem durant :{0}", msg.getIntProperty("hores"));
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, null, ex);
            mdctx.setRollbackOnly();
        }
    }
    
}
