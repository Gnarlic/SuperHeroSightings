/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Elnic
 */
public class SightingInfo {
    
    private int sightingId;
    private int superbeingId;
    private int locationId;
    private String locationName;
    private Date sightingDate;
    private String superbeingName;
    private String superbeingPower;
    private String heroOrVillain;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public int getSuperbeingId() {
        return superbeingId;
    }

    public void setSuperbeingId(int superbeingId) {
        this.superbeingId = superbeingId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Date getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(Date sightingDate) {
        this.sightingDate = sightingDate;
    }

    public String getSuperbeingName() {
        return superbeingName;
    }

    public void setSuperbeingName(String superbeingName) {
        this.superbeingName = superbeingName;
    }

    public String getSuperbeingPower() {
        return superbeingPower;
    }

    public void setSuperbeingPower(String superbeingPower) {
        this.superbeingPower = superbeingPower;
    }

    public String getHeroOrVillain() {
        return heroOrVillain;
    }

    public void setHeroOrVillain(String heroOrVillain) {
        this.heroOrVillain = heroOrVillain;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.sightingId;
        hash = 17 * hash + this.superbeingId;
        hash = 17 * hash + this.locationId;
        hash = 17 * hash + Objects.hashCode(this.locationName);
        hash = 17 * hash + Objects.hashCode(this.sightingDate);
        hash = 17 * hash + Objects.hashCode(this.superbeingName);
        hash = 17 * hash + Objects.hashCode(this.superbeingPower);
        hash = 17 * hash + Objects.hashCode(this.heroOrVillain);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SightingInfo other = (SightingInfo) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (this.superbeingId != other.superbeingId) {
            return false;
        }
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.superbeingName, other.superbeingName)) {
            return false;
        }
        if (!Objects.equals(this.superbeingPower, other.superbeingPower)) {
            return false;
        }
        if (!Objects.equals(this.heroOrVillain, other.heroOrVillain)) {
            return false;
        }
        if (!Objects.equals(this.sightingDate, other.sightingDate)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
