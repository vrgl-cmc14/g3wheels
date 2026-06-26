package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class searchCustomer extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color MID_BLUE = new Color(20, 50, 120);
    private static final Color WHITE = Color.WHITE;
    private static final Color ROW_ALT = new Color(240, 243, 252);
    private static final Color ROW_BORDER = new Color(220, 225, 240);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final customerParent mainFrame;

    private JTextField idSearchField;
    private JTextField emailSearchField;
    private JTextField usernameSearchField;
    private JPanel recordsPanel;

    public searchCustomer(customerParent mainFrame) {
        this.mainFrame = mainFrame;

        setTitle("3G Wheels \u2013 Search Customer Records");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 100, 780, 660);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(780, 70));

        JLabel titleLbl = new JLabel("SEARCH CUSTOMER RECORDS");
        titleLbl.setForeground(WHITE);
        titleLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(titleLbl, BorderLayout.CENTER);
        contentPane.add(header, BorderLayout.NORTH);

        JPanel searchBar = new JPanel(new GridLayout(3, 1, 0, 4));
        searchBar.setBackground(WHITE);
        searchBar.setBorder(new EmptyBorder(10, 14, 10, 14));

        JPanel idRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        idRow.setBackground(WHITE);

        JLabel idLbl = new JLabel("Customer ID:");
        idLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        idLbl.setForeground(DARK_BLUE);
        idLbl.setPreferredSize(new Dimension(100, 26));
        idRow.add(idLbl);

        idSearchField = new JTextField();
        idSearchField.setPreferredSize(new Dimension(220, 26));
        idSearchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        idRow.add(idSearchField);

        JButton idSearchBtn = new JButton("SEARCH BY ID");
        idSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        idSearchBtn.setBackground(DARK_BLUE);
        idSearchBtn.setForeground(WHITE);
        idSearchBtn.setFocusPainted(false);
        idSearchBtn.setOpaque(true);
        idSearchBtn.setBorderPainted(false);
        idSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        idSearchBtn.addActionListener(e -> performSearch("id"));
        idSearchField.addActionListener(e -> performSearch("id"));
        idRow.add(idSearchBtn);

        searchBar.add(idRow);

        JPanel emailRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        emailRow.setBackground(WHITE);

        JLabel emailLbl = new JLabel("Email:");
        emailLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        emailLbl.setForeground(DARK_BLUE);
        emailLbl.setPreferredSize(new Dimension(100, 26));
        emailRow.add(emailLbl);

        emailSearchField = new JTextField();
        emailSearchField.setPreferredSize(new Dimension(220, 26));
        emailSearchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        emailRow.add(emailSearchField);

        JButton emailSearchBtn = new JButton("SEARCH BY EMAIL");
        emailSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        emailSearchBtn.setBackground(DARK_BLUE);
        emailSearchBtn.setForeground(WHITE);
        emailSearchBtn.setFocusPainted(false);
        emailSearchBtn.setOpaque(true);
        emailSearchBtn.setBorderPainted(false);
        emailSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        emailSearchBtn.addActionListener(e -> performSearch("email"));
        emailSearchField.addActionListener(e -> performSearch("email"));
        emailRow.add(emailSearchBtn);

        searchBar.add(emailRow);

        JPanel userRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        userRow.setBackground(WHITE);

        JLabel userLbl = new JLabel("Username:");
        userLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        userLbl.setForeground(DARK_BLUE);
        userLbl.setPreferredSize(new Dimension(100, 26));
        userRow.add(userLbl);

        usernameSearchField = new JTextField();
        usernameSearchField.setPreferredSize(new Dimension(220, 26));
        usernameSearchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        userRow.add(usernameSearchField);

        JButton userSearchBtn = new JButton("SEARCH BY USERNAME");
        userSearchBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        userSearchBtn.setBackground(DARK_BLUE);
        userSearchBtn.setForeground(WHITE);
        userSearchBtn.setFocusPainted(false);
        userSearchBtn.setOpaque(true);
        userSearchBtn.setBorderPainted(false);
        userSearchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        userSearchBtn.addActionListener(e -> performSearch("username"));
        usernameSearchField.addActionListener(e -> performSearch("username"));
        userRow.add(userSearchBtn);

        searchBar.add(userRow);

        contentPane.add(searchBar, BorderLayout.NORTH);

        JPanel centerArea = new JPanel(new BorderLayout());
        centerArea.setBackground(WHITE);

        JPanel tableHeader = new JPanel(null);
        tableHeader.setBackground(MID_BLUE);
        tableHeader.setPreferredSize(new Dimension(780, 36));

        tableHeader.add(makeHeaderLabel("ID", 20, 60));
        tableHeader.add(makeHeaderLabel("USERNAME", 85, 140));
        tableHeader.add(makeHeaderLabel("EMAIL", 230, 230));
        tableHeader.add(makeHeaderLabel("PHONE", 465, 130));

        centerArea.add(tableHeader, BorderLayout.NORTH);

        recordsPanel = new JPanel();
        recordsPanel.setLayout(new BoxLayout(recordsPanel, BoxLayout.Y_AXIS));
        recordsPanel.setBackground(WHITE);

        centerArea.add(recordsPanel, BorderLayout.CENTER);

        contentPane.add(centerArea, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        footer.setBackground(new Color(235, 238, 248));
        footer.setBorder(new MatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));

        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> dispose());
        footer.add(closeBtn);

        contentPane.add(footer, BorderLayout.SOUTH);
    }

    private void performSearch(String mode) {
        String keyword;
        String sql;
        String baseSelect =
            "SELECT customer_id, first_name, middle_name, last_name, suffix, " +
            "date_of_birth, email_address, phone_number, " +
            "driver_license_number, address, username, password " +
            "FROM Customer WHERE ";

        switch (mode) {
            case "id":
                keyword = idSearchField.getText().trim();
                sql = baseSelect + "CAST(customer_id AS CHAR) LIKE ? ORDER BY customer_id";
                break;
            case "email":
                keyword = emailSearchField.getText().trim();
                sql = baseSelect + "email_address LIKE ? ORDER BY customer_id";
                break;
            default:
                keyword = usernameSearchField.getText().trim();
                sql = baseSelect + "username LIKE ? ORDER BY customer_id";
                break;
        }

        if (keyword.isEmpty()) {
            recordsPanel.removeAll();
            recordsPanel.revalidate();
            recordsPanel.repaint();
            return;
        }

        recordsPanel.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            boolean hasRecords = false;
            int rowIndex = 0;

            while (rs.next()) {
                hasRecords = true;

                int id = rs.getInt("customer_id");
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

                Color rowBg = (rowIndex % 2 == 0) ? WHITE : ROW_ALT;

                JPanel row = new JPanel(null);
                row.setBackground(rowBg);
                row.setPreferredSize(new Dimension(780, 38));
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
                row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ROW_BORDER));

                row.add(makeRowLabel(String.valueOf(id), 20, 60));
                row.add(makeRowLabel(username != null ? username.toUpperCase() : "—", 85, 140));
                row.add(makeRowLabel(email != null ? email : "—", 230, 230));
                row.add(makeRowLabel(phone != null ? phone : "—", 465, 130));

                JButton viewBtn = new JButton("VIEW");
                viewBtn.setBounds(610, 5, 70, 28);
                viewBtn.setBackground(DARK_BLUE);
                viewBtn.setForeground(WHITE);
                viewBtn.setFont(new Font("Dialog", Font.BOLD, 10));
                viewBtn.setFocusPainted(false);
                viewBtn.setBorderPainted(false);
                viewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                viewBtn.addActionListener(e -> {
                    customerDetails details = new customerDetails(
                        id, firstName, middleName, lastName, suffix,
                        dob, email, phone, licenseNum, address,
                        username, password, mainFrame
                    );
                    details.setVisible(true);
                });
                row.add(viewBtn);

                recordsPanel.add(row);
                rowIndex++;
            }
            rs.close();

            if (!hasRecords) {
                JLabel noRecords = new JLabel("NO RESULTS FOUND.");
                noRecords.setFont(new Font("Dialog", Font.BOLD, 12));
                noRecords.setForeground(new Color(150, 160, 180));
                noRecords.setHorizontalAlignment(SwingConstants.CENTER);
                noRecords.setAlignmentX(Component.CENTER_ALIGNMENT);
                recordsPanel.add(Box.createVerticalStrut(30));
                recordsPanel.add(noRecords);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        recordsPanel.revalidate();
        recordsPanel.repaint();
    }

    private JLabel makeHeaderLabel(String text, int x, int width) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(WHITE);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));
        lbl.setBounds(x, 0, width, 36);
        return lbl;
    }

    private JLabel makeRowLabel(String text, int x, int width) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        lbl.setForeground(MID_BLUE);
        lbl.setBounds(x, 0, width, 38);
        return lbl;
    }
}