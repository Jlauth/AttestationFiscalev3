package view;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import model.Attestation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serial;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Creer extends JFrame {

    /**
     * Variables du JFrame
     */
    private final JTextField txtNom;
    private final JTextField txtPrenom;
    private final JTextField txtAdresse;
    private final JTextField txtVille;
    private final JTextField txtCP;
    private final JTextField txtMontantAttest;
    private final JComboBox<String> cmbTitre;
    private final Calendar calendar = Calendar.getInstance();

    /**
     * Getters
     */
    public String getTxtNomClient() {
        return txtNom.getText();
    }

    public String getTxtPrenomClient() {
        return txtPrenom.getText();
    }

    public String getTxtAdresseClient() {
        return txtAdresse.getText();
    }

    public String getTxtVilleClient() {
        return txtVille.getText();
    }

    public String getTxtCPClient() {
        return txtCP.getText();
    }

    public String getTxtMontantAttest() {
        return txtMontantAttest.getText();
    }

    public String getCmbTitre() {
        if (cmbTitre.getSelectedItem() == "Aucun titre") {
            return "";
        }
        return (String) cmbTitre.getSelectedItem();
    }

    public String getDateAttestation() {
        return calendar.get(Calendar.DAY_OF_MONTH) + " " + getMonthForInt(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR);
    }

    public String getExerciceFiscal() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    String getMonthForInt(int m) {
        String month = "invalid";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (m >= 0 && m <= 11) {
            month = months[m];
        }
        return month;
    }

    public int getYearChooser() {
        return calendar.get(Calendar.YEAR);
    }


    /**
     * Création du Frame
     */
    public Creer() {

        /*
          Création Creer
         */
        JPanel contentPane = new JPanel();
        setTitle("Creer Fiscale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        /*
          Titre
         */
        JLabel lblTitre = new JLabel("Titre");
        lblTitre.setBounds(40, 60, 120, 14);
        contentPane.add(lblTitre);

        cmbTitre = new JComboBox<>();
        cmbTitre.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        cmbTitre.setBounds(40, 80, 150, 20);
        contentPane.add(cmbTitre);
        /*
          Nom
         */
        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(40, 110, 120, 14);
        contentPane.add(lblNom);

        txtNom = new JTextField();
        txtNom.setBounds(40, 130, 150, 20);
        contentPane.add(txtNom);

        /*
          Prénom
         */
        JLabel lblPrenom = new JLabel("Prénom");
        lblPrenom.setBounds(40, 160, 120, 14);
        contentPane.add(lblPrenom);

        txtPrenom = new JTextField();
        txtPrenom.setBounds(40, 180, 150, 20);
        contentPane.add(txtPrenom);


        /*
          Adresse
         */
        JLabel lblAdresse = new JLabel("Adresse");
        lblAdresse.setBounds(40, 210, 120, 14);
        contentPane.add(lblAdresse);

        txtAdresse = new JTextField();
        txtAdresse.setBounds(40, 230, 150, 20);
        contentPane.add(txtAdresse);

        /*
          Ville
         */
        JLabel lblVille = new JLabel("Ville");
        lblVille.setBounds(40, 260, 120, 14);
        contentPane.add(lblVille);

        txtVille = new JTextField();
        txtVille.setBounds(40, 280, 150, 20);
        contentPane.add(txtVille);

        /*
          Code Postal
         */
        JLabel lblCP = new JLabel("Code Postal");
        lblCP.setBounds(40, 310, 120, 14);
        contentPane.add(lblCP);

        txtCP = new JTextField();
        txtCP.setBounds(40, 330, 150, 20);
        contentPane.add(txtCP);

          /*
          Montant attestation
         */
        JLabel lblMontantAttest = new JLabel("Montant attestation");
        lblMontantAttest.setBounds(250, 110, 120, 14);
        contentPane.add(lblMontantAttest);

        txtMontantAttest = new JTextField();
        txtMontantAttest.setBounds(250, 130, 150, 20);
        contentPane.add(txtMontantAttest);

        /*
          Choix exercice fiscal
         */
        JLabel lblExerciceFinancier = new JLabel("Année fiscale");
        lblExerciceFinancier.setBounds(250, 160, 120, 14);
        contentPane.add(lblExerciceFinancier);

        JYearChooser exerciceFiscal = new JYearChooser();
        exerciceFiscal.setBounds(250, 180, 150, 20);
        contentPane.add(exerciceFiscal);
        exerciceFiscal.setEnabled(true);

        /*
          Date attestation
        */
        JLabel lblDateAttestation = new JLabel("Date d'émission");
        lblDateAttestation.setBounds(250, 210, 120, 14);
        contentPane.add(lblDateAttestation);

        JDateChooser dateAttestation = new JDateChooser();
        dateAttestation.setDateFormatString("dd MMMM yyyy");
        dateAttestation.setCalendar(Calendar.getInstance()); // set la date du jour dans le frame
        dateAttestation.setBounds(250, 230, 150, 20);
        contentPane.add(dateAttestation);
        dateAttestation.setEnabled(true);


        /*
          Bouton enregistrer
         */
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEnregistrer.setBounds(50, 500, 120, 50);
        contentPane.add(btnEnregistrer);
        // Méthode isInputValid() lors de l'event clic button enregistrer
        btnEnregistrer.addActionListener(e -> {
            try {
                isInputValid();
            } catch (InvalidFormatException | IOException e1) {
                e1.printStackTrace();
            }
        });

          /*
          Btn Accueil
         */
        JButton btnAccueil = new JButton("Accueil");
        btnAccueil.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAccueil.setBounds(450, 10, 120, 50);
        contentPane.add(btnAccueil);
        btnAccueil.addActionListener(e -> {
            Accueil accueil = new Accueil();
            accueil.setVisible(true);
            dispose();
        });

        /*
          Bouton quitter
         */
        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setForeground(Color.RED);
        btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnQuitter.setBounds(450, 490, 120, 50);
        contentPane.add(btnQuitter);
        // Méthode close() lors de l'event key escape
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");
        am.put("cancel", new AbstractAction() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        // Méthode close() lors de l'event clic button quitter
        btnQuitter.addActionListener(e -> close());

    }

    public void isInputValid() throws InvalidFormatException, IOException {
       /* if (("".equals(getTxtNom())) || "".equals(getTxtPrenom()) || "".equals(getTxtVille()) || "".equals(getTxtAdresse()) || "".equals(getTxtMontantAttest())) {
            JOptionPane.showMessageDialog(contentPane, "Merci de remplir tous les champs");
        } else {*/
        save();
    }

    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    public void save() throws IOException {
        Attestation attestation = new Attestation(this);
        int n = JOptionPane.showOptionDialog(new JFrame(), "Confirmer enregistrement", "Enregistrer",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            attestation.save();
        }
    }
}

// TODO essayer de configurer également les keys messagebox
// TODO event escape + enter sur quitter et enregistrer respectivement -> enlever l'utilisation de la touche espace dans les deux cas
// TODO customiser les boutons


