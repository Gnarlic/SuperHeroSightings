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

/**
 *
 * @author Elnic
 */
public class SightingServiceTest {
    
    private LocationService lService;
    private OrganizationService oService;
    private SuperbeingService suService;
    private SightingService sService;
    
    public SightingServiceTest() {
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
     * Test of getAllSightings method, of class SightingDao.
     */
    @Test
    public void testAddAndGetAllSightings() throws ParseException {
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

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sService.addSighting(sight, testSuper.getSuperbeingId());

        assertEquals(1, sService.getAllSightings().size());
    }

    /**
     * Test of getSightingsWithSuperbeing method, of class SightingDao.
     */
    @Test
    public void testGetSightingsWithSuperbeing() throws ParseException {
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

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sService.addSighting(sight, testSuper.getSuperbeingId());

        List<SightingInfo> sifo = sService.getSightingsWithSuperbeing(testSuper.getSuperbeingId());

        assertEquals(1, sifo.size());
    }

    /**
     * Test of getSightingsOnDate method, of class SightingDao.
     */
    @Test
    public void testGetSightingsOnDate() throws ParseException {
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

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sService.addSighting(sight, testSuper.getSuperbeingId());

        List<SightingInfo> sighting = sService.getSightingsOnDate("2010-10-10");

        assertEquals(1, sighting.size());
    }

    /**
     * Test of editSighting method, of class SightingDao.
     */
    @Test
    public void testEditSighting() throws ParseException {
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
        
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);
        
        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);
        
        sService.addSighting(sight, testSuper.getSuperbeingId());
        
        Date date2 = fmt.parse("2012-10-10");
        sight.setSightingDate(date2);
        sService.editSighting(sight, testSuper.getSuperbeingId());
        
        assertEquals(date2, sight.getSightingDate());
    }

    /**
     * Test of deleteSighting method, of class SightingDao.
     */
    @Test
    public void testDeleteSighting() throws ParseException {
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

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suService.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sService.addSighting(sight, testSuper.getSuperbeingId());
        
        SightingInfo sifo = sService.getSighting(sight.getSightingId());
        sService.deleteSighting(sifo.getSightingId());
        
        assertTrue(sService.getAllSightings().isEmpty());
    }
    public class SightingServiceImpl implements SightingService {

        public List<SightingInfo> getAllSightings() {
            return null;
        }

        public List<Sighting> getLastTenSightings() {
            return null;
        }

        public List<SightingInfo> getSightingsWithSuperbeing(int superId) {
            return null;
        }

        public List<SightingInfo> getSightingsAtLocation(int locationId) {
            return null;
        }

        public List<SightingInfo> getSightingsOnDate(String date) {
            return null;
        }

        public SightingInfo getSighting(int sightingId) {
            return null;
        }

        public Sighting addSighting(Sighting sighting, int superId) {
            return null;
        }

        public void editSighting(Sighting sighting, int superbeingId) {
        }

        public void deleteSighting(int sightingId) {
        }

        public List<SightingInfo> getLast10Sightings() {
            return null;
        }

        public List<SightingInfo> sightingAndLocationForSuper(int superbeingId) {
            return null;
        }
    }
    
}
