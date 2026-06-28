package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class cRentForm extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color CARD_BLUE = new Color(10, 45, 110);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(200, 210, 230);
    private static final Color ROW_BASE = new Color(230, 235, 245);
    private static final Color ROW_ALT = new Color(210, 218, 238);
    

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final int customerId;
    private final int vehicleId;
    private final int rentalRate;
    private final String firstName, middleName, lastName;
    private final String suffix, dob, email, phone;
    private final String licenseNum, address, username, password;

    private JTextField tfStartDate, tfEndDate;
    private JTextField tfPickup, tfReturn;
    private JLabel lblTotalCost;
    private JComboBox<String> cbPaymentMethod;
    private JTextField tfReferenceCode;
    private JPanel unavailablePanel;

    public cRentForm(int customerId,
                     int vehicleId, String vehicleType, String brand, String model,
                     int rentalRate,
                     String firstName, String middleName, String lastName,
                     String suffix, String dob, String email, String phone,
                     String licenseNum, String address, String username, String password) {

        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentalRate = rentalRate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.licenseNum = licenseNum;
        this.address = address;
        this.username = username;
        this.password = password;

        setTitle("3G Wheels - Rent a Vehicle");
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_BLUE);
        setContentPane(root);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setBorder(new EmptyBorder(22, 0, 10, 0));
        JLabel title = new JLabel("RENTAL FORM");
        title.setForeground(WHITE);
        title.setFont(new Font("Dialog", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(0, 0));
        body.setBackground(DARK_BLUE);
        root.add(body, BorderLayout.CENTER);

        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(DARK_BLUE);
        left.setBorder(new EmptyBorder(0, 16, 16, 8));
        left.setPreferredSize(new Dimension(210, 0));

        JLabel leftTitle = new JLabel("UNAVAILABLE DATES");
        leftTitle.setForeground(WHITE);
        leftTitle.setFont(new Font("Dialog", Font.BOLD, 11));
        leftTitle.setHorizontalAlignment(SwingConstants.CENTER);
        leftTitle.setOpaque(true);
        leftTitle.setBackground(CARD_BLUE);
        leftTitle.setBorder(new EmptyBorder(8, 0, 8, 0));
        left.add(leftTitle, BorderLayout.NORTH);

        unavailablePanel = new JPanel();
        unavailablePanel.setLayout(new BoxLayout(unavailablePanel, BoxLayout.Y_AXIS));
        unavailablePanel.setBackground(DARK_BLUE);

        JScrollPane leftScroll = new JScrollPane(unavailablePanel);
        leftScroll.setBorder(BorderFactory.createLineBorder(new Color(30, 60, 130)));
        leftScroll.getViewport().setBackground(DARK_BLUE);
        leftScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        left.add(leftScroll, BorderLayout.CENTER);

        body.add(left, BorderLayout.WEST);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(DARK_BLUE);
        content.setBorder(new EmptyBorder(0, 8, 16, 16));

        addSectionBar(content, "VEHICLE");
        addReadOnlyRow(content, "Vehicle ID", String.valueOf(vehicleId), false);
        addReadOnlyRow(content, "Vehicle Type", vehicleType, true);
        addReadOnlyRow(content, "Brand", brand, false);
        addReadOnlyRow(content, "Model", model, true);

        addSectionBar(content, "RENTAL DETAILS");
        tfStartDate = addFieldRow(content, "Start Date (YYYY-MM-DD)", false);
        tfEndDate = addFieldRow(content, "End Date (YYYY-MM-DD)", true);
        tfPickup = addFieldRow(content, "Pickup Location", false);
        tfReturn = addFieldRow(content, "Return Location", true);

        addSectionBar(content, "TOTAL COST");
        lblTotalCost = new JLabel("0");
        lblTotalCost.setFont(new Font("Dialog", Font.BOLD, 14));
        lblTotalCost.setForeground(new Color(30, 30, 80));
        JPanel costRow = makePanelRow(false);
        JLabel costKey = new JLabel("Amount:");
        costKey.setFont(new Font("Dialog", Font.BOLD, 12));
        costKey.setForeground(DARK_BLUE);
        costKey.setPreferredSize(new Dimension(190, 36));
        costRow.add(costKey, BorderLayout.WEST);
        costRow.add(lblTotalCost, BorderLayout.CENTER);
        content.add(costRow);

        addSectionBar(content, "PAYMENT");
        cbPaymentMethod = new JComboBox<>(new String[]{"Cash", "Card", "E-wallet"});
        addComboRow(content, "Payment Method", cbPaymentMethod, false);
        tfReferenceCode = addFieldRow(content, "Reference Code", true);

        JScrollPane rightScroll = new JScrollPane(content);
        rightScroll.setBorder(null);
        rightScroll.getViewport().setBackground(DARK_BLUE);
        rightScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        body.add(rightScroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        footer.setBackground(DARK_BLUE);

        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setBackground(new Color(30, 50, 100));
        cancelBtn.setForeground(LIGHT_GRAY);
        cancelBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(e -> dispose());

        JButton confirmBtn = new JButton("CONFIRM");
        confirmBtn.setBackground(new Color(30, 70, 160));
        confirmBtn.setForeground(WHITE);
        confirmBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        confirmBtn.setFocusPainted(false);
        confirmBtn.setBorderPainted(false);
        confirmBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmBtn.addActionListener(e -> handleConfirm());

        footer.add(cancelBtn);
        footer.add(confirmBtn);
        root.add(footer, BorderLayout.SOUTH);

        
        FocusAdapter dateListener = new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { computeCost(); }
        };
        tfStartDate.addFocusListener(dateListener);
        tfEndDate.addFocusListener(dateListener);

        loadUnavailableDates();

        pack();
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
    }

    private void loadUnavailableDates() {
        unavailablePanel.removeAll();

        String sql = "SELECT rental_start_date, rental_end_date, status " +
                     "FROM Rental " +
                     "WHERE vehicle_id = ? " +
                     "AND status IN ('Pending', 'Confirmed', 'Ongoing') " +
                     "ORDER BY rental_start_date";

        List<String[]> entries = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entries.add(new String[]{
                    rs.getString("rental_start_date"),
                    rs.getString("rental_end_date"),
                    rs.getString("status")
                });
            }
        } catch (Exception ex) {
            JLabel err = new JLabel("Error: " + ex.getMessage());
            err.setForeground(Color.RED);
            err.setFont(new Font("Dialog", Font.PLAIN, 10));
            unavailablePanel.add(err);
        }

        if (entries.isEmpty()) {
            JLabel none = new JLabel("No bookings yet");
            none.setForeground(LIGHT_GRAY);
            none.setFont(new Font("Dialog", Font.ITALIC, 11));
            none.setAlignmentX(Component.CENTER_ALIGNMENT);
            none.setBorder(new EmptyBorder(12, 0, 0, 0));
            unavailablePanel.add(none);
        } else {
            boolean alt = false;
            for (String[] entry : entries) {
                JPanel card = new JPanel(new GridLayout(3, 1, 0, 2));
                card.setBackground(alt ? ROW_ALT : ROW_BASE);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 200, 220)),
                    new EmptyBorder(6, 8, 6, 8)
                ));
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 68));

                JLabel from = new JLabel("From: " + entry[0]);
                from.setFont(new Font("Dialog", Font.PLAIN, 11));
                from.setForeground(DARK_BLUE);

                JLabel to = new JLabel("To:     " + entry[1]);
                to.setFont(new Font("Dialog", Font.PLAIN, 11));
                to.setForeground(DARK_BLUE);

                JLabel status = new JLabel("[ " + entry[2] + " ]");
                status.setFont(new Font("Dialog", Font.BOLD, 10));
                status.setForeground(statusColor(entry[2]));

                card.add(from);
                card.add(to);
                card.add(status);

                unavailablePanel.add(card);
                alt = !alt;
            }
        }

        unavailablePanel.revalidate();
        unavailablePanel.repaint();
    }

    private Color statusColor(String status) {
        return switch (status) {
            case "Ongoing" -> new Color(180, 80, 0);
            case "Confirmed" -> new Color(20, 100, 20);
            case "Pending" -> new Color(100, 80, 0);
            default -> DARK_BLUE;
        };
    }

    private boolean isVehicleAvailable(LocalDate start, LocalDate end) {
        String sql = "SELECT COUNT(*) FROM Rental " +
                     "WHERE vehicle_id = ? " +
                     "AND status IN ('Pending', 'Confirmed', 'Ongoing') " +
                     "AND rental_start_date < ? " +
                     "AND rental_end_date > ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ps.setDate(2, java.sql.Date.valueOf(end));
            ps.setDate(3, java.sql.Date.valueOf(start));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) == 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void computeCost() {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(tfStartDate.getText().trim(), fmt);
            LocalDate end = LocalDate.parse(tfEndDate.getText().trim(), fmt);
            long days = ChronoUnit.DAYS.between(start, end);
            if (days > 0) {
                lblTotalCost.setText(" " + (days * rentalRate));
            } else {
                lblTotalCost.setText("Invalid dates");
            }
        } catch (Exception ex) {
            lblTotalCost.setText("0");
        }
    }

    private void handleConfirm() {
        String startStr = tfStartDate.getText().trim();
        String endStr = tfEndDate.getText().trim();
        String pickup = tfPickup.getText().trim();
        String returnLoc = tfReturn.getText().trim();
        String refCode = tfReferenceCode.getText().trim();
        String method = (String) cbPaymentMethod.getSelectedItem();

        if (startStr.isEmpty() || endStr.isEmpty() || pickup.isEmpty()
                || returnLoc.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all rental and payment fields.",
                "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long days;
        LocalDate start, end;
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            start = LocalDate.parse(startStr, fmt);
            end   = LocalDate.parse(endStr, fmt);
            days  = ChronoUnit.DAYS.between(start, end);
            if (days <= 0) throw new Exception("End date must be after start date.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Date error: " + ex.getMessage(),
                "Invalid Dates", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (start.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(this,
                "You cannot book a rental starting in the past.\n" +
                "Please select today or a future date.",
                "Invalid Start Date", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isVehicleAvailable(start, end)) {
            JOptionPane.showMessageDialog(this,
                "This vehicle is unavailable for the selected dates.\n" +
                "It has an existing Pending, Confirmed, or Ongoing rental\n" +
                "that overlaps with your requested period.\n\n" +
                "Please choose different dates or select another vehicle.",
                "Vehicle Unavailable", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int totalCost = (int)(days * rentalRate);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            conn.setAutoCommit(false);

            int rentalId;
            String rentalSql = "INSERT INTO Rental " +
                "(customer_id, vehicle_id, rental_start_date, rental_end_date, " +
                "pickup_location, return_location, status, total_cost) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'Pending', ?)";
            try (PreparedStatement ps = conn.prepareStatement(rentalSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, customerId);
                ps.setInt(2, vehicleId);
                ps.setDate(3, java.sql.Date.valueOf(start));
                ps.setDate(4, java.sql.Date.valueOf(end));
                ps.setString(5, pickup);
                ps.setString(6, returnLoc);
                ps.setInt(7, totalCost);
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                rentalId = keys.getInt(1);
            }

            String paymentSql = "INSERT INTO Payment " +
                "(rental_id, payment_datetime, payment_method, reference_code, amount_paid) " +
                "VALUES (?, NOW(), ?, ?, ?)";
            
            try (PreparedStatement ps = conn.prepareStatement(paymentSql)) {
                ps.setInt(1, rentalId);
                ps.setString(2, method);
                ps.setString(3, refCode);
                ps.setInt(4, totalCost);
                ps.executeUpdate();
            }

            conn.commit();

            JOptionPane.showMessageDialog(this,
                "Rental confirmed!\nRental ID: " + rentalId +
                "\nTotal: " + totalCost,
                "Success", JOptionPane.INFORMATION_MESSAGE);

            for (Frame frame : Frame.getFrames()) {
                frame.dispose();
            }
            SwingUtilities.invokeLater(() -> new menu(
                customerId, firstName, middleName, lastName,
                suffix, dob, email, phone,
                licenseNum, address, username, password
            ).setVisible(true));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Failed to save rental: " + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSectionBar(JPanel parent, String text) {
        parent.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel bar = new JPanel(new BorderLayout()) {
            @Override public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, 30);
            }
        };
        bar.setBackground(CARD_BLUE);
        bar.setPreferredSize(new Dimension(0, 30));
        bar.setMinimumSize(new Dimension(0, 30));
        JLabel lbl = new JLabel(text);
        lbl.setForeground(WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 11));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(lbl, BorderLayout.CENTER);
        parent.add(bar);
    }

    private void addReadOnlyRow(JPanel parent, String label, String value, boolean alternate) {
        JPanel row = makePanelRow(alternate);
        JLabel keyLbl = new JLabel(label + ":");
        keyLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        keyLbl.setForeground(DARK_BLUE);
        keyLbl.setPreferredSize(new Dimension(190, 36));
        JLabel valLbl = new JLabel(value != null ? value : "-");
        valLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        valLbl.setForeground(new Color(30, 30, 80));
        row.add(keyLbl, BorderLayout.WEST);
        row.add(valLbl, BorderLayout.CENTER);
        parent.add(row);
    }

    private JTextField addFieldRow(JPanel parent, String label, boolean alternate) {
        JPanel row = makePanelRow(alternate);
        JLabel keyLbl = new JLabel(label + ":");
        keyLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        keyLbl.setForeground(DARK_BLUE);
        keyLbl.setPreferredSize(new Dimension(190, 36));
        JTextField tf = new JTextField();
        tf.setFont(new Font("Dialog", Font.PLAIN, 12));
        tf.setForeground(new Color(30, 30, 80));
        tf.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        row.add(keyLbl, BorderLayout.WEST);
        row.add(tf, BorderLayout.CENTER);
        parent.add(row);
        return tf;
    }

    private void addComboRow(JPanel parent, String label, JComboBox<String> combo, boolean alternate) {
        JPanel row = makePanelRow(alternate);
        JLabel keyLbl = new JLabel(label + ":");
        keyLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        keyLbl.setForeground(DARK_BLUE);
        keyLbl.setPreferredSize(new Dimension(190, 36));
        combo.setFont(new Font("Dialog", Font.PLAIN, 12));
        row.add(keyLbl, BorderLayout.WEST);
        row.add(combo, BorderLayout.CENTER);
        parent.add(row);
    }

    private JPanel makePanelRow(boolean alternate) {
        JPanel row = new JPanel(new BorderLayout()) {
            @Override public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, 36);
            }
        };
        row.setBackground(alternate ? ROW_ALT : ROW_BASE);
        row.setPreferredSize(new Dimension(0, 36));
        row.setMinimumSize(new Dimension(0, 36));
        row.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 200, 220)),
            new EmptyBorder(0, 12, 0, 12)));
        return row;
    }
}