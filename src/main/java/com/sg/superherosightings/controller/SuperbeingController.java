/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Superbeing;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.OrganizationService;
import com.sg.superherosightings.service.SightingService;
import com.sg.superherosightings.service.SuperbeingService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Elnic
 */
@Controller
public class SuperbeingController {
    
    @Inject
    SuperbeingService suService;
    @Inject
    SightingService sService;
    @Inject
    OrganizationService oService;
    @Inject
    LocationService lService;
    
    @RequestMapping(value="/superbeing/superbeinghomePage", method=RequestMethod.GET)
    public String superbeingHomePage(Model model) {
        model.addAttribute("superbeing", suService.getAllSuperbeings());
        return "/superbeing/superbeinghome";
    }
    
    @RequestMapping(value="/superbeing/addSuper", method=RequestMethod.GET)
    public String addSuperbeingPage(Model model) {
        model.addAttribute("superbeing", new Superbeing());
        return "/superbeing/addsuperbeing";
    }
    
    @RequestMapping(value="/superbeing/editSuper", method=RequestMethod.GET)
    public String editSuperbeing(HttpServletRequest request, Model model) {
        String superId = request.getParameter("superbeingId");
        int superbeingId = Integer.parseInt(superId);
        Superbeing superbeing = suService.getSuperbeing(superbeingId);
        model.addAttribute("superbeing", superbeing);
        return "/superbeing/editsuperbeing";
    }
    
    @RequestMapping(value="/superbeing/editSuperbeing", method=RequestMethod.POST)
    public String superbeingEdit(@Valid @ModelAttribute("superbeing") Superbeing superbeingE, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "redirect:/";
        } else {
            suService.editSuper(superbeingE);
            model.addAttribute("superbeing", suService.getAllSuperbeings());
            return "/superbeing/superbeinghome";
        }
    }
    
    @RequestMapping(value="/superbeing/deleteSuper", method=RequestMethod.GET)
    public String deleteSuperbeing(HttpServletRequest request, Model model) {
        String superbeingIdString = request.getParameter("superbeingId");
        int superbeingId = Integer.parseInt(superbeingIdString);
        suService.deleteSuper(superbeingId);
        model.addAttribute("superbeing", suService.getAllSuperbeings());
        return "superbeing/superbeinghome";
    }
    
    
    
    @RequestMapping(value="/superbeing/addSuperbeing", method=RequestMethod.POST)
    public String addSuperbeing(@Valid @ModelAttribute("superbeing") Superbeing superbeing, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/superbeing/addsuperbeing";
        } else {
            suService.addSuper(superbeing);
            model.addAttribute("superbeing", suService.getAllSuperbeings());
            return "/superbeing/superbeinghome";
        }
        
    }
    
    @RequestMapping(value="/superbeing/superbeingdetails", method=RequestMethod.GET)
    public String superbeingDetails(HttpServletRequest request, Model model) {
        String superId = request.getParameter("superbeingId");
        int superbeingId = Integer.parseInt(superId);
        model.addAttribute("superbeing", suService.getSuperbeing(superbeingId));
        model.addAttribute("sightings", sService.getSightingsWithSuperbeing(superbeingId));
        model.addAttribute("organizations", oService.getOrganizationsForSuperbeing(superbeingId));
        return "/superbeing/superbeingdetails";
    }
    
}
