/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SightingInfo;
import com.sg.superherosightings.model.Superbeing;
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
public class OrganizationServiceTest {
    
    private LocationService lService;
    private OrganizationService oService;
    private SuperbeingService suService;
    private SightingService sService;
    
    public OrganizationServiceTest() {
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
     * Test of getAllOrganizations method, of class OrganizationService.
     */
    @Test
    public void testGetAllOrganizations() {
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
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oService.addOrganization(org);
        List<Organization> org2 = oService.getAllOrganizations();
        assertEquals(1, org2.size());
    }

    /**
     * Test of getOrganizationsForSuperbeing method, of class OrganizationService.
     */
    @Test
    public void testGetOrganizationsForSuperbeing() {
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
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oService.addOrganization(org);
        
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);
        
        oService.addMember(testSuper.getSuperbeingId(), org.getOrganizationId());
        
        List<Organization> orgList = oService.getOrganizationsForSuperbeing(testSuper.getSuperbeingId());
        
        assertEquals(1, orgList.size());
    }

    /**
     * Test of getOrganization method, of class OrganizationService.
     */
    @Test
    public void testAddAndGetOrganization() {
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
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oService.addOrganization(org);
        
        assertEquals(1, oService.getAllOrganizations().size());
    }

    /**
     * Test of editOrganization method, of class OrganizationService.
     */
    @Test
    public void testEditOrganization() {
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
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oService.addOrganization(org);
        
        org.setOrganizationName("Avant gardeners");
        oService.editOrganization(org);
        Organization org2 = oService.getOrganization(org.getOrganizationId());
        assertEquals(org2.getOrganizationName(), "Avant gardeners");
    }

    /**
     * Test of deleteOrganization method, of class OrganizationService.
     */
    @Test
    public void testDeleteOrganization() {
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
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oService.addOrganization(org);
        
        oService.deleteOrganization(org.getOrganizationId());
        
        assertTrue(oService.getAllOrganizations().isEmpty());
    }



    /**
     * Test of addMember method, of class OrganizationService.
     */
    @Test
    public void testAddMember() {
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
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oService.addOrganization(org);
        
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");
        suService.addSuper(testSuper);
        
        oService.addMember(testSuper.getSuperbeingId(), org.getOrganizationId());
        
        assertEquals(oService.getOrganizationsForSuperbeing(testSuper.getSuperbeingId()).size(), 1);
    }

    public class OrganizationServiceImpl implements OrganizationService {

        public List<Organization> getAllOrganizations() {
            return null;
        }

        public List<Organization> getOrganizationsForSuperbeing(int superId) {
            return null;
        }

        public Organization getOrganization(int organizationId) {
            return null;
        }

        public Organization addOrganization(Organization organization) {
            return null;
        }

        public void editOrganization(Organization organization) {
        }

        public void deleteOrganization(int organizationId) {
        }

        public List<Organization> searchOrganizations(String organization, Model model) {
            return null;
        }

        public void addMember(int superbeingId, int organizationId) {
        }
    }
    
}
