/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package unimanagement;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.sql.ResultSetMetaData;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import unimanagement.nonsupervisorsdashboard;

/**
 *
 * @author HP
 */
public class AdminDashboard extends javax.swing.JFrame {
    private JDateChooser dateChooser;
    private JTextField dynamicTextField;
    private JPanel filterInputPanel;
    private final static String TEXT_FIELD = "TextField";
    private final static String DATE_CHOOSER = "DateChooser";
public AdminDashboard() {
        initComponents();
        setupDynamicFilterInput();
        setupComboBoxListener();
    }

    private String getUnitPrefixFromTab(String tabName) {
        switch (tabName.toLowerCase()) {
            case "facility": return "FAC";
            case "cafeteria": return "CAF";
            case "horticulture": return "HOR";
            case "security": return "SEC";
            case "maintenance": return "MAI";
            default: return "";
        }
    }

    

     // Logs tab already exists — nothing needs to be changed here

private String getFilterInputValue() {
    String selected = jComboBox2.getSelectedItem().toString();
    if (selected.equalsIgnoreCase("date")) {
        if (dateChooser.getDate() == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dateChooser.getDate());
    } else {
        return dynamicTextField.getText().trim(); // now uses the new dynamic field
    }
}




  // this replaces jTextField1



private void setupDynamicFilterInput() {
    // Dynamically create fresh input components
    dateChooser = new JDateChooser();
    dynamicTextField = new JTextField();

    // Match size to the original jTextField1
    Dimension size = jTextField1.getSize();
    dateChooser.setPreferredSize(size);
    dynamicTextField.setPreferredSize(size);

    // Create CardLayout panel and add both input types
    filterInputPanel = new JPanel(new CardLayout());
    filterInputPanel.setBounds(jTextField1.getBounds());  // Position it where jTextField1 was

    filterInputPanel.add(dynamicTextField, TEXT_FIELD);
    filterInputPanel.add(dateChooser, DATE_CHOOSER);

    // Replace jTextField1 with the dynamic panel
    jPanel2.setLayout(null);  // Important for custom bounds
    jPanel2.remove(jTextField1);
    jPanel2.add(filterInputPanel);

    // Show the right panel initially
    String selected = jComboBox2.getSelectedItem().toString();
    switchInputPanel(selected.equalsIgnoreCase("date") ? DATE_CHOOSER : TEXT_FIELD);

    jPanel2.revalidate();
    jPanel2.repaint();
}




private void switchInputPanel(String panelName) {
    CardLayout cl = (CardLayout) filterInputPanel.getLayout();
    cl.show(filterInputPanel, panelName);
}


    private void setupComboBoxListener() {
        jComboBox2.addActionListener(e -> {
            String selected = jComboBox2.getSelectedItem().toString();
            if (selected.equalsIgnoreCase("date")) {
                switchInputPanel(DATE_CHOOSER);
            } else {
                switchInputPanel(TEXT_FIELD);
            }
        });
    }
    private String getUnitPrefix(String fullUnitName) {
    switch (fullUnitName.toLowerCase()) {
        case "cafeteria": return "CAF";
        case "maintenance": return "MAI";
        case "facility": return "FAC";
        case "security": return "SEC";
        case "horticulture": return "HOR";
        default: return "";
    }
}

    private void displayResults(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        boolean hasRecords = false;
        while (rs.next()) {
            hasRecords = true;
            for (int i = 1; i <= columnCount; i++) {
                sb.append(meta.getColumnName(i)).append(": ").append(rs.getString(i)).append(" | ");
            }
            sb.append("\n");
        }

        if (!hasRecords) {
            sb.append("No records found.");
        }

        jTextArea1.setText(sb.toString());


        
        
        
        
        
        
//    private void exportResultsToCSV() {
//        if (currentResultSet == null) {
//            JOptionPane.showMessageDialog(this, "No data to export.");
//            return;
//        }
//
//        try {
//            currentResultSet.beforeFirst();
//            ResultSetMetaData meta = currentResultSet.getMetaData();
//            int columnCount = meta.getColumnCount();
//            FileWriter writer = new FileWriter("exported_results.csv");
//
//            // Write header
//            for (int i = 1; i <= columnCount; i++) {
//                writer.append(meta.getColumnName(i));
//                if (i < columnCount) writer.append(",");
//            }
//            writer.append("\n");
//
//            // Write data rows
//            while (currentResultSet.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    writer.append(currentResultSet.getString(i));
//                    if (i < columnCount) writer.append(",");
//                }
//                writer.append("\n");
//            }
//
//            writer.flush();
//            writer.close();
//            JOptionPane.showMessageDialog(this, "Results exported to exported_results.csv");
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error exporting CSV: " + ex.getMessage());
//        }
    }

    /**
     * Creates new form AdminDashboard
     */
   

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
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\unimanagement\\src\\images\\pau_icon-150x150.png")); // NOI18N

        jLabel2.setBackground(new java.awt.Color(51, 153, 255));
        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ADMIN DASHBOARD");

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 204));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel3.setText("Select a Unit");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cafeteria", "Maintenance", "Facility", "Security", "Horticulture ", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("View Staff");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("View Logs ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Rosters");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Filter by:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an option", "Date ", "staffID" }));

        jButton4.setText("Filter Signin Logs");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Filter Roster Logs");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton6.setText("Download");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jButton3)
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(61, 61, 61)
                                        .addComponent(jButton4))
                                    .addComponent(jButton5)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(201, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(403, 403, 403))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Staff Logs", jPanel2);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));

        jButton8.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton8.setText("Display Record");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jLabel5.setText("STAFF ID");

        jTextField2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "staffID", "shift_start", "shift_end", "login_time", "logout_time", "attendance_date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Facility", jPanel3);

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));

        jTextField3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jLabel6.setText("STAFF ID");

        jButton9.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton9.setText("Display Record");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "staffID", "shift_start", "shift_end", "login_time", "logout_time", "attendance_date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Cafeteria", jPanel4);

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jLabel7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jLabel7.setText("STAFF ID");

        jTextField4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jButton10.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton10.setText("Display Record");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "staffID", "shift_start", "shift_end", "login_time", "logout_time", "attendance_date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Horticulture ", jPanel5);

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        jLabel8.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jLabel8.setText("STAFF ID");

        jTextField5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jButton11.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton11.setText("Display Record");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "staffID", "shift_start", "shift_end", "login_time", "logout_time", "attendance_date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addGap(261, 261, 261)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Security", jPanel6);

        jPanel7.setBackground(new java.awt.Color(51, 153, 255));

        jButton7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton7.setText("Display Record");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jLabel9.setText("STAFF ID");

        jTextField6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "staffID", "shift_start", "shift_end", "login_time", "logout_time", "attendance_date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(273, 273, 273)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Maintenance", jPanel7);

        jTabbedPane1.addTab("Unit Dashboard", jTabbedPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    String selectedFilter = jComboBox2.getSelectedItem().toString().trim().toLowerCase();
    String userInput = getFilterInputValue(); // Could be from jTextField or DateChooser

    if (userInput.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a value to filter by.");
        return;
    }

    PreparedStatement pstmt;
    String sql;

    if (selectedFilter.equalsIgnoreCase("staffID")) {
        sql = "SELECT * FROM signin_logs WHERE staffID = ? ORDER BY login_time DESC";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userInput);

    } else if (selectedFilter.equalsIgnoreCase("date")) {
        java.sql.Date selectedDate;
        try {
            selectedDate = java.sql.Date.valueOf(userInput); // yyyy-MM-dd
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use yyyy-MM-dd.");
            return;
        }

        sql = "SELECT * FROM signin_logs WHERE signin_date = ? ORDER BY login_time DESC";
        pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, selectedDate);

    } else {
        JOptionPane.showMessageDialog(this, "Unknown filter selected.");
        return;
    }

    ResultSet rs = pstmt.executeQuery();

    StringBuilder logBuilder = new StringBuilder();
    logBuilder.append("=== Filtered Sign-In Logs ===\n\n");

    boolean found = false;
    while (rs.next()) {
        found = true;
        String staffID = rs.getString("staffID");
        java.sql.Timestamp loginTime = rs.getTimestamp("login_time");
        java.sql.Timestamp logoutTime = rs.getTimestamp("logout_time");
        Date signinDate = rs.getDate("signin_date");

        logBuilder.append("Staff ID: ").append(staffID).append("\n")
                  .append("Sign-In Date: ").append(signinDate).append("\n")
                  .append("Login Time: ").append(loginTime).append("\n")
                  .append("Logout Time: ").append(logoutTime != null ? logoutTime : "Still signed in").append("\n")
                  .append("-----------------------------\n");
    }

    if (!found) {
        logBuilder.append("No logs found for the given filter.");
    }

    jTextArea1.setText(logBuilder.toString());

    rs.close();
    pstmt.close();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error fetching sign-in logs: " + e.getMessage());
}
            // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    String selectedUnit = jComboBox1.getSelectedItem().toString().trim();
    String unitPrefix = "";

    if (!selectedUnit.equalsIgnoreCase("All Units")) {
        // Map unit name to staffID prefix
        switch (selectedUnit.toLowerCase()) {
            case "cafeteria" -> unitPrefix = "CAF";
            case "facility" -> unitPrefix = "FAC";
            case "horticulture" -> unitPrefix = "HOR";
            case "security" -> unitPrefix = "SEC";
            case "maintenance" -> unitPrefix = "MAI";
            default -> {
                JOptionPane.showMessageDialog(this, "Unknown unit selected.");
                return;
            }
        }
    }

    String sql;
    PreparedStatement pstmt;

    if (unitPrefix.isEmpty()) {
        // Show all rosters
        sql = "SELECT staffID, shift_start, shift_end FROM rosters ORDER BY shift_start DESC";
        pstmt = conn.prepareStatement(sql);
    } else {
        // Show rosters for specific unit
        sql = "SELECT staffID, shift_start, shift_end FROM rosters WHERE staffID LIKE ? ORDER BY shift_start DESC";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, unitPrefix + "%");
    }

    ResultSet rs = pstmt.executeQuery();

    StringBuilder sb = new StringBuilder();
    sb.append("📋 ROSTERS FOR: ").append(selectedUnit.toUpperCase()).append("\n\n");
    sb.append(String.format("%-12s %-25s %-25s\n", "Staff ID", "Shift Start", "Shift End"));
    sb.append("=".repeat(70)).append("\n");

    boolean found = false;

    while (rs.next()) {
        found = true;
        String staffID = rs.getString("staffID");
        java.sql.Timestamp shiftStart = rs.getTimestamp("shift_start");
        java.sql.Timestamp shiftEnd = rs.getTimestamp("shift_end");

        sb.append(String.format("%-12s %-25s %-25s\n", staffID, shiftStart, shiftEnd));
    }

    if (!found) {
        sb.append("\n⚠️ No roster entries found for this unit.\n");
    }

    jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 13));
    jTextArea1.setText(sb.toString());

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "❌ Error retrieving roster: " + ex.getMessage());
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    String sql = "SELECT * FROM shift_attendance ORDER BY shift_start DESC";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    StringBuilder logBuilder = new StringBuilder();
    logBuilder.append("=== Full Shift Attendance Logs ===\n\n");

    boolean found = false;

    while (rs.next()) {
        found = true;
        String staffID = rs.getString("staffID");
        java.sql.Timestamp shiftStart = rs.getTimestamp("shift_start");
        java.sql.Timestamp shiftEnd = rs.getTimestamp("shift_end");
        java.sql.Timestamp loginTime = rs.getTimestamp("login_time");
        java.sql.Timestamp logoutTime = rs.getTimestamp("logout_time");
        Date attendanceDate = rs.getDate("attendance_date");

        logBuilder.append("Staff ID: ").append(staffID).append("\n")
                  .append("Attendance Date: ").append(attendanceDate).append("\n")
                  .append("Shift Start: ").append(shiftStart).append("\n")
                  .append("Shift End: ").append(shiftEnd).append("\n")
                  .append("Login Time: ").append(loginTime).append("\n")
                  .append("Logout Time: ").append(logoutTime != null ? logoutTime : "Still signed in").append("\n")
                  .append("--------------------------------------------------\n");
    }

    if (!found) {
        logBuilder.append("No shift attendance logs found.");
    }

    jTextArea1.setText(logBuilder.toString());

    rs.close();
    pstmt.close();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error fetching shift attendance logs: " + e.getMessage());
}



        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    // ✅ Get all sign-in logs
    String sql = "SELECT * FROM signin_logs ORDER BY login_time DESC";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    StringBuilder logBuilder = new StringBuilder();
    logBuilder.append("=== Full Sign-In Logs ===\n\n");

    boolean found = false;

    while (rs.next()) {
        found = true;
        String staffID = rs.getString("staffID");
        java.sql.Timestamp loginTime = rs.getTimestamp("login_time");
        java.sql.Timestamp logoutTime = rs.getTimestamp("logout_time");
        Date signinDate = rs.getDate("signin_date");

        logBuilder.append("Staff ID: ").append(staffID).append("\n")
                  .append("Sign-In Date: ").append(signinDate).append("\n")
                  .append("Login Time: ").append(loginTime).append("\n")
                  .append("Logout Time: ").append(
                      logoutTime != null ? logoutTime : "Still signed in").append("\n")
                  .append("-------------------------------------------\n");
    }

    if (!found) {
        logBuilder.append("No sign-in logs found.");
    }

    jTextArea1.setText(logBuilder.toString());

    rs.close();
    pstmt.close();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error fetching sign-in logs: " + e.getMessage());
}


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    String sql = "SELECT staffID, shift_start, shift_end FROM rosters ORDER BY shift_start DESC";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    StringBuilder sb = new StringBuilder();
    sb.append(" ALL STAFF SHIFT ROSTERS\n\n");
    sb.append(String.format("%-12s %-25s %-25s\n", "Staff ID", "Shift Start", "Shift End"));
    sb.append("=".repeat(70)).append("\n");

    boolean found = false;

    while (rs.next()) {
        found = true;
        String staffID = rs.getString("staffID");
        java.sql.Timestamp shiftStart = rs.getTimestamp("shift_start");
        java.sql.Timestamp shiftEnd = rs.getTimestamp("shift_end");

        sb.append(String.format("%-12s %-25s %-25s\n", staffID, shiftStart, shiftEnd));
    }

    if (!found) {
        sb.append("\n⚠️ No roster entries found.\n");
    }

    jTextArea1.setText(sb.toString());

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "❌ Error retrieving roster: " + ex.getMessage());
}
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Save Logs as CSV");

int userSelection = fileChooser.showSaveDialog(this);
if (userSelection != JFileChooser.APPROVE_OPTION) {
    return;
}

File fileToSave = fileChooser.getSelectedFile();
if (!fileToSave.getName().toLowerCase().endsWith(".csv")) {
    fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
}

try (PrintWriter pw = new PrintWriter(new FileWriter(fileToSave))) {
    String textContent = jTextArea1.getText();
    
    if (textContent == null || textContent.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No content to save in CSV.");
        return;
    }

    // Split lines and write to CSV file
    String[] lines = textContent.split("\n");
    for (String line : lines) {
        pw.println(line);
    }

    JOptionPane.showMessageDialog(this, "Text area content successfully saved as CSV!");
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error saving CSV: " + e.getMessage());
    e.printStackTrace();
}
      // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    String staffID = jTextField2.getText().trim().toUpperCase();
    String expectedPrefix = getUnitPrefixFromTab("Facility"); // tab name

    if (staffID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Staff ID.");
        return;
    }

    if (!staffID.startsWith(expectedPrefix)) {
        JOptionPane.showMessageDialog(this, "Invalid Staff ID for Facility unit. Expected prefix: " + expectedPrefix);
        return;
    }

    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

        String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, staffID);
        ResultSet rs = pstmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        DefaultTableModel model = new DefaultTableModel();

        for (int i = 1; i <= colCount; i++) {
            model.addColumn(rsmd.getColumnName(i));
        }

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = new Object[colCount];
            for (int i = 1; i <= colCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No attendance found for Staff ID: " + staffID);
        }

        jTable1.setModel(model);

        rs.close();
        pstmt.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        ex.printStackTrace();
    }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    String staffID = jTextField6.getText().trim().toUpperCase();
    String expectedPrefix = getUnitPrefixFromTab("Maintenance"); // tab name

    if (staffID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Staff ID.");
        return;
    }

    if (!staffID.startsWith(expectedPrefix)) {
        JOptionPane.showMessageDialog(this, "Invalid Staff ID for Maintenance unit. Expected prefix: " + expectedPrefix);
        return;
    }

    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

        String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, staffID);
        ResultSet rs = pstmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        DefaultTableModel model = new DefaultTableModel();

        for (int i = 1; i <= colCount; i++) {
            model.addColumn(rsmd.getColumnName(i));
        }

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = new Object[colCount];
            for (int i = 1; i <= colCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No attendance found for Staff ID: " + staffID);
        }

        jTable5.setModel(model);

        rs.close();
        pstmt.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        ex.printStackTrace();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    String staffID = jTextField3.getText().trim().toUpperCase();
    String expectedPrefix = getUnitPrefixFromTab("Cafeteria"); // tab name

    if (staffID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Staff ID.");
        return;
    }

    if (!staffID.startsWith(expectedPrefix)) {
        JOptionPane.showMessageDialog(this, "Invalid Staff ID for Cafeteria unit. Expected prefix: " + expectedPrefix);
        return;
    }

    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

        String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, staffID);
        ResultSet rs = pstmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        DefaultTableModel model = new DefaultTableModel();

        for (int i = 1; i <= colCount; i++) {
            model.addColumn(rsmd.getColumnName(i));
        }

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = new Object[colCount];
            for (int i = 1; i <= colCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No attendance found for Staff ID: " + staffID);
        }

        jTable2.setModel(model);

        rs.close();
        pstmt.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        ex.printStackTrace();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
String staffID = jTextField4.getText().trim().toUpperCase();
    String expectedPrefix = getUnitPrefixFromTab("Horticulture"); // tab name

    if (staffID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Staff ID.");
        return;
    }

    if (!staffID.startsWith(expectedPrefix)) {
        JOptionPane.showMessageDialog(this, "Invalid Staff ID for Horticulture unit. Expected prefix: " + expectedPrefix);
        return;
    }

    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

        String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, staffID);
        ResultSet rs = pstmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        DefaultTableModel model = new DefaultTableModel();

        for (int i = 1; i <= colCount; i++) {
            model.addColumn(rsmd.getColumnName(i));
        }

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = new Object[colCount];
            for (int i = 1; i <= colCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No attendance found for Staff ID: " + staffID);
        }

        jTable3.setModel(model);

        rs.close();
        pstmt.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        ex.printStackTrace();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    String staffID = jTextField5.getText().trim().toUpperCase();
    String expectedPrefix = getUnitPrefixFromTab("Security"); // tab name

    if (staffID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Staff ID.");
        return;
    }

    if (!staffID.startsWith(expectedPrefix)) {
        JOptionPane.showMessageDialog(this, "Invalid Staff ID for Security unit. Expected prefix: " + expectedPrefix);
        return;
    }

    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

        String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, staffID);
        ResultSet rs = pstmt.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        DefaultTableModel model = new DefaultTableModel();

        for (int i = 1; i <= colCount; i++) {
            model.addColumn(rsmd.getColumnName(i));
        }

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = new Object[colCount];
            for (int i = 1; i <= colCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No attendance found for Staff ID: " + staffID);
        }

        jTable4.setModel(model);

        rs.close();
        pstmt.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        ex.printStackTrace();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
