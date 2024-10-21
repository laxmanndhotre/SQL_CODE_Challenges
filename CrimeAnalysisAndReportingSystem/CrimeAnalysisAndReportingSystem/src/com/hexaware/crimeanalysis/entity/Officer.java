package com.hexaware.crimeanalysis.entity;

public class Officer {
    private String officerID;
    private String firstName;
    private String lastName;
    private String badgeNumber;
    private String rank;
    private String contactInformation;
    private String agencyID; 

    // Default Constructor
    public Officer() {}

    // Parameterized Constructor
    public Officer(String officerID, String firstName, String lastName, String badgeNumber, String rank, String contactInformation, String agencyID) {
        this.officerID = officerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.contactInformation = contactInformation;
        this.agencyID = agencyID;
    }

    // Getters and Setters
    public String getOfficerID() {
        return officerID;
    }

    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }
}
