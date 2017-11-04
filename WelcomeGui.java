package com.Inregistrare.Auto;

/* Prima fereastra - Welcome */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;

public class WelcomeGui extends JFrame {
    JButton exitButton, masinaButton, camionButton;
    BufferedImage img;
    Statement myStmt;

    public WelcomeGui(Statement myStmt) {
        this.myStmt = myStmt;
        // Titlul ferestrei
        this.setTitle("Inregistrare Auto");
        // Modalitatea de inchidere a ferestrei
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Marimea ferestrei
        this.setSize(1200,700);
        // Pozitioneaza fereastra in centrul ecranului
        this.setLocationRelativeTo(null);
        // Creaza mainPanel - panel principal care include toate elementele
        JLabel mainLabel = new JLabel();
        mainLabel.setSize(1200,700);
        this.setResizable(false);

        try {
            img = ImageIO.read(new File("C:\\Users\\Razvan\\Desktop\\truck.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Modifica dimensiunile la imaginea de background
        Image dimg = img.getScaledInstance(mainLabel.getWidth(), mainLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);

        // Adauga imaginea de fond la mainLabel
        mainLabel.setIcon(imageIcon);
        mainLabel.setLayout(new BorderLayout());
        this.setContentPane(mainLabel);

        // Creaza buttonPanel care va contine butoanele
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        // Precizeaza layout pentru buttonPanel
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));

        //Butonul masini
        masinaButton = new JButton("MASINI");
        masinaButton.setPreferredSize(new Dimension(150,40));
        masinaButton.setFont(new Font("Arial", Font.PLAIN, 18));
        masinaButton.setBackground(Color.LIGHT_GRAY);

        //Butonul camion
        camionButton = new JButton("CAMIOANE");
        camionButton.setPreferredSize(new Dimension(150,40));
        camionButton.setFont(new Font("Arial", Font.PLAIN, 18));
        camionButton.setBackground(Color.LIGHT_GRAY);

        // Butonul exit
        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(150,40));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.setBackground(Color.LIGHT_GRAY);

        // Face ca butoanele sa poata fi receptive la events
        ListenForButton listenForButton = new ListenForButton();
        exitButton.addActionListener(listenForButton);
        masinaButton.addActionListener(listenForButton);
        camionButton.addActionListener(listenForButton);

        // Adauga butoanele la buttonPanel
        buttonPanel.add(masinaButton);
        buttonPanel.add(camionButton);
        buttonPanel.add(exitButton);

        // Adauga buttonPanel la mainPanel
        mainLabel.add(buttonPanel, BorderLayout.SOUTH);

        // Face fereastra vizibila
        this.pack();
        this.setVisible(true);
    }
    // Pentru events
    private class ListenForButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Sursa eventului este butonul "EXIT"
            if (e.getSource() == exitButton){
                // Inchide fereastra Welcome
                System.exit(0);

            }// Sursa eventului este butonul "Masini"
            else if (e.getSource() == masinaButton) {
                // Deschide fereastra Register
                new RegisterCar(myStmt);
                // Inchide fereastra Welcome
                dispose();

            }// Sursa eventului este butonul "Camioane"
            else if (e.getSource() == camionButton) {
                // Deschide fereastra Register
                new RegisterCamion(myStmt);
                // Inchide fereastra Welcome
                dispose();
            }
        }
    }
}