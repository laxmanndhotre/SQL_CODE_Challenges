package com.hexaware.crimeanalysis.main;



import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.hexaware.crimeanalysis.dao.CrimeAnalysisDaoImpl;
import com.hexaware.crimeanalysis.entity.Case;
import com.hexaware.crimeanalysis.entity.Incident;
import com.hexaware.crimeanalysis.entity.Officer;
import com.hexaware.crimeanalysis.entity.Report;
import com.hexaware.crimeanalysis.myexceptions.IncidentNumberNotFoundException;
import com.hexaware.crimeanalysis.service.CrimeAnalysisServiceImpl;

public class MainModule {
	 public static void main(String[] args) {
	        CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\nCrime Analysis System");
	            System.out.println("1. Create an Incident");
	            System.out.println("2. Update Incident Status");
	            System.out.println("3. Search Incidents by Criteria");
	            System.out.println("4. Get Incidents in Date Range");
	            System.out.println("5. Generate Incident Report");
	            System.out.println("6. Create a Case");
	            System.out.println("7. Get Case Details");
	            System.out.println("8. Update Case Details");
	            System.out.println("9. Get All Cases");
	            System.out.println("10. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 1:
	                    // Create an Incident
	                    Incident incident = new Incident();
	                    System.out.print("Enter Incident ID: ");
	                    incident.setIncidentID(scanner.nextInt());
	                    scanner.nextLine(); 

	                    System.out.print("Enter Incident Type: ");
	                    incident.setIncidentType(scanner.nextLine());

	                    System.out.print("Enter Incident Date (YYYY-MM-DD): ");
	                    incident.setIncidentDate(scanner.nextLine());

	                    System.out.print("Enter Location: ");
	                    incident.setLocation(scanner.nextLine());

	                    System.out.print("Enter Description: ");
	                    incident.setDescription(scanner.nextLine());

	                    System.out.print("Enter Status (e.g., Open, Closed): ");
	                    incident.setStatus(scanner.nextLine());
	                    System.out.print("Enter Victim ID: ");
	                    incident.setVictimID(scanner.nextInt());
	                    System.out.print("Enter Suspect ID: ");
	                    incident.setSuspectID(scanner.nextInt());

	                    if (service.createIncident(incident)) {
	                        System.out.println("Incident created successfully.");
	                    } else {
	                        System.out.println("Error creating incident.");
	                    }
	                    break;

	                case 2:
	                    // Update Incident Status
	                    System.out.print("Enter Incident ID to update: ");
	                    int incidentId = scanner.nextInt();
	                    scanner.nextLine();  

	                    System.out.print("Enter new Status: ");
	                    String newStatus = scanner.nextLine();

					try {
						if (service.updateIncidentStatus(newStatus, incidentId)) {
	                        System.out.println("Incident status updated successfully.");
	                    } else {
	                        System.out.println("Error updating incident status.");
	                    }
					} catch (IncidentNumberNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                    break;

	                case 3:
	                    // Search Incidents by Criteria
	                    System.out.print("Enter search criteria (e.g., Robbery): ");
	                    String criteria = scanner.nextLine();
	                    Collection<Incident> incidents = service.searchIncidents(criteria);
	                    System.out.println("Search Results:");
	                    for (Incident inc : incidents) {
	                        System.out.println(inc.toString());
	                    }
	                    break;

	                case 4:
	                    // Get Incidents in Date Range
	                    System.out.print("Enter start date (YYYY-MM-DD): ");
	                    String startDate = scanner.nextLine();

	                    System.out.print("Enter end date (YYYY-MM-DD): ");
	                    String endDate = scanner.nextLine();

	                    Collection<Incident> dateRangeIncidents = service.getIncidentsInDateRange(startDate, endDate);
	                    System.out.println("Incidents in Date Range:");
	                    for (Incident inc : dateRangeIncidents) {
	                        System.out.println(inc.toString());
	                    }
	                    break;

	                case 5:
	                    // Generate Incident Report
	                    System.out.print("Enter Incident ID to generate report: ");
	                    int reportIncidentID = scanner.nextInt();
	                    scanner.nextLine(); 

	                    Incident fetchedIncident = null;
	                    for (Incident inc : service.searchIncidents("")) {
	                        if (inc.getIncidentID() == reportIncidentID) {
	                            fetchedIncident = inc;
	                            break;
	                        }
	                    }

	                    if (fetchedIncident != null) {
	                        Report report = service.generateIncidentReport(fetchedIncident);
	                        System.out.println("Report generated successfully.");
	                        System.out.println("Incident ID: " + report.getIncidentID());
	                        System.out.println("Report Date: " + report.getReportDate());
	                        System.out.println("Report Details: " + report.getReportDetails());
	                        System.out.println("Status: " + report.getStatus());
	                    } else {
	                        System.out.println("Incident not found.");
	                    }
	                    break;

	                case 6:
	                    // Create a Case
	                    System.out.print("Enter case description: ");
	                    String caseDescription = scanner.nextLine();

	                    System.out.print("Enter incident ID associated with the case: ");
	                    int associatedIncidentId = scanner.nextInt();
	                    scanner.nextLine(); 

	                    Case createdCase = service.createCase(caseDescription, associatedIncidentId);
	                    if (createdCase != null) {
	                        System.out.println("Case created successfully with ID: " + createdCase.getCaseId());
	                    } else {
	                        System.out.println("Error creating case.");
	                    }
	                    break;

	                case 7:
	                    // Get Case Details
	                    System.out.print("Enter case ID to retrieve details: ");
	                    int caseId = scanner.nextInt();
	                    scanner.nextLine(); 

	                    try {
	                        
	                        Case caseDetails = service.getCaseDetails(caseId);
	                        if (caseDetails != null) {
	                            System.out.println("Case ID: " + caseDetails.getCaseId());
	                            System.out.println("Case Description: " + caseDetails.getCaseDescription());
	                            System.out.println("Incident ID: " + caseDetails.getIncidentId());
	                        } else {
	                            System.out.println("Case not found.");
	                        }
	                    } catch (IncidentNumberNotFoundException e) {
	                        // Handle the custom exception if the incident number is not found
	                        System.out.println("Error: Case with the provided ID does not exist. " + e.getMessage());
	                    }
	                    break;
	                case 8:
	                    // Update Case Details
	                    System.out.print("Enter Case ID to update: ");
	                    int caseIdToUpdate = scanner.nextInt();
	                    scanner.nextLine();  

	                    try {
	                        
	                        Case caseToUpdate = service.getCaseDetails(caseIdToUpdate);
	                        if (caseToUpdate != null) {
	                            System.out.print("Enter new case description: ");
	                            caseToUpdate.setCaseDescription(scanner.nextLine());
	                            
	                            // Try to update the case details
	                            if (service.updateCaseDetails(caseToUpdate)) {
	                                System.out.println("Case updated successfully.");
	                            } else {
	                                System.out.println("Error updating case.");
	                            }
	                        } else {
	                            System.out.println("Case not found.");
	                        }
	                    } catch (IncidentNumberNotFoundException e) {
	                        // Handle the custom exception if the case ID is not found
	                        System.out.println("Error: Case with the provided ID does not exist. " + e.getMessage());
	                    }
	                    break;

	                case 9:
	                    // Get All Cases
	                    List<Case> allCases = service.getAllCases();
	                    System.out.println("All Cases:");
	                    for (Case c : allCases) {
	                        System.out.println("Case ID: " + c.getCaseId() + ", Description: " + c.getCaseDescription());
	                    }
	                    break;

	                case 10:
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    System.exit(0);
	                    break;

	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }
	}