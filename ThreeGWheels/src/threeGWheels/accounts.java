package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class accounts extends Abstraction {

    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final int customerId;
    private String firstName, middleName, lastName;
    private String suffix, dob, email, phone;
    private String licenseNum, address, username, password;

    public accounts(int customerId) {
        super("3G Wheels - My Account", 560, 680);
        this.customerId = customerId;
        initComponents();
    }

    private boolean loadFromDatabase() {
        String sql = "SELECT first_name, middle_name, last_name, suffix, " +
                     "date_of_birth, email_address, phone_number, " +
                     "driver_license_number, address, username, password " +
                     "FROM Customer WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    firstName = rs.getString("first_name");
                    middleName = rs.getString("middle_name");
                    lastName = rs.getString("last_name");
                    suffix = rs.getString("suffix");
                    dob = rs.getString("date_of_birth");
                    email = rs.getString("email_address");
                    phone = rs.getString("phone_number");
                    licenseNum = rs.getString("driver_license_number");
                    address = rs.getString("address");
                    username = rs.getString("username");
                    password = rs.getString("password");
                    return true;
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Database error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        return false;
    }



    @Override
    protected void initComponents() {
        if (!loadFromDatabase()) {
            JOptionPane.showMessageDialog(this,
                    "Could not load account data.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_BLUE);
        setContentPane(root);

        root.add(buildHeader(),  BorderLayout.NORTH);
        root.add(buildContent(), BorderLayout.CENTER);
        root.add(buildFooter(),  BorderLayout.SOUTH);
    }

    @Override
    protected JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setBorder(new EmptyBorder(24, 0, 24, 0));

        String fullName = firstName
                + (middleName != null && !middleName.isBlank() ? " " + middleName : "")
                + " " + lastName
                + (suffix != null && !suffix.isBlank() ? " " + suffix : "");

        JLabel title = makeLabel("" + fullName.toUpperCase(), WHITE, Font.BOLD, 18);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);

        return header;
    }

    @Override
    protected JPanel buildContent() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(new Color(230, 235, 245));
        wrapper.add(buildSectionHeader("PERSONAL INFORMATION"));
        wrapper.add(buildRow("Customer ID", String.valueOf(customerId), false));
        wrapper.add(buildRow("First Name", firstName, true));
        wrapper.add(buildRow("Middle Name", middleName, false));
        wrapper.add(buildRow("Last Name", lastName, true));
        wrapper.add(buildRow("Suffix", suffix, false));
        wrapper.add(buildRow("Date of Birth", dob, true));
        wrapper.add(buildSectionHeader("CONTACT INFORMATION"));
        wrapper.add(buildRow("Email Address", email, false));
        wrapper.add(buildRow("Phone Number", phone, true));
        wrapper.add(buildRow("Address", address, false));
        wrapper.add(buildSectionHeader("LICENSE & CREDENTIALS"));
        wrapper.add(buildRow("Driver License No.", licenseNum, true));
        wrapper.add(buildRow("Username", username, false));
        wrapper.add(buildRow("Password", "********", true));

        JScrollPane scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        scroll.setBackground(new Color(230, 235, 245));

        JPanel content = new JPanel(new BorderLayout());
        content.add(scroll, BorderLayout.CENTER);
        return content;
    }

    @Override
    protected JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 14));
        footer.setBackground(DARK_BLUE);

        JButton backBtn = makeButton("BACK", new Color(30, 50, 100), LIGHT_GRAY);
        backBtn.addActionListener(e -> {
            new menu(customerId, firstName, middleName, lastName,
                     suffix, dob, email, phone,
                     licenseNum, address, username, password)
                    .setVisible(true);
            dispose();
        });

        JButton updateBtn = makeButton("UPDATE", new Color(30, 70, 160), WHITE);
        updateBtn.addActionListener(e -> {
            updateAccount dialog = new updateAccount(this, customerId);
            dialog.setVisible(true);
        });

        JButton deleteBtn = makeButton("DELETE ACCOUNT", new Color(140, 30, 30), WHITE);
        deleteBtn.addActionListener(e ->
            new deleteAccount(this, customerId).confirmAndDelete()
        );

        footer.add(backBtn);
        footer.add(updateBtn);
        footer.add(deleteBtn);
        return footer;
    }

    private JPanel buildSectionHeader(String title) {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(CARD_BLUE);
        bar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(40, 80, 160)),
                new EmptyBorder(6, 16, 6, 16)
        ));
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        JLabel lbl = makeLabel(title, WHITE, Font.BOLD, 12);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(lbl, BorderLayout.CENTER);
        return bar;
    }

    private JPanel buildRow(String label, String value, boolean alternate) {
        JPanel row = new JPanel(new GridLayout(1, 2, 0, 0));
        row.setBackground(alternate ? new Color(210, 218, 238) : new Color(230, 235, 245));
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 200, 220)),
                new EmptyBorder(8, 16, 8, 16)
        ));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel keyLbl = makeLabel(label + ":", DARK_BLUE, Font.BOLD, 12);
        String display = (value == null || value.isBlank()) ? "-" : value;
        JLabel valLbl = makeLabel(display, new Color(30, 30, 80), Font.PLAIN, 12);

        row.add(keyLbl);
        row.add(valLbl);
        return row;
    }
}