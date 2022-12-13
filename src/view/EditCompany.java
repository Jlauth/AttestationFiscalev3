package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class EditCompany extends JFrame {

    private final JTextField companyApprovalTxt;
    private final JTextField companyNameTxt;
    private final JComboBox<String> holderTitleCmb;
    private final JTextField holderNameTxt;
    private final JTextField holderFirstnameTxt;
    private final JTextField companyAddressTxt;
    private final JTextField companyCityTxt;
    private final JTextField companyZipTxt;
    private final JTextField companyTelTxt;
    private final JTextField companyMailTxt;

    public String getCompanyTelTxt() {
        return "+33 (1) 47 08 98 38";
    }

    public String getCompanyMailTxt() {
        return "contact@arkadia-pc.fr";
    }

    public String getCompanyAddressTxt() {
        return "4, rue des Pyrénées";
    }

    public String getCompanyZipTxt() {
        return "92500";
    }

    public String getCompanyCityTxt() {
        return "Reuil Malmaison";
    }

    public String getHolderNameTxt() {
        return "ARAUJO";
    }

    public String getHolderFirstnameTxt() {
        return "Adelino";
    }

    public String getCompanyNameTxt() {
        return "Arkadia PC";
    }


    public String getCompanyApprovalTxt() {
        return "Agrément N° SAP524160330";
    }

    public String getHolderTitleCmb() {
        return "Monsieur";
                //Objects.requireNonNull(holderTitleCmb.getSelectedItem()).toString();
    }

    public EditCompany() {

         /*
          Editer entreprise
         */
        JPanel editCompanyPane = new JPanel();
        setTitle("Edition des données entreprise");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        editCompanyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(editCompanyPane);
        setLocationRelativeTo(null);
        editCompanyPane.setLayout(null);

         /*
          Numéro agrément
         */
        JLabel companyApprovalLbl = new JLabel("Numéro d'agrément");
        companyApprovalLbl.setBounds(40, 10, 120, 14);
        editCompanyPane.add(companyApprovalLbl);

        companyApprovalTxt = new JTextField();
        companyApprovalTxt.setBounds(40, 30, 150, 22);
        editCompanyPane.add(companyApprovalTxt);

        /*
          Nom entreprise
         */
        JLabel companyNameLbl = new JLabel("Nom entreprise");
        companyNameLbl.setBounds(40, 60, 120, 14);
        editCompanyPane.add(companyNameLbl);

        companyNameTxt = new JTextField();
        companyNameTxt.setBounds(40, 80, 150, 20);
        editCompanyPane.add(companyNameTxt);

        /*
          Titre gérant
         */
        JLabel holderTitleLbl = new JLabel("Titre");
        holderTitleLbl.setBounds(40, 60, 120, 14);
        editCompanyPane.add(holderTitleLbl);

        holderTitleCmb = new JComboBox<>();
        holderTitleCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        holderTitleCmb.setBounds(40, 80, 150, 20);
        editCompanyPane.add(holderTitleCmb);

        /*
          Nom gérant
         */
        JLabel holderNameLbl = new JLabel("Nom du gérant");
        holderNameLbl.setBounds(40, 110, 120, 14);
        editCompanyPane.add(holderNameLbl);

        holderNameTxt = new JTextField();
        holderNameTxt.setBounds(40, 130, 150, 20);
        editCompanyPane.add(holderNameTxt);

        /*
          Prénom
         */
        JLabel holderFirstnameLbl = new JLabel("Prénom du gérant");
        holderFirstnameLbl.setBounds(40, 160, 46, 14);
        editCompanyPane.add(holderFirstnameLbl);

        holderFirstnameTxt = new JTextField();
        holderFirstnameTxt.setBounds(40, 180, 153, 20);
        editCompanyPane.add(holderFirstnameTxt);

        /*
          Adresse
         */
        JLabel companyAddressLbl = new JLabel("Adresse");
        companyAddressLbl.setBounds(40, 210, 120, 14);
        editCompanyPane.add(companyAddressLbl);

        companyAddressTxt = new JTextField();
        companyAddressTxt.setBounds(40, 230, 150, 20);
        editCompanyPane.add(companyAddressTxt);

        /*
          Ville
         */
        JLabel companyCityLbl = new JLabel("Ville");
        companyCityLbl.setBounds(40, 260, 120, 14);
        editCompanyPane.add(companyCityLbl);

        companyCityTxt = new JTextField();
        companyCityTxt.setBounds(40, 280, 150, 20);
        editCompanyPane.add(companyCityTxt);

        /*
          Code Postal
         */
        JLabel companyZipLbl = new JLabel("Code Postal");
        companyZipLbl.setBounds(40, 310, 120, 14);
        editCompanyPane.add(companyZipLbl);

        companyZipTxt = new JTextField();
        companyZipTxt.setBounds(40, 330, 150, 20);
        editCompanyPane.add(companyZipTxt);

        /*
        Tel
         */
        JLabel companyTelLbl = new JLabel("Numéro de téléphone");
        companyTelLbl.setBounds(40, 400, 210, 14);
        editCompanyPane.add(companyTelLbl);

        companyTelTxt = new JTextField();
        companyTelTxt.setBounds(40, 420, 210, 20);
        editCompanyPane.add(companyTelTxt);

        /*
        Mail
         */
        JLabel companyMailLbl = new JLabel("Mail");
        companyMailLbl.setBounds(300, 400, 210, 14);
        editCompanyPane.add(companyMailLbl);

        companyMailTxt = new JTextField();
        companyMailTxt.setBounds(300, 420, 210, 20);
        editCompanyPane.add(companyMailTxt);

        /*
          Btn enregistrer
         */
        JButton companySaveBtn = new JButton("Enregistrer");
        companySaveBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        companySaveBtn.setBounds(50, 500, 120, 50);
        editCompanyPane.add(companySaveBtn);
        companySaveBtn.addActionListener(e -> {

        });

        /*
          Btn accueil
         */
        JButton homeBtn = new JButton("Home");
        homeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        homeBtn.setBounds(450, 10, 120, 50);
        editCompanyPane.add(homeBtn);
        homeBtn.addActionListener(e -> {
            Home home = new Home();
            home.setVisible(true);
            dispose();
        });

        /*
          Btn Quitter
         */
        JButton logoutBtn = new JButton("Quitter");
        logoutBtn.setForeground(Color.RED);
        logoutBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        logoutBtn.setBounds(450, 490, 120, 50);
        editCompanyPane.add(logoutBtn);
        logoutBtn.addActionListener(e -> close());
    }

    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }


}
