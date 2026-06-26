package threeGWheels;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class updateRental extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final Color DARK_BLUE = new Color(10, 25, 70);
    private static final Color WHITE = Color.WHITE;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final JFrame detailsFrame;
    private final rentalParent mainFrame;
    private final int rentalID;
    private JComboBox<String> cbStatus;

    public updateRental(JFrame detailsFrame, rentalParent mainFrame, int rentalId, String currentStatus) {

        super(detailsFrame, "UPDATE RENTAL \u2013 ID " + rentalId, true);
        this.detailsFrame = detailsFrame;
        this.mainFrame = mainFrame;
        this.rentalID = rentalId;

        setBounds(250, 120, 520, 220);
        setResizable(false);

        JPanel dialogPane = new JPanel(new BorderLayout());
        dialogPane.setBackground(WHITE);
        setContentPane(dialogPane);

        JPanel dialogHeader = new JPanel(new BorderLayout());
        dialogHeader.setBackground(DARK_BLUE);
        dialogHeader.setPreferredSize(new Dimension(520, 45));
        dialogPane.add(dialogHeader, BorderLayout.NORTH);

        JLabel dialogTitle = new JLabel("UPDATE RENTAL STATUS  \u2013  ID " + rentalId);
        dialogTitle.setForeground(WHITE);
        dialogTitle.setFont(new Font("Dialog", Font.BOLD, 13));
        dialogTitle.setHorizontalAlignment(SwingConstants.CENTER);
        dialogHeader.add(dialogTitle, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(WHITE);
        formPanel.setBorder(new EmptyBorder(12, 14, 12, 14));
        dialogPane.add(formPanel, BorderLayout.CENTER);

        JLabel lblStatus = new JLabel("STATUS:  *");
        lblStatus.setFont(new Font("Dialog", Font.BOLD, 11));
        lblStatus.setForeground(DARK_BLUE);
        lblStatus.setBounds(10, 36, 230, 18);
        formPanel.add(lblStatus);

        cbStatus = new JComboBox<>(new String[]{"Pending", "Confirmed", "Ongoing", "Completed", "Cancelled"});
        if (currentStatus != null) {
            for (int i = 0; i < cbStatus.getItemCount(); i++) {
                if (cbStatus.getItemAt(i).equalsIgnoreCase(currentStatus)) {
                    cbStatus.setSelectedIndex(i);
                    break;
                }
            }
        }
        cbStatus.setBounds(259, 33, 220, 26);
        formPanel.add(cbStatus);

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

        JButton saveButton = new JButton("UPDATE STATUS");
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
        String newStatus = (String) cbStatus.getSelectedItem();
        String sql = "UPDATE Rental SET status = ? WHERE rental_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, rentalID);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Rental status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose();
            detailsFrame.dispose();
            if (mainFrame != null) mainFrame.refresh();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}