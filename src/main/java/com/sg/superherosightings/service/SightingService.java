/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingInfo;
import java.util.List;

/**
 *
 * @author Elnic
 */
public interface SightingService {
    
    List<SightingInfo> getAllSightings();
    
    List<Sighting> getLastTenSightings();
    
    List<SightingInfo> getSightingsWithSuperbeing(int superId);
    
    List<SightingInfo> getSightingsAtLocation(int locationId);
    
    List<SightingInfo> getSightingsOnDate(String date);
    
    SightingInfo getSighting(int sightingId);
    
    Sighting addSighting(Sighting sighting, int superId);
    
    public void editSighting(Sighting sighting, int superbeingId);
    
    public void deleteSighting(int sightingId);
    
    public List<SightingInfo> getLast10Sightings();
    
    public List<SightingInfo> sightingAndLocationForSuper(int superbeingId);
    
}
