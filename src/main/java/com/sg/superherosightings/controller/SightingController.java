/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;


import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.OrganizationService;
import com.sg.superherosightings.service.SightingService;
import com.sg.superherosightings.service.SuperbeingService;
import java.util.Date;
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
public class SightingController {
    
    
    @Inject
    SuperbeingService suService;
    @Inject
    SightingService sService;
    @Inject
    OrganizationService oService;
    @Inject
    LocationService lService;
    
    @RequestMapping(value="/sighting/sightinghome", method=RequestMethod.GET)
    public String sightingHome(Model model) {
        model.addAttribute("sightings", sService.getAllSightings());
        return "/sighting/sightinghome";
    }
    
    @RequestMapping(value="/sighting/sightingdetails", method=RequestMethod.GET)
    public String sightingDetails(HttpServletRequest request, Model model) {
        String sightingIdString = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdString);
        model.addAttribute("sighting", sService.getSighting(sightingId));
        return "/sighting/sightingdetails";
    }
    
    @RequestMapping(value="/sighting/addsighting", method=RequestMethod.GET)
    public String addSightingPage(Model model) {
        model.addAttribute("sighting", new Sighting());
        model.addAttribute("locations", lService.getAllLocations());
        model.addAttribute("superbeings", suService.getAllSuperbeings());
        return "/sighting/addsighting";
    }
    
    @RequestMapping(value="/sighting/addSighting", method=RequestMethod.POST)
    public String addSighting(@Valid @ModelAttribute("sighting") Sighting sighting, HttpServletRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/sighting/addsighting";
        } else {
            sService.addSighting(sighting, Integer.parseInt(request.getParameter("superbeingId")));
            model.addAttribute("sightings", sService.getAllSightings());
            return "/sighting/sightinghome";
        }
    }
    
    @RequestMapping(value="/sighting/editsighting", method=RequestMethod.GET)
    public String editSightingPage(HttpServletRequest request, Model model) {
        String sightingIdString = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdString);
        model.addAttribute("sighting", sService.getSighting(sightingId));
        model.addAttribute("locations", lService.getAllLocations());
        model.addAttribute("superbeings", suService.getAllSuperbeings());
        return "/sighting/editsighting";
    }
    
    @RequestMapping(value="/sighting/editSighting", method=RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, HttpServletRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/sighting/editsighting";
        } else {
            sService.editSighting(sighting, Integer.parseInt(request.getParameter("superbeingId")));
            model.addAttribute("sighting", sService.getAllSightings());
            return "/sighting/sightinghome";
        }
    }
    
    @RequestMapping(value="/sighting/searchDate", method=RequestMethod.GET)
    public String searchDate(@ModelAttribute("Date") Date date, HttpServletRequest request, Model model) {
        model.addAttribute("sightings", sService.getSightingsOnDate(request.getParameter("sightingDate")));

        return "/sighting/sightinghome";
    }
    
    @RequestMapping(value="/sighting/deleteSighting", method=RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model) {
        String sightingIdString = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdString);
        sService.deleteSighting(sightingId);
        model.addAttribute("sightings", sService.getAllSightings());
        return "/sighting/sightinghome";
    }
    
}
