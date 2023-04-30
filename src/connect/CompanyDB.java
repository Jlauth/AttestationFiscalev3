package connect;

import java.awt.*;
import java.sql.*;

import static java.lang.System.out;

/**
 * Classe CompanyDB permettant la connexion à la base de données de l'entreprise
 * Fournit des méthodes pour mettre à jour et récupérer les informations de l'entreprise.
 */
public class CompanyDB extends Component {


    /** L'adresse de l'entreprise. */
    private String companyAddress;

    /** Le titre du détenteur de l'entreprise. */
    private String holderTitle;

    /** Le nom du détenteur de l'entreprise. */
    private String holderName;

    /** Le prénom du détenteur de l'entreprise. */
    private String holderFirstN;

    /** La ville de l'entreprise. */
    private String companyCity;

    /** Le code postal de l'entreprise. */
    private String companyZip;

    /** Le numéro d'agrément de l'entreprise. */
    private String companyApproval;

    /** Le nom de l'entreprise. */
    private String companyName;

    /** Le numéro de téléphone de l'entreprise. */
    private String companyPhone;

    /** L'adresse e-mail de l'entreprise. */
    private String companyMail;

    /**
     * Retourne le titre du détenteur de l'entreprise
     * @return la chaîne de caractères représentant le titre du détenteur de l'entreprise
     */
    public String getHolderTitle() {
        return holderTitle;
    }

    /**
     * Modifie le titre du détenteur de l'entreprise et retourne le nouveau titre
     * @param holderTitle le nouveau titre du détenteur de l'entreprise
     * @return le nouveau titre du détenteur de l'entreprise modifié
     */
    public String setHolderTitle(String holderTitle) {
        this.holderTitle = holderTitle;
        return holderTitle;
    }

    /**
     * Retourne le nom du détenteur de l'entreprise
     * @return la chaîne de caractères représentant le nom du détenteur de l'entreprise
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Modifie le nom du détenteur de l'entreprise et retourne le nouveau nom
     * @param holderName le nouveau nom du détenteur de l'entreprise
     * @return le nouveau nom du détenteur de l'entreprise modifié
     */
    public String setHolderName(String holderName) {
        this.holderName = holderName;
        return holderName;
    }

    /**
     * Retourne le prénom du détenteur de l'entreprise
     * @return la chaîne de caractères représentant le prénom du détenteur de l'entreprise
     */
    public String getHolderFirstN() {
        return holderFirstN;
    }

    /**
     * Modifie le prénom du détenteur de l'entreprise et retourne le nouveau prénom
     * @param holderFirstN le nouveau prénom du détenteur de l'entreprise
     * @return le nouveau prénom du détenteur de l'entreprise modifié
     */
    public String setHolderFirstN(String holderFirstN) {
        this.holderFirstN = holderFirstN;
        return holderFirstN;
    }

    /**
     * Retourne la ville de l'entreprise
     * @return la chaîne de caractères représentant la ville de l'entreprise
     */
    public String getCompanyCity() {
        return companyCity;
    }

    /**
     * Modifie la ville de l'entreprise et retourne la nouvelle ville
     * @param companyCity la nouvelle ville de l'entreprise
     * @return la nouvelle ville modifiée
     */
    public String setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
        return companyCity;
    }

    /**
     * Retourne le code postal de l'entreprise
     * @return la chaîne de caractères représentant le code postal de l'entreprise
     */
    public String getCompanyZip() {
        return companyZip;
    }

    /**
     * Modifie le code postal de l'entreprise et retourne le nouveau code postal
     * @param companyZip le nouveau code postal de l'entreprise
     * @return le nouveau code postal modifié
     */
    public String setCompanyZip(String companyZip) {
        this.companyZip = companyZip;
        return companyZip;
    }

    /**
     * Retourne le numéro d'agrément de l'entreprise
     * @return la chaîne de caractères représentant le numéro d'agrément de l'entreprise
     */
    public String getCompanyApproval() {
        return companyApproval;
    }

    /**
     * Modifie le numéro d'agrément de l'entreprise et retourne la nouvelle approbation
     * @param companyApproval le nouveau numéro d'agrément de l'entreprise
     * @return le nouveau numéro d'agrément modifié
     */
    public String setCompanyApproval(String companyApproval) {
        this.companyApproval = companyApproval;
        return companyApproval;
    }

    /**
     * Retourne le nom de l'entreprise
     * @return la chaîne de caractères représentant le nom de l'entreprise
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Modifie le nom de l'entreprise et retourne le nouveau nom
     * @param companyName le nouveau nom de l'entreprise
     * @return le nouveau nom de l'entreprise modifié
     */
    public String setCompanyName(String companyName) {
        this.companyName = companyName;
        return companyName;
    }

    /**
     * Retourne l'adresse de l'entreprise
     * @return la chaîne de caractères représentant l'adresse de l'entreprise
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * Modifie l'adresse de l'entreprise et retourne la nouvelle adresse
     * @param companyAddress la nouvelle adresse de l'entreprise
     * @return la nouvelle adresse modifiée
     */
    public String setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
        return companyAddress;
    }

    /**
     * Retourne le numéro de téléphone de l'entreprise
     * @return la chaîne de caractères représentant le numéro de téléphone de l'entreprise
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * Modifie le numéro de téléphone de l'entreprise et retourne le nouveau numéro de téléphone
     * @param companyPhone le nouveau numéro de téléphone de l'entreprise
     * @return le nouveau numéro de téléphone modifié
     */
    public String setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
        return companyPhone;
    }

    /**
     * Retourne l'adresse email de l'entreprise
     * @return la chaîne de caractères représentant l'adresse email de l'entreprise
     */
    public String getCompanyMail() {
        return companyMail;
    }

    /**
     * Modifie l'adresse email de l'entreprise et retourne la nouvelle adresse email
     * @param companyMail la nouvelle adresse email de l'entreprise
     * @return la nouvelle adresse email modifiée
     */
    public String setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
        return companyMail;
    }

    /**
     * Constructeur par défaut de la classe CompanyDB.
     * Initialise les variables de la classe.
     */
    public CompanyDB() {
        companyAddress = "";
        holderTitle = "";
        holderName = "";
        holderFirstN = "";
        companyCity = "";
        companyZip = "";
        companyApproval = "";
        companyName = "";
        companyPhone = "";
        companyMail = "";
    }

    /**
     * Met à jour les informations d'une entreprise dans la base de données.
     * @param id            l'identifiant de l'entreprise à mettre à jour
     * @param titre         le titre de la personne de contact de l'entreprise
     * @param nom           le nom de la personne de contact de l'entreprise
     * @param prenom        le prénom de la personne de contact de l'entreprise
     * @param adresse       l'adresse de l'entreprise
     * @param ville         la ville de l'entreprise
     * @param codepostal    le code postal de l'entreprise
     * @param agrement      le numéro d'agrément de l'entreprise
     * @param nomentreprise le nom de l'entreprise
     * @param tel           le numéro de téléphone de l'entreprise
     * @return true si la mise à jour s'est effectuée avec succès, false sinon
     */
    public boolean update(int id, String titre, String nom, String prenom, String adresse, String ville, String codepostal, String agrement,
                       String nomentreprise, String tel, String mail) {
        // Création de la requête SQL avec les champs à mettre à jour
        String sql = "UPDATE entreprise SET titre = ?, nom = ?, prenom = ?, adresse = ?, ville = ?, codepostal = ?, agrement = ?, " +
                "nomentreprise = ?, tel = ?, mail = ? WHERE id = ?";
        try {
            // Obtention de l'instance unique du gestionnaire de base de données
            DBManager dbManager = connect.DBManager.getInstance();
            // Obtention de la connexion à la base de données
            Connection connection = dbManager.getConnection("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\connect\\db\\arkadiapc.db");
            // Préparation de la requête SQL
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // Attribution des valeurs à mettre à jour aux paramètres de la requête SQL
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
            // Exécution de la requête SQL
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
        return  false;
    }

    /**
     * Sélectionne toutes les informations de l'entreprise à partir de la base de données
     * Les informations récupérées sont stockées dans les variables correspondantes
     */
    public void selectCompanyInfo() {
        try (Statement stmt = connect.DBManager.getInstance().getConnection("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\connect\\db\\arkadiapc.db").createStatement()) {
            String query = "SELECT titre, nom, prenom, adresse, ville, codepostal, agrement, nomentreprise, tel, mail FROM entreprise";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // Récupération des données de l'entreprise depuis le ResultSet
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
                // Affichage des données de l'entreprise dans la console
                System.out.println(holderTitle + " " + holderName + " " + holderName + " " + companyAddress + " " + companyCity + " " + companyZip +
                        " " + companyApproval + " " + companyName + " " + companyPhone + " " + companyMail);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }
}
