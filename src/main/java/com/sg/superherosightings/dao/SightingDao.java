 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingInfo;
import com.sg.superherosightings.model.Superbeing;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public interface SightingDao {
    
    List<SightingInfo> getAllSightings();
    
    List<Sighting> getLastTenSightings();
    
    List<SightingInfo> getSightingsWithSuperbeing(Superbeing superbeing);
    
    List<SightingInfo> getSightingsAtLocation(Location location);
    
    List<SightingInfo> getSightingsOnDate(String date);
    
    SightingInfo getSighting(int sightingId);
    
    Sighting addSighting(Sighting sighting, Superbeing superbeing);
    
    public void editSighting(Sighting sighting, Superbeing superbeing);
    
    public void deleteSighting(SightingInfo sighting);
    
    public List<SightingInfo> getLast10Sightings();
    
    public List<SightingInfo> sightingAndLocationForSuper(Superbeing superbeing);
    
}
