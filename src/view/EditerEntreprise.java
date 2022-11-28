package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditerEntreprise extends JFrame {

    private JTextField txtNomEntreprise;
    private JTextField txtAgrement;
    private JTextField txtNomGerant;
    private JTextField txtPrenomGerant;


    public JTextField getTxtNomGerant() {
        return txtNomGerant;
    }

    public JTextField getTxtPrenomGerant() {
        return txtPrenomGerant;
    }

    public JTextField getTxtNomEntreprise() {
        return txtNomEntreprise;
    }

    public JTextField getTxtAgrement() {
        return txtAgrement;
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

        JTextField txtAgrement = new JTextField();
        txtAgrement.setColumns(10);
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
        txtNomEntreprise.setColumns(10);

        /*
          Nom gérant
         */
        JLabel lblNomGerant = new JLabel("Nom du gérant");
        lblNomGerant.setBounds(40, 110, 120, 14);
        editerEntreprisePane.add(lblNomGerant);

        JTextField txtNomGerant = new JTextField();
        txtNomGerant.setBounds(40, 130, 150, 20);
        editerEntreprisePane.add(txtNomGerant);
        txtNomGerant.setColumns(10);


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
