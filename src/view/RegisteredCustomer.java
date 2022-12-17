package view;

import connect.CompanyDB;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        registeredCustPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(37, 88, 167)));
        setContentPane(registeredCustPane);
        setLocationRelativeTo(null);
        registeredCustPane.setLayout(null);

        JLabel labelSelect = new JLabel();
        labelSelect.setBounds(100, 30, 600, 30);
        labelSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelSelect.setForeground(Color.BLACK);
        labelSelect.setText("Sélection du client");
        registeredCustPane.add(labelSelect);
        /*
        Liste des données client
         */
        // ajout des données au modèle
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        CompanyDB companyDB = new CompanyDB();
        companyDB.selectCompanyInfo();
        String company = companyDB.getCompanyName() + " " + companyDB.getCompanyAddress() +
                " " + companyDB.getCompanyCity();
        model.addElement(company);
        // création du panel liste
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        list.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2){
                    int index = list.locationToIndex(e.getPoint());
                    Object item = model.getElementAt(index);
                    String text = JOptionPane.showInputDialog("Rename item", item);
                    String newitem = "";
                    if(text != null) {
                        newitem = text.trim();
                    } else {
                        return;
                    }
                    if(!newitem.isEmpty()){
                        model.remove(index);
                        model.add(index, newitem);
                        ListSelectionModel selectionModel = list.getSelectionModel();
                        selectionModel.setLeadSelectionIndex(index);
                    }
                }
            }
        });
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.add(new JScrollPane(list));
        JButton removeall = new JButton("Remove All");
        JButton add = new JButton("Add");
        JButton rename = new JButton("Rename");
        JButton delete = new JButton("Delete");
        add.addActionListener(new ActionListener(){

            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = JOptionPane.showInputDialog("Add a new item");
                String item = null;
                if(text != null){
                    item = text.trim();
                } else {
                    return;
                }
                if (!item.isEmpty()){
                    model.addElement(item);
                }
            }
        });
        delete.addActionListener(new ActionListener(){

            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSelectionModel selectionModel = list.getSelectionModel();
                int index = selectionModel.getMinSelectionIndex();
                if (index >= 0){
                    model.remove(index);
                }
            }
        });
        removeall.addActionListener(new ActionListener(){

            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSelectionModel selectionModel = list.getSelectionModel();
                int index = selectionModel.getMinSelectionIndex();
                if (index == -1){
                    return;
                }
                Object item = model.getElementAt(index);
                String text = JOptionPane.showInputDialog("Rename item", item);
                String newitem = null;
                if(text != null){
                    newitem = text.trim();
                } else {
                    return;
                }
                if (!newitem.isEmpty()) {
                    model.remove(index);
                    model.add(index, newitem);
                }
            }
        });
        removeall.addActionListener(new ActionListener(){

            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
            }
        });
        rightPanel.add(add);
        rightPanel.add(rename);
        rightPanel.add(delete);
        rightPanel.add(removeall);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        listPanel.add(leftPanel);
        listPanel.add(rightPanel);
        registeredCustPane.add(listPanel);
        listPanel.setBounds(50, 100, 600, 500);
        listPanel.setVisible(true);

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
