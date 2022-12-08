import connect.CustomerDB;
import connect.Connect;
import view.EditCompany;
import view.Home;

public class Main {

    public static void main(String[] args) {

        Home home = new Home();
        EditCompany editCompany = new EditCompany();
        home.setVisible(true);
        new Connect();
        CustomerDB customerDb = new CustomerDB();
        customerDb.selectAll();
    }
}