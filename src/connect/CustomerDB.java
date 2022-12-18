package connect;

import view.CreateCertificate;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CustomerDB extends Component {

    private Connection connect() {
        String url = "jdbc:sqlite:src/connect/arkadiapc.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void delete() {
        String sql = "ALTER TABLE client DROP COLUMN dateattest";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DefaultTableModel selectCustomerInfo() {
        String sql = "SELECT * FROM client";
        DefaultTableModel model = null;
        try (Connection connection = this.connect()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String[] columns = {"Titre", "Nom", "Prénom", "Adresse", "Ville", "Code postal"};
            int i = 0;
            String[][] data = new String[20][20];
            while (rs.next()) {
                String titre = rs.getString("titre");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String ville = rs.getString("ville");
                String codepostal = rs.getString("codepostal");
                data[i][0] = titre;
                data[i][1] = nom;
                data[i][2] = prenom;
                data[i][3] = adresse;
                data[i][4] = ville;
                data[i][5] = codepostal;
                i++;
            }
            model = new DefaultTableModel(data, columns);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }

    public void addCustomer(CreateCertificate createCertificate) {
        String sql = "INSERT INTO client(titre,nom,prenom,adresse,ville,codepostal,montantattest,anneefiscale,dateattest) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection connection = this.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, createCertificate.getCustomerTitleCmb());
            pstmt.setString(2, createCertificate.getCustomerNameTxt());
            pstmt.setString(3, createCertificate.getCustomerFirstNTxt());
            pstmt.setString(4, createCertificate.getCustomerAddressTxt());
            pstmt.setString(5, createCertificate.getCustomerCityTxt());
            pstmt.setString(6, createCertificate.getCustomerZipTxt());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String customerTitle;
    private String customerName;
    private String customerFirstN;
    private String customerAddress;
    private String customerCity;
    private String customerZip;

    public String getCustomerTitle() {
        return customerTitle;
    }

    public String setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
        return customerTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String setCustomerName(String customerName) {
        this.customerName = customerName;
        return customerName;
    }

    public String getCustomerFirstN() {
        return customerFirstN;
    }

    public String setCustomerFirstN(String customerFirstN) {
        this.customerFirstN = customerFirstN;
        return customerFirstN;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
        return customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public String setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
        return customerCity;
    }

    public String getCustomerZip() {
        return customerZip;
    }

    public String setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
        return customerZip;
    }
}
