/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package unimanagement;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import unimanagement.cafeteria;
import unimanagement.facility;
import unimanagement.horticulture;
import unimanagement.maintenance;
import unimanagement.security;

/**
 *
 * @author HP
 */
public class nonsupervisorsdashboard extends javax.swing.JFrame {
  

    public nonsupervisorsdashboard(String role) {
        initComponents();

//        if (role.equalsIgnoreCase("Director") || role.equalsIgnoreCase("Head of IT")) {
//            setupSubTabs(); // Only for Head of Unit or Director
//        }
    }

//    private void setupSubTabs() {
//        JTabbedPane subTabs = new JTabbedPane();
//
//        // Each subtab uses a restricted version of the dashboard
//        subTabs.addTab("Facility", new nonsupervisorsdashboard("Facility").getContentPanel());
//        subTabs.addTab("Cafeteria", new nonsupervisorsdashboard("Cafeteria").getContentPanel());
//        subTabs.addTab("Horticulture", new nonsupervisorsdashboard("Horticulture").getContentPanel());
//        subTabs.addTab("Security", new nonsupervisorsdashboard("Security").getContentPanel());
//        subTabs.addTab("Maintenance", new nonsupervisorsdashboard("Maintenance").getContentPanel());
//        
//        jPanel1.setLayout(new BorderLayout());
//        jPanel1.removeAll();
//        jPanel1.add(subTabs, BorderLayout.CENTER);
//        jPanel1.revalidate();
//        jPanel1.repaint();
//    }
//
//    public JPanel getContentPanel() {
//        return jPanel1;
//    }
    
    
    
    private String getUnitPrefix(String unitName) {
    switch (unitName.trim().toLowerCase()) {
        case "facility": return "FAC";
        case "cafeteria": return "CAF";
        case "horticulture": return "HOR";
        case "security": return "SEC";
        case "maintenance": return "MAI";
        default: return "";
    }
}


//    private boolean isInternalUse;
//
//    // Main constructor (used from AdminDashboard)
//    public nonsupervisorsdashboard() {
//        this(false); // defaults to full view
//    }
//
//    // Secondary constructor (used for subtabs only)
//    public nonsupervisorsdashboard(boolean isInternalUse) {
//        this.isInternalUse = isInternalUse;
//        initComponents(); // this is the ONE initComponents() method NetBeans generates
//
//        if (!isInternalUse) {
//            setupSubTabs();  // only add tabs if it's the full Unit Dashboard view
//        }
//    }
//
//    private void setupSubTabs() {
//        JTabbedPane subTabs = new JTabbedPane();
//
//        // Use this same dashboard inside each subtab (each will function independently)
//        subTabs.addTab("Security", new nonsupervisorsdashboard(true).getContentPanel());
//        subTabs.addTab("Maintenance", new nonsupervisorsdashboard(true).getContentPanel());
//        subTabs.addTab("Facility", new nonsupervisorsdashboard(true).getContentPanel());
//        subTabs.addTab("Cafeteria", new nonsupervisorsdashboard(true).getContentPanel());
//        subTabs.addTab("Horticulture", new nonsupervisorsdashboard(true).getContentPanel());
//
//        // Display the subtabs inside jPanel1 (your main dashboard panel)
//        jPanel1.setLayout(new BorderLayout());
//        jPanel1.removeAll(); // clear whatever was there before
//        jPanel1.add(subTabs, BorderLayout.CENTER);
//        jPanel1.revalidate();
//        jPanel1.repaint();
//    }
//
//    public JPanel getContentPanel() {
//        return jPanel1; // return the top-level panel (designed in NetBeans)
//    }

// public nonsupervisorsdashboard() {
//        initComponents();  // this already exists in your form
//        setupTabs();       // our custom tab injector
//    }
//
//    private void setupTabs() {
//        JTabbedPane subTabs = new JTabbedPane();
//
//        // Use dummy values or admin-specific values
//        String adminID = "admin_viewer"; // or null
//        boolean isSupervisor = false;
//
//    // Create panel content from frames
//    subTabs.addTab("Security", new security(adminID, "Security", isSupervisor).getContentPanel());
//    subTabs.addTab("Maintenance", new maintenance(adminID, "Maintenance", isSupervisor).getContentPanel());
//    subTabs.addTab("Facility", new facility(adminID, "Facility", isSupervisor).getContentPanel());
//    subTabs.addTab("Cafeteria", new cafeteria(adminID, "Cafeteria", isSupervisor).getContentPanel());
//    subTabs.addTab("Horticulture", new horticulture(adminID, "Horticulture", isSupervisor).getContentPanel());
//
//        // Inject into existing jPanel1 (must be declared in your form)
//        jPanel1.setLayout(new BorderLayout());
//        jPanel1.add(subTabs, BorderLayout.CENTER);
//    }
//    
//    public JPanel getContentPanel() {
//    return jPanel1; // Replace with the actual main panel instance used in the JFrame
//}



    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1260, 780));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\cafeteria\\src\\images\\pau_icon-150x150.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NON-SUPERVISOR STAFF DASHBOARD");
        jLabel2.setPreferredSize(new java.awt.Dimension(1400, 800));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1250, 800));

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 900));

        jButton1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton1.setText("Export Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 22)); // NOI18N
        jLabel3.setText("STAFF ID");

        jTextField1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jButton2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton2.setText("Display Record");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(699, 699, 699)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("facility", jPanel1);

        jPanel9.setBackground(new java.awt.Color(0, 0, 204));
        jPanel9.setPreferredSize(new java.awt.Dimension(1250, 900));

        jButton3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton3.setText("Export Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 22)); // NOI18N
        jLabel4.setText("STAFF ID");

        jTextField2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jButton4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton4.setText("Display Record");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(699, 699, 699)
                        .addComponent(jButton3)
                        .addContainerGap(93, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton4)
                        .addGap(330, 330, 330)
                        .addComponent(jButton3))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(410, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("cafeteria", jPanel5);

        jPanel10.setBackground(new java.awt.Color(0, 0, 204));
        jPanel10.setPreferredSize(new java.awt.Dimension(1250, 900));

        jButton5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton5.setText("Export Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 22)); // NOI18N
        jLabel5.setText("STAFF ID");

        jTextField3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jButton6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton6.setText("Display Record");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(699, 699, 699)
                        .addComponent(jButton5)
                        .addContainerGap(93, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton6))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(132, 132, 132)
                .addComponent(jButton5)
                .addContainerGap(404, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("horticulture", jPanel6);

        jPanel11.setBackground(new java.awt.Color(0, 0, 204));
        jPanel11.setPreferredSize(new java.awt.Dimension(1250, 900));

        jButton7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton7.setText("Export Report");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 22)); // NOI18N
        jLabel6.setText("STAFF ID");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        jButton11.setText("Display record");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(915, 915, 915)
                                .addComponent(jButton7))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(93, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton11)))
                .addGap(99, 99, 99)
                .addComponent(jButton7)
                .addContainerGap(404, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("security", jPanel7);

        jPanel12.setBackground(new java.awt.Color(0, 0, 204));
        jPanel12.setPreferredSize(new java.awt.Dimension(1250, 900));

        jButton9.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton9.setText("Export Report");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 22)); // NOI18N
        jLabel7.setText("STAFF ID");

        jTextField5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N

        jButton10.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton10.setText("Display Record");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

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
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(699, 699, 699)
                        .addComponent(jButton9))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(236, 236, 236)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton10))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(108, 108, 108)
                .addComponent(jButton9)
                .addContainerGap(410, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("maintenance", jPanel8);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(440, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private String currentStaffID;
    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
String staffID = jTextField5.getText().trim().toUpperCase(); // force uppercase

if (staffID.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter Staff ID.");
    return;
}

// ✅ STEP 1: Check if staffID prefix matches unit
String expectedPrefix = "MAI"; // 🔁 Change per unit: SEC, MAI, FAC, HOR
if (!staffID.startsWith(expectedPrefix)) {
    JOptionPane.showMessageDialog(this, "Invalid Staff ID for this unit. Please enter a valid " + expectedPrefix + " Staff ID.");
    return;
}

try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    // ✅ STEP 2: Confirm staff exists in signin_log
    String verifySql = "SELECT * FROM signin_logs WHERE staffID = ?";
    PreparedStatement verifyPst = conn.prepareStatement(verifySql);
    verifyPst.setString(1, staffID);
    ResultSet verifyRs = verifyPst.executeQuery();

    if (!verifyRs.next()) {
        JOptionPane.showMessageDialog(this, "No record found for Staff ID: " + staffID + " in this unit.");
        return;
    }

    // ✅ STEP 3: Fetch records from shift_attendance
    String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, staffID);

    ResultSet rs = pstmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    DefaultTableModel model = new DefaultTableModel();

    for (int i = 1; i <= columnCount; i++) {
        model.addColumn(rsmd.getColumnName(i));
    }

    boolean found = false;
    while (rs.next()) {
        found = true;
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            row[i - 1] = rs.getObject(i);
        }
        model.addRow(row);
    }

    if (!found) {
        JOptionPane.showMessageDialog(this, "No shift attendance records found for Staff ID: " + staffID);
    }

    jTable5.setModel(model);

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    ex.printStackTrace();
}


            // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     String staffID = jTextField2.getText().trim().toUpperCase(); // force uppercase

if (staffID.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter Staff ID.");
    return;
}

// ✅ STEP 1: Check if staffID prefix matches unit
String expectedPrefix = "CAF"; // 🔁 Change per unit: SEC, MAI, FAC, HOR
if (!staffID.startsWith(expectedPrefix)) {
    JOptionPane.showMessageDialog(this, "Invalid Staff ID for this unit. Please enter a valid " + expectedPrefix + " Staff ID.");
    return;
}

try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    // ✅ STEP 2: Confirm staff exists in signin_log
    String verifySql = "SELECT * FROM signin_logs WHERE staffID = ?";
    PreparedStatement verifyPst = conn.prepareStatement(verifySql);
    verifyPst.setString(1, staffID);
    ResultSet verifyRs = verifyPst.executeQuery();

    if (!verifyRs.next()) {
        JOptionPane.showMessageDialog(this, "No record found for Staff ID: " + staffID + " in this unit.");
        return;
    }

    // ✅ STEP 3: Fetch records from shift_attendance
    String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, staffID);

    ResultSet rs = pstmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    DefaultTableModel model = new DefaultTableModel();

    for (int i = 1; i <= columnCount; i++) {
        model.addColumn(rsmd.getColumnName(i));
    }

    boolean found = false;
    while (rs.next()) {
        found = true;
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            row[i - 1] = rs.getObject(i);
        }
        model.addRow(row);
    }

    if (!found) {
        JOptionPane.showMessageDialog(this, "No shift attendance records found for Staff ID: " + staffID);
    }

    jTable2.setModel(model);

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    ex.printStackTrace();
}

      // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String staffID = jTextField1.getText().trim().toUpperCase(); // force uppercase

if (staffID.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter Staff ID.");
    return;
}

// ✅ STEP 1: Check if staffID prefix matches unit
String expectedPrefix = "FAC"; // 🔁 Change per unit: SEC, MAI, FAC, HOR
if (!staffID.startsWith(expectedPrefix)) {
    JOptionPane.showMessageDialog(this, "Invalid Staff ID for this unit. Please enter a valid " + expectedPrefix + " Staff ID.");
    return;
}

try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    // ✅ STEP 2: Confirm staff exists in signin_log
    String verifySql = "SELECT * FROM signin_logs WHERE staffID = ?";
    PreparedStatement verifyPst = conn.prepareStatement(verifySql);
    verifyPst.setString(1, staffID);
    ResultSet verifyRs = verifyPst.executeQuery();

    if (!verifyRs.next()) {
        JOptionPane.showMessageDialog(this, "No record found for Staff ID: " + staffID + " in this unit.");
        return;
    }

    // ✅ STEP 3: Fetch records from shift_attendance
    String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, staffID);

    ResultSet rs = pstmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    DefaultTableModel model = new DefaultTableModel();

    for (int i = 1; i <= columnCount; i++) {
        model.addColumn(rsmd.getColumnName(i));
    }

    boolean found = false;
    while (rs.next()) {
        found = true;
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            row[i - 1] = rs.getObject(i);
        }
        model.addRow(row);
    }

    if (!found) {
        JOptionPane.showMessageDialog(this, "No shift attendance records found for Staff ID: " + staffID);
    }

    jTable1.setModel(model);

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    ex.printStackTrace();
}

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      String staffID = jTextField3.getText().trim().toUpperCase(); // force uppercase

if (staffID.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter Staff ID.");
    return;
}

// ✅ STEP 1: Check if staffID prefix matches unit
String expectedPrefix = "HOR"; // 🔁 Change per unit: SEC, MAI, FAC, HOR
if (!staffID.startsWith(expectedPrefix)) {
    JOptionPane.showMessageDialog(this, "Invalid Staff ID for this unit. Please enter a valid " + expectedPrefix + " Staff ID.");
    return;
}

try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    // ✅ STEP 2: Confirm staff exists in signin_log
    String verifySql = "SELECT * FROM signin_logs WHERE staffID = ?";
    PreparedStatement verifyPst = conn.prepareStatement(verifySql);
    verifyPst.setString(1, staffID);
    ResultSet verifyRs = verifyPst.executeQuery();

    if (!verifyRs.next()) {
        JOptionPane.showMessageDialog(this, "No record found for Staff ID: " + staffID + " in this unit.");
        return;
    }

    // ✅ STEP 3: Fetch records from shift_attendance
    String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, staffID);

    ResultSet rs = pstmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    DefaultTableModel model = new DefaultTableModel();

    for (int i = 1; i <= columnCount; i++) {
        model.addColumn(rsmd.getColumnName(i));
    }

    boolean found = false;
    while (rs.next()) {
        found = true;
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            row[i - 1] = rs.getObject(i);
        }
        model.addRow(row);
    }

    if (!found) {
        JOptionPane.showMessageDialog(this, "No shift attendance records found for Staff ID: " + staffID);
    }

    jTable3.setModel(model);

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    ex.printStackTrace();
}

       // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    String staffID = jTextField6.getText().trim().toUpperCase(); // force uppercase

if (staffID.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter Staff ID.");
    return;
}

// ✅ STEP 1: Check if staffID prefix matches unit
String expectedPrefix = "SEC"; // 🔁 Change per unit: SEC, MAI, FAC, HOR
if (!staffID.startsWith(expectedPrefix)) {
    JOptionPane.showMessageDialog(this, "Invalid Staff ID for this unit. Please enter a valid " + expectedPrefix + " Staff ID.");
    return;
}

try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    // ✅ STEP 2: Confirm staff exists in signin_log
    String verifySql = "SELECT * FROM signin_logs WHERE staffID = ?";
    PreparedStatement verifyPst = conn.prepareStatement(verifySql);
    verifyPst.setString(1, staffID);
    ResultSet verifyRs = verifyPst.executeQuery();

    if (!verifyRs.next()) {
        JOptionPane.showMessageDialog(this, "No record found for Staff ID: " + staffID + " in this unit.");
        return;
    }

    // ✅ STEP 3: Fetch records from shift_attendance
    String sql = "SELECT * FROM shift_attendance WHERE staffID = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, staffID);

    ResultSet rs = pstmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    DefaultTableModel model = new DefaultTableModel();

    for (int i = 1; i <= columnCount; i++) {
        model.addColumn(rsmd.getColumnName(i));
    }

    boolean found = false;
    while (rs.next()) {
        found = true;
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            row[i - 1] = rs.getObject(i);
        }
        model.addRow(row);
    }

    if (!found) {
        JOptionPane.showMessageDialog(this, "No shift attendance records found for Staff ID: " + staffID);
    }

    jTable4.setModel(model);

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    ex.printStackTrace();
}

            // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(nonsupervisorsdashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nonsupervisorsdashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nonsupervisorsdashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nonsupervisorsdashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String role = null;
                new nonsupervisorsdashboard(role).setVisible(true);
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
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
