package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class adminDashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color DARK_BLUE  = new Color(10, 25, 70);
    private static final Color HOVER_BLUE = new Color(20, 45, 110);
    private static final Color WHITE = Color.WHITE;

    public adminDashboard() {
        setTitle("3G Wheels – Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 520);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(WHITE);
        setContentPane(contentPane);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setPreferredSize(new Dimension(420, 80));
        contentPane.add(header, BorderLayout.NORTH);

        JLabel companyName = new JLabel("🚘 3G WHEELS");
        companyName.setForeground(WHITE);
        companyName.setFont(new Font("Dialog", Font.BOLD, 26));
        companyName.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(companyName, BorderLayout.CENTER);

        JPanel menuWrapper = new JPanel();
        menuWrapper.setBackground(WHITE);
        contentPane.add(menuWrapper, BorderLayout.CENTER);
        menuWrapper.setLayout(null);

        JPanel cards = new JPanel();
        cards.setBounds(56, 26, 300, 326);
        cards.setBackground(WHITE);
        cards.setPreferredSize(new Dimension(300, 326));
        menuWrapper.add(cards);
        cards.setLayout(null);

        JPanel vehicleCard = new JPanel(new BorderLayout());
        vehicleCard.setBounds(0, 0, 300, 68);
        vehicleCard.setBackground(DARK_BLUE);
        vehicleCard.setBorder(new EmptyBorder(0, 18, 0, 18));
        vehicleCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cards.add(vehicleCard);

        JLabel vehicleLabel = new JLabel("VEHICLE");
        vehicleLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        vehicleLabel.setForeground(WHITE);
        vehicleCard.add(vehicleLabel, BorderLayout.WEST);

        JLabel vehicleArrow = new JLabel(">");
        vehicleArrow.setFont(new Font("Dialog", Font.BOLD, 17));
        vehicleArrow.setForeground(WHITE);
        vehicleCard.add(vehicleArrow, BorderLayout.EAST);
        
        
        vehicleCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                vehicleParent vp = new vehicleParent();
                vp.setVisible(true);
                setVisible(false);
            }
            public void mouseEntered(MouseEvent e) { vehicleCard.setBackground(HOVER_BLUE); }
            public void mouseExited(MouseEvent e)  { vehicleCard.setBackground(DARK_BLUE);  }
        });

        JPanel customerCard = new JPanel(new BorderLayout());
        customerCard.setBounds(0, 86, 300, 68);
        customerCard.setBackground(DARK_BLUE);
        customerCard.setBorder(new EmptyBorder(0, 18, 0, 18));
        customerCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cards.add(customerCard);

        JLabel customerLabel = new JLabel("CUSTOMER");
        customerLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        customerLabel.setForeground(WHITE);
        customerCard.add(customerLabel, BorderLayout.WEST);

        JLabel customerArrow = new JLabel(">");
        customerArrow.setFont(new Font("Dialog", Font.BOLD, 17));
        customerArrow.setForeground(WHITE);
        customerCard.add(customerArrow, BorderLayout.EAST);

        customerCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	customerParent cstmp = new customerParent();
                cstmp.setVisible(true);
                setVisible(false);
            }
            public void mouseEntered(MouseEvent e) { customerCard.setBackground(HOVER_BLUE); }
            public void mouseExited(MouseEvent e)  { customerCard.setBackground(DARK_BLUE);  }
        });

        JPanel reservationCard = new JPanel(new BorderLayout());
        reservationCard.setBounds(0, 172, 300, 68);
        reservationCard.setBackground(DARK_BLUE);
        reservationCard.setBorder(new EmptyBorder(0, 18, 0, 18));
        reservationCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cards.add(reservationCard);

        JLabel reservationLabel = new JLabel("RESERVATION");
        reservationLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        reservationLabel.setForeground(WHITE);
        reservationCard.add(reservationLabel, BorderLayout.WEST);

        JLabel reservationArrow = new JLabel(">");
        reservationArrow.setFont(new Font("Dialog", Font.BOLD, 17));
        reservationArrow.setForeground(WHITE);
        reservationCard.add(reservationArrow, BorderLayout.EAST);

        reservationCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	rentalParent rp = new rentalParent();
                rp.setVisible(true);
                setVisible(false);
            }
            public void mouseEntered(MouseEvent e) { reservationCard.setBackground(HOVER_BLUE); }
            public void mouseExited(MouseEvent e)  { reservationCard.setBackground(DARK_BLUE);  }
        });

        JPanel reportsCard = new JPanel(new BorderLayout());
        reportsCard.setBounds(0, 258, 300, 68);
        reportsCard.setBackground(DARK_BLUE);
        reportsCard.setBorder(new EmptyBorder(0, 18, 0, 18));
        reportsCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cards.add(reportsCard);

        JLabel reportsLabel = new JLabel("GENERATE REPORTS");
        reportsLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        reportsLabel.setForeground(WHITE);
        reportsCard.add(reportsLabel, BorderLayout.WEST);

        JLabel reportsArrow = new JLabel(">");
        reportsArrow.setFont(new Font("Dialog", Font.BOLD, 17));
        reportsArrow.setForeground(WHITE);
        reportsCard.add(reportsArrow, BorderLayout.EAST);

        reportsCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                reportsParent rpt = new reportsParent();
                rpt.setVisible(true);
                setVisible(false);
            }
            public void mouseEntered(MouseEvent e) { reportsCard.setBackground(HOVER_BLUE); }
            public void mouseExited(MouseEvent e)  { reportsCard.setBackground(DARK_BLUE);  }
        });

        JPanel footer = new JPanel();
        footer.setBackground(WHITE);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(210, 218, 240)));
        contentPane.add(footer, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Logout");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		adminLogin aL = new adminLogin();
        		aL.setVisible(true);
        		setVisible(false);
        	}
        });
        footer.add(btnNewButton);
    }
}