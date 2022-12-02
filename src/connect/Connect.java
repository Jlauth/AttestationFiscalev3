package connect;

import java.sql.*;

public class Connect {

    public Connection connection = null;

    public Connect() {
        try {
            String url = "jdbc:sqlite:src/connect/arkadiapc.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connecté à la base de données");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}





/*
   public void createNewDatabase(String fileName) {
          String url = "jdbc:sqlite:src/connect/arkadiapc.db" + fileName;
          try (Connection connewdb = DriverManager.getConnection(url)) {
              if (connewdb != null) {
                  DatabaseMetaData meta = connewdb.getMetaData();
                  System.out.println("Le driver utilisé est : " + meta.getDriverName());
                  System.out.println("Une nouvelle base de donnée vient d'être crée");
              }
          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
      }

      public void createNewTable() {
          String url = "jdbc:sqlite:src/connect/arkadiapc.db";
          String sql = "CREATE TABLE IF NOT EXISTS entreprise (" +
                  "id integer PRIMARY KEY NOT NULL," +
                  "titre text NOT NULL," +
                  "nom text NOT NULL," +
                  "prenom text NOT NULL," +
                  "adresse text NOT NULL," +
                  "ville text NOT NULL," +
                  "codepostal text NOT NULL," +
                  "agrement text NOT NULL)";

          try(Connection conntable = DriverManager.getConnection(url) ) {
              Statement stmt = conntable.createStatement();
              stmt.execute(sql);
          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
      }
 */
