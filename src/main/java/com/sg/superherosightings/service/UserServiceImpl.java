/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.UserDao;
import com.sg.superherosightings.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Elnic
 */
public class UserServiceImpl implements UserService {
    
    @Inject
    private UserDao uDao;

    @Override
    public User addUser(User newUser) {
        return uDao.addUser(newUser);
    }

    @Override
    public void deleteUser(User user) {
        uDao.deleteUser(user.getUsername());
    }

    @Override
    public List<User> getAllUser() {
        return uDao.getAllUsers();
    }

    @Override
    public User getUser(int userid) {
        return uDao.getUser(userid);
    }

    @Override
    public void editUser(User user) {
        uDao.editUser(user);
    }

    @Override
    public void removeAuthority(User user) {
        uDao.removeAuthority(user);
    }
    
}
