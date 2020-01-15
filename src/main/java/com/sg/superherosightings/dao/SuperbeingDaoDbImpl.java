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

    private static final String SQL_ADD_Superbeing = "insert into Superbeing (SuperbeingName, SuperbeingDescription, SuperbeingPower, HeroOrVillain) values (?,?,?,?)";
    private static final String SQL_GET_ALL_SuperbeingS = "select * from Superbeing";
    private static final String SQL_GET_Superbeing = "select * from Superbeing where SuperbeingId = ?";
    private static final String SQL_DELETE_Superbeing = "delete from Superbeing where SuperbeingId = ?";
    private static final String SQL_GET_SuperbeingS_AT_LOCATION = "select sb.SuperbeingId, sb.Superbeingname, sb.Superbeingdescription, sb.Superbeingpower, sb.heroorvillain "
            + "from Superbeing sb "
            + "inner join Superbeinglocation sl ON sb.SuperbeingId = sl.SuperbeingId "
            + "where sl.locationid = ?";
    private static final String SQL_EDIT_Superbeing = "update Superbeing set Superbeingname = ?, Superbeingdescription = ?, Superbeingpower = ?, heroorvillain = ? where Superbeingid = ?";
    private static final String SQL_SEARCH_Superbeing = "select * from Superbeing where SuperbeingName like ?";
    private static final String SQL_GET_MEMBERS = "select * from Superbeing s "
            + "inner join SuperbeingOrganization so ON s.SuperbeingId = so.SuperbeingId "
            + "where so.organizationId = ?";
    
    private JdbcTemplate jdbc;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Superbeing> getAllSuperbeings() {
        return jdbc.query(SQL_GET_ALL_SuperbeingS, new SuperbeingMapper());
    }

    @Override
    public List<Superbeing> getSuperbeingsAtLocation(Location location) {
        return jdbc.query(SQL_GET_SuperbeingS_AT_LOCATION, new SuperbeingMapper(), location.getLocationId());
    }

    @Override
    public Superbeing getSuperbeing(int superId) {
        return jdbc.queryForObject(SQL_GET_Superbeing, new SuperbeingMapper(), superId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superbeing addSuper(Superbeing Superbeing) {
        jdbc.update(SQL_ADD_Superbeing,
                Superbeing.getSuperbeingName(),
                Superbeing.getSuperbeingDescription(),
                Superbeing.getSuperbeingPower(),
                Superbeing.getHeroOrVillain());
        int SuperbeingId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        Superbeing.setSuperbeingId(SuperbeingId);
        return Superbeing;
    }

    @Override
    public void editSuper(Superbeing Superbeing) {
        jdbc.update(SQL_EDIT_Superbeing,
                Superbeing.getSuperbeingName(),
                Superbeing.getSuperbeingDescription(),
                Superbeing.getSuperbeingPower(),
                Superbeing.getHeroOrVillain(),
                Superbeing.getSuperbeingId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuper(Superbeing Superbeing) {
        jdbc.update(SQL_DELETE_Superbeing, Superbeing.getSuperbeingId());
    }

    @Override
    public List<Superbeing> searchSuperbeing(String superName) {
        return jdbc.query(SQL_SEARCH_Superbeing, new SuperbeingMapper(), "%"+superName+"%");
    }

    @Override
    public List<Superbeing> getMembers(Organization organization) {
        return jdbc.query(SQL_GET_MEMBERS, new SuperbeingMapper(), organization.getOrganizationId());
    }

    private static final class SuperbeingMapper implements RowMapper<Superbeing> {

        @Override
        public Superbeing mapRow(ResultSet rs, int i) throws SQLException {

            Superbeing Superbeing = new Superbeing();
            Superbeing.setSuperbeingId(rs.getInt("SuperbeingId"));
            Superbeing.setSuperbeingName(rs.getString("SuperbeingName"));
            Superbeing.setSuperbeingPower(rs.getString("SuperbeingPower"));
            Superbeing.setSuperbeingDescription(rs.getString("SuperbeingDescription"));
            Superbeing.setHeroOrVillain(rs.getString("HeroOrVillain"));
            return Superbeing;

        }

    }

}
