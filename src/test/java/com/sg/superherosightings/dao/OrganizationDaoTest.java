/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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

/**
 *
 * @author Elnic
 */
public class OrganizationDaoTest {

    private LocationDao lDao;
    private OrganizationDao oDao;
    private SuperbeingDao suDao;
    private SightingDao sDao;
    
    public OrganizationDaoTest() {
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
     * Test of getAllOrganizations method, of class OrganizationDao.
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
        lDao.addLocation(loc);
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oDao.addOrganization(org);
        List<Organization> org2 = oDao.getAllOrganizations();
        assertEquals(1, org2.size());
    }

    /**
     * Test of getOrganizationsForSuperbeing method, of class OrganizationDao.
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
        
        List<Organization> orgList = oDao.getOrganizationsForSuperbeing(testSuper);
        
        assertEquals(1, orgList.size());
    }

    /**
     * Test of getOrganization method, of class OrganizationDao.
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
        lDao.addLocation(loc);
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oDao.addOrganization(org);
        
        assertEquals(1, oDao.getAllOrganizations().size());
    }

    /**
     * Test of editOrganization method, of class OrganizationDao.
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
        lDao.addLocation(loc);
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oDao.addOrganization(org);
        
        org.setOrganizationName("Avant gardeners");
        oDao.editOrganization(org);
        Organization org2 = oDao.getOrganization(org.getOrganizationId());
        assertEquals(org2.getOrganizationName(), "Avant gardeners");
    }

    /**
     * Test of deleteOrganization method, of class OrganizationDao.
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
        lDao.addLocation(loc);
        
        
        Organization org = new Organization();
        org.setOrganizationName("Avengers");
        org.setLocationId(loc.getLocationId());
        org.setOrganizationDescription("Mightiest Heros");
        org.setOrganizationPhoneNumber("1234567897");
        oDao.addOrganization(org);
        
        oDao.deleteOrganization(org);
        
        assertTrue(oDao.getAllOrganizations().isEmpty());
    }

    /**
     * Test of searchOrganizations method, of class OrganizationDao.
     */
    @Test
    public void testSearchOrganizations() {
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
        
        List<Organization> orgList = oDao.searchOrganizations("ave");
        assertEquals(1, orgList.size());
    }

    /**
     * Test of addMember method, of class OrganizationDao.
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
        
        assertEquals(oDao.getOrganizationsForSuperbeing(testSuper).size(), 1);
    }

    public class OrganizationDaoImpl implements OrganizationDao {

        public List<Organization> getAllOrganizations() {
            return null;
        }

        public List<Organization> getOrganizationsForSuperbeing(Superbeing superbeing) {
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

        public void deleteOrganization(Organization organization) {
        }

        public List<Organization> searchOrganizations(String organization) {
            return null;
        }

        public void addMember(Superbeing superbeing, Organization organization) {
        }
    }
    
}
