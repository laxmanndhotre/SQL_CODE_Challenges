package com.hexaware.crimeanalysis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hexaware.crimeanalysis.entity.Case;
import com.hexaware.crimeanalysis.entity.Incident;
import com.hexaware.crimeanalysis.entity.Report;
import com.hexaware.crimeanalysis.myexceptions.IncidentNumberNotFoundException;
import com.hexaware.crimeanalysis.util.DBConnection;

public class CrimeAnalysisDaoImpl implements ICrimeAnalysisDao {
	private Connection connection;

	public CrimeAnalysisDaoImpl() {
		connection = DBConnection.getConnection();
	}

	@Override
	public boolean createIncident(Incident incident) {
		
		String query = "INSERT INTO incidents (IncidentID, IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, incident.getIncidentID());
			stmt.setString(2, incident.getIncidentType());
			stmt.setString(3, incident.getIncidentDate());
			stmt.setString(4, incident.getLocation());
			stmt.setString(5, incident.getDescription());
			stmt.setString(6, incident.getStatus());
			stmt.setInt(7, incident.getVictimID());
			stmt.setInt(8, incident.getSuspectID());

			return stmt.executeUpdate() > 0; // Return true if at least one row was inserted
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Return false on failure
		}
	}

	@Override
	public boolean updateIncidentStatus(String status, int incidentId) throws IncidentNumberNotFoundException {
		String query = "UPDATE incidents SET Status = ? WHERE IncidentID = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, status);
			stmt.setInt(2, incidentId);
			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				throw new IncidentNumberNotFoundException("Incident ID " + incidentId + " not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Collection<Incident> getIncidentsInDateRange(String startDate, String endDate) {
		Collection<Incident> incidents = new ArrayList<>();
		String query = "SELECT * FROM incidents WHERE IncidentDate BETWEEN ? AND ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, startDate);
			stmt.setString(2, endDate);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Incident incident = new Incident();
				incident.setIncidentID(rs.getInt("IncidentID"));
				incident.setIncidentType(rs.getString("IncidentType"));
				incident.setIncidentDate(rs.getString("IncidentDate"));
				incident.setLocation(rs.getString("Location"));
				incident.setDescription(rs.getString("Description"));
				incident.setStatus(rs.getString("Status"));
				incidents.add(incident);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return incidents;
	}

	@Override
	public Collection<Incident> searchIncidents(String criteria) {
		Collection<Incident> incidents = new ArrayList<>();
		String query = "SELECT * FROM incidents WHERE IncidentType LIKE ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, "%" + criteria + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Incident incident = new Incident();
				incident.setIncidentID(rs.getInt("IncidentID"));
				incident.setIncidentType(rs.getString("IncidentType"));
				incident.setIncidentDate(rs.getString("IncidentDate"));
				incident.setLocation(rs.getString("Location"));
				incident.setDescription(rs.getString("Description"));
				incident.setStatus(rs.getString("Status"));
				incidents.add(incident);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return incidents;
	}

	@Override
	public Report generateIncidentReport(Incident incident) {
		// Create a new Report object
		Report report = new Report();

		report.setIncidentID(incident.getIncidentID());
		report.setReportDetails(incident.getDescription());
		report.setReportDate(incident.getIncidentDate());
		report.setStatus(incident.getStatus());

		return report;
	}

	@Override
	public Case createCase(String caseDescription, int incidentId) {
		String query = "INSERT INTO cases (CaseDescription, IncidentId) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, caseDescription);
			stmt.setInt(2, incidentId);

			int affectedRows = stmt.executeUpdate();

			// Get the generated CaseId
			if (affectedRows > 0) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					int caseId = generatedKeys.getInt(1); // Retrieve the generated case ID
					return new Case(caseId, caseDescription, incidentId); // Return the new Case object
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // Return null if case creation fails
	}

	@Override
	public Case getCaseDetails(int caseId) throws IncidentNumberNotFoundException {
		String query = "SELECT * FROM cases WHERE CaseId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, caseId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Case(rs.getInt("CaseId"), rs.getString("CaseDescription"), rs.getInt("IncidentId"));
			} else {
				// Throw custom exception if no case is found
				throw new IncidentNumberNotFoundException("Case ID " + caseId + " not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncidentNumberNotFoundException("Database error while retrieving case: " + e.getMessage());
		}
	}

	@Override
	public boolean updateCaseDetails(Case caseObj) throws IncidentNumberNotFoundException {
		// Verify that the case exists before updating
		Case existingCase = getCaseDetails(caseObj.getCaseId());
		if (existingCase == null) {
			throw new IncidentNumberNotFoundException("Case ID " + caseObj.getCaseId() + " not found.");
		}

		String query = "UPDATE cases SET CaseDescription = ?, IncidentId = ? WHERE CaseId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, caseObj.getCaseDescription());
			stmt.setInt(2, caseObj.getIncidentId());
			stmt.setInt(3, caseObj.getCaseId());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Case> getAllCases() {
		List<Case> cases = new ArrayList<>();
		String query = "SELECT * FROM cases";
		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				cases.add(new Case(rs.getInt("CaseId"), rs.getString("CaseDescription"), rs.getInt("IncidentId")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases; // Return the list of cases
	}

	@Override
	public Incident getIncidentById(int incidentId) {
		String sql = "SELECT * FROM incidents WHERE incidentID = ?";
		Incident incident = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, incidentId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				// Create the Incident object
				incident = new Incident(rs.getInt("incidentID"), rs.getString("incidentType"),
						rs.getString("incidentDate"), rs.getString("location"), rs.getString("description"),
						rs.getString("status"), rs.getInt("victimID"), rs.getInt("suspectID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return incident; // Return the retrieved Incident or null if not found
	}

}
