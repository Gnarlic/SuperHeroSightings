/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Organization;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public interface OrganizationService {
    
    List<Organization> getAllOrganizations();
    
    List<Organization> getOrganizationsForSuperbeing(int superId);
    
    Organization getOrganization(int organizationId);
    
    Organization addOrganization(Organization organization);
    
    public void editOrganization(Organization organization);
    
    public void deleteOrganization(int organizationId);
    
    List<Organization> searchOrganizations(String organization, Model model);
    
    public void addMember(int superbeingId, int organizationId);
    
}
