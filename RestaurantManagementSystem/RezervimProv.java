package RestaurantManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RezervimProv implements ActionListener{
    JFrame frame = new JFrame();
    JButton back = new JButton("Back");
    JButton vazhdo = new JButton("Finish Order");
    JButton kthehu = new JButton("Load data");
    ImageIcon icon = new ImageIcon("src/RestaurantManagementSystem/lounge.jpg");
    JTextArea id_area = new JTextArea();
    JTextArea nr_area = new JTextArea();
    JTextArea name_area = new JTextArea();
    JTextArea surname_area = new JTextArea();
    JTextArea prod_id = new JTextArea();
    JTextArea prod_name = new JTextArea();
    JTextArea curr_area = new JTextArea();
    JTextArea pay_area = new JTextArea();
    JTable table = new JTable();
    JScrollPane tableScrollPane = new JScrollPane(table);
    JTable table2 = new JTable();
    JScrollPane tableScrollPane2 = new JScrollPane(table2);
    JButton delete = new JButton("Delete Data");

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/administrator";
    private static final String DB_USER= "root";
    private static final String DB_PASSWORD= "Vlora$2025";

    RezervimProv(){
        //op frame:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,1000);
        frame.setLayout(new GridLayout(8,4,8,10));




        //Info:
        JLabel cl_info = new JLabel("Client information");
        cl_info.setBackground(Color.red);
        cl_info.setOpaque(true);

        JLabel emp1 = new JLabel();
        emp1.setOpaque(true);

        JLabel emp2 = new JLabel();
        emp2.setOpaque(true);

        //Info2:
        JLabel rez = new JLabel("The product IDs are:100-pasta,101-pizza,102-traditional");
        rez.setBackground(Color.red);
        rez.setOpaque(true);

        //Client ID:
        JLabel id = new JLabel("Client's ID:");
        id.setOpaque(true);

        //Prod ID:
        JLabel prod = new JLabel("Product ID:");
        prod.setOpaque(true);

        //Number:
        JLabel nr = new JLabel("Client's Phone Number:");
        nr.setOpaque(true);

        //Product Name:
        JLabel p_name = new JLabel("Product name:");
        p_name.setOpaque(true);

        //Client Name:
        JLabel name = new JLabel("Name:");
        name.setOpaque(true);

        //Currency:
        JLabel curr = new JLabel("Currency:");
        curr.setOpaque(true);

        //Client Surname:
        JLabel surname = new JLabel("Surname:");
        surname.setOpaque(true);

        //Payment method:
        JLabel pay = new JLabel("Payment Method:");
        pay.setOpaque(true);

        //Client's Info:
        JLabel data = new JLabel("Client Data:");
        data.setOpaque(true);

        //Order info:
        JLabel data2 = new JLabel("Order Data:");
        data2.setOpaque(true);

        JLabel emp4 = new JLabel();
        emp4.setOpaque(true);

        JLabel emp5 = new JLabel();
        emp4.setOpaque(true);

        //Tabela 1:
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Tabela2:
        tableScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //Butonat:
        kthehu.setBackground(Color.red);
        kthehu.setFocusable(false);
        kthehu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id_area.getText().length() != 10 || nr_area.getText().length() != 10){
                    JOptionPane.showMessageDialog(frame,"Invalid ID or phone number!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    char[] input = id_area.getText().toCharArray();
                    char[] inp1 = nr_area.getText().toCharArray();
                    String in = prod_id.getText();
                    //ID:
                    if ((in.equals("100") || in.equals("101") || in.equals("102") &&
                    (input[0] == 'A' || input[0] == 'B' || input[0] == 'C' || input[0] == 'D' || input[0] == 'E' || input[0] == 'F'
                            || input[0] == 'G' || input[0] == 'H' || input[0] == 'I' || input[0] == 'J' || input[0] == 'K'
                            || input[0] == 'L' || input[0] == 'M' || input[0] == 'N' || input[0] == 'O' || input[0] == 'P'
                            || input[0] == 'Q' || input[0] == 'R' || input[0] == 'S' || input[0] == 'T' || input[0] == 'U'
                            || input[0] == 'V' || input[0] == 'W' || input[0] == 'X' || input[0] == 'Y' || input[0] == 'Z')
                            && (input[9] == 'A' || input[9] == 'B' || input[9] == 'C' || input[9] == 'D' || input[9] == 'E'
                            || input[9] == 'F' || input[9] == 'G' || input[9] == 'H' || input[9] == 'I' || input[9] == 'J'
                            || input[9] == 'K' || input[9] == 'L' || input[9] == 'M' || input[9] == 'N' || input[9] == 'O'
                            || input[9] == 'P' || input[9] == 'Q' || input[9] == 'R' || input[9] == 'S' || input[9] == 'T'
                            || input[9] == 'U' || input[9] == 'V' || input[9] == 'W' || input[9] == 'X' || input[9] == 'Y'
                            || input[9] == 'Z')
                            //Phone number:
                            && (inp1[0] == '0' && inp1[1] == '6' && inp1[2] == '9') || (inp1[2] == '8')
                            || (inp1[2] == '7'))){

                        insertData(id_area.getText(), nr_area.getText(), name_area.getText(), surname_area.getText());
                        insertData2(prod_id.getText(), prod_name.getText(), curr_area.getText(), pay_area.getText());
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Invalid ID,Phone Number or Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        delete.setFocusable(false);
        delete.setBackground(Color.red);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTable("administrator","klient3");
                deleteTable2("administrator","klient4");
            }
        });

        vazhdo.setFocusable(false);
        vazhdo.setBackground(Color.red);
        vazhdo.addActionListener(this);


        back.setBackground(Color.red);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frame.dispose();
                    new Regjistrim();
                }
            }
        });



        //Shtimi  elementeve ne frame:
        frame.add(cl_info);
        frame.add(emp1);
        frame.add(emp2);
        frame.add(rez);
        frame.add(id);
        frame.add(id_area);
        frame.add(prod);
        frame.add(prod_id);
        frame.add(nr);
        frame.add(nr_area);
        frame.add(p_name);
        frame.add(prod_name);
        frame.add(name);
        frame.add(name_area);
        frame.add(curr);
        frame.add(curr_area);
        frame.add(surname);
        frame.add(surname_area);
        frame.add(pay);
        frame.add(pay_area);
        frame.add(data);
        frame.add(tableScrollPane);
        frame.add(data2);
        frame.add(tableScrollPane2);
        frame.add(back);
        frame.add(kthehu);
        frame.add(delete);
        frame.add(vazhdo);

        //imazhi:
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
    }

    private void insertData(String input, String inp1, String inp2, String inp3){
        String query = "INSERT INTO klient3(idklient3,nr,name,surname) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, input);
            stmt.setString(2, inp1);
            stmt.setString(3, inp2);
            stmt.setString(4, inp3);

            stmt.executeUpdate();

            Statement pstmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idklient3,nr,name,surname FROM klient3");

            // Build table model
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nr", "Name", "Surname"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("idklient3"),
                        rs.getString("nr"),
                        rs.getString("name"),
                        rs.getString("surname")
                });
            }

            table.setModel(model);

        } catch (SQLException ex) {
            /*JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    private void insertData2(String inp4, String inp5, String inp6, String inp7){
        String query = "INSERT INTO klient4(prod_id,prod_name,currency,pay) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, inp4);
            stmt.setString(2, inp5);
            stmt.setString(3, inp6);
            stmt.setString(4, inp7);

            stmt.executeUpdate();

            Statement pstmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT prod_id,prod_name,currency,pay FROM klient4");

            // Build table model
            DefaultTableModel model = new DefaultTableModel(new String[]{"Prod_ID", "Prod_name", "Currency", "Payment"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("prod_id"),
                        rs.getString("prod_name"),
                        rs.getString("currency"),
                        rs.getString("pay")
                });
            }

            table2.setModel(model);

        } catch (SQLException ex) {
            /*JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==vazhdo && id_area.getText().length() != 10 || nr_area.getText().length() != 10) {
            JOptionPane.showMessageDialog(frame, "Invalid ID,Phone number or Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(frame,"The order has been comleted successfuly!","Success!",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void deleteTable(String dbName, String tableName) {
        try (Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "DROP TABLE " + tableName;
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Table deleted successfully.","Done",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting table: " + ex.getMessage());
        }
    }

    public static void deleteTable2(String dbName, String tableName) {
        try (Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "DROP TABLE " + tableName;
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Table deleted successfully.","Done",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting table: " + ex.getMessage());
        }
    }
}
