/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Organization;
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
public class OrganizationController {
    
    @Inject
    SuperbeingService suService;
    @Inject
    SightingService sService;
    @Inject
    OrganizationService oService;
    @Inject
    LocationService lService;
    
   
    @RequestMapping(value="/organization/organizationhome", method=RequestMethod.GET)
    public String organizationHome(Model model) {
        model.addAttribute("organization", oService.getAllOrganizations());
        return "/organization/organizationhome";
    }
    
    @RequestMapping(value="/organization/organizationdetails", method=RequestMethod.GET)
    public String organizationDetails(HttpServletRequest request, Model model) {
        String organizationIdString = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdString);
        Organization o = oService.getOrganization(organizationId);
        model.addAttribute("organization", o);
        model.addAttribute("members", suService.getMembers(organizationId));
        model.addAttribute("location", lService.getLocation(o.getLocationId()));
        
        return "/organization/organizationdetails";
    }
    
    @RequestMapping(value="/organization/addorganization", method=RequestMethod.GET)
    public String addOrganiztionPage(Model model) {
        model.addAttribute("organization", new Organization());
        model.addAttribute("location", lService.getAllLocations());
        return "/organization/addorganization";
    }
    
    @RequestMapping(value="/organization/addOrganization", method=RequestMethod.POST)
    public String addOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "test");
            model.addAttribute("location", lService.getAllLocations());
            return "/organization/addorganization";
        } else {
            oService.addOrganization(organization);
            model.addAttribute("organization", oService.getAllOrganizations());
            return "/organization/organizationhome";
        }
    }
    
    @RequestMapping(value="/organization/editorganization", method=RequestMethod.GET)
    public String editOrganizationPage(HttpServletRequest request, Model model) {
        String organizationIdString = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdString);
        model.addAttribute("organization", oService.getOrganization(organizationId));
        model.addAttribute("location", lService.getAllLocations());
        return "/organization/editorganization";
    }
    
    @RequestMapping(value="/organization/editOrganization", method=RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("organization", oService.getAllOrganizations());
            return "location/locationhome";
        } else {
            oService.editOrganization(organization);
            model.addAttribute("organization", oService.getAllOrganizations());
            return "organization/organizationhome";
        }
    }
    
    @RequestMapping(value="/organization/deleteOrganization", method=RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) {
        oService.deleteOrganization(Integer.parseInt(request.getParameter("organizationId")));
        model.addAttribute("organization", oService.getAllOrganizations());
        return "/organization/organizationhome";
    }
    
    @RequestMapping(value="/organization/addmember", method=RequestMethod.GET)
    public String addMemberPage(HttpServletRequest request, Model model) {
        model.addAttribute("organization", oService.getOrganization(Integer.parseInt(request.getParameter("organizationId"))));
        model.addAttribute("superbeings", suService.getAllSuperbeings());
        return "/organization/addmember";
    }
    
    @RequestMapping(value="/organization/addMember", method=RequestMethod.POST)
    public String addMember(HttpServletRequest request, Model model) {
        int superbeingId = Integer.parseInt(request.getParameter("superbeingId"));
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));
        oService.addMember(superbeingId, organizationId);
        model.addAttribute("organization", oService.getOrganization(organizationId));
        return "/organization/organizationdetails";
    }
    
}
