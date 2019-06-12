/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.web.user;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.forgiving.common.user.Address;
import net.forgiving.common.user.User;
import net.forgiving.user.CreationUserBean;

/**
 *
 * @author gabalca
 */
@ConversationScoped
@Named("newUser")
public class NewUserWizardBean implements Serializable{
    
    @EJB
    private CreationUserBean createManager;
    @Inject
    private Conversation conversation;
    
    private boolean finishedNormally;
    
    private String username;
    private String password;
    private String email;
    
    private Address address;
    
    public void startWizard(){
        System.out.println("Starting wizard");
        if(conversation.isTransient()){
            conversation.begin();
        }
    }
    
    public void cancel() throws IOException{
        createManager.cancelCreation();
        conversation.end();
        finishedNormally=true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("users");
    }
    
    public void saveUser() throws IOException{
        createManager.createUser();
        conversation.end();
        finishedNormally=true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("users");
    }
    
    @PostConstruct
    public void init(){
        finishedNormally=false;
        address = new Address();
    }
    
    @PreDestroy
    public void dispose(){
        if(!finishedNormally){
            createManager.cancelCreation();
        }
    }
    
    public String processStep1(){
        User us = new User();
        us.setUsername(username);
        us.setPassword(password);
        us.setEmail(email);
        
        createManager.setUser(us);
        
        
        
        return "user2?faces-redirect=true";
               
    }
    
    public String processStep2(){
        System.out.println("address es "+address);
        createManager.setAddress(address);
        //redirigir al seguent step
        return "user3?faces-redirect=true";
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
