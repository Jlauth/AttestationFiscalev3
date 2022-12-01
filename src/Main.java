import model.Attestation;
import view.Accueil;
import view.Creer;
import view.EditerEntreprise;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Creer creer = new Creer();
        EditerEntreprise editerEntreprise = new EditerEntreprise();
        new Attestation(creer, editerEntreprise);
        // true
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
}