package com.crimeanalysisandreportsystem.test;

import com.crimeanalysisandreportsystem.dao.IncidentDao;
import com.crimeanalysisandreportsystem.entity.Incident;
import com.crimeanalysisandreportsystem.entity.IncidentStatus;
import com.crimeanalysisandreportsystem.exception.DataAccessException;
import com.crimeanalysisandreportsystem.exception.IncidentNumberNotFoundException;
import com.crimeanalysisandreportsystem.service.IncidentDaoImpl;
import com.crimeanalysisandreportsystem.util.DatabaseConnection;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CrimeAnalysisTest {

    private IncidentDao incidentDao;
    private Incident incident;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Database connection is not available.");
        }
        connection.setAutoCommit(false);

        incidentDao = new IncidentDaoImpl(connection);

        incident = new Incident("Theft", new Date(), new BigDecimal("34.0522"), new BigDecimal("-118.2437"),
                "Suspected robbery", IncidentStatus.OPEN, 1, 1, 1);
    }

    @Test
    void testCreateIncident_Success() throws DataAccessException, IncidentNumberNotFoundException {
        Incident newIncident = new Incident("Theft", new Date(), new BigDecimal("34.0522"),
                new BigDecimal("-118.2437"), "Suspected robbery", IncidentStatus.OPEN, 1, 1, 1);

        boolean result = incidentDao.createIncident(newIncident);
        assertTrue(result);
        assertNotEquals(0, newIncident.getIncidentID());
        System.out.println("Incident creation result: " + result + ", ID: " + newIncident.getIncidentID());

        Incident retrievedIncident = incidentDao.getIncidentById(newIncident.getIncidentID());
        
        System.out.println("Retrieved Incident Details:");
        System.out.println("Type: " + retrievedIncident.getIncidentType());
        System.out.println("Description: " + retrievedIncident.getDescription());
        
        assertNotNull(retrievedIncident);
        assertEquals(newIncident.getIncidentType(), retrievedIncident.getIncidentType());
        assertEquals(newIncident.getDescription(), retrievedIncident.getDescription());
    }

    @Test
    void testCreateIncident_Failure() {
        Exception exception = assertThrows(DataAccessException.class, () -> {
            incidentDao.createIncident(null);
        });
        System.out.println("Expected exception caught: " + exception.getMessage());
        assertEquals("Incident cannot be null", exception.getMessage());
        
    }
    
    @Test
    void testGetIncidentById() throws DataAccessException, IncidentNumberNotFoundException {
        incidentDao.createIncident(incident);
        System.out.println("Incident created with ID: " + incident.getIncidentID());
        Incident retrieved = incidentDao.getIncidentById(incident.getIncidentID());
        System.out.println("Retrieved Incident ID: " + retrieved.getIncidentID());
        assertNotNull(retrieved);
        assertEquals(incident.getIncidentID(), retrieved.getIncidentID());
    }

    @Test
    void testUpdateIncidentStatus() throws DataAccessException, IncidentNumberNotFoundException {
        incidentDao.createIncident(incident);
        System.out.println("Incident created with ID: " + incident.getIncidentID());
        
        boolean updated = incidentDao.updateIncidentStatus(IncidentStatus.CLOSED, incident.getIncidentID());
        System.out.println("Status update result: " + updated);
        
        assertTrue(updated);

        Incident updatedIncident = incidentDao.getIncidentById(incident.getIncidentID());
        assertEquals(IncidentStatus.CLOSED, updatedIncident.getStatus());
    }

    @AfterEach
    void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback(); 
                DatabaseConnection.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
