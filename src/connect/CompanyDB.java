package connect;

import view.EditCompany;

import java.awt.*;
import java.sql.*;

import static java.lang.System.out;

public class CompanyDB extends Component {

    private static final String url = "jdbc:sqlite:src/connect/arkadiapc.db";


    private Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        return connection;
    }

    public void createNewCompany() {
        String sql = "CREATE TABLE IF NOT EXISTS entreprise(" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "titre TEXT NOT NULL," +
                "nom TEXT NOT NULL," +
                "prenom TEXT NOT NULL," +
                "adresse TEXT NOT NULL," +
                "ville TEXT NOT NULL," +
                "codepostal TEXT NOT NULL," +
                "agrement TEXT NOT NULL," +
                "nomentreprise TEXT NOT NULL," +
                "tel TEXT NOT NULL," +
                "mail TEXT NOT NULL);";
        try (Connection connection = DriverManager.getConnection(url); Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    public void addCompany(EditCompany editCompany) {
        String sql = "INSERT INTO entreprise(titre, nom, prenom, adresse, ville, codepostal, agrement, nomentreprise, tel, mail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = this.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, editCompany.getHolderTitleCmb());
            pstmt.setString(2, editCompany.getHolderNameTxt());
            pstmt.setString(3, editCompany.getHolderFirstnameTxt());
            pstmt.setString(4, editCompany.getCompanyAddressTxt());
            pstmt.setString(5, editCompany.getCompanyCityTxt());
            pstmt.setString(6, editCompany.getCompanyZipTxt());
            pstmt.setString(7, editCompany.getCompanyApprovalTxt());
            pstmt.setString(8, editCompany.getCompanyNameTxt());
            pstmt.setString(9, editCompany.getCompanyPhoneTxt());
            pstmt.setString(10, editCompany.getCompanyMailTxt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    public void update(int id, String titre, String nom, String prenom, String adresse, String ville, String codepostal, String agrement, String nomentreprise, String tel, String mail) {
        String sql = "UPDATE entreprise SET titre = ?," +
                "nom = ?," +
                "prenom = ?," +
                "adresse = ?," +
                "ville = ?," +
                "codepostal = ?," +
                "agrement = ?," +
                "nomentreprise = ?," +
                "tel = ?," +
                "mail = ?" +
                "WHERE id = ?";
        try (Connection connection = this.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, holderTitle);
            pstmt.setString(2, holderName);
            pstmt.setString(3, holderFirstN);
            pstmt.setString(4, companyAddress);
            pstmt.setString(5, companyCity);
            pstmt.setString(6, companyZip);
            pstmt.setString(7, companyApproval);
            pstmt.setString(8, companyName);
            pstmt.setString(9, companyPhone);
            pstmt.setString(10, companyMail);
            pstmt.setInt(11, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    public void selectCompanyInfo() {
        String query = "SELECT titre, nom, prenom, adresse, ville, codepostal, agrement, nomentreprise, tel, mail FROM entreprise";
        try (Statement stmt = connect().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                holderTitle = rs.getString("titre");
                holderName = rs.getString("nom");
                holderFirstN = rs.getString("prenom");
                companyAddress = rs.getString("adresse");
                companyCity = rs.getString("ville");
                companyZip = rs.getString("codepostal");
                companyApproval = rs.getString("agrement");
                companyName = rs.getString("nomentreprise");
                companyPhone = rs.getString("tel");
                companyMail = rs.getString("mail");
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM entreprise WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String companyAddress;
    private String holderTitle;
    private String holderName;
    private String holderFirstN;
    private String companyCity;
    private String companyZip;
    private String companyApproval;
    private String companyName;
    private String companyPhone;
    private String companyMail;

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getHolderTitle() {
        return holderTitle;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getHolderFirstN() {
        return holderFirstN;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public String getCompanyZip() {
        return companyZip;
    }

    public String getCompanyApproval() {
        return companyApproval;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public String setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
        return companyAddress;
    }

    public String setHolderTitle(String holderTitle) {
        this.holderTitle = holderTitle;
        return holderTitle;
    }

    public String setHolderName(String holderName) {
        this.holderName = holderName;
        return holderName;
    }

    public String setHolderFirstN(String holderFirstN) {
        this.holderFirstN = holderFirstN;
        return holderFirstN;
    }

    public String setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
        return companyCity;
    }

    public String setCompanyZip(String companyZip) {
        this.companyZip = companyZip;
        return companyZip;
    }

    public String setCompanyApproval(String companyApproval) {
        this.companyApproval = companyApproval;
        return companyApproval;
    }

    public String setCompanyName(String companyName) {
        this.companyName = companyName;
        return companyName;
    }

    public String setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
        return companyPhone;
    }

    public String setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
        return companyMail;
    }
}