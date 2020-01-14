/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Superbeing;
import java.sql.SQLException;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public interface LocationDao {
    
    List<Location> getAllLocations();
    
    List<Location> getLocationsForSuperbeing(Superbeing superbeing);
    
    Location getLocation(int locationId);
    
    Location addLocation(Location location);
    
    public void editLocation(Location location);
    
    public void deleteLocation(Location location);
    
    public List<Location> searchLocations(String location);
    
}
