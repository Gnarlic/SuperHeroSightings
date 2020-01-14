/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperbeingDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingInfo;
import com.sg.superherosightings.model.Superbeing;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Elnic
 */
public class SightingServiceImpl implements SightingService {
    
    private SightingDao sDao;
    @Inject
    SuperbeingDao suDao;
    @Inject
    LocationDao lDao;
    
    @Inject
    SightingServiceImpl(SightingDao sDao) {
        this.sDao = sDao;
    }

    @Override
    public List<SightingInfo> getAllSightings() {
        return sDao.getAllSightings();
    }

    @Override
    public List<Sighting> getLastTenSightings() {
        return sDao.getLastTenSightings();
    }

    @Override
    public List<SightingInfo> getSightingsWithSuperbeing(int superId) {
        Superbeing superbeing = suDao.getSuperbeing(superId);
        return sDao.getSightingsWithSuperbeing(superbeing);
    }

    @Override
    public List<SightingInfo> getSightingsAtLocation(int locationId) {
        Location location = lDao.getLocation(locationId);
        return sDao.getSightingsAtLocation(location);
    }

    @Override
    public List<SightingInfo> getSightingsOnDate(String date) {
        return sDao.getSightingsOnDate(date);
    }

    @Override
    public SightingInfo getSighting(int sightingId) {
        return sDao.getSighting(sightingId);
    }

    @Override
    public Sighting addSighting(Sighting sighting, int superId) {
        Superbeing superbeing = suDao.getSuperbeing(superId);
        return sDao.addSighting(sighting, superbeing);
    }

    @Override
    public void editSighting(Sighting sighting, int superbeingId) {
        Superbeing superbeing = suDao.getSuperbeing(superbeingId);
        sDao.editSighting(sighting, superbeing);
    }

    @Override
    public void deleteSighting(int sightingId) {
        SightingInfo sight = sDao.getSighting(sightingId);
        sDao.deleteSighting(sight);
    }

    @Override
    public List<SightingInfo> getLast10Sightings() {
        return sDao.getLast10Sightings();
    }

    @Override
    public List<SightingInfo> sightingAndLocationForSuper(int superbeingId) {
        Superbeing superbeing = suDao.getSuperbeing(superbeingId);
        return sDao.sightingAndLocationForSuper(superbeing);
    }
    
    
    
}
