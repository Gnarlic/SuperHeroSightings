/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Superbeing;
import java.util.List;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public interface SuperbeingService {
    
    List<Superbeing> getAllSuperbeings();
    
    List<Superbeing> getSuperbeingsAtLocation(int locationId);
    
    Superbeing getSuperbeing(int superId);
    
    Superbeing addSuper(Superbeing superbeing);
    
    public void editSuper(Superbeing superbeing);
    
    public void deleteSuper(int superId);
    
    public List<Superbeing> searchSuperbeing(String superName, Model model);
    
    public List<Superbeing> getMembers(int organizationId);
    
}
