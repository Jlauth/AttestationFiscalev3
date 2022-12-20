import connect.Connect;
import view.HomeFrame;
public class Main {
    public static void main(String[] args) {
        HomeFrame homeFrame = new HomeFrame();
        homeFrame.setVisible(true);
        new Connect();
    }
}