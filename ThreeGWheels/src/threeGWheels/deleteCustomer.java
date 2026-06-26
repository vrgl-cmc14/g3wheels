package threeGWheels;

import javax.swing.*;
import java.sql.*;

public class deleteCustomer {

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final JFrame detailsFrame;
    private final customerParent mainFrame;
    private final int customerID;

    public deleteCustomer(JFrame detailsFrame, customerParent mainFrame, int customerID) {
        this.detailsFrame = detailsFrame;
        this.mainFrame = mainFrame;
        this.customerID = customerID;
    }

    public void confirmAndDelete() {
        int confirm = JOptionPane.showConfirmDialog(
            detailsFrame,
            "Are you sure you want to delete Customer ID " + customerID + "?\nThis action cannot be undone.",
            "CONFIRM DELETE",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            deleteRecord();
        }
    }

    private void deleteRecord() {
        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerID);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(detailsFrame,
                    "Customer deleted successfully.",
                    "Deleted", JOptionPane.INFORMATION_MESSAGE);

            detailsFrame.dispose();
            if (mainFrame != null) mainFrame.reloadTable();

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(detailsFrame,
                    "Cannot delete: this customer has related records (e.g. rentals).",
                    "Delete Failed", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(detailsFrame,
                    "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}