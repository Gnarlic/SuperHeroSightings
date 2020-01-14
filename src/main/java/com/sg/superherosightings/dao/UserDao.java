/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

/**
 *
 * @author Elnic
 */
import com.sg.superherosightings.model.User;
import java.util.List;

public interface UserDao {

 public User addUser(User newUser);

 public void deleteUser(String username);
 
 public List<User> getAllUsers();
 
 public User getUser(int user);
 
 public void editUser(User user);
 
 public void removeAuthority(User user);
 

}