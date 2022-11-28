import model.Attestation;
import view.Accueil;
import view.Creer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Creer creer = new Creer();
        new Attestation(creer);

        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }
}