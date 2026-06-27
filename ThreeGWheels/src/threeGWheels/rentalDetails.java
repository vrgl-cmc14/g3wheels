package threeGWheels;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class rentalDetails extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE  = new Color(10, 25, 70);
    private static final Color WHITE      = Color.WHITE;

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";
    private JLabel valRentalId = new JLabel("—");
    private JLabel valStatus = new JLabel("—");
    private JLabel valStartDate = new JLabel("—");
    private JLabel valEndDate = new JLabel("—");
    private JLabel valPickup = new JLabel("—");
    private JLabel valReturn = new JLabel("—");
    private JLabel valTotalCost = new JLabel("—");
    private JLabel valVehicleId = new JLabel("—");
    private JLabel valVehicleType = new JLabel("—");
    private JLabel valBrand = new JLabel("—");
    private JLabel valModel = new JLabel("—");
    private JLabel valYear = new JLabel("—");
    private JLabel valPlate = new JLabel("—");
    private JLabel valRegNum = new JLabel("—");
    private JLabel valSeating = new JLabel("—");
    private JLabel valRate = new JLabel("—");
    private JLabel valFuel = new JLabel("—");
    private JLabel valTransmission = new JLabel("—");
    private JLabel valMileage = new JLabel("—");
    private JLabel valFullName = new JLabel("—");
    private JLabel valUsername = new JLabel("—");
    private JLabel valEmail = new JLabel("—");
    private JLabel valPhone = new JLabel("—");
    private JLabel valPaymentId = new JLabel("—");
    private JLabel valDatetime = new JLabel("—");
    private JLabel valMethod = new JLabel("—");
    private JLabel valRefCode = new JLabel("—");
    private JLabel valAmountPaid = new JLabel("—");

    private rentalParent mainFrame;

    public rentalDetails(int rentalId, int customerId, int vehicleId,
                         String startDate, String endDate,
                         String pickup, String returnLoc,
                         String status, int totalCost,
                         rentalParent parent) {

        this.mainFrame = parent;

        setTitle("3G Wheels - Rental Details");
        setBounds(200, 150, 420, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(620, 70));

        JLabel titleLbl = new JLabel("RENTAL DETAILS - ID #" + rentalId);
        titleLbl.setForeground(WHITE);
        titleLbl.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(titleLbl, BorderLayout.CENTER);
        contentPane.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(WHITE);

        mainPanel.add(Box.createVerticalStrut(30));

        JPanel rentalHeader = new JPanel(new BorderLayout());
        rentalHeader.setBackground(DARK_BLUE);
        rentalHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        JLabel rentalHeaderLbl = new JLabel("RENTAL INFORMATION");
        rentalHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        rentalHeaderLbl.setForeground(WHITE);
        rentalHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        rentalHeader.add(rentalHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(rentalHeader);

        JPanel rentalGrid = new JPanel(new GridLayout(7, 2, 0, 0));
        rentalGrid.setBackground(WHITE);
        rentalGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 196));

        JPanel lblRentalIdPane = new JPanel(new BorderLayout()); lblRentalIdPane.setBackground(WHITE); lblRentalIdPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblRentalId = new JLabel("Rental ID:"); lblRentalId.setFont(new Font("Dialog", Font.BOLD, 12)); lblRentalId.setForeground(DARK_BLUE);
        lblRentalIdPane.add(lblRentalId, BorderLayout.WEST); rentalGrid.add(lblRentalIdPane);
        JPanel valRentalIdPane = new JPanel(new BorderLayout()); valRentalIdPane.setBackground(WHITE); valRentalIdPane.setBorder(new EmptyBorder(6,8,6,16));
        valRentalId.setFont(new Font("Dialog", Font.PLAIN, 12)); valRentalIdPane.add(valRentalId, BorderLayout.WEST); rentalGrid.add(valRentalIdPane);

        JPanel lblStatusPane = new JPanel(new BorderLayout()); lblStatusPane.setBackground(new Color(240,243,252)); lblStatusPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblStatus = new JLabel("Status:"); lblStatus.setFont(new Font("Dialog", Font.BOLD, 12)); lblStatus.setForeground(DARK_BLUE);
        lblStatusPane.add(lblStatus, BorderLayout.WEST); rentalGrid.add(lblStatusPane);
        JPanel valStatusPane = new JPanel(new BorderLayout()); valStatusPane.setBackground(new Color(240,243,252)); valStatusPane.setBorder(new EmptyBorder(6,8,6,16));
        valStatus.setFont(new Font("Dialog", Font.PLAIN, 12)); valStatusPane.add(valStatus, BorderLayout.WEST); rentalGrid.add(valStatusPane);

        JPanel lblStartPane = new JPanel(new BorderLayout()); lblStartPane.setBackground(WHITE); lblStartPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblStart = new JLabel("Start Date:"); lblStart.setFont(new Font("Dialog", Font.BOLD, 12)); lblStart.setForeground(DARK_BLUE);
        lblStartPane.add(lblStart, BorderLayout.WEST); rentalGrid.add(lblStartPane);
        JPanel valStartPane = new JPanel(new BorderLayout()); valStartPane.setBackground(WHITE); valStartPane.setBorder(new EmptyBorder(6,8,6,16));
        valStartDate.setFont(new Font("Dialog", Font.PLAIN, 12)); valStartPane.add(valStartDate, BorderLayout.WEST); rentalGrid.add(valStartPane);

        JPanel lblEndPane = new JPanel(new BorderLayout()); lblEndPane.setBackground(new Color(240,243,252)); lblEndPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblEnd = new JLabel("End Date:"); lblEnd.setFont(new Font("Dialog", Font.BOLD, 12)); lblEnd.setForeground(DARK_BLUE);
        lblEndPane.add(lblEnd, BorderLayout.WEST); rentalGrid.add(lblEndPane);
        JPanel valEndPane = new JPanel(new BorderLayout()); valEndPane.setBackground(new Color(240,243,252)); valEndPane.setBorder(new EmptyBorder(6,8,6,16));
        valEndDate.setFont(new Font("Dialog", Font.PLAIN, 12)); valEndPane.add(valEndDate, BorderLayout.WEST); rentalGrid.add(valEndPane);

        JPanel lblPickupPane = new JPanel(new BorderLayout()); lblPickupPane.setBackground(WHITE); lblPickupPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblPickup = new JLabel("Pickup Location:"); lblPickup.setFont(new Font("Dialog", Font.BOLD, 12)); lblPickup.setForeground(DARK_BLUE);
        lblPickupPane.add(lblPickup, BorderLayout.WEST); rentalGrid.add(lblPickupPane);
        JPanel valPickupPane = new JPanel(new BorderLayout()); valPickupPane.setBackground(WHITE); valPickupPane.setBorder(new EmptyBorder(6,8,6,16));
        valPickup.setFont(new Font("Dialog", Font.PLAIN, 12)); valPickupPane.add(valPickup, BorderLayout.WEST); rentalGrid.add(valPickupPane);

        JPanel lblReturnPane = new JPanel(new BorderLayout()); lblReturnPane.setBackground(new Color(240,243,252)); lblReturnPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblReturn = new JLabel("Return Location:"); lblReturn.setFont(new Font("Dialog", Font.BOLD, 12)); lblReturn.setForeground(DARK_BLUE);
        lblReturnPane.add(lblReturn, BorderLayout.WEST); rentalGrid.add(lblReturnPane);
        JPanel valReturnPane = new JPanel(new BorderLayout()); valReturnPane.setBackground(new Color(240,243,252)); valReturnPane.setBorder(new EmptyBorder(6,8,6,16));
        valReturn.setFont(new Font("Dialog", Font.PLAIN, 12)); valReturnPane.add(valReturn, BorderLayout.WEST); rentalGrid.add(valReturnPane);

        JPanel lblCostPane = new JPanel(new BorderLayout()); lblCostPane.setBackground(WHITE); lblCostPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblCost = new JLabel("Total Cost:"); lblCost.setFont(new Font("Dialog", Font.BOLD, 12)); lblCost.setForeground(DARK_BLUE);
        lblCostPane.add(lblCost, BorderLayout.WEST); rentalGrid.add(lblCostPane);
        JPanel valCostPane = new JPanel(new BorderLayout()); valCostPane.setBackground(WHITE); valCostPane.setBorder(new EmptyBorder(6,8,6,16));
        valTotalCost.setFont(new Font("Dialog", Font.PLAIN, 12)); valCostPane.add(valTotalCost, BorderLayout.WEST); rentalGrid.add(valCostPane);

        mainPanel.add(rentalGrid);
        mainPanel.add(Box.createVerticalStrut(12)); 
        
        JPanel vehicleHeader = new JPanel(new BorderLayout());
        vehicleHeader.setBackground(DARK_BLUE);
        vehicleHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        JLabel vehicleHeaderLbl = new JLabel("VEHICLE INFORMATION");
        vehicleHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        vehicleHeaderLbl.setForeground(WHITE);
        vehicleHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        vehicleHeader.add(vehicleHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(vehicleHeader);

        JPanel vehicleGrid = new JPanel(new GridLayout(4, 2, 0, 0));
        vehicleGrid.setBackground(WHITE);
        vehicleGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 112));

        JPanel lblVIdPane = new JPanel(new BorderLayout()); lblVIdPane.setBackground(WHITE); lblVIdPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblVId = new JLabel("Vehicle ID:"); lblVId.setFont(new Font("Dialog", Font.BOLD, 12)); lblVId.setForeground(DARK_BLUE);
        lblVIdPane.add(lblVId, BorderLayout.WEST); vehicleGrid.add(lblVIdPane);
        JPanel valVIdPane = new JPanel(new BorderLayout()); valVIdPane.setBackground(WHITE); valVIdPane.setBorder(new EmptyBorder(6,8,6,16));
        valVehicleId.setFont(new Font("Dialog", Font.PLAIN, 12)); valVIdPane.add(valVehicleId, BorderLayout.WEST); vehicleGrid.add(valVIdPane);

        JPanel lblBrandPane = new JPanel(new BorderLayout()); lblBrandPane.setBackground(new Color(240,243,252)); lblBrandPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblBrand = new JLabel("Brand:"); lblBrand.setFont(new Font("Dialog", Font.BOLD, 12)); lblBrand.setForeground(DARK_BLUE);
        lblBrandPane.add(lblBrand, BorderLayout.WEST); vehicleGrid.add(lblBrandPane);
        JPanel valBrandPane = new JPanel(new BorderLayout()); valBrandPane.setBackground(new Color(240,243,252)); valBrandPane.setBorder(new EmptyBorder(6,8,6,16));
        valBrand.setFont(new Font("Dialog", Font.PLAIN, 12)); valBrandPane.add(valBrand, BorderLayout.WEST); vehicleGrid.add(valBrandPane);

        JPanel lblModelPane = new JPanel(new BorderLayout()); lblModelPane.setBackground(WHITE); lblModelPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblModel = new JLabel("Model:"); lblModel.setFont(new Font("Dialog", Font.BOLD, 12)); lblModel.setForeground(DARK_BLUE);
        lblModelPane.add(lblModel, BorderLayout.WEST); vehicleGrid.add(lblModelPane);
        JPanel valModelPane = new JPanel(new BorderLayout()); valModelPane.setBackground(WHITE); valModelPane.setBorder(new EmptyBorder(6,8,6,16));
        valModel.setFont(new Font("Dialog", Font.PLAIN, 12)); valModelPane.add(valModel, BorderLayout.WEST); vehicleGrid.add(valModelPane);
        
        JPanel lblPlatePane = new JPanel(new BorderLayout()); lblPlatePane.setBackground(new Color(240,243,252)); lblPlatePane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblPlate = new JLabel("License Plate:"); lblPlate.setFont(new Font("Dialog", Font.BOLD, 12)); lblPlate.setForeground(DARK_BLUE);
        lblPlatePane.add(lblPlate, BorderLayout.WEST); vehicleGrid.add(lblPlatePane);
        JPanel valPlatePane = new JPanel(new BorderLayout()); valPlatePane.setBackground(new Color(240,243,252)); valPlatePane.setBorder(new EmptyBorder(6,8,6,16));
        valPlate.setFont(new Font("Dialog", Font.PLAIN, 12)); valPlatePane.add(valPlate, BorderLayout.WEST); vehicleGrid.add(valPlatePane);

        mainPanel.add(vehicleGrid);
        mainPanel.add(Box.createVerticalStrut(12)); 
        
        JPanel customerHeader = new JPanel(new BorderLayout());
        customerHeader.setBackground(DARK_BLUE);
        customerHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        JLabel customerHeaderLbl = new JLabel("CUSTOMER INFORMATION");
        customerHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        customerHeaderLbl.setForeground(WHITE);
        customerHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        customerHeader.add(customerHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(customerHeader);

        JPanel customerGrid = new JPanel(new GridLayout(4, 2, 0, 0));
        customerGrid.setBackground(WHITE);
        customerGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 112));

        JPanel lblNamePane = new JPanel(new BorderLayout()); lblNamePane.setBackground(WHITE); lblNamePane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblName = new JLabel("Full Name:"); lblName.setFont(new Font("Dialog", Font.BOLD, 12)); lblName.setForeground(DARK_BLUE);
        lblNamePane.add(lblName, BorderLayout.WEST); customerGrid.add(lblNamePane);
        JPanel valNamePane = new JPanel(new BorderLayout()); valNamePane.setBackground(WHITE); valNamePane.setBorder(new EmptyBorder(6,8,6,16));
        valFullName.setFont(new Font("Dialog", Font.PLAIN, 12)); valNamePane.add(valFullName, BorderLayout.WEST); customerGrid.add(valNamePane);

        JPanel lblUNamePane = new JPanel(new BorderLayout()); lblUNamePane.setBackground(new Color(240,243,252)); lblUNamePane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblUName = new JLabel("Username:"); lblUName.setFont(new Font("Dialog", Font.BOLD, 12)); lblUName.setForeground(DARK_BLUE);
        lblUNamePane.add(lblUName, BorderLayout.WEST); customerGrid.add(lblUNamePane);
        JPanel valUNamePane = new JPanel(new BorderLayout()); valUNamePane.setBackground(new Color(240,243,252)); valUNamePane.setBorder(new EmptyBorder(6,8,6,16));
        valUsername.setFont(new Font("Dialog", Font.PLAIN, 12)); valUNamePane.add(valUsername, BorderLayout.WEST); customerGrid.add(valUNamePane);

        JPanel lblEmailPane = new JPanel(new BorderLayout()); lblEmailPane.setBackground(WHITE); lblEmailPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblEmail = new JLabel("Email:"); lblEmail.setFont(new Font("Dialog", Font.BOLD, 12)); lblEmail.setForeground(DARK_BLUE);
        lblEmailPane.add(lblEmail, BorderLayout.WEST); customerGrid.add(lblEmailPane);
        JPanel valEmailPane = new JPanel(new BorderLayout()); valEmailPane.setBackground(WHITE); valEmailPane.setBorder(new EmptyBorder(6,8,6,16));
        valEmail.setFont(new Font("Dialog", Font.PLAIN, 12)); valEmailPane.add(valEmail, BorderLayout.WEST); customerGrid.add(valEmailPane);

        JPanel lblPhonePane = new JPanel(new BorderLayout()); lblPhonePane.setBackground(new Color(240,243,252)); lblPhonePane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblPhone = new JLabel("Phone Number:"); lblPhone.setFont(new Font("Dialog", Font.BOLD, 12)); lblPhone.setForeground(DARK_BLUE);
        lblPhonePane.add(lblPhone, BorderLayout.WEST); customerGrid.add(lblPhonePane);
        JPanel valPhonePane = new JPanel(new BorderLayout()); valPhonePane.setBackground(new Color(240,243,252)); valPhonePane.setBorder(new EmptyBorder(6,8,6,16));
        valPhone.setFont(new Font("Dialog", Font.PLAIN, 12)); valPhonePane.add(valPhone, BorderLayout.WEST); customerGrid.add(valPhonePane);

        mainPanel.add(customerGrid);
        mainPanel.add(Box.createVerticalStrut(12)); 
        
        JPanel paymentHeader = new JPanel(new BorderLayout());
        paymentHeader.setBackground(DARK_BLUE);
        paymentHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        JLabel paymentHeaderLbl = new JLabel("PAYMENT INFORMATION");
        paymentHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        paymentHeaderLbl.setForeground(WHITE);
        paymentHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        paymentHeader.add(paymentHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(paymentHeader);

        JPanel paymentGrid = new JPanel(new GridLayout(5, 2, 0, 0));
        paymentGrid.setBackground(WHITE);
        paymentGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

        JPanel lblPayIdPane = new JPanel(new BorderLayout()); lblPayIdPane.setBackground(WHITE); lblPayIdPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblPayId = new JLabel("Payment ID:"); lblPayId.setFont(new Font("Dialog", Font.BOLD, 12)); lblPayId.setForeground(DARK_BLUE);
        lblPayIdPane.add(lblPayId, BorderLayout.WEST); paymentGrid.add(lblPayIdPane);
        JPanel valPayIdPane = new JPanel(new BorderLayout()); valPayIdPane.setBackground(WHITE); valPayIdPane.setBorder(new EmptyBorder(6,8,6,16));
        valPaymentId.setFont(new Font("Dialog", Font.PLAIN, 12)); valPayIdPane.add(valPaymentId, BorderLayout.WEST); paymentGrid.add(valPayIdPane);

        JPanel lblDtPane = new JPanel(new BorderLayout()); lblDtPane.setBackground(new Color(240,243,252)); lblDtPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblDt = new JLabel("Date & Time:"); lblDt.setFont(new Font("Dialog", Font.BOLD, 12)); lblDt.setForeground(DARK_BLUE);
        lblDtPane.add(lblDt, BorderLayout.WEST); paymentGrid.add(lblDtPane);
        JPanel valDtPane = new JPanel(new BorderLayout()); valDtPane.setBackground(new Color(240,243,252)); valDtPane.setBorder(new EmptyBorder(6,8,6,16));
        valDatetime.setFont(new Font("Dialog", Font.PLAIN, 12)); valDtPane.add(valDatetime, BorderLayout.WEST); paymentGrid.add(valDtPane);

        JPanel lblMethPane = new JPanel(new BorderLayout()); lblMethPane.setBackground(WHITE); lblMethPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblMeth = new JLabel("Payment Method:"); lblMeth.setFont(new Font("Dialog", Font.BOLD, 12)); lblMeth.setForeground(DARK_BLUE);
        lblMethPane.add(lblMeth, BorderLayout.WEST); paymentGrid.add(lblMethPane);
        JPanel valMethPane = new JPanel(new BorderLayout()); valMethPane.setBackground(WHITE); valMethPane.setBorder(new EmptyBorder(6,8,6,16));
        valMethod.setFont(new Font("Dialog", Font.PLAIN, 12)); valMethPane.add(valMethod, BorderLayout.WEST); paymentGrid.add(valMethPane);

        JPanel lblRefPane = new JPanel(new BorderLayout()); lblRefPane.setBackground(new Color(240,243,252)); lblRefPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblRef = new JLabel("Reference Code:"); lblRef.setFont(new Font("Dialog", Font.BOLD, 12)); lblRef.setForeground(DARK_BLUE);
        lblRefPane.add(lblRef, BorderLayout.WEST); paymentGrid.add(lblRefPane);
        JPanel valRefPane = new JPanel(new BorderLayout()); valRefPane.setBackground(new Color(240,243,252)); valRefPane.setBorder(new EmptyBorder(6,8,6,16));
        valRefCode.setFont(new Font("Dialog", Font.PLAIN, 12)); valRefPane.add(valRefCode, BorderLayout.WEST); paymentGrid.add(valRefPane);

        JPanel lblAmtPane = new JPanel(new BorderLayout()); lblAmtPane.setBackground(WHITE); lblAmtPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblAmt = new JLabel("Amount Paid:"); lblAmt.setFont(new Font("Dialog", Font.BOLD, 12)); lblAmt.setForeground(DARK_BLUE);
        lblAmtPane.add(lblAmt, BorderLayout.WEST); paymentGrid.add(lblAmtPane);
        JPanel valAmtPane = new JPanel(new BorderLayout()); valAmtPane.setBackground(WHITE); valAmtPane.setBorder(new EmptyBorder(6,8,6,16));
        valAmountPaid.setFont(new Font("Dialog", Font.PLAIN, 12)); valAmtPane.add(valAmountPaid, BorderLayout.WEST); paymentGrid.add(valAmtPane);

        mainPanel.add(paymentGrid);
        mainPanel.add(Box.createVerticalStrut(16));

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        contentPane.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        footer.setBackground(new Color(235, 238, 248));
        footer.setBorder(new MatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));

        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dispose());
        footer.add(closeBtn);

        JButton updateBtn = new JButton("UPDATE STATUS");
        updateBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        updateBtn.setFocusPainted(false);
        updateBtn.addActionListener(e -> {
            updateRental dialog = new updateRental(
                    rentalDetails.this, mainFrame, rentalId, status);
            dialog.setLocationRelativeTo(rentalDetails.this);
            dialog.setVisible(true);
        });
        footer.add(updateBtn);

        JButton deleteBtn = new JButton("DELETE");
        deleteBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        deleteBtn.setFocusPainted(false);
        deleteBtn.addActionListener(e -> {
            deleteRental dr = new deleteRental(rentalDetails.this, mainFrame, rentalId);
            dr.confirmAndDelete();
        });
        footer.add(deleteBtn);

        contentPane.add(footer, BorderLayout.SOUTH);

        populateRentalFields(rentalId, startDate, endDate, pickup, returnLoc, status, totalCost);
        populateVehicleFields(vehicleId);
        populateCustomerFields(customerId);
        populatePaymentFields(rentalId);
    }

    private void populateRentalFields(int rentalId, String startDate, String endDate,
                                      String pickup, String returnLoc, String status, int totalCost) {
        valRentalId.setText(String.valueOf(rentalId));
        valStatus.setText(status != null ? status : "N/A");
        valStartDate.setText(startDate != null ? startDate : "N/A");
        valEndDate.setText(endDate != null ? endDate : "N/A");
        valPickup.setText(pickup != null ? pickup : "N/A");
        valReturn.setText(returnLoc != null ? returnLoc : "N/A");
        valTotalCost.setText("" + totalCost);
    }

    private void populateVehicleFields(int vehicleId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Vehicle WHERE vehicle_id = ?")) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valVehicleId.setText(String.valueOf(rs.getInt("vehicle_id")));
                valVehicleType.setText(rs.getString("vehicle_type"));
                valBrand.setText(rs.getString("brand"));
                valModel.setText(rs.getString("model"));
                valYear.setText(rs.getString("year_model"));
                valPlate.setText(rs.getString("license_plate_number"));
                valRegNum.setText(rs.getString("vehicle_registration_number"));
                valSeating.setText(String.valueOf(rs.getInt("seating_capacity")));
                valRate.setText("" + rs.getInt("rental_rate"));
                valFuel.setText(rs.getString("fuel_type"));
                valTransmission.setText(rs.getString("transmission"));
                valMileage.setText(rs.getInt("mileage") + " km");
            } else { valVehicleId.setText("Not found"); }
            rs.close();
        } catch (Exception ex) { valVehicleId.setText("Error"); ex.printStackTrace(); }
    }

    private void populateCustomerFields(int customerId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(
                "SELECT first_name, middle_name, last_name, suffix, username, email_address, phone_number FROM Customer WHERE customer_id = ?")) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String sfx = rs.getString("suffix");
                valFullName.setText(rs.getString("first_name") + " " + rs.getString("middle_name")
                        + " " + rs.getString("last_name")
                        + (sfx != null && !sfx.isEmpty() ? " " + sfx : ""));
                valUsername.setText(rs.getString("username"));
                valEmail.setText(rs.getString("email_address"));
                valPhone.setText(rs.getString("phone_number"));
            } else { valFullName.setText("Not found"); }
            rs.close();
        } catch (Exception ex) { valFullName.setText("Error"); ex.printStackTrace(); }
    }

    private void populatePaymentFields(int rentalId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Payment WHERE rental_id = ?")) {
            ps.setInt(1, rentalId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valPaymentId.setText(String.valueOf(rs.getInt("payment_id")));
                valDatetime.setText(rs.getString("payment_datetime"));
                valMethod.setText(rs.getString("payment_method"));
                valRefCode.setText(rs.getString("reference_code"));
                valAmountPaid.setText("" + rs.getInt("amount_paid"));
            } else {
                valPaymentId.setText("No payment record found.");
            }
            rs.close();
        } catch (Exception ex) { valPaymentId.setText("Error"); ex.printStackTrace(); }
    }
}