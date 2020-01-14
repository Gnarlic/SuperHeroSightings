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
public class SightingDaoTest {

    private LocationDao lDao;
    private OrganizationDao oDao;
    private SuperbeingDao suDao;
    private SightingDao sDao;

    public SightingDaoTest() {
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

        lDao.addLocation(loc2);

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sDao.addSighting(sight, testSuper);

        assertEquals(1, sDao.getAllSightings().size());
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

        lDao.addLocation(loc2);

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sDao.addSighting(sight, testSuper);

        List<SightingInfo> sifo = sDao.getSightingsWithSuperbeing(testSuper);

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

        lDao.addLocation(loc2);

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sDao.addSighting(sight, testSuper);

        List<SightingInfo> sighting = sDao.getSightingsOnDate("2010-10-10");

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
        
        lDao.addLocation(loc2);
        
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);
        
        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);
        
        sDao.addSighting(sight, testSuper);
        
        Date date2 = fmt.parse("2012-10-10");
        sight.setSightingDate(date2);
        sDao.editSighting(sight, testSuper);
        
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

        lDao.addLocation(loc2);

        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);

        Sighting sight = new Sighting();
        sight.setLocationId(loc2.getLocationId());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("2010-10-10");
        sight.setSightingDate(date);

        sDao.addSighting(sight, testSuper);
        
        SightingInfo sifo = sDao.getSighting(sight.getSightingId());
        sDao.deleteSighting(sifo);
        
        assertTrue(sDao.getAllSightings().isEmpty());
    }


    public class SightingDaoImpl implements SightingDao {

        public List<SightingInfo> getAllSightings() {
            return null;
        }

        public List<Sighting> getLastTenSightings() {
            return null;
        }

        public List<SightingInfo> getSightingsWithSuperbeing(Superbeing superbeing) {
            return null;
        }

        public List<SightingInfo> getSightingsAtLocation(Location location) {
            return null;
        }

        public List<SightingInfo> getSightingsOnDate(String date) {
            return null;
        }

        public SightingInfo getSighting(int sightingId) {
            return null;
        }

        public Sighting addSighting(Sighting sighting, Superbeing superbeing) {
            return null;
        }

        public void editSighting(Sighting sighting, Superbeing superbeing) {
        }

        public void deleteSighting(SightingInfo sighting) {
        }

        public List<SightingInfo> getLast10Sightings() {
            return null;
        }

        public List<SightingInfo> sightingAndLocationForSuper(Superbeing superbeing) {
            return null;
        }
    }

}
