/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation;

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.inject.Inject;
import net.forgiving.donation.persistence.DonationsDao;

/**
 *
 * @author gabalca
 */
@Stateless
public class DonationsNewsletterBean {
    
    @Inject
    private DonationsDao donationsDao;
    
    //configurar el sendNewsLetter per a enviar el darrer dissabte de més, i a part
    //el dia de sant valentí 14/02 i el dia abans de nadal (23/12)
    
    @Schedules({
            @Schedule(dayOfMonth = "Last Sat"),
            @Schedule(dayOfMonth = "14", month = "2"),
            @Schedule(dayOfMonth = "23", month = "12", info="nadal"),
    })
    public void sendNewsLetter(Timer t){
        if("nadal".equals(t.getInfo())){
            System.out.println("Enviant la newsletter de nadal");
        }else{
            System.out.println("enviant alguna altra newsletter");
        }
    }
    
}
