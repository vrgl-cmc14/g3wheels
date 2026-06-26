package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class vehicleParent extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color WHITE = Color.WHITE;
    private static final Color ROW_ALT = new Color(240, 244, 255);
    private static final Color ROW_BORDER = new Color(210, 218, 240);
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private JPanel recordsPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                vehicleParent frame = new vehicleParent();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public vehicleParent() {
        setTitle("3G Wheels – Vehicle Records");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 620, 660);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(WHITE);
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel headerBanner = new JPanel(new BorderLayout());
        headerBanner.setBackground(DARK_BLUE);
        headerBanner.setPreferredSize(new Dimension(620, 90));
        contentPane.add(headerBanner, BorderLayout.NORTH);

        JLabel companyName = new JLabel("🚘 3G WHEELS");
        companyName.setForeground(WHITE);
        companyName.setFont(new Font("Dialog", Font.BOLD, 22));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        headerBanner.add(companyName, BorderLayout.CENTER);

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(WHITE);
        toolbar.setBorder(new EmptyBorder(8, 14, 8, 14));
        toolbar.setPreferredSize(new Dimension(620, 42));

        JLabel pageTitle = new JLabel("VEHICLE RECORDS");
        pageTitle.setForeground(DARK_BLUE);
        pageTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        toolbar.add(pageTitle, BorderLayout.WEST);

        JButton addVehicleButton = new JButton("+ ADD VEHICLE");
        addVehicleButton.setBackground(WHITE);
        addVehicleButton.setForeground(DARK_BLUE);
        addVehicleButton.setFont(new Font("Dialog", Font.BOLD, 11));
        addVehicleButton.setFocusPainted(false);
        addVehicleButton.setBorder(new EmptyBorder(5, 12, 5, 12));
        addVehicleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addVehicleButton.addActionListener(e -> {
            createVehicle addFrame = new createVehicle(this);
            addFrame.setVisible(true);
            setVisible(false);
        });

        JButton searchButton = new JButton("SEARCH");
        searchButton.setBackground(WHITE);
        searchButton.setForeground(DARK_BLUE);
        searchButton.setFont(new Font("Dialog", Font.BOLD, 11));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(new EmptyBorder(5, 12, 5, 12));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(e -> {
            searchVehicle searchFrame = new searchVehicle(this);
            searchFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        buttonPanel.setBackground(WHITE);
        buttonPanel.add(searchButton);
        buttonPanel.add(addVehicleButton);
        toolbar.add(buttonPanel, BorderLayout.EAST);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);
        centerArea.add(toolbar, BorderLayout.NORTH);

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

        JButton backBtn = new JButton("BACK");
        backBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        backBtn.setBackground(DARK_BLUE);
        backBtn.setForeground(WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(true);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            dispose();
            adminDashboard dashboard = new adminDashboard();
            dashboard.setVisible(true);
        });
        footer.add(backBtn);

        contentPane.add(footer, BorderLayout.SOUTH);

        loadRecords(recordsPanel);
    }

    public void refresh() {
        loadRecords(recordsPanel);
    }

    private void loadRecords(JPanel recordsPanel) {
        recordsPanel.removeAll();

        JPanel headerRow = new JPanel(null);
        headerRow.setBackground(new Color(30, 60, 140));
        headerRow.setPreferredSize(new Dimension(580, 32));
        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        JLabel headerID = makeHeaderLabel("ID", 10, 32);
        JLabel headerType = makeHeaderLabel("TYPE", 60, 100);
        JLabel headerBrand = makeHeaderLabel("BRAND", 170, 120);
        JLabel headerModel = makeHeaderLabel("MODEL", 300, 160);

        headerID.setHorizontalAlignment(SwingConstants.CENTER);
        headerType.setHorizontalAlignment(SwingConstants.CENTER);
        headerBrand.setHorizontalAlignment(SwingConstants.CENTER);
        headerModel.setHorizontalAlignment(SwingConstants.CENTER);

        headerRow.add(headerID);
        headerRow.add(headerType);
        headerRow.add(headerBrand);
        headerRow.add(headerModel);
        recordsPanel.add(headerRow);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM Vehicle ORDER BY vehicle_id")) {

            boolean hasRecords = false;
            int rowIndex = 0;

            while (results.next()) {
                hasRecords = true;

                int vehicleID = results.getInt("vehicle_id");
                String vehicleType = results.getString("vehicle_type");
                String vehicleBrand = results.getString("brand");
                String vehicleModel = results.getString("model");
                String vehicleYear = results.getString("year_model");
                String licensePlate = results.getString("license_plate_number");
                String registrationNumber = results.getString("vehicle_registration_number");
                int seatingCapacity = results.getInt("seating_capacity");
                int rentalRate = results.getInt("rental_rate");
                String fuelType = results.getString("fuel_type");
                String transmission = results.getString("transmission");
                int mileage = results.getInt("mileage");

                Color rowBackground = (rowIndex % 2 == 0) ? WHITE : ROW_ALT;

                JPanel recordRow = new JPanel(null);
                recordRow.setBackground(rowBackground);
                recordRow.setPreferredSize(new Dimension(580, 38));
                recordRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
                recordRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ROW_BORDER));

                JLabel labelID = new JLabel(String.valueOf(vehicleID));
                JLabel labelType = new JLabel(vehicleType.toUpperCase());
                JLabel labelBrand = new JLabel(vehicleBrand.toUpperCase());
                JLabel labelModel = new JLabel(vehicleModel.toUpperCase());

                labelID.setFont(new Font("Dialog", Font.PLAIN, 12));
                labelType.setFont(new Font("Dialog", Font.PLAIN, 12));
                labelBrand.setFont(new Font("Dialog", Font.PLAIN, 12));
                labelModel.setFont(new Font("Dialog", Font.PLAIN, 12));

                labelID.setForeground(DARK_BLUE);
                labelType.setForeground(DARK_BLUE);
                labelBrand.setForeground(DARK_BLUE);
                labelModel.setForeground(DARK_BLUE);

                labelID.setBounds(10, 10, 40, 18);
                labelType.setBounds(60, 10, 100, 18);
                labelBrand.setBounds(170, 10, 120, 18);
                labelModel.setBounds(300, 10, 150, 18);

                labelID.setHorizontalAlignment(SwingConstants.CENTER);
                labelType.setHorizontalAlignment(SwingConstants.CENTER);
                labelBrand.setHorizontalAlignment(SwingConstants.CENTER);
                labelModel.setHorizontalAlignment(SwingConstants.CENTER);

                JButton viewButton = new JButton("VIEW");
                viewButton.setBounds(468, 7, 80, 24);
                viewButton.setBackground(DARK_BLUE);
                viewButton.setForeground(WHITE);
                viewButton.setFont(new Font("Dialog", Font.BOLD, 11));
                viewButton.setFocusPainted(false);
                viewButton.setBorder(new EmptyBorder(4, 10, 4, 10));
                viewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                viewButton.addActionListener(e -> {
                    vehicleDetails detailsFrame = new vehicleDetails(this,
                        vehicleID, vehicleType, vehicleBrand, vehicleModel,
                        vehicleYear, licensePlate, registrationNumber,
                        seatingCapacity, rentalRate, fuelType, transmission, mileage
                    );
                    detailsFrame.setVisible(true);
                });

                recordRow.add(labelID);
                recordRow.add(labelType);
                recordRow.add(labelBrand);
                recordRow.add(labelModel);
                recordRow.add(viewButton);

                recordsPanel.add(recordRow);
                rowIndex++;
            }

            if (!hasRecords) {
                JLabel noRecordsLabel = new JLabel("NO VEHICLES FOUND.");
                noRecordsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
                noRecordsLabel.setForeground(new Color(150, 160, 180));
                noRecordsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                noRecordsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                recordsPanel.add(Box.createVerticalStrut(30));
                recordsPanel.add(noRecordsLabel);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        recordsPanel.revalidate();
        recordsPanel.repaint();
    }

    private JLabel makeHeaderLabel(String text, int x, int width) {
        JLabel label = new JLabel(text);
        label.setForeground(WHITE);
        label.setFont(new Font("Dialog", Font.BOLD, 12));
        label.setBounds(x, 7, width, 18);
        return label;
    }
}