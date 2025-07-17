/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
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
    public List<Map<String, Object>> getMostFrequentOrders() throws SQLException {
    List<Map<String, Object>> result = new ArrayList<>();

    String sql = """
        SELECT product_name, COUNT(*) AS order_count
        FROM customer_orders
        GROUP BY product_name
        ORDER BY order_count DESC
        LIMIT 5
    """;

    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006");
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("product_name", rs.getString("product_name"));
            row.put("order_count", rs.getInt("order_count"));
            result.add(row);
        }
    }

    return result;
}


  
    private String buildQueryForPeriod(String tableName, String period, String dateColumn) {
    return buildQueryForPeriod(tableName, period, dateColumn, null);
}

private String buildQueryForPeriod(String tableName, String period, String dateColumn, String extraCondition) {
    String whereClause = "";

    switch (period.toLowerCase()) {
        case "daily":
            whereClause = String.format("WHERE %s >= CURDATE()", dateColumn);
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
            whereClause = "";  // no filtering
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

    public List<Map<String, Object>> generateDetailedReport(String period) throws SQLException {
    List<Map<String, Object>> allData = new ArrayList<>();

    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006");
    String customerQuery = buildQueryForPeriod("customer_orders", period, "order_time");
    String kitchenQuery = buildQueryForPeriod("food_inventory", period, "last_updated");
    String servingQuery = buildQueryForPeriod("customer_orders", period, "order_time", "order_status = 'Served'");

    // Customer Orders
    try (PreparedStatement stmt = conn.prepareStatement(customerQuery);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("type", "Customer Order");
            row.put("customer_name", rs.getString("customer_name"));
            row.put("product_name", rs.getString("product_name"));
            row.put("quantity", rs.getString("quantity"));
            row.put("unit_price", rs.getString("unit_price"));
            row.put("total_price", rs.getDouble("total_price"));
            row.put("payment_method", rs.getString("payment_method"));
            row.put("payment_confirmed", rs.getString("payment_confirmed"));
            row.put("order_status", rs.getString("order_status"));
            row.put("order_time", rs.getTimestamp("order_time"));
            row.put("order_id", rs.getString("order_id"));

            allData.add(row);
        }
    }

    // Kitchen Updates
    try (PreparedStatement stmt = conn.prepareStatement(kitchenQuery);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("type", "Kitchen Update");
            row.put("item_name", rs.getString("item_name"));
            row.put("quantity_available", rs.getInt("quantity_available"));
            row.put("unit_price", rs.getDouble("unit_price"));
            allData.add(row);
        }
    }

    // Served Orders
    try (PreparedStatement stmt = conn.prepareStatement(servingQuery);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("type", "Served Order");
            row.put("order_id", rs.getString("order_id"));
            row.put("customer_name", rs.getString("customer_name"));
            row.put("product_name", rs.getString("product_name"));
            row.put("quantity", rs.getString("quantity"));
            row.put("unit_price", rs.getString("unit_price"));
            row.put("total_price", rs.getDouble("total_price"));
            row.put("served_time", rs.getTimestamp("order_time")); // Assuming same as order_time
            allData.add(row);
        }
        
    }

    return allData;
}

   

}
