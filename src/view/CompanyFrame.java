package view;

import connect.CompanyDB;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Objects;

import static javax.swing.JOptionPane.YES_OPTION;

/**
 * Classe CompanyFrame représentant la fenêtre d'édition des données de l'entreprise.
 */
public class CompanyFrame extends JFrame {

    /** Déclaration du bouton de déconnexion. */
    private static JButton logoutBtn;

    /** Déclaration du bouton de retour à l'accueil. */
    private static JButton homeBtn;

    /** Création des bordures pour le bouton de déconnexion. */
    private final Border lineBorderLogout = BorderFactory.createLineBorder(new Color(229, 83, 80));

    /** Création des bordures pour le bouton de retour à l'accueil. */
    private final Border lineBorderHome = BorderFactory.createLineBorder(new Color(0, 138, 173));

    /** Récupération des insets (marges intérieures) de la bordure de déconnexion pour créer une EmptyBorder. */
    private final Insets insets = lineBorderLogout.getBorderInsets(logoutBtn);

    /** Création d'une EmptyBorder avec les insets récupérées pour ajouter des marges vides autour du bouton de déconnexion. */
    private final EmptyBorder emptyBorder = new EmptyBorder(insets);

    /** Champ texte pour le numéro d'agrément de l'entreprise. */
    private final JTextField companyApprovalTxt;

    /** Champ texte pour le nom de l'entreprise. */
    private final JTextField companyNameTxt;

    /** Menu déroulant pour le titre du gérant de l'entreprise. */
    private final JComboBox<String> holderTitleCmb;

    /** Champ texte pour le nom de famille du gérant de l'entreprise. */
    private final JTextField holderNameTxt;

    /** Champ texte pour le prénom du gérant de l'entreprise. */
    private final JTextField holderFirstnameTxt;

    /** Champ texte pour l'adresse de l'entreprise. */
    private final JTextField companyAddressTxt;

    /** Champ texte pour la ville de l'entreprise. */
    private final JTextField companyCityTxt;

    /** Champ texte pour le code postal de l'entreprise. */
    private final JTextField companyZipTxt;

    /** Champ texte pour le numéro de téléphone de l'entreprise. */
    private final JTextField companyPhoneTxt;

    /** Champ texte pour l'adresse email de l'entreprise. */
    private final JTextField companyMailTxt;

    /**
     * Constructeur de la classe CompanyFrame.
     * Crée une fenêtre permettant d'éditer les données de l'entreprise.
     * Les champs de saisie et les listes déroulantes sont préremplis avec les informations de l'entreprise récupérées depuis la base de données.
     */
    public CompanyFrame() {
        // Crée une instance de la classe CompanyDB pour interagir avec une base de données contenant des informations sur l'entreprise.
        CompanyDB companyDB = new CompanyDB();
        // La méthode selectCompanyInfo() est appelée pour récupérer les informations de l'entreprise à partir de la base de données.
        companyDB.selectCompanyInfo();

        /*
         * Crée une fenêtre permettant d'éditer les données de l'entreprise.
         * La fenêtre comporte un titre, une bordure, une taille et une position spécifiés.
         * Elle affiche également une étiquette de texte indiquant le but de la fenêtre.
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
        companyLbl.setForeground(Color.BLACK);
        companyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        editCompanyPane.add(companyLbl);

        /*
         * Titre du gérant
         */
        JLabel holderTitleLbl = new JLabel("Titre");
        holderTitleLbl.setBounds(50, 250, 120, 14);
        editCompanyPane.add(holderTitleLbl);

        holderTitleCmb = new JComboBox<>();
        holderTitleCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Madame", "Mademoiselle", "Monsieur", "Aucun titre"}));
        holderTitleCmb.setBounds(50, 275, 150, 25);
        holderTitleCmb.setSelectedItem(companyDB.getHolderTitle());
        editCompanyPane.add(holderTitleCmb);

        /*
         * Nom gérant
         */
        JLabel holderNameLbl = new JLabel("Nom du gérant");
        holderNameLbl.setBounds(230, 250, 120, 14);
        editCompanyPane.add(holderNameLbl);

        holderNameTxt = new JTextField();
        holderNameTxt.setBounds(230, 275, 150, 25);
        holderNameTxt.setText(companyDB.getHolderName());
        editCompanyPane.add(holderNameTxt);

        /*
         * Prénom du gérant
         */
        JLabel holderFirstnameLbl = new JLabel("Prénom du gérant");
        holderFirstnameLbl.setBounds(410, 250, 120, 14);
        editCompanyPane.add(holderFirstnameLbl);

        holderFirstnameTxt = new JTextField();
        holderFirstnameTxt.setBounds(410, 275, 150, 25);
        holderFirstnameTxt.setText(companyDB.getHolderFirstN());
        editCompanyPane.add(holderFirstnameTxt);

        /*
         * Adresse de l'entreprise.
         */
        JLabel companyAddressLbl = new JLabel("Adresse");
        companyAddressLbl.setBounds(50, 330, 120, 14);
        editCompanyPane.add(companyAddressLbl);

        companyAddressTxt = new JTextField();
        companyAddressTxt.setBounds(50, 355, 510, 25);
        companyAddressTxt.setText(companyDB.getCompanyAddress());
        editCompanyPane.add(companyAddressTxt);

        /*
         * Ville de l'entreprise
         */
        JLabel companyCityLbl = new JLabel("Ville");
        companyCityLbl.setBounds(50, 400, 120, 14);
        editCompanyPane.add(companyCityLbl);

        companyCityTxt = new JTextField();
        companyCityTxt.setBounds(50, 425, 330, 25);
        companyCityTxt.setText(companyDB.getCompanyCity());
        editCompanyPane.add(companyCityTxt);

        /*
         * Code postal de l'entreprise
         */
        JLabel companyZipLbl = new JLabel("Code Postal");
        companyZipLbl.setBounds(410, 400, 120, 14);
        editCompanyPane.add(companyZipLbl);

        companyZipTxt = new JTextField();
        companyZipTxt.setBounds(410, 425, 150, 25);
        companyZipTxt.setText(companyDB.getCompanyZip());
        editCompanyPane.add(companyZipTxt);

        /*
         * Numéro d'agrément
         */
        JLabel companyApprovalLbl = new JLabel("Numéro d'agrément");
        companyApprovalLbl.setBounds(50, 480, 120, 14);
        editCompanyPane.add(companyApprovalLbl);

        companyApprovalTxt = new JTextField();
        companyApprovalTxt.setBounds(50, 500, 150, 25);
        companyApprovalTxt.setText(companyDB.getCompanyApproval());
        editCompanyPane.add(companyApprovalTxt);

        /*
         * Nom de l'entreprise
         */
        JLabel companyNameLbl = new JLabel("Nom entreprise");
        companyNameLbl.setBounds(230, 480, 120, 14);
        editCompanyPane.add(companyNameLbl);

        companyNameTxt = new JTextField();
        companyNameTxt.setBounds(230, 500, 150, 25);
        companyNameTxt.setText(companyDB.getCompanyName());
        editCompanyPane.add(companyNameTxt);

        /*
         * Numéro de téléphone de l'entreprise
         */
        JLabel companyTelLbl = new JLabel("Téléphone");
        companyTelLbl.setBounds(50, 540, 120, 14);
        editCompanyPane.add(companyTelLbl);

        companyPhoneTxt = new JTextField();
        companyPhoneTxt.setBounds(50, 565, 150, 25);
        companyPhoneTxt.setText(companyDB.getCompanyPhone());
        editCompanyPane.add(companyPhoneTxt);

        /*
         * EMail de l'entreprise
         */
        JLabel companyMailLbl = new JLabel("Mail");
        companyMailLbl.setBounds(230, 540, 120, 14);
        editCompanyPane.add(companyMailLbl);

        companyMailTxt = new JTextField();
        companyMailTxt.setBounds(230, 565, 150, 25);
        companyMailTxt.setText(companyDB.getCompanyMail());
        editCompanyPane.add(companyMailTxt);

        /*
        Crée un bouton "Enregistrer" pour enregistrer les nouvelles informations de l'entreprise.
        Le bouton est ajouté à l'interface graphique et un listener d'action est attaché pour déclencher une boîte de dialogue demandant
        à l'utilisateur s'il souhaite enregistrer les modifications. Si l'utilisateur clique sur "Oui", les modifications sont enregistrées
        dans la base de données en utilisant la classe companyDB.
        */
        JButton companySaveBtn = new JButton("Enregistrer");
        companySaveBtn.setToolTipText("Enregistre les nouvelles informations entreprise");
        companySaveBtn.setForeground(Color.BLACK);
        companySaveBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        companySaveBtn.setBounds(50, 650, 200, 50);
        editCompanyPane.add(companySaveBtn);
        companySaveBtn.addActionListener(e -> {
            companyDB.selectCompanyInfo();
                    int n = JOptionPane.showOptionDialog(new JFrame(), "Enregistrer les modifications?", "Modifier",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, YES_OPTION);
                    if (n == YES_OPTION) {
                        boolean success = companyDB.update(1, companyDB.setHolderTitle(Objects.requireNonNull(holderTitleCmb.getSelectedItem()).toString()),
                                companyDB.setHolderName(holderNameTxt.getText()),
                                companyDB.setHolderFirstN(holderFirstnameTxt.getText()),
                                companyDB.setCompanyAddress(companyAddressTxt.getText()),
                                companyDB.setCompanyCity(companyCityTxt.getText()),
                                companyDB.setCompanyZip(companyZipTxt.getText()),
                                companyDB.setCompanyApproval(companyApprovalTxt.getText()),
                                companyDB.setCompanyName(companyNameTxt.getText()),
                                companyDB.setCompanyPhone(companyPhoneTxt.getText()),
                                companyDB.setCompanyMail(companyMailTxt.getText()));
                        if (success) {
                            JOptionPane.showMessageDialog(new JFrame(), "Les modifications ont été enregistrées avec succès.");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Une erreur est survenue lors de l'enregistrement des modifications.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
        });

        /*
         * Bouton accueil : Crée un bouton d'accueil avec une icône
         */
        // création de l'icône home
        ImageIcon homeIcon = new ImageIcon("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\home.png");
        Image newHomeImg = homeIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        homeIcon.setImage(newHomeImg);

        // création du bouton d'accueil
        homeBtn = new JButton(homeIcon);
        homeBtn.setBounds(650, 10, 60, 60);
        homeBtn.setToolTipText("Page d'accueil");
        homeBtn.setBorder(emptyBorder);
        homeBtn.setFocusPainted(false);
        homeBtn.setOpaque(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setToolTipText("Accueil");
        // changement visuel lorsqu'on survole le bouton
        homeBtn.getModel().addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
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
         * Bouton quitter : Crée un bouton quitter avec une icône
         */
        // Création de l'icône pour le bouton quitter
        ImageIcon logoutIcon = new ImageIcon("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\logoutbis.png");
        Image logoutImg = logoutIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoutIcon.setImage(logoutImg);
        // Création du bouton Quitter
        logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(650, 700, 60, 60);
        logoutBtn.setBorder(emptyBorder);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setToolTipText("Quitter");
        // Action pour fermer l'application
        logoutBtn.addActionListener(e -> close());
        // Action pour changer le visuel du bouton lorsqu'il est survolé
        logoutBtn.getModel().addChangeListener(new ChangeListener() {
            /**
             * Invoked when the target of the listener has changed its state.
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
     * Permet de retourner à la page d'accueil après confirmation de l'utilisateur.
     */
    private void home() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Retourner à l'accueil?", "Accueil", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, YES_OPTION);
        if (n == YES_OPTION) {
            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setVisible(true);
            dispose();
        }
    }

    /**
     * Ferme l'application après confirmation de l'utilisateur.
     */
    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, YES_OPTION);
        if (n == YES_OPTION) {
            dispose();
        }
    }
}
