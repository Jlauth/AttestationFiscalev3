package connect;

import view.Creer;

import java.sql.*;

public class ClientDB {

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

    public void insertClient(Creer creer) {
        String sql = "INSERT INTO client(titre,nom,prenom,adresse,ville,codepostal,montantattest,anneefiscale,dateattest) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection connection = this.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, creer.getCmbTitre());
            pstmt.setString(2, creer.getTxtNomClient());
            pstmt.setString(3, creer.getTxtPrenomClient());
            pstmt.setString(4, creer.getTxtAdresseClient());
            pstmt.setString(5, creer.getTxtVilleClient());
            pstmt.setString(6, creer.getTxtCPClient());
            pstmt.setInt(7, Integer.parseInt(creer.getTxtMontantAttest()));
            pstmt.setString(8, creer.getAnneeFiscaleFormat());
            pstmt.setString(9, creer.getDateAttestationFormat());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
