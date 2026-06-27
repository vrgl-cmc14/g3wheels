package threeGWheels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class menu extends Abstraction {

    private static final long serialVersionUID = 1L;

    private final int customerId;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String suffix;
    private final String dob;
    private final String email;
    private final String phone;
    private final String licenseNum;
    private final String address;
    private final String username;
    private final String password;

    public menu(int customerId, String firstName, String middleName, String lastName, String suffix, String dob, String email, String phone,
                String licenseNum, String address, String username, String password) {

        super("3G Wheels - Menu", 480, 560);

        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.licenseNum = licenseNum;
        this.address = address;
        this.username = username;
        this.password = password;

        initComponents();
    }

    @Override
    protected void initComponents() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(DARK_BLUE);
        setContentPane(root);

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildContent(), BorderLayout.CENTER);
        root.add(buildFooter(), BorderLayout.SOUTH);
    }

    @Override
    protected JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK_BLUE);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(40, 70, 160)),
            new EmptyBorder(24, 0, 24, 0)
        ));

        JLabel logo = makeLabel("🚘 3G WHEELS", WHITE, Font.BOLD, 22);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(logo, BorderLayout.CENTER);

        return header;
    }

    @Override
    protected JPanel buildContent() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBackground(DARK_BLUE);
        outer.setBorder(new EmptyBorder(24, 24, 24, 24));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(CARD_BLUE);
        wrapper.setBorder(BorderFactory.createLineBorder(new Color(40, 70, 160), 2));

        JPanel grid = new JPanel(new GridLayout(3, 1, 0, 0));
        grid.setBackground(CARD_BLUE);

        grid.add(buildCard("HISTORY", e -> new history(customerId).setVisible(true)));
        grid.add(buildCard("OFFERS", e -> openOffers()));
        grid.add(buildCard("ACCOUNTS", e -> openAccounts()));

        wrapper.add(grid, BorderLayout.CENTER);
        outer.add(wrapper, BorderLayout.CENTER);

        return outer;
    }

    @Override
    protected JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 14));
        footer.setBackground(DARK_BLUE);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFocusPainted(false);
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(e -> {
            new index().setVisible(true);
            dispose();
        });

        footer.add(logoutBtn);
        return footer;
    }

    private JPanel buildCard(String title, ActionListener onClick) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD_BLUE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 70, 160)),
            new EmptyBorder(20, 24, 20, 24)
        ));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel titleLbl = makeLabel(title, WHITE, Font.BOLD, 14);
        JLabel arrow = makeLabel(">", ACCENT, Font.BOLD, 16);
        arrow.setHorizontalAlignment(SwingConstants.RIGHT);

        card.add(titleLbl, BorderLayout.WEST);
        card.add(arrow, BorderLayout.EAST);

        card.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { card.setBackground(CARD_HOVER); }
            @Override public void mouseExited(MouseEvent e) { card.setBackground(CARD_BLUE); }
            @Override public void mouseClicked(MouseEvent e) { onClick.actionPerformed(null); }
        });

        return card;
    }

    private void openOffers() {
        new offers(
            customerId, firstName, middleName, lastName,
            suffix, dob, email, phone,
            licenseNum, address, username, password
        ).setVisible(true);
    }

    private void openAccounts() {
        new accounts(customerId).setVisible(true);
        dispose();
    }
}