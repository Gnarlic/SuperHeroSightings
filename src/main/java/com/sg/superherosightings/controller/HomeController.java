/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.OrganizationService;
import com.sg.superherosightings.service.SightingService;
import com.sg.superherosightings.service.SuperbeingService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Elnic
 */
@Controller
public class HomeController {

    @Inject
    LocationService lService;
    @Inject
    OrganizationService oService;
    @Inject
    SuperbeingService suService;
    @Inject
    SightingService sService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage(Model model) {

        model.addAttribute("sightings", sService.getLast10Sightings());
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(HttpServletRequest request, Model model) {
        String searchCategory = request.getParameter("searchCategory");
        String searchTerm = request.getParameter("searchTerm");
        try {
            switch (searchCategory) {
                case "Superbeing":
                    model.addAttribute("superbeing", suService.searchSuperbeing(searchTerm, model));
                    return "/superbeing/superbeinghome";
                case "Location":
                    model.addAttribute("locations", lService.searchLocations(searchTerm, model));
                    return "/location/locationhome";
                case "Organization":
                    model.addAttribute("organization", oService.searchOrganizations(searchTerm, model));
                    return "/organization/organizationhome";
            }
        } catch (EmptyResultDataAccessException | BadSqlGrammarException e) {
            model.addAttribute("errorMessage", "<div class=\"text-center alert alert-danger\">No Results. Please Try Again</div>");
            model.addAttribute("sightings", sService.getLast10Sightings());
            return "/index";
        }
        return "redirect:/";
    }

}
