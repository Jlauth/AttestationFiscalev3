package view;

import com.toedter.calendar.JDateChooser;
import model.Attestation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serial;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Creer extends JFrame {

    /**
     * Variables du JFrame
     */
    private final JComboBox<String> cmbTitreClient;
    private final JTextField txtNomClient;
    private final JTextField txtPrenomClient;
    private final JTextField txtAdresseClient;
    private final JTextField txtVilleClient;
    private final JTextField txtCpClient;
    private final JTextField txtMontantAttest;
    private final JDateChooser anneeFiscale;
    private final JDateChooser dateAttestation;
    private final JLabel lblEnregister;

    /**
     * Getters
     */
    public String getCmbTitre() {
        if (cmbTitreClient.getSelectedItem() == "Aucun titre") {
            return "";
        }
        return Objects.requireNonNull(cmbTitreClient.getSelectedItem()).toString();
    }

    public String getTxtNomClient() {
        return txtNomClient.getText();
    }

    public String getTxtPrenomClient() {
        return txtPrenomClient.getText();
    }

    public String getTxtAdresseClient() {
        return txtAdresseClient.getText();
    }

    public String getTxtVilleClient() {
        return txtVilleClient.getText();
    }

    public String getTxtCPClient() {
        return txtCpClient.getText();
    }

    public String getTxtMontantAttest() {
        return txtMontantAttest.getText();
    }
    
    public String getDateAttestationFormat() {
        Date date = dateAttestation.getDateEditor().getDate();
        OffsetDateTime odt = date.toInstant().atOffset(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("EEEE d'%s' MMMM uuuu", dateSuffix(odt.getDayOfMonth())), Locale.FRANCE);
        return odt.format(formatter);
    }

    public String getAnneeFiscaleFormat() {
        Date date = anneeFiscale.getDateEditor().getDate();
        OffsetDateTime odtAnneeFisc = date.toInstant().atOffset(ZoneOffset.UTC);
        DateTimeFormatter afFormater = DateTimeFormatter.ofPattern("yyyy", Locale.FRANCE);
        return odtAnneeFisc.format(afFormater);
    }

    /**
     * Création du Frame
     */
    public Creer() {

        /*
          Création Creer
         */
        JPanel contentPane = new JPanel();
        setTitle("Création attestation fiscale - Gestion des attestations fiscales Arkadia PC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        /*
          Titre
         */
        JLabel lblTitreClient = new JLabel("Titre");
        lblTitreClient.setBounds(40, 60, 120, 14);
        contentPane.add(lblTitreClient);

        cmbTitreClient = new JComboBox<>();
        cmbTitreClient.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        cmbTitreClient.setBounds(40, 80, 150, 20);
        contentPane.add(cmbTitreClient);

        /*
          Nom
         */
        JLabel lblNomClient = new JLabel("Nom");
        lblNomClient.setBounds(40, 110, 120, 14);
        contentPane.add(lblNomClient);

        txtNomClient = new JTextField();
        txtNomClient.setBounds(40, 130, 150, 20);
        contentPane.add(txtNomClient);

        /*
          Prénom
         */
        JLabel lblPrenomClient = new JLabel("Prénom");
        lblPrenomClient.setBounds(40, 160, 120, 14);
        contentPane.add(lblPrenomClient);

        txtPrenomClient = new JTextField();
        txtPrenomClient.setBounds(40, 180, 150, 20);
        contentPane.add(txtPrenomClient);

        /*
          Adresse
         */
        JLabel lblAdresseClient = new JLabel("Adresse");
        lblAdresseClient.setBounds(40, 210, 120, 14);
        contentPane.add(lblAdresseClient);

        txtAdresseClient = new JTextField();
        txtAdresseClient.setBounds(40, 230, 150, 20);
        contentPane.add(txtAdresseClient);

        /*
          Ville
         */
        JLabel lblVilleClient = new JLabel("Ville");
        lblVilleClient.setBounds(40, 260, 120, 14);
        contentPane.add(lblVilleClient);

        txtVilleClient = new JTextField();
        txtVilleClient.setBounds(40, 280, 150, 20);
        contentPane.add(txtVilleClient);

        /*
          Code Postal
         */
        JLabel lblCpClient = new JLabel("Code Postal");
        lblCpClient.setBounds(40, 310, 120, 14);
        contentPane.add(lblCpClient);

        txtCpClient = new JTextField();
        txtCpClient.setBounds(40, 330, 150, 20);
        contentPane.add(txtCpClient);

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
        JLabel lblAnneeFiscale = new JLabel("Année fiscale");
        lblAnneeFiscale.setBounds(250, 160, 120, 14);
        contentPane.add(lblAnneeFiscale);

        anneeFiscale = new JDateChooser();
        anneeFiscale.setDateFormatString("yyyy");
        anneeFiscale.setDate(Calendar.getInstance(Locale.FRANCE).getTime());
        anneeFiscale.setBounds(250, 180, 150, 20);
        contentPane.add(anneeFiscale);
        anneeFiscale.setEnabled(true);

        /*
          Date attestation
        */
        JLabel lblDateAttestation = new JLabel("Date d'émission");
        lblDateAttestation.setBounds(250, 210, 120, 14);
        contentPane.add(lblDateAttestation);

        dateAttestation = new JDateChooser();
        dateAttestation.setDateFormatString("dd MMMM yyyy");
        dateAttestation.setDate(Calendar.getInstance(Locale.FRANCE).getTime());
        dateAttestation.setBounds(250, 230, 150, 20);
        contentPane.add(dateAttestation);
        dateAttestation.setEnabled(true);

        /*
          Bouton enregistrer
         */
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEnregistrer.setBounds(50, 380, 120, 50);
        contentPane.add(btnEnregistrer);
        // Méthode isInputValid() lors de l'event clic button enregistrer
        btnEnregistrer.addActionListener(e -> {
            try {
                isInputValid();
            } catch (InvalidFormatException | IOException | ParseException e1) {
                e1.printStackTrace();
            }
        });

        lblEnregister = new JLabel();
        lblEnregister.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEnregister.setBounds(50, 450, 300, 50);
        contentPane.add(lblEnregister);


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
        btnQuitter.addActionListener(e -> close());
    }

    /**
     * Méthode de test sur le jour du mois
     */
    static String dateSuffix(final int dayOfMonth) {
        return (dayOfMonth % 31 == 1 || dayOfMonth == 1) ? "er" : " ";
    }

    /**
     * Fermeture de l'application
     */
    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    /**
     * Vérification de la validité des champs
     */
    public void isInputValid() throws InvalidFormatException, IOException, ParseException {
        Attestation attestation = new Attestation(this, new EditerEntreprise());
        if (("".equals(getTxtNomClient())) || "".equals(getTxtPrenomClient()) || "".equals(getTxtVilleClient()) || "".equals(getTxtAdresseClient()) ||
                "".equals(getTxtCPClient()) || "".equals(getDateAttestationFormat()) || "".equals(getAnneeFiscaleFormat()) || "".equals(getTxtMontantAttest())) {
            JOptionPane.showMessageDialog(new JOptionPane(), "Merci de remplir tous les champs");
        } else {
            attestation.savePdf(this);
            lblEnregister.setText("Document bien enregistré");
        }
    }
}
