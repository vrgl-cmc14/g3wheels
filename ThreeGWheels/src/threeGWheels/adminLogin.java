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

    private JTextField     txtUsername;
    private JPasswordField txtPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                adminLogin frame = new adminLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public adminLogin() {
        setTitle("3G Wheels – Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 520);
        setResizable(false);

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

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(WHITE);
        toolbar.setBorder(new EmptyBorder(8, 14, 8, 14));
        toolbar.setPreferredSize(new Dimension(480, 42));

        JLabel pageTitle = new JLabel("ADMIN LOGIN");
        pageTitle.setForeground(DARK_BLUE);
        pageTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        toolbar.add(pageTitle, BorderLayout.WEST);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);
        centerArea.add(toolbar, BorderLayout.NORTH);

        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(WHITE);

        JPanel form = new JPanel(null);
        form.setBounds(70, 62, 340, 230);
        form.setBackground(WHITE);
        form.setPreferredSize(new Dimension(340, 230));

        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setBounds(0, 41, 340, 18);
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 11));
        lblUsername.setForeground(DARK_BLUE);

        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setBounds(0, 104, 340, 18);
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 11));
        lblPassword.setForeground(DARK_BLUE);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(106, 96, 234, 34);
        txtPassword.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ROW_BORDER, 1),
            new EmptyBorder(4, 8, 4, 8)));

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setBounds(0, 142, 340, 18);
        errorLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        errorLabel.setForeground(new Color(180, 30, 30));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton loginButton = new JButton("LOG IN");
        loginButton.setBounds(0, 157, 340, 38);
        loginButton.setBackground(new Color(0, 64, 128));
        loginButton.setForeground(WHITE);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 13));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new EmptyBorder(8, 0, 8, 0));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ActionListener loginAction = e -> {
            String user = txtUsername.getText().trim();
            String pass = new String(txtPassword.getPassword()).trim();
            if (user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS)) {
                adminDashboard dashboard = new adminDashboard();
                dashboard.setVisible(true);
                dispose();
            } else {
                errorLabel.setText("Invalid username or password.");
                txtPassword.setText("");
            }
        };

        loginButton.addActionListener(loginAction);
        txtPassword.addActionListener(loginAction);
        formWrapper.setLayout(null);
        form.setLayout(null);

        form.add(lblUsername);
        form.add(lblPassword);
        form.add(txtPassword);
        form.add(errorLabel);
        form.add(loginButton);
        formWrapper.add(form);
        
                txtUsername = new JTextField();
                txtUsername.setBounds(106, 36, 234, 28);
                form.add(txtUsername);
                txtUsername.setFont(new Font("Dialog", Font.PLAIN, 13));
                txtUsername.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ROW_BORDER, 1),
                    new EmptyBorder(4, 8, 4, 8)));
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