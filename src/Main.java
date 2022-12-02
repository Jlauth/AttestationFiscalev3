import connect.ClientDB;
import connect.Connect;
import view.Accueil;

public class Main {

    public static void main(String[] args) {

        Accueil accueil = new Accueil();
        accueil.setVisible(true);
        new Connect();
        ClientDB clientDB = new ClientDB();
        clientDB.selectAll();
    }
}