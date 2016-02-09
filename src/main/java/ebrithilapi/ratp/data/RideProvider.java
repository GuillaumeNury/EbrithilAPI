/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.data;

import ebrithilapi.ratp.model.Ride;

/**
 *
 * @author Guillaume
 */
public class RideProvider {
    private StationProvider stationProvider;
    private LineProvider lineProvider;
    
    public Ride AllerL320;
    public Ride AllerL310;

    public RideProvider(StationProvider stationProvider, LineProvider lineProvider) {
        this.stationProvider = stationProvider;
        this.lineProvider = lineProvider;
        
        this.AllerL310 = new Ride(this.lineProvider.L310, false, this.stationProvider.MairieNoisyLeGrand310);
        this.AllerL320 = new Ride(this.lineProvider.L320, false, this.stationProvider.MairieNoisyLeGrand320);
    }
    
    
}
