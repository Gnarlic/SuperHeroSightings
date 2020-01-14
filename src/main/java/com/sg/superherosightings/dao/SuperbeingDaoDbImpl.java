/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
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
public class SuperbeingDaoDbImpl implements SuperbeingDao {

    private static final String SQL_ADD_SUPERBEING = "insert into superbeing (SuperbeingName, SuperbeingDescription, SuperbeingPower, HeroOrVillain) values (?,?,?,?)";
    private static final String SQL_GET_ALL_SUPERBEINGS = "select * from superbeing";
    private static final String SQL_GET_SUPERBEING = "select * from superbeing where SuperbeingId = ?";
    private static final String SQL_DELETE_SUPERBEING = "delete from superbeing where SuperbeingId = ?";
    private static final String SQL_GET_SUPERBEINGS_AT_LOCATION = "select sb.superbeingId, sb.superbeingname, sb.superbeingdescription, sb.superbeingpower, sb.heroorvillain "
            + "from superbeing sb "
            + "inner join superbeinglocation sl ON sb.superbeingId = sl.superbeingId "
            + "where sl.locationid = ?";
    private static final String SQL_EDIT_SUPERBEING = "update superbeing set superbeingname = ?, superbeingdescription = ?, superbeingpower = ?, heroorvillain = ? where superbeingid = ?";
    private static final String SQL_SEARCH_SUPERBEING = "select * from superbeing where superbeingName like ?";
    private static final String SQL_GET_MEMBERS = "select * from superbeing s "
            + "inner join SuperbeingOrganization so ON s.superbeingId = so.superbeingId "
            + "where so.organizationId = ?";
    
    private JdbcTemplate jdbc;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Superbeing> getAllSuperbeings() {
        return jdbc.query(SQL_GET_ALL_SUPERBEINGS, new SuperbeingMapper());
    }

    @Override
    public List<Superbeing> getSuperbeingsAtLocation(Location location) {
        return jdbc.query(SQL_GET_SUPERBEINGS_AT_LOCATION, new SuperbeingMapper(), location.getLocationId());
    }

    @Override
    public Superbeing getSuperbeing(int superId) {
        return jdbc.queryForObject(SQL_GET_SUPERBEING, new SuperbeingMapper(), superId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superbeing addSuper(Superbeing superbeing) {
        jdbc.update(SQL_ADD_SUPERBEING,
                superbeing.getSuperbeingName(),
                superbeing.getSuperbeingDescription(),
                superbeing.getSuperbeingPower(),
                superbeing.getHeroOrVillain());
        int superbeingId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superbeing.setSuperbeingId(superbeingId);
        return superbeing;
    }

    @Override
    public void editSuper(Superbeing superbeing) {
        jdbc.update(SQL_EDIT_SUPERBEING,
                superbeing.getSuperbeingName(),
                superbeing.getSuperbeingDescription(),
                superbeing.getSuperbeingPower(),
                superbeing.getHeroOrVillain(),
                superbeing.getSuperbeingId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuper(Superbeing superbeing) {
        jdbc.update(SQL_DELETE_SUPERBEING, superbeing.getSuperbeingId());
    }

    @Override
    public List<Superbeing> searchSuperbeing(String superName) {
        return jdbc.query(SQL_SEARCH_SUPERBEING, new SuperbeingMapper(), "%"+superName+"%");
    }

    @Override
    public List<Superbeing> getMembers(Organization organization) {
        return jdbc.query(SQL_GET_MEMBERS, new SuperbeingMapper(), organization.getOrganizationId());
    }

    private static final class SuperbeingMapper implements RowMapper<Superbeing> {

        @Override
        public Superbeing mapRow(ResultSet rs, int i) throws SQLException {

            Superbeing superbeing = new Superbeing();
            superbeing.setSuperbeingId(rs.getInt("SuperbeingId"));
            superbeing.setSuperbeingName(rs.getString("SuperbeingName"));
            superbeing.setSuperbeingPower(rs.getString("SuperbeingPower"));
            superbeing.setSuperbeingDescription(rs.getString("SuperbeingDescription"));
            superbeing.setHeroOrVillain(rs.getString("HeroOrVillain"));
            return superbeing;

        }

    }

}
