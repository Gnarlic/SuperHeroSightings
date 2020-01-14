/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.User;
import java.util.List;

/**
 *
 * @author Elnic
 */
public interface UserService {
    
    public User addUser(User newUser);
    
    public void deleteUser(User user);
    
    public List<User> getAllUser();
    
    public User getUser(int userid);
    
    public void editUser(User user);
    
    public void removeAuthority(User user);
    
}
