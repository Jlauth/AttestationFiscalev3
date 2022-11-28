import model.Attestation;
import view.Accueil;
import view.Creer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Creer creer = new Creer();
        new Attestation(creer);
        // true
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
}