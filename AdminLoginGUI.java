import javax.swing.*;
import java.awt.*;


public class AdminLoginGUI {
    public static boolean login = false;
    public static void openAdminLogin(JFrame mainMenuWindow) {

        JFrame frame = new JFrame("Admin Login");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Admin Login");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(120, 20, 200, 40);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 90, 100, 30);

        JTextField userField = new JTextField();
        userField.setBounds(150, 90, 180, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 130, 100, 30);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 130, 180, 30);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(140, 190, 120, 35);

        
        loginBtn.addActionListener( e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (Admin.adminLogin(username, password)) {
                
                mainMenuWindow.dispose();
                frame.dispose();

                AdminDashboard.open();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials");
            }
        });
        
        
        frame.add(title);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginBtn);

        frame.setVisible(true);
        
    }
}
