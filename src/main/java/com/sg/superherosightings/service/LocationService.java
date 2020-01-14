/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public interface LocationService {
    
    List<Location> getAllLocations();
    
    List<Location> getLocationsForSuperbeing(int superId);
    
    Location getLocation(int locationId);
    
    Location addLocation(Location location);
    
    public void editLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public List<Location> searchLocations(String location, Model model);
    
}
