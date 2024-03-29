package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Classe HomeFrame représentant la fenêtre principale de l'application.
 * Permet à l'utilisateur de naviguer entre les différentes fonctionnalités de l'application.
 */
public class HomeFrame extends JFrame {

    /** Déclaration du bouton de déconnexion. */
    private static JButton logoutBtn;

    /** Création des bordures pour le bouton de déconnexion. */
    private final Border lineBorder = BorderFactory.createLineBorder(new Color(229, 83, 80));

    /** Récupération des insets (marges intérieures) de la bordure de déconnexion pour créer une EmptyBorder. */
    private final Insets insets = lineBorder.getBorderInsets(logoutBtn);

    /** Création d'une EmptyBorder avec les insets récupérées pour ajouter des marges vides autour du bouton de déconnexion. */
    private final EmptyBorder emptyBorder = new EmptyBorder(insets);

    /**
     * Constructeur de la classe HomeFrame.
     * Crée une fenêtre d'accueil avec l'accès à la création d'une attestation fiscale
     * et l'édition des informations de l'entreprise.
     */
    public HomeFrame() {

        /*
         * Crée une nouvelle fenêtre d'accueil pour la gestion des attestations fiscales.
         */
        JPanel homePane = new JPanel();
        setTitle("Accueil - Gestion des attestations fiscales Arkadia PC");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        homePane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(homePane);
        setLocationRelativeTo(null);
        homePane.setLayout(null);

        JLabel homeLbl = new JLabel();
        homeLbl.setBounds(0, 30, 600, 30);
        homeLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
        homeLbl.setText("Bienvenue dans votre gestionnaire d'attestations fiscales");
        homeLbl.setForeground(Color.BLACK);
        homeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        homePane.add(homeLbl);

        /*
         * Bouton nouvelle attestation
         */
        JButton certificateBtn = new JButton("Nouvelle attestation");
        certificateBtn.setBounds(140, 120, 300, 60);
        certificateBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        certificateBtn.setForeground(new Color(37, 88, 167));
        certificateBtn.setToolTipText("Création d'une nouvelle attestation fiscale");
        certificateBtn.addActionListener(e -> {
            CertificateFrame certificateFrame = new CertificateFrame();
            certificateFrame.setVisible(true);
            dispose();
        });
        certificateBtn.setVisible(true);
        homePane.add(certificateBtn);

        /*
         * Bouton éditer entreprise
         */
        JButton editCompanyBtn = new JButton("Editer infos entreprise");
        editCompanyBtn.setBounds(140, 190, 300, 60);
        editCompanyBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        editCompanyBtn.setForeground(new Color(37, 88, 167));
        editCompanyBtn.setToolTipText("Edition des informations de votre entreprise");
        editCompanyBtn.addActionListener(e -> {
            CompanyFrame companyFrame = new CompanyFrame();
            companyFrame.setVisible(true);
            dispose();
        });
        editCompanyBtn.setVisible(true);
        homePane.add(editCompanyBtn);

        /*
         * Bouton quitter : Crée un bouton quitter avec une icône
         */
        // Création de l'icône pour le bouton quitter
        ImageIcon logoutIcon = new ImageIcon("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\logoutbis.png");
        Image logoutImg = logoutIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoutIcon.setImage(logoutImg);
        // Création du bouton Quitter
        logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(510, 290, 60, 60);
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
                    logoutBtn.setBorder(lineBorder);
                } else {
                    logoutBtn.setBorder(emptyBorder);
                }
            }
        });
        homePane.add(logoutBtn);
    }

    /**
     * Ferme l'application après confirmation de l'utilisateur.
     */
    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}



