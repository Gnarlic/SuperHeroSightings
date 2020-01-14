/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.service.LocationService;
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
public class LocationController {
    
    @Inject
    LocationService lService;
    
    @RequestMapping(value="/location/locationhome", method=RequestMethod.GET)
    public String locationHome(HttpServletRequest request, Model model) {
        model.addAttribute("locations", lService.getAllLocations());
        return "/location/locationhome";
    }
    
    @RequestMapping(value="/location/locationdetails", method=RequestMethod.GET)
    public String locationDetails(HttpServletRequest request, Model model) {
        String locationIdString = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdString);
        model.addAttribute("location", lService.getLocation(locationId));
        return "/location/locationdetails";
    }
    
    @RequestMapping(value="/location/addlocation", method=RequestMethod.GET)
    public String addLocationPage(Model model) {
        model.addAttribute("location", new Location());
        return "/location/addlocation";
    }
    
    @RequestMapping(value="/location/addLocation", method=RequestMethod.POST)
    public String addLocation(@Valid @ModelAttribute("location") Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("location", new Location());
            return "/location/addlocation";
        } else {
            lService.addLocation(location);
            model.addAttribute("locations", lService.getAllLocations());
            return "/location/locationhome";
        }
    }
    
    @RequestMapping(value="/location/editlocation", method=RequestMethod.GET)
    public String editLocationPage(HttpServletRequest request, Model model) {
        String locationIdString = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdString);
        model.addAttribute("location", lService.getLocation(locationId));
        return "/location/editlocation";
    }
    
    @RequestMapping(value="/location/editLocation", method=RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("locations", lService.getAllLocations());
            return "/location/locationhome";
        } else {
            lService.editLocation(location);
            model.addAttribute("locations", lService.getAllLocations());
            return "/location/locationhome";
        }
    }
    
    @RequestMapping(value="/location/deleteLocation", method=RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
        String locationIdString = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdString);
        lService.deleteLocation(locationId);
        model.addAttribute("locations", lService.getAllLocations());
        return "/location/locationhome";
    }
}
