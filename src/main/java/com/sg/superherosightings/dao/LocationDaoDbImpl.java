/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Superbeing;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Elnic
 */
public class LocationDaoDbImpl implements LocationDao {

    private JdbcTemplate jdbc;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    private static final String SQL_GET_ALL_LOCATIONS = "select * from Location";
    private static final String SQL_GET_LOCATIONS_FOR_SUPERBEING = "select * from Location l inner join SuperbeingLocation sl ON l.LocationId = sl.LocationId where sl.SuperbeingId = ?";
    private static final String SQL_GET_LOCATION_FOR_SIGHTING = "";
    private static final String SQL_GET_LOCATION = "select * from Location where LocationId = ?";
    private static final String SQL_ADD_LOCATION = "INSERT INTO Location (LocationName, LocationDescription, LocationAddress, "
            + "LocationStreetName, LocationCityName, LocationStateName, LocationZipCode, "
            + "Latitude, Longitude) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_EDIT_LOCATION = "update Location set "
            + "LocationName = ?, LocationDescription = ?, LocationAddress = ?, LocationStreetName = ?, "
            + "LocationCityName = ?, LocationStateName = ?, LocationZipCode = ?, Latitude = ?, Longitude = ? where LocationId = ?";
    private static final String SQL_DELETE_LOCATION = "delete from Location where LocationId = ?";
    private static final String SQL_SEARCH_LOCATIONS = "Select * from Location where LocationName like ?";

    @Override
    public List<Location> getAllLocations() {
        return jdbc.query(SQL_GET_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getLocationsForSuperbeing(Superbeing superbeing) {
        return jdbc.query(SQL_GET_LOCATIONS_FOR_SUPERBEING, new LocationMapper(), superbeing.getSuperbeingId());
    }

    @Override
    public Location getLocation(int locationId) {
        return jdbc.queryForObject(SQL_GET_LOCATION, new LocationMapper(), locationId);
    }

    @Override
    public Location addLocation(Location l) {
        jdbc.update(SQL_ADD_LOCATION, l.getLocationName(), l.getLocationDescription(), l.getLocationAddress(), l.getLocationStreetName(), l.getLocationCityName(),
                l.getLocationStateName(), l.getLocationZipCode(), l.getLatitude(), l.getLongitude());
        int locationId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        l.setLocationId(locationId);
        return l;
    }

    @Override
    public void editLocation(Location l) {
        jdbc.update(SQL_EDIT_LOCATION,
                l.getLocationName(),
                l.getLocationDescription(),
                l.getLocationAddress(),
                l.getLocationStreetName(),
                l.getLocationCityName(),
                l.getLocationStateName(),
                l.getLocationZipCode(),
                l.getLatitude(),
                l.getLongitude(),
                l.getLocationId());
    }

    @Override
    public void deleteLocation(Location l) {
        jdbc.update(SQL_DELETE_LOCATION, l.getLocationId());
    }

    @Override
    public List<Location> searchLocations(String location) throws EmptyResultDataAccessException {
        return jdbc.query(SQL_SEARCH_LOCATIONS, new LocationMapper(), "%"+location+"%");
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("LocationId"));
            location.setLocationName(rs.getString("LocationName"));
            location.setLocationDescription(rs.getString("LocationDescription"));
            location.setLocationAddress(rs.getString("LocationAddress"));
            location.setLocationStreetName(rs.getString("LocationStreetName"));
            location.setLocationCityName(rs.getString("LocationCityName"));
            location.setLocationStateName(rs.getString("LocationStateName"));
            location.setLocationZipCode(rs.getString("LocationZipCode"));
            location.setLatitude(rs.getString("Latitude"));
            location.setLongitude(rs.getString("Longitude"));
            return location;
        }

    }
}
