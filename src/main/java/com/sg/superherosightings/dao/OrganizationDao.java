/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superbeing;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public interface OrganizationDao {
    
    List<Organization> getAllOrganizations();
    
    List<Organization> getOrganizationsForSuperbeing(Superbeing superbeing);
    
    Organization getOrganization(int organizationId);
    
    Organization addOrganization(Organization organization);
    
    public void editOrganization(Organization organization);
    
    public void deleteOrganization(Organization organization);
    
    List<Organization> searchOrganizations(String organization);
    
    public void addMember(Superbeing superbeing, Organization organization);
    
}
