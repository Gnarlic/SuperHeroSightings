/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Superbeing;
import java.util.List;

/**
 *
 * @author Elnic
 */
public interface SuperbeingDao {

    List<Superbeing> getAllSuperbeings();

    List<Superbeing> getSuperbeingsAtLocation(Location location);

    Superbeing getSuperbeing(int superId);

    Superbeing addSuper(Superbeing superbeing);

    public void editSuper(Superbeing superbeing);

    public void deleteSuper(Superbeing superbeing);

    public List<Superbeing> searchSuperbeing(String superName);

    public List<Superbeing> getMembers(Organization organization);
}
