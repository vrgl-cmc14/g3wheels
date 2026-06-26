package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class searchRental extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;
    private static final Color ROW_ALT = new Color(240, 244, 255);
    private static final Color ROW_BORDER = new Color(210, 218, 240);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final rentalParent mainFrame;

    private JTextField idSearchField;
    private JTextField usernameSearchField;
    private JPanel recordsPanel;

    public searchRental(rentalParent mainFrame) {
        this.mainFrame = mainFrame;

        setTitle("3G Wheels \u2013 Search Rent Records");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 100, 780, 620);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(780, 70));

        JLabel titleLbl = new JLabel("SEARCH RENTAL RECORDS");
        titleLbl.setForeground(WHITE);
        titleLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(titleLbl, BorderLayout.CENTER);
        contentPane.add(header, BorderLayout.NORTH);

        JPanel searchBar = new JPanel(new GridLayout(2, 1, 0, 4));
        searchBar.setBackground(WHITE);
        searchBar.setBorder(new EmptyBorder(10, 14, 10, 14));

        JPanel idRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        idRow.setBackground(WHITE);

        JLabel idLbl = new JLabel("Rental ID:");
        idLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        idLbl.setForeground(DARK_BLUE);
        idLbl.setPreferredSize(new Dimension(90, 26));
        idRow.add(idLbl);

        idSearchField = new JTextField();
        idSearchField.setPreferredSize(new Dimension(240, 26));
        idSearchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        idRow.add(idSearchField);

        JButton idSearchBtn = new JButton("SEARCH BY ID");
        idSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        idSearchBtn.setBackground(DARK_BLUE);
        idSearchBtn.setForeground(WHITE);
        idSearchBtn.setFocusPainted(false);
        idSearchBtn.setOpaque(true);
        idSearchBtn.setBorderPainted(false);
        idSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        idSearchBtn.addActionListener(e -> performSearch("id"));
        idSearchField.addActionListener(e -> performSearch("id"));
        idRow.add(idSearchBtn);

        searchBar.add(idRow);

        JPanel userRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        userRow.setBackground(WHITE);

        JLabel userLbl = new JLabel("Username:");
        userLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        userLbl.setForeground(DARK_BLUE);
        userLbl.setPreferredSize(new Dimension(90, 26));
        userRow.add(userLbl);

        usernameSearchField = new JTextField();
        usernameSearchField.setPreferredSize(new Dimension(240, 26));
        usernameSearchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        userRow.add(usernameSearchField);

        JButton userSearchBtn = new JButton("SEARCH BY USERNAME");
        userSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        userSearchBtn.setBackground(DARK_BLUE);
        userSearchBtn.setForeground(WHITE);
        userSearchBtn.setFocusPainted(false);
        userSearchBtn.setOpaque(true);
        userSearchBtn.setBorderPainted(false);
        userSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        userSearchBtn.addActionListener(e -> performSearch("username"));
        usernameSearchField.addActionListener(e -> performSearch("username"));
        userRow.add(userSearchBtn);

        searchBar.add(userRow);

        contentPane.add(searchBar, BorderLayout.NORTH);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);

        JPanel tableHeader = new JPanel(null);
        tableHeader.setBackground(new Color(30, 60, 140));
        tableHeader.setPreferredSize(new Dimension(680, 32));
        tableHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        tableHeader.add(makeHeaderLabel("ID", 10, 40));
        tableHeader.add(makeHeaderLabel("USERNAME", 55, 120));
        tableHeader.add(makeHeaderLabel("VEHICLE", 180, 145));
        tableHeader.add(makeHeaderLabel("START DATE", 310, 100));
        tableHeader.add(makeHeaderLabel("END DATE", 450, 100));
        tableHeader.add(makeHeaderLabel("STATUS", 562, 90));

        centerArea.add(tableHeader, BorderLayout.NORTH);

        recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.Y_AXIS));
        recordsPanel.setBackground(WHITE);

        JScrollPane scrollPane = new JScrollPane(recordsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerArea.add(scrollPane, BorderLayout.CENTER);

        contentPane.add(centerArea, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        footer.setBackground(new Color(235, 238, 248));
        footer.setBorder(new MatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));

        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dispose());
        footer.add(closeBtn);

        contentPane.add(footer, BorderLayout.SOUTH);
    }

    private void performSearch(String mode) {
        String keyword;
        String sql;

        if ("id".equals(mode)) {
            keyword = idSearchField.getText().trim();
            sql = "SELECT r.rental_id, c.username, "
                + "CONCAT(v.brand, ' ', v.model) AS vehicle, "
                + "r.rental_start_date, r.rental_end_date, r.status, "
                + "r.pickup_location, r.return_location, r.total_cost, "
                + "r.customer_id, r.vehicle_id "
                + "FROM Rental r "
                + "JOIN Customer c ON r.customer_id = c.customer_id "
                + "JOIN Vehicle v ON r.vehicle_id = v.vehicle_id "
                + "WHERE CAST(r.rental_id AS CHAR) LIKE ? "
                + "ORDER BY r.rental_id";
        } else {
            keyword = usernameSearchField.getText().trim();
            sql = "SELECT r.rental_id, c.username, "
                + "CONCAT(v.brand, ' ', v.model) AS vehicle, "
                + "r.rental_start_date, r.rental_end_date, r.status, "
                + "r.pickup_location, r.return_location, r.total_cost, "
                + "r.customer_id, r.vehicle_id "
                + "FROM Rental r "
                + "JOIN Customer c ON r.customer_id = c.customer_id "
                + "JOIN Vehicle v ON r.vehicle_id = v.vehicle_id "
                + "WHERE c.username LIKE ? "
                + "ORDER BY r.rental_id";
        }

        if (keyword.isEmpty()) {
            recordsPanel.removeAll();
            recordsPanel.revalidate();
            recordsPanel.repaint();
            return;
        }

        recordsPanel.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

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

                row.add(makeRowLabel(String.valueOf(rentalId), 10, 40));
                row.add(makeRowLabel(username != null ? username : "—", 55, 120));
                row.add(makeRowLabel(vehicle != null ? vehicle : "—", 180, 145));
                row.add(makeRowLabel(startDate != null ? startDate : "—", 310, 100));
                row.add(makeRowLabel(endDate != null ? endDate : "—", 450, 100));

                JLabel statusLbl = makeRowLabel(status != null ? status : "—", 562, 90);
                statusLbl.setForeground(statusColor(status));
                statusLbl.setFont(new Font("Dialog", Font.BOLD, 11));
                row.add(statusLbl);

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
                        startDate, endDate, pickup, returnLoc, status, totalCost,
                        mainFrame);
                    details.setVisible(true);
                });
                row.add(detailsBtn);

                recordsPanel.add(row);
                rowIndex++;
            }
            rs.close();

            if (!hasRecords) {
                JLabel noRecords = new JLabel("NO RESULTS FOUND.");
                noRecords.setFont(new Font("Dialog", Font.BOLD, 12));
                noRecords.setForeground(new Color(150, 160, 180));
                noRecords.setHorizontalAlignment(SwingConstants.CENTER);
                noRecords.setAlignmentX(Component.CENTER_ALIGNMENT);
                recordsPanel.add(Box.createVerticalStrut(30));
                recordsPanel.add(noRecords);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        recordsPanel.revalidate();
        recordsPanel.repaint();
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
            case "Pending": return new Color(200, 80, 0);
            case "Confirmed": return new Color(180, 150, 0);
            case "Ongoing": return new Color(0, 0, 0);
            case "Completed": return new Color(0, 120, 50);
            case "Cancelled": return new Color(180, 30, 30);
            default: return DARK_BLUE;
        }
    }
}