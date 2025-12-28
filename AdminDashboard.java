import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class AdminDashboard {
    private static JFrame window;
    private static JPanel sidePanel;
    private static JPanel contentPanel;
    private static CardLayout cardLayout;
    
    public static void main(String[] args) {
        open();
    }
    
    public static void open() {
        window = new JFrame("Admin Dashboard");
        window.setLayout(new BorderLayout());
        window.setSize(1980, 1020);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        
        createHeader();
        
        JPanel mainContent = createMainPanel();
        
        window.add(mainContent, BorderLayout.CENTER);
        window.setVisible(true);
    }
    
    public static void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setPreferredSize(new Dimension(0, 100));
        
        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        header.add(title, BorderLayout.CENTER);
        window.add(header, BorderLayout.NORTH);
    }
    
    public static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        sidePanel = createSidePanel();
        
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        
        setupContentViews();
        
        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    public static JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setBackground(new Color(170, 180, 206));
        
        // Add some spacing at top
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Create navigation buttons
        JButton patientBtn = createButton("Patient Management");
        JButton doctorBtn = createButton("Doctor Management");
        JButton logoutBtn = createButton("Logout");
        
        // Add action listeners
        patientBtn.addActionListener(e -> cardLayout.show(contentPanel, "PATIENT"));
        doctorBtn.addActionListener(e -> cardLayout.show(contentPanel, "DOCTOR"));
        logoutBtn.addActionListener(e -> {
            window.dispose();
            MainMenu.main(null);
        });
        
        // Add buttons to sidebar
        sidePanel.add(patientBtn);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(doctorBtn);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(Box.createVerticalGlue());
        sidePanel.add(logoutBtn);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        return sidePanel;
    }
    
    public static void setupContentViews() {
        JPanel patientPanel = createPatientManagementPanel();
        JPanel doctorPanel = createDoctorManagementPanel();
        
        contentPanel.add(patientPanel, "PATIENT");
        contentPanel.add(doctorPanel, "DOCTOR");
        
        cardLayout.show(contentPanel, "PATIENT");
    }
    private static JPanel createDoctorManagementPanel() {
        JPanel doctorPanel = new JPanel(new BorderLayout());
        
        JLabel title = new JLabel("Doctor Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        doctorPanel.add(title, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Manage Data", createDoctorCRUDPanel());
        tabs.addTab("Search", createDoctorSearchPanel());
        tabs.addTab("View Logs", new JPanel());
        
        
        doctorPanel.add(tabs, BorderLayout.CENTER);
        return doctorPanel;
    }
    private static JPanel createDoctorCRUDPanel() {
        Doctors.createDoctorListFile();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel dataEnter = new JPanel();
        dataEnter.setLayout(new BoxLayout(dataEnter, BoxLayout.Y_AXIS));
        JLabel LUser = new JLabel("Username: ");
        JTextField user = new JTextField();

        JLabel LPass = new JLabel(" Password: ");
        JTextField pass = new JTextField();
        JLabel LName = new JLabel(" Full Name: ");
        JTextField name = new JTextField();
        JLabel lDoB = new JLabel(" Enter Doctor DOB: ");
        JTextField dob  = new JTextField();
        JLabel LGen = new JLabel(" Enter Gender: ");
        JTextField gen = new JTextField();
        JLabel LSal = new JLabel(" Enter Monthly Salary: ");
        JTextField sal = new JTextField();
        JLabel LOff = new JLabel(" Enter Office Number: ");
        JTextField offN = new JTextField();
        JLabel LSpec = new JLabel(" Enter Specialization: ");
        JTextField spec = new JTextField();
        
        
        dataEnter.add(LUser);
        dataEnter.add(user);
        dataEnter.add(LPass);
        dataEnter.add(pass);
        dataEnter.add(LName);
        dataEnter.add(name);
        dataEnter.add(lDoB);
        dataEnter.add(dob);
        dataEnter.add(LGen);
        dataEnter.add(gen);
        dataEnter.add(LSal);
        dataEnter.add(sal);
        dataEnter.add(LOff);
        dataEnter.add(offN);
        dataEnter.add(LSpec);
        dataEnter.add(spec);
        


        JPanel btn = new JPanel();
        JButton button = new JButton("Add Doctor");
        button.setMinimumSize(new Dimension(250, 80));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(230, 40));
        button.setFocusPainted(false);
        button.setBackground(new Color(170, 180, 206));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(195, 207, 236));
            }
            
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(133, 141, 161));
            }
        });
        button.addActionListener(e ->{

        if(!user.getText().isEmpty() ||!pass.getText().isEmpty() ||!name.getText().isEmpty() ||!dob.getText().isEmpty() ||!gen.getText().isEmpty() ||!sal.getText().isEmpty() ||!offN.getText().isEmpty() ||!spec.getText().isEmpty()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader("DoctorList.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("DoctorList.txt", true));
                
                String id;
                for(int i = 1; true; i++ ){
                    String line;
                    if((line = reader.readLine()) == null){
                        
                        id = Integer.toString(i);
                        for(int j = 4-Patients.checkDigits(i); j > 0; j--){
                            id = "0" + id; 
                        }
                        Doctors.createDoctorFile(user.getText(), pass.getText(), name.getText(), id, dob.getText(), gen.getText(), sal.getText(), offN.getText(), spec.getText());
                        writer.write(id + " " + name.getText() + " " + spec.getText() + " " + offN.getText() + " " + "Doctor-" + id + ".txt"+ " " + user.getText() + " " + pass.getText() + "\n");
                        
                        break;
                    }
                }
                System.out.println("===============================================");
                System.out.println("      Doctor Data Entered Successfully");
                
                writer.close();
                reader.close();

            }
            catch(java.io.IOException ioe){
                System.out.println("Exception Occured During Writing 'DoctorList.txt' ");
            }
        }else{
            JOptionPane.showMessageDialog(null, 
            "Please fill in all required fields!", 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
        }
        });
        btn.add(button);
        panel.add(btn, BorderLayout.SOUTH);
        panel.add(dataEnter, BorderLayout.CENTER);
        return panel;
    }
    private static JPanel createDoctorSearchPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JPanel searchBar = new JPanel(new FlowLayout());
    JTextField searchField = new JTextField(20);
    
    JButton searchBtn = new JButton("Search");

    JButton refreshBtn = new JButton("Refresh");

    String[] columns = {"ID", "Name", "Specialization", "Office Number", "File Name"};
    String[][] initialData = Doctors.returnData(); 
    JTable resultsTable = new JTable(initialData, columns);
    JScrollPane scrollPane = new JScrollPane(resultsTable);

    searchBtn.addActionListener(e -> {
        String search = searchField.getText();
        Doctors.displayDoctorData(Doctors.searchDoctorMenu(search));
    });
    
    refreshBtn.addActionListener(e -> {
        searchField.setText("");
        
        String[][] refreshedData = Doctors.returnData();
        DefaultTableModel model = new DefaultTableModel(refreshedData, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        resultsTable.setModel(model);
        
        System.out.println("Doctor data refreshed");
    });
    
    searchBar.add(new JLabel("Search:"));
    searchBar.add(searchField);
    searchBar.add(searchBtn);
    searchBar.add(refreshBtn);
    
    panel.add(searchBar, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    
    return panel;
}
    private static JPanel createPatientManagementPanel() {
        JPanel doctorPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Patient Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        doctorPanel.add(title, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Manage Data", createPatientCRUDPanel());
        tabs.addTab("Search", createPatientSearchPanel());
        tabs.addTab("View Logs", new JPanel());
        
        
        doctorPanel.add(tabs, BorderLayout.CENTER);
        return doctorPanel;
    }
    private static JPanel createPatientCRUDPanel() {
        Patients.createPatientListFile();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel dataEnter = new JPanel();
        dataEnter.setLayout(new BoxLayout(dataEnter, BoxLayout.Y_AXIS));
        JLabel LUser = new JLabel("Username: ");
        JTextField user = new JTextField();

        JLabel LPass = new JLabel(" Password: ");
        JTextField pass = new JTextField();
        JLabel LName = new JLabel(" Full Name: ");
        JTextField name = new JTextField();
        JLabel lDoB = new JLabel(" Enter Patient DOB: ");
        JTextField dob  = new JTextField();
        JLabel LDoV = new JLabel(" Enter Date Of Visit: ");
        JTextField dov = new JTextField();
        JLabel LGender = new JLabel(" Enter Gender: ");
        JTextField gender = new JTextField();
        JLabel LPers = new JLabel(" Enter Prescription: ");
        JTextField pers = new JTextField();
        JLabel LDoc = new JLabel(" Enter Visiting Doctor ID: ");
        JTextField doc = new JTextField();
        JLabel Lillness = new JLabel(" Illness: ");
        JTextField ill = new JTextField();
        
        dataEnter.add(LUser);
        dataEnter.add(user);
        dataEnter.add(LPass);
        dataEnter.add(pass);
        dataEnter.add(LName);
        dataEnter.add(name);
        dataEnter.add(lDoB);
        dataEnter.add(dob);
        dataEnter.add(LDoV);
        dataEnter.add(dov);
        dataEnter.add(LGender);
        dataEnter.add(gender);
        dataEnter.add(LDoc);
        dataEnter.add(doc);
        dataEnter.add(LPers);
        dataEnter.add(pers);
        dataEnter.add(Lillness);
        dataEnter.add(ill);


        JPanel btn = new JPanel();
        JButton button = new JButton("Add Patient");
        button.setMinimumSize(new Dimension(250, 80));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(230, 40));
        button.setFocusPainted(false);
        button.setBackground(new Color(170, 180, 206));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(195, 207, 236));
            }
            
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(133, 141, 161));
            }
        });
        button.addActionListener(e ->{

        if(!user.getText().isEmpty() ||!pass.getText().isEmpty() ||!name.getText().isEmpty() ||!dob.getText().isEmpty() ||!dov.getText().isEmpty() ||!gender.getText().isEmpty() ||!doc.getText().isEmpty() ||!ill.getText().isEmpty() ||!pers.getText().isEmpty()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader("PatientList.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("PatientList.txt", true));
                
                String id;
                for(int i = 1; true; i++ ){
                    String line;
                    if((line = reader.readLine()) == null){
                        
                        id = Integer.toString(i);
                        for(int j = 4-Patients.checkDigits(i); j > 0; j--){
                            id = "0" + id; 
                        }
                        String[] data = (Patients.createPatientFile(user.getText(), pass.getText(), name.getText(), id, dob.getText(), dov.getText(), gender.getText(), ill.getText(), pers.getText(), doc.getText()));
                        writer.write(id + " " + name.getText() + " " + dov.getText() + " " + ill.getText() + " " + "Patient-" + id + ".txt"+ " " + user.getText() + " " + pass.getText() + "\n");
                        
                        break;
                    }
                }
                System.out.println("===============================================");
                System.out.println("      Patient Data Entered Successfully");
                
                writer.close();
                reader.close();

            }
            catch(java.io.IOException ioe){
                System.out.println("Exception Occured During Writing 'PatientList.txt' ");
            }
        }else{
            JOptionPane.showMessageDialog(null, 
            "Please fill in all required fields!", 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
        }
        });
        btn.add(button);
        panel.add(btn, BorderLayout.SOUTH);
        panel.add(dataEnter, BorderLayout.CENTER);
        return panel;
    }
    private static JPanel createPatientSearchPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    
    JPanel searchBar = new JPanel(new FlowLayout());
    JTextField searchField = new JTextField(20);
    
    JButton searchBtn = new JButton("Search");

    JButton refreshBtn = new JButton("Refresh");

    String[] columns = {"ID", "Name", "Last Visit", "Illness", "File Name"};
    String[][] initialData = Patients.returnData(); 
    JTable resultsTable = new JTable(initialData, columns);
    JScrollPane scrollPane = new JScrollPane(resultsTable);

    searchBtn.addActionListener(e -> {
        String search = searchField.getText();
        Patients.displayPatientData(Patients.searchPatientMenu(search));
    });

    refreshBtn.addActionListener(e -> {

        searchField.setText("");

        String[][] refreshedData = Patients.returnData();

        DefaultTableModel model = new DefaultTableModel(refreshedData, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        resultsTable.setModel(model);

        System.out.println("Patient data refreshed");
    });
    
    searchBar.add(new JLabel("Search:"));
    searchBar.add(searchField);
    searchBar.add(searchBtn);
    searchBar.add(refreshBtn);
    
    panel.add(searchBar, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    
    return panel;
}
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(230, 40));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(220, 220, 220));
            }
            
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(Color.WHITE);
            }
        });
        
        return button;
    }
}