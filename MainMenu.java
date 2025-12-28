import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu {
    private static JButton createButton(String text, int x, int y) {
    JButton btn = new JButton(text);
    btn.setBounds(x, y, 380, 80);
    btn.setFont(new Font("Segoe UI", Font.BOLD, 25));
    btn.setForeground(Color.WHITE);
    btn.setBackground(new Color(52, 152, 219));
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);
    btn.setOpaque(true);

    btn.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            btn.setBackground(new Color(41, 128, 185));
        }

        public void mouseExited(MouseEvent e) {
            btn.setBackground(new Color(52, 152, 219));
        }
    });

    return btn;
}
    
    public static void main(String[] args) {

        JFrame window = new JFrame("Practice Window");
        window.setLayout(null);
        window.setSize(1980, 1080);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Hospital Management System");
        title.setBounds(390, 20, 1080, 100);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        window.add(title);

        JButton admin = createButton("Admin Login Page", 750, 200);
        admin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            AdminLoginGUI.openAdminLogin(window);
        }
    });

        window.add(admin);

        JButton doctor = createButton("Doctor Login Page", 750, 400);

        window.add(doctor);

        JButton patient = createButton("Patient Login Page", 750, 600);

        window.add(patient);

        window.setVisible(true);
    }
}
