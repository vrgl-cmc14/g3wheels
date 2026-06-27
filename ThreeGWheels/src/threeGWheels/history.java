package threeGWheels;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class history extends Abstraction {

    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color ROW_ALT = new Color(210, 218, 238);
    private static final Color ROW_BASE = new Color(230, 235, 245);

    private static final int ROW_WIDTH = 740;

    private final int customerId;
    private JPanel rowsPanel;

    public history(int customerId) {
        super("3G Wheels - Rental History", 780, 620);
        this.customerId = customerId;
        initComponents();
    }

    @Override
    protected void initComponents() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_BLUE);
        setContentPane(root);

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildContent(), BorderLayout.CENTER);
        root.add(buildFooter(), BorderLayout.SOUTH);
    }

    @Override
    protected JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setBorder(new EmptyBorder(22, 0, 22, 0));
        JLabel title = makeLabel("RENTAL HISTORY", WHITE, Font.BOLD, 18);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        return header;
    }

    @Override
    protected JPanel buildContent() {
        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(DARK_BLUE);
        body.setBorder(new EmptyBorder(16, 20, 16, 20));

        JPanel headerRow = new JPanel(null);
        headerRow.setBackground(CARD_BLUE);
        headerRow.setPreferredSize(new Dimension(ROW_WIDTH, 36));

        addHeaderCell(headerRow, "ID", 20, 50);
        addHeaderCell(headerRow, "VEHICLE", 70, 210);
        addHeaderCell(headerRow, "START DATE", 280, 110);
        addHeaderCell(headerRow, "END DATE", 390, 110);
        addHeaderCell(headerRow, "STATUS", 500, 110);
        addHeaderCell(headerRow, "", 615, 90);

        body.add(headerRow, BorderLayout.NORTH);

        rowsPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, super.getPreferredSize().height);
            }
        };
        rowsPanel.setLayout(new BoxLayout(rowsPanel, BoxLayout.Y_AXIS));
        rowsPanel.setBackground(DARK_BLUE);

        loadRows();

        JScrollPane scroll = new JScrollPane(rowsPanel);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(DARK_BLUE);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        body.add(scroll, BorderLayout.CENTER);
        return body;
    }

    @Override
    protected JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 12));
        footer.setBackground(DARK_BLUE);
        JButton backBtn = makeButton("BACK", new Color(30, 50, 100), LIGHT_GRAY);
        backBtn.addActionListener(e -> dispose());
        footer.add(backBtn);
        return footer;
    }

    private void loadRows() {
        String sql =
            "SELECT r.rental_id, v.brand, v.model, " +
            "r.rental_start_date, r.rental_end_date, r.status " +
            "FROM Rental r " +
            "JOIN Vehicle v ON r.vehicle_id = v.vehicle_id " +
            "WHERE r.customer_id = ? " +
            "ORDER BY r.rental_start_date DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            boolean alternate = false;
            boolean any = false;

            while (rs.next()) {
                any = true;
                rowsPanel.add(buildRow(
                    rs.getInt("rental_id"),
                    rs.getString("brand") + " " + rs.getString("model"),
                    rs.getString("rental_start_date"),
                    rs.getString("rental_end_date"),
                    rs.getString("status"),
                    alternate
                ));
                alternate = !alternate;
            }

            if (!any) {
                JLabel none = new JLabel("No rental records found.");
                none.setForeground(LIGHT_GRAY);
                none.setFont(new Font("Dialog", Font.ITALIC, 13));
                none.setAlignmentX(Component.CENTER_ALIGNMENT);
                none.setBorder(new EmptyBorder(24, 0, 0, 0));
                rowsPanel.add(none);
            }

        } catch (Exception ex) {
            JLabel err = new JLabel("Error loading history: " + ex.getMessage());
            err.setForeground(Color.RED);
            rowsPanel.add(err);
            ex.printStackTrace();
        }
    }

    private JPanel buildRow(int rentalId, String vehicle,
                            String startDate, String endDate,
                            String status, boolean alternate) {

        JPanel row = new JPanel(null);
        row.setBackground(alternate ? ROW_ALT : ROW_BASE);
        row.setPreferredSize(new Dimension(ROW_WIDTH, 38));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 200, 220)));

        addCell(row, String.valueOf(rentalId), 20, 50);
        addCell(row, vehicle, 70, 210);
        addCell(row, startDate, 280, 110);
        addCell(row, endDate, 390, 110);

        JLabel statusLbl = new JLabel(status != null ? status : "-");
        statusLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        statusLbl.setForeground(statusColor(status));
        statusLbl.setBounds(500, 0, 110, 38);
        row.add(statusLbl);

        JButton viewBtn = new JButton("VIEW");
        viewBtn.setBackground(DARK_BLUE);
        viewBtn.setForeground(WHITE);
        viewBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        viewBtn.setFocusPainted(false);
        viewBtn.setBorderPainted(false);
        viewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewBtn.setBounds(615, 5, 80, 28);
        viewBtn.addActionListener(e -> new cRentalDetails(rentalId).setVisible(true));
        row.add(viewBtn);

        return row;
    }

    private void addCell(JPanel row, String text, int x, int w) {
        JLabel lbl = new JLabel(text != null ? text : "-");
        lbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        lbl.setForeground(MID_BLUE);
        lbl.setBounds(x, 0, w, 38);
        row.add(lbl);
    }

    private void addHeaderCell(JPanel panel, String text, int x, int w) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl.setForeground(WHITE);
        lbl.setBounds(x, 0, w, 36);
        panel.add(lbl);
    }

    private Color statusColor(String status) {
        if (status == null) return MID_BLUE;
        return switch (status) {
            case "Ongoing" -> new Color(180, 80, 0);
            case "Confirmed" -> new Color(20, 100, 20);
            case "Pending" -> new Color(100, 80, 0);
            case "Completed" -> new Color(0, 100, 80);
            case "Cancelled" -> new Color(160, 20, 20);
            default -> MID_BLUE;
        };
    }
}