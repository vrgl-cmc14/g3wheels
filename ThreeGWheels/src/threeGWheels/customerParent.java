package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class customerParent extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color ROW_WHITE = Color.WHITE;
    private static final Color ROW_LIGHT = new Color(240, 243, 252);
    private static final Color WHITE = Color.WHITE;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private JPanel rowsPanel;

    public customerParent() {
        setTitle("3G Wheels – Customer Parent");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 620, 660);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel headerBanner = new JPanel(new BorderLayout());
        headerBanner.setBackground(DARK_BLUE);
        headerBanner.setPreferredSize(new Dimension(620, 70));

        JLabel companyName = new JLabel("🚘 3G WHEELS");
        companyName.setForeground(WHITE);
        companyName.setFont(new Font("Dialog", Font.BOLD, 26));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        headerBanner.add(companyName, BorderLayout.CENTER);
        contentPane.add(headerBanner, BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(WHITE);
        body.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(body, BorderLayout.CENTER);

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setBackground(WHITE);
        toolbar.setBorder(new EmptyBorder(8, 10, 8, 10));

        JLabel pageTitle = new JLabel("CUSTOMER RECORDS");
        pageTitle.setFont(new Font("Dialog", Font.BOLD, 14));
        pageTitle.setForeground(DARK_BLUE);
        toolbar.add(pageTitle, BorderLayout.WEST);

        JPanel btnGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btnGroup.setBackground(WHITE);

        JButton searchButton = new JButton("Search a Customer");
        searchButton.setBackground(new Color(0, 0, 160));
        searchButton.setForeground(WHITE);
        searchButton.setFont(new Font("Dialog", Font.BOLD, 11));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(e -> {
            searchCustomer searchFrame = new searchCustomer(this);
            searchFrame.setVisible(true);
        });
        btnGroup.add(searchButton);

        JButton addButton = new JButton("Add a Customer Record");
        addButton.setBackground(new Color(0, 64, 128));
        addButton.setForeground(WHITE);
        addButton.setFont(new Font("Dialog", Font.BOLD, 11));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGroup.add(addButton);

        toolbar.add(btnGroup, BorderLayout.EAST);
        body.add(toolbar, BorderLayout.NORTH);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBackground(WHITE);
        tableWrapper.setBorder(null);

        JPanel headerRow = new JPanel(null);
        headerRow.setBackground(MID_BLUE);
        headerRow.setPreferredSize(new Dimension(620, 36));

        addHeaderCell(headerRow, "ID",            20,  36, 80,  36);
        addHeaderCell(headerRow, "USERNAME",      100, 36, 180, 36);
        addHeaderCell(headerRow, "EMAIL ADDRESS", 280, 36, 260, 36);

        tableWrapper.add(headerRow, BorderLayout.NORTH);

        rowsPanel = new JPanel();
        rowsPanel.setLayout(new BoxLayout(rowsPanel, BoxLayout.Y_AXIS));
        rowsPanel.setBackground(WHITE);

        loadCustomerRows(rowsPanel);

        JScrollPane scrollPane = new JScrollPane(rowsPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        body.add(tableWrapper, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        footer.setBackground(new Color(235, 238, 248));

        JButton backButton = new JButton("BACK");
        backButton.setBackground(WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Dialog", Font.BOLD, 11));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            dispose();
            adminDashboard dashboard = new adminDashboard();
            dashboard.setVisible(true);
        });

        footer.add(backButton);
        contentPane.add(footer, BorderLayout.SOUTH);
    }

    public void reloadTable() {
        rowsPanel.removeAll();
        loadCustomerRows(rowsPanel);
        rowsPanel.revalidate();
        rowsPanel.repaint();
    }

    private void loadCustomerRows(JPanel panel) {
        String sql = "SELECT customer_id, first_name, middle_name, last_name, suffix, " +
                     "date_of_birth, email_address, phone_number, " +
                     "driver_license_number, address, username, password " +
                     "FROM Customer ORDER BY customer_id";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean alternate = false;
            while (rs.next()) {
                int    id = rs.getInt("customer_id");
                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String lastName = rs.getString("last_name");
                String suffix = rs.getString("suffix");
                String dob = rs.getString("date_of_birth");
                String email = rs.getString("email_address");
                String phone = rs.getString("phone_number");
                String licenseNum = rs.getString("driver_license_number");
                String address = rs.getString("address");
                String username = rs.getString("username");
                String password = rs.getString("password");

                panel.add(buildRow(
                    id, firstName, middleName, lastName, suffix,
                    dob, email, phone, licenseNum, address,
                    username, password, alternate
                ));
                alternate = !alternate;
            }

        } catch (Exception ex) {
            JPanel errorRow = new JPanel();
            errorRow.setBackground(ROW_WHITE);
            JLabel errorLabel = new JLabel("Error loading data: " + ex.getMessage());
            errorLabel.setForeground(Color.RED);
            errorRow.add(errorLabel);
            panel.add(errorRow);
            ex.printStackTrace();
        }
    }

    private JPanel buildRow(int id, String firstName, String middleName, String lastName,
                            String suffix, String dob, String email, String phone,
                            String licenseNum, String address, String username,
                            String password, boolean alternate) {

        JPanel row = new JPanel(null);
        row.setBackground(alternate ? ROW_LIGHT : ROW_WHITE);
        row.setPreferredSize(new Dimension(620, 38));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 225, 240)));

        JLabel idLabel = new JLabel(String.valueOf(id));
        idLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        idLabel.setForeground(MID_BLUE);
        idLabel.setBounds(20, 0, 80, 38);
        row.add(idLabel);

        JLabel userLabel = new JLabel(username != null ? username.toUpperCase() : "—");
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        userLabel.setForeground(MID_BLUE);
        userLabel.setBounds(100, 0, 180, 38);
        row.add(userLabel);

        JLabel emailLabel = new JLabel(email != null ? email : "—");
        emailLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        emailLabel.setForeground(MID_BLUE);
        emailLabel.setBounds(280, 0, 250, 38);
        row.add(emailLabel);

        JButton viewButton = new JButton("VIEW");
        viewButton.setBackground(DARK_BLUE);
        viewButton.setForeground(WHITE);
        viewButton.setFont(new Font("Dialog", Font.BOLD, 11));
        viewButton.setFocusPainted(false);
        viewButton.setBorderPainted(false);
        viewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewButton.setBounds(530, 5, 70, 28);
        viewButton.addActionListener(e -> {
            customerDetails details = new customerDetails(
                id, firstName, middleName, lastName, suffix,
                dob, email, phone, licenseNum, address, username, password,
                this
            );
            details.setVisible(true);
        });
        row.add(viewButton);

        return row;
    }

    private void addHeaderCell(JPanel panel, String text, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl.setForeground(WHITE);
        lbl.setBounds(x, 0, w, h);
        panel.add(lbl);
    }
}