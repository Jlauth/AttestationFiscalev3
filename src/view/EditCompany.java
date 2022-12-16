package view;

import connect.CompanyDB;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static javax.swing.JOptionPane.YES_OPTION;

public class EditCompany extends JFrame {

    private static JButton logoutBtn;
    private static JButton homeBtn;
    private final Border lineBorderLogout = BorderFactory.createLineBorder(new Color(229, 83, 80));
    private final Border lineBorderHome = BorderFactory.createLineBorder(new Color(0, 138, 173));
    private final Insets insets = lineBorderLogout.getBorderInsets(logoutBtn);
    private final EmptyBorder emptyBorder = new EmptyBorder(insets);
    private final JTextField companyApprovalTxt;
    private final JTextField companyNameTxt;
    private final JComboBox<String> holderTitleCmb;
    private final JTextField holderNameTxt;
    private final JTextField holderFirstnameTxt;
    private final JTextField companyAddressTxt;
    private final JTextField companyCityTxt;
    private final JTextField companyZipTxt;
    private final JTextField companyPhoneTxt;
    private final JTextField companyMailTxt;


    public String getCompanyPhoneTxt() {
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
        // initialisation de la DB entreprise pour récupérer les infos enregistrées en interne
        CompanyDB companyDB = new CompanyDB();
        companyDB.selectCompanyInfo();

         /*
          Editer entreprise
         */
        JPanel editCompanyPane = new JPanel();
        setTitle("Données entreprise - Arkadia PC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 820);
        editCompanyPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(editCompanyPane);
        setLocationRelativeTo(null);
        editCompanyPane.setLayout(null);

        JLabel companyLbl = new JLabel();
        companyLbl.setBounds(30, 90, 600, 30);
        companyLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
        companyLbl.setText("Edition des données de votre entreprise");
        companyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        editCompanyPane.add(companyLbl);

        /*
          Titre gérant
         */
        JLabel holderTitleLbl = new JLabel("Titre");
        holderTitleLbl.setBounds(50, 250, 120, 14);
        editCompanyPane.add(holderTitleLbl);

        holderTitleCmb = new JComboBox<>();
        holderTitleCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        holderTitleCmb.setBounds(50, 275, 150, 25);
        holderTitleCmb.setSelectedIndex(3);
        editCompanyPane.add(holderTitleCmb);

        /*
          Nom gérant
         */
        JLabel holderNameLbl = new JLabel("Nom du gérant");
        holderNameLbl.setBounds(230, 250, 120, 14);
        editCompanyPane.add(holderNameLbl);

        holderNameTxt = new JTextField();
        holderNameTxt.setBounds(230, 275, 150, 25);
        holderNameTxt.setText(companyDB.getHolderName());
        editCompanyPane.add(holderNameTxt);

        /*
          Prénom
         */
        JLabel holderFirstnameLbl = new JLabel("Prénom du gérant");
        holderFirstnameLbl.setBounds(410, 250, 120, 14);
        editCompanyPane.add(holderFirstnameLbl);

        holderFirstnameTxt = new JTextField();
        holderFirstnameTxt.setBounds(410, 275, 150, 25);
        holderFirstnameTxt.setText(companyDB.getHolderFirstN());
        editCompanyPane.add(holderFirstnameTxt);

        /*
          Adresse
         */
        JLabel companyAddressLbl = new JLabel("Adresse");
        companyAddressLbl.setBounds(50, 330, 120, 14);
        editCompanyPane.add(companyAddressLbl);

        companyAddressTxt = new JTextField();
        companyAddressTxt.setBounds(50, 355, 510, 25);
        companyAddressTxt.setText(companyDB.getCompanyAddress());
        editCompanyPane.add(companyAddressTxt);

        /*
          Ville
         */
        JLabel companyCityLbl = new JLabel("Ville");
        companyCityLbl.setBounds(50, 400, 120, 14);
        editCompanyPane.add(companyCityLbl);

        companyCityTxt = new JTextField();
        companyCityTxt.setBounds(50, 425, 330, 25);
        companyCityTxt.setText(companyDB.getCompanyCity());
        editCompanyPane.add(companyCityTxt);

        /*
          Code Postal
         */
        JLabel companyZipLbl = new JLabel("Code Postal");
        companyZipLbl.setBounds(410, 400, 120, 14);
        editCompanyPane.add(companyZipLbl);

        companyZipTxt = new JTextField();
        companyZipTxt.setBounds(410, 425, 150, 25);
        companyZipTxt.setText(companyDB.getCompanyZip());
        editCompanyPane.add(companyZipTxt);

         /*
          Numéro agrément
         */
        JLabel companyApprovalLbl = new JLabel("Numéro d'agrément");
        companyApprovalLbl.setBounds(50, 480, 120, 14);
        editCompanyPane.add(companyApprovalLbl);

        companyApprovalTxt = new JTextField();
        companyApprovalTxt.setBounds(50, 500, 150, 25);
        companyApprovalTxt.setText(companyDB.getCompanyApproval());
        editCompanyPane.add(companyApprovalTxt);

        /*
          Nom entreprise
         */
        JLabel companyNameLbl = new JLabel("Nom entreprise");
        companyNameLbl.setBounds(230, 480, 120, 14);
        editCompanyPane.add(companyNameLbl);

        companyNameTxt = new JTextField();
        companyNameTxt.setBounds(230, 500, 150, 25);
        companyNameTxt.setText(companyDB.getCompanyName());
        editCompanyPane.add(companyNameTxt);

        /*
        Tel
         */
        JLabel companyTelLbl = new JLabel("Téléphone");
        companyTelLbl.setBounds(50, 540, 120, 14);
        editCompanyPane.add(companyTelLbl);

        companyPhoneTxt = new JTextField();
        companyPhoneTxt.setBounds(50, 565, 150, 25);
        companyPhoneTxt.setText(companyDB.getCompanyPhone());
        editCompanyPane.add(companyPhoneTxt);

        /*
        Mail
         */
        JLabel companyMailLbl = new JLabel("Mail");
        companyMailLbl.setBounds(230, 540, 120, 14);
        editCompanyPane.add(companyMailLbl);

        companyMailTxt = new JTextField();
        companyMailTxt.setBounds(230, 565, 150, 25);
        companyMailTxt.setText(companyDB.getCompanyMail());
        editCompanyPane.add(companyMailTxt);

        /*
          Btn enregistrer
         */
        JButton companySaveBtn = new JButton("Enregistrer");
        companySaveBtn.setToolTipText("Enregistre les nouvelles informations entreprise");
        companySaveBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        companySaveBtn.setBounds(50, 650, 200, 50);
        editCompanyPane.add(companySaveBtn);
        companySaveBtn.addActionListener(e -> {
            companyDB.selectCompanyInfo();
                    int n = JOptionPane.showOptionDialog(new JFrame(), "Enregistrer les modifications?", "Modifier", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, YES_OPTION);
                    if (n == YES_OPTION) {
                        companyDB.update(1, companyDB.setHolderTitle(String.valueOf(holderTitleCmb.getSelectedIndex())),
                                companyDB.setHolderName(holderNameTxt.getText()),
                                companyDB.setHolderFirstN(holderFirstnameTxt.getText()),
                                companyDB.setCompanyAddress(companyAddressTxt.getText()),
                                companyDB.setCompanyCity(companyCityTxt.getText()),
                                companyDB.setCompanyZip(companyZipTxt.getText()),
                                companyDB.setCompanyApproval(companyApprovalTxt.getText()),
                                companyDB.setCompanyName(companyNameTxt.getText()),
                                companyDB.setCompanyPhone(companyPhoneTxt.getText()),
                                companyDB.setCompanyMail(companyMailTxt.getText()));
                    }
        });

        /*
          Btn accueil
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
        editCompanyPane.add(homeBtn);

        /*
          Btn Quitter
         */
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
        editCompanyPane.add(logoutBtn);
    }

    /**
     * Retour Accueil
     */
    private void home() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Retourner à l'accueil?", "Accueil", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, YES_OPTION);
        if (n == YES_OPTION) {
            Home home = new Home();
            home.setVisible(true);
            dispose();
        }
    }

    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, YES_OPTION);
        if (n == YES_OPTION) {
            dispose();
        }
    }
}
