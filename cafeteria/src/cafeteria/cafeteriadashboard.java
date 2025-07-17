/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafeteria;

import javax.swing.*;
import java.awt.*;
import cafeteria.reportGenerator;
import cafeteria.ReportData;
import cafeteria.kitchen;
import cafeteria.orders;
import cafeteria.serving;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author HP
 */
public class cafeteriadashboard extends javax.swing.JFrame {

    private JTable ordersTable, kitchenTable, servingTable;
    private Timer refreshTimer;

    public cafeteriadashboard() {
        initComponents();
        setupTabs();
        setupReportActions();
        setupAutoRefresh(); // Auto-refresh table data
    }

    // ---------------- EMAIL + PDF ----------------

    public static String getHeadOfUnitEmail() {
        String email = null;
        String query = "SELECT email FROM registration WHERE role = 'Head of Unit' LIMIT 1";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006");
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching Head of Unit email: " + e.getMessage());
        }
        return email;
    }

    public void sendReportEmail(String reportText) {
        try {
            String filePath = "report.pdf";
            exportToPDF(reportText);
            File pdfFile = new File(filePath);

            String to = getHeadOfUnitEmail();
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
            message.setSubject("Cafeteria Report");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Dear Head of Unit,\n\nPlease find the attached cafeteria report.");

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

    // ---------------- REPORT GENERATION ----------------

    private void displayReport(String period) {
        try {
            reportGenerator generator = new reportGenerator();
            List<Map<String, Object>> report = generator.generateDetailedReport(period);

            StringBuilder sb = new StringBuilder();
            sb.append("====== ").append(period.toUpperCase()).append(" REPORT ======\n\n");

            if (report.isEmpty()) {
                sb.append("No entries found for the selected period.");
            } else {
                for (Map<String, Object> entry : report) {
                    sb.append("Type: ").append(entry.getOrDefault("type", "")).append("\n");

                    switch ((String) entry.get("type")) {
                        case "Customer Order":
                            sb.append("Customer: ").append(entry.get("customer_name")).append("\n");
                            sb.append("Product: ").append(entry.get("product_name")).append("\n");
                            sb.append("Quantity: ").append(entry.get("quantity")).append("\n");
                            sb.append("Unit Price: ₦").append(entry.get("unit_price")).append("\n");
                            sb.append("Total Price: ₦").append(entry.get("total_price")).append("\n");
                            sb.append("Payment Method: ").append(entry.get("payment_method")).append("\n");
                            sb.append("Payment Confirmed: ").append(entry.get("payment_confirmed")).append("\n");
                            sb.append("Status: ").append(entry.get("order_status")).append("\n");
                            sb.append("Order Time: ").append(entry.get("order_time")).append("\n");
                            sb.append("Order ID: ").append(entry.get("order_id")).append("\n");
                            break;

                        case "Kitchen Update":
                            sb.append("Item Name: ").append(entry.get("item_name")).append("\n");
                            sb.append("Available Qty: ").append(entry.get("quantity_available")).append("\n");
                            sb.append("Unit Price: ₦").append(entry.get("unit_price")).append("\n");
                            break;

                        case "Served Order":
                            sb.append("Order ID: ").append(entry.get("order_id")).append("\n");
                            sb.append("Customer: ").append(entry.get("customer_name")).append("\n");
                            sb.append("Product: ").append(entry.get("product_name")).append("\n");
                            sb.append("Quantity: ").append(entry.get("quantity")).append("\n");
                            sb.append("Unit Price: ₦").append(entry.get("unit_price")).append("\n");
                            sb.append("Total Price: ₦").append(entry.get("total_price")).append("\n");
                            sb.append("Served Time: ").append(entry.get("served_time")).append("\n");
                            break;

                        default:
                            sb.append("Unknown entry type.\n");
                    }

                    sb.append("------------------------------------------------------------\n");
                }

                sb.append("\n\n====== TOP ORDERED ITEMS ======\n");
                List<Map<String, Object>> topOrders = generator.getMostFrequentOrders();
                for (Map<String, Object> item : topOrders) {
                    sb.append("Product: ").append(item.get("product_name"))
                      .append(" - Ordered: ").append(item.get("order_count")).append(" times\n");
                }
            }

            jTextArea1.setText(sb.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            jTextArea1.setText("An error occurred while generating the report.\n" + ex.getMessage());
        }
    }

    private void setupReportActions() {
        jButton5.addActionListener(evt -> displayReport("daily"));
        jButton6.addActionListener(evt -> displayReport("weekly"));
        jButton7.addActionListener(evt -> displayReport("monthly"));
        jButton8.addActionListener(evt -> displayReport("yearly"));
    }

    // ---------------- TAB SETUP ----------------

    private void setupTabs() {
        kitchen kitchenFrame = new kitchen();
        orders ordersFrame = new orders();
        serving servingFrame = new serving();

        kitchenFrame.setReadOnlyMode(true);
        ordersFrame.setReadOnlyMode(true);
        servingFrame.setReadOnlyMode(true);

        // Get JTable references from the frames
        this.kitchenTable = kitchenFrame.getTable();
        this.ordersTable = ordersFrame.getTable();
        this.servingTable = servingFrame.getTable();

        jTabbedPane1.addTab("Kitchen", kitchenFrame.getMainPanel());
        jTabbedPane1.addTab("Orders", ordersFrame.getMainPanel());
        jTabbedPane1.addTab("Serving", servingFrame.getMainPanel());
    }

    // ---------------- AUTO-REFRESH ----------------

    private void setupAutoRefresh() {
        refreshTimer = new Timer(5000, evt -> {
            loadOrdersTable();
            loadKitchenTable();
            loadServingTable();
        });
        refreshTimer.start();
    }

    private void loadOrdersTable() {
        if (ordersTable == null) {
            System.err.println("ordersTable not initialized!");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006")) {
            String sql = "SELECT customer_name, product_name, quantity, unit_price, total_price, payment_method, payment_confirmed, order_status FROM customer_orders ORDER BY order_time DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel(new String[]{"Customer", "Product", "Qty", "Unit Price", "Total", "Payment Method", "Confirmed", "Status"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("customer_name"),
                        rs.getString("product_name"),
                        rs.getString("quantity"),
                        rs.getString("unit_price"),
                        rs.getString("total_price"),
                        rs.getString("payment_method"),
                        rs.getString("payment_confirmed"),
                        rs.getString("order_status")
                });
            }
            ordersTable.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadKitchenTable() {
        if (kitchenTable == null) {
            System.err.println("kitchenTable not initialized!");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006")) {
            String sql = "SELECT item_name, quantity_available, unit_price FROM food_inventory";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel(new String[]{"Item", "Available Qty", "Unit Price"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("item_name"),
                        rs.getInt("quantity_available"),
                        rs.getDouble("unit_price")
                });
            }
            kitchenTable.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadServingTable() {
        if (servingTable == null) {
            System.err.println("servingTable not initialized!");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006")) {
            String sql = "SELECT customer_name, product_name, quantity, order_status FROM customer_orders WHERE order_status = 'Served' ORDER BY order_time DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel(new String[]{"Customer", "Product", "Qty", "Status"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("customer_name"),
                        rs.getString("product_name"),
                        rs.getString("quantity"),
                        rs.getString("order_status")
                });
            }
            servingTable.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    

    

    
    
        
//        public static String getHeadOfUnitEmail() {
//        String email = null;
//        String query = "SELECT email FROM registration WHERE role = 'Head of Unit' LIMIT 1";
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        
//        try {
//            conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/cafeteria", "root", "Awele2006");
//
//            stmt = conn.prepareStatement(query);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                email = rs.getString("email");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error fetching Head of Unit email: " + e.getMessage());
//        } finally {
//            // Close resources safely
//            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
//            try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
//            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
//        }
//
//        return email;
//    }
//        
//public void sendReportEmail(String reportText) {
//    try {
//        String filePath = "report.pdf";
//        exportToPDF(reportText);  // This should already be defined
//
//        File pdfFile = new File(filePath);
//
//        // Example: Get Head of Unit's email (replace with actual DB query)
//        String to = getHeadOfUnitEmail();  // implement this to fetch email
//        final String from = "udsako@gmail.com"; // your email
//        final String password = "rzvqeubeobzljlkl";
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("udsako@gmail.com", "rzvqeubeobzljlkl");
//            }
//        });
//
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(from));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//        message.setSubject("Cafeteria Report");
//
//        MimeBodyPart textPart = new MimeBodyPart();
//        textPart.setText("Dear Head of Unit,\n\nPlease find the attached cafeteria report.");
//
//        MimeBodyPart attachmentPart = new MimeBodyPart();
//        DataSource source = new FileDataSource(pdfFile);
//        attachmentPart.setDataHandler(new DataHandler(source));
//        attachmentPart.setFileName(pdfFile.getName());
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(textPart);
//        multipart.addBodyPart(attachmentPart);
//
//        message.setContent(multipart);
//        Transport.send(message);
//
//        JOptionPane.showMessageDialog(null, "Report sent successfully!");
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(null, "Failed to send email: " + e.getMessage());
//    }
//}
//
//    
//    
//    public File exportToPDF(String content) {
//    File file = new File("report.pdf");  // Or use JFileChooser if you want custom location
//    try {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(file));
//        document.open();
//
//        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
//        document.add(new Paragraph(content, font));
//
//        document.close();
//        JOptionPane.showMessageDialog(null, "PDF exported: " + file.getAbsolutePath());
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(null, "PDF export failed: " + e.getMessage());
//    }
//    return file;
//}
//    
//
//    public cafeteriadashboard() {
//    initComponents();
//    setupTabs();
//    setupReportActions();
//    }
//    
//    
//
//    String[] columnNames = {"Customer", "Product Name", "Quantity", "Unit Price", "Total Price", "Payment Method", "Payment Confirmed", "Payment Confirmation", "Order Status", "Order time", "Order Id"};
//        DefaultTableModel sharedModel = new DefaultTableModel(columnNames, 0);
//    // Create instances of your modules
//    private void setupTabs() {
//        
//        
//        // Embed your custom JPanels or JFrames inside the tabbed pane
//        kitchen kitchenFrame = new kitchen();
//        orders ordersFrame = new orders();
//        serving servingFrame = new serving();
//        
//        kitchenFrame.setReadOnlyMode(true);
//        ordersFrame.setReadOnlyMode(true);
//        servingFrame.setReadOnlyMode(true);
//
//        // Replace tab contents if already added in NetBeans
//jTabbedPane1.addTab("Kitchen", kitchenFrame.getMainPanel());
//jTabbedPane1.addTab("Orders", ordersFrame.getMainPanel());
//jTabbedPane1.addTab("Serving", servingFrame.getMainPanel());
//        // The 4th tab (index 3) should already contain the ReportPanel you designed
//       // Update Reports tab title if it exists
//
//    }
//
//    private void setupReportActions() {
//        jButton5.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                displayReport("daily");
//            }
//        });
//
//        jButton6.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                displayReport("weekly");
//            }
//        });
//
//        jButton7.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                displayReport("monthly");
//            }
//        });
//
//        jButton8.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                displayReport("yearly");
//            }
//        });
//    }
//  
//        
//    
//    private void displayReport(String period) {
//    try {
//        reportGenerator generator = new reportGenerator();
//        List<Map<String, Object>> report = generator.generateDetailedReport(period);
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("====== ").append(period.toUpperCase()).append(" REPORT ======\n\n");
//
//        if (report.isEmpty()) {
//            sb.append("No entries found for the selected period.");
//        } else {
//            for (Map<String, Object> entry : report) {
//                sb.append("Type: ").append(entry.getOrDefault("type", "")).append("\n");
//
//                switch ((String) entry.get("type")) {
//                    case "Customer Order":
//                        sb.append("Customer: ").append(entry.get("customer_name")).append("\n");
//                        sb.append("Product: ").append(entry.get("product_name")).append("\n");
//                        sb.append("Quantity: ").append(entry.get("quantity")).append("\n");
//                        sb.append("Unit Price: ₦").append(entry.get("unit_price")).append("\n");
//                        sb.append("Total Price: ₦").append(entry.get("total_price")).append("\n");
//                        sb.append("Payment Method: ").append(entry.get("payment_method")).append("\n");
//                        sb.append("Payment Confirmed: ").append(entry.get("payment_confirmed")).append("\n");
//                        sb.append("Status: ").append(entry.get("order_status")).append("\n");
//                        sb.append("Order Time: ").append(entry.get("order_time")).append("\n");
//                        sb.append("Order ID: ").append(entry.get("order_id")).append("\n");
//                        break;
//
//                    case "Kitchen Update":
//                        sb.append("Item Name: ").append(entry.get("item_name")).append("\n");
//                        sb.append("Available Qty: ").append(entry.get("quantity_available")).append("\n");
//                        sb.append("Unit Price: ₦").append(entry.get("unit_price")).append("\n");
//                        break;
//
//                    case "Served Order":
//                        sb.append("Order ID: ").append(entry.get("order_id")).append("\n");
//                        sb.append("Customer: ").append(entry.get("customer_name")).append("\n");
//                        sb.append("Product: ").append(entry.get("product_name")).append("\n");
//                        sb.append("Quantity: ").append(entry.get("quantity")).append("\n");
//                        sb.append("Unit Price: ₦").append(entry.get("unit_price")).append("\n");
//                        sb.append("Total Price: ₦").append(entry.get("total_price")).append("\n");
//                        sb.append("Served Time: ").append(entry.get("served_time")).append("\n");
//                        break;
//
//                    default:
//                        sb.append("Unknown entry type.\n");
//                }
//
//                sb.append("------------------------------------------------------------\n");
//            }
//            sb.append("\n\n====== TOP ORDERED ITEMS ======\n");
//
//    List<Map<String, Object>> topOrders = generator.getMostFrequentOrders();
//    for (Map<String, Object> item : topOrders) {
//    sb.append("Product: ").append(item.get("product_name"))
//      .append(" - Ordered: ").append(item.get("order_count")).append(" times\n");
//}
//
//        }
//
//        jTextArea1.setText(sb.toString());
//
//    } catch (Exception ex) {
//        ex.printStackTrace();
//        jTextArea1.setText("An error occurred while generating the report.\n" + ex.getMessage());
//    }

    
    // Optionally add user activity panel later
//    JPanel userActivityPanel = new JPanel();
//    userActivityPanel.add(new JLabel("User Activity Logs will appear here."));
//    jTabbedPane1.addTab("User Activity", userActivityPanel);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1260, 780));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1250, 800));

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 900));

        jButton5.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton5.setText("Daily Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton6.setText("Weekly Report ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton7.setText("Monthly Report");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton8.setText("Yearly Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(new java.awt.Font("Microsoft Himalaya", 1, 18)); // NOI18N
        jButton1.setText("Export Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(27, 27, 27)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(jButton1)))
                .addContainerGap(502, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reports ", jPanel1);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\cafeteria\\src\\images\\pau_icon-150x150.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Microsoft Himalaya", 1, 45)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CAFETERIA REAL-TIME DASHBOARD");
        jLabel2.setPreferredSize(new java.awt.Dimension(1400, 800));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(623, 623, 623))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JCheckBox downloadCheckBox = new JCheckBox("Download as PDF");
        JCheckBox emailCheckBox = new JCheckBox("Send to Head of Unit Email");

        Object[] options = {downloadCheckBox, emailCheckBox};
        int result = JOptionPane.showConfirmDialog(
            null,
            options,
            "Select Export Options",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String reportText = jTextArea1.getText();

            if (downloadCheckBox.isSelected()) {
                exportToPDF(reportText);  // Save as PDF
            }

            if (emailCheckBox.isSelected()) {
                sendReportEmail(reportText);  // Email PDF
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            reportGenerator report = new reportGenerator();
            List<Map<String, Object>> data = report.generateDetailedReport("yearly");

            // Optional: print or render the report
            System.out.println("Report contains " + data.size() + " entries.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate yearly report:\n" + ex.getMessage());
        }   // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            reportGenerator report = new reportGenerator();
            List<Map<String, Object>> data = report.generateDetailedReport("monthly");

            // Optional: print or render the report
            System.out.println("Report contains " + data.size() + " entries.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate weekly report:\n" + ex.getMessage());
        }  // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            reportGenerator report = new reportGenerator();
            List<Map<String, Object>> data = report.generateDetailedReport("weekly");

            // Optional: print or render the report
            System.out.println("Report contains " + data.size() + " entries.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate monthly report:\n" + ex.getMessage());
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            reportGenerator report = new reportGenerator();
            List<Map<String, Object>> data = report.generateDetailedReport("daily");

            // Optional: print or render the report
            System.out.println("Report contains " + data.size() + " entries.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate daily report:\n" + ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(cafeteriadashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cafeteriadashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cafeteriadashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cafeteriadashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cafeteriadashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
