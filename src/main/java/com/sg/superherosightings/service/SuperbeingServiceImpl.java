/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SuperbeingDao;
import com.sg.superherosightings.model.Superbeing;
import java.util.List;
import javax.inject.Inject;

import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public class SuperbeingServiceImpl implements SuperbeingService {
    
    SuperbeingDao suDao;
    @Inject
    OrganizationDao oDao;
    @Inject
    LocationDao lDao;
    @Inject
    SuperbeingServiceImpl(SuperbeingDao superDao) {
        this.suDao = superDao;
    }

    @Override
    public List<Superbeing> getAllSuperbeings() {
        return suDao.getAllSuperbeings();
    }

    @Override
    public List<Superbeing> getSuperbeingsAtLocation(int locationId) {
        return suDao.getSuperbeingsAtLocation(lDao.getLocation(locationId));
    }

    @Override
    public Superbeing getSuperbeing(int superId) {
        return suDao.getSuperbeing(superId);
    }

    @Override
    public Superbeing addSuper(Superbeing superbeing) {
        return suDao.addSuper(superbeing);
    }

    @Override
    public void editSuper(Superbeing superbeing) {
        suDao.editSuper(superbeing);
    }

    @Override
    public void deleteSuper(int superId) {
        suDao.deleteSuper(suDao.getSuperbeing(superId));
    }

    @Override
    public List<Superbeing> searchSuperbeing(String superName, Model model) {
        List<Superbeing> superbeing = suDao.searchSuperbeing(superName);
        if (superbeing.isEmpty()) {
            model.addAttribute("errorMessage", "<div class=\"text-center alert alert-danger\">No Results</div>");
            return superbeing;
        } else {
            return superbeing;
        }
    }

    @Override
    public List<Superbeing> getMembers(int organizationId) {
        return suDao.getMembers(oDao.getOrganization(organizationId));
    }
    
}
