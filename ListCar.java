package com.Inregistrare.Auto;

/* Fereastra List */
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ListCar extends JFrame {
    JButton exitButton, menuButton;
    Statement myStmt;
    Vector data, row, columnNames;

    public ListCar(Statement myStmt) {
        this.myStmt = myStmt;

        // Seteaza fereastra Register: titlu, mod de inchidere, dimensiuni si pozitia
        this.setTitle("Evidenta Masinilor inregistrate");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Creaza main panel panel principal care contine toate celelalte elemente
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.CYAN);

        JPanel listPanel = new JPanel();
        columnNames = new Vector();
        columnNames.add("ID");
        columnNames.add("Nume");
        columnNames.add("Prenume");
        columnNames.add("Judet");
        columnNames.add("Marca");
        columnNames.add("Model");
        columnNames.add("An");

        try {
            ResultSet myRs = myStmt.executeQuery("select * from masina");
            data = new Vector();
            while (myRs.next()) {
                row = new Vector();
                row.add(myRs.getString(1));
                row.add(myRs.getString(2).substring(0,12));
                row.add(myRs.getString(3).substring(0,20));
                row.add(myRs.getString(4).substring(0,12));
                row.add(myRs.getString(5).substring(0,12));
                row.add(myRs.getString(6).substring(0,12));
                row.add(myRs.getString(7));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Creaza tabel
        JTable jTable = new JTable(data, columnNames);
        jTable.setRowHeight(jTable.getRowHeight() + 10);
        jTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JTableHeader header = jTable.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(1100,700));
        listPanel.add(scrollPane);
        mainPanel.add(listPanel,BorderLayout.CENTER);

        // Creaza buttonPanel pentru butoane
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));

        // Butonul "Meniu"
        menuButton = new JButton("MENIU");
        menuButton.setPreferredSize(new Dimension(150,40));
        menuButton.setFont(new Font("Arial", Font.PLAIN, 18));
        menuButton.setBackground(Color.LIGHT_GRAY);

        // Butonul "EXIT"
        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(150,40));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.setBackground(Color.LIGHT_GRAY);

        // Face ca butoanele sa poata fi receptive la events
        ListenForButton listenForButton = new ListenForButton();
        exitButton.addActionListener(listenForButton);
        menuButton.addActionListener(listenForButton);

        //Adauga butoane la buttonPanel
        buttonPanel.add(menuButton);
        buttonPanel.add(exitButton);

        // Adauga buttonPanel la mainPanel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Adauga mainPanel la frame
        this.add(mainPanel);
        // Face fereastra vizibila
        this.setVisible(true);
    }
    // Pentru events
    private class ListenForButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Sursa eventului este butonul "EXIT"
            if (e.getSource() == exitButton){
                // Inchide fereastra ListCar
                System.exit(0);
            } else if (e.getSource() == menuButton) {
                new WelcomeGui(myStmt);
                dispose();
            }
        }
    }
}