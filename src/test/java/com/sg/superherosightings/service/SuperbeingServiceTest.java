/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SuperbeingDao;
import com.sg.superherosightings.dao.SuperbeingDaoTest;
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
public class SuperbeingServiceTest {
    
    private LocationService lService;
    private OrganizationService oService;
    private SuperbeingService suService;
    private SightingService sService;
    
    public SuperbeingServiceTest() {
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
     * Test of getAllSuperbeings method, of class SuperbeingDao.
     */
    @Test
    public void testGetAllSuperbeings() {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);
        
        assertEquals(1, suService.getAllSuperbeings().size());
    }

    /**
     * Test of getSuperbeing method, of class SuperbeingDao.
     */
    @Test
    public void testAddAndGetSuperbeing() {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);
        
        assertEquals(1, suService.getAllSuperbeings().size());
    }

    /**
     * Test of editSuper method, of class SuperbeingDao.
     */
    @Test
    public void testEditSuper() {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);
        
        testSuper.setSuperbeingName("Bubs");
        suService.editSuper(testSuper);
        
        assertEquals("Bubs", testSuper.getSuperbeingName());
    }

    /**
     * Test of deleteSuper method, of class SuperbeingDao.
     */
    @Test
    public void testDeleteSuper() {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);
        
        suService.deleteSuper(testSuper.getSuperbeingId());
        
        assertTrue(suService.getAllSuperbeings().isEmpty());
    }


    /**
     * Test of getMembers method, of class SuperbeingDao.
     */
    @Test
    public void testGetMembers() {
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
        
        List<Superbeing> supList = suService.getMembers(org.getOrganizationId());
        
        assertEquals(1, supList.size());
        
    }

    public class SuperbeingServiceImpl implements SuperbeingService {

        public List<Superbeing> getAllSuperbeings() {
            return null;
        }

        public List<Superbeing> getSuperbeingsAtLocation(int locationId) {
            return null;
        }

        public Superbeing getSuperbeing(int superId) {
            return null;
        }

        public Superbeing addSuper(Superbeing superbeing) {
            return null;
        }

        public void editSuper(Superbeing superbeing) {
        }

        public void deleteSuper(int superId) {
        }

        public List<Superbeing> searchSuperbeing(String superName, Model model) {
            return null;
        }

        public List<Superbeing> getMembers(int organizationId) {
            return null;
        }
    }
    
}
