package view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditCustomer extends JFrame {

    private final JComboBox<String> editCustomerTitleCmb;
    private final JTextField editCustomerNameTxt;
    private final JTextField editCustomerFirstnameTxt;
    private final JTextField editCustomerAddressTxt;
    private final JTextField editCustomerCityTxt;
    private final JTextField editCustomerZipTxt;
    private final JTextField editCertificateAmountTxt;
    private final JDateChooser editCertificateDate;
    private final JTextField editFiscalYearTxt;
    private final JLabel editSaveLbl;

    public JComboBox<String> getEditCustomerTitleCmb() {
        return editCustomerTitleCmb;
    }

    public JTextField getEditCustomerNameTxt() {
        return editCustomerNameTxt;
    }

    public JTextField getEditCustomerFirstnameTxt() {
        return editCustomerFirstnameTxt;
    }

    public JTextField getEditCustomerAddressTxt() {
        return editCustomerAddressTxt;
    }

    public JTextField getEditCustomerCityTxt() {
        return editCustomerCityTxt;
    }

    public JTextField getEditCustomerZipTxt() {
        return editCustomerZipTxt;
    }

    public JTextField getEditCertificateAmountTxt() {
        return editCertificateAmountTxt;
    }

    public JDateChooser getEditCertificateDate() {
        return editCertificateDate;
    }

    public JTextField getEditFiscalYearTxt() {
        return editFiscalYearTxt;
    }

    public JLabel getEditSaveLbl() {
        return editSaveLbl;
    }


    public EditCustomer() {

        /*
          Editer client
         */
        JPanel editCustomerPane = new JPanel();
        setTitle("Modification données client - Arkadia PC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 720);
        editCustomerPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(editCustomerPane);
        setLocationRelativeTo(null);
        editCustomerPane.setLayout(null);

        JLabel editLbl = new JLabel();
        editLbl.setBounds(30, 30, 600, 30);
        editLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
        editLbl.setText("Modifier les données client et/ou de l'attestation");
        editLbl.setHorizontalAlignment(SwingConstants.CENTER);
        editCustomerPane.add(editLbl);

        JLabel editCustomInfoLbl = new JLabel();
        editCustomInfoLbl.setBounds(50, 110, 600, 30);
        editCustomInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        editCustomInfoLbl.setText("Informations client");
        editCustomerPane.add(editCustomInfoLbl);

        JLabel editCertInfoLbl = new JLabel();
        editCertInfoLbl.setBounds(50, 390, 600, 30);
        editCertInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        editCertInfoLbl.setText("Informations attestation");
        editCustomerPane.add(editCertInfoLbl);

         /*
          Titre
         */
        JLabel editCustomerTitleLbl = new JLabel("Titre");
        editCustomerTitleLbl.setBounds(50, 150, 120, 14);
        editCustomerPane.add(editCustomerTitleLbl);

        editCustomerTitleCmb = new JComboBox<>();
        editCustomerTitleCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        editCustomerTitleCmb.setBounds(50, 175, 150, 25);
        editCustomerPane.add(editCustomerTitleCmb);

        /*
          Nom
         */
        JLabel editCustomerNameLbl = new JLabel("Nom");
        editCustomerNameLbl.setBounds(230, 150, 120, 14);
        editCustomerPane.add(editCustomerNameLbl);

        editCustomerNameTxt = new JTextField();
        editCustomerNameTxt.setBounds(230, 175, 150, 25);
        editCustomerPane.add(editCustomerNameTxt);

        /*
          Prénom
         */
        JLabel editCustomerFirstnameLbl = new JLabel("Prénom");
        editCustomerFirstnameLbl.setBounds(410, 150, 120, 14);
        editCustomerPane.add(editCustomerFirstnameLbl);

        editCustomerFirstnameTxt = new JTextField();
        editCustomerFirstnameTxt.setBounds(410, 175, 150, 25);
        editCustomerPane.add(editCustomerFirstnameTxt);

        /*
          Adresse
         */
        JLabel editCustomerAddressLbl = new JLabel("Adresse");
        editCustomerAddressLbl.setBounds(50, 230, 120, 14);
        editCustomerPane.add(editCustomerAddressLbl);

        editCustomerAddressTxt = new JTextField();
        editCustomerAddressTxt.setBounds(50, 255, 510, 25);
        editCustomerPane.add(editCustomerAddressTxt);

        /*
          Ville
         */
        JLabel editCustomerCityLbl = new JLabel("Ville");
        editCustomerCityLbl.setBounds(50, 300, 120, 14);
        editCustomerPane.add(editCustomerCityLbl);

        editCustomerCityTxt = new JTextField();
        editCustomerCityTxt.setBounds(50, 325, 330, 25);
        editCustomerPane.add(editCustomerCityTxt);

        /*
          Code Postal
         */
        JLabel editCustomerZipLbl = new JLabel("Code Postal");
        editCustomerZipLbl.setBounds(410, 300, 120, 14);
        editCustomerPane.add(editCustomerZipLbl);

        editCustomerZipTxt = new JTextField();
        editCustomerZipTxt.setBounds(410, 325, 150, 25);
        editCustomerPane.add(editCustomerZipTxt);

        /*
         Montant attestation
        */
        JLabel editCertificateAmountLbl = new JLabel("Montant total");
        editCertificateAmountLbl.setBounds(50, 440, 120, 14);
        editCustomerPane.add(editCertificateAmountLbl);

        editCertificateAmountTxt = new JTextField();
        editCertificateAmountTxt.setBounds(50, 465, 150, 25);
        editCustomerPane.add(editCertificateAmountTxt);

        /*
          Choix exercice fiscal
         */
        JLabel editFiscalYearLbl = new JLabel("Année fiscale");
        editFiscalYearLbl.setBounds(230, 440, 120, 14);
        editCustomerPane.add(editFiscalYearLbl);

        editFiscalYearTxt = new JTextField();
        editFiscalYearTxt.setBounds(230, 465, 150, 25);
        editCustomerPane.add(editFiscalYearTxt);

        /*
          Date attestation
        */
        JLabel editCertificateDateLbl = new JLabel("Date d'émission");
        editCertificateDateLbl.setBounds(410, 440, 120, 14);
        editCustomerPane.add(editCertificateDateLbl);

        editCertificateDate = new JDateChooser();
        editCertificateDate.setDateFormatString("dd MMMM yyyy");
        editCertificateDate.setDate(Calendar.getInstance(Locale.FRANCE).getTime());
        editCertificateDate.setBounds(410, 465, 150, 25);
        editCustomerPane.add(editCertificateDate);
        editCertificateDate.setEnabled(true);

         /*
          Bouton enregistrer
         */
        JButton editSaveBtn = new JButton("Modifier");
        editSaveBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        editSaveBtn.setBounds(50, 550, 150, 40);
        editSaveBtn.setForeground(new Color(37, 88, 167));
        editCustomerPane.add(editSaveBtn);
        editSaveBtn.addActionListener(e -> {
        });

        editSaveLbl = new JLabel();
        editSaveLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
        editSaveLbl.setBounds(50, 610, 500, 50);
        editSaveLbl.setText("Modifie également la base de données");
        editCustomerPane.add(editSaveLbl);

        /*
          Btn importer data client
         */
        JButton importCustomerBtn = new JButton(("Importer"));
        importCustomerBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        importCustomerBtn.setBounds(410, 85, 150, 40);
        importCustomerBtn.setForeground(new Color(37, 88, 167));
        editCustomerPane.add((importCustomerBtn));
        importCustomerBtn.addActionListener(e -> {
            CustomerDataManagement customerDataManagement = new CustomerDataManagement();
            customerDataManagement.setVisible(true);
            dispose();
        });

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
        editCustomerPane.add(homeBtn);
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
        Image newLogoutImg = logoutIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH );
        logoutIcon.setImage(newLogoutImg);

        JButton logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(600, 600, 50, 50);
        logoutBtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, logoutIcon));
        logoutBtn.setForeground(new Color(37, 88, 167));
        logoutBtn.setToolTipText("Quitter");
        editCustomerPane.add(logoutBtn);
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
        Date date = editCertificateDate.getDateEditor().getDate();
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
     * Fermeture de l'app
     */
    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}
