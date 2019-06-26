/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.security;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import net.forgiving.common.user.User;
import net.forgiving.user.persistence.UserDao;

/**
 *
 * @author gabalca
 */
@BasicAuthenticationMechanismDefinition(realmName = "4giving")
@ApplicationScoped
public class CustomIdentityStore implements IdentityStore{
    
    @Inject
    private UserDao userDao;
    
    public CredentialValidationResult validate(UsernamePasswordCredential credential){
        System.out.println("Validating user "+credential.getCaller());
        User us = userDao.getUser(credential.getCaller());
        
        if(us!=null){
            if(us.getPassword().equals(credential.getPasswordAsString())){
                System.out.println("User validated!");
                
                Set<String> groups=new HashSet<>();
                groups.add("User");
                if(credential.getCaller().contains("e")){
                    groups.add("Admin");
                }
                return new CredentialValidationResult(credential.getCaller(), groups);
            }else{
                return CredentialValidationResult.INVALID_RESULT;
            }
        }else{
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        return IdentityStore.super.validate(credential); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return IdentityStore.super.getCallerGroups(validationResult); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int priority() {
        return IdentityStore.super.priority(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return IdentityStore.super.validationTypes(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
