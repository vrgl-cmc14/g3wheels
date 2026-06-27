package threeGWheels;

import javax.swing.*;
import java.sql.*;

public class deleteAccount {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final accounts parentFrame;
    private final int customerID;

    public deleteAccount(accounts parentFrame, int customerID) {
        this.parentFrame = parentFrame;
        this.customerID = customerID;
    }

    public void confirmAndDelete() {
        int confirm = JOptionPane.showConfirmDialog(
            parentFrame,
            "Are you sure you want to delete your account?\nThis action cannot be undone.",
            "CONFIRM DELETE",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            deleteRecord();
        }
    }

    private void deleteRecord() {
        String deletePayments = "DELETE FROM Payment WHERE rental_id IN " +
                                "(SELECT rental_id FROM Rental WHERE customer_id = ?)";
        String deleteRentals  = "DELETE FROM Rental WHERE customer_id = ?";
        String deleteCustomer = "DELETE FROM Customer WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(deletePayments);
                 PreparedStatement ps2 = conn.prepareStatement(deleteRentals);
                 PreparedStatement ps3 = conn.prepareStatement(deleteCustomer)) {

                ps1.setInt(1, customerID);
                ps1.executeUpdate();

                ps2.setInt(1, customerID);
                ps2.executeUpdate();

                ps3.setInt(1, customerID);
                ps3.executeUpdate();

                conn.commit();

                JOptionPane.showMessageDialog(parentFrame,
                    "Your account has been deleted.",
                    "3G Wheels", JOptionPane.INFORMATION_MESSAGE);

                parentFrame.dispose();
                new index().setVisible(true);

            } catch (Exception ex) {
                conn.rollback();
                JOptionPane.showMessageDialog(parentFrame,
                    "Delete failed. All changes have been rolled back.\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                "Database connection error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}