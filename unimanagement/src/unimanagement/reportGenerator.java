/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unimanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author HP
 */
public class reportGenerator {
    
    private Connection conn;

    public reportGenerator() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006");
    }

    // Fetch sign-in logs filtered by optional staffID and/or date (date format: "YYYY-MM-DD")
    public List<Map<String, Object>> getFilteredSignInLogs(String staffID, String date) throws SQLException {
        List<Map<String, Object>> logs = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM signin_logs WHERE 1=1");

        if (staffID != null && !staffID.trim().isEmpty()) {
            sql.append(" AND staffID = ?");
        }
        if (date != null && !date.trim().isEmpty()) {
            sql.append(" AND DATE(sign_in_time) = ?");
        }

        PreparedStatement pst = conn.prepareStatement(sql.toString());

        int paramIndex = 1;
        if (staffID != null && !staffID.trim().isEmpty()) {
            pst.setString(paramIndex++, staffID);
        }
        if (date != null && !date.trim().isEmpty()) {
            pst.setString(paramIndex++, date);
        }

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("staffID", rs.getString("staffID"));
            entry.put("sign_in_time", rs.getTimestamp("sign_in_time"));
            entry.put("sign_out_time", rs.getTimestamp("sign_out_time"));
            // Add more fields as needed
            logs.add(entry);
        }
        return logs;
    }

    // Fetch roster logs filtered by optional staffID and/or date (date format: "YYYY-MM-DD")
    public List<Map<String, Object>> getFilteredRosterLogs(String staffID, String date) throws SQLException {
        List<Map<String, Object>> logs = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM rosters WHERE 1=1");

        if (staffID != null && !staffID.trim().isEmpty()) {
            sql.append(" AND staffID = ?");
        }
        if (date != null && !date.trim().isEmpty()) {
            sql.append(" AND DATE(shift_start) = ?");
        }

        PreparedStatement pst = conn.prepareStatement(sql.toString());

        int paramIndex = 1;
        if (staffID != null && !staffID.trim().isEmpty()) {
            pst.setString(paramIndex++, staffID);
        }
        if (date != null && !date.trim().isEmpty()) {
            pst.setString(paramIndex++, date);
        }

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("staffID", rs.getString("staffID"));
            entry.put("shift_start", rs.getTimestamp("shift_start"));
            entry.put("shift_end", rs.getTimestamp("shift_end"));
            logs.add(entry);
        }
        return logs;
    }

    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    private String buildQueryForPeriod(String tableName, String period, String dateColumn) {
        return buildQueryForPeriod(tableName, period, dateColumn, null);
    }

    private String buildQueryForPeriod(String tableName, String period, String dateColumn, String extraCondition) {
        String whereClause = "";

        switch (period.toLowerCase()) {
            case "daily":
                whereClause = String.format("WHERE DATE(%s) = CURDATE()", dateColumn);
                break;
            case "weekly":
                whereClause = String.format("WHERE %s >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)", dateColumn);
                break;
            case "monthly":
                whereClause = String.format("WHERE %s >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)", dateColumn);
                break;
            case "yearly":
                whereClause = String.format("WHERE %s >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)", dateColumn);
                break;
            default:
                whereClause = ""; // No filtering
        }

        if (extraCondition != null && !extraCondition.isEmpty()) {
            if (whereClause.isEmpty()) {
                whereClause = "WHERE " + extraCondition;
            } else {
                whereClause += " AND " + extraCondition;
            }
        }

        return String.format("SELECT * FROM %s %s ORDER BY %s DESC", tableName, whereClause, dateColumn);
    }

    
    /**
     * Generates a combined report from multiple related tables.
     * @param period
     * @return 
     * @throws java.sql.SQLException
     */
public List<Map<String, Object>> generateDetailedReport(String period, String unit) throws SQLException {
    List<Map<String, Object>> allData = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

        // 1. Rosters table — filtered by shift_start + unit (via registration join)
        String rostersQuery = """
            SELECT r.staffID, r.shift_start, r.shift_end
            FROM rosters r
            JOIN registration reg ON r.staffID = reg.staffID
            WHERE reg.unit = ? AND DATE(r.shift_start) BETWEEN ? AND ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(rostersQuery)) {
            setPeriodParameters(stmt, unit, period);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("type", "Roster");
                    row.put("staffID", rs.getString("staffID"));
                    row.put("shift_start", rs.getTimestamp("shift_start"));
                    row.put("shift_end", rs.getTimestamp("shift_end"));
                    allData.add(row);
                }
            }
        }

        // 2. Unit Shifts table — unit already present in table
        String unitShiftsQuery = """
            SELECT shift_id, unit, shift_name, start_time, end_time
            FROM unit_shifts
            WHERE unit = ? AND DATE(shift_name) BETWEEN ? AND ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(unitShiftsQuery)) {
            setPeriodParameters(stmt, unit, period);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("type", "Unit Shift");
                    row.put("shift_id", rs.getString("shift_id"));
                    row.put("unit", rs.getString("unit"));
                    row.put("shift_name", rs.getDate("shift_name"));
                    row.put("shift_start", rs.getTime("stat_time"));
                    row.put("shift_end", rs.getTime("end_time"));
                    allData.add(row);
                }
            }
        }

        // 3. Shift Attendance table — unit via registration join
        String shiftAttendanceQuery = """
            SELECT a.attendance_id, a.staffID, a.shift_start, a.shift_end, 
                   a.login_time, a.logout_time, a.attendance_date
            FROM shift_attendance a
            JOIN registration reg ON a.staffID = reg.staffID
            WHERE reg.unit = ? AND a.attendance_date BETWEEN ? AND ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(shiftAttendanceQuery)) {
            setPeriodParameters(stmt, unit, period);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("type", "Shift Attendance");
                    row.put("attendance_id", rs.getString("attendance_id"));
                    row.put("staff_id", rs.getString("staffID"));
                    row.put("shift_start", rs.getTimestamp("shift_start"));
                    row.put("shift_end", rs.getTimestamp("shift_end"));
                    row.put("login_time", rs.getTimestamp("login_time"));
                    row.put("logout_time", rs.getTimestamp("logout_time"));
                    row.put("attendance_date", rs.getDate("attendance_date"));
                    allData.add(row);
                }
            }
        }

        // 4. Shift Swap table — both requester and requested_with must be in same unit
        String shiftSwapQuery = """
            SELECT s.request_id, s.requester_id, s.requested_shift_id, 
                   s.requested_with_id, s.status, s.request_date
            FROM shift_swap s
            JOIN registration r1 ON s.requester_id = r1.staffID
            JOIN registration r2 ON s.requested_with_id = r2.staffID
            WHERE r1.unit = ? AND r2.unit = ? AND s.request_date BETWEEN ? AND ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(shiftSwapQuery)) {
            setPeriodParametersForSwap(stmt, unit, period);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("type", "Shift Swap");
                    row.put("request_id", rs.getString("request_id"));
                    row.put("requester_id", rs.getString("requester_id"));
                    row.put("requested_shift_id", rs.getString("requested_shift_id"));
                    row.put("requested_with_id", rs.getString("requested_with_id"));
                    row.put("Status", rs.getBoolean("status"));
                    row.put("request_date", rs.getDate("request_date"));
                    allData.add(row);
                }
            }
        }
    }

    return allData;
}

    

private void setPeriodParameters(PreparedStatement stmt, String unit, String period) throws SQLException {
    Date[] range = getDateRangeForPeriod(period);
    stmt.setString(1, unit);
    stmt.setDate(2, range[0]);
    stmt.setDate(3, range[1]);
}

private void setPeriodParametersForSwap(PreparedStatement stmt, String unit, String period) throws SQLException {
    Date[] range = getDateRangeForPeriod(period);
    stmt.setString(1, unit);
    stmt.setString(2, unit); // both requester and requested_with should be from the same unit
    stmt.setDate(3, range[0]);
    stmt.setDate(4, range[1]);
}

private Date[] getDateRangeForPeriod(String period) {
    LocalDate now = LocalDate.now();
    LocalDate start;
    LocalDate end;

    switch (period.toLowerCase()) {
        case "weekly":
            start = now.with(DayOfWeek.MONDAY);
            end = start.plusDays(6);
            break;
        case "monthly":
            start = now.withDayOfMonth(1);
            end = now.withDayOfMonth(now.lengthOfMonth());
            break;
        case "yearly":
            start = now.withDayOfYear(1);
            end = now.withDayOfYear(now.lengthOfYear());
            break;
        default:
            start = now;
            end = now;
            break;
    }

    return new Date[]{Date.valueOf(start), Date.valueOf(end)};
}


    
}
