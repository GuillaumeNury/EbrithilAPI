/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.data;

/**
 *
 * @author Guillaume
 */
public class MainProvider {
    public LineProvider LineProvider;
    public StationProvider StationProvider;
    public RideProvider RideProvider;
    private static MainProvider _MainProvider;

    public MainProvider() {
        this.StationProvider = new StationProvider();
        this.LineProvider = new LineProvider(this.StationProvider);
        this.RideProvider = new RideProvider(this.StationProvider, this.LineProvider);
    }
    
    public static MainProvider getProvider(){
        if(MainProvider._MainProvider == null){
            MainProvider._MainProvider = new MainProvider();
        }
        
        return MainProvider._MainProvider;
    }
    
    public static RideProvider getRideProvider(){
        return MainProvider.getProvider().RideProvider;
    }
}
