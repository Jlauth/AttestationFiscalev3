package view;

import javax.swing.*;
import java.awt.*;

public class CustomerDataManagement extends JFrame {

    private final JPanel customerDataManagementPane;
    private JList list;
    private String editCustomerCmb;

    public JPanel getCustomerDataManagementPane() {
        return customerDataManagementPane;
    }

    public String getList() {
        return list.toString();
    }

    public void setList(JList list){
        this.list = list;
    }

    public String getEditCustomerCmb() {
        return editCustomerCmb;
    }

    public void setEditCustomerCmb(String editCustomerCmb) {
        this.editCustomerCmb = editCustomerCmb;
    }



    public CustomerDataManagement(){

         /*
          Gestion données client
         */
        customerDataManagementPane = new JPanel();
        setTitle("Gestion des données client - Arkadia PC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 720);
        customerDataManagementPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(customerDataManagementPane);
        setLocationRelativeTo(null);
        customerDataManagementPane.setLayout(null);

        JLabel customerDataLbl = new JLabel();
        customerDataLbl.setBounds(30, 30, 600, 30);
        customerDataLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
        customerDataLbl.setText("Import des données client");
        customerDataLbl.setHorizontalAlignment(SwingConstants.CENTER);
        customerDataManagementPane.add(customerDataLbl);

        /*
         Création de la liste
         */
        JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        /*
         Cmb
         */
        JComboBox<Object> editCustomerCmb = new JComboBox<>();
        editCustomerCmb.setModel(new DefaultComboBoxModel<>());
        editCustomerCmb.setPreferredSize(new Dimension(250, 80));
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
        customerDataManagementPane.add(homeBtn);
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
        customerDataManagementPane.add(logoutBtn);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.addActionListener(e -> close());
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
