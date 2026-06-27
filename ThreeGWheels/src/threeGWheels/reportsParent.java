package threeGWheels;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.*;

public class reportsParent extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_ROW = new Color(240, 243, 252);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private Connection database;

    private JLabel totalVehiclesLabel = new JLabel("—");
    private JLabel sedansLabel = new JLabel("—");
    private JLabel pickupsLabel = new JLabel("—");
    private JLabel coupesLabel = new JLabel("—");

    private JLabel suvsLabel = new JLabel("—");
    private JLabel sedansTypeLabel = new JLabel("—");
    private JLabel hatchbacksLabel = new JLabel("—");
    private JLabel pickupsTypeLabel = new JLabel("—");
    private JLabel coupesTypeLabel = new JLabel("—");

    private JLabel automaticLabel = new JLabel("—");
    private JLabel manualLabel = new JLabel("—");

    private JLabel gasLabel = new JLabel("—");
    private JLabel dieselLabel = new JLabel("—");
    private JLabel hybridLabel = new JLabel("—");
    private JLabel electricLabel = new JLabel("—");

    private JLabel totalCustomersLabel = new JLabel("—");

    private JLabel allRentalsLabel = new JLabel("—");
    private JLabel rentalsThisMonthLabel = new JLabel("—");
    private JLabel rentalsNextMonthLabel = new JLabel("—");
    private JLabel rentalsLastMonthLabel = new JLabel("—");

    private JLabel confirmedLabel = new JLabel("—");
    private JLabel ongoingLabel = new JLabel("—");
    private JLabel cancelledLabel = new JLabel("—");
    private JLabel pendingLabel = new JLabel("—");
    private JLabel completedLabel = new JLabel("—");

    private JLabel totalPaymentLabel = new JLabel("—");
    private JLabel paymentThisMonthLabel = new JLabel("—");
    private JLabel paymentLastMonthLabel = new JLabel("—");
    private JLabel cashPaymentLabel = new JLabel("—");
    private JLabel cardPaymentLabel = new JLabel("—");
    private JLabel ewalletPaymentLabel = new JLabel("—");

    public reportsParent() {
        setTitle("3G Wheels - Reports & Summary");
        setName("reportsParent");
        setBounds(200, 100, 480, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(420, 500));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(480, 70));

        JLabel titleLbl = new JLabel("🚘 3G WHEELS");
        titleLbl.setForeground(WHITE);
        titleLbl.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(titleLbl, BorderLayout.CENTER);
        contentPane.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(WHITE);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel vehicleSummaryHeader = new JPanel(new BorderLayout());
        vehicleSummaryHeader.setBackground(DARK_BLUE);
        vehicleSummaryHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        
        JLabel vehicleSummaryHeaderLbl = new JLabel("VEHICLE SUMMARY");
        vehicleSummaryHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        vehicleSummaryHeaderLbl.setForeground(WHITE);
        vehicleSummaryHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        vehicleSummaryHeader.add(vehicleSummaryHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(vehicleSummaryHeader);

        JPanel vehicleSummaryGrid = new JPanel(new GridLayout(1, 2, 0, 0));
        vehicleSummaryGrid.setBackground(WHITE);
        vehicleSummaryGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        JPanel lblTotalPane = new JPanel(new BorderLayout()); 
        lblTotalPane.setBackground(WHITE); 
        lblTotalPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblTotal = new JLabel("Total Vehicles:"); 
        lblTotal.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblTotal.setForeground(DARK_BLUE);
        lblTotalPane.add(lblTotal, BorderLayout.WEST); 
        vehicleSummaryGrid.add(lblTotalPane);
        
        JPanel valTotalPane = new JPanel(new BorderLayout()); 
        valTotalPane.setBackground(WHITE); 
        valTotalPane.setBorder(new EmptyBorder(6,8,6,16));
        totalVehiclesLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valTotalPane.add(totalVehiclesLabel, BorderLayout.WEST); 
        vehicleSummaryGrid.add(valTotalPane);

        mainPanel.add(vehicleSummaryGrid);
        mainPanel.add(Box.createVerticalStrut(5));
        
        
        JPanel vehicleTypeHeader = new JPanel(new BorderLayout());
        vehicleTypeHeader.setBackground(DARK_BLUE);
        vehicleTypeHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        
        JLabel vehicleTypeHeaderLbl = new JLabel("VEHICLE TYPE BREAKDOWN");
        vehicleTypeHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        vehicleTypeHeaderLbl.setForeground(WHITE);
        vehicleTypeHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        vehicleTypeHeader.add(vehicleTypeHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(vehicleTypeHeader);

        JPanel vehicleTypeGrid = new JPanel(new GridLayout(5, 2, 0, 0));
        vehicleTypeGrid.setBackground(WHITE);
        vehicleTypeGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

        JPanel lblSuvPane = new JPanel(new BorderLayout()); 
        lblSuvPane.setBackground(WHITE); 
        lblSuvPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblSuv = new JLabel("SUVs:"); 
        lblSuv.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblSuv.setForeground(DARK_BLUE);
        lblSuvPane.add(lblSuv, BorderLayout.WEST); 
        vehicleTypeGrid.add(lblSuvPane);
        
        JPanel valSuvPane = new JPanel(new BorderLayout()); 
        valSuvPane.setBackground(WHITE); 
        valSuvPane.setBorder(new EmptyBorder(6,8,6,16));
        suvsLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valSuvPane.add(suvsLabel, BorderLayout.WEST); 
        vehicleTypeGrid.add(valSuvPane);

        JPanel lblSedanTypePane = new JPanel(new BorderLayout()); 
        lblSedanTypePane.setBackground(LIGHT_ROW); 
        lblSedanTypePane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblSedanType = new JLabel("Sedans:"); 
        lblSedanType.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblSedanType.setForeground(DARK_BLUE);
        lblSedanTypePane.add(lblSedanType, BorderLayout.WEST); 
        vehicleTypeGrid.add(lblSedanTypePane);
        
        JPanel valSedanTypePane = new JPanel(new BorderLayout()); 
        valSedanTypePane.setBackground(LIGHT_ROW); 
        valSedanTypePane.setBorder(new EmptyBorder(6,8,6,16));
        sedansTypeLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valSedanTypePane.add(sedansTypeLabel, BorderLayout.WEST); 
        vehicleTypeGrid.add(valSedanTypePane);

        JPanel lblHatchPane = new JPanel(new BorderLayout()); 
        lblHatchPane.setBackground(WHITE); 
        lblHatchPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblHatch = new JLabel("Hatchbacks:"); 
        lblHatch.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblHatch.setForeground(DARK_BLUE);
        lblHatchPane.add(lblHatch, BorderLayout.WEST); 
        vehicleTypeGrid.add(lblHatchPane);
        
        JPanel valHatchPane = new JPanel(new BorderLayout()); 
        valHatchPane.setBackground(WHITE); 
        valHatchPane.setBorder(new EmptyBorder(6,8,6,16));
        hatchbacksLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valHatchPane.add(hatchbacksLabel, BorderLayout.WEST); 
        vehicleTypeGrid.add(valHatchPane);

        JPanel lblPickupTypePane = new JPanel(new BorderLayout()); 
        lblPickupTypePane.setBackground(LIGHT_ROW); 
        lblPickupTypePane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblPickupType = new JLabel("Pickups:"); 
        lblPickupType.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblPickupType.setForeground(DARK_BLUE);
        lblPickupTypePane.add(lblPickupType, BorderLayout.WEST); 
        vehicleTypeGrid.add(lblPickupTypePane);
        
        JPanel valPickupTypePane = new JPanel(new BorderLayout()); 
        valPickupTypePane.setBackground(LIGHT_ROW); 
        valPickupTypePane.setBorder(new EmptyBorder(6,8,6,16));
        pickupsTypeLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valPickupTypePane.add(pickupsTypeLabel, BorderLayout.WEST); 
        vehicleTypeGrid.add(valPickupTypePane);

        JPanel lblCoupeTypePane = new JPanel(new BorderLayout()); 
        lblCoupeTypePane.setBackground(WHITE); 
        lblCoupeTypePane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblCoupeType = new JLabel("Coupes:"); 
        lblCoupeType.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblCoupeType.setForeground(DARK_BLUE);
        lblCoupeTypePane.add(lblCoupeType, BorderLayout.WEST); 
        vehicleTypeGrid.add(lblCoupeTypePane);
        
        JPanel valCoupeTypePane = new JPanel(new BorderLayout()); 
        valCoupeTypePane.setBackground(WHITE); 
        valCoupeTypePane.setBorder(new EmptyBorder(6,8,6,16));
        coupesTypeLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valCoupeTypePane.add(coupesTypeLabel, BorderLayout.WEST); 
        vehicleTypeGrid.add(valCoupeTypePane);

        mainPanel.add(vehicleTypeGrid);
        mainPanel.add(Box.createVerticalStrut(12));

        
        JPanel transmissionHeader = new JPanel(new BorderLayout());
        transmissionHeader.setBackground(DARK_BLUE);
        transmissionHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        
        JLabel transmissionHeaderLbl = new JLabel("TRANSMISSION TYPES");
        transmissionHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        transmissionHeaderLbl.setForeground(WHITE);
        transmissionHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        transmissionHeader.add(transmissionHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(transmissionHeader);

        JPanel transmissionGrid = new JPanel(new GridLayout(2, 2, 0, 0));
        transmissionGrid.setBackground(WHITE);
        transmissionGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));

        JPanel lblAutoPane = new JPanel(new BorderLayout()); 
        lblAutoPane.setBackground(WHITE); 
        lblAutoPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblAuto = new JLabel("Automatic:"); 
        lblAuto.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblAuto.setForeground(DARK_BLUE);
        lblAutoPane.add(lblAuto, BorderLayout.WEST); 
        transmissionGrid.add(lblAutoPane);
        
        JPanel valAutoPane = new JPanel(new BorderLayout()); 
        valAutoPane.setBackground(WHITE); 
        valAutoPane.setBorder(new EmptyBorder(6,8,6,16));
        automaticLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valAutoPane.add(automaticLabel, BorderLayout.WEST); 
        transmissionGrid.add(valAutoPane);

        JPanel lblManualPane = new JPanel(new BorderLayout()); 
        lblManualPane.setBackground(LIGHT_ROW); 
        lblManualPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblManual = new JLabel("Manual:"); 
        lblManual.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblManual.setForeground(DARK_BLUE);
        lblManualPane.add(lblManual, BorderLayout.WEST); 
        transmissionGrid.add(lblManualPane);
        
        JPanel valManualPane = new JPanel(new BorderLayout()); 
        valManualPane.setBackground(LIGHT_ROW); 
        valManualPane.setBorder(new EmptyBorder(6,8,6,16));
        manualLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valManualPane.add(manualLabel, BorderLayout.WEST); 
        transmissionGrid.add(valManualPane);

        mainPanel.add(transmissionGrid);
        mainPanel.add(Box.createVerticalStrut(12));

        JPanel fuelHeader = new JPanel(new BorderLayout());
        fuelHeader.setBackground(DARK_BLUE);
        fuelHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        
        JLabel fuelHeaderLbl = new JLabel("FUEL TYPES");
        fuelHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        fuelHeaderLbl.setForeground(WHITE);
        fuelHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        fuelHeader.add(fuelHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(fuelHeader);

        JPanel fuelGrid = new JPanel(new GridLayout(4, 2, 0, 0));
        fuelGrid.setBackground(WHITE);
        fuelGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 112));

        JPanel lblGasPane = new JPanel(new BorderLayout()); 
        lblGasPane.setBackground(WHITE); 
        lblGasPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblGas = new JLabel("Gas:"); 
        lblGas.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblGas.setForeground(DARK_BLUE);
        lblGasPane.add(lblGas, BorderLayout.WEST); 
        fuelGrid.add(lblGasPane);
        
        JPanel valGasPane = new JPanel(new BorderLayout()); 
        valGasPane.setBackground(WHITE); 
        valGasPane.setBorder(new EmptyBorder(6,8,6,16));
        gasLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valGasPane.add(gasLabel, BorderLayout.WEST); 
        fuelGrid.add(valGasPane);

        JPanel lblDieselPane = new JPanel(new BorderLayout()); 
        lblDieselPane.setBackground(LIGHT_ROW); 
        lblDieselPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblDiesel = new JLabel("Diesel:"); 
        lblDiesel.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblDiesel.setForeground(DARK_BLUE);
        lblDieselPane.add(lblDiesel, BorderLayout.WEST); 
        fuelGrid.add(lblDieselPane);
        
        JPanel valDieselPane = new JPanel(new BorderLayout()); 
        valDieselPane.setBackground(LIGHT_ROW); 
        valDieselPane.setBorder(new EmptyBorder(6,8,6,16));
        dieselLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valDieselPane.add(dieselLabel, BorderLayout.WEST); 
        fuelGrid.add(valDieselPane);

        JPanel lblHybridPane = new JPanel(new BorderLayout()); 
        lblHybridPane.setBackground(WHITE); 
        lblHybridPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblHybrid = new JLabel("Hybrid:"); 
        lblHybrid.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblHybrid.setForeground(DARK_BLUE);
        lblHybridPane.add(lblHybrid, BorderLayout.WEST); 
        fuelGrid.add(lblHybridPane);
        
        JPanel valHybridPane = new JPanel(new BorderLayout()); 
        valHybridPane.setBackground(WHITE); 
        valHybridPane.setBorder(new EmptyBorder(6,8,6,16));
        hybridLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valHybridPane.add(hybridLabel, BorderLayout.WEST); fuelGrid.add(valHybridPane);

        JPanel lblElectricPane = new JPanel(new BorderLayout()); 
        lblElectricPane.setBackground(LIGHT_ROW); 
        lblElectricPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblElectric = new JLabel("Electric:"); 
        lblElectric.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblElectric.setForeground(DARK_BLUE);
        lblElectricPane.add(lblElectric, BorderLayout.WEST); 
        fuelGrid.add(lblElectricPane);
        JPanel valElectricPane = new JPanel(new BorderLayout()); 
        valElectricPane.setBackground(LIGHT_ROW); 
        valElectricPane.setBorder(new EmptyBorder(6,8,6,16));
        electricLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valElectricPane.add(electricLabel, BorderLayout.WEST); fuelGrid.add(valElectricPane);

        mainPanel.add(fuelGrid);
        mainPanel.add(Box.createVerticalStrut(12));

        JPanel customerHeader = new JPanel(new BorderLayout());
        customerHeader.setBackground(DARK_BLUE);
        customerHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        JLabel customerHeaderLbl = new JLabel("CUSTOMER SUMMARY");
        customerHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        customerHeaderLbl.setForeground(WHITE);
        customerHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        customerHeader.add(customerHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(customerHeader);

        JPanel customerGrid = new JPanel(new GridLayout(1, 2, 0, 0));
        customerGrid.setBackground(WHITE);
        customerGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        JPanel lblCustomerPane = new JPanel(new BorderLayout()); 
        lblCustomerPane.setBackground(WHITE); 
        lblCustomerPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblCustomer = new JLabel("Total Customers / Users:"); 
        lblCustomer.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblCustomer.setForeground(DARK_BLUE);
        lblCustomerPane.add(lblCustomer, BorderLayout.WEST); 
        customerGrid.add(lblCustomerPane);
        
        JPanel valCustomerPane = new JPanel(new BorderLayout()); 
        valCustomerPane.setBackground(WHITE); 
        valCustomerPane.setBorder(new EmptyBorder(6,8,6,16));
        totalCustomersLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valCustomerPane.add(totalCustomersLabel, BorderLayout.WEST); 
        customerGrid.add(valCustomerPane);

        mainPanel.add(customerGrid);
        mainPanel.add(Box.createVerticalStrut(12));

        JPanel rentalHeader = new JPanel(new BorderLayout());
        rentalHeader.setBackground(DARK_BLUE);
        rentalHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        
        JLabel rentalHeaderLbl = new JLabel("RENTAL TRANSACTIONS");
        rentalHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        rentalHeaderLbl.setForeground(WHITE);
        rentalHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        rentalHeader.add(rentalHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(rentalHeader);

        JPanel rentalGrid = new JPanel(new GridLayout(4, 2, 0, 0));
        rentalGrid.setBackground(WHITE);
        rentalGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 112));

        JPanel lblAllRentalsPane = new JPanel(new BorderLayout()); 
        lblAllRentalsPane.setBackground(WHITE); 
        lblAllRentalsPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblAllRentals = new JLabel("All Rental Transactions:"); 
        lblAllRentals.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblAllRentals.setForeground(DARK_BLUE);
        lblAllRentalsPane.add(lblAllRentals, BorderLayout.WEST); 
        rentalGrid.add(lblAllRentalsPane);
        
        JPanel valAllRentalsPane = new JPanel(new BorderLayout()); 
        valAllRentalsPane.setBackground(WHITE); 
        valAllRentalsPane.setBorder(new EmptyBorder(6,8,6,16));
        allRentalsLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valAllRentalsPane.add(allRentalsLabel, BorderLayout.WEST); 
        rentalGrid.add(valAllRentalsPane);

        JPanel lblThisMonthPane = new JPanel(new BorderLayout()); 
        lblThisMonthPane.setBackground(LIGHT_ROW); 
        lblThisMonthPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblThisMonth = new JLabel("Rentals This Month:"); 
        lblThisMonth.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblThisMonth.setForeground(DARK_BLUE);
        lblThisMonthPane.add(lblThisMonth, BorderLayout.WEST); 
        rentalGrid.add(lblThisMonthPane);
        
        JPanel valThisMonthPane = new JPanel(new BorderLayout()); 
        valThisMonthPane.setBackground(LIGHT_ROW); 
        valThisMonthPane.setBorder(new EmptyBorder(6,8,6,16));
        rentalsThisMonthLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valThisMonthPane.add(rentalsThisMonthLabel, BorderLayout.WEST); 
        rentalGrid.add(valThisMonthPane);

        JPanel lblNextMonthPane = new JPanel(new BorderLayout()); 
        lblNextMonthPane.setBackground(WHITE); 
        lblNextMonthPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblNextMonth = new JLabel("Rentals Next Month:"); 
        lblNextMonth.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblNextMonth.setForeground(DARK_BLUE);
        lblNextMonthPane.add(lblNextMonth, BorderLayout.WEST); 
        rentalGrid.add(lblNextMonthPane);
        JPanel valNextMonthPane = new JPanel(new BorderLayout()); 
        valNextMonthPane.setBackground(WHITE); 
        valNextMonthPane.setBorder(new EmptyBorder(6,8,6,16));
        rentalsNextMonthLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valNextMonthPane.add(rentalsNextMonthLabel, BorderLayout.WEST); 
        rentalGrid.add(valNextMonthPane);

        JPanel lblLastMonthPane = new JPanel(new BorderLayout()); 
        lblLastMonthPane.setBackground(LIGHT_ROW); 
        lblLastMonthPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblLastMonth = new JLabel("Rentals Previous Month:"); 
        lblLastMonth.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblLastMonth.setForeground(DARK_BLUE);
        lblLastMonthPane.add(lblLastMonth, BorderLayout.WEST); 
        rentalGrid.add(lblLastMonthPane);
        
        JPanel valLastMonthPane = new JPanel(new BorderLayout()); 
        valLastMonthPane.setBackground(LIGHT_ROW); 
        valLastMonthPane.setBorder(new EmptyBorder(6,8,6,16));
        rentalsLastMonthLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valLastMonthPane.add(rentalsLastMonthLabel, BorderLayout.WEST); 
        rentalGrid.add(valLastMonthPane);

        mainPanel.add(rentalGrid);
        mainPanel.add(Box.createVerticalStrut(12));

        JPanel statusHeader = new JPanel(new BorderLayout());
        statusHeader.setBackground(DARK_BLUE);
        statusHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        
        JLabel statusHeaderLbl = new JLabel("RENTAL STATUS");
        statusHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        statusHeaderLbl.setForeground(WHITE);
        statusHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        statusHeader.add(statusHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(statusHeader);

        JPanel statusGrid = new JPanel(new GridLayout(5, 2, 0, 0));
        statusGrid.setBackground(WHITE);
        statusGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

        JPanel lblConfirmedPane = new JPanel(new BorderLayout()); 
        lblConfirmedPane.setBackground(WHITE); 
        lblConfirmedPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblConfirmed = new JLabel("Confirmed:"); 
        lblConfirmed.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblConfirmed.setForeground(DARK_BLUE);
        lblConfirmedPane.add(lblConfirmed, BorderLayout.WEST); statusGrid.add(lblConfirmedPane);
        
        JPanel valConfirmedPane = new JPanel(new BorderLayout()); 
        valConfirmedPane.setBackground(WHITE); 
        valConfirmedPane.setBorder(new EmptyBorder(6,8,6,16));
        confirmedLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valConfirmedPane.add(confirmedLabel, BorderLayout.WEST); 
        statusGrid.add(valConfirmedPane);

        JPanel lblOngoingPane = new JPanel(new BorderLayout()); 
        lblOngoingPane.setBackground(LIGHT_ROW); 
        lblOngoingPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblOngoing = new JLabel("Ongoing:"); 
        lblOngoing.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblOngoing.setForeground(DARK_BLUE);
        lblOngoingPane.add(lblOngoing, BorderLayout.WEST); 
        statusGrid.add(lblOngoingPane);
        
        JPanel valOngoingPane = new JPanel(new BorderLayout()); 
        valOngoingPane.setBackground(LIGHT_ROW); 
        valOngoingPane.setBorder(new EmptyBorder(6,8,6,16));
        ongoingLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valOngoingPane.add(ongoingLabel, BorderLayout.WEST); 
        statusGrid.add(valOngoingPane);

        JPanel lblCancelledPane = new JPanel(new BorderLayout()); 
        lblCancelledPane.setBackground(WHITE); 
        lblCancelledPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblCancelled = new JLabel("Cancelled:"); 
        lblCancelled.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblCancelled.setForeground(DARK_BLUE);
        lblCancelledPane.add(lblCancelled, BorderLayout.WEST); 
        statusGrid.add(lblCancelledPane);
        
        JPanel valCancelledPane = new JPanel(new BorderLayout()); 
        valCancelledPane.setBackground(WHITE); 
        valCancelledPane.setBorder(new EmptyBorder(6,8,6,16));
        cancelledLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valCancelledPane.add(cancelledLabel, BorderLayout.WEST); 
        statusGrid.add(valCancelledPane);

        JPanel lblPendingPane = new JPanel(new BorderLayout()); 
        lblPendingPane.setBackground(LIGHT_ROW); 
        lblPendingPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblPending = new JLabel("Pending:"); 
        lblPending.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblPending.setForeground(DARK_BLUE);
        lblPendingPane.add(lblPending, BorderLayout.WEST); 
        statusGrid.add(lblPendingPane);
        
        JPanel valPendingPane = new JPanel(new BorderLayout()); 
        valPendingPane.setBackground(LIGHT_ROW); 
        valPendingPane.setBorder(new EmptyBorder(6,8,6,16));
        pendingLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valPendingPane.add(pendingLabel, BorderLayout.WEST); 
        statusGrid.add(valPendingPane);

        JPanel lblCompletedPane = new JPanel(new BorderLayout()); 
        lblCompletedPane.setBackground(WHITE); 
        lblCompletedPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblCompleted = new JLabel("Completed:"); 
        lblCompleted.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblCompleted.setForeground(DARK_BLUE);
        lblCompletedPane.add(lblCompleted, BorderLayout.WEST); 
        statusGrid.add(lblCompletedPane);
        
        JPanel valCompletedPane = new JPanel(new BorderLayout()); 
        valCompletedPane.setBackground(WHITE); 
        valCompletedPane.setBorder(new EmptyBorder(6,8,6,16));
        completedLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valCompletedPane.add(completedLabel, BorderLayout.WEST); 
        statusGrid.add(valCompletedPane);

        mainPanel.add(statusGrid);
        mainPanel.add(Box.createVerticalStrut(12));

        JPanel paymentHeader = new JPanel(new BorderLayout());
        paymentHeader.setBackground(DARK_BLUE);
        paymentHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        
        JLabel paymentHeaderLbl = new JLabel("PAYMENT SUMMARY");
        paymentHeaderLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        paymentHeaderLbl.setForeground(WHITE);
        paymentHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
        paymentHeader.add(paymentHeaderLbl, BorderLayout.CENTER);
        mainPanel.add(paymentHeader);

        JPanel paymentGrid = new JPanel(new GridLayout(6, 2, 0, 0));
        paymentGrid.setBackground(WHITE);
        paymentGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 168));

        JPanel lblTotalPayPane = new JPanel(new BorderLayout()); 
        lblTotalPayPane.setBackground(WHITE); 
        lblTotalPayPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblTotalPay = new JLabel("Accumulated Payment (Total):"); 
        lblTotalPay.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblTotalPay.setForeground(DARK_BLUE);
        lblTotalPayPane.add(lblTotalPay, BorderLayout.WEST); 
        paymentGrid.add(lblTotalPayPane);
        
        JPanel valTotalPayPane = new JPanel(new BorderLayout()); 
        valTotalPayPane.setBackground(WHITE); 
        valTotalPayPane.setBorder(new EmptyBorder(6,8,6,16));
        totalPaymentLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valTotalPayPane.add(totalPaymentLabel, BorderLayout.WEST); 
        paymentGrid.add(valTotalPayPane);

        JPanel lblPayThisPane = new JPanel(new BorderLayout()); 
        lblPayThisPane.setBackground(LIGHT_ROW); 
        lblPayThisPane.setBorder(new EmptyBorder(6,16,6,8));
        JLabel lblPayThis = new JLabel("Payments This Month:"); 
        lblPayThis.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblPayThis.setForeground(DARK_BLUE);
        lblPayThisPane.add(lblPayThis, BorderLayout.WEST); 
        paymentGrid.add(lblPayThisPane);
        
        JPanel valPayThisPane = new JPanel(new BorderLayout()); 
        valPayThisPane.setBackground(LIGHT_ROW); 
        valPayThisPane.setBorder(new EmptyBorder(6,8,6,16));
        paymentThisMonthLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valPayThisPane.add(paymentThisMonthLabel, BorderLayout.WEST); 
        paymentGrid.add(valPayThisPane);

        JPanel lblPayLastPane = new JPanel(new BorderLayout()); 
        lblPayLastPane.setBackground(WHITE); 
        lblPayLastPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblPayLast = new JLabel("Payments Previous Month:"); 
        lblPayLast.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblPayLast.setForeground(DARK_BLUE);
        lblPayLastPane.add(lblPayLast, BorderLayout.WEST); 
        paymentGrid.add(lblPayLastPane);
        
        JPanel valPayLastPane = new JPanel(new BorderLayout()); 
        valPayLastPane.setBackground(WHITE); 
        valPayLastPane.setBorder(new EmptyBorder(6,8,6,16));
        paymentLastMonthLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valPayLastPane.add(paymentLastMonthLabel, BorderLayout.WEST); 
        paymentGrid.add(valPayLastPane);

        JPanel lblCashPane = new JPanel(new BorderLayout()); 
        lblCashPane.setBackground(LIGHT_ROW); 
        lblCashPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblCash = new JLabel("Cash Payments:"); 
        lblCash.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblCash.setForeground(DARK_BLUE);
        lblCashPane.add(lblCash, BorderLayout.WEST); 
        paymentGrid.add(lblCashPane);
        
        JPanel valCashPane = new JPanel(new BorderLayout()); 
        valCashPane.setBackground(LIGHT_ROW); 
        valCashPane.setBorder(new EmptyBorder(6,8,6,16));
        cashPaymentLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valCashPane.add(cashPaymentLabel, BorderLayout.WEST); 
        paymentGrid.add(valCashPane);

        JPanel lblCardPane = new JPanel(new BorderLayout()); 
        lblCardPane.setBackground(WHITE); 
        lblCardPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblCard = new JLabel("Card Payments:"); 
        lblCard.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblCard.setForeground(DARK_BLUE);
        lblCardPane.add(lblCard, BorderLayout.WEST); 
        paymentGrid.add(lblCardPane);
        
        JPanel valCardPane = new JPanel(new BorderLayout()); 
        valCardPane.setBackground(WHITE); 
        valCardPane.setBorder(new EmptyBorder(6,8,6,16));
        cardPaymentLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valCardPane.add(cardPaymentLabel, BorderLayout.WEST); 
        paymentGrid.add(valCardPane);

        JPanel lblEwalletPane = new JPanel(new BorderLayout()); 
        lblEwalletPane.setBackground(LIGHT_ROW); 
        lblEwalletPane.setBorder(new EmptyBorder(6,16,6,8));
        
        JLabel lblEwallet = new JLabel("E-Wallet Payments:"); 
        lblEwallet.setFont(new Font("Dialog", Font.BOLD, 12)); 
        lblEwallet.setForeground(DARK_BLUE);
        lblEwalletPane.add(lblEwallet, BorderLayout.WEST); 
        paymentGrid.add(lblEwalletPane);
        
        JPanel valEwalletPane = new JPanel(new BorderLayout()); 
        valEwalletPane.setBackground(LIGHT_ROW); 
        valEwalletPane.setBorder(new EmptyBorder(6,8,6,16));
        ewalletPaymentLabel.setFont(new Font("Dialog", Font.PLAIN, 12)); 
        valEwalletPane.add(ewalletPaymentLabel, BorderLayout.WEST); paymentGrid.add(valEwalletPane);

        mainPanel.add(paymentGrid);
        mainPanel.add(Box.createVerticalStrut(16));

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        contentPane.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        footer.setBackground(new Color(235, 238, 248));
        footer.setBorder(new MatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));

        JButton refreshBtn = new JButton("REFRESH");
        refreshBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        refreshBtn.setFocusPainted(false);
        refreshBtn.addActionListener(e -> refreshReport());
        footer.add(refreshBtn);

        JButton backBtn = new JButton("BACK");
        backBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            adminDashboard dashboard = new adminDashboard();
            dashboard.setVisible(true);
        });
        footer.add(backBtn);

        contentPane.add(footer, BorderLayout.SOUTH);

        connectToDatabase();
        refreshReport();
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Could not connect to database.\n" + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private long getNumberFromDatabase(String sql) {
        if (database == null) return 0;
        try (Statement statement = database.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            return result.next() ? result.getLong(1) : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private void refreshReport() {
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();
        int thisMonth = today.getMonthValue();
        int lastMonth = today.minusMonths(1).getMonthValue();
        int lastMonthYear = today.minusMonths(1).getYear();
        int nextMonth = today.plusMonths(1).getMonthValue();
        int nextMonthYear = today.plusMonths(1).getYear();

        totalVehiclesLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle")));

        suvsLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE vehicle_type='SUV'")));
        sedansTypeLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE vehicle_type='Sedan'")));
        hatchbacksLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE vehicle_type='Hatchback'")));
        pickupsTypeLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE vehicle_type='Pickup'")));
        coupesTypeLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE vehicle_type='Coupe'")));

        automaticLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE transmission='Automatic'")));
        manualLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE transmission='Manual'")));

        gasLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE fuel_type='Gas'")));
        dieselLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE fuel_type='Diesel'")));
        hybridLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE fuel_type='Hybrid'")));
        electricLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Vehicle WHERE fuel_type='Electric'")));

        totalCustomersLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Customer")));

        allRentalsLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Rental")));
        rentalsThisMonthLabel.setText(showNumber(getNumberFromDatabase(String.format(
            "SELECT COUNT(*) FROM Rental WHERE MONTH(rental_start_date)=%d AND YEAR(rental_start_date)=%d",
            thisMonth, thisYear))));
        rentalsNextMonthLabel.setText(showNumber(getNumberFromDatabase(String.format(
            "SELECT COUNT(*) FROM Rental WHERE MONTH(rental_start_date)=%d AND YEAR(rental_start_date)=%d",
            nextMonth, nextMonthYear))));
        rentalsLastMonthLabel.setText(showNumber(getNumberFromDatabase(String.format(
            "SELECT COUNT(*) FROM Rental WHERE MONTH(rental_start_date)=%d AND YEAR(rental_start_date)=%d",
            lastMonth, lastMonthYear))));

        confirmedLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Rental WHERE status='Confirmed'")));
        ongoingLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Rental WHERE status='Ongoing'")));
        cancelledLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Rental WHERE status='Cancelled'")));
        pendingLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Rental WHERE status='Pending'")));
        completedLabel.setText(showNumber(getNumberFromDatabase("SELECT COUNT(*) FROM Rental WHERE status='Completed'")));

        totalPaymentLabel.setText(showNumber(getNumberFromDatabase("SELECT COALESCE(SUM(amount_paid),0) FROM Payment")));
        paymentThisMonthLabel.setText(showNumber(getNumberFromDatabase(String.format(
            "SELECT COALESCE(SUM(amount_paid),0) FROM Payment WHERE MONTH(payment_datetime)=%d AND YEAR(payment_datetime)=%d",
            thisMonth, thisYear))));
        paymentLastMonthLabel.setText(showNumber(getNumberFromDatabase(String.format(
            "SELECT COALESCE(SUM(amount_paid),0) FROM Payment WHERE MONTH(payment_datetime)=%d AND YEAR(payment_datetime)=%d",
            lastMonth, lastMonthYear))));
        cashPaymentLabel.setText(showNumber(getNumberFromDatabase(
            "SELECT COALESCE(SUM(amount_paid),0) FROM Payment WHERE payment_method='Cash'")));
        cardPaymentLabel.setText(showNumber(getNumberFromDatabase(
            "SELECT COALESCE(SUM(amount_paid),0) FROM Payment WHERE payment_method='Card'")));
        ewalletPaymentLabel.setText(showNumber(getNumberFromDatabase(
            "SELECT COALESCE(SUM(amount_paid),0) FROM Payment WHERE payment_method='E-wallet'")));
    }

    private String showNumber(long amount) {
        return String.format("%,d", amount);
    }

    private String showPeso(long amount) {
        return "" + String.format("%,d", amount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new reportsParent().setVisible(true));
    }
}