package threeGWheels;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class updateCustomer extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final JFrame detailsFrame;
    private final customerParent mainFrame;
    private final int customerID;

    private JTextField tfFirstName;
    private JTextField tfMiddleName;
    private JTextField tfLastName;
    private JComboBox<String> cbSuffix;
    private JTextField tfDob;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfLicense;
    private JTextField tfAddress;
    private JTextField tfUsername;
    private JTextField tfPassword;

    public updateCustomer(JFrame detailsFrame, customerParent mainFrame, int id,
                          String firstName, String middleName, String lastName,
                          String suffix, String dob, String email, String phone,
                          String licenseNum, String address,
                          String username, String password) {

        super(detailsFrame, "UPDATE CUSTOMER - ID " + id, true);
        this.detailsFrame = detailsFrame;
        this.mainFrame = mainFrame;
        this.customerID = id;

        setBounds(250, 120, 520, 600);
        setResizable(false);

        JPanel dialogPane = new JPanel(new BorderLayout());
        dialogPane.setBackground(WHITE);
        setContentPane(dialogPane);

        JPanel dialogHeader = new JPanel(new BorderLayout());
        dialogHeader.setBackground(DARK_BLUE);
        dialogHeader.setPreferredSize(new Dimension(520, 45));
        dialogPane.add(dialogHeader, BorderLayout.NORTH);

        JLabel dialogTitle = new JLabel("UPDATE CUSTOMER - ID " + id);
        dialogTitle.setForeground(WHITE);
        dialogTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        dialogTitle.setHorizontalAlignment(SwingConstants.CENTER);
        dialogHeader.add(dialogTitle, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(WHITE);
        formPanel.setBorder(new EmptyBorder(12, 14, 12, 14));
        dialogPane.add(formPanel, BorderLayout.CENTER);

        int lx = 14;
        int fx = 260;
        int lw = 230;
        int fw = 220;
        int fh = 26;
        int lh = 18;
        int gap = 36;

        JLabel lblFirstName = new JLabel("FIRST NAME *");
        lblFirstName.setFont(new Font("Dialog", Font.BOLD, 11));
        lblFirstName.setForeground(DARK_BLUE);
        lblFirstName.setBounds(lx, 4, lw, lh);
        formPanel.add(lblFirstName);

        tfFirstName = new JTextField(sanitize(firstName));
        tfFirstName.setBounds(fx, 0, fw, fh);
        formPanel.add(tfFirstName);

        JLabel lblMiddleName = new JLabel("MIDDLE NAME *");
        lblMiddleName.setFont(new Font("Dialog", Font.BOLD, 11));
        lblMiddleName.setForeground(DARK_BLUE);
        lblMiddleName.setBounds(lx, 4 + gap, lw, lh);
        formPanel.add(lblMiddleName);

        tfMiddleName = new JTextField(sanitize(middleName));
        tfMiddleName.setBounds(fx, gap, fw, fh);
        formPanel.add(tfMiddleName);

        JLabel lblLastName = new JLabel("LAST NAME *");
        lblLastName.setFont(new Font("Dialog", Font.BOLD, 11));
        lblLastName.setForeground(DARK_BLUE);
        lblLastName.setBounds(lx, 4 + gap * 2, lw, lh);
        formPanel.add(lblLastName);

        tfLastName = new JTextField(sanitize(lastName));
        tfLastName.setBounds(fx, gap * 2, fw, fh);
        formPanel.add(tfLastName);

        JLabel lblSuffix = new JLabel("SUFFIX");
        lblSuffix.setFont(new Font("Dialog", Font.BOLD, 11));
        lblSuffix.setForeground(DARK_BLUE);
        lblSuffix.setBounds(lx, 4 + gap * 3, lw, lh);
        formPanel.add(lblSuffix);

        cbSuffix = new JComboBox<>(new String[]{"", "II", "III", "IV", "V", "Jr.", "Sr."});
        cbSuffix.setSelectedItem(sanitize(suffix));
        cbSuffix.setBounds(fx, gap * 3, fw, fh);
        formPanel.add(cbSuffix);

        JLabel lblDob = new JLabel("DATE OF BIRTH (YYYY-MM-DD) *");
        lblDob.setFont(new Font("Dialog", Font.BOLD, 11));
        lblDob.setForeground(DARK_BLUE);
        lblDob.setBounds(lx, 4 + gap * 4, lw, lh);
        formPanel.add(lblDob);

        tfDob = new JTextField(sanitize(dob));
        tfDob.setBounds(fx, gap * 4, fw, fh);
        formPanel.add(tfDob);

        JLabel lblEmail = new JLabel("EMAIL ADDRESS *");
        lblEmail.setFont(new Font("Dialog", Font.BOLD, 11));
        lblEmail.setForeground(DARK_BLUE);
        lblEmail.setBounds(lx, 4 + gap * 5, lw, lh);
        formPanel.add(lblEmail);

        tfEmail = new JTextField(sanitize(email));
        tfEmail.setBounds(fx, gap * 5, fw, fh);
        formPanel.add(tfEmail);

        JLabel lblPhone = new JLabel("PHONE NUMBER *");
        lblPhone.setFont(new Font("Dialog", Font.BOLD, 11));
        lblPhone.setForeground(DARK_BLUE);
        lblPhone.setBounds(lx, 4 + gap * 6, lw, lh);
        formPanel.add(lblPhone);

        tfPhone = new JTextField(sanitize(phone));
        tfPhone.setBounds(fx, gap * 6, fw, fh);
        formPanel.add(tfPhone);

        JLabel lblLicense = new JLabel("DRIVER LICENSE NUMBER *");
        lblLicense.setFont(new Font("Dialog", Font.BOLD, 11));
        lblLicense.setForeground(DARK_BLUE);
        lblLicense.setBounds(lx, 4 + gap * 7, lw, lh);
        formPanel.add(lblLicense);

        tfLicense = new JTextField(sanitize(licenseNum));
        tfLicense.setBounds(fx, gap * 7, fw, fh);
        formPanel.add(tfLicense);

        JLabel lblAddress = new JLabel("ADDRESS *");
        lblAddress.setFont(new Font("Dialog", Font.BOLD, 11));
        lblAddress.setForeground(DARK_BLUE);
        lblAddress.setBounds(lx, 4 + gap * 8, lw, lh);
        formPanel.add(lblAddress);

        tfAddress = new JTextField(sanitize(address));
        tfAddress.setBounds(fx, gap * 8, fw, fh);
        formPanel.add(tfAddress);

        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 11));
        lblUsername.setForeground(DARK_BLUE);
        lblUsername.setBounds(lx, 4 + gap * 9, lw, lh);
        formPanel.add(lblUsername);

        tfUsername = new JTextField(sanitize(username));
        tfUsername.setBounds(fx, gap * 9, fw, fh);
        formPanel.add(tfUsername);

        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 11));
        lblPassword.setForeground(DARK_BLUE);
        lblPassword.setBounds(lx, 4 + gap * 10, lw, lh);
        formPanel.add(lblPassword);

        tfPassword = new JTextField(sanitize(password));
        tfPassword.setBounds(fx, gap * 10, fw, fh);
        formPanel.add(tfPassword);

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
        saveButton.addActionListener(e -> saveUpdate());
        dialogFooter.add(saveButton);
    }

    private void saveUpdate() {
        String firstName = tfFirstName.getText().trim();
        String middleName = tfMiddleName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String dob = tfDob.getText().trim();
        String email = tfEmail.getText().trim();
        String phone = tfPhone.getText().trim();
        String license = tfLicense.getText().trim();
        String address = tfAddress.getText().trim();

        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() ||
            dob.isEmpty() || email.isEmpty() || phone.isEmpty() ||
            license.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Date of Birth must be in YYYY-MM-DD format.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE customer SET "
                + "first_name = ?, middle_name = ?, last_name = ?, suffix = ?, "
                + "date_of_birth = ?, email_address = ?, phone_number = ?, "
                + "driver_license_number = ?, address = ?, username = ?, password = ? "
                + "WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, middleName);
            ps.setString(3, lastName);

            String sfx = (String) cbSuffix.getSelectedItem();
            if (sfx == null || sfx.isEmpty()) ps.setNull(4, Types.VARCHAR);
            else ps.setString(4, sfx);

            ps.setDate(5, Date.valueOf(dob));
            ps.setString(6, email);
            ps.setString(7, phone);
            ps.setString(8, license);
            ps.setString(9, address);

            String u = tfUsername.getText().trim();
            String p = tfPassword.getText().trim();
            ps.setString(10, u.isEmpty() ? null : u);
            ps.setString(11, p.isEmpty() ? null : p);
            ps.setInt(12, customerID);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Customer updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose();
            detailsFrame.dispose();
            if (mainFrame != null) mainFrame.reloadTable();

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Email or license number already used by another customer.", "Duplicate Value", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private String sanitize(String v) {
        return (v == null || v.trim().equalsIgnoreCase("N/A")) ? "" : v.trim();
    }
}