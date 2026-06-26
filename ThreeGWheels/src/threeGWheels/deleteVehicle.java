package threeGWheels;

import javax.swing.*;
import java.sql.*;

public class deleteVehicle {

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "haha";

    private final JFrame detailsFrame;
    private final vehicleParent mainFrame;
    private final int vehicleID;

    public deleteVehicle(JFrame detailsFrame, vehicleParent mainFrame, int vehicleID) {
        this.detailsFrame = detailsFrame;
        this.mainFrame = mainFrame;
        this.vehicleID = vehicleID;
    }

    public void confirmAndDelete() {
        int confirm = JOptionPane.showConfirmDialog(
            detailsFrame,
            "Are you sure you want to delete Vehicle ID " + vehicleID + "?\nThis action cannot be undone.",
            "CONFIRM DELETE",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            deleteRecord();
        }
    }

    private void deleteRecord() {
        String sql = "DELETE FROM Vehicle WHERE vehicle_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehicleID);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(detailsFrame, "Vehicle deleted successfully.",
                "Deleted", JOptionPane.INFORMATION_MESSAGE);

            detailsFrame.dispose();
            if (mainFrame != null) mainFrame.refresh();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(detailsFrame, "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}