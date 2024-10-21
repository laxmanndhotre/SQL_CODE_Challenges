package com.hexaware.crimeanalysis.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexaware.crimeanalysis.dao.CrimeAnalysisDaoImpl;
import com.hexaware.crimeanalysis.entity.Incident; 
import com.hexaware.crimeanalysis.myexceptions.IncidentNumberNotFoundException;

class CrimeAnalysisDaoTest {

    private CrimeAnalysisDaoImpl dao;

    @BeforeEach
    void setUp() {
        dao = new CrimeAnalysisDaoImpl(); 
    }


    @Test
    void testCreateIncident() {
        // Arrange
        Incident incident = new Incident(101, "Robbery", "2024-10-20", "New York", "Stolen car", "Open", 101, 201);

        // Act
        boolean result = dao.createIncident(incident);

        // Assert
        assertTrue(result, "Incident creation should be successful."); // Expect creation to be successful
        
        // Verify the created incident attributes
        Incident createdIncident = dao.getIncidentById(101);

        
        System.out.println("Created Incident: " + createdIncident);

        assertNotNull(createdIncident, "Created incident should not be null.");
        assertEquals("Robbery", createdIncident.getIncidentType());
        assertEquals("2024-10-20", createdIncident.getIncidentDate());
        assertEquals("New York", createdIncident.getLocation());
        assertEquals("Stolen car", createdIncident.getDescription());
        assertEquals("Open", createdIncident.getStatus());
        assertEquals(101, createdIncident.getVictimID());
        assertEquals(201, createdIncident.getSuspectID());
    }

    @Test
    void testUpdateIncidentStatus() {
        // Arrange
        int incidentId = 1;
        String newStatus = "Closed";

        // Act
        boolean result;
		try {
			result = dao.updateIncidentStatus(newStatus, incidentId);
			 // Assert
	        assertTrue(result); // Expect update to be successful
		} catch (IncidentNumberNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       

        // Verify the status has been updated
        Incident updatedIncident = dao.getIncidentById(incidentId);
        assertEquals("Closed", updatedIncident.getStatus());
    }
}
