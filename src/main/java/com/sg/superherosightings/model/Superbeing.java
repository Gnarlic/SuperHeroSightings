/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Elnic
 */
public class Superbeing {
    
    private int superbeingId;
    @NotNull
    private String superbeingName;
    private String superbeingDescription;
    @NotNull
    private String superbeingPower;
    @NotNull
    private String heroOrVillain;

    public int getSuperbeingId() {
        return superbeingId;
    }

    public void setSuperbeingId(int superbeingId) {
        this.superbeingId = superbeingId;
    }

    public String getSuperbeingName() {
        return superbeingName;
    }

    public void setSuperbeingName(String superbeingName) {
        this.superbeingName = superbeingName;
    }

    public String getSuperbeingDescription() {
        return superbeingDescription;
    }

    public void setSuperbeingDescription(String superbeingDescription) {
        this.superbeingDescription = superbeingDescription;
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
        int hash = 5;
        hash = 43 * hash + this.superbeingId;
        hash = 43 * hash + Objects.hashCode(this.superbeingName);
        hash = 43 * hash + Objects.hashCode(this.superbeingDescription);
        hash = 43 * hash + Objects.hashCode(this.superbeingPower);
        hash = 43 * hash + Objects.hashCode(this.heroOrVillain);
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
        final Superbeing other = (Superbeing) obj;
        if (this.superbeingId != other.superbeingId) {
            return false;
        }
        if (!Objects.equals(this.superbeingName, other.superbeingName)) {
            return false;
        }
        if (!Objects.equals(this.superbeingDescription, other.superbeingDescription)) {
            return false;
        }
        if (!Objects.equals(this.superbeingPower, other.superbeingPower)) {
            return false;
        }
        if (!Objects.equals(this.heroOrVillain, other.heroOrVillain)) {
            return false;
        }
        return true;
    }


    
}
