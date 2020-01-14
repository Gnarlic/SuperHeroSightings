/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingInfo;
import com.sg.superherosightings.model.Superbeing;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
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
public class LocationDaoTest {

    private LocationDao lDao;
    private OrganizationDao oDao;
    private SuperbeingDao suDao;
    private SightingDao sDao;

    public LocationDaoTest() {
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
        lDao = ctx.getBean("LocationDao", LocationDao.class);
        oDao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        suDao = ctx.getBean("SuperbeingDao", SuperbeingDao.class);
        sDao = ctx.getBean("SightingDao", SightingDao.class);
        List<Superbeing> superbeings = suDao.getAllSuperbeings();
        for (Superbeing currentSuper : superbeings) {
            suDao.deleteSuper(currentSuper);
        }
        List<Organization> org = oDao.getAllOrganizations();
        for (Organization currentOrg : org) {
            oDao.deleteOrganization(currentOrg);
        }
        List<Location> loc = lDao.getAllLocations();
        for (Location currentLoc : loc) {
            lDao.deleteLocation(currentLoc);
        }
        List<SightingInfo> sight = sDao.getAllSightings();
        for (SightingInfo currentSight : sight) {
            sDao.deleteSighting(currentSight);
        }

    
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllLocations method, of class LocationDao.
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
        
        lDao.addLocation(loc2);
        lDao.addLocation(loc);
        assertEquals(2, lDao.getAllLocations().size());
    }

    /**
     * Test of getLocationsForSuperbeing method, of class LocationDao.
     */
    @Test
    public void testGetLocationsForSuperbeing() throws ParseException {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);
        
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
        
        lDao.addLocation(loc2);
        lDao.addLocation(loc);
        
        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);
        
        sDao.addSighting(sight, testSuper);
        
        List<Location> locList = lDao.getLocationsForSuperbeing(testSuper);
        
        assertEquals(0, locList.size());
        
        
    }
    
    /**
     * Test of addLocation method, of class LocationDao.
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
        
        lDao.addLocation(loc);
        
        assertEquals(1, lDao.getAllLocations().size());
    }

    /**
     * Test of editLocation method, of class LocationDao.
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
        lDao.addLocation(loc);
        loc.setLocationName("Lowes");
        lDao.editLocation(loc);
        Location loc2 = lDao.getLocation(loc.getLocationId());
        assertEquals(loc2.getLocationName(), "Lowes");
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
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
        lDao.addLocation(loc);
        int size = lDao.getAllLocations().size();
        lDao.deleteLocation(loc);
        int size2 = lDao.getAllLocations().size();
        assertEquals(size, size2+1);
        
    }

    /**
     * Test of searchLocations method, of class LocationDao.
     */
    @Test
    public void testSearchLocations() {
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
        lDao.addLocation(loc);
        List<Location> locList = lDao.searchLocations("h");
        assertEquals(1, locList.size());
        
    }
    

    public class LocationDaoImpl implements LocationDao {

        public List<Location> getAllLocations() {
            return null;
        }

        public List<Location> getLocationsForSuperbeing(Superbeing superbeing) {
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

        public void deleteLocation(Location location) {
        }

        public List<Location> searchLocations(String location) {
            return null;
        }
    }

}
