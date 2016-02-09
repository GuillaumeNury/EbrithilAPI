/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp;

import ebrithilapi.ratp.data.MainProvider;
import ebrithilapi.ratp.model.Ride;
import ebrithilapi.ratp.service.RideService;
import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class HorairesRatp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainProvider.getRideProvider().AllerL310.printTimeTable();
        MainProvider.getRideProvider().AllerL320.printTimeTable();
        
        System.out.println("\n---------\n");
        
        ArrayList<Ride> rides = new ArrayList<>();
        rides.add(MainProvider.getRideProvider().AllerL310);
        rides.add(MainProvider.getRideProvider().AllerL320);
        
        RideService rideService = new RideService();
        rideService.getNextBus(rides);
    }
}
