/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SuperbeingDao;
import com.sg.superherosightings.model.Organization;
import java.util.List;
import javax.inject.Inject;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public class OrganizationServiceImpl implements OrganizationService{
    
    private OrganizationDao oDao;
    @Inject
    SuperbeingDao suDao;
    
    @Inject
    OrganizationServiceImpl(OrganizationDao oDao) {
        this.oDao = oDao;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return oDao.getAllOrganizations();
    }

    @Override
    public List<Organization> getOrganizationsForSuperbeing(int superId) {
        return oDao.getOrganizationsForSuperbeing(suDao.getSuperbeing(superId));
    }

    @Override
    public Organization getOrganization(int organizationId) {
        return oDao.getOrganization(organizationId);
    }

    @Override
    public Organization addOrganization(Organization organization) {
        return oDao.addOrganization(organization);
    }

    @Override
    public void editOrganization(Organization organization) {
        oDao.editOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        oDao.deleteOrganization(oDao.getOrganization(organizationId));
    }

    @Override
    public List<Organization> searchOrganizations(String organization, Model model) {
        
        List<Organization> org = oDao.searchOrganizations(organization);
        if (org.isEmpty()) {
            model.addAttribute("errorMessage", "<div class=\"text-center alert alert-danger\">No Results</div>");
            return org;
        } else {
            return org;
        }
    }

    @Override
    public void addMember(int superbeingId, int organizationId) {
        oDao.addMember(suDao.getSuperbeing(superbeingId), oDao.getOrganization(organizationId));
    }
    
}
