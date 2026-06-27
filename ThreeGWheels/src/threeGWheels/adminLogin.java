package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class adminLogin extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;
    private static final Color ROW_ALT = new Color(240, 244, 255);
    private static final Color ROW_BORDER = new Color(210, 218, 240);

    private static final String ADMIN_USER = "3gwheelsadmin";
    private static final String ADMIN_PASS = "3gwheelsdabest";

    private JTextField tfUsername;
    private JPasswordField tfPassword;

    public adminLogin() {
        setTitle("3G Wheels - Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 560);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(480, 90));

        JLabel companyName = new JLabel("🚘 3G WHEELS ADMIN");
        companyName.setForeground(WHITE);
        companyName.setFont(new Font("Dialog", Font.BOLD, 22));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(companyName, BorderLayout.CENTER);
        contentPane.add(header, BorderLayout.NORTH);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(WHITE);
        toolbar.setBorder(new EmptyBorder(16, 14, 8, 14));
        toolbar.setPreferredSize(new Dimension(480, 60));

        JLabel pageTitle = new JLabel("ADMIN LOGIN");
        pageTitle.setForeground(DARK_BLUE);
        pageTitle.setFont(new Font("Dialog", Font.BOLD, 18));
        pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
        toolbar.add(pageTitle, BorderLayout.CENTER);

        centerArea.add(toolbar, BorderLayout.NORTH);

        JPanel formWrapper = new JPanel(null);
        formWrapper.setBackground(WHITE);

        JPanel form = new JPanel(null);
        form.setBackground(WHITE);
        form.setBounds(70, 30, 340, 260);

        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setBounds(0, 20, 100, 18);
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 11));
        lblUsername.setForeground(DARK_BLUE);

        tfUsername = new JTextField();
        tfUsername.setBounds(106, 14, 234, 32);
        tfUsername.setFont(new Font("Dialog", Font.PLAIN, 13));
        tfUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ROW_BORDER, 1),
            new EmptyBorder(4, 8, 4, 8)));

        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setBounds(0, 78, 100, 18);
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 11));
        lblPassword.setForeground(DARK_BLUE);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(106, 72, 234, 32);
        tfPassword.setFont(new Font("Dialog", Font.PLAIN, 13));
        tfPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ROW_BORDER, 1),
            new EmptyBorder(4, 8, 4, 8)));

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setBounds(0, 118, 340, 18);
        errorLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        errorLabel.setForeground(new Color(180, 30, 30));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton loginButton = new JButton("LOG IN");
        loginButton.setBounds(0, 140, 340, 38);
        loginButton.setBackground(new Color(0, 64, 128));
        loginButton.setForeground(WHITE);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 13));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new EmptyBorder(8, 0, 8, 0));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ActionListener loginAction = e -> {
            String user = tfUsername.getText().trim();
            String pass = new String(tfPassword.getPassword()).trim();
            if (user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS)) {
                new adminDashboard().setVisible(true);
                dispose();
            } else {
                errorLabel.setText("Invalid username or password.");
                tfPassword.setText("");
            }
        };

        loginButton.addActionListener(loginAction);
        tfPassword.addActionListener(loginAction);

        form.add(lblUsername);
        form.add(tfUsername);
        form.add(lblPassword);
        form.add(tfPassword);
        form.add(errorLabel);
        form.add(loginButton);

        formWrapper.add(form);
        centerArea.add(formWrapper, BorderLayout.CENTER);
        contentPane.add(centerArea, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(ROW_ALT);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ROW_BORDER));
        JLabel footerLabel = new JLabel("ayawKoNaMagCode");
        footerLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        footerLabel.setForeground(new Color(150, 160, 180));
        footer.add(footerLabel);
        contentPane.add(footer, BorderLayout.SOUTH);
    }
}