import view.HomeFrame;

/**
 * Classe Main représentant le point d'entrée de l'application.
 */
public class Main {
    /**
     * Méthode main qui crée la fenêtre principale de l'application et initialise la base de données.
     * @param args les arguments de ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {
        HomeFrame homeFrame = new HomeFrame();
        homeFrame.setVisible(true);
    }
}