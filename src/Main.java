import connect.CustomerDb;
import connect.Connect;
import view.Home;

public class Main {

    public static void main(String[] args) {

        Home home = new Home();
        home.setVisible(true);
        new Connect();
        CustomerDb customerDb = new CustomerDb();
        customerDb.selectAll();
    }
}