package RestaurantManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;


public class Regjistrim  {
    JButton login = new JButton("Log in");
    JFrame frame = new JFrame();
    ImageIcon icon = new ImageIcon("src/RestaurantManagementSystem/lounge.jpg");
    JPasswordField pass_area = new JPasswordField();
    JTextArea email_area = new JTextArea();

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/administrator";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Vlora$2025";

    Regjistrim() {
        //op frame:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(4, 2, 10, 10));


        //Welcome:
        JLabel restoranti = new JLabel("Welcome!");
        restoranti.setBackground(Color.red);
        restoranti.setOpaque(true);

        //Emri restorantit:
        JLabel emri_res = new JLabel("FineDining");
        emri_res.setBackground(Color.red);
        emri_res.setOpaque(true);

        //Email:
        JLabel email = new JLabel("Email:");
        email.setOpaque(true);

        //Password:
        JLabel pass = new JLabel("Password:");
        pass.setOpaque(true);

        //Fushe bosh:
        JLabel emp = new JLabel();
        emp.setOpaque(true);

        //Butoni
        login.setBackground(Color.red);
        login.setFocusable(false);

        //Shtimi i elementeve ne frame:
        frame.add(restoranti);
        frame.add(emri_res);
        frame.add(email);
        frame.add(email_area);
        frame.add(pass);
        frame.add(pass_area);
        frame.add(emp);
        frame.add(login);


        //Imazhi:
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);


        //Kontrollon nese te dhenat qe shkruhen jane ne db:
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = email_area.getText();
                @Deprecated
                String inp1 = pass_area.getText();
                if (!input.isEmpty()) {
                    boolean exists = checkStringInDatabase(input,inp1);
                    if (exists) {
                        frame.dispose();
                        new RezervimProv();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Email or password is incorrect!", "Login failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

    //ekzekutimi i query:
    private boolean checkStringInDatabase(String input,String inp1) {
        String query = "SELECT * FROM login WHERE email = ? AND pass = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, input);
            stmt.setString(2, inp1);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
