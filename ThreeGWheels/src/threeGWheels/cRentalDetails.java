package threeGWheels;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class cRentalDetails extends JFrame {

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

    public cRentalDetails(int rentalId) {
        setTitle("3G Wheels - Reservation Details");
        setSize(500, 680);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_BLUE);
        setContentPane(root);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setBorder(new EmptyBorder(22, 0, 22, 0));
        JLabel title = new JLabel("RESERVATION DETAILS");
        title.setForeground(WHITE);
        title.setFont(new Font("Dialog", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(DARK_BLUE);
        content.setBorder(new EmptyBorder(0, 16, 16, 16));

        loadDetails(content, rentalId);

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(DARK_BLUE);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        root.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        footer.setBackground(DARK_BLUE);
        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setBackground(new Color(30, 50, 100));
        closeBtn.setForeground(LIGHT_GRAY);
        closeBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> dispose());
        footer.add(closeBtn);
        root.add(footer, BorderLayout.SOUTH);
    }

    private void loadDetails(JPanel content, int rentalId) {
        String sql =
            "SELECT r.rental_id, r.rental_start_date, r.rental_end_date, " +
            "r.pickup_location, r.return_location, r.status, r.total_cost, " +
            "v.brand, v.model, v.vehicle_type, v.year_model, " +
            "v.license_plate_number, v.fuel_type, v.transmission, " +
            "p.payment_id, p.payment_datetime, p.payment_method, " +
            "p.reference_code, p.amount_paid " +
            "FROM Rental r " +
            "JOIN Vehicle v ON r.vehicle_id = v.vehicle_id " +
            "LEFT JOIN Payment p ON p.rental_id = r.rental_id " +
            "WHERE r.rental_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rentalId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                JLabel none = new JLabel("No record found for Rental ID " + rentalId);
                none.setForeground(LIGHT_GRAY);
                content.add(none);
                return;
            }

            boolean[] alt = {false};

            addSectionBar(content, "RESERVATION");
            addRow(content, "Rental ID", String.valueOf(rs.getInt("rental_id")), alt);
            addRow(content, "Status", rs.getString("status"), alt);
            addRow(content, "Start Date", rs.getString("rental_start_date"), alt);
            addRow(content, "End Date", rs.getString("rental_end_date"), alt);
            addRow(content, "Pickup Location", rs.getString("pickup_location"), alt);
            addRow(content, "Return Location", rs.getString("return_location"), alt);
            addRow(content, "Total Cost", "" + rs.getInt("total_cost"), alt);

            addSectionBar(content, "VEHICLE");
            alt[0] = false;
            addRow(content, "Brand & Model", rs.getString("brand") + " " + rs.getString("model"), alt);
            addRow(content, "Type", rs.getString("vehicle_type"), alt);
            addRow(content, "Year", rs.getString("year_model"), alt);
            addRow(content, "Plate No.", rs.getString("license_plate_number"), alt);
            addRow(content, "Fuel Type", rs.getString("fuel_type"), alt);
            addRow(content, "Transmission", rs.getString("transmission"), alt);

            addSectionBar(content, "PAYMENT");
            alt[0] = false;
            String payId = rs.getString("payment_id");
            if (payId == null) {
                addRow(content, "Payment", "No payment record linked.", alt);
            } else {
                addRow(content, "Payment ID", payId, alt);
                addRow(content, "Date & Time", rs.getString("payment_datetime"), alt);
                addRow(content, "Method", rs.getString("payment_method"), alt);
                addRow(content, "Reference Code", rs.getString("reference_code"), alt);
                addRow(content, "Amount Paid", "" + rs.getInt("amount_paid"), alt);
            }

        } catch (Exception ex) {
            JLabel err = new JLabel("Error: " + ex.getMessage());
            err.setForeground(Color.RED);
            content.add(err);
            ex.printStackTrace();
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

    private void addRow(JPanel parent, String label, String value, boolean[] alt) {
        JPanel row = new JPanel(new BorderLayout()) {
            @Override public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, 36);
            }
        };
        row.setBackground(alt[0] ? ROW_ALT : ROW_BASE);
        row.setPreferredSize(new Dimension(0, 36));
        row.setMinimumSize(new Dimension(0, 36));
        row.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 200, 220)),
            new EmptyBorder(0, 12, 0, 12)
        ));

        JLabel keyLbl = new JLabel(label + ":");
        keyLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        keyLbl.setForeground(DARK_BLUE);
        keyLbl.setPreferredSize(new Dimension(160, 36));

        JLabel valLbl = new JLabel(value == null || value.isBlank() ? "-" : value);
        valLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        valLbl.setForeground(new Color(30, 30, 80));

        row.add(keyLbl, BorderLayout.WEST);
        row.add(valLbl, BorderLayout.CENTER);
        parent.add(row);

        alt[0] = !alt[0];
    }
}