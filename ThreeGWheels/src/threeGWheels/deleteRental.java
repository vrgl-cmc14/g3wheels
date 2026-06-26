package threeGWheels;

import javax.swing.*;
import java.sql.*;

public class deleteRental {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final JFrame detailsFrame;
    private final rentalParent mainFrame;
    private final int rentalID;

    public deleteRental(JFrame detailsFrame, rentalParent mainFrame, int rentalID) {
        this.detailsFrame = detailsFrame;
        this.mainFrame = mainFrame;
        this.rentalID  = rentalID;
    }

    public void confirmAndDelete() {
        int confirm = JOptionPane.showConfirmDialog(
            detailsFrame,
            "Are you sure you want to delete Rental ID " + rentalID + "?\nThis action cannot be undone.",
            "CONFIRM DELETE",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            deleteRecord();
        }
    }

    private void deleteRecord() {
        String deletePayment = "DELETE FROM Payment WHERE rental_id = ?";
        String deleteRental  = "DELETE FROM Rental WHERE rental_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(deletePayment);
                 PreparedStatement ps2 = conn.prepareStatement(deleteRental)) {

                ps1.setInt(1, rentalID);
                ps1.executeUpdate();

                ps2.setInt(1, rentalID);
                ps2.executeUpdate();

                conn.commit();

                JOptionPane.showMessageDialog(detailsFrame, "Rental deleted successfully.",
                    "Deleted", JOptionPane.INFORMATION_MESSAGE);
                detailsFrame.dispose();
                if (mainFrame != null) mainFrame.refresh();

            } catch (Exception ex) {
                conn.rollback();
                JOptionPane.showMessageDialog(detailsFrame, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(detailsFrame, "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}