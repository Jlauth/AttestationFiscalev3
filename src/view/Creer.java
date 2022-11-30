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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.Serial;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    private final JYearChooser anneeFiscale;
    private JDateChooser dateAttestation;
    private SimpleDateFormat dateFormat;

    /**
     * Getters
     */
    public String getCmbTitre() {
        if (cmbTitreClient.getSelectedItem() == "Aucun titre") {
            return "";
        }
        return "Monsieur";
    }

    public String getTxtNomClient() {
        return "Lauth";
    }

    public String getTxtPrenomClient() {
        return "Jean";
    }

    public String getTxtAdresseClient() {
        return "18, rue Leconte de Lisle";
    }

    public String getTxtVilleClient() {
        return "Romans-sur-Isère";
    }

    public String getTxtCPClient() {
        return "26100";
    }

    public String getTxtMontantAttest() {
        return txtMontantAttest.getText();
    }

    /**
     * Méthode de formatage de la date d'attestation
     */
    public String getDateAttestation() {
        DateFormat formaterDateAttest = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRANCE);
        return formaterDateAttest.format(dateAttestation.getDateEditor().getDate());
    }

    /**
     * Méthode de récupération de l'année fiscale
     */
    public int getAnneeFiscale() {
        return anneeFiscale.getYear();
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

        anneeFiscale = new JYearChooser();
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
        dateAttestation.setCalendar(Calendar.getInstance()); // set la date du jour dans le frame
        dateAttestation.setBounds(250, 230, 150, 20);
        dateAttestation.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        });

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
        btnQuitter.addActionListener(e -> close());

    }


    /**
     * Vérification de la validité des champs
     */
    public void isInputValid() throws InvalidFormatException, IOException {
       /* if (("".equals(getTxtNom())) || "".equals(getTxtPrenom()) || "".equals(getTxtVille()) || "".equals(getTxtAdresse()) || "".equals(getTxtMontantAttest())) {
            JOptionPane.showMessageDialog(contentPane, "Merci de remplir tous les champs");
        } else {*/
        save();
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
     * Sauvegarde de l'application
     */
    public void save() throws IOException {
        Attestation attestation = new Attestation(this, new EditerEntreprise());
        int n = JOptionPane.showOptionDialog(new JFrame(), "Confirmer enregistrement", "Enregistrer",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            attestation.save();
        }
    }
}
