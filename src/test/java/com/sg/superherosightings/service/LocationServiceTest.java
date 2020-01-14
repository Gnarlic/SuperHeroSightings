/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingInfo;
import com.sg.superherosightings.model.Superbeing;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public class LocationServiceTest {
    
    private LocationService lService;
    private OrganizationService oService;
    private SuperbeingService suService;
    private SightingService sService;
    
    public LocationServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        lService = ctx.getBean("LocationService", LocationService.class);
        oService = ctx.getBean("OrganizationService", OrganizationService.class);
        suService = ctx.getBean("SuperbeingService", SuperbeingService.class);
        sService = ctx.getBean("SightingService", SightingService.class);
        List<Superbeing> superbeings = suService.getAllSuperbeings();
        for (Superbeing currentSuper : superbeings) {
            suService.deleteSuper(currentSuper.getSuperbeingId());
        }
        List<Organization> org = oService.getAllOrganizations();
        for (Organization currentOrg : org) {
            oService.deleteOrganization(currentOrg.getOrganizationId());
        }
        List<Location> loc = lService.getAllLocations();
        for (Location currentLoc : loc) {
            lService.deleteLocation(currentLoc.getLocationId());
        }
        List<SightingInfo> sight = sService.getAllSightings();
        for (SightingInfo currentSight : sight) {
            sService.deleteSighting(currentSight.getSightingId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllLocations method, of class LocationService.
     */
    @Test
    public void testGetAllLocations() {
        Location loc = new Location();
        loc.setLocationName("Home Depot");
        loc.setLocationDescription("Updo your house");
        loc.setLocationAddress("1234");
        loc.setLocationCityName("city");
        loc.setLocationStreetName("street");
        loc.setLocationStateName("Oregon");
        loc.setLocationZipCode("97739");
        loc.setLatitude("0");
        loc.setLongitude("0");
        
        Location loc2 = new Location(); 
        loc2.setLocationName("Elevator World");
        loc2.setLocationDescription("Up and away");
        loc2.setLocationAddress("9878");
        loc2.setLocationCityName("town");
        loc2.setLocationStreetName("road");
        loc2.setLocationStateName("Oregon");
        loc2.setLocationZipCode("97738");
        loc2.setLatitude("1");
        loc2.setLongitude("1");
        
        lService.addLocation(loc2);
        lService.addLocation(loc);
        
        assertEquals(2, lService.getAllLocations().size());
    }

    /**
     * Test of getLocationsForSuperbeing method, of class LocationService.
     */
    @Test
    public void testGetLocationsForSuperbeing() throws ParseException {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);
        
        Location loc = new Location();
        loc.setLocationName("Home Depot");
        loc.setLocationDescription("Updo your house");
        loc.setLocationAddress("1234");
        loc.setLocationCityName("city");
        loc.setLocationStreetName("street");
        loc.setLocationStateName("Oregon");
        loc.setLocationZipCode("97739");
        loc.setLatitude("0");
        loc.setLongitude("0");
        
        Location loc2 = new Location(); 
        loc2.setLocationName("Elevator World");
        loc2.setLocationDescription("Up and away");
        loc2.setLocationAddress("9878");
        loc2.setLocationCityName("town");
        loc2.setLocationStreetName("road");
        loc2.setLocationStateName("Oregon");
        loc2.setLocationZipCode("97738");
        loc2.setLatitude("1");
        loc2.setLongitude("1");
        
        lService.addLocation(loc2);
        lService.addLocation(loc);
        
        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);
        
        sService.addSighting(sight, testSuper.getSuperbeingId());
        
        List<Location> locList = lService.getLocationsForSuperbeing(testSuper.getSuperbeingId());
        
        assertEquals(0, locList.size());
    }

    /**
     * Test of getLocation method, of class LocationService.
     */
    @Test
    public void testAddAndGetLocation() {
        Location loc = new Location();
        loc.setLocationName("Hooters");
        loc.setLocationDescription("Best wings");
        loc.setLocationAddress("1234");
        loc.setLocationStreetName("fluff st");
        loc.setLocationCityName("Whiffle-bat City");
        loc.setLocationStateName("Oregon");
        loc.setLocationZipCode("97730");
        loc.setLatitude("0");
        loc.setLongitude("0");
        
        lService.addLocation(loc);
        
        assertEquals(1, lService.getAllLocations().size());
    }

    /**
     * Test of editLocation method, of class LocationService.
     */
    @Test
    public void testEditLocation() {
        Location loc = new Location();
        loc.setLocationName("Home Depot");
        loc.setLocationDescription("Updo your house");
        loc.setLocationAddress("1234");
        loc.setLocationCityName("city");
        loc.setLocationStreetName("street");
        loc.setLocationStateName("Oregon");
        loc.setLocationZipCode("97739");
        loc.setLatitude("0");
        loc.setLongitude("0");
        lService.addLocation(loc);
        loc.setLocationName("Lowes");
        lService.editLocation(loc);
        Location loc2 = lService.getLocation(loc.getLocationId());
        assertEquals(loc2.getLocationName(), "Lowes");
    }

    /**
     * Test of deleteLocation method, of class LocationService.
     */
    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setLocationName("Home Depot");
        loc.setLocationDescription("Updo your house");
        loc.setLocationAddress("1234");
        loc.setLocationCityName("city");
        loc.setLocationStreetName("street");
        loc.setLocationStateName("Oregon");
        loc.setLocationZipCode("97739");
        loc.setLatitude("0");
        loc.setLongitude("0");
        lService.addLocation(loc);
        int size = lService.getAllLocations().size();
        lService.deleteLocation(loc.getLocationId());
        int size2 = lService.getAllLocations().size();
        assertEquals(size, size2+1);
    }


    public class LocationServiceImpl implements LocationService {

        public List<Location> getAllLocations() {
            return null;
        }

        public List<Location> getLocationsForSuperbeing(int superId) {
            return null;
        }

        public Location getLocation(int locationId) {
            return null;
        }

        public Location addLocation(Location location) {
            return null;
        }

        public void editLocation(Location location) {
        }

        public void deleteLocation(int locationId) {
        }

        public List<Location> searchLocations(String location, Model model) {
            return null;
        }
    }
    
}
