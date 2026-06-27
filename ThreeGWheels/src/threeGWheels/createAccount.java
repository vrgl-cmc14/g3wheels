package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class createAccount extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color CARD_BLUE = new Color(10, 45, 110);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(200, 210, 230);
    private static final Color ROW_BASE = new Color(230, 235, 245);
    private static final Color ROW_ALT = new Color(210, 218, 238);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private JTextField tfFirstName, tfMiddleName, tfLastName;
    private JTextField tfDob, tfEmail, tfPhone;
    private JTextField tfLicense, tfAddress;
    private JTextField tfUsername, tfPassword;
    private JComboBox<String> cbSuffix;

    public createAccount() {
        setTitle("3G Wheels - Create Account");
        setSize(520, 680);
        setMinimumSize(new Dimension(520, 600));
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_BLUE);
        setContentPane(root);

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildForm(), BorderLayout.CENTER);
        root.add(buildFooter(), BorderLayout.SOUTH);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(40, 70, 160)),
            new EmptyBorder(22, 0, 22, 0)
        ));
        JLabel title = new JLabel("CREATE ACCOUNT");
        title.setForeground(WHITE);
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        return header;
    }

    private JPanel buildForm() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(DARK_BLUE);
        content.setBorder(new EmptyBorder(0, 16, 16, 16));

        addSectionBar(content, "PERSONAL INFORMATION");
        tfFirstName = addFieldRow(content, "First Name", false);
        tfMiddleName = addFieldRow(content, "Middle Name", true);
        tfLastName = addFieldRow(content, "Last Name", false);

        addSectionBar(content, "SUFFIX (optional)");
        cbSuffix = new JComboBox<>(new String[]{"None", "Jr.", "Sr,", "II", "III", "IV", "V"});
        addComboRow(content, "Suffix", cbSuffix, false);

        addSectionBar(content, "CONTACT DETAILS");
        tfDob = addFieldRow(content, "Date of Birth (YYYY-MM-DD)", false);
        tfEmail = addFieldRow(content, "Email Address", true);
        tfPhone = addFieldRow(content, "Phone Number", false);
        tfAddress = addFieldRow(content, "Address", true);

        addSectionBar(content, "LICENSE");
        tfLicense = addFieldRow(content, "Driver License Number", false);

        addSectionBar(content, "ACCOUNT CREDENTIALS");
        tfUsername = addFieldRow(content, "Username", false);
        tfPassword = addFieldRow(content, "Password", true);

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(DARK_BLUE);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(DARK_BLUE);
        wrapper.add(scroll, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
        footer.setBackground(DARK_BLUE);

        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setBackground(new Color(30, 50, 100));
        cancelBtn.setForeground(LIGHT_GRAY);
        cancelBtn.setFont(new Font("Dialog", Font.BOLD, 12));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(e -> {
            new index().setVisible(true);
            dispose();
        });

        JButton submitBtn = new JButton("CREATE ACCOUNT");
        submitBtn.setBackground(new Color(0, 80, 40));
        submitBtn.setForeground(WHITE);
        submitBtn.setFont(new Font("Dialog", Font.BOLD, 12));
        submitBtn.setFocusPainted(false);
        submitBtn.setBorderPainted(false);
        submitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitBtn.addActionListener(e -> handleSubmit());

        footer.add(cancelBtn);
        footer.add(submitBtn);
        return footer;
    }

    private void handleSubmit() {
        String firstName = tfFirstName.getText().trim();
        String middleName = tfMiddleName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String suffix = cbSuffix.getSelectedItem().equals("None") ? null : (String) cbSuffix.getSelectedItem();
        String dob = tfDob.getText().trim();
        String email = tfEmail.getText().trim();
        String phone = tfPhone.getText().trim();
        String address = tfAddress.getText().trim();
        String license = tfLicense.getText().trim();
        String username = tfUsername.getText().trim();
        String password = tfPassword.getText().trim();

        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty()
                || dob.isEmpty() || email.isEmpty() || phone.isEmpty()
                || address.isEmpty() || license.isEmpty()
                || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all required fields.",
                "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO Customer " +
                     "(first_name, middle_name, last_name, suffix, date_of_birth, " +
                     "email_address, phone_number, driver_license_number, address, " +
                     "username, password) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, middleName);
            ps.setString(3, lastName);
            ps.setString(4, suffix);
            ps.setString(5, dob);
            ps.setString(6, email);
            ps.setString(7, phone);
            ps.setString(8, license);
            ps.setString(9, address);
            ps.setString(10, username);
            ps.setString(11, password);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                "Account created successfully! You may now log in.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            new index().setVisible(true);
            dispose();

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this,
                "Email, license number, or username is already in use.\nPlease use a different one.",
                "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error creating account: " + ex.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void addSectionBar(JPanel parent, String text) {
        parent.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel bar = new JPanel(new BorderLayout()) {
            @Override public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, 30);
            }
        };
        bar.setBackground(CARD_BLUE);
        bar.setPreferredSize(new Dimension(0, 30));
        bar.setMinimumSize(new Dimension(0, 30));
        JLabel lbl = new JLabel(text);
        lbl.setForeground(WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 11));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(lbl, BorderLayout.CENTER);
        parent.add(bar);
    }

    private JTextField addFieldRow(JPanel parent, String label, boolean alternate) {
        JPanel row = makeRow(alternate);
        JLabel keyLbl = new JLabel(label + ":");
        keyLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        keyLbl.setForeground(DARK_BLUE);
        keyLbl.setPreferredSize(new Dimension(200, 36));
        JTextField tf = new JTextField();
        tf.setFont(new Font("Dialog", Font.PLAIN, 12));
        tf.setForeground(new Color(30, 30, 80));
        tf.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        row.add(keyLbl, BorderLayout.WEST);
        row.add(tf, BorderLayout.CENTER);
        parent.add(row);
        return tf;
    }

    private void addComboRow(JPanel parent, String label, JComboBox<String> combo, boolean alternate) {
        JPanel row = makeRow(alternate);
        JLabel keyLbl = new JLabel(label + ":");
        keyLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        keyLbl.setForeground(DARK_BLUE);
        keyLbl.setPreferredSize(new Dimension(200, 36));
        combo.setFont(new Font("Dialog", Font.PLAIN, 12));
        row.add(keyLbl, BorderLayout.WEST);
        row.add(combo, BorderLayout.CENTER);
        parent.add(row);
    }

    private JPanel makeRow(boolean alternate) {
        JPanel row = new JPanel(new BorderLayout()) {
            @Override public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, 36);
            }
        };
        row.setBackground(alternate ? ROW_ALT : ROW_BASE);
        row.setPreferredSize(new Dimension(0, 36));
        row.setMinimumSize(new Dimension(0, 36));
        row.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(190, 200, 220)),
            new EmptyBorder(0, 12, 0, 12)
        ));
        return row;
    }
}