/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

/**
 *
 * @author Elnic
 */
import com.sg.superherosightings.model.User;
import com.sg.superherosightings.service.UserService;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Inject
    private UserService service;
    @Inject
    private PasswordEncoder encoder;

 

    // This endpoint retrieves all users from the database and puts the
    // List of users on the model
    @RequestMapping(value = "/user/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = service.getAllUser();
        model.put("users", users);
        return "/user/displayUserList";
    }
    // This endpoint just displays the Add User form

    @RequestMapping(value = "/user/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "/user/addUserForm";
    }
    // This endpoint processes the form data and creates a new User

    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();
        // This example uses a plain HTML form so we must get values 
        // from the request
        newUser.setUsername(req.getParameter("username"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin 
        // box is checked
        newUser.addAuthority("ROLE_SIDEKICK");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        service.addUser(newUser);

        return "redirect:/user/displayUserList";
    }
    // This endpoint deletes the specified User
    
    @RequestMapping(value="/user/editUser", method=RequestMethod.POST)
    public String editUser(HttpServletRequest rq) {
        User editUser = new User();
        
        editUser.setUsername(rq.getParameter("username"));
        String clearPw = rq.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        editUser.setPassword(hashPw);
        editUser.setId(Integer.parseInt(rq.getParameter("id")));
        editUser.addAuthority("ROLE_SIDEKICK");
        if (null != rq.getParameter("isAdmin")) {
            editUser.addAuthority("ROLE_ADMIN");
        } else {
            service.removeAuthority(service.getUser(Integer.parseInt(rq.getParameter("id"))));
        }
        service.editUser(editUser);
        return "redirect:/user/displayUserList";
    }

    @RequestMapping(value = "/user/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("userid") String userid,
            Map<String, Object> model) {
        User user = service.getUser(Integer.parseInt(userid));
        service.deleteUser(user);
        return "redirect:/user/displayUserList";
    }
    
    @RequestMapping(value="/user/editUserForm", method=RequestMethod.GET)
    public String editUserPage(HttpServletRequest request, Model model) {
        model.addAttribute("user", service.getUser(Integer.parseInt(request.getParameter("userid"))));
        return "/user/editUserForm";
    }
    
    
}