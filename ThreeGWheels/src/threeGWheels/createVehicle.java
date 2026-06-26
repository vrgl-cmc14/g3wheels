package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class createVehicle extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;

    private JComboBox<String> cmbVehicleType;
    private JTextField txtBrand;
    private JTextField txtModel;
    private JTextField txtYearModel;
    private JTextField txtLicensePlate;
    private JTextField txtRegistrationNumber;
    private JTextField txtSeatingCapacity;
    private JTextField txtRentalRate;
    private JComboBox<String> cmbFuelType;
    private JComboBox<String> cmbTransmission;
    private JTextField txtMileage;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private vehicleParent parentFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                adminLogin login = new adminLogin();
                login.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public createVehicle() {
        this(null);
    }

    public createVehicle(vehicleParent parent) {
        this.parentFrame = parent;
        setTitle("3G Wheels – Add Vehicle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 560);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel headerBanner = new JPanel(new BorderLayout());
        headerBanner.setBackground(DARK_BLUE);
        headerBanner.setPreferredSize(new Dimension(450, 60));
        contentPane.add(headerBanner, BorderLayout.NORTH);

        JLabel companyName = new JLabel("ADD NEW VEHICLE");
        companyName.setForeground(WHITE);
        companyName.setFont(new Font("Dialog", Font.BOLD, 16));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        headerBanner.add(companyName, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(WHITE);
        formPanel.setBorder(new EmptyBorder(12, 14, 14, 14));
        contentPane.add(formPanel, BorderLayout.CENTER);

        JLabel typeLabel = new JLabel("VEHICLE TYPE *");
        typeLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        typeLabel.setForeground(DARK_BLUE);
        typeLabel.setBounds(14, 4, 180, 18);
        formPanel.add(typeLabel);

        cmbVehicleType = new JComboBox<>(new String[]{"SUV", "Sedan", "Hatchback", "Pickup", "Coupe"});
        cmbVehicleType.setBounds(200, 0, 210, 26);
        formPanel.add(cmbVehicleType);

        JLabel brandLabel = new JLabel("BRAND *");
        brandLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        brandLabel.setForeground(DARK_BLUE);
        brandLabel.setBounds(14, 40, 180, 18);
        formPanel.add(brandLabel);

        txtBrand = new JTextField();
        txtBrand.setBounds(200, 36, 210, 26);
        formPanel.add(txtBrand);

        JLabel modelLabel = new JLabel("MODEL *");
        modelLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        modelLabel.setForeground(DARK_BLUE);
        modelLabel.setBounds(14, 76, 180, 18);
        formPanel.add(modelLabel);

        txtModel = new JTextField();
        txtModel.setBounds(200, 72, 210, 26);
        formPanel.add(txtModel);

        JLabel yearLabel = new JLabel("YEAR MODEL *");
        yearLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        yearLabel.setForeground(DARK_BLUE);
        yearLabel.setBounds(14, 112, 180, 18);
        formPanel.add(yearLabel);

        txtYearModel = new JTextField();
        txtYearModel.setBounds(200, 108, 210, 26);
        formPanel.add(txtYearModel);

        JLabel plateLabel = new JLabel("LICENSE PLATE *");
        plateLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        plateLabel.setForeground(DARK_BLUE);
        plateLabel.setBounds(14, 148, 180, 18);
        formPanel.add(plateLabel);

        txtLicensePlate = new JTextField();
        txtLicensePlate.setBounds(200, 144, 210, 26);
        formPanel.add(txtLicensePlate);

        JLabel regLabel = new JLabel("REG. NUMBER *");
        regLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        regLabel.setForeground(DARK_BLUE);
        regLabel.setBounds(14, 184, 180, 18);
        formPanel.add(regLabel);

        txtRegistrationNumber = new JTextField();
        txtRegistrationNumber.setBounds(200, 180, 210, 26);
        formPanel.add(txtRegistrationNumber);

        JLabel seatLabel = new JLabel("SEATING CAPACITY *");
        seatLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        seatLabel.setForeground(DARK_BLUE);
        seatLabel.setBounds(14, 220, 180, 18);
        formPanel.add(seatLabel);

        txtSeatingCapacity = new JTextField();
        txtSeatingCapacity.setBounds(200, 216, 210, 26);
        formPanel.add(txtSeatingCapacity);

        JLabel rateLabel = new JLabel("RENTAL RATE *");
        rateLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        rateLabel.setForeground(DARK_BLUE);
        rateLabel.setBounds(14, 256, 180, 18);
        formPanel.add(rateLabel);

        txtRentalRate = new JTextField();
        txtRentalRate.setBounds(200, 252, 210, 26);
        formPanel.add(txtRentalRate);

        JLabel fuelLabel = new JLabel("FUEL TYPE *");
        fuelLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        fuelLabel.setForeground(DARK_BLUE);
        fuelLabel.setBounds(14, 292, 180, 18);
        formPanel.add(fuelLabel);

        cmbFuelType = new JComboBox<>(new String[]{"Gas", "Diesel", "Hybrid", "Electric"});
        cmbFuelType.setBounds(200, 288, 210, 26);
        formPanel.add(cmbFuelType);

        JLabel transLabel = new JLabel("TRANSMISSION *");
        transLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        transLabel.setForeground(DARK_BLUE);
        transLabel.setBounds(14, 328, 180, 18);
        formPanel.add(transLabel);

        cmbTransmission = new JComboBox<>(new String[]{"Automatic", "Manual"});
        cmbTransmission.setBounds(200, 324, 210, 26);
        formPanel.add(cmbTransmission);

        JLabel mileageLabel = new JLabel("MILEAGE (KM) *");
        mileageLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        mileageLabel.setForeground(DARK_BLUE);
        mileageLabel.setBounds(14, 364, 180, 18);
        formPanel.add(mileageLabel);

        txtMileage = new JTextField();
        txtMileage.setBounds(200, 360, 210, 26);
        formPanel.add(txtMileage);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        footer.setBackground(new Color(235, 238, 248));
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        contentPane.add(footer, BorderLayout.SOUTH);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setBackground(WHITE);
        cancelButton.setForeground(DARK_BLUE);
        cancelButton.setFont(new Font("Dialog", Font.BOLD, 11));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(new EmptyBorder(5, 16, 5, 16));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> {
            if (parentFrame != null) parentFrame.setVisible(true);
            dispose();
        });
        footer.add(cancelButton);

        JButton saveButton = new JButton("SAVE VEHICLE");
        saveButton.setBackground(DARK_BLUE);
        saveButton.setForeground(WHITE);
        saveButton.setFont(new Font("Dialog", Font.BOLD, 11));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(new EmptyBorder(5, 16, 5, 16));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> saveVehicle());
        footer.add(saveButton);
    }

    private void clearFields() {
        cmbVehicleType.setSelectedIndex(0);
        txtBrand.setText("");
        txtModel.setText("");
        txtYearModel.setText("");
        txtLicensePlate.setText("");
        txtRegistrationNumber.setText("");
        txtSeatingCapacity.setText("");
        txtRentalRate.setText("");
        cmbFuelType.setSelectedIndex(0);
        cmbTransmission.setSelectedIndex(0);
        txtMileage.setText("");
        txtBrand.requestFocus();
    }

    private void saveVehicle() {
        if (txtBrand.getText().trim().isEmpty() ||
            txtModel.getText().trim().isEmpty() ||
            txtYearModel.getText().trim().isEmpty() ||
            txtLicensePlate.getText().trim().isEmpty() ||
            txtRegistrationNumber.getText().trim().isEmpty() ||
            txtSeatingCapacity.getText().trim().isEmpty() ||
            txtRentalRate.getText().trim().isEmpty() ||
            txtMileage.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                "Please fill in all required fields.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int yearModel, seatingCapacity, rentalRate, mileage;
        try {
            yearModel       = Integer.parseInt(txtYearModel.getText().trim());
            seatingCapacity = Integer.parseInt(txtSeatingCapacity.getText().trim());
            rentalRate      = Integer.parseInt(txtRentalRate.getText().trim());
            mileage         = Integer.parseInt(txtMileage.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Year Model, Seating Capacity, Rental Rate, and Mileage must be numbers.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO Vehicle " +
            "(vehicle_type, brand, model, year_model, license_plate_number, " +
            "vehicle_registration_number, seating_capacity, rental_rate, " +
            "fuel_type, transmission, mileage) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, (String) cmbVehicleType.getSelectedItem());
            ps.setString(2, txtBrand.getText().trim());
            ps.setString(3, txtModel.getText().trim());
            ps.setInt(4, yearModel);
            ps.setString(5, txtLicensePlate.getText().trim());
            ps.setString(6, txtRegistrationNumber.getText().trim());
            ps.setInt(7, seatingCapacity);
            ps.setInt(8, rentalRate);
            ps.setString(9, (String) cmbFuelType.getSelectedItem());
            ps.setString(10, (String) cmbTransmission.getSelectedItem());
            ps.setInt(11, mileage);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Vehicle added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                if (parentFrame != null) {
                    parentFrame.refresh();
                    parentFrame.setVisible(true);
                }
                dispose();
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this,
                "License plate or registration number already exists.",
                "Duplicate Entry",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Database error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}