package threeGWheels;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class offers extends Abstraction {

    private static final long serialVersionUID = 1L;

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color ROW_ALT = new Color(210, 218, 238);
    private static final Color ROW_BASE = new Color(230, 235, 245);

    private static final int ROW_WIDTH = 620;

    private final int customerId;
    private final String firstName, middleName, lastName;
    private final String suffix, dob, email, phone;
    private final String licenseNum, address, username, password;

    private JPanel rowsPanel;

    public offers(int customerId,
                  String firstName, String middleName, String lastName,
                  String suffix, String dob, String email, String phone,
                  String licenseNum, String address, String username, String password) {

        super("3G Wheels - Offers", 660, 620);
        this.customerId = customerId;
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

        JLabel title = makeLabel("AVAILABLE VEHICLES", WHITE, Font.BOLD, 18);
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

        addHeaderCell(headerRow, "ID", 20, 60);
        addHeaderCell(headerRow, "VEHICLE TYPE", 80, 130);
        addHeaderCell(headerRow, "BRAND", 210, 130);
        addHeaderCell(headerRow, "MODEL", 340, 160);
        addHeaderCell(headerRow, "", 510, 80);

        body.add(headerRow, BorderLayout.NORTH);

        rowsPanel = new JPanel();
        rowsPanel.setLayout(new BoxLayout(rowsPanel, BoxLayout.Y_AXIS));
        rowsPanel.setBackground(DARK_BLUE);

        loadVehicleRows();

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

    private void loadVehicleRows() {
        String sql = "SELECT vehicle_id, vehicle_type, brand, model, year_model, " +
                     "license_plate_number, vehicle_registration_number, " +
                     "seating_capacity, rental_rate, fuel_type, transmission, mileage " +
                     "FROM Vehicle ORDER BY vehicle_id";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean alternate = false;
            while (rs.next()) {
                rowsPanel.add(buildRow(
                    rs.getInt("vehicle_id"),
                    rs.getString("vehicle_type"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getInt("year_model"),
                    rs.getString("license_plate_number"),
                    rs.getString("vehicle_registration_number"),
                    rs.getInt("seating_capacity"),
                    rs.getInt("rental_rate"),
                    rs.getString("fuel_type"),
                    rs.getString("transmission"),
                    rs.getInt("mileage"),
                    alternate
                ));
                alternate = !alternate;
            }

        } catch (Exception ex) {
            JPanel err = new JPanel();
            err.setBackground(ROW_BASE);
            JLabel msg = new JLabel("Error loading data: " + ex.getMessage());
            msg.setForeground(Color.RED);
            err.add(msg);
            rowsPanel.add(err);
            ex.printStackTrace();
        }
    }

    private JPanel buildRow(int id, String type, String brand, String model,
                            int yearModel, String plate, String regNum,
                            int seats, int rate, String fuel,
                            String transmission, int mileage, boolean alternate) {

        JPanel row = new JPanel(null);
        row.setBackground(alternate ? ROW_ALT : ROW_BASE);
        row.setPreferredSize(new Dimension(ROW_WIDTH, 38));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 200, 220)));

        JLabel idLbl = new JLabel(String.valueOf(id));
        idLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        idLbl.setForeground(MID_BLUE);
        idLbl.setBounds(20, 0, 60, 38);
        row.add(idLbl);

        JLabel typeLbl = new JLabel(type != null ? type : "-");
        typeLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        typeLbl.setForeground(MID_BLUE);
        typeLbl.setBounds(80, 0, 130, 38);
        row.add(typeLbl);

        JLabel brandLbl = new JLabel(brand != null ? brand : "-");
        brandLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        brandLbl.setForeground(MID_BLUE);
        brandLbl.setBounds(210, 0, 130, 38);
        row.add(brandLbl);

        JLabel modelLbl = new JLabel(model != null ? model : "-");
        modelLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        modelLbl.setForeground(MID_BLUE);
        modelLbl.setBounds(340, 0, 160, 38);
        row.add(modelLbl);

        JButton viewBtn = new JButton("VIEW");
        viewBtn.setBackground(DARK_BLUE);
        viewBtn.setForeground(WHITE);
        viewBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        viewBtn.setFocusPainted(false);
        viewBtn.setBorderPainted(false);
        viewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewBtn.setBounds(510, 5, 68, 28);
        viewBtn.addActionListener(e ->
            new cVehicleDetails(customerId, id, type, brand, model, yearModel, plate, regNum, seats, rate, fuel, transmission, mileage,
                firstName, middleName, lastName, suffix, dob, email, phone, licenseNum, address, username, password).setVisible(true)
        );
        row.add(viewBtn);

        return row;
    }

    private void addHeaderCell(JPanel panel, String text, int x, int w) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl.setForeground(WHITE);
        lbl.setBounds(x, 0, w, 36);
        panel.add(lbl);
    }
}