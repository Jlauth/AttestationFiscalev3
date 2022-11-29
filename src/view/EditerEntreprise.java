package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditerEntreprise extends JFrame {

    private JTextField txtNomEntreprise;
    private JTextField txtAdresseEntreprise;
    private JTextField txtCPEntreprise;
    private JTextField txtVilleEntreprise;
    private JTextField txtTelEntreprise;
    private JTextField txtMailEntreprise;
    private JTextField txtAgrement;
    private JTextField txtNomGerant;
    private JTextField txtPrenomGerant;
    private JComboBox<String> cmbTitreGerant;

    public String getTxtNomEntreprise() {
        return "Arkadia PC";
    }
    public String getTxtAdresseEntreprise() {
        return "4, rue des Pyrénées";
    }
    public String getTxtCPEntreprise() {
        return "92500";
    }
    public String getTxtVilleEntreprise() {
        return "Rueil Malmaison";
    }
    public String getTxtTelEntreprise() {
        return "+33 (1) 47 08 98 38";
    }
    public String getTxtMailEntreprise() {
        return "contact@arkadia-pc.fr";
    }
    public String getTxtNomGerant() {
        return "ARAUJO";
    }
    public String getTxtPrenomGerant() {
        return "Adelino";
    }
    public String getTxtAgrement() {
        return "Agrément N° SAP524160330";
    }

    public String getCmbTitreGerant() {
        return "Monsieur";
    }

    public EditerEntreprise() {

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

        txtAgrement = new JTextField();
        txtAgrement.setBounds(40, 30, 150, 22);
        editerEntreprisePane.add(txtAgrement);
        
        /*
          Nom entreprise
         */
        JLabel lblNomEntreprise = new JLabel("Nom entreprise");
        lblNomEntreprise.setBounds(40, 60, 120, 14);
        editerEntreprisePane.add(lblNomEntreprise);

        txtNomEntreprise = new JTextField();
        txtNomEntreprise.setBounds(40, 80, 150, 20);
        editerEntreprisePane.add(txtNomEntreprise);

        /*
          Titre gérant
         */
        JLabel lblTitreGerant = new JLabel("Titre du gérant");
        lblTitreGerant.setBounds(150, 110, 120, 14);
        editerEntreprisePane.add(lblTitreGerant);

        cmbTitreGerant = new JComboBox<>();
        cmbTitreGerant.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        cmbTitreGerant.setBounds(40, 80, 150, 20);
        editerEntreprisePane.add(cmbTitreGerant);
                
        
        /*
          Nom gérant
         */
        JLabel lblNomGerant = new JLabel("Nom du gérant");
        lblNomGerant.setBounds(40, 110, 120, 14);
        editerEntreprisePane.add(lblNomGerant);

        txtNomGerant = new JTextField();
        txtNomGerant.setBounds(40, 130, 150, 20);
        editerEntreprisePane.add(txtNomGerant);

        /*
          Prénom gérant
         */
        JLabel lblPrenom = new JLabel("Prénom du gérant");
        lblPrenom.setBounds(40, 160, 46, 14);
        editerEntreprisePane.add(lblPrenom);

        txtPrenomGerant = new JTextField();
        txtPrenomGerant.setBounds(40, 180, 153, 20);
        editerEntreprisePane.add(txtPrenomGerant);

        /*
          Adresse entreprise
         */
        JLabel lblAdresseEntreprise = new JLabel("Adresse");
        lblAdresseEntreprise.setBounds(40, 210, 120, 14);
        editerEntreprisePane.add(lblAdresseEntreprise);

        txtAdresseEntreprise = new JTextField();
        txtAdresseEntreprise.setBounds(40, 230, 150, 20);
        editerEntreprisePane.add(txtAdresseEntreprise);

        /*
          Ville entreprise
         */
        JLabel lblVilleEntreprise = new JLabel("Ville");
        lblVilleEntreprise.setBounds(40, 260, 120, 14);
        editerEntreprisePane.add(lblVilleEntreprise);

        txtVilleEntreprise = new JTextField();
        txtVilleEntreprise.setBounds(40, 280, 150, 20);
        editerEntreprisePane.add(txtVilleEntreprise);

        /*
          Code Postal entreprise
         */
        JLabel lblCPEntreprise = new JLabel("Code Postal");
        lblCPEntreprise.setBounds(40, 310, 120, 14);
        editerEntreprisePane.add(lblCPEntreprise);

        txtCPEntreprise = new JTextField();
        txtCPEntreprise.setBounds(40, 330, 150, 20);
        editerEntreprisePane.add(txtCPEntreprise);

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
          Btn Accueil
         */
        JButton btnAccueil = new JButton("Accueil");
        btnAccueil.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAccueil.setBounds(450, 10, 120, 50);
        editerEntreprisePane.add(btnAccueil);
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
