/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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
public class OrganizationDaoDbImpl implements OrganizationDao {

    private JdbcTemplate jdbc;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }
    private static final String SQL_ADD_MEMBER = "Insert into superbeingorganization (SuperbeingId, OrganizationId) values (?,?)";
    private static final String SQL_SEARCH_ORGANIZATIONS = "select * from Organization where OrganizationName like ?";
    private static final String SQL_GET_ALL_ORGANIZATIONS = "select * from Organization";
    private static final String SQL_GET_ALL_ORGANIZATIONS_FOR_SUPER = "Select o.OrganizationId, o.OrganizationName, o.OrganizationDescription, o.OrganizationPhoneNumber, o.LocationId from Organization o "
            + "Inner join SuperbeingOrganization so on o.OrganizationId = so.OrganizationId "
            + "where so.SuperbeingId = ?";
    private static final String SQL_GET_ORGANIZATION = "select * from Organization where OrganizationId = ?";
    private static final String SQL_ADD_ORGANIZATION = "insert into Organization (OrganizationName, OrganizationDescription, OrganizationPhoneNumber, LocationId) values (?,?,?,?)";
    private static final String SQL_EDIT_ORGANIZATION = "update Organization set "
            + "OrganizationName = ?, OrganizationDescription = ?, OrganizationPhoneNumber = ?, LocationId = ? where OrganizationId = ?";
    private static final String SQL_DELETE_ORGANIZATION = "delete from Organization where OrganizationId = ?";
    
    @Override

    public List<Organization> getAllOrganizations() {
        return jdbc.query(SQL_GET_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public List<Organization> getOrganizationsForSuperbeing(Superbeing superbeing) {
        return jdbc.query(SQL_GET_ALL_ORGANIZATIONS_FOR_SUPER, new OrganizationMapper(), superbeing.getSuperbeingId());
    }

    @Override
    public Organization getOrganization(int organizationId) {
        return jdbc.queryForObject(SQL_GET_ORGANIZATION, new OrganizationMapper(), organizationId);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(Organization o) {
        jdbc.update(SQL_ADD_ORGANIZATION,
                o.getOrganizationName(),
                o.getOrganizationDescription(),
                o.getOrganizationPhoneNumber(),
                o.getLocationId());
        int organizationId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
                
        o.setOrganizationId(organizationId);
        
        return o;
        
    }

    @Override
    public void editOrganization(Organization o) {
        jdbc.update(SQL_EDIT_ORGANIZATION,
                o.getOrganizationName(),
                o.getOrganizationDescription(),
                o.getOrganizationPhoneNumber(),
                o.getLocationId(),
                o.getOrganizationId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(Organization organization) {
        jdbc.update(SQL_DELETE_ORGANIZATION, organization.getOrganizationId());
    }

    @Override
    public List<Organization> searchOrganizations(String organization) {
        return jdbc.query(SQL_SEARCH_ORGANIZATIONS, new OrganizationMapper(), "%"+organization+"%");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMember(Superbeing superbeing, Organization organization) {
        jdbc.update(SQL_ADD_MEMBER, superbeing.getSuperbeingId(), organization.getOrganizationId());
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("OrganizationId"));
            org.setOrganizationName(rs.getString("OrganizationName"));
            org.setOrganizationDescription(rs.getString("OrganizationDescription"));
            org.setOrganizationPhoneNumber(rs.getString("OrganizationPhoneNumber"));
            org.setLocationId(rs.getInt("LocationId"));
            return org;
        }

    }

}
