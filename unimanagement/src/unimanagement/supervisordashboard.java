/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package unimanagement;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static unimanagement.LoginSession.unit;

/**
 *
 * @author HP
 */
public class supervisordashboard extends javax.swing.JFrame {
    private String supervisorUnit;
    private JDateChooser dateChooser;
    private JPanel filterInputPanel;
    private final static String TEXT_FIELD = "TextField";
    private final static String DATE_CHOOSER = "DateChooser";

    public supervisordashboard(String unit) {
        this.supervisorUnit = unit;
        initComponents(); // your NetBeans GUI setup

        setupDynamicFilterInput();
        setupComboBoxListener();
    }
    
    private String getFilterInputValue() {
    String selected = jComboBox2.getSelectedItem().toString();
    if (selected.equalsIgnoreCase("date")) {
        if (dateChooser.getDate() == null) return "";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dateChooser.getDate());
    } else {
        return jTextField1.getText().trim();
    }
}


    private void setupDynamicFilterInput() {
        // Create date chooser
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(jTextField1.getPreferredSize());

        // Create a new panel with CardLayout to hold both input types
        filterInputPanel = new JPanel(new CardLayout());
        filterInputPanel.setBounds(jTextField1.getBounds());
        filterInputPanel.add(jTextField1, TEXT_FIELD);
        filterInputPanel.add(dateChooser, DATE_CHOOSER);

        // Remove jTextField1 from jPanel10 and add the card panel instead
        jPanel10.remove(jTextField1);
        jPanel10.add(filterInputPanel);

        // Show correct panel initially
        switchInputPanel(jComboBox2.getSelectedItem().toString().equals("date") ? DATE_CHOOSER : TEXT_FIELD);

        jPanel10.revalidate();
        jPanel10.repaint();
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


//    private void setupButtonActions() {
//        jButton12.addActionListener(e -> viewLogs());
//        jButton13.addActionListener(e -> filterLogs());
//    }

//    private void viewLogs() {
//        try (Connection conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {
//
//            String query = "SELECT * FROM signin_logs WHERE staffID = ?";
//            PreparedStatement pst = conn.prepareStatement(query);
//            pst.setString(1, supervisorUnit);
//            ResultSet rs = pst.executeQuery();
//            displayResults(rs);
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Error fetching logs: " + ex.getMessage());
//        }
//    }

//    private void filterLogs() {
//        String filterType = jComboBox2.getSelectedItem().toString();
//        String filterValue;
//
//        if ("date".equalsIgnoreCase(filterType)) {
//            Date selectedDate = dateChooser.getDate();
//            if (selectedDate == null) {
//                JOptionPane.showMessageDialog(this, "Please select a date.");
//                return;
//            }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            filterValue = sdf.format(selectedDate);
//        } else {
//            filterValue = jTextField1.getText().trim();
//            if (filterValue.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please enter a staff ID.");
//                return;
//            }
//        }
//
//        try (Connection conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {
//
//            String query = "SELECT * FROM signin_logs WHERE " + filterType + " = ? AND staffID = ?";
//            PreparedStatement pst = conn.prepareStatement(query);
//            pst.setString(1, filterValue);
//            pst.setString(2, supervisorUnit);
//            ResultSet rs = pst.executeQuery();
//            displayResults(rs);
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Error filtering logs: " + ex.getMessage());
//        }
//    }

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

        jTextArea2.setText(sb.toString());
    }



    
    

//    private JTextField staffIdField;
//    private JTextField dateField;  // format: YYYY-MM-DD
//    private JTextArea resultArea;
//    private JButton filterSignInButton;
//    private JButton filterRosterButton;
//
//    public supervisordashboard() {
//        initComponents();
////        setupReportActions();
//
//        JPanel inputPanel = new JPanel(new FlowLayout());
//
//        inputPanel.add(new JLabel("Staff ID:"));
//        staffIdField = new JTextField(10);
//        inputPanel.add(staffIdField);
//
//        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
//        dateField = new JTextField(10);
//        inputPanel.add(dateField);
//
//        filterSignInButton = new JButton("Filter Sign-In Logs");
//        filterRosterButton = new JButton("Filter Roster Logs");
//        inputPanel.add(filterSignInButton);
//        inputPanel.add(filterRosterButton);
//
//        add(inputPanel, BorderLayout.NORTH);
//
//        resultArea = new JTextArea();
//        resultArea.setEditable(false);
//        add(new JScrollPane(resultArea), BorderLayout.CENTER);
//
//        // Button actions
//        filterSignInButton.addActionListener(e -> filterSignInLogs());
//        filterRosterButton.addActionListener(e -> filterRosterLogs());
//    }
//
//    private void filterSignInLogs() {
//    String staffID = staffIdField.getText().trim();
//    String date = dateField.getText().trim();
//
//    
//    try {
//        reportGenerator generator = new reportGenerator();
//        List<Map<String, Object>> logs = generator.getFilteredSignInLogs(staffID, date);
//        if (logs.isEmpty()) {
//            resultArea.setText("No sign-in log entries found for the given filter.\n");
//        } else {
//            StringBuilder sb = new StringBuilder();
//            sb.append("Sign-In Logs:\n");
//            for (Map<String, Object> entry : logs) {
//                sb.append("Staff ID: ").append(entry.get("staffID")).append("\n");
//                sb.append("Sign In: ").append(entry.get("sign_in_time")).append("\n");
//                sb.append("Sign Out: ").append(entry.get("sign_out_time")).append("\n");
//                sb.append("---------------\n");
//            }
//            resultArea.setText(sb.toString());
//        }
//    } catch (Exception ex) {
//        resultArea.setText("Error fetching sign-in logs:\n" + ex.getMessage());
//    }
//}

//private void filterRosterLogs() {
//    String staffID = staffIdField.getText().trim();
//    String date = dateField.getText().trim();
//
//    try {
//        reportGenerator generator = new reportGenerator();
//        List<Map<String, Object>> logs = generator.getFilteredRosterLogs(staffID, date);
//        if (logs.isEmpty()) {
//            resultArea.setText("No roster log entries found for the given filter.\n");
//        } else {
//            StringBuilder sb = new StringBuilder();
//            sb.append("Roster Logs:\n");
//            for (Map<String, Object> entry : logs) {
//                sb.append("Staff ID: ").append(entry.get("staffID")).append("\n");
//                sb.append("Shift Start: ").append(entry.get("shift_start")).append("\n");
//                sb.append("Shift End: ").append(entry.get("shift_end")).append("\n");
//                sb.append("---------------\n");
//            }
//            resultArea.setText(sb.toString());
//        }
//    } catch (Exception ex) {
//        resultArea.setText("Error fetching roster logs:\n" + ex.getMessage());
//    }
//}

    
        public File exportToPDF(String content) {
        File file = new File("report.pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
            document.add(new Paragraph(content, font));
            document.close();
            JOptionPane.showMessageDialog(null, "PDF exported: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "PDF export failed: " + e.getMessage());
        }
        return file;
    }
        
        public static String getSupervisorEmail() {
        String email = null;
        String query = "SELECT email FROM registration WHERE role = 'Supervisor' LIMIT 1";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching Supervisor email: " + e.getMessage());
        }
        return email;
    }  
    
    public void sendReportEmail(String reportText) {
        try {
            String filePath = "report.pdf";
            exportToPDF(reportText);
            File pdfFile = new File(filePath);

            String to = getSupervisorEmail();
            final String from = "udsako@gmail.com";
            final String password = "rzvqeubeobzljlkl";

            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Staff Management Report");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Dear Supervisor,\n\nPlease find the attached Staff Management report.");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(pdfFile);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(pdfFile.getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Report sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to send email: " + e.getMessage());
        }
    }
    
    
    private void displayReport(String period, String unit) {
    try {
        reportGenerator generator = new reportGenerator();
        List<Map<String, Object>> report = generator.generateDetailedReport(period, unit);

        StringBuilder sb = new StringBuilder();
        sb.append("====== ").append(unit.toUpperCase()).append(" - ").append(period.toUpperCase()).append(" REPORT ======\n\n");

        if (report.isEmpty()) {
            sb.append("No entries found for the selected period and unit.");
        } else {
            for (Map<String, Object> entry : report) {
                sb.append("Type: ").append(entry.getOrDefault("type", "")).append("\n");

                switch ((String) entry.get("type")) {
                    case "Roster":
                        sb.append("Staff ID: ").append(entry.get("staffID")).append("\n");
                        sb.append("Shift Start: ").append(entry.get("shift_start")).append("\n");
                        sb.append("Shift End: ").append(entry.get("shift_end")).append("\n");
                        break;

                    case "Unit Shift":
                        sb.append("Shift ID: ").append(entry.get("shift_id")).append("\n");
                        sb.append("Unit: ").append(entry.get("unit")).append("\n");
                        sb.append("Shift Name: ").append(entry.get("shift_name")).append("\n");
                        sb.append("Shift Start: ").append(entry.get("start_time")).append("\n");
                        sb.append("Shift End: ").append(entry.get("end_time")).append("\n");
                        break;

                    case "Shift Attendance":
                        sb.append("Attendance ID: ").append(entry.get("attendance_id")).append("\n");
                        sb.append("Staff ID: ").append(entry.get("staff_id")).append("\n");
                        sb.append("Shift Start: ").append(entry.get("shift_start")).append("\n");
                        sb.append("Shift End: ").append(entry.get("shift_end")).append("\n");
                        sb.append("Login Time: ").append(entry.get("login_time")).append("\n");
                        sb.append("Logout Time: ").append(entry.get("logout_time")).append("\n");
                        sb.append("Attendance Date: ").append(entry.get("attendance_date")).append("\n");

                        Timestamp loginTime = (Timestamp) entry.get("login_time");
                        Timestamp logoutTime = (Timestamp) entry.get("logout_time");
                        Timestamp shiftStart = (Timestamp) entry.get("shift_start");
                        Timestamp shiftEnd = (Timestamp) entry.get("shift_end");

                        if ("Absent".equalsIgnoreCase((String) entry.get("status"))) {
                            sb.append("⚠️ Absence detected.\n");
                        } else {
                            if (loginTime != null && shiftStart != null && loginTime.after(shiftStart)) {
                                sb.append("⚠️ Late Sign-in detected.\n");
                            }
                            if (logoutTime != null && shiftEnd != null && logoutTime.before(shiftEnd)) {
                                sb.append("⚠️ Early Sign-out detected.\n");
                            }
                        }
                        break;

                    case "Shift Swap":
                        sb.append("Request ID: ").append(entry.get("request_id")).append("\n");
                        sb.append("From Staff: ").append(entry.get("requester_id")).append("\n");
                        sb.append("To Staff: ").append(entry.get("requested_shift_id")).append("\n");
                        sb.append("Shift ID: ").append(entry.get("requested_with_id")).append("\n");
                        sb.append("Status: ").append(entry.get("approved")).append("\n");
                        sb.append("Request Date: ").append(entry.get("request_date")).append("\n");
                        break;

                    default:
                        sb.append("Unknown entry type.\n");
                }

                sb.append("------------------------------------------------------------\n");
            }
        }
        jTextArea1.setText(sb.toString());

    } catch (Exception ex) {
        ex.printStackTrace();
        jTextArea1.setText("An error occurred while generating the report.\n" + ex.getMessage());
    }

}
    
    public void fetchAttendanceDataToTable() {
    DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
    model.setRowCount(0); // Clear existing rows

    String unit = LoginSession.getLoggedInUnit(); // Ensure this is set on login

    String query = """
        SELECT a.attendance_id, a.staffID, a.shift_start, a.shift_end,
               a.login_time, a.logout_time, a.attendance_date
        FROM shift_attendance a
        JOIN registration r ON a.staffID = r.staffID
        WHERE r.unit = ?
        ORDER BY a.attendance_date DESC
    """;

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/universitymanagement", "root", "Awele2006");
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, unit);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String attendanceId = rs.getString("attendance_id");
                String staffId = rs.getString("staffID");
                Timestamp shiftStart = rs.getTimestamp("shift_start");
                Timestamp shiftEnd = rs.getTimestamp("shift_end");
                Timestamp loginTime = rs.getTimestamp("login_time");
                Timestamp logoutTime = rs.getTimestamp("logout_time");
                Date attendanceDate = rs.getDate("attendance_date");

                model.addRow(new Object[]{
                    attendanceId,
                    staffId,
                    shiftStart,
                    shiftEnd,
                    loginTime,
                    logoutTime,
                    attendanceDate
                });
            }
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to fetch shift attendance data:\n" + ex.getMessage());
    }
}

//    private void setupReportActions() {
//        jButton6.addActionListener(evt -> displayReport("daily"));
//        jButton7.addActionListener(evt -> displayReport("weekly"));
//        jButton8.addActionListener(evt -> displayReport("monthly"));
//        jButton9.addActionListener(evt -> displayReport("yearly"));
//    }
    
public void loadShiftSwapRequests(String selectedUnit) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear old rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006");

        String sql = """
            SELECT ss.request_id, ss.requester_id, ss.requested_shift_id, ss.requested_with_id, ss.status, ss.request_date
            FROM shift_swap ss
            JOIN registration s ON ss.requester_id = s.staffID
            WHERE s.unit = ?
            ORDER BY ss.request_date DESC
        """;

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, selectedUnit);
        ResultSet rs = pst.executeQuery();

        boolean hasData = false;

        while (rs.next()) {
            hasData = true;

            int requestId = rs.getInt("request_id");
            String requesterId = rs.getString("requester_id");
            int requestedshiftId = rs.getInt("requested_shift_id");
            String requestedWithId = rs.getString("requested_with_id");
            String status = rs.getString("status");
            Timestamp requestDate = rs.getTimestamp("request_date");

            // Add data to JTable
            model.addRow(new Object[]{
                requestId,
                requesterId,
                requestedshiftId,
                requestedWithId,
                status,
                requestDate
            });
        }

        if (!hasData) {
            JOptionPane.showMessageDialog(this,
                "No shift swap has been done in the " + selectedUnit + " unit.",
                "No Data",
                JOptionPane.INFORMATION_MESSAGE);
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error loading shift swap requests: " + ex.getMessage());
    }

}


    /**
     * Creates new form supervisordashboard
     */


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1260, 780));

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "requester_id", "requested_shift_id", "requested_with_id", "status", "request_date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton4.setText("swap confirmation");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 22)); // NOI18N
        jLabel3.setText("Unit");

        jComboBox1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Unit", "Facility", "Cafeteria", "Security", "Horticulture", "Maintenance", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton5.setText("Display");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(75, 75, 75)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(jButton4)))
                .addContainerGap(461, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(156, 156, 156)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Shiftswap", jPanel1);

        jPanel9.setBackground(new java.awt.Color(0, 0, 204));
        jPanel9.setPreferredSize(new java.awt.Dimension(1250, 900));

        jButton6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton6.setText("Daily Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton7.setText("Weekly Report ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton8.setText("Monthly Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton9.setText("Yearly Report");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jButton10.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton10.setText("Export Report");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(27, 27, 27)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(jButton10)))
                .addContainerGap(502, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reports ", jPanel9);

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));

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
        jScrollPane1.setViewportView(jTable2);

        jButton11.setText("Get from Database");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jButton11)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Attendance data", jPanel2);

        jPanel4.setBackground(new java.awt.Color(0, 0, 204));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "staffID", "shift_start", "shift_end"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        jScrollPane3.setViewportView(jTable3);

        jButton2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton2.setText("upload ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton3.setText("download ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jButton2)
                        .addGap(184, 184, 184)
                        .addComponent(jButton3)))
                .addContainerGap(517, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Unit shifts roaster", jPanel4);

        jPanel10.setBackground(new java.awt.Color(0, 0, 204));

        jLabel4.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jLabel4.setText("Filter logs by:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an option", "Date", "Staff ID" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton12.setText("View Logs");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Filter Logs");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane5.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224)
                        .addComponent(jButton12)
                        .addGap(50, 50, 50)
                        .addComponent(jButton13)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Logs ", jPanel10);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\cafeteria\\src\\images\\pau_icon-150x150.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SUPERVISOR DASHBOARD");
        jLabel2.setPreferredSize(new java.awt.Dimension(1400, 800));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(440, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save CSV File");

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    File fileToSave = fileChooser.getSelectedFile();
    if (!fileToSave.getName().toLowerCase().endsWith(".csv")) {
        fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
    }

    try (PrintWriter pw = new PrintWriter(new FileWriter(fileToSave))) {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        int columnCount = model.getColumnCount();

        // Write headers
        for (int i = 0; i < columnCount; i++) {
            pw.print(model.getColumnName(i));
            if (i < columnCount - 1) pw.print(",");
        }
        pw.println();

        // Write rows
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < columnCount; col++) {
                Object value = model.getValueAt(row, col);
                pw.print(value != null ? value.toString() : "");
                if (col < columnCount - 1) pw.print(",");
            }
            pw.println();
        }

        JOptionPane.showMessageDialog(this, "Roster successfully downloaded as CSV!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving CSV: " + e.getMessage());
        e.printStackTrace();
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
try (Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    // Get supervisor's unit
    String supervisorUnit = LoginSession.getLoggedInUnit();
    String unitPrefix = getUnitPrefix(supervisorUnit);

    if (unitPrefix.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Unable to determine unit prefix for: " + supervisorUnit);
        return;
    }

    // Fetch only roster entries for this unit
    String sql = "SELECT staffID, shift_start, shift_end FROM rosters WHERE staffID LIKE ? ORDER BY shift_start DESC";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, unitPrefix + "%");  // e.g., "MAI%"

    ResultSet rs = pstmt.executeQuery();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();

    DefaultTableModel model = new DefaultTableModel();

    // Add column headers
    for (int i = 1; i <= columnCount; i++) {
        model.addColumn(rsmd.getColumnName(i));
    }

    boolean found = false;

    // Fill table rows
    while (rs.next()) {
        found = true;
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            row[i - 1] = rs.getObject(i);
        }
        model.addRow(row);
    }

    jTable3.setModel(model);

    if (!found) {
        JOptionPane.showMessageDialog(this, "No roster entries found for unit: " + supervisorUnit);
    }

    rs.close();
    pstmt.close();

} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error uploading roster: " + ex.getMessage());
}

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        fetchAttendanceDataToTable();            // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        JCheckBox emailCheckBox = new JCheckBox("Send to Supervisor");

        Object[] options = {emailCheckBox};
        int result = JOptionPane.showConfirmDialog(
            null,
            options,
            "Select Export Options",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String reportText = jTextArea1.getText();

            if (emailCheckBox.isSelected()) {
                sendReportEmail(reportText);  // Email PDF
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            String supervisorUnit = LoginSession.getLoggedInUnit();
            displayReport("yearly", supervisorUnit);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate yearly report:\n" + ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            String supervisorUnit = LoginSession.getLoggedInUnit();
            displayReport("monthly", supervisorUnit);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate monthly report:\n" + ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String supervisorUnit = LoginSession.getLoggedInUnit();
            displayReport("weekly", supervisorUnit);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate weekly report:\n" + ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String supervisorUnit = LoginSession.getLoggedInUnit();
            displayReport("daily", supervisorUnit);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate daily report:\n" + ex.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
String selectedUnit = (String) jComboBox1.getSelectedItem();
String loggedInUnit = LoginSession.getLoggedInUnit(); // or use LoginSession.unit

if (selectedUnit == null || selectedUnit.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please select a unit first.");
    return;
}

// ✅ Enforce: Supervisors can only view their own unit
if (!selectedUnit.equalsIgnoreCase(loggedInUnit)) {
    JOptionPane.showMessageDialog(this,
        "Access Denied. You can only view shift swap requests for your own unit: " + loggedInUnit);
    return;
}

loadShiftSwapRequests(selectedUnit);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a shift swap to confirm.");
            return;
        }

        int swapId = (int) jTable1.getValueAt(selectedRow, 0);
        String currentStatus = (String) jTable1.getValueAt(selectedRow, jTable1.getColumn("status").getModelIndex());

        if (!currentStatus.equalsIgnoreCase("Pending")) {
            JOptionPane.showMessageDialog(this, "Only pending swaps can be approved.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to approve this swap?", "Confirm Approval", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {
            con.setAutoCommit(false); // Start transaction

            // 1. Get requester and requested_with from DB
            String fetchSql = "SELECT requester_id, requested_with_id FROM shift_swap WHERE swap_id = ?";
            PreparedStatement fetchPst = con.prepareStatement(fetchSql);
            fetchPst.setInt(1, swapId);
            ResultSet rs = fetchPst.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Swap request not found.");
                return;
            }

            String requesterId = rs.getString("requester_id");
            String requestedWithId = rs.getString("requested_with_id");

            // 2. Get roster entries for both users
            String getRosterSql = "SELECT * FROM rosters WHERE staff_id = ?";
            PreparedStatement rosterPst = con.prepareStatement(getRosterSql);

            rosterPst.setString(1, requesterId);
            ResultSet rs1 = rosterPst.executeQuery();
            Timestamp shiftStart1 = null, shiftEnd1 = null;
            if (rs1.next()) {
                shiftStart1 = rs1.getTimestamp("shift_start");
                shiftEnd1 = rs1.getTimestamp("shift_end");
            }

            rosterPst.setString(1, requestedWithId);
            ResultSet rs2 = rosterPst.executeQuery();
            Timestamp shiftStart2 = null, shiftEnd2 = null;
            if (rs2.next()) {
                shiftStart2 = rs2.getTimestamp("shift_start");
                shiftEnd2 = rs2.getTimestamp("shift_end");
            }

            // 3. Swap the shifts
            if (shiftStart1 != null && shiftStart2 != null) {
                String updateRosterSql = "UPDATE rosters SET shift_start = ?, shift_end = ? WHERE staff_id = ?";
                PreparedStatement updatePst = con.prepareStatement(updateRosterSql);

                // Update requester
                updatePst.setTimestamp(1, shiftStart2);
                updatePst.setTimestamp(2, shiftEnd2);
                updatePst.setString(3, requesterId);
                updatePst.executeUpdate();

                // Update requested_with
                updatePst.setTimestamp(1, shiftStart1);
                updatePst.setTimestamp(2, shiftEnd1);
                updatePst.setString(3, requestedWithId);
                updatePst.executeUpdate();
            }

            // 4. Mark swap request as Approved
            String updateSwapSql = "UPDATE shift_swap SET status = 'Approved' WHERE swap_id = ?";
            PreparedStatement pst = con.prepareStatement(updateSwapSql);
            pst.setInt(1, swapId);
            pst.executeUpdate();

            con.commit(); // Commit all

            JOptionPane.showMessageDialog(this, "Swap approved and roster updated!");

            // Refresh display
            String selectedUnit = (String) jComboBox1.getSelectedItem();
            loadShiftSwapRequests(selectedUnit);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

    String supervisorUnit = LoginSession.getLoggedInUnit(); // e.g., "Maintenance"
    String unitPrefix = getUnitPrefix(supervisorUnit);      // e.g., "MAI"

    if (unitPrefix.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Invalid or unknown supervisor unit.");
        return;
    }

    String selectedFilter = jComboBox2.getSelectedItem().toString().trim().toLowerCase();
    String userInput = getFilterInputValue(); // ← ✅ Correct handling of jTextField1 or dateChooser

    if (userInput.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a value for the selected filter.");
        return;
    }

    PreparedStatement pstmt;
    String sql;

    if (selectedFilter.equalsIgnoreCase("Staff ID")) {
        if (!userInput.startsWith(unitPrefix)) {
            JOptionPane.showMessageDialog(this, "Entered Staff ID does not match your unit prefix (" + unitPrefix + ").");
            return;
        }

        sql = "SELECT * FROM signin_logs WHERE staffID = ? ORDER BY login_time DESC";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userInput);

    } else if (selectedFilter.equalsIgnoreCase("Date")) {
        java.sql.Date selectedDate;
        try {
            selectedDate = java.sql.Date.valueOf(userInput); // yyyy-MM-dd
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format.");
            return;
        }

        sql = "SELECT * FROM signin_logs WHERE signin_date = ? AND staffID LIKE ? ORDER BY login_time DESC";
        pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, selectedDate);
        pstmt.setString(2, unitPrefix + "%");
    } else {
        JOptionPane.showMessageDialog(this, "Unknown filter selected.");
        return;
    }

    ResultSet rs = pstmt.executeQuery();

    StringBuilder logBuilder = new StringBuilder();
    logBuilder.append("=== Sign-In Logs for Unit: ").append(supervisorUnit).append(" ===\n\n");

    boolean found = false;
    while (rs.next()) {
        found = true;
        String staffID = rs.getString("staffID");
        Timestamp loginTime = rs.getTimestamp("login_time");
        Timestamp logoutTime = rs.getTimestamp("logout_time");
        Date signinDate = rs.getDate("signin_date");

        logBuilder.append("Staff ID: ").append(staffID).append("\n")
                  .append("Sign-In Date: ").append(signinDate).append("\n")
                  .append("Login Time: ").append(loginTime).append("\n")
                  .append("Logout Time: ").append(logoutTime != null ? logoutTime : "Still signed in").append("\n")
                  .append("-----------------------------\n");
    }

    if (!found) {
        logBuilder.append("No logs found for the given filter and unit.");
    }

    jTextArea2.setText(logBuilder.toString());

    rs.close();
    pstmt.close();

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error fetching sign-in logs: " + e.getMessage());
}



        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String filterType = jComboBox2.getSelectedItem().toString();
        String filterValue;

        if ("date".equalsIgnoreCase(filterType)) {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a date.");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            filterValue = sdf.format(selectedDate);
        } else {
            filterValue = jTextField1.getText().trim();
            if (filterValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a staff ID.");
                return;
            }
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/universitymanagement", "root", "Awele2006")) {

            String query = "SELECT * FROM signin_logs WHERE " + filterType + " = ? AND staffID = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, filterValue);
            pst.setString(2, supervisorUnit);
            ResultSet rs = pst.executeQuery();
            displayResults(rs);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error filtering logs: " + ex.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(supervisordashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supervisordashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supervisordashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supervisordashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supervisordashboard(unit).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
