package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class searchVehicle extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color WHITE = Color.WHITE;
    private static final Color ROW_ALT = new Color(240, 243, 252);
    private static final Color ROW_BORDER = new Color(220, 225, 240);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final vehicleParent mainFrame;

    private JTextField idSearchField;
    private JComboBox<String> typeComboBox;
    private JTextField brandSearchField;
    private JTextField modelSearchField;
    private JPanel recordsPanel;

    public searchVehicle(vehicleParent mainFrame) {
        this.mainFrame = mainFrame;

        setTitle("3G Wheels - Search Vehicle Records");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 100, 620, 660);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(620, 90));

        JLabel titleLbl = new JLabel("SEARCH: VEHICLE");
        titleLbl.setForeground(WHITE);
        titleLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(titleLbl, BorderLayout.CENTER);

        JPanel searchBar = new JPanel(new GridLayout(4, 1, 0, 4));
        searchBar.setBackground(WHITE);
        searchBar.setBorder(new EmptyBorder(10, 14, 10, 14));

        // Search by ID
        JPanel idRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        idRow.setBackground(WHITE);

        JLabel idLbl = new JLabel("Vehicle ID:");
        idLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        idLbl.setForeground(DARK_BLUE);
        idLbl.setPreferredSize(new Dimension(100, 26));
        idRow.add(idLbl);

        idSearchField = new JTextField();
        idSearchField.setPreferredSize(new Dimension(220, 26));
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

        // Search by Type (ComboBox)
        JPanel typeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        typeRow.setBackground(WHITE);

        JLabel typeLbl = new JLabel("Type:");
        typeLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        typeLbl.setForeground(DARK_BLUE);
        typeLbl.setPreferredSize(new Dimension(100, 26));
        typeRow.add(typeLbl);

        typeComboBox = new JComboBox<>(new String[]{"SUV", "Sedan", "Hatchback", "Pickup", "Coupe"});
        typeComboBox.setPreferredSize(new Dimension(220, 26));
        typeComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        typeComboBox.setBackground(WHITE);
        typeRow.add(typeComboBox);

        JButton typeSearchBtn = new JButton("SEARCH BY TYPE");
        typeSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        typeSearchBtn.setBackground(DARK_BLUE);
        typeSearchBtn.setForeground(WHITE);
        typeSearchBtn.setFocusPainted(false);
        typeSearchBtn.setOpaque(true);
        typeSearchBtn.setBorderPainted(false);
        typeSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        typeSearchBtn.addActionListener(e -> performSearch("type"));
        typeRow.add(typeSearchBtn);

        searchBar.add(typeRow);

        // Search by Brand
        JPanel brandRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        brandRow.setBackground(WHITE);

        JLabel brandLbl = new JLabel("Brand:");
        brandLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        brandLbl.setForeground(DARK_BLUE);
        brandLbl.setPreferredSize(new Dimension(100, 26));
        brandRow.add(brandLbl);

        brandSearchField = new JTextField();
        brandSearchField.setPreferredSize(new Dimension(220, 26));
        brandSearchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        brandRow.add(brandSearchField);

        JButton brandSearchBtn = new JButton("SEARCH BY BRAND");
        brandSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        brandSearchBtn.setBackground(DARK_BLUE);
        brandSearchBtn.setForeground(WHITE);
        brandSearchBtn.setFocusPainted(false);
        brandSearchBtn.setOpaque(true);
        brandSearchBtn.setBorderPainted(false);
        brandSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        brandSearchBtn.addActionListener(e -> performSearch("brand"));
        brandSearchField.addActionListener(e -> performSearch("brand"));
        brandRow.add(brandSearchBtn);

        searchBar.add(brandRow);

        JPanel modelRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        modelRow.setBackground(WHITE);

        JLabel modelLbl = new JLabel("Model:");
        modelLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        modelLbl.setForeground(DARK_BLUE);
        modelLbl.setPreferredSize(new Dimension(100, 26));
        modelRow.add(modelLbl);

        modelSearchField = new JTextField();
        modelSearchField.setPreferredSize(new Dimension(220, 26));
        modelSearchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        modelRow.add(modelSearchField);

        JButton modelSearchBtn = new JButton("SEARCH BY MODEL");
        modelSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        modelSearchBtn.setBackground(DARK_BLUE);
        modelSearchBtn.setForeground(WHITE);
        modelSearchBtn.setFocusPainted(false);
        modelSearchBtn.setOpaque(true);
        modelSearchBtn.setBorderPainted(false);
        modelSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modelSearchBtn.addActionListener(e -> performSearch("model"));
        modelSearchField.addActionListener(e -> performSearch("model"));
        modelRow.add(modelSearchBtn);

        searchBar.add(modelRow);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(header, BorderLayout.NORTH);
        northPanel.add(searchBar, BorderLayout.CENTER);
        contentPane.add(northPanel, BorderLayout.NORTH);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);

        JPanel tableHeader = new JPanel(null);
        tableHeader.setBackground(MID_BLUE);
        tableHeader.setPreferredSize(new Dimension(620, 36));

        tableHeader.add(makeHeaderLabel("ID", 10, 32));
        tableHeader.add(makeHeaderLabel("TYPE", 60, 100));
        tableHeader.add(makeHeaderLabel("BRAND", 170, 120));
        tableHeader.add(makeHeaderLabel("MODEL", 300, 160));

        centerArea.add(tableHeader, BorderLayout.NORTH);

        recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.Y_AXIS));
        recordsPanel.setBackground(WHITE);

        centerArea.add(recordsPanel, BorderLayout.CENTER);

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
        String baseSelect =
            "SELECT vehicle_id, vehicle_type, brand, model, year_model, " +
            "license_plate_number, vehicle_registration_number, seating_capacity, " +
            "rental_rate, fuel_type, transmission, mileage " +
            "FROM Vehicle WHERE ";

        switch (mode) {
            case "id":
                keyword = idSearchField.getText().trim();
                if (keyword.isEmpty()) { clearRecords(); return; }
                sql = baseSelect + "CAST(vehicle_id AS CHAR) LIKE ? ORDER BY vehicle_id";
                break;
            case "type":
                keyword = (String) typeComboBox.getSelectedItem();
                sql = baseSelect + "vehicle_type = ? ORDER BY vehicle_id";
                break;
            case "brand":
                keyword = brandSearchField.getText().trim();
                if (keyword.isEmpty()) { clearRecords(); return; }
                sql = baseSelect + "brand LIKE ? ORDER BY vehicle_id";
                break;
            default:
                keyword = modelSearchField.getText().trim();
                if (keyword.isEmpty()) { clearRecords(); return; }
                sql = baseSelect + "model LIKE ? ORDER BY vehicle_id";
                break;
        }

        recordsPanel.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (mode.equals("type")) {
                ps.setString(1, keyword);
            } else {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();

            boolean hasRecords = false;
            int rowIndex = 0;

            while (rs.next()) {
                hasRecords = true;

                int vehicleId = rs.getInt("vehicle_id");
                String vehicleType = rs.getString("vehicle_type");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int yearModel = rs.getInt("year_model");
                String licensePlate = rs.getString("license_plate_number");
                String regNumber = rs.getString("vehicle_registration_number");
                int seating = rs.getInt("seating_capacity");
                int rentalRate = rs.getInt("rental_rate");
                String fuelType = rs.getString("fuel_type");
                String transmission = rs.getString("transmission");
                int mileage = rs.getInt("mileage");

                Color rowBg = (rowIndex % 2 == 0) ? WHITE : ROW_ALT;

                JPanel row = new JPanel(null);
                row.setBackground(rowBg);
                row.setPreferredSize(new Dimension(580, 38));
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
                row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ROW_BORDER));

                row.add(makeRowLabel(String.valueOf(vehicleId), 10, 40));
                row.add(makeRowLabel(vehicleType != null ? vehicleType.toUpperCase() : "—", 60, 100));
                row.add(makeRowLabel(brand != null ? brand.toUpperCase() : "—", 170, 120));
                row.add(makeRowLabel(model != null ? model.toUpperCase() : "—", 300, 150));

                JButton viewBtn = new JButton("VIEW");
                viewBtn.setBounds(468, 7, 80, 24);
                viewBtn.setBackground(DARK_BLUE);
                viewBtn.setForeground(WHITE);
                viewBtn.setFont(new Font("Dialog", Font.BOLD, 10));
                viewBtn.setFocusPainted(false);
                viewBtn.setBorderPainted(false);
                viewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                viewBtn.addActionListener(e -> {
                    vehicleDetails details = new vehicleDetails(
                        mainFrame, vehicleId, vehicleType, brand, model,
                        String.valueOf(yearModel), licensePlate, regNumber,
                        seating, rentalRate, fuelType, transmission, mileage
                    );
                    details.setVisible(true);
                });
                row.add(viewBtn);

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

    private void clearRecords() {
        recordsPanel.removeAll();
        recordsPanel.revalidate();
        recordsPanel.repaint();
    }

    private JLabel makeHeaderLabel(String text, int x, int width) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl.setBounds(x, 0, width, 36);
        return lbl;
    }

    private JLabel makeRowLabel(String text, int x, int width) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        lbl.setForeground(MID_BLUE);
        lbl.setBounds(x, 0, width, 38);
        return lbl;
    }
}