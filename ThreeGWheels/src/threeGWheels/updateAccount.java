package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class updateAccount extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;
    private static final Color ERROR_RED = new Color(180, 30, 30);
    private static final Color SUCCESS_GREEN = new Color(20, 120, 50);

    private final int customerId;
    private final accounts parentFrame;

    private String currentPasswordInDB = "";

    private JTextField    tfFirstName, tfMiddleName, tfLastName;
    private JComboBox<String> cbSuffix;
    private JTextField    tfDob, tfEmail, tfPhone;
    private JTextField    tfLicense, tfAddress, tfUsername;
    private JPasswordField pfCurrentPassword, pfNewPassword, pfRetypePassword;

    private JLabel lblNewPasswordHint, lblRetypeHint;
    private static final int LABEL_X = 14;
    private static final int LABEL_W = 190;
    private static final int FIELD_X = 210;
    private static final int FIELD_W = 224;
    private static final int ROW_H = 26;
    private static final int ROW_STEP = 40; 
    private static final int SECTION_H = 28;
    private static final int SECTION_GAP = 12; 

    public updateAccount(accounts parent, int customerId) {
        super(parent, "Update Account", true);
        this.parentFrame = parent;
        this.customerId  = customerId;

        setSize(470, 660);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(WHITE);
        setContentPane(root);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(470, 54));
        JLabel title = new JLabel("UPDATE ACCOUNT");
        title.setForeground(WHITE);
        title.setFont(new Font("Dialog", Font.BOLD, 16));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(null);
        form.setBackground(WHITE);
        form.setBorder(new EmptyBorder(10, 14, 14, 14));

        int y = 4; 

        y = addSection(form, "PERSONAL INFORMATION", y);
        
        y = addRow(form, "First Name *",  y); 
        tfFirstName  = lastField;
        
        y = addRow(form, "Middle Name",   y); 
        tfMiddleName = lastField;
        
        y = addRow(form, "Last Name *",   y); 
        tfLastName   = lastField;

        addLabel(form, "Suffix", y);
        cbSuffix = new JComboBox<>(new String[]{ "", "Jr.", "Sr.", "II", "III", "IV", "V" });
        cbSuffix.setFont(new Font("Dialog", Font.PLAIN, 12));
        cbSuffix.setBounds(FIELD_X, y, FIELD_W, ROW_H);
        form.add(cbSuffix);
        y += ROW_STEP;

        y = addRow(form, "Date of Birth (YYYY-MM-DD) *", y); 
        tfDob = lastField;
        
        y = addSection(form, "CONTACT INFORMATION", y);
        
        y = addRow(form, "Email Address *", y); 
        tfEmail   = lastField;
        
        y = addRow(form, "Phone Number *",  y); 
        tfPhone   = lastField;
        
        y = addRow(form, "Address *",        y); 
        tfAddress = lastField;

        y = addSection(form, "LICENSE & CREDENTIALS", y);
        
        y = addRow(form, "Driver License No. *", y); 
        
        tfLicense  = lastField;
        y = addRow(form, "Username *",            y); 
        tfUsername = lastField;

        y = addSection(form, "CHANGE PASSWORD", y);

        addLabel(form, "Current Password *", y);
        pfCurrentPassword = new JPasswordField();
        pfCurrentPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        pfCurrentPassword.setBounds(FIELD_X, y, FIELD_W, ROW_H);
        form.add(pfCurrentPassword);
        y += ROW_STEP;

        addLabel(form, "New Password", y);
        pfNewPassword = new JPasswordField();
        pfNewPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        pfNewPassword.setBounds(FIELD_X, y, FIELD_W, ROW_H);
        form.add(pfNewPassword);
        y += ROW_STEP - 8;

        lblNewPasswordHint = new JLabel(" ");
        lblNewPasswordHint.setFont(new Font("Dialog", Font.ITALIC, 11));
        lblNewPasswordHint.setBounds(FIELD_X, y, FIELD_W, 16);
        form.add(lblNewPasswordHint);
        y += 20;

        addLabel(form, "Retype New Password", y);
        pfRetypePassword = new JPasswordField();
        pfRetypePassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        pfRetypePassword.setBounds(FIELD_X, y, FIELD_W, ROW_H);
        pfRetypePassword.setEnabled(false);
        form.add(pfRetypePassword);
        y += ROW_STEP - 8;

        lblRetypeHint = new JLabel(" ");
        lblRetypeHint.setFont(new Font("Dialog", Font.ITALIC, 11));
        lblRetypeHint.setBounds(FIELD_X, y, FIELD_W, 16);
        form.add(lblRetypeHint);
        y += 24;

        final int totalH = y;
        form.setPreferredSize(new Dimension(436, totalH));

        pfNewPassword.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { onNewPasswordChanged(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { onNewPasswordChanged(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { onNewPasswordChanged(); }
        });
        pfRetypePassword.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { onRetypeChanged(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { onRetypeChanged(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { onRetypeChanged(); }
        });

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        root.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        footer.setBackground(new Color(235, 238, 248));
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));

        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setBackground(WHITE);
        cancelBtn.setForeground(DARK_BLUE);
        cancelBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorder(new EmptyBorder(5, 16, 5, 16));
        cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(e -> dispose());

        JButton saveBtn = new JButton("SAVE CHANGES");
        saveBtn.setBackground(DARK_BLUE);
        saveBtn.setForeground(WHITE);
        saveBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        saveBtn.setFocusPainted(false);
        saveBtn.setBorder(new EmptyBorder(5, 16, 5, 16));
        saveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveBtn.addActionListener(e -> saveChanges());

        footer.add(cancelBtn);
        footer.add(saveBtn);
        root.add(footer, BorderLayout.SOUTH);

        loadCurrentData();
    }

    private JTextField lastField;

    private void addLabel(JPanel panel, String text, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Dialog", Font.BOLD, 11));
        lbl.setForeground(DARK_BLUE);
        lbl.setBounds(LABEL_X, y + 4, LABEL_W, 18);
        panel.add(lbl);
    }

    private int addRow(JPanel panel, String labelText, int y) {
        addLabel(panel, labelText, y);
        JTextField tf = new JTextField();
        tf.setFont(new Font("Dialog", Font.PLAIN, 12));
        tf.setBounds(FIELD_X, y, FIELD_W, ROW_H);
        panel.add(tf);
        lastField = tf;
        return y + ROW_STEP;
    }

    private int addSection(JPanel panel, String text, int y) {
        y += SECTION_GAP;
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(DARK_BLUE);
        bar.setBounds(LABEL_X, y, LABEL_W + FIELD_W + (FIELD_X - LABEL_X - LABEL_W), SECTION_H);
        JLabel lbl = new JLabel(text);
        lbl.setForeground(WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 11));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(lbl, BorderLayout.CENTER);
        panel.add(bar);
        return y + SECTION_H + 8;
    }

    private void loadCurrentData() {
        String sql = "SELECT first_name, middle_name, last_name, suffix, " +
                     "date_of_birth, email_address, phone_number, " +
                     "driver_license_number, address, username, password " +
                     "FROM Customer WHERE customer_id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tfFirstName.setText(rs.getString("first_name"));
                    tfMiddleName.setText(rs.getString("middle_name"));
                    tfLastName.setText(rs.getString("last_name"));
                    String sfx = rs.getString("suffix");
                    if (sfx != null) cbSuffix.setSelectedItem(sfx);
                    tfDob.setText(rs.getString("date_of_birth"));
                    tfEmail.setText(rs.getString("email_address"));
                    tfPhone.setText(rs.getString("phone_number"));
                    tfLicense.setText(rs.getString("driver_license_number"));
                    tfAddress.setText(rs.getString("address"));
                    tfUsername.setText(rs.getString("username"));
                    currentPasswordInDB = rs.getString("password");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not load data:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onNewPasswordChanged() {
        String newPass = new String(pfNewPassword.getPassword());
        if (newPass.isEmpty()) {
            pfRetypePassword.setEnabled(false);
            pfRetypePassword.setText("");
            lblNewPasswordHint.setText(" ");
            lblRetypeHint.setText(" ");
        } else {
            pfRetypePassword.setEnabled(true);
            lblNewPasswordHint.setForeground(new Color(30, 90, 180));
            lblNewPasswordHint.setText("Retype your new password below.");
            onRetypeChanged();
        }
    }

    private void onRetypeChanged() {
        String newPass    = new String(pfNewPassword.getPassword());
        String retypePass = new String(pfRetypePassword.getPassword());
        if (newPass.isEmpty() || retypePass.isEmpty()) {
            lblRetypeHint.setText(" ");
            return;
        }
        if (newPass.equals(retypePass)) {
            lblRetypeHint.setForeground(SUCCESS_GREEN);
            lblRetypeHint.setText("Passwords match.");
        } else {
            lblRetypeHint.setForeground(ERROR_RED);
            lblRetypeHint.setText("Passwords do not match.");
        }
    }

    private void saveChanges() {
        String firstName = tfFirstName.getText().trim();
        String middleName = tfMiddleName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String suffix = (String) cbSuffix.getSelectedItem();
        String dob = tfDob.getText().trim();
        String email = tfEmail.getText().trim();
        String phone = tfPhone.getText().trim();
        String license = tfLicense.getText().trim();
        String address = tfAddress.getText().trim();
        String username = tfUsername.getText().trim();

        String enteredCurrent = new String(pfCurrentPassword.getPassword()).trim();
        String newPass = new String(pfNewPassword.getPassword());
        String retypePass = new String(pfRetypePassword.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty()
                || email.isEmpty() || phone.isEmpty() || license.isEmpty()
                || address.isEmpty() || username.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all required fields (marked with *).",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (enteredCurrent.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your current password to confirm changes.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            pfCurrentPassword.requestFocus();
            return;
        }

        if (!enteredCurrent.equals(currentPasswordInDB)) {
            JOptionPane.showMessageDialog(this,
                    "Current password is incorrect. No changes were saved.",
                    "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            pfCurrentPassword.setText("");
            pfCurrentPassword.requestFocus();
            return;
        }

        String finalPassword = currentPasswordInDB;
        if (!newPass.isEmpty()) {
            if (!newPass.equals(retypePass)) {
                JOptionPane.showMessageDialog(this,
                        "New password and retype do not match.",
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                pfRetypePassword.requestFocus();
                return;
            }
            finalPassword = newPass;
        }

        String sql = "UPDATE Customer SET " +
                     "first_name = ?, middle_name = ?, last_name = ?, suffix = ?, " +
                     "date_of_birth = ?, email_address = ?, phone_number = ?, " +
                     "driver_license_number = ?, address = ?, username = ?, password = ? " +
                     "WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, middleName.isEmpty() ? null : middleName);
            ps.setString(3, lastName);
            ps.setString(4, suffix.isEmpty() ? null : suffix);
            ps.setString(5, dob);
            ps.setString(6, email);
            ps.setString(7, phone);
            ps.setString(8, license);
            ps.setString(9, address);
            ps.setString(10, username);
            ps.setString(11, finalPassword);
            ps.setInt(12, customerId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                        "Account updated successfully!",
                        "3G Wheels", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                parentFrame.dispose();
                new accounts(customerId).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Update failed. Customer not found.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Database error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}