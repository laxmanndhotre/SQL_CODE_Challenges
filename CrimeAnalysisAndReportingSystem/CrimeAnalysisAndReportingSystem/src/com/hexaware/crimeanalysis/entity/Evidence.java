package com.hexaware.crimeanalysis.entity;

public class Evidence {
    private String evidenceID;
    private String description;
    private String locationFound;
    private String incidentID; 

    // Default Constructor
    public Evidence() {}

    // Parameterized Constructor
    public Evidence(String evidenceID, String description, String locationFound, String incidentID) {
        this.evidenceID = evidenceID;
        this.description = description;
        this.locationFound = locationFound;
        this.incidentID = incidentID;
    }

    // Getters and Setters
    public String getEvidenceID() {
        return evidenceID;
    }

    public void setEvidenceID(String evidenceID) {
        this.evidenceID = evidenceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }

    public String getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(String incidentID) {
        this.incidentID = incidentID;
    }
}
