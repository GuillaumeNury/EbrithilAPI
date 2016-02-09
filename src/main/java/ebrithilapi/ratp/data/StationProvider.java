/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.data;

import ebrithilapi.ratp.model.Station;

/**
 *
 * @author Guillaume
 */
public class StationProvider {
    public Station MairieNoisyLeGrand320;    
    public Station MairieNoisyLeGrand310;

    public StationProvider() {
        this.MairieNoisyLeGrand310 = new Station("310_626_638", "Mairie de Noisy le Grand");
        this.MairieNoisyLeGrand320 = new Station("320_668_707", "Mairie de Noisy le Grand");
    }
}
