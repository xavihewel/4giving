/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author gabalca
 */
/*
@DatabaseIdentityStoreDefinition(
        callerQuery = "select password from caller where name = ?",
        groupsQuery = "select group_name from caller_groups where caller_name=?",
        priority = 70,
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=3072"
        }   
)
*/


@ApplicationScoped
public class ApplicationSecurityConfig {
    
}
