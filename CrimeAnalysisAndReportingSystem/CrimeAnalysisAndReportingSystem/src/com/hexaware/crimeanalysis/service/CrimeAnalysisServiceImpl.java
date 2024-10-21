package com.hexaware.crimeanalysis.service;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import com.hexaware.crimeanalysis.dao.CrimeAnalysisDaoImpl;
import com.hexaware.crimeanalysis.dao.ICrimeAnalysisDao;
import com.hexaware.crimeanalysis.entity.Case;
import com.hexaware.crimeanalysis.entity.Incident;
import com.hexaware.crimeanalysis.entity.Report;
import com.hexaware.crimeanalysis.myexceptions.IncidentNumberNotFoundException;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService { 
    private CrimeAnalysisDaoImpl dao;

    public CrimeAnalysisServiceImpl() {
        this.dao = new CrimeAnalysisDaoImpl();
    }

    @Override
    public boolean createIncident(Incident incident) {
        if (incident == null) {
            throw new IllegalArgumentException("Incident cannot be null.");
        }

        validateIncident(incident);
        return dao.createIncident(incident);
    }

    @Override
    public boolean updateIncidentStatus(String newStatus, int incidentId) throws IncidentNumberNotFoundException {
        validateStatus(newStatus);
        return dao.updateIncidentStatus(newStatus, incidentId);
    }

    @Override
    public Collection<Incident> getIncidentsInDateRange(String startDate, String endDate) {
        validateDate(startDate);
        validateDate(endDate);
        return dao.getIncidentsInDateRange(startDate, endDate);
    }

    @Override
    public Collection<Incident> searchIncidents(String criteria) {
        if (criteria == null || criteria.trim().isEmpty()) {
            throw new IllegalArgumentException("Search criteria cannot be null or empty.");
        }
        return dao.searchIncidents(criteria);
    }

    @Override
    public Report generateIncidentReport(Incident incident) {
        if (incident == null) {
            throw new IllegalArgumentException("Incident cannot be null.");
        }
        return dao.generateIncidentReport(incident);
    }

    @Override
    public Case createCase(String caseDescription, int incidentId) {
        if (caseDescription == null || caseDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Case description cannot be null or empty.");
        }
        return dao.createCase(caseDescription, incidentId);
    }

    @Override
    public Case getCaseDetails(int caseId) throws IncidentNumberNotFoundException {
        if (caseId <= 0) {
            throw new IllegalArgumentException("Invalid case ID.");
        }
        return dao.getCaseDetails(caseId);
    }

    @Override
    public boolean updateCaseDetails(Case caseObj) throws IncidentNumberNotFoundException {
        if (caseObj == null) {
            throw new IllegalArgumentException("Case object cannot be null.");
        }
        return dao.updateCaseDetails(caseObj);
    }

    @Override
    public List<Case> getAllCases() {
        return dao.getAllCases();
    }

    // Helper validation methods
    private void validateIncident(Incident incident) {
        if (incident.getIncidentType() == null || incident.getIncidentType().trim().isEmpty()) {
            throw new IllegalArgumentException("Incident type cannot be null or empty.");
        }
        if (incident.getIncidentDate() == null || !isValidDate(incident.getIncidentDate())) {
            throw new IllegalArgumentException("Invalid incident date format. Please use YYYY-MM-DD.");
        }
        if (incident.getLocation() == null || incident.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty.");
        }
        if (incident.getDescription() == null || incident.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
       
    }

    private boolean isValidDate(String date) {
        // Basic regex to check date format YYYY-MM-DD
        return Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date);
    }

    private void validateStatus(String status) {
        if (status == null || (!status.equalsIgnoreCase("Open") && !status.equalsIgnoreCase("Closed"))) {
            throw new IllegalArgumentException("Status must be either 'Open' or 'Closed'.");
        }
    }

    private void validateDate(String date) {
        if (date == null || !isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }
}
