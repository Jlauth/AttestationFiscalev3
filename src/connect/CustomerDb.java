package connect;

import view.CreateCertificate;

import java.sql.*;

public class CustomerDb {

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

    public void selectAll() {
        String sql = "SELECT id, titre, nom, prenom, adresse, ville, codepostal, agrement FROM entreprise";
        try (Connection connection = this.connect()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") +
                        rs.getString("titre") +
                        rs.getString("nom") +
                        rs.getString("prenom") +
                        rs.getString("adresse") +
                        rs.getString("ville") +
                        rs.getString("codepostal") +
                        rs.getString("agrement"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertClient(CreateCertificate createCertificate) {
        String sql = "INSERT INTO client(titre,nom,prenom,adresse,ville,codepostal,montantattest,anneefiscale,dateattest) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection connection = this.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, createCertificate.getCustomerTitleCmb());
            pstmt.setString(2, createCertificate.getCustomerNameTxt());
            pstmt.setString(3, createCertificate.getCustomerFirstnameTxt());
            pstmt.setString(4, createCertificate.getCustomerAddressTxt());
            pstmt.setString(5, createCertificate.getCustomerCityTxt());
            pstmt.setString(6, createCertificate.getCustomerZipTxt());
            pstmt.setInt(7, Integer.parseInt(createCertificate.getCertificateAmountTxt()));
            pstmt.setString(8, createCertificate.getFiscalYearTxt());
            pstmt.setString(9, createCertificate.getDateAttestationFormat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
