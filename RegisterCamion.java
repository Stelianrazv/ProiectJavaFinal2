package com.Inregistrare.Auto;

/* Fereastra Register */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

// RegisterCamion mosteneste JFrame
public class RegisterCamion extends JFrame {
    JButton exitButton, registerButton, menuButton, listCamionButton;
    JLabel NumeLabel, PrenumeLabel, JudetLabel, MarcaLabel, ModelLabel, AnLabel;
    JTextField NumeField, PrenumeField, JudetField, MarcaField, ModelField, AnField;
    Statement myStmt;

    // RegisterCamion Constructor
    public RegisterCamion(Statement myStmt) {
        this.myStmt = myStmt;

        // Seteaza fereastra Register: titlu, mod de inchidere, dimensiuni si pozitia
        this.setTitle("Inregistrare autocamioane");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Creaza main panel panel principal care contine toate celelalte elemente
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.CYAN);

        // Creaza regPanel pentru elementele principale din fereastra
        JPanel regPanel = new JPanel();
        regPanel.setLayout(new BoxLayout(regPanel, 1));

        // Creaza textul "Nume"
        NumeLabel = new JLabel("Nume");
        NumeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(NumeLabel);
        // Creaza campul pentru a introduce Numele
        NumeField = new JTextField(5);
        NumeField.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(NumeField);

        // Creaza textul "Prenume"
        PrenumeLabel = new JLabel("Prenume");
        PrenumeLabel.setFont(new Font( "Arial", Font.BOLD, 16));
        regPanel.add(PrenumeLabel);
        // Creaza campul pentru a introduce Prenumele
        PrenumeField = new JTextField(5);
        PrenumeField.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(PrenumeField);

        // Creaza campul Judet
        JudetLabel = new JLabel("Judet");
        JudetLabel.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(JudetLabel);
        // Creaza campul pentru a introduce Judet
        JudetField = new JTextField(5);
        JudetField.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(JudetField);

        // Creaza campul MarcaAutocamion
        MarcaLabel = new JLabel("Marca");
        MarcaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(MarcaLabel);
        // Creaza campul pentru a introduce MarcaAutocamion
        MarcaField = new JTextField(5);
        MarcaField.setFont( new Font("Arial", Font.BOLD, 16));
        regPanel.add(MarcaField);

        // Creaz campul Modelautocamion
        ModelLabel = new JLabel("Model Autocamion");
        ModelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(ModelLabel);
        // Creaza campul pentru a introduce Model
        ModelField = new JTextField(5);
        ModelField.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(ModelField);

        // Creaza campul AnAutocamion
        AnLabel = new JLabel("An");
        AnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(AnLabel);
        // Creaza campul pentru a introduce AnAutocamion
        AnField = new JTextField(5);
        AnField.setFont(new Font("Arial", Font.BOLD, 16));
        regPanel.add(AnField);

        // Adauga regPanel la mainPanel
        mainPanel.add(regPanel, BorderLayout.CENTER);

        // Creaza buttonPanel pentru butoane
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( Color.BLACK );
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // Butonul "Inregistrare"
        registerButton = new JButton("INREGISTRARE");
        registerButton.setPreferredSize(new Dimension(180, 40));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        registerButton.setBackground(Color.LIGHT_GRAY);

        // Butonul "Lista Camioane"
        listCamionButton = new JButton("LISTA CAMIOANE");
        listCamionButton.setPreferredSize(new Dimension(200, 40));
        listCamionButton.setFont(new Font("Arial", Font.PLAIN, 18));
        listCamionButton.setBackground(Color.LIGHT_GRAY);

        // Butonul "Meniu"
        menuButton = new JButton("MENIU");
        menuButton.setPreferredSize(new Dimension(150, 40));
        menuButton.setFont(new Font("Arial", Font.PLAIN, 18));
        menuButton.setBackground(Color.LIGHT_GRAY);

        // Butonul "EXIT"
        exitButton = new JButton("IESIRE");
        exitButton.setPreferredSize(new Dimension(150, 40));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.setBackground( Color.LIGHT_GRAY );

        // Face ca butoanele sa poata fi receptive la events
        ListenForButton listenForButton = new ListenForButton();
        exitButton.addActionListener(listenForButton);
        registerButton.addActionListener(listenForButton);
        menuButton.addActionListener(listenForButton);
        listCamionButton.addActionListener(listenForButton);

        // Adauga butoanele la buttonPanel
        buttonPanel.add(registerButton);
        buttonPanel.add(listCamionButton);
        buttonPanel.add(menuButton);
        buttonPanel.add(exitButton );

        // adauga buttonPanel la mainPanel
        mainPanel.add( buttonPanel, BorderLayout.SOUTH );
        // Adauga mainPanel la Frame
        this.add(mainPanel);
        // Face fereastra vizibila
        this.setVisible( true );

    }

    // Pentru evenimente (metode)
    private class ListenForButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Sursa Evenimentului RegisterButton
            if (e.getSource() == registerButton) {

                    //Aici vom adauga acces la baza de date
                    Camion camion = new Camion(MainApp.camionCount, NumeField.getText(), PrenumeField.getText(), JudetField.getText(), MarcaField.getText(), ModelField.getText(), AnField.getText());
                    camion.saveCamion(myStmt);
                    MainApp.camionCount++;
                    new WelcomeGui(myStmt);
                    dispose();

                }// Sursa eventului este butonul "Lista Camioane"
                else if (e.getSource() == listCamionButton) {
                    // Deschide fereastra Lista Camioane
                    new ListCamion(myStmt);
                    // Inchide fereastra RegisterCamion
                    dispose();

                }// Sursa eventului este butonul "Meniu"
                else if (e.getSource() == menuButton) {
                    // Deschide fereastra WelcomeGui
                    new WelcomeGui(myStmt);
                    // Inchide fereastra RegisterCamion
                    dispose();

            }// Sursa eventului este butonul "Iesire"
            if (e.getSource() == exitButton) {
                // Inchide fereastra RegisterCamion
                System.exit(0);
            }
        }
    }
}

