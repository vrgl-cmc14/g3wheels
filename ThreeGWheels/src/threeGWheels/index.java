package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class index extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField usernameBox;
    private JPasswordField passwordBox;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color CARD_BLUE = new Color(10, 45, 110);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(200, 210, 230);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new index().setVisible(true));
    }

    public index() {
        setTitle("3G Wheels - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 520);
        setLocationRelativeTo(null);
        setResizable(false);

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
            new EmptyBorder(28, 0, 28, 0)
        ));

        JLabel logo = new JLabel("🚘 3G WHEELS");
        logo.setForeground(WHITE);
        logo.setFont(new Font("Dialog", Font.BOLD, 24));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(logo, BorderLayout.CENTER);

        return header;
    }

    private JPanel buildForm() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(DARK_BLUE);
        outer.setBorder(new EmptyBorder(24, 32, 24, 32));

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(CARD_BLUE);
        wrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(40, 70, 160), 2),
            new EmptyBorder(28, 28, 28, 28)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 6, 0);

        gbc.gridy = 0;
        wrapper.add(makeFieldLabel("USERNAME:"), gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 18, 0);
        usernameBox = new JTextField();
        usernameBox.setFont(new Font("Dialog", Font.PLAIN, 13));
        usernameBox.setPreferredSize(new Dimension(0, 32));
        wrapper.add(usernameBox, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 6, 0);
        wrapper.add(makeFieldLabel("PASSWORD:"), gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 24, 0);
        passwordBox = new JPasswordField();
        passwordBox.setFont(new Font("Dialog", Font.PLAIN, 13));
        passwordBox.setPreferredSize(new Dimension(0, 32));
        wrapper.add(passwordBox, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        JButton loginBtn = makeButton("LOGIN", new Color(30, 70, 160), WHITE);
        loginBtn.addActionListener(e -> handleLogin());
        wrapper.add(loginBtn, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel twoBtn = new JPanel(new GridLayout(1, 2, 10, 0));
        twoBtn.setBackground(CARD_BLUE);

        JButton createBtn = makeButton("CREATE ACC", new Color(0, 80, 40), LIGHT_GRAY);
        createBtn.addActionListener(e -> {
            new createAccount().setVisible(true);
            dispose();
        });

        JButton adminBtn = makeButton("ADMIN", new Color(120, 20, 20), LIGHT_GRAY);
        adminBtn.addActionListener(e -> {
            new adminLogin().setVisible(true);
            dispose();
        });

        twoBtn.add(createBtn);
        twoBtn.add(adminBtn);
        wrapper.add(twoBtn, gbc);

        outer.add(wrapper, BorderLayout.CENTER);
        return outer;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(DARK_BLUE);
        footer.setPreferredSize(new Dimension(0, 16));
        return footer;
    }

    private JLabel makeFieldLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(LIGHT_GRAY);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        return lbl;
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Dialog", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 32));
        return btn;
    }

    private void handleLogin() {
        String username = usernameBox.getText().trim();
        String password = new String(passwordBox.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter username and password.",
                "Login Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Customer customer = authenticateUser(username, password);
        if (customer != null) {
            JOptionPane.showMessageDialog(this,
                "Welcome, " + customer.firstName + " " + customer.lastName + "!",
                "3G Wheels", JOptionPane.INFORMATION_MESSAGE);
            new menu(
                customer.customerId,
                customer.firstName, customer.middleName, customer.lastName,
                customer.suffix, customer.dob, customer.email, customer.phone,
                customer.licenseNum, customer.address,
                customer.username, customer.password
            ).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid username or password. Please try again.",
                "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordBox.setText("");
        }
    }

    private static class Customer {
        int customerId;
        String firstName, middleName, lastName;
        String suffix, dob, email, phone;
        String licenseNum, address, username, password;
    }

    private Customer authenticateUser(String username, String password) {
        String sql = "SELECT customer_id, first_name, middle_name, last_name, " +
                     "suffix, date_of_birth, email_address, phone_number, " +
                     "driver_license_number, address, username, password " +
                     "FROM Customer WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.customerId = rs.getInt("customer_id");
                    c.firstName = rs.getString("first_name");
                    c.middleName = rs.getString("middle_name");
                    c.lastName = rs.getString("last_name");
                    c.suffix = rs.getString("suffix");
                    c.dob = rs.getString("date_of_birth");
                    c.email = rs.getString("email_address");
                    c.phone = rs.getString("phone_number");
                    c.licenseNum = rs.getString("driver_license_number");
                    c.address = rs.getString("address");
                    c.username = rs.getString("username");
                    c.password = rs.getString("password");
                    return c;
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Database connection error:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        return null;
    }
}