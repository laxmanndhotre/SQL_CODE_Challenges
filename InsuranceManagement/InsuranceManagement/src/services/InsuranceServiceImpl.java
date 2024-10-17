package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import dao.IPolicyService;
import entities.Policy;

public class InsuranceServiceImpl implements IPolicyService {
    private final String url = "jdbc:mysql://localhost:3306/insurance";
    private final String user = "root";  
    private final String password = "0909";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    
    public boolean createPolicy(Policy policy) {
        String sql = "INSERT INTO Policy (policyName, policyType) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setString(2, policy.getPolicyType());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    
    public Policy getPolicy(int policyId)   {
        String sql = "SELECT * FROM Policy WHERE policyId = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, policyId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Policy(rs.getInt("policyId"), rs.getString("policyName"), rs.getString("policyType"));
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
       
        return null;
    }

    
    public Collection<Policy> getAllPolicies() {
        Collection<Policy> policies = new ArrayList<>();
        String sql = "SELECT * FROM Policy";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                policies.add(new Policy(rs.getInt("policyId"), rs.getString("policyName"), rs.getString("policyType")));
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
        return policies;
    }

    
    public boolean updatePolicy(Policy policy) {
        String sql = "UPDATE Policy SET policyName = ?, policyType = ? WHERE policyId = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setString(2, policy.getPolicyType());
            pstmt.setInt(3, policy.getPolicyId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
    }

    
    public boolean deletePolicy(int policyId) {
        String sql = "DELETE FROM Policy WHERE policyId = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, policyId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
        	System.out.println(e);
            return false;
        }
    }
}
