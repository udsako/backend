/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unimanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author HP
 */
public class AdminAccessUtils {
    
     public static void showAdminDashboard() {
        try {
            AdminDashboard dashboard = new AdminDashboard();
            dashboard.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();  // <-- make sure you SEE any issues
            JOptionPane.showMessageDialog(null, "Error launching admin dashboard: " + e.getMessage());
        }
    
     }
                 

    // You can also include methods like getStaffByUnit(), getRostersByUnit(), etc.


    // Generic method to fetch records based on unit
    private static List<Map<String, Object>> getRecordsByUnit(String query, String unitFilter) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, unitFilter);
            ResultSet rs = pstmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                result.add(row);
            }
        }

        return result;
    }

    // Staff by unit
    public static List<Map<String, Object>> getStaffByUnit(String unit) throws Exception {
        String query = "SELECT staffID, name, role, unit FROM registration WHERE unit = ?";
        return getRecordsByUnit(query, unit);
    }

    // Sign-in logs by unit
    public static List<Map<String, Object>> getSignInLogsByUnit(String unit) throws Exception {
        String query = "SELECT staffID, login_time, logout_time, unit FROM signin_logs WHERE unit = ?";
        return getRecordsByUnit(query, unit);
    }

    // Rosters by unit
    public static List<Map<String, Object>> getRostersByUnit(String unit) throws Exception {
        String query = "SELECT staffID, shift_start, shift_end, unit FROM roasters WHERE unit = ?";
        return getRecordsByUnit(query, unit);
    }

    
}
