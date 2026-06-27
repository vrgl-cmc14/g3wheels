package threeGWheels;

import java.awt.*;
import javax.swing.*;

public abstract class Abstraction extends JFrame {

    private static final long serialVersionUID = 1L;

    protected static final Color DARK_BLUE = new Color(0, 25, 80);
    protected static final Color CARD_BLUE = new Color(10, 45, 110);
    protected static final Color CARD_HOVER = new Color(20, 70, 160);
    protected static final Color ROW_ALT = new Color(15, 55, 130);
    protected static final Color ACCENT = new Color(255, 200, 0);
    protected static final Color WHITE = Color.WHITE;
    protected static final Color LIGHT_GRAY = new Color(200, 210, 230);

    public Abstraction(String title, int width, int height) {
        setTitle(title);
        setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected abstract void initComponents();
    protected abstract JPanel buildHeader();
    protected abstract JPanel buildContent();
    protected abstract JPanel buildFooter();

    protected JLabel makeLabel(String text, Color color, int style, int size) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(color);
        lbl.setFont(new Font("Dialog", style, size));
        return lbl;
    }

    protected JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Dialog", Font.BOLD, 11));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}