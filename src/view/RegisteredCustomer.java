package view;

import connect.CustomerDB;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RegisteredCustomer extends JFrame {
    private static JButton logoutBtn;
    private static JButton homeBtn;
    private final Border lineBorderLogout = BorderFactory.createLineBorder(new Color(229, 83, 80));
    private final Border lineBorderHome = BorderFactory.createLineBorder(new Color(0, 138, 173));
    private final Insets insets = lineBorderLogout.getBorderInsets(logoutBtn);
    private final EmptyBorder emptyBorder = new EmptyBorder(insets);

    public RegisteredCustomer() {

        /*
          Editer client
         */
        JPanel registeredCustPane = new JPanel();
        setTitle("Client enregistré - Arkadia PC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 820);
        registeredCustPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3,
                new Color(37, 88, 167)));
        setContentPane(registeredCustPane);
        setLocationRelativeTo(null);
        registeredCustPane.setLayout(null);

        JLabel labelSelect = new JLabel();
        labelSelect.setBounds(180, 70, 600, 30);
        labelSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelSelect.setForeground(Color.BLACK);
        labelSelect.setText("Sélection du client");
        registeredCustPane.add(labelSelect);

        /*
        Liste des données client
         */
        // ajout des données au modèle
        CustomerDB customerDB = new CustomerDB();
        JTable table = new JTable(customerDB.selectCustomerInfo());
        table.setShowGrid(false);
        table.setShowVerticalLines(true);
        table.setRowHeight(16);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(300, 300, 20, 20);
        JPanel custPanel = new JPanel();
        custPanel.add(pane);
        custPanel.setSize(500, 500);
        custPanel.setBounds(50, 110, 500, 500);
        registeredCustPane.add(custPanel);

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
        registeredCustPane.add(homeBtn);

        /*
          Bouton Logout
         */
        ImageIcon logoutIcon = new ImageIcon("src/media/images/logoutbis.png");
        Image newLogoutImg = logoutIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoutIcon.setImage(newLogoutImg);
        // logoutBtn config
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
        logoutBtn.addActionListener(e -> close());
        registeredCustPane.add(logoutBtn);
    }

    /**
     * Retour Accueil
     */
    private void home() {
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
    public void close() {
        int n = JOptionPane.showOptionDialog(new JFrame(), "Fermer application?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Oui", "Non"}, JOptionPane.YES_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

}
