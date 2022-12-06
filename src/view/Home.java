package view;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    public Home() {

         /*
          Création Home
         */
        JPanel homePane = new JPanel();
        setTitle("Home - Gestion des attestations fiscales Arkadia PC");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        homePane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(homePane);
        setLocationRelativeTo(null);
        homePane.setLayout(null);

        JLabel homeLbl = new JLabel();
        homeLbl.setBounds(30, 30, 600, 30);
        homeLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
        homeLbl.setText("Bienvenue dans votre gestionnaire d'attestations fiscales");
        homeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        homePane.add(homeLbl);

        JTextArea homeNewAttestLbl = new JTextArea();
        homeNewAttestLbl.setBounds(50, 130, 600, 50);
        homeNewAttestLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        homeNewAttestLbl.setText("A partir de cette page d'accueil,  vous avez accès à la création d'une nouvelle attestation");
        homeNewAttestLbl.setLineWrap(true);
        homePane.add(homeNewAttestLbl);

        JTextArea editHomeLbl = new JTextArea();
        editHomeLbl.setBounds(50, 300, 600, 30);
        editHomeLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        editHomeLbl.setText("Ainsi qu'à l'édition d'un de vos clients ou de vos données d'entreprise");
        homePane.add(editHomeLbl);

        /*
          Bouton nouvelle attestation
         */
        JButton certificateBtn = new JButton("Créer attestation");
        certificateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        certificateBtn.setBounds(100, 180, 180, 50);
        certificateBtn.setForeground(new Color(37, 88, 167));
        certificateBtn.setHorizontalAlignment(SwingConstants.CENTER);
        homePane.add(certificateBtn);
        certificateBtn.addActionListener(e -> {
            CreateCertificate createCertificate = new CreateCertificate();
            createCertificate.setVisible(true);
            dispose();
        });

        /*
          Bouton éditer client
         */
        JButton editCustomerBtn = new JButton("Editer client");
        editCustomerBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        editCustomerBtn.setBounds(100, 350, 180, 50);
        editCustomerBtn.setForeground(new Color(37, 88, 167));
        homePane.add(editCustomerBtn);
        editCustomerBtn.addActionListener(e -> {
            EditCustomer editCustomer = new EditCustomer();
            editCustomer.setVisible(true);
            dispose();
        });

         /*
          Bouton éditer entreprise
         */
        JButton editCompanyBtn = new JButton("Editer entreprise");
        editCompanyBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        editCompanyBtn.setBounds(350, 350, 180, 50);
        editCompanyBtn.setForeground(new Color(37, 88, 167));
        homePane.add(editCompanyBtn);
        editCompanyBtn.addActionListener(e -> {
            EditCompany editCompany = new EditCompany();
            editCompany.setVisible(true);
            dispose();
        });

         /*
          Btn Quitter
         */
        ImageIcon logoutIcon = new ImageIcon("src/media/images/logout.png");
        Image logoutImg = logoutIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH );
        logoutIcon.setImage(logoutImg);

        JButton logoutBtn = new JButton(logoutIcon);
        logoutBtn.setBounds(600, 490, 50, 50);

        logoutBtn.setToolTipText("Quitter");
        homePane.add(logoutBtn);
        logoutBtn.addActionListener(e -> close());
    }

    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}



