package view;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import connect.CompanyDB;
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

/**
 * Classe CertificateFrame représentant la fenêtre de création d'une attestation fiscale.
 */
public class CertificateFrame extends JFrame {

    // Déclaration des boutons de déconnexion et de retour à l'accueil
    private static JButton logoutBtn;
    private static JButton homeBtn;

    // Création des bordures pour les boutons de déconnexion et de retour à l'accueil
    private final Border lineBorderLogout = BorderFactory.createLineBorder(new Color(229, 83, 80));
    private final Border lineBorderHome = BorderFactory.createLineBorder(new Color(0, 138, 173));

    // Récupération des insets (marges intérieures) de la bordure de déconnexion pour créer une EmptyBorder
    private final Insets insets = lineBorderLogout.getBorderInsets(logoutBtn);
    private final EmptyBorder emptyBorder = new EmptyBorder(insets);

    // Création des champs pour les informations du client
    private final JComboBox<String> customerTitleCmb;
    private final JTextField customerNameTxt;
    private final JTextField customerFirstNTxt;
    private final JTextField customerAddressTxt;
    private final JTextField customerCityTxt;
    private final JTextField customerZipTxt;

    // Création des champs pour le montant, la date et l'année fiscale de l'attestation
    private final JTextField certificateAmountTxt;
    private final JDateChooser certificateDate;
    private final JTextField fiscalYearTxt;

    // Affichage du label de sauvegarde
    private final JLabel saveLbl;

    /**
     * Getter pour récupérer le titre de civilité du client sélectionné dans la liste déroulante.
     * Si aucun titre n'est sélectionné, une chaîne vide est retournée.
     * @return String représentant le titre de civilité sélectionné.
     */
    public String getCustomerTitleCmb() {
        if (customerTitleCmb.getSelectedItem() == "Aucun titre") {
            return "";
        } else {
            return Objects.requireNonNull(customerTitleCmb.getSelectedItem()).toString();
        }
    }

    /**
     *  Getter pour récupérer le titre de civilité abrégé du client sélectionné dans la liste déroulante.
     *  Les titres abrégés possibles sont "Mme" pour Madame, "Mr" pour Monsieur et "Mlle" pour Mademoiselle.
     *  @return String représentant le titre de civilité abrégé sélectionné.
     */
    public String getCustomerTitleCmbShorted() {
        return switch (Objects.requireNonNull(customerTitleCmb.getSelectedItem()).toString()) {
            case "Madame" -> "Mme";
            case "Monsieur" -> "Mr";
            case "Mademoiselle" -> "Mlle";
            default -> "";
        };
    }

    /**
     * Getter pour récupérer le nom du client saisi dans le champ de texte correspondant.
     * @return String représentant le nom du client.
     */
    public String getCustomerNameTxt() {
        return customerNameTxt.getText();
    }

    /**
     * Getter pour récupérer le prénom du client saisi dans le champ de texte correspondant.
     * @return String représentant le prénom du client.
     */
    public String getCustomerFirstNTxt() {
        return customerFirstNTxt.getText();
    }

    /**
     * Getter pour récupérer l'adresse du client saisie dans le champ de texte correspondant.
     * @return String représentant l'adresse du client.
     */
    public String getCustomerAddressTxt() {
        return customerAddressTxt.getText();
    }

    /**
     * Getter pour récupérer la ville du client saisie dans le champ de texte correspondant
     * @return String représentant la ville du client.
     */
    public String getCustomerCityTxt() {
        return customerCityTxt.getText();
    }

    /**
     * Getter pour récupérer le code postal du client saisi dans le champ de texte correspondant.
     * @return String représentant le code postal du client.
     */
    public String getCustomerZipTxt() {
        return customerZipTxt.getText();
    }

    /**
     * Getter pour récupérer le montant de l'attestation saisi dans le champ de texte correspondant.
     * @return String représentant le montant de l'attestation.
     */
    public String getCertificateAmountTxt() {
        return certificateAmountTxt.getText();
    }

    /**
     * Getter pour récupérer l'année fiscale saisie dans le champ de texte correspondant.
     * @return String représentant l'année fiscale.
     */
    public String getFiscalYearTxt() {
        return fiscalYearTxt.getText();
    }

    /**
     * Configuration du frame CertificateFrame
     */
    public CertificateFrame() {

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
        createLbl.setBounds(30, 70, 600, 30);
        createLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        createLbl.setText("Création d'une nouvelle attestation fiscale");
        createLbl.setForeground(Color.BLACK);
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
         * Titre du client
         */
        JLabel customerTitleLbl = new JLabel("Titre");
        customerTitleLbl.setBounds(50, 250, 120, 14);
        createPane.add(customerTitleLbl);

        customerTitleCmb = new JComboBox<>();
        customerTitleCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        customerTitleCmb.setBounds(50, 275, 150, 25);
        createPane.add(customerTitleCmb);

        /*
         * Nom du client
         */
        JLabel customerNameLbl = new JLabel("Nom");
        customerNameLbl.setBounds(230, 250, 120, 14);
        createPane.add(customerNameLbl);

        customerNameTxt = new JTextField();
        customerNameTxt.setBounds(230, 275, 150, 25);
        createPane.add(customerNameTxt);

        /*
         * Prénom du client
         */
        JLabel customerFirstNLbl = new JLabel("Prénom");
        customerFirstNLbl.setBounds(410, 250, 120, 14);
        createPane.add(customerFirstNLbl);

        customerFirstNTxt = new JTextField();
        customerFirstNTxt.setBounds(410, 275, 150, 25);
        createPane.add(customerFirstNTxt);

        /*
         * Adresse du client
         */
        JLabel customerAddressLbl = new JLabel("Adresse");
        customerAddressLbl.setBounds(50, 330, 120, 14);
        createPane.add(customerAddressLbl);

        customerAddressTxt = new JTextField();
        customerAddressTxt.setBounds(50, 355, 510, 25);
        createPane.add(customerAddressTxt);

        /*
         * Ville du client
         */
        JLabel customerCityLbl = new JLabel("Ville");
        customerCityLbl.setBounds(50, 400, 120, 14);
        createPane.add(customerCityLbl);

        customerCityTxt = new JTextField();
        customerCityTxt.setBounds(50, 425, 330, 25);
        createPane.add(customerCityTxt);

        /*
         * Code Postal du client
         */
        JLabel customerZipLbl = new JLabel("Code Postal");
        customerZipLbl.setBounds(410, 400, 120, 14);
        createPane.add(customerZipLbl);

        customerZipTxt = new JTextField();
        customerZipTxt.setBounds(410, 425, 150, 25);
        createPane.add(customerZipTxt);

        /*
         * Montant de l'attestation
         */
        JLabel certificateAmountLbl = new JLabel("Montant total");
        certificateAmountLbl.setBounds(50, 540, 120, 14);
        createPane.add(certificateAmountLbl);

        certificateAmountTxt = new JTextField();
        certificateAmountTxt.setBounds(50, 565, 150, 25);
        createPane.add(certificateAmountTxt);

        /*
         * Choix de l'exercice fiscal
         */
        JLabel fiscalYearLbl = new JLabel("Année fiscale");
        fiscalYearLbl.setBounds(230, 540, 120, 14);
        createPane.add(fiscalYearLbl);

        fiscalYearTxt = new JTextField();
        fiscalYearTxt.setBounds(230, 565, 150, 25);
        fiscalYearTxt.getText();
        createPane.add(fiscalYearTxt);

        /*
         * Date attestation
         */
        JLabel certificateDateLbl = new JLabel("Date d'émission");
        certificateDateLbl.setBounds(410, 540, 120, 14);
        createPane.add(certificateDateLbl);

        certificateDate = new JDateChooser();
        certificateDate.setDateFormatString("dd MMMM yyyy");
        certificateDate.setDate(Calendar.getInstance(Locale.FRANCE).getTime());
        certificateDate.setBounds(410, 565, 150, 25);
        createPane.add(certificateDate);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) certificateDate.getDateEditor();
        editor.setEditable(false);
        editor.setEnabled(false);
        editor.setDisabledTextColor(Color.BLACK);

        /*
         * Ajout d'un bouton de sauvegarde pour créer une nouvelle attestation avec des vérifications de validation d'entrée.
         * Le bouton appelle la méthode "isInputValid()" pour vérifier la validité des entrées.
         * Si les entrées sont valides, une nouvelle attestation est créée. Sinon, les erreurs sont gérées et une exception est levée.
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
        createPane.add(saveLbl);

        /*
         * Bouton d'accueil avec une icône et un effet de survol. Lorsque l'utilisateur clique sur le bouton,
         * la méthode "home()" est appelée pour retourner à la page d'accueil.
         */
        ImageIcon homeIcon = new ImageIcon("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\home.png");
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
        homeBtn.getModel().addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    homeBtn.setBorder(lineBorderHome);
                } else {
                    homeBtn.setBorder(emptyBorder);
                }
            }
        });
        homeBtn.addActionListener(e -> home());
        createPane.add(homeBtn);

        /* Création d'un bouton de déconnexion avec une icône personnalisée,
         * un texte d'info-bulle et une action associée à son clic pour fermer l'application.
         * Le visuel du bouton est également configuré pour changer lorsque la souris est au-dessus.
         */
        ImageIcon logoutIcon = new ImageIcon("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\logoutbis.png");
        Image logoutImg = logoutIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoutIcon.setImage(logoutImg);
        logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(650, 700, 60, 60);
        logoutBtn.setBorder(emptyBorder);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setToolTipText("Quitter");
        logoutBtn.addActionListener(e -> close());
        logoutBtn.getModel().addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
             *
             * @param e a ChangeEvent object
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    logoutBtn.setBorder(lineBorderLogout);
                } else {
                    logoutBtn.setBorder(emptyBorder);
                }
            }
        });
        createPane.add(logoutBtn);
    }

    /**
     * Récupère la date sélectionnée dans l'interface graphique du formulaire d'attestation
     * et la formate selon le format défini par la constante FORMATTER_DATE_ATTEST.
     * @return une chaîne de caractères représentant la date au format défini.
     */
    public String getDateAttestationFormat() {
        Date date = certificateDate.getDateEditor().getDate();
        OffsetDateTime odt = date.toInstant().atOffset(ZoneOffset.UTC);
        return odt.format(FORMATTER_DATE_ATTEST);
    }

    /**
     * Formatter de date pour l'attestation, utilisant les éléments suivants :
     * - Jour de la semaine en toutes lettres
     * - Jour du mois en chiffres et en lettres ordinaires (ex: 1er)
     * - Mois en toutes lettres
     * - Année sur 4 chiffres
     */
    private static final DateTimeFormatter FORMATTER_DATE_ATTEST = new DateTimeFormatterBuilder()
            .appendPattern("EEEE ")
            .appendText(ChronoField.DAY_OF_MONTH, getDayOfMonthMap())
            .appendPattern(" MMMM yyyy")
            .toFormatter(Locale.FRANCE);

    /**
     * Retourne une map associant chaque jour du mois, représenté en nombre, à une chaîne de caractères
     * indiquant le jour sous forme ordinal pour le jour 1, et sous forme de nombre pour les jours de 2 à 31.
     * @return la map associant chaque jour du mois à sa représentation en chaîne de caractères
     */
    public static Map<Long, String> getDayOfMonthMap() {
        Map<Long, String> dayOfMonthMap = new HashMap<>(Map.of(1L, "1er"));
        for (long d = 2; d <= 31; d++) {
            dayOfMonthMap.put(d, String.valueOf(d));
        }
        return dayOfMonthMap;
    }

    /**
     * Vérifie si les entrées sont valides avant de générer le certificat.
     * Si l'entrée est invalide, une erreur est affichée dans une boîte de dialogue.
     * @throws InvalidFormatException si une entrée est au mauvais format
     * @throws IOException si une erreur de lecture ou d'écriture se produit
     * @throws ParseException si une erreur de conversion de date se produit
     */
    public void isInputValid() throws InvalidFormatException, IOException, ParseException {
        CompanyDB companyDB = new CompanyDB();
        Certificate certificate = new Certificate(this, companyDB);
        if (!getEmptyFields().isEmpty()) {
            JOptionPane.showMessageDialog(new JOptionPane(), "Merci de remplir les champs suivants : " + getEmptyFields());
        } else {
            // Les entrées sont valides, génère le certificat
            certificate.savePdf(this);
        }
    }

    /**
     * Cette méthode renvoie une chaîne de caractères qui liste les champs obligatoires vides.
     * Elle est utilisée pour fournir un message d'erreur clair lorsque les entrées de l'utilisateur sont invalides.
     */
     public String getEmptyFields() {
        String emptyFields = "";
        if ("".equals(getCustomerNameTxt())) {
            emptyFields += "Nom du client, ";
        }
        if ("".equals(getCustomerFirstNTxt())) {
            emptyFields += "Prénom du client, ";
        }
        if ("".equals(getCustomerCityTxt())) {
            emptyFields += "Ville du client, ";
        }
        if ("".equals(getCustomerAddressTxt())) {
            emptyFields += "Adresse du client, ";
        }
        if ("".equals(getCustomerZipTxt())) {
            emptyFields += "Code postal du client, ";
        }
        if ("".equals(getDateAttestationFormat())) {
            emptyFields += "Date, ";
        }
        if ("".equals(getFiscalYearTxt())) {
            emptyFields += "Année fiscale, ";
        }
        if ("".equals(getCertificateAmountTxt())) {
            emptyFields += "Montant, ";
        }
        // Enlever la dernière virgule et l'espace si la chaîne n'est pas vide
        if (!emptyFields.isEmpty()) {
            emptyFields = emptyFields.substring(0, emptyFields.length() - 2);
        }
        return emptyFields;
    }

    /**
     * Permet de retourner à la page d'accueil après confirmation de l'utilisateur.
     */
    private void home() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Retourner à l'accueil?", "Accueil", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setVisible(true);
            dispose();
        }
    }

    /**
     * Ferme l'application après confirmation de l'utilisateur.
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
