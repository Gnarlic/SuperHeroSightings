/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SuperbeingDao;
import com.sg.superherosightings.model.Location;
import java.util.List;
import javax.inject.Inject;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public class LocationServiceImpl implements LocationService {

    private LocationDao lDao;
    @Inject
    SuperbeingDao suDao;

    @Inject
    LocationServiceImpl(LocationDao lDao) {
        this.lDao = lDao;
    }

    @Override
    public List<Location> getAllLocations() {
        return lDao.getAllLocations();
    }

    @Override
    public List<Location> getLocationsForSuperbeing(int superId) {
        return lDao.getLocationsForSuperbeing(suDao.getSuperbeing(superId));
    }

    @Override
    public Location getLocation(int locationId) {
        return lDao.getLocation(locationId);
    }

    @Override
    public Location addLocation(Location location) {
        if (location.getLatitude() == null || location.getLongitude() == null || location.getLatitude().equals("") || location.getLongitude().equals("")) {
            location.setLatitude("00.0");
            location.setLongitude("00.0");
        }
        lDao.addLocation(location);
        return location;
    }

    @Override
    public void editLocation(Location location) {
        lDao.editLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        lDao.deleteLocation(lDao.getLocation(locationId));
    }

    @Override
    public List<Location> searchLocations(String location, Model model) {
        if (lDao.searchLocations(location).isEmpty()) {
            model.addAttribute("errorMessage", "<div class=\"text-center alert alert-danger\">No Results</div>");
        }
        return lDao.searchLocations(location);
    }

}
