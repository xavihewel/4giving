/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.mail;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
@Stateless
public class MailManager {
    
    @Resource
    SessionContext sessionCtx;
    
    @Asynchronous
    public Future<Boolean> sendMail(User us, 
            String body, String subject){
        
        boolean result=false;
        try{
            prepareMessage();
            if(sessionCtx.wasCancelCalled()){
                return new AsyncResult<>(false);
            }
            connectToServer();
            if(sessionCtx.wasCancelCalled()){
                return new AsyncResult<>(false);
            }
            deliverMessage();
            result=true;
        }catch(Exception e){
            System.out.println("Error enviant mail: "+e);
            throw e;
        }
        return new AsyncResult<>(result);
    }

    private void prepareMessage() {
        System.out.println("Preparing the message");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void connectToServer() {
        System.out.println("connecting... ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deliverMessage() {
        System.out.println("sending... ");
        if(1==1)throw new RuntimeException("error enviant mail");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
