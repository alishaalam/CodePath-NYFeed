package com.happytimes.alisha.nyfeed.model;

import org.parceler.Parcel;

/**
 * Created by alishaalam on 7/28/16.
 */
@Parcel
public class Person {

    public String organization;
    public String role;
    public String firstname;
    public int rank;
    public String lastname;

    public Person() {
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
