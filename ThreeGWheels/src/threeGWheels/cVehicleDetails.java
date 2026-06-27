package threeGWheels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class cVehicleDetails extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color CARD_BLUE = new Color(10, 45, 110);
    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_GRAY = new Color(200, 210, 230);
    private static final Color ROW_BASE = new Color(230, 235, 245);
    private static final Color ROW_ALT = new Color(210, 218, 238);

    public cVehicleDetails(int customerId, int vehicleId, String type, String brand, String model, int yearModel, String plate, String regNum, int seats, int rate, String fuel,
    						String transmission, int mileage, String firstName, String middleName, String lastName, String suffix, String dob, String email, String phone,
                            String licenseNum, String address, String username, String password) {

        setTitle("3G Wheels - Vehicle Details");
        setMinimumSize(new Dimension(500, 400));
        setSize(500, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_BLUE);
        setContentPane(root);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setBorder(new EmptyBorder(22, 0, 22, 0));
        JLabel title = new JLabel("VEHICLE DETAILS");
        title.setForeground(WHITE);
        title.setFont(new Font("Dialog", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(DARK_BLUE);
        content.setBorder(new EmptyBorder(0, 16, 16, 16));

        boolean[] alt = {false};

        addSectionBar(content, "VEHICLE INFORMATION");
        addRow(content, "Vehicle ID", String.valueOf(vehicleId), alt[0]); alt[0] = !alt[0];
        addRow(content, "Vehicle Type", type, alt[0]); alt[0] = !alt[0];
        addRow(content, "Brand", brand, alt[0]); alt[0] = !alt[0];
        addRow(content, "Model", model, alt[0]); alt[0] = !alt[0];
        addRow(content, "Year Model", String.valueOf(yearModel), alt[0]); alt[0] = !alt[0];

        addSectionBar(content, "REGISTRATION");
        alt[0] = false;
        addRow(content, "License Plate No.", plate, alt[0]); alt[0] = !alt[0];
        addRow(content, "Registration No.", regNum, alt[0]);

        addSectionBar(content, "RATE");
        addRow(content, "Rental Rate", "" + rate + " / day", false);

        addSectionBar(content, "OTHER ATTRIBUTES");
        alt[0] = false;
        addRow(content, "Seating Capacity", seats + " seats", alt[0]); alt[0] = !alt[0];
        addRow(content, "Fuel Type", fuel, alt[0]); alt[0] = !alt[0];
        addRow(content, "Transmission", transmission, alt[0]); alt[0] = !alt[0];
        addRow(content, "Mileage", mileage + " km", alt[0]);

        root.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        footer.setBackground(DARK_BLUE);

        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setBackground(new Color(30, 50, 100));
        closeBtn.setForeground(LIGHT_GRAY);
        closeBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> dispose());

        JButton rentBtn = new JButton("RENT NOW");
        rentBtn.setBackground(new Color(30, 70, 160));
        rentBtn.setForeground(WHITE);
        rentBtn.setFont(new Font("Dialog", Font.BOLD, 11));
        rentBtn.setFocusPainted(false);
        rentBtn.setBorderPainted(false);
        rentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rentBtn.addActionListener(e ->
            new cRentForm(
                customerId,
                vehicleId, type, brand, model, rate,
                firstName, middleName, lastName, suffix, dob,
                email, phone, licenseNum, address, username, password
            ).setVisible(true)
        );

        footer.add(closeBtn);
        footer.add(rentBtn);
        root.add(footer, BorderLayout.SOUTH);
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

    private void addRow(JPanel parent, String label, String value, boolean alternate) {
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
        JLabel keyLbl = new JLabel(label + ":");
        keyLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        keyLbl.setForeground(DARK_BLUE);
        keyLbl.setPreferredSize(new Dimension(190, 36));
        String display = (value == null || value.isBlank()) ? "-" : value;
        JLabel valLbl = new JLabel(display);
        valLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        valLbl.setForeground(new Color(30, 30, 80));
        row.add(keyLbl, BorderLayout.WEST);
        row.add(valLbl, BorderLayout.CENTER);
        parent.add(row);
    }
}