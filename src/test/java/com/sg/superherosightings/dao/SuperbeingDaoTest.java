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
public class SuperbeingDaoTest {

    private LocationDao lDao;
    private OrganizationDao oDao;
    private SuperbeingDao suDao;
    private SightingDao sDao;

    public SuperbeingDaoTest() {
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
     * Test of getAllSuperbeings method, of class SuperbeingDao.
     */
    @Test
    public void testGetAllSuperbeings() {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);
        
        assertEquals(1, suDao.getAllSuperbeings().size());
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

        suDao.addSuper(testSuper);
        
        assertEquals(1, suDao.getAllSuperbeings().size());
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

        suDao.addSuper(testSuper);
        
        testSuper.setSuperbeingName("Bubs");
        suDao.editSuper(testSuper);
        
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

        suDao.addSuper(testSuper);
        
        suDao.deleteSuper(testSuper);
        
        assertTrue(suDao.getAllSuperbeings().isEmpty());
    }

    /**
     * Test of searchSuperbeing method, of class SuperbeingDao.
     */
    @Test
    public void testSearchSuperbeing() {
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");

        suDao.addSuper(testSuper);
        List<Superbeing> supList = suDao.searchSuperbeing("Bug");
        assertEquals(1, supList.size());
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
        lDao.addLocation(loc);
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oDao.addOrganization(org);
        
        Superbeing testSuper = new Superbeing();
        testSuper.setSuperbeingName("Bugsy");
        testSuper.setSuperbeingDescription("Exterminator");
        testSuper.setSuperbeingPower("Pesticides");
        testSuper.setHeroOrVillain("Hero");
        suDao.addSuper(testSuper);
        
        oDao.addMember(testSuper, org);
        
        List<Superbeing> supList = suDao.getMembers(org);
        
        assertEquals(1, supList.size());
        
    }

    public class SuperbeingDaoImpl implements SuperbeingDao {

        public List<Superbeing> getAllSuperbeings() {
            return null;
        }

        public List<Superbeing> getSuperbeingsAtLocation(Location location) {
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

        public void deleteSuper(Superbeing superbeing) {
        }

        public List<Superbeing> searchSuperbeing(String superName) {
            return null;
        }

        public List<Superbeing> getMembers(Organization organization) {
            return null;
        }
    }

}
