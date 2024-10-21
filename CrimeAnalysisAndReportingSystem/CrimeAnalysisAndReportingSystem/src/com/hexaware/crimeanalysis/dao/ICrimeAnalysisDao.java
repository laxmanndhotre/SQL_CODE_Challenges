package com.hexaware.crimeanalysis.dao;

import java.util.Collection;
import java.util.List;

import com.hexaware.crimeanalysis.entity.Case;
import com.hexaware.crimeanalysis.entity.Incident;
import com.hexaware.crimeanalysis.entity.Report;
import com.hexaware.crimeanalysis.myexceptions.IncidentNumberNotFoundException;

public interface ICrimeAnalysisDao {
    boolean createIncident(Incident incident);
    boolean updateIncidentStatus(String status, int incidentId) throws IncidentNumberNotFoundException;
    Collection<Incident> getIncidentsInDateRange(String startDate, String endDate);
    Collection<Incident> searchIncidents(String criteria);
    Report generateIncidentReport(Incident incident);
    Case createCase(String caseDescription, int incidentId); 
    Case getCaseDetails(int caseId) throws IncidentNumberNotFoundException;
    boolean updateCaseDetails(Case caseObj) throws IncidentNumberNotFoundException;
    List<Case> getAllCases();
	Incident getIncidentById(int incidentId);
    

}
