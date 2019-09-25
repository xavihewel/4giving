/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.shipmentproviders;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import net.forgiving.donation.shipping.ShippingInfo;

/**
 *
 * @author xavier.verges
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
            propertyName = "destinationType",
            propertyValue = "javax.jms.Queue"
    ),
    @ActivationConfigProperty(
            propertyName = "destinationLookup",
            propertyValue = "jms/4givingDelivery"
    ),
    @ActivationConfigProperty(
            propertyName = "messageSelector",
            propertyValue = "urgent = FALSE AND internationsl = TRUE"
    )
})
public class MRWEnviamentMDBean implements MessageListener {

    private static final Logger LOG = Logger.getLogger(SeurEnviamentMDBean.class.getName());

    @Resource
    private MessageDrivenContext mdCtx;

    @Override
    public void onMessage(Message msg) {
        if (msg instanceof ObjectMessage) {
            try {
                ShippingInfo info = (ShippingInfo) ((ObjectMessage) msg).getObject();

                LOG.log(Level.INFO, "Enviant a MRW la donació: {0}", info.getDonationId());
            } catch (JMSException ex) {
                LOG.log(Level.SEVERE, "No puc extreure la info del missatge: {0}", msg);
                mdCtx.setRollbackOnly();
            }
        } else {
            LOG.log(Level.INFO, "No se processar el missatge: {0}", msg);
            mdCtx.setRollbackOnly();
        }
    }

}
