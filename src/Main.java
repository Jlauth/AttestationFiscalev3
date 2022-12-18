import connect.Connect;
import connect.CustomerDB;
import view.Home;

public class Main {

    public static void main(String[] args) {
        Home home = new Home();
        home.setVisible(true);
        new Connect();
        CustomerDB customerDB = new CustomerDB();
        //customerDB.delete();
    }
}