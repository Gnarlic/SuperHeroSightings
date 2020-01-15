/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SightingInfo;
import com.sg.superherosightings.model.Superbeing;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 *
 * @author Elnic
 */
public class SightingDaoDbImpl implements SightingDao {

    private JdbcTemplate jdbc;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    private static final String SQL_GET_LAST_TEN_SIGHTINGS = "select sb.SuperbeingName, sb.SuperbeingId, sb.HeroOrVillain, l.LocationId, l.LocationName, s.SightingDate, s.SightingId from Superbeing sb "
            + "inner join SuperbeingSighting ss ON sb.SuperbeingId = ss.SuperbeingId "
            + "inner join Sighting s ON ss.SightingId = s.SightingId "
            + "inner join Location l ON s.LocationId = l.LocationId ORDER BY s.SightingDate DESC LIMIT 10";
    private static final String SQL_GET_SIGHTINGS_FOR_LOCATION = "select sb.SuperbeingName, sb.SuperbeingId, sb.HeroOrVillain, l.LocationId, l.LocationName, s.SightingDate, s.SightingId from Superbeing sb "
            + "inner join SuperbeingSighting ss ON sb.SuperbeingId = ss.SuperbeingId "
            + "inner join Sighting s ON ss.SightingId = s.SightingId "
            + "inner join Location l ON s.LocationId = l.LocationId where locationId = ? ORDER BY s.SightingDate DESC";
    private static final String SQL_GET_SIGHTINGS_WITH_SUPER = "select sb.SuperbeingName, sb.SuperbeingId, sb.HeroOrVillain, l.LocationId, l.LocationName, s.SightingDate, s.SightingId from Superbeing sb "
            + "inner join SuperbeingSighting ss ON sb.SuperbeingId = ss.SuperbeingId "
            + "inner join Sighting s ON ss.SightingId = s.SightingId "
            + "inner join Location l ON s.LocationId = l.LocationId where sb.superbeingId = ? ORDER BY s.SightingDate DESC";
    private static final String SQL_GET_ALL_SIGHTINGS = "select sb.SuperbeingName, sb.SuperbeingId, sb.HeroOrVillain, l.LocationId, l.LocationName, s.SightingDate, s.SightingId from Superbeing sb "
            + "inner join SuperbeingSighting ss ON sb.SuperbeingId = ss.SuperbeingId "
            + "inner join Sighting s ON ss.SightingId = s.SightingId "
            + "inner join Location l ON s.LocationId = l.LocationId ORDER BY s.SightingDate DESC";
    private static final String SQL_GET_SIGHTINGS_FOR_DATE = "select sb.SuperbeingName, sb.SuperbeingId, sb.HeroOrVillain, l.LocationId, l.LocationName, s.SightingDate, s.SightingId from Superbeing sb "
            + "inner join SuperbeingSighting ss ON sb.SuperbeingId = ss.SuperbeingId "
            + "inner join Sighting s ON ss.SightingId = s.SightingId "
            + "inner join Location l ON s.LocationId = l.LocationId where s.SightingDate = ?";
    private static final String SQL_ADD_SIGHTING = "insert into Sighting (LocationId, SightingDate) values (?,?) ";
    private static final String SQL_ADD_SIGHTING_SUPERBEING = "insert into SuperbeingSighting (sightingId, superbeingId) values (?,?) ";
    private static final String SQL_GET_SIGHTING = "select sb.SuperbeingName, sb.SuperbeingId, sb.HeroOrVillain, l.LocationId, l.LocationName, s.SightingDate, s.SightingId from Superbeing sb "
            + "inner join SuperbeingSighting ss ON sb.SuperbeingId = ss.SuperbeingId "
            + "inner join Sighting s ON ss.SightingId = s.SightingId "
            + "inner join Location l ON s.LocationId = l.LocationId where s.sightingId = ?";
    private static final String SQL_EDIT_SIGHTING = "update Sighting set LocationId = ?, SightingDate = ? where SightingId = ?";
    private static final String SQL_DELETE_SIGHTING = "delete from sighting where SightingId = ?";
    private static final String SQL_GET_SIGHTINGS_AND_LOCATIONS_FOR_SUPER = "select l.locationId, l.locationName, l.locationDescription , s.sightingdate, sb.superbeingName from location l "
            + "inner join sightinglocation sl ON l.locationId = sl.locationId "
            + "inner join SuperbeingSighting ss ON sl.sightingId = ss.sightingId "
            + "inner join sighting s ON ss.sightingid = s.sightingid "
            + "inner join superbeing sb ON ss.superbeingId = sb.superbeingId  "
            + "where ss.superbeingid = ?";
    private static final String SQL_EDIT_SUPERBEING_SIGHTING = "update SuperbeingSighting set superbeingId = ? where sightingId = ?";

    @Override
    public List<SightingInfo> getLast10Sightings() {
        return jdbc.query(SQL_GET_LAST_TEN_SIGHTINGS, new SightingInfoMapper());
    }

    @Override
    public List<SightingInfo> getAllSightings() {
        return jdbc.query(SQL_GET_ALL_SIGHTINGS, new SightingInfoMapper());
    }

    @Override
    public List<Sighting> getLastTenSightings() {
        return jdbc.query(SQL_GET_LAST_TEN_SIGHTINGS, new SightingMapper());
    }

    @Override
    public List<SightingInfo> getSightingsWithSuperbeing(Superbeing superbeing) {
        return jdbc.query(SQL_GET_SIGHTINGS_WITH_SUPER, new SightingInfoMapper(), superbeing.getSuperbeingId());
    }

    @Override
    public List<SightingInfo> getSightingsAtLocation(Location location) {
        return jdbc.query(SQL_GET_SIGHTINGS_FOR_LOCATION, new SightingInfoMapper(), location.getLocationId());
    }

    @Override
    public List<SightingInfo> getSightingsOnDate(String date) {
        return jdbc.query(SQL_GET_SIGHTINGS_FOR_DATE, new SightingInfoMapper(), date);
    }

    @Override
    public SightingInfo getSighting(int sightingId) {
        return jdbc.queryForObject(SQL_GET_SIGHTING, new SightingInfoMapper(), sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting, Superbeing superbeing) {
        jdbc.update(SQL_ADD_SIGHTING,
                sighting.getLocationId(),
                sighting.getSightingDate());
        int sightingId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(sightingId);
        jdbc.update(SQL_ADD_SIGHTING_SUPERBEING,
                sightingId,
                superbeing.getSuperbeingId());
        return sighting;
    }

    @Override
    public void editSighting(Sighting sighting, Superbeing superbeing) {

        jdbc.update(SQL_EDIT_SIGHTING,
                sighting.getLocationId(),
                sighting.getSightingDate(),
                sighting.getSightingId());
        jdbc.update(SQL_EDIT_SUPERBEING_SIGHTING,
                superbeing.getSuperbeingId(),
                sighting.getSightingId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(SightingInfo sighting) {
        jdbc.update(SQL_DELETE_SIGHTING, sighting.getSightingId());
    }

    @Override
    public List<SightingInfo> sightingAndLocationForSuper(Superbeing superbeing) {
        return jdbc.query(SQL_GET_SIGHTINGS_AND_LOCATIONS_FOR_SUPER, new SightingInfoMapper(), superbeing.getSuperbeingId());
    }

    private static final class SightingInfoMapper implements RowMapper<SightingInfo> {

        @Override
        public SightingInfo mapRow(ResultSet rs, int i) throws SQLException {
            SightingInfo sifo = new SightingInfo();
            sifo.setSuperbeingName(rs.getString("SuperbeingName"));
            sifo.setLocationName(rs.getString("LocationName"));
            sifo.setSightingDate(rs.getTimestamp("SightingDate"));
            sifo.setSightingId(rs.getInt("SightingId"));
            sifo.setLocationId(rs.getInt("LocationId"));
            sifo.setSuperbeingId(rs.getInt("SuperbeingId"));
            return sifo;
        }

    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {

            Sighting sight = new Sighting();
            sight.setSightingId(rs.getInt("SightingId"));
            sight.setLocationId(rs.getInt("LocationId"));
            sight.setSightingDate(rs.getTimestamp("SightingDate"));
            return sight;

        }

    }

}
