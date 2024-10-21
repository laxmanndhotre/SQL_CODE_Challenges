package com.hexaware.crimeanalysis.entity;

public class Victim {
    private String victimID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;  
    private String gender;
    private String contactInformation;

    
    public Victim() {}

    
    public Victim(String victimID, String firstName, String lastName, String dateOfBirth, String gender, String contactInformation) {
        this.victimID = victimID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInformation = contactInformation;
    }

    
    public String getVictimID() {
        return victimID;
    }

    public void setVictimID(String victimID) {
        this.victimID = victimID;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
}