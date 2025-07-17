/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafeteria;

import cafeteria.email;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.Timer;


/**
 *
 * @author HP
 */
public class kitchen extends javax.swing.JFrame {
    
        public JTable getTable() {
    return jTable1;
}

public JPanel getMainPanel() {
    return jPanel1;
}
    public kitchen(DefaultTableModel model) {
    initComponents();
    jTable1.setModel(model);
}
    
    public void setReadOnlyMode(boolean readOnly) {
    jButton1.setEnabled(!readOnly);
    jButton2.setEnabled(!readOnly);
    jButton3.setEnabled(!readOnly);
    jButton4.setEnabled(!readOnly);
    jButton6.setEnabled(!readOnly);
    jComboBox1.setEnabled(!readOnly);
    jComboBox2.setEnabled(!readOnly);
    jComboBox3.setEnabled(!readOnly);    // Disable update status
    // Disable or hide any editing features
}

    
    
        
public kitchen() {
        initComponents();
        jPanel1.setPreferredSize(new Dimension(1200,4000));
        jPanel1.setLayout(new BorderLayout());
        
            Timer timer = new Timer(300000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshOrderTable();
            }
        });
        timer.start(); // Start the timer
    }

    
     public void refreshOrderTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); // Replace jTable1 with your actual table name
        model.setRowCount(0); // Clear existing rows

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cafeteria",
                "root",
                "Awele2006"
            );

            String sql = "SELECT * FROM customer_order"; // Replace with your actual table/query
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getString("order_id"),
                    rs.getString("customer_name"),
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("unit_price"),
                    rs.getDouble("total_price"),
                    rs.getString("payment_method"),
                    rs.getString("order_status")
                };
                model.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Method to alert low inventory items and send email notifications
    public void alertLowInventory() {
        int threshold = 5; // low stock threshold

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006");
             Statement stmt = con.createStatement()) {

            // Query to check low inventory items
            ResultSet rs = stmt.executeQuery("SELECT item_name, quantity_available FROM food_inventory");

            StringBuilder lowItems = new StringBuilder();
            boolean lowStockFound = false;

            while (rs.next()) {
                int qty = rs.getInt("quantity_available");
                String product = rs.getString("item_name");

                if (qty <= threshold) {
                    lowStockFound = true;
                    lowItems.append(product).append(": ").append(qty).append(" units left\n");
                }
            }

            if (lowStockFound) {
                // Show popup with all low stock items
                JOptionPane.showMessageDialog(null,
                        "⚠️ Low Stock Alert:\n" + lowItems.toString(),
                        "Low Stock Warning",
                        JOptionPane.WARNING_MESSAGE);

                // Fetch emails of Kitchen and Head of Unit staff
                String sqlUsers = "SELECT email FROM registration WHERE role IN ('Kitchen', 'Head of Unit')";
                try (PreparedStatement psUsers = con.prepareStatement(sqlUsers);
                     ResultSet rsUsers = psUsers.executeQuery()) {

                    String subject = "⚠️ Low Food Inventory Alert";
                    String messageBody = "<h3>Attention:</h3><p>The following food items are low in inventory:</p><pre>"
                            + lowItems.toString() + "</pre>";

                    while (rsUsers.next()) {
                        String userEmail = rsUsers.getString("email");
                        // Assuming 'email' is an accessible object with sendEmail method
                        email.sendEmail(userEmail, subject, messageBody);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1240, 660));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\cafeteria\\src\\images\\pau_icon-150x150.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 36)); // NOI18N
        jLabel2.setText("KITCHEN INVENTORY SYSTEM");

        jLabel3.setText("Item Name");

        jLabel4.setText("Quantity available");

        jLabel5.setText("Unit Price");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plantain", "Eggs", "Jollof Rice", "Fried Rice", "Special Rice", "Yamarita", "Amala", "Ewedu", "Egusi", "Sausage", "Beef", "Chicken", "Eba", "Semo ", "Poundo Yam", "Stew", "Spcial Fried Rice", "Spaghetti", "Macaroni", "Boiled yam", "Egg sauce", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30", "20", "20", "20", "20", "20", "15", "40", "30", "21", "30", "15", "10", "15", "19", "20", "30", "15", "10", "20", "15", " " }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "500 ", "200 ", "200", "150 ", "500 ", "250", "250 ", "400", "1800", "1500", " ", " " }));

        jTable1.setBackground(new java.awt.Color(0, 153, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "item_name", "quantity_available", "unit_price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton1.setText("Add to Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton2.setText("Insert To database ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton3.setText("Update to database");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton4.setText("Get from database");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton6.setText("Delete from database");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(264, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jButton4)
                                .addGap(26, 26, 26)
                                .addComponent(jButton2)
                                .addGap(26, 26, 26)
                                .addComponent(jButton6)))
                        .addGap(28, 28, 28)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String itemname = jComboBox1.getSelectedItem().toString();
    String quantity = jComboBox3.getSelectedItem().toString();
    String unitPrice = jComboBox2.getSelectedItem().toString();
    

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

  

    // Add row
    model.addRow(new Object[]{itemname, unitPrice, quantity});
  // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006");
    String sql = "INSERT INTO food_inventory (item_name, quantity_available, unit_price) VALUES (?, ?, ?)";
    PreparedStatement pst = con.prepareStatement(sql);

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        String itemName = model.getValueAt(i, 0).toString();
        int quantityAvailable = Integer.parseInt(model.getValueAt(i, 1).toString());
        double unitPrice = Double.parseDouble(model.getValueAt(i, 2).toString());

        pst.setString(1, itemName);
        pst.setInt(2, quantityAvailable);
        pst.setDouble(3, unitPrice);
        pst.executeUpdate();
    }

    JOptionPane.showMessageDialog(this, "Food inventory saved successfully.");
    con.close();
    alertLowInventory();
    

} catch (SQLIntegrityConstraintViolationException dupEx) {
    JOptionPane.showMessageDialog(this, "Duplicate entry: This item already exists in the inventory.");
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error saving food inventory: " + e.getMessage());
}

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006")) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear table

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM food_inventory");

        while (rs.next()) {
            String itemname = rs.getString("item_name");
            int unitPrice = rs.getInt("unit_price");
            int quantity_available = rs.getInt("quantity_available");

            model.addRow(new Object[]{itemname, unitPrice, quantity_available});
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data.");
    }
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006");

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int rowCount = model.getRowCount();

    // Merge duplicates in JTable by item_name (case-insensitive) + unit_price
    Map<String, Integer> mergedQuantities = new HashMap<>();
    Map<String, Double> itemPrices = new HashMap<>();

    for (int i = 0; i < rowCount; i++) {
        String itemName = model.getValueAt(i, 0).toString().trim().toLowerCase();
        int quantity = Integer.parseInt(model.getValueAt(i, 1).toString().trim());
        double unitPrice = Double.parseDouble(model.getValueAt(i, 2).toString().trim());

        String key = itemName + "_" + unitPrice;

        mergedQuantities.put(key, mergedQuantities.getOrDefault(key, 0) + quantity);
        itemPrices.put(key, unitPrice);
    }

    // Clear JTable and insert merged items back
    model.setRowCount(0);

    for (String key : mergedQuantities.keySet()) {
        String[] parts = key.split("_");
        String itemName = parts[0];
        double unitPrice = itemPrices.get(key);
        int mergedQty = mergedQuantities.get(key);

        // Display in JTable with first-letter uppercase
        model.addRow(new Object[]{capitalize(itemName), mergedQty, unitPrice});

        // Update or insert into database
        String checkSql = "SELECT quantity_available FROM food_inventory WHERE LOWER(item_name) = ? AND unit_price = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);
        checkStmt.setString(1, itemName);
        checkStmt.setDouble(2, unitPrice);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            int currentQty = rs.getInt("quantity_available");
            int newQty = currentQty + mergedQty;

            String updateSql = "UPDATE food_inventory SET quantity_available = ? WHERE LOWER(item_name) = ? AND unit_price = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateSql);
            updateStmt.setInt(1, newQty);
            updateStmt.setString(2, itemName);
            updateStmt.setDouble(3, unitPrice);
            updateStmt.executeUpdate();
            updateStmt.close();
        } else {
            // Insert new if not exists
            String insertSql = "INSERT INTO food_inventory (item_name, quantity_available, unit_price) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = con.prepareStatement(insertSql);
            insertStmt.setString(1, capitalize(itemName));
            insertStmt.setInt(2, mergedQty);
            insertStmt.setDouble(3, unitPrice);
            insertStmt.executeUpdate();
            insertStmt.close();
        }

        rs.close();
        checkStmt.close();
        
    }

    con.close();
    JOptionPane.showMessageDialog(this, "Inventory successfully updated to database.");
    
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Database Update Error: " + e.getMessage());
}






        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    // Helper to capitalize item names
private String capitalize(String str) {
    if (str == null || str.isEmpty()) return str;
    return str.substring(0, 1).toUpperCase() + str.substring(1);
}
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow != -1) {
        String itemname = jTable1.getValueAt(selectedRow, 0).toString(); // assuming foodName is column 0

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006")) {

            // Delete from database
            String deleteQuery = "DELETE FROM food_inventory WHERE item_name = ?";
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            stmt.setString(1, itemname);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow); // delete from table
                JOptionPane.showMessageDialog(this, "Item deleted from database and table.");
            } else {
                JOptionPane.showMessageDialog(this, "Item not found in database.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting item from database.");
        }

    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to delete.");
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kitchen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
