package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditerClient extends JFrame {

    public EditerClient() {

        /*
          Editer client
         */
        JPanel editerClientPane = new JPanel();
        setTitle("Edition des données client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        editerClientPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(editerClientPane);
        setLocationRelativeTo(null);
        editerClientPane.setLayout(null);

        /*
          Titre
         */
        JLabel lblTitre = new JLabel("Titre");
        lblTitre.setBounds(40, 60, 120, 14);
        editerClientPane.add(lblTitre);

        JComboBox<String> cmbTitre = new JComboBox<>();
        cmbTitre.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        cmbTitre.setBounds(40, 80, 150, 22);
        editerClientPane.add(cmbTitre);

        /*
          Nom
         */
        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(40, 110, 120, 14);
        editerClientPane.add(lblNom);

        JTextField txtNom = new JTextField();
        txtNom.setBounds(40, 130, 150, 20);
        editerClientPane.add(txtNom);
        txtNom.setColumns(10);

        /*
          Prénom
         */
        JLabel lblPrenom = new JLabel("Prénom");
        lblPrenom.setBounds(40, 160, 46, 14);
        editerClientPane.add(lblPrenom);

        JTextField txtPrenom = new JTextField();
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(40, 180, 153, 20);
        editerClientPane.add(txtPrenom);

        /*
          Adresse
         */
        JLabel lblAdresse = new JLabel("Adresse");
        lblAdresse.setBounds(40, 210, 120, 14);
        editerClientPane.add(lblAdresse);

        JTextField txtAdresse = new JTextField();
        txtAdresse.setColumns(10);
        txtAdresse.setBounds(40, 230, 150, 20);
        editerClientPane.add(txtAdresse);

        /*
          Ville
         */
        JLabel lblVille = new JLabel("Ville");
        lblVille.setBounds(40, 260, 120, 14);
        editerClientPane.add(lblVille);

        JTextField txtVille = new JTextField();
        txtVille.setColumns(10);
        txtVille.setBounds(40, 280, 150, 20);
        editerClientPane.add(txtVille);

        /*
          Code Postal
         */
        JLabel lblCP = new JLabel("Code Postal");
        lblCP.setBounds(40, 310, 120, 14);
        editerClientPane.add(lblCP);

        JTextField txtCP = new JTextField();
        txtCP.setColumns(10);
        txtCP.setBounds(40, 330, 150, 20);
        editerClientPane.add(txtCP);


        /*
          Btn enregistrer
         */
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEnregistrer.setBounds(50, 500, 120, 50);
        editerClientPane.add(btnEnregistrer);
        btnEnregistrer.addActionListener(e -> {

        });

        /*
          Btn importer data clients
         */
        JButton btnImportClient = new JButton(("Importer client"));
        btnImportClient.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnImportClient.setBounds(200, 500, 120, 50);
        editerClientPane.add((btnImportClient));
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        /*
          Btn Accueil
         */
        JButton btnAccueil = new JButton("Accueil");
        btnAccueil.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAccueil.setBounds(450, 10, 120, 50);
        editerClientPane.add(btnAccueil);
        btnAccueil.addActionListener(e -> {
            Accueil accueil = new Accueil();
            accueil.setVisible(true);
            dispose();
        });

        /*
          Btn Quitter
         */
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setForeground(Color.RED);
        btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnQuitter.setBounds(450, 490, 120, 50);
        editerClientPane.add(btnQuitter);
        btnQuitter.addActionListener(e -> close());
    }

    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}
