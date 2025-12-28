import java.util.*;

import javax.swing.*;

import java.io.*;
import java.awt.*;

public class Patients {
    public static Scanner input = new Scanner(System.in);
    public static String patientLogin(){
        String line;
        String[] parameters;
        String user;
        String password;
        
        System.out.print("Username: "); user = input.nextLine();
        System.out.print("Password: ");password = input.nextLine();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PatientList.txt"));
            while((line = reader.readLine()) != null){
                parameters = line.split("\\s+");
                if(parameters[0].equals(user) && parameters[1].equals(password)){
                    System.out.println("Login Successful");
                    reader.close();
                    return parameters[4];
                }
            }
            reader.close();
        } catch (java.io.IOException ioe) {
            System.out.println("Exception Occurred");
        }
        return "Patient Not Found";
    }
    public static void deletePatient(String fileName) {
        String fullPath = "patients\\" + fileName;

        File patientFile = new File(fullPath);
        boolean deleted = patientFile.delete();

        if (!deleted) {
            System.out.println("Could Not Delete File: " + fullPath);
            return;
        }
        System.out.println("Patient File Deleted Successfully");

        try {
            File inputFile = new File("PatientList.txt");
            File tempFile = new File("PatientList_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                if (parts.length >= 5 && parts[4].equals(fileName)) {
                    writer.write("[Deleted Record]");
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
            reader.close();
            writer.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
            System.out.println("PatientList Updated With Deleted Record");
        } catch (Exception e) {
            System.out.println("Error Updating 'PatientList.txt' During Delete");
        }
    }
    public static void updatePatientList(String oldFileName, String[] updatedLines) {
    try {
        File inputFile = new File("PatientList.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        File tempFile = new File("PatientList_temp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            if (parts[4].equals(oldFileName)) {
                
                parts[0] = updatedLines[0];
                parts[1] = updatedLines[1];
                parts[2] = updatedLines[2];
                parts[3] = updatedLines[3];
                parts[4] = "Patient-" + updatedLines[3] + ".txt";
            }

            writer.write(String.join(" ", parts));
            writer.newLine();
        }
        reader.close();
        writer.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    } catch (Exception e) {
        System.out.println("Error Updating 'PatientList.txt'");
    }
}
    public static void editPatientDataMenu(String FileName) {
    displayPatientData(FileName);
    String fullPath = "patients\\" + FileName;
    String[] lines = new String[10];
    int j;

    String name = FileName.substring(0, 12);

    while (true) {
        System.out.println("===============================================");
        System.out.println("||       Edit " + name + " Data               ||");
        System.out.println("===============================================");
        System.out.println("|| 1: Edit Patient Username                  ||");
        System.out.println("|| 2: Edit Patient Password                  ||");
        System.out.println("|| 3: Edit Patient Name                      ||");
        System.out.println("|| 4: Edit Date Of Birth                     ||");
        System.out.println("|| 5: Edit Gender                            ||");
        System.out.println("===============================================");
        System.out.print(" Choice: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1: j = 0; break;
            case 2: j = 1; break;
            case 3: j = 2; break;
            case 4: j = 4; break;
            case 5: j = 6; break;
            default: continue;
        }
        break;
    }

    try {
        BufferedReader reader = new BufferedReader(new FileReader(fullPath));
        for (int i = 0; i < lines.length; i++) {
            lines[i] = reader.readLine();
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Error Reading " + FileName);
        return;
    }

    System.out.print("Enter Edited Data: ");
    lines[j] = input.nextLine();

    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath));
        for (int i = 0; i < lines.length; i++) {
            writer.write(lines[i]);
            writer.newLine();
        }

        writer.close();
    } catch (IOException e) {
        System.out.println("Error Writing " + FileName);
    }
    updatePatientList(FileName, lines);
    System.out.println("===============================================");
    System.out.println("        Edited Patient Data Successfully        ");
    System.out.println("===============================================");
}
    public static void displayPatientData(String FileName){
        String name = FileName.substring(0,12);
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout());
        window.setSize(600, 800);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setPreferredSize(new Dimension(0, 60));
        
        JLabel title = new JLabel(name + " Data");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        if (FileName.equals("Patient Not Found")){
            return;
        }

        String line;
        String[] data = new String[10];
        System.out.println("===============================================");
        System.out.println("||         " + name + " Data                 ||" );
        System.out.println("===============================================");
        try{
            BufferedReader reader = new BufferedReader(new FileReader("patients\\" + FileName));
            for(int i = 0; (line = reader.readLine()) != null; i++){
                data[i] =  line;
            }
            JPanel personalPanel = new JPanel(new GridLayout(0, 2));
                personalPanel.setBorder(BorderFactory.createTitledBorder(
                    "Personal Information"
                ));
                JTextField text;
                personalPanel.add(new JLabel("   Username:"));
                text = new JTextField("  " + data[0]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Password:"));
                text = new JTextField("  " + data[1]);
                text.setEditable(false);
                personalPanel.add(text);
                
                personalPanel.add(new JLabel("   Name:"));
                text = new JTextField("  " + data[2]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Patient ID:"));
                text = new JTextField("  " + data[3]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Date Of Birth:"));
                text = new JTextField("  " + data[4]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Date Of Visit:"));
                text = new JTextField("  " + data[5]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Gender:"));
                text = new JTextField("  " + data[6]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Illness:"));
                text = new JTextField("  " + data[7]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Prescription:"));
                text = new JTextField("  " + data[8]);
                text.setEditable(false);
                personalPanel.add(text);

                personalPanel.add(new JLabel("   Doctor ID:"));
                text = new JTextField("  " + data[9]);
                text.setEditable(false);
                personalPanel.add(text);
                window.add(personalPanel);
            System.out.println("===============================================");
            reader.close();
        }catch(java.io.IOException ioe){
            System.out.println("Exception Occured During Reading File: " + FileName);
        }
        header.add(title, BorderLayout.CENTER);
        window.add(header, BorderLayout.NORTH);
        window.setVisible(true);
    }
    public static void patientEntryMenu(){
        int exit;
        while(true){
            System.out.println("===============================================");
            System.out.println("||            Patient Entry Menu             ||");
            System.out.println("===============================================");
            //addPatientList();
            while(true){
                try{
                    System.out.print("Enter 0 To Exit Entry Menu: "); exit = input.nextInt();
                    break;
                }
                catch(Exception e){
                    System.out.println("Enter Valid Integer");
                    input.next();
                    continue;
                }
            }
            input.nextLine();
            if(exit == 0){
                break;
            }else{
                continue;
            }
        }
        System.out.println("===============================================");
    }
    public static String searchPatientMenu(String search){
        String line;
        String[] parameters;
        
        
        
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PatientList.txt"));
            while((line = reader.readLine()) != null){
                parameters = line.split("\\s+");
                if(parameters[0].equals("[Deleted")) continue;
                if(parameters[0].equals(search) ||parameters[1].equals(search) ||parameters[2].equals(search) ||parameters[3].equals(search) ||parameters[4].equals(search)){
                    System.out.println("===============================================");
                    System.out.println("                 Patient Found");
                    reader.close();
                    System.out.println("===============================================");
                    return parameters[4];
                }
            }
            reader.close();
        } catch (java.io.IOException ioe) {
            System.out.println("Exception Occurred");
        }
        System.out.println("===============================================");
        return "Patient Not Found";
    }
    public static String[] createPatientFile(String user, String pass, String name, String id, String dob, String dateOfVisit, String gender, String ill, String perscrptn, String docID){
        String FileName = "Patient-" + id + ".txt";
        
        try {
            File patient = new File("patients\\" + FileName);
            if(!patient.exists()){
                patient.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("patients\\" + FileName)); 
           
            writer.write(user + "\n");
            writer.write(pass + "\n");
            writer.write(name + "\n");
            writer.write(id + "\n");
            writer.write(dob + "\n");
            writer.write(dateOfVisit + "\n");
            writer.write(gender + "\n");
            writer.write(ill + "\n");
            writer.write(perscrptn + "\n");
            writer.write(docID + "\n");
            writer.close();
            String data[] = {dateOfVisit, ill};
            return data;
        } catch (Exception e) {
            System.out.println("Exception Occurred During File Handling | File Name: " + FileName);
            String[] a = {"a"};
            return a;
        }
    }
    public static int checkDigits(int num){
        int count = 0;
        while(num != 0){
            count++;
            num =  num/10;
        }
        return count;
    }
    public static void createPatientListFile (){
        File patients = new File("PatientList.txt");
        int exit;
        while(true){
            try{
                if(!patients.exists()){
                    patients.createNewFile();
                }else{
                    break;
                }
            }
            catch(java.io.IOException ioe){
                System.out.println("Exception Occured During 'PatientList.txt' Creation");
                System.out.print("Do You Wish To Retry (1: Yes, 0: No): ");exit = input.nextInt();
                input.nextLine();
                if (exit == 1){
                    continue;
                }
                else if (exit == 0){
                    break;
                } else{
                    System.out.println("Invalid Input, Retrying Now");
                    continue;
                }
            }
        }
    }
    /*public static void addPatientList(){
        System.out.println("===============================================");
        System.out.println("||          Add New Patient Menu             ||");
        System.out.println("===============================================");
        try{
            BufferedReader reader = new BufferedReader(new FileReader("PatientList.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("PatientList.txt", true));
            
            String id;
            for(int i = 1; true; i++ ){
                String line;
                if((line = reader.readLine()) == null){
                    System.out.print(" Patient Username: "); String user = input.nextLine();
                    System.out.print(" Patient Password: "); String pass = input.nextLine();
                    System.out.print(" Patient Name (Insert '_' Between Name): "); String name = input.nextLine();
                    id = Integer.toString(i);
                    for(int j = 4-checkDigits(i); j > 0; j--){
                        id = "0" + id; 
                    }
                    String[] data = (createPatientFile(user, pass, name, id));
                    writer.write(id + " " + name + " " + data[0] + " " + data[1] +" Patient-" + id + ".txt"+ "\n" + user + " " + pass);
                    
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
        System.out.println("===============================================");
    }*/
    public static String[][] returnData(){
        String line;
        String[] words;
        String[][] array2D;
        int count = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("PatientList.txt"));
            
            while((line = reader.readLine())!= null)count++;
            
        }
        catch(Exception e){
            System.out.println("Error Returning 2D Array");
        }
        array2D = new String[count][5];
        try{
            BufferedReader reader = new BufferedReader(new FileReader("PatientList.txt"));
            for(int i = 0; i < array2D.length && (line = reader.readLine()) != null; i++){
                words = line.split("\\s+");
                array2D[i] = words;
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Error Returning 2D Array");
        }
        return array2D;
    }
}