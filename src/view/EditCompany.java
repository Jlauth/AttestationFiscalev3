package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditCompany extends JFrame {

    private JTextField txtNomEntreprise;
    private JTextField txtAgrement;
    private JTextField txtNomGerant;
    private JTextField txtPrenomGerant;
    private JTextField txtAdresseEntreprise;
    private JTextField txtCpEntreprise;
    private JTextField txtVilleEntreprise;
    private JTextField txtTelEntreprise;
    private JTextField txtMailEntreprise;

    public String getTxtTelEntreprise() {
        return "+33 (1) 47 08 98 38";
    }

    public String getTxtMailEntreprise() {
        return "contact@arkadia-pc.fr";
    }

    public String getTxtAdresseEntreprise() {
        return "4, rue des Pyrénées";
    }

    public String getTxtCpEntreprise() {
        return "92500";
    }

    public String getTxtVilleEntreprise() {
        return "Reuil Malmaison";
    }

    public String getTxtNomGerant() {
        return "Araujo";
    }

    public String getTxtPrenomGerant() {
        return "Adelino";
    }

    public String getTxtNomEntreprise() {
        return "Arkadia PC";
    }

    public String getTxtAgrement() {
        return "Agrément N° SAP524160330";
    }

    public String getCmbTitreGerant() {
        return "Monsieur";
    }

    public EditCompany() {

         /*
          Editer entreprise
         */
        JPanel editerEntreprisePane = new JPanel();
        setTitle("Edition des données entreprise");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        editerEntreprisePane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(editerEntreprisePane);
        setLocationRelativeTo(null);
        editerEntreprisePane.setLayout(null);

         /*
          Numéro agrément
         */
        JLabel lblAgrement = new JLabel("Numéro d'agrément");
        lblAgrement.setBounds(40, 10, 120, 14);
        editerEntreprisePane.add(lblAgrement);

        JTextField txtAgrement = new JTextField();
        txtAgrement.setBounds(40, 30, 150, 22);
        editerEntreprisePane.add(txtAgrement);

        /*
          Nom entreprise
         */
        JLabel lblNomEntreprise = new JLabel("Nom entreprise");
        lblNomEntreprise.setBounds(40, 60, 120, 14);
        editerEntreprisePane.add(lblNomEntreprise);

        JTextField txtNomEntreprise = new JTextField();
        txtNomEntreprise.setBounds(40, 80, 150, 20);
        editerEntreprisePane.add(txtNomEntreprise);

        /*
          Titre gérant
         */
        JLabel lblTitreGerant = new JLabel("Titre");
        lblTitreGerant.setBounds(40, 60, 120, 14);
        editerEntreprisePane.add(lblTitreGerant);

        JComboBox<String> cmbTitreGerant = new JComboBox<>();
        cmbTitreGerant.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        cmbTitreGerant.setBounds(40, 80, 150, 20);
        editerEntreprisePane.add(cmbTitreGerant);

        /*
          Nom gérant
         */
        JLabel lblNomGerant = new JLabel("Nom du gérant");
        lblNomGerant.setBounds(40, 110, 120, 14);
        editerEntreprisePane.add(lblNomGerant);

        JTextField txtPrenomGerant = new JTextField();
        txtPrenomGerant.setBounds(40, 130, 150, 20);
        editerEntreprisePane.add(txtPrenomGerant);
        txtPrenomGerant.setColumns(10);

        /*
          Prénom
         */
        JLabel lblPrenom = new JLabel("Prénom du gérant");
        lblPrenom.setBounds(40, 160, 46, 14);
        editerEntreprisePane.add(lblPrenom);

        JTextField txtPrenom = new JTextField();
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(40, 180, 153, 20);
        editerEntreprisePane.add(txtPrenom);

        /*
          Adresse
         */
        JLabel lblAdresse = new JLabel("Adresse");
        lblAdresse.setBounds(40, 210, 120, 14);
        editerEntreprisePane.add(lblAdresse);

        JTextField txtAdresse = new JTextField();
        txtAdresse.setColumns(10);
        txtAdresse.setBounds(40, 230, 150, 20);
        editerEntreprisePane.add(txtAdresse);

        /*
          Ville
         */
        JLabel lblVille = new JLabel("Ville");
        lblVille.setBounds(40, 260, 120, 14);
        editerEntreprisePane.add(lblVille);

        JTextField txtVille = new JTextField();
        txtVille.setColumns(10);
        txtVille.setBounds(40, 280, 150, 20);
        editerEntreprisePane.add(txtVille);

        /*
          Code Postal
         */
        JLabel lblCP = new JLabel("Code Postal");
        lblCP.setBounds(40, 310, 120, 14);
        editerEntreprisePane.add(lblCP);

        JTextField txtCP = new JTextField();
        txtCP.setColumns(10);
        txtCP.setBounds(40, 330, 150, 20);
        editerEntreprisePane.add(txtCP);

        /*
          Btn enregistrer
         */
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEnregistrer.setBounds(50, 500, 120, 50);
        editerEntreprisePane.add(btnEnregistrer);
        btnEnregistrer.addActionListener(e -> {

        });

        /*
          Btn Home
         */
        JButton btnAccueil = new JButton("Home");
        btnAccueil.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAccueil.setBounds(450, 10, 120, 50);
        editerEntreprisePane.add(btnAccueil);
        btnAccueil.addActionListener(e -> {
            Home home = new Home();
            home.setVisible(true);
            dispose();
        });

        /*
          Btn Quitter
         */
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setForeground(Color.RED);
        btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnQuitter.setBounds(450, 490, 120, 50);
        editerEntreprisePane.add(btnQuitter);
        btnQuitter.addActionListener(e -> close());
    }

    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }



}
