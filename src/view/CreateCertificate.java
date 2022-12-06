package view;

import com.toedter.calendar.JDateChooser;
import model.Certificate;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CreateCertificate extends JFrame {

    /**
     * Variables du JFrame
     */
    private final JComboBox<String> customerTitleCmb;
    private final JTextField customerNameTxt;
    private final JTextField customerFirstnameTxt;
    private final JTextField customerAddressTxt;
    private final JTextField customerCityTxt;
    private final JTextField customerZipTxt;
    private final JTextField certificateAmountTxt;
    private final JDateChooser certificateDate;
    private final JTextField fiscalYearTxt;
    private final JLabel saveLbl;

    /**
     * Getters
     */
    public String getCustomerTitleAbbrevCmb() {
        return switch (Objects.requireNonNull(customerTitleCmb.getSelectedItem()).toString()) {
            case "Madame" -> "Mme";
            case "Monsieur" -> "Mr";
            case "Mademoiselle" -> "Mlle";
            default -> "";
        };
    }

    public String getCustomerTitleCmb() {
        if(customerTitleCmb.getSelectedItem()=="Aucun titre"){
            return "";
        }else{
            return Objects.requireNonNull(customerTitleCmb.getSelectedItem()).toString();
        }
    }

    public String getCustomerNameTxt() {
        return customerNameTxt.getText();
    }

    public String getCustomerFirstnameTxt() {
        return customerFirstnameTxt.getText();
    }

    public String getCustomerAddressTxt() {
        return customerAddressTxt.getText();
    }

    public String getCustomerCityTxt() {
        return customerCityTxt.getText();
    }

    public String getCustomerZipTxt() {
        return customerZipTxt.getText();
    }

    public String getCertificateAmountTxt() {
        return certificateAmountTxt.getText();
    }

    public String getFiscalYearTxt() {
        return fiscalYearTxt.getText();
    }

    /**
     * Configuration du frame CreateCertificate
     */
    public CreateCertificate() {

        /*
        Création attestation
         */
        JPanel createPane = new JPanel();
        setTitle("Nouvelle attestation fiscale - Arkadia PC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 720);
        createPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(createPane);
        setLocationRelativeTo(null);
        createPane.setLayout(null);

        JLabel createLbl = new JLabel();
        createLbl.setBounds(30, 30, 600, 30);
        createLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
        createLbl.setText("Création d'une nouvelle attestation fiscale");
        createLbl.setHorizontalAlignment(SwingConstants.CENTER);
        createPane.add(createLbl);

        JLabel createCustomInfoLbl = new JLabel();
        createCustomInfoLbl.setBounds(50, 110, 600, 30);
        createCustomInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        createCustomInfoLbl.setText("Informations client");
        createPane.add(createCustomInfoLbl);

        JLabel createCertifInfoLbl = new JLabel();
        createCertifInfoLbl.setBounds(50, 390, 600, 30);
        createCertifInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        createCertifInfoLbl.setText("Informations attestation");
        createPane.add(createCertifInfoLbl);

        /*
          Titre
         */
        JLabel customerTitleLbl = new JLabel("Titre");
        customerTitleLbl.setBounds(50, 150, 120, 14);
        createPane.add(customerTitleLbl);

        customerTitleCmb = new JComboBox<>();
        customerTitleCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        customerTitleCmb.setBounds(50, 175, 150, 25);
        createPane.add(customerTitleCmb);

        /*
          Nom
         */
        JLabel customerNameLbl = new JLabel("Nom");
        customerNameLbl.setBounds(230, 150, 120, 14);
        createPane.add(customerNameLbl);

        customerNameTxt = new JTextField();
        customerNameTxt.setBounds(230, 175, 150, 25);
        createPane.add(customerNameTxt);

        /*
          Prénom
         */
        JLabel customerFirstnameLbl = new JLabel("Prénom");
        customerFirstnameLbl.setBounds(410, 150, 120, 14);
        createPane.add(customerFirstnameLbl);

        customerFirstnameTxt = new JTextField();
        customerFirstnameTxt.setBounds(410, 175, 150, 25);
        createPane.add(customerFirstnameTxt);

        /*
          Adresse
         */
        JLabel customerAddressLbl = new JLabel("Adresse");
        customerAddressLbl.setBounds(50, 230, 120, 14);
        createPane.add(customerAddressLbl);

        customerAddressTxt = new JTextField();
        customerAddressTxt.setBounds(50, 255, 510, 25);
        createPane.add(customerAddressTxt);

        /*
          Ville
         */
        JLabel customerCityLbl = new JLabel("Ville");
        customerCityLbl.setBounds(50, 300, 120, 14);
        createPane.add(customerCityLbl);

        customerCityTxt = new JTextField();
        customerCityTxt.setBounds(50, 325, 330, 25);
        createPane.add(customerCityTxt);

        /*
          Code Postal
         */
        JLabel customerZipLbl = new JLabel("Code Postal");
        customerZipLbl.setBounds(410, 300, 120, 14);
        createPane.add(customerZipLbl);

        customerZipTxt = new JTextField();
        customerZipTxt.setBounds(410, 325, 150, 25);
        createPane.add(customerZipTxt);

        /*
         Montant attestation
        */
        JLabel certificateAmountLbl = new JLabel("Montant total");
        certificateAmountLbl.setBounds(50, 440, 120, 14);
        createPane.add(certificateAmountLbl);

        certificateAmountTxt = new JTextField();
        certificateAmountTxt.setBounds(50, 465, 150, 25);
        createPane.add(certificateAmountTxt);

        /*
          Choix exercice fiscal
         */
        JLabel fiscalYearLbl = new JLabel("Année fiscale");
        fiscalYearLbl.setBounds(230, 440, 120, 14);
        createPane.add(fiscalYearLbl);

        fiscalYearTxt = new JTextField();
        fiscalYearTxt.setBounds(230, 465, 150, 25);
        createPane.add(fiscalYearTxt);

        /*
          Date attestation
        */
        JLabel certificateDateLbl = new JLabel("Date d'émission");
        certificateDateLbl.setBounds(410, 440, 120, 14);
        createPane.add(certificateDateLbl);

        certificateDate = new JDateChooser();
        certificateDate.setDateFormatString("dd MMMM yyyy");
        certificateDate.setDate(Calendar.getInstance(Locale.FRANCE).getTime());
        certificateDate.setBounds(410, 465, 150, 25);
        createPane.add(certificateDate);
        certificateDate.setEnabled(true);

        /*
          Bouton enregistrer
         */
        JButton saveBtn = new JButton("Créer");
        saveBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        saveBtn.setBounds(50, 550, 150, 40);
        saveBtn.setForeground(new Color(37, 88, 167));
        createPane.add(saveBtn);
        saveBtn.addActionListener(e -> {
            try {
                isInputValid();
            } catch (InvalidFormatException | IOException | ParseException e1) {
                e1.printStackTrace();
            }
        });

        saveLbl = new JLabel();
        saveLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
        saveLbl.setBounds(50, 610, 500, 50);
        saveLbl.setText("Enregistre également les données client");
        createPane.add(saveLbl);

        /*
         Btn Home
         */
        ImageIcon homeIcon = new ImageIcon("src/media/images/home.png");
        Image newHomeImg = homeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        homeIcon.setImage(newHomeImg);

        JButton homeBtn = new JButton(homeIcon);
        homeBtn.setBounds(600, 10, 50, 50);
        homeBtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, homeIcon));
        homeBtn.setToolTipText("Page d'accueil");
        createPane.add(homeBtn);
        homeBtn.setContentAreaFilled(false);
        homeBtn.addActionListener(e -> {
            Home home = new Home();
            home.setVisible(true);
            dispose();
        });

        /*
          Bouton Logout
         */
        ImageIcon logoutIcon = new ImageIcon("src/media/images/logout.png");
        Image newLogoutImg = logoutIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logoutIcon.setImage(newLogoutImg);

        JButton logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(600, 600, 50, 50);
        logoutBtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, logoutIcon));
        logoutBtn.setForeground(new Color(37, 88, 167));
        logoutBtn.setToolTipText("Quitter");
        createPane.add(logoutBtn);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.addActionListener(e -> close());
    }

    /**
     * Méthode de formatage de la date attestation
     * Récupère la date attestation renseignée dans le frame
     * Implémente la méthode dateSuffix()
     * Retourne la date au format "EEEE dd MMMM uuuu
     */
    public String getDateAttestationFormat() {
        Date date = certificateDate.getDateEditor().getDate();
        OffsetDateTime odt = date.toInstant().atOffset(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.format("EEEE d'%s' MMMM uuuu", dateSuffix(odt.getDayOfMonth())), Locale.FRANCE);
        return odt.format(formatter);
    }

    /**
     * Méthode de test sur le jour du mois
     */
    static String dateSuffix(final int dayOfMonth) {
        return (dayOfMonth % 31 == 1 || dayOfMonth == 1) ? "er" : " ";
    }

    /**
     * Vérification de la validité des champs
     * Si champs et dossier de destination valide créé le pdf
     * Sinon retourne erreur
     */
    public void isInputValid() throws InvalidFormatException, IOException, ParseException {
        Certificate certificate = new Certificate(this, new EditCompany());
        /*if (("".equals(getCustomerNameTxt())) || "".equals(getCustomerFirstnameTxt()) || "".equals(getCustomerCityTxt()) || "".equals(getCustomerAddressTxt()) ||
                "".equals(getCustomerZipTxt()) || "".equals(getDateAttestationFormat()) || "".equals(getFiscalYearTxt()) || "".equals(getCertificateAmountTxt())) {
            JOptionPane.showMessageDialog(new JOptionPane(), "Merci de remplir tous les champs");
        } else {*/
        if (certificate.savePdf(this)) {
            //CustomerDb customerDb = new CustomerDb();
            //customerDb.addCustomer(this);
            saveLbl.setText("Attestation enregistrée.");
            saveLbl.setForeground(Color.GREEN);
        } else {
            saveLbl.setText("Cette attestation a déjà été enregistrée dans le dossier ciblé");
            saveLbl.setForeground(Color.RED);
        }
    }


    /**
     * Fermeture de l'app
     */
    private void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}
