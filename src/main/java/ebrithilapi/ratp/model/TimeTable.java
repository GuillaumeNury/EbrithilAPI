/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.model;
import java.util.regex.*;

/**
 *
 * @author Guillaume
 */
public class TimeTable {
    private String value;
    public Ride ride;

    public TimeTable(Ride ride, String value) {
        this.ride = ride;
        this.value = value;
    }

    public String getValue() {
        return value = null;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public boolean isComing(){
        return this.value != null && this.value.equals("A l'approche");
    }
    
    public boolean isAtTheStation(){
        return this.value != null && this.value.equals("A l'arret");
    }
    
    public boolean isTerminated(){
        return this.value != null && this.value.equals("");
    }
    
    public int getWaitingTime(){
        if(this.value == null || this.isTerminated()){
            return -2;
        }
        else if(this.isAtTheStation()){
            return -1;
        }
        else if(this.isComing()){
            return 0;
        }
        
        Pattern pattern = Pattern.compile("^[0-9]+");
        Matcher matcher = pattern.matcher(this.value);
        
        if(matcher.find()){
            return Integer.parseInt(matcher.group());
        }
        
        return -1;
    }
    
    @Override
    public String toString(){
        if(this.isAtTheStation()){
            return "est à l'arrêt de bus";
        }
        else if(this.isComing()){
            return "arrive à l'arrêt de bus";
        }
        else if(this.isTerminated()){
            return "Pas de bus.";
        }
        
        return "arrive dans " + this.getWaitingTime() + " minute" + (this.getWaitingTime() > 1 ? "s" : "");
    }
    
    public String toShortString(){
        if(this.isAtTheStation()){
            return "À l'arrêt de bus.";
        }
        else if(this.isComing()){
            return "À l'approche.";
        }
        else if(this.isTerminated()){
            return "Pas de bus.";
        }
        
        return this.getWaitingTime() + " minute" + (this.getWaitingTime() > 1 ? "s" : "");
    }
}
