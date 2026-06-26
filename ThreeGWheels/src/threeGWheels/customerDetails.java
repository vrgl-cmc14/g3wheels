package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class customerDetails extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;

    public customerDetails(
            int id,
            String firstName, String middleName, String lastName,
            String suffix, String dob, String email, String phone,
            String licenseNum, String address, String username, String password,
            customerParent parent) {

        setTitle("3G Wheels – Customer Details");
        setBounds(200, 150, 420, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(700, 70));

        JLabel title = new JLabel("CUSTOMER DETAILS");
        title.setForeground(WHITE);
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        contentPane.add(header, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(WHITE);
        detailsPanel.setLayout(new GridLayout(12, 2, 10, 10));
        detailsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        addField(detailsPanel, "Customer ID:", String.valueOf(id));
        addField(detailsPanel, "First Name:", firstName);
        addField(detailsPanel, "Middle Name:", middleName);
        addField(detailsPanel, "Last Name:", lastName);
        addField(detailsPanel, "Suffix:", suffix);
        addField(detailsPanel, "Date of Birth:", dob);
        addField(detailsPanel, "Email Address:", email);
        addField(detailsPanel, "Phone Number:", phone);
        addField(detailsPanel, "Driver License Number:", licenseNum);
        addField(detailsPanel, "Address:", address);
        addField(detailsPanel, "Username:", username);
        addField(detailsPanel, "Password:", password);

        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setBorder(null);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        footer.setBackground(new Color(235, 238, 248));

        JButton closeButton = new JButton("CLOSE");
        closeButton.setFont(new Font("Dialog", Font.BOLD, 11));
        closeButton.addActionListener(e -> dispose());

        JButton updateButton = new JButton("UPDATE");
        updateButton.setFont(new Font("Dialog", Font.BOLD, 11));
        updateButton.addActionListener(e -> {
            updateCustomer dialog = new updateCustomer(
                    this, parent, id,
                    firstName, middleName, lastName,
                    suffix, dob, email, phone,
                    licenseNum, address, username, password);
            dialog.setVisible(true);
        });

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Dialog", Font.BOLD, 11));
        deleteButton.addActionListener(e -> {
            deleteCustomer deleter = new deleteCustomer(this, parent, id);
            deleter.confirmAndDelete();
        });

        footer.add(closeButton);
        footer.add(updateButton);
        footer.add(deleteButton);
        contentPane.add(footer, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, String label, String value) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Dialog", Font.BOLD, 12));

        JLabel val = new JLabel(value == null || value.trim().isEmpty() ? "N/A" : value);
        val.setFont(new Font("Dialog", Font.PLAIN, 12));

        panel.add(lbl);
        panel.add(val);
    }
}