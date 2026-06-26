package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class rentalParent extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color WHITE = Color.WHITE;
    private static final Color ROW_ALT = new Color(240, 244, 255);
    private static final Color ROW_BORDER = new Color(210, 218, 240);
    private static final Color BTN_ACTIVE = new Color(10, 25, 70);
    private static final Color BTN_INACTIVE = new Color(180, 190, 220);
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private JPanel recordsPanel;
    private JButton btnAll;
    private JButton btnCurrent;
    private JButton btnFuture;
    private JButton btnPast;
    private String activeFilter = "ALL";

    public rentalParent() {
        setTitle("3G Wheels - Rental Records");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 780, 660);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel headerBanner = new JPanel(new BorderLayout());
        headerBanner.setBackground(DARK_BLUE);
        headerBanner.setPreferredSize(new Dimension(780, 90));
        contentPane.add(headerBanner, BorderLayout.NORTH);

        JLabel companyName = new JLabel("🚘 3G WHEELS");
        companyName.setForeground(WHITE);
        companyName.setFont(new Font("Dialog", Font.BOLD, 22));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        headerBanner.add(companyName, BorderLayout.CENTER);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);
        contentPane.add(centerArea, BorderLayout.CENTER);

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(WHITE);
        toolbar.setBorder(new EmptyBorder(8, 14, 4, 14));
        toolbar.setPreferredSize(new Dimension(780, 42));

        JLabel pageTitle = new JLabel("RENTAL RECORDS");
        pageTitle.setForeground(DARK_BLUE);
        pageTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        toolbar.add(pageTitle, BorderLayout.WEST);

        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        filterBar.setBackground(WHITE);
        filterBar.setBorder(new EmptyBorder(0, 14, 6, 14));

        btnAll = new JButton("ALL");
        btnAll.setFont(new Font("Dialog", Font.BOLD, 11));
        btnAll.setBackground(BTN_ACTIVE);
        btnAll.setForeground(WHITE);
        btnAll.setOpaque(true);
        btnAll.setBorderPainted(false);
        btnAll.setFocusPainted(false);
        btnAll.setPreferredSize(new Dimension(82, 26));
        btnAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAll.addActionListener(e -> {
            activeFilter = "ALL";
            btnAll.setBackground(BTN_ACTIVE);
            btnCurrent.setBackground(BTN_INACTIVE);
            btnFuture.setBackground(BTN_INACTIVE);
            btnPast.setBackground(BTN_INACTIVE);
            refresh();
        });
        filterBar.add(btnAll);

        btnCurrent = new JButton("CURRENT");
        btnCurrent.setFont(new Font("Dialog", Font.BOLD, 11));
        btnCurrent.setBackground(BTN_INACTIVE);
        btnCurrent.setForeground(WHITE);
        btnCurrent.setOpaque(true);
        btnCurrent.setBorderPainted(false);
        btnCurrent.setFocusPainted(false);
        btnCurrent.setPreferredSize(new Dimension(110, 26));
        btnCurrent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCurrent.addActionListener(e -> {
            activeFilter = "CURRENT";
            btnCurrent.setBackground(BTN_ACTIVE);
            btnAll.setBackground(BTN_INACTIVE);
            btnFuture.setBackground(BTN_INACTIVE);
            btnPast.setBackground(BTN_INACTIVE);
            refresh();
        });
        filterBar.add(btnCurrent);

        btnFuture = new JButton("FUTURE");
        btnFuture.setFont(new Font("Dialog", Font.BOLD, 11));
        btnFuture.setBackground(BTN_INACTIVE);
        btnFuture.setForeground(WHITE);
        btnFuture.setOpaque(true);
        btnFuture.setBorderPainted(false);
        btnFuture.setFocusPainted(false);
        btnFuture.setPreferredSize(new Dimension(82, 26));
        btnFuture.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnFuture.addActionListener(e -> {
            activeFilter = "FUTURE";
            btnFuture.setBackground(BTN_ACTIVE);
            btnAll.setBackground(BTN_INACTIVE);
            btnCurrent.setBackground(BTN_INACTIVE);
            btnPast.setBackground(BTN_INACTIVE);
            refresh();
        });
        filterBar.add(btnFuture);

        btnPast = new JButton("PAST");
        btnPast.setFont(new Font("Dialog", Font.BOLD, 11));
        btnPast.setBackground(BTN_INACTIVE);
        btnPast.setForeground(WHITE);
        btnPast.setOpaque(true);
        btnPast.setBorderPainted(false);
        btnPast.setFocusPainted(false);
        btnPast.setPreferredSize(new Dimension(82, 26));
        btnPast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPast.addActionListener(e -> {
            activeFilter = "PAST";
            btnPast.setBackground(BTN_ACTIVE);
            btnAll.setBackground(BTN_INACTIVE);
            btnCurrent.setBackground(BTN_INACTIVE);
            btnFuture.setBackground(BTN_INACTIVE);
            refresh();
        });
        filterBar.add(btnPast);

        JButton btnSearch = new JButton("SEARCH");
        btnSearch.setFont(new Font("Dialog", Font.BOLD, 11));
        btnSearch.setBackground(BTN_INACTIVE);
        btnSearch.setForeground(WHITE);
        btnSearch.setOpaque(true);
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        btnSearch.setPreferredSize(new Dimension(90, 26));
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.addActionListener(e -> {
            searchRental searchFrame = new searchRental(this);
            searchFrame.setLocationRelativeTo(this);
            searchFrame.setVisible(true);
        });
        filterBar.add(btnSearch);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(WHITE);
        northPanel.add(toolbar, BorderLayout.NORTH);
        northPanel.add(filterBar, BorderLayout.SOUTH);
        centerArea.add(northPanel, BorderLayout.NORTH);

        recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.Y_AXIS));
        recordsPanel.setBackground(WHITE);

        JScrollPane scrollPane = new JScrollPane(recordsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerArea.add(scrollPane, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        footer.setBackground(new Color(235, 238, 248));

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Dialog", Font.BOLD, 11));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            dispose();
            adminDashboard dashboard = new adminDashboard();
            dashboard.setVisible(true);
        });
        footer.add(backButton);
        contentPane.add(footer, BorderLayout.SOUTH);

        loadRecords(recordsPanel);
    }

    public void refresh() {
        loadRecords(recordsPanel);
    }

    private void loadRecords(JPanel panel) {
        panel.removeAll();

        JPanel headerRow = new JPanel(null);
        headerRow.setBackground(new Color(30, 60, 140));
        headerRow.setPreferredSize(new Dimension(680, 32));
        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        headerRow.add(makeHeaderLabel("ID", 10, 40));
        headerRow.add(makeHeaderLabel("USERNAME", 55, 120));
        headerRow.add(makeHeaderLabel("VEHICLE", 180, 145));
        headerRow.add(makeHeaderLabel("START DATE", 310, 100));
        headerRow.add(makeHeaderLabel("END DATE", 450, 100));
        headerRow.add(makeHeaderLabel("STATUS", 562, 90));
        panel.add(headerRow);

        String filterClause;
        switch (activeFilter) {
            case "CURRENT": filterClause = " AND CURDATE() BETWEEN r.rental_start_date AND r.rental_end_date"; break;
            case "FUTURE": filterClause = " AND r.rental_start_date > CURDATE()"; break;
            case "PAST": filterClause = " AND r.rental_end_date < CURDATE()"; break;
            default: filterClause = "";
        }

        String sql = "SELECT r.rental_id, c.username, "
                + "CONCAT(v.brand, ' ', v.model) AS vehicle, "
                + "r.rental_start_date, r.rental_end_date, r.status, "
                + "r.pickup_location, r.return_location, r.total_cost, "
                + "r.customer_id, r.vehicle_id "
                + "FROM Rental r "
                + "JOIN Customer c ON r.customer_id = c.customer_id "
                + "JOIN Vehicle v ON r.vehicle_id = v.vehicle_id "
                + "WHERE 1=1" + filterClause
                + " ORDER BY r.rental_id";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean hasRecords = false;
            int rowIndex = 0;

            while (rs.next()) {
                hasRecords = true;
                int rentalId = rs.getInt("rental_id");
                String username = rs.getString("username");
                String vehicle = rs.getString("vehicle");
                String startDate = rs.getString("rental_start_date");
                String endDate = rs.getString("rental_end_date");
                String status = rs.getString("status");
                String pickup = rs.getString("pickup_location");
                String returnLoc = rs.getString("return_location");
                int totalCost = rs.getInt("total_cost");
                int customerId = rs.getInt("customer_id");
                int vehicleId = rs.getInt("vehicle_id");

                Color rowBg = (rowIndex % 2 == 0) ? WHITE : ROW_ALT;
                JPanel row = new JPanel(null);
                row.setBackground(rowBg);
                row.setPreferredSize(new Dimension(680, 38));
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
                row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ROW_BORDER));

                JLabel idLbl = makeRowLabel(String.valueOf(rentalId), 10, 40);
                JLabel userLbl = makeRowLabel(username != null ? username : "—", 55, 120);
                JLabel vehicleLbl = makeRowLabel(vehicle != null ? vehicle : "—", 180, 145);
                JLabel startLbl = makeRowLabel(startDate != null ? startDate : "—", 310, 100);
                JLabel endLbl = makeRowLabel(endDate != null ? endDate : "—", 450, 100);

                JLabel statusLbl = makeRowLabel(status != null ? status : "—", 562, 90);
                statusLbl.setForeground(statusColor(status));
                statusLbl.setFont(new Font("Dialog", Font.BOLD, 11));

                JButton detailsBtn = new JButton("DETAILS");
                detailsBtn.setBounds(660, 7, 72, 24);
                detailsBtn.setBackground(DARK_BLUE);
                detailsBtn.setForeground(WHITE);
                detailsBtn.setFont(new Font("Dialog", Font.BOLD, 10));
                detailsBtn.setFocusPainted(false);
                detailsBtn.setBorder(new EmptyBorder(4, 6, 4, 6));
                detailsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                detailsBtn.addActionListener(e -> {
                    rentalDetails details = new rentalDetails(
                        rentalId, customerId, vehicleId,
                        startDate, endDate, pickup, returnLoc, status, totalCost, this);
                    details.setVisible(true);
                });

                row.add(idLbl);
                row.add(userLbl);
                row.add(vehicleLbl);
                row.add(startLbl);
                row.add(endLbl);
                row.add(statusLbl);
                row.add(detailsBtn);
                panel.add(row);
                rowIndex++;
            }

            if (!hasRecords) {
                JLabel noRecords = new JLabel("NO RENTAL RECORDS FOUND.");
                noRecords.setFont(new Font("Dialog", Font.BOLD, 12));
                noRecords.setForeground(new Color(150, 160, 180));
                noRecords.setHorizontalAlignment(SwingConstants.CENTER);
                noRecords.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(Box.createVerticalStrut(30));
                panel.add(noRecords);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        panel.revalidate();
        panel.repaint();
    }

    private JLabel makeHeaderLabel(String text, int x, int width) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl.setBounds(x, 7, width, 18);
        return lbl;
    }

    private JLabel makeRowLabel(String text, int x, int width) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        lbl.setForeground(DARK_BLUE);
        lbl.setBounds(x, 10, width, 18);
        return lbl;
    }

    private Color statusColor(String status) {
        if (status == null) return DARK_BLUE;
        switch (status) {
            case "Pending": 
            	return new Color(200, 80, 0);
            case "Confirmed": 
            	return new Color(180, 150, 0);
            case "Ongoing": 
            	return new Color(0, 0, 0);
            case "Completed": 
            	return new Color(0, 120, 50);
            case "Cancelled": 
            	return new Color(180, 30, 30);
            default: 
            	return DARK_BLUE;
        }
    }
}