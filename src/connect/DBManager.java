package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton permettant de gérer la connexion à une base de données SQLite en utilisant JDBC.
 */
public class DBManager {

    /** Instance unique de la classe DBManager pour le pattern Singleton. */
    private static DBManager instance;

    /** Objet Connection qui représente la connexion à la base de données. */
    private Connection connection;

    /** URL de la base de données SQLite. */
    String url = "jdbc:sqlite:arkadiapc.db";

    /**
     * Constructeur de la classe DBManager.
     * Initialise la connexion à la base de données en utilisant l'URL par défaut.
     */
    public DBManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour récupérer l'instance unique de la classe DBManager (pattern Singleton).
     * Si l'instance n'existe pas encore, elle est créée.
     * @return l'instance unique de la classe DBManager
     */
    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Méthode pour établir une connexion à une base de données SQLite en utilisant l'URL passée en paramètre.
     * @param url l'URL de la base de données SQLite
     * @return l'objet Connection représentant la connexion à la base de données
     */
    public Connection getConnection(String url) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            System.out.println("Connexion à la base de données réussie.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
        return connection;
    }
}
