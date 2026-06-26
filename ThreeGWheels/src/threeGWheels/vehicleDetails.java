package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class vehicleDetails extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color WHITE = Color.WHITE;

    private final int vehicleID;
    private final String vehicleType, vehicleBrand, vehicleModel, vehicleYear;
    private final String licensePlate, registrationNumber, fuelType, transmission;
    private final int seatingCapacity, rentalRate, mileage;
    private final vehicleParent mainFrame;

    public vehicleDetails(vehicleParent mainFrame, int vehicleID, String vehicleType,
                          String vehicleBrand, String vehicleModel, String vehicleYear,
                          String licensePlate, String registrationNumber,
                          int seatingCapacity, int rentalRate,
                          String fuelType, String transmission, int mileage) {

        this.mainFrame = mainFrame;
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.licensePlate = licensePlate;
        this.registrationNumber = registrationNumber;
        this.seatingCapacity = seatingCapacity;
        this.rentalRate = rentalRate;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.mileage = mileage;

        setTitle("3G Wheels – Vehicle Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 150, 420, 530);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel headerBanner = new JPanel(new BorderLayout());
        headerBanner.setBackground(DARK_BLUE);
        headerBanner.setPreferredSize(new Dimension(420, 55));

        JLabel companyName = new JLabel("🚘 3G WHEELS");
        companyName.setForeground(WHITE);
        companyName.setFont(new Font("Dialog", Font.BOLD, 20));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        headerBanner.add(companyName, BorderLayout.CENTER);
        contentPane.add(headerBanner, BorderLayout.NORTH);

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(MID_BLUE);
        toolbar.setBorder(new EmptyBorder(7, 14, 7, 14));

        JLabel pageTitle = new JLabel("VEHICLE DETAILS  –  ID " + vehicleID);
        pageTitle.setForeground(WHITE);
        pageTitle.setFont(new Font("Dialog", Font.BOLD, 12));
        toolbar.add(pageTitle, BorderLayout.WEST);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);
        centerArea.add(toolbar, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(null);
        detailsPanel.setBackground(WHITE);
        detailsPanel.setBorder(new EmptyBorder(10, 14, 10, 14));

        int labelX = 14;
        int valueX = 210;
        int rowH = 26;
        int startY = 10;
        int gap = 4;

        addDetailRow(detailsPanel, "VEHICLE ID", String.valueOf(vehicleID), labelX, valueX, startY);
        addDetailRow(detailsPanel, "TYPE", vehicleType.toUpperCase(), labelX, valueX, startY + (rowH + gap));
        addDetailRow(detailsPanel, "BRAND", vehicleBrand.toUpperCase(), labelX, valueX, startY + (rowH + gap) * 2);
        addDetailRow(detailsPanel, "MODEL", vehicleModel.toUpperCase(), labelX, valueX, startY + (rowH + gap) * 3);
        addDetailRow(detailsPanel, "YEAR MODEL", vehicleYear, labelX, valueX, startY + (rowH + gap) * 4);
        addDetailRow(detailsPanel, "LICENSE PLATE", licensePlate.toUpperCase(), labelX, valueX, startY + (rowH + gap) * 5);
        addDetailRow(detailsPanel, "REGISTRATION NUMBER", registrationNumber.toUpperCase(), labelX, valueX, startY + (rowH + gap) * 6);
        addDetailRow(detailsPanel, "SEATING CAPACITY", String.valueOf(seatingCapacity), labelX, valueX, startY + (rowH + gap) * 7);
        addDetailRow(detailsPanel, "RENTAL RATE (PHP/DAY)", String.valueOf(rentalRate), labelX, valueX, startY + (rowH + gap) * 8);
        addDetailRow(detailsPanel, "FUEL TYPE", fuelType.toUpperCase(), labelX, valueX, startY + (rowH + gap) * 9);
        addDetailRow(detailsPanel, "TRANSMISSION", transmission.toUpperCase(), labelX, valueX, startY + (rowH + gap) * 10);
        addDetailRow(detailsPanel, "MILEAGE (KM)", String.valueOf(mileage), labelX, valueX, startY + (rowH + gap) * 11);

        centerArea.add(detailsPanel, BorderLayout.CENTER);
        contentPane.add(centerArea, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        footer.setBackground(new Color(235, 238, 248));
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));

        JButton closeButton = new JButton("CLOSE");
        closeButton.setBackground(WHITE);
        closeButton.setForeground(DARK_BLUE);
        closeButton.setFont(new Font("Dialog", Font.BOLD, 11));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(new EmptyBorder(5, 14, 5, 14));
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> dispose());

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBackground(MID_BLUE);
        updateButton.setForeground(WHITE);
        updateButton.setFont(new Font("Dialog", Font.BOLD, 11));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(new EmptyBorder(5, 14, 5, 14));
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(e -> {
            new updateVehicle(
                this, mainFrame, vehicleID, vehicleType, vehicleBrand, vehicleModel,
                vehicleYear, licensePlate, registrationNumber, seatingCapacity,
                rentalRate, fuelType, transmission, mileage
            ).setVisible(true);
        });

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBackground(new Color(180, 30, 30));
        deleteButton.setForeground(WHITE);
        deleteButton.setFont(new Font("Dialog", Font.BOLD, 11));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(new EmptyBorder(5, 14, 5, 14));
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e ->
            new deleteVehicle(this, mainFrame, vehicleID).confirmAndDelete()
        );

        footer.add(closeButton);
        footer.add(updateButton);
        footer.add(deleteButton);
        contentPane.add(footer, BorderLayout.SOUTH);
    }

    private void addDetailRow(JPanel panel, String fieldLabel, String fieldValue,
                              int labelX, int valueX, int y) {
        JLabel label = new JLabel(fieldLabel);
        label.setFont(new Font("Dialog", Font.BOLD, 12));
        label.setForeground(DARK_BLUE);
        label.setBounds(labelX, y, 190, 22);
        panel.add(label);

        JLabel value = new JLabel(fieldValue);
        value.setFont(new Font("Dialog", Font.PLAIN, 12));
        value.setForeground(Color.BLACK);
        value.setBounds(valueX, y, 160, 22);
        panel.add(value);
    }
}