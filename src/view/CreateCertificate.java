package view;

import com.toedter.calendar.JDateChooser;
import connect.CustomerDB;
import model.Certificate;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

public class CreateCertificate extends JFrame {
    private static JButton logoutBtn, homeBtn, customerBtn, newCustomerBtn;
    private final Border lineBorderLogout = BorderFactory.createLineBorder(new Color(229, 83, 80));
    private final Border lineBorderHome = BorderFactory.createLineBorder(new Color (0, 138, 173));
    private final Insets insets = lineBorderLogout.getBorderInsets(logoutBtn);
    private final EmptyBorder emptyBorder = new EmptyBorder(insets);
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

    public String getCustomerTitleCmb() {
        if (customerTitleCmb.getSelectedItem() == "Aucun titre") {
            return "";
        } else {
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
        setTitle("Attestation fiscale - Arkadia PC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 820);
        createPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(createPane);
        setLocationRelativeTo(null);
        createPane.setLayout(null);

        JLabel createLbl = new JLabel();
        createLbl.setBounds(30, 30, 600, 30);
        createLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        createLbl.setText("Création d'une nouvelle attestation fiscale");
        createLbl.setHorizontalAlignment(SwingConstants.CENTER);
        createPane.add(createLbl);

        JLabel createCustomInfoLbl = new JLabel();
        createCustomInfoLbl.setBounds(50, 210, 600, 30);
        createCustomInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        createCustomInfoLbl.setText("Informations client");
        createPane.add(createCustomInfoLbl);

        JLabel createCertifInfoLbl = new JLabel();
        createCertifInfoLbl.setBounds(50, 490, 600, 30);
        createCertifInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        createCertifInfoLbl.setText("Informations attestation");
        createPane.add(createCertifInfoLbl);

        /*
          Titre
         */
        JLabel customerTitleLbl = new JLabel("Titre");
        customerTitleLbl.setBounds(50, 250, 120, 14);
        createPane.add(customerTitleLbl);

        customerTitleCmb = new JComboBox<>();
        customerTitleCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        customerTitleCmb.setBounds(50, 275, 150, 25);
        createPane.add(customerTitleCmb);

        /*
          Nom
         */
        JLabel customerNameLbl = new JLabel("Nom");
        customerNameLbl.setBounds(230, 250, 120, 14);
        createPane.add(customerNameLbl);

        customerNameTxt = new JTextField();
        customerNameTxt.setBounds(230, 275, 150, 25);
        createPane.add(customerNameTxt);

        /*
          Prénom
         */
        JLabel customerFirstnameLbl = new JLabel("Prénom");
        customerFirstnameLbl.setBounds(410, 250, 120, 14);
        createPane.add(customerFirstnameLbl);

        customerFirstnameTxt = new JTextField();
        customerFirstnameTxt.setBounds(410, 275, 150, 25);
        createPane.add(customerFirstnameTxt);

        /*
          Adresse
         */
        JLabel customerAddressLbl = new JLabel("Adresse");
        customerAddressLbl.setBounds(50, 330, 120, 14);
        createPane.add(customerAddressLbl);

        customerAddressTxt = new JTextField();
        customerAddressTxt.setBounds(50, 355, 510, 25);
        createPane.add(customerAddressTxt);

        /*
          Ville
         */
        JLabel customerCityLbl = new JLabel("Ville");
        customerCityLbl.setBounds(50, 400, 120, 14);
        createPane.add(customerCityLbl);

        customerCityTxt = new JTextField();
        customerCityTxt.setBounds(50, 425, 330, 25);
        createPane.add(customerCityTxt);

        /*
          Code Postal
         */
        JLabel customerZipLbl = new JLabel("Code Postal");
        customerZipLbl.setBounds(410, 400, 120, 14);
        createPane.add(customerZipLbl);

        customerZipTxt = new JTextField();
        customerZipTxt.setBounds(410, 425, 150, 25);
        createPane.add(customerZipTxt);

        /*
         Montant attestation
        */
        JLabel certificateAmountLbl = new JLabel("Montant total");
        certificateAmountLbl.setBounds(50, 540, 120, 14);
        createPane.add(certificateAmountLbl);

        certificateAmountTxt = new JTextField();
        certificateAmountTxt.setBounds(50, 565, 150, 25);
        createPane.add(certificateAmountTxt);

        /*
          Choix exercice fiscal
         */
        JLabel fiscalYearLbl = new JLabel("Année fiscale");
        fiscalYearLbl.setBounds(230, 540, 120, 14);
        createPane.add(fiscalYearLbl);

        fiscalYearTxt = new JTextField();
        fiscalYearTxt.setBounds(230, 565, 150, 25);
        fiscalYearTxt.getText();
        createPane.add(fiscalYearTxt);

        /*
          Date attestation
        */
        JLabel certificateDateLbl = new JLabel("Date d'émission");
        certificateDateLbl.setBounds(410, 540, 120, 14);
        createPane.add(certificateDateLbl);

        certificateDate = new JDateChooser();
        certificateDate.setDateFormatString("dd MMMM yyyy");
        certificateDate.setDate(Calendar.getInstance(Locale.FRANCE).getTime());
        certificateDate.setBounds(410, 565, 150, 25);
        createPane.add(certificateDate);
        certificateDate.setEnabled(true);

        /*
          Bouton crée attestation
         */
        JButton saveBtn = new JButton("Créer");
        saveBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        saveBtn.setBounds(50, 650, 200, 50);
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
        saveLbl.setBounds(50, 710, 500, 50);
        saveLbl.setText("Enregistre également les données client");
        createPane.add(saveLbl);

        /*
         Btn Home
         */
        ImageIcon homeIcon = new ImageIcon("src/media/images/home.png");
        Image newHomeImg = homeIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        homeIcon.setImage(newHomeImg);

        homeBtn = new JButton(homeIcon);
        homeBtn.setBounds(650, 10, 60, 60);
        homeBtn.setToolTipText("Page d'accueil");
        homeBtn.setBorder(emptyBorder);
        homeBtn.setFocusPainted(false);
        homeBtn.setOpaque(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setToolTipText("Accueil");
        // action changement du visuel
        homeBtn.getModel().addChangeListener(new ChangeListener(){
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if(model.isRollover()){
                    homeBtn.setBorder(lineBorderHome);
                } else {
                    homeBtn.setBorder(emptyBorder);
                }
            }
        });
        homeBtn.addActionListener(e -> {
           home();
        });
        createPane.add(homeBtn);

        /*
          Bouton Logout
         */
        // transfo icon vers image afin de pouvoir la scale aux dimensions logoutBtn
        ImageIcon logoutIcon = new ImageIcon("src/media/images/logoutbis.png");
        Image logoutImg = logoutIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoutIcon.setImage(logoutImg);
        // logoutBtn
        logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(650, 700, 60, 60);
        logoutBtn.setBorder(emptyBorder);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setToolTipText("Quitter");
        // action close
        logoutBtn.addActionListener(e -> close());
        // action changement du visuel
        logoutBtn.getModel().addChangeListener(new ChangeListener(){
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if(model.isRollover()){
                    logoutBtn.setBorder(lineBorderLogout);
                } else {
                    logoutBtn.setBorder(emptyBorder);
                }
            }
        });
        createPane.add(logoutBtn);

        /*
        Bouton nouveau client
         */
        newCustomerBtn = new JButton("Nouveau client");
        newCustomerBtn.setToolTipText("Crée un client en même temps que l'attestation");
        newCustomerBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        newCustomerBtn.setBounds(50, 120, 200, 50);
        newCustomerBtn.setForeground(new Color(37, 88, 167));
        createPane.add(newCustomerBtn);

        /*
        Bouton client existant
         */
        customerBtn = new JButton("Client existant");
        customerBtn.setToolTipText("Permet la sélection d'un client enregistré en base de données");
        customerBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        customerBtn.setBounds(360, 120, 200, 50);
        customerBtn.setForeground(new Color(37, 88, 167));
        createPane.add(customerBtn);
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
        return odt.format(FORMATTER_DATE_ATTEST);
    }

    private static final DateTimeFormatter FORMATTER_DATE_ATTEST = new DateTimeFormatterBuilder()
            .appendPattern("EEEE ")
            .appendText(ChronoField.DAY_OF_MONTH, getDayOfMonthMap())
            .appendPattern(" MMMM yyyy")
            .toFormatter(Locale.FRANCE);

    public static Map<Long, String> getDayOfMonthMap(){
        Map<Long, String> dayOfMonthMap = new HashMap<>(Map.of(1L, "1er"));
        for(long d = 2; d <= 31; d++){
            dayOfMonthMap.put(d, String.valueOf(d));
        }
        return dayOfMonthMap;
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
            CustomerDB customerDb = new CustomerDB();
            //customerDb.addCustomer(this);
        } else {
            saveLbl.setText("Cette attestation a déjà été enregistrée dans le dossier ciblé");
            saveLbl.setForeground(Color.RED);
        }
    }

    /**
     * Retour Accueil
     */
    private void home(){
        int n = JOptionPane.showOptionDialog(new JFrame(), "Retourner à l'accueil?", "Accueil", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            Home home = new Home();
            home.setVisible(true);
            dispose();
        }
    }
    /**
     * Fermeture de l'app
     */
    private void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
            saveLbl.setText("Attestation enregistrée.");
            saveLbl.setForeground(Color.GREEN);
        }
    }
}
