package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class updateVehicle extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final JFrame detailsFrame;
    private final vehicleParent mainFrame;
    private final int vehicleID;

    public updateVehicle(JFrame detailsFrame, vehicleParent mainFrame, int vehicleID,
                         String vehicleType, String vehicleBrand, String vehicleModel,
                         String vehicleYear, String licensePlate, String registrationNumber,
                         int seatingCapacity, int rentalRate,
                         String fuelType, String transmission, int mileage) {

        super(detailsFrame, "UPDATE VEHICLE \u2013 ID " + vehicleID, true);
        this.detailsFrame = detailsFrame;
        this.mainFrame = mainFrame;
        this.vehicleID = vehicleID;

        setBounds(250, 180, 420, 560);
        setResizable(false);

        JPanel dialogPane = new JPanel(new BorderLayout());
        dialogPane.setBackground(WHITE);
        setContentPane(dialogPane);

        JPanel dialogHeader = new JPanel(new BorderLayout());
        dialogHeader.setBackground(DARK_BLUE);
        dialogHeader.setPreferredSize(new Dimension(420, 45));
        dialogPane.add(dialogHeader, BorderLayout.NORTH);

        JLabel dialogTitle = new JLabel("UPDATE VEHICLE  \u2013  ID " + vehicleID);
        dialogTitle.setForeground(WHITE);
        dialogTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        dialogTitle.setHorizontalAlignment(SwingConstants.CENTER);
        dialogHeader.add(dialogTitle, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(WHITE);
        formPanel.setBorder(new EmptyBorder(12, 14, 12, 14));
        dialogPane.add(formPanel, BorderLayout.CENTER);

        JLabel typeLabel = new JLabel("VEHICLE TYPE *");
        typeLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        typeLabel.setForeground(DARK_BLUE);
        typeLabel.setBounds(14, 4, 180, 18);
        formPanel.add(typeLabel);

        JComboBox<String> typeField = new JComboBox<>(new String[]{"SUV", "Sedan", "Hatchback", "Pickup", "Coupe"});
        typeField.setSelectedItem("SUV");
        typeField.setBounds(200, 0, 185, 26);
        formPanel.add(typeField);

        JLabel brandLabel = new JLabel("BRAND *");
        brandLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        brandLabel.setForeground(DARK_BLUE);
        brandLabel.setBounds(14, 40, 180, 18);
        formPanel.add(brandLabel);

        JTextField brandField = new JTextField("Brand");
        brandField.setBounds(200, 36, 185, 26);
        formPanel.add(brandField);

        JLabel modelLabel = new JLabel("MODEL *");
        modelLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        modelLabel.setForeground(DARK_BLUE);
        modelLabel.setBounds(14, 76, 180, 18);
        formPanel.add(modelLabel);

        JTextField modelField = new JTextField("Model");
        modelField.setBounds(200, 72, 185, 26);
        formPanel.add(modelField);

        JLabel yearLabel = new JLabel("YEAR MODEL *");
        yearLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        yearLabel.setForeground(DARK_BLUE);
        yearLabel.setBounds(14, 112, 180, 18);
        formPanel.add(yearLabel);

        JTextField yearField = new JTextField("Year");
        yearField.setBounds(200, 108, 185, 26);
        formPanel.add(yearField);

        JLabel plateLabel = new JLabel("LICENSE PLATE *");
        plateLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        plateLabel.setForeground(DARK_BLUE);
        plateLabel.setBounds(14, 148, 180, 18);
        formPanel.add(plateLabel);

        JTextField plateField = new JTextField("License Plate");
        plateField.setBounds(200, 144, 185, 26);
        formPanel.add(plateField);

        JLabel regLabel = new JLabel("REG. NUMBER *");
        regLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        regLabel.setForeground(DARK_BLUE);
        regLabel.setBounds(14, 184, 180, 18);
        formPanel.add(regLabel);

        JTextField regField = new JTextField("Reg. Number");
        regField.setBounds(200, 180, 185, 26);
        formPanel.add(regField);

        JLabel seatLabel = new JLabel("SEATING CAPACITY *");
        seatLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        seatLabel.setForeground(DARK_BLUE);
        seatLabel.setBounds(14, 220, 180, 18);
        formPanel.add(seatLabel);

        JTextField seatField = new JTextField("Seating Capacity");
        seatField.setBounds(200, 216, 185, 26);
        formPanel.add(seatField);

        JLabel rateLabel = new JLabel("RENTAL RATE *");
        rateLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        rateLabel.setForeground(DARK_BLUE);
        rateLabel.setBounds(14, 256, 180, 18);
        formPanel.add(rateLabel);

        JTextField rateField = new JTextField("Rental Rate");
        rateField.setBounds(200, 252, 185, 26);
        formPanel.add(rateField);

        JLabel fuelLabel = new JLabel("FUEL TYPE *");
        fuelLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        fuelLabel.setForeground(DARK_BLUE);
        fuelLabel.setBounds(14, 292, 180, 18);
        formPanel.add(fuelLabel);

        JComboBox<String> fuelField = new JComboBox<>(new String[]{"Gas", "Diesel", "Hybrid", "Electric"});
        fuelField.setSelectedItem("Gas");
        fuelField.setBounds(200, 288, 185, 26);
        formPanel.add(fuelField);

        JLabel transLabel = new JLabel("TRANSMISSION *");
        transLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        transLabel.setForeground(DARK_BLUE);
        transLabel.setBounds(14, 328, 180, 18);
        formPanel.add(transLabel);

        JComboBox<String> transField = new JComboBox<>(new String[]{"Automatic", "Manual"});
        transField.setSelectedItem("Automatic");
        transField.setBounds(200, 324, 185, 26);
        formPanel.add(transField);

        JLabel mileageLabel = new JLabel("MILEAGE (KM) *");
        mileageLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        mileageLabel.setForeground(DARK_BLUE);
        mileageLabel.setBounds(14, 364, 180, 18);
        formPanel.add(mileageLabel);

        JTextField mileageField = new JTextField("Mileage");
        mileageField.setBounds(200, 360, 185, 26);
        formPanel.add(mileageField);

        typeField.setSelectedItem(vehicleType);
        brandField.setText(vehicleBrand);
        modelField.setText(vehicleModel);
        yearField.setText(vehicleYear);
        plateField.setText(licensePlate);
        regField.setText(registrationNumber);
        seatField.setText(String.valueOf(seatingCapacity));
        rateField.setText(String.valueOf(rentalRate));
        fuelField.setSelectedItem(fuelType);
        transField.setSelectedItem(transmission);
        mileageField.setText(String.valueOf(mileage));

        JPanel dialogFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        dialogFooter.setBackground(new Color(235, 238, 248));
        dialogFooter.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        dialogPane.add(dialogFooter, BorderLayout.SOUTH);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setBackground(WHITE);
        cancelButton.setForeground(DARK_BLUE);
        cancelButton.setFont(new Font("Dialog", Font.BOLD, 11));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(new EmptyBorder(5, 14, 5, 14));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> dispose());
        dialogFooter.add(cancelButton);

        JButton saveButton = new JButton("SAVE CHANGES");
        saveButton.setBackground(DARK_BLUE);
        saveButton.setForeground(WHITE);
        saveButton.setFont(new Font("Dialog", Font.BOLD, 11));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(new EmptyBorder(5, 14, 5, 14));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> saveUpdate(
            (String) typeField.getSelectedItem(),
            brandField.getText().trim(),
            modelField.getText().trim(),
            yearField.getText().trim(),
            plateField.getText().trim(),
            regField.getText().trim(),
            seatField.getText().trim(),
            rateField.getText().trim(),
            (String) fuelField.getSelectedItem(),
            (String) transField.getSelectedItem(),
            mileageField.getText().trim()
        ));
        dialogFooter.add(saveButton);
    }

    private void saveUpdate(String newType, String newBrand, String newModel,
                            String newYear, String newPlate, String newReg,
                            String newSeat, String newRate, String newFuel,
                            String newTrans, String newMileage) {

        if (newBrand.isEmpty() || newModel.isEmpty() || newYear.isEmpty() ||
            newPlate.isEmpty() || newReg.isEmpty() || newSeat.isEmpty() ||
            newRate.isEmpty() || newMileage.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int parsedYear, parsedSeat, parsedRate, parsedMileage;
        try {
            parsedYear = Integer.parseInt(newYear);
            parsedSeat = Integer.parseInt(newSeat);
            parsedRate = Integer.parseInt(newRate);
            parsedMileage = Integer.parseInt(newMileage);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Year Model, Seating Capacity, Rental Rate, and Mileage must be numbers.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE Vehicle SET vehicle_type=?, brand=?, model=?, year_model=?, " +
            "license_plate_number=?, vehicle_registration_number=?, seating_capacity=?, " +
            "rental_rate=?, fuel_type=?, transmission=?, mileage=? WHERE vehicle_id=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newType);
            ps.setString(2, newBrand);
            ps.setString(3, newModel);
            ps.setInt(4, parsedYear);
            ps.setString(5, newPlate);
            ps.setString(6, newReg);
            ps.setInt(7, parsedSeat);
            ps.setInt(8, parsedRate);
            ps.setString(9, newFuel);
            ps.setString(10, newTrans);
            ps.setInt(11, parsedMileage);
            ps.setInt(12, vehicleID);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Vehicle updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose();
            detailsFrame.dispose();
            if (mainFrame != null) mainFrame.refresh();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}