/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Elnic
 */
public class Location {
    
    private int locationId;
    @NotNull
    @NotEmpty(message="Location Name Required")
    private String locationName;
    
    private String locationDescription;
    @NotEmpty(message="Address Required")
    private String locationAddress;
    @Length(min=1)
    private String locationStreetName;
    private String locationCityName;
    private String locationStateName;
    private String locationZipCode;
    private String latitude;
    private String longitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationStreetName() {
        return locationStreetName;
    }

    public void setLocationStreetName(String locationStreetName) {
        this.locationStreetName = locationStreetName;
    }

    public String getLocationCityName() {
        return locationCityName;
    }

    public void setLocationCityName(String locationCityName) {
        this.locationCityName = locationCityName;
    }

    public String getLocationStateName() {
        return locationStateName;
    }

    public void setLocationStateName(String locationStateName) {
        this.locationStateName = locationStateName;
    }

    public String getLocationZipCode() {
        return locationZipCode;
    }

    public void setLocationZipCode(String locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.locationId;
        hash = 89 * hash + Objects.hashCode(this.locationName);
        hash = 89 * hash + Objects.hashCode(this.locationDescription);
        hash = 89 * hash + Objects.hashCode(this.locationAddress);
        hash = 89 * hash + Objects.hashCode(this.locationStreetName);
        hash = 89 * hash + Objects.hashCode(this.locationCityName);
        hash = 89 * hash + Objects.hashCode(this.locationStateName);
        hash = 89 * hash + Objects.hashCode(this.locationZipCode);
        hash = 89 * hash + Objects.hashCode(this.latitude);
        hash = 89 * hash + Objects.hashCode(this.longitude);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        if (!Objects.equals(this.locationStreetName, other.locationStreetName)) {
            return false;
        }
        if (!Objects.equals(this.locationCityName, other.locationCityName)) {
            return false;
        }
        if (!Objects.equals(this.locationStateName, other.locationStateName)) {
            return false;
        }
        if (!Objects.equals(this.locationZipCode, other.locationZipCode)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }
    
    
    
}
