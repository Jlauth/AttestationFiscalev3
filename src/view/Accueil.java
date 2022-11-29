package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Accueil extends JFrame {

    public Accueil() {

         /*
          Création Accueil
         */
        JPanel accueilPane = new JPanel();
        setTitle("Creer Fiscale");
        setBackground(new Color(37,88,167));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        accueilPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(accueilPane);
        setLocationRelativeTo(null);
        accueilPane.setLayout(null);

        /*
          Bouton nouvelle attestation
         */
        JButton btnAttestation = new JButton("Nouvelle attestation");
        btnAttestation.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAttestation.setBounds(175, 100, 200, 50);
        accueilPane.add(btnAttestation);
        btnAttestation.addActionListener(e -> {
            Creer creer = new Creer();
            creer.setVisible(true);
            dispose();
        });

        /*
          Bouton éditer client
         */
        JButton btnEditerClient = new JButton("Editer client");
        btnEditerClient.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEditerClient.setBounds(175, 200, 200, 50);
        accueilPane.add(btnEditerClient);
        btnEditerClient.addActionListener(e -> {
            EditerClient editerClient = new EditerClient();
            editerClient.setVisible(true);
            dispose();
        });

         /*
          Bouton éditer entreprise
         */
        JButton btnEditerEntreprise = new JButton("Editer entreprise");
        btnEditerEntreprise.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEditerEntreprise.setBounds(175,300,200, 50);
        accueilPane.add(btnEditerEntreprise);
        btnEditerEntreprise.addActionListener(e -> {
            EditerEntreprise editerEntreprise = new EditerEntreprise();
            editerEntreprise.setVisible(true);
            dispose();
        });

         /*
          Btn Quitter
         */
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setForeground(Color.RED);
        btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnQuitter.setBounds(450, 490, 120, 50);
        accueilPane.add(btnQuitter);
        btnQuitter.addActionListener(e -> close());
    }

    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}



