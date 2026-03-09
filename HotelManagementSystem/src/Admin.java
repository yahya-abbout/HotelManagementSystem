import java.util.ArrayList;

public class Admin {


ArrayList<Customer> customers;

    public Admin(ArrayList<Customer> customers) {

        this.customers = customers;
    }

    public void AddCostumer(String name, String LastName, int id, int plan, double TotalPrice){
        customers.add(new Customer(name, LastName, id, plan, TotalPrice));
        System.out.println("Costumer added");
    }
    public void DeleteCostumer(String name, String LastName, int id, int plan, double TotalPrice){
        for(int i=0;i<customers.size();i++){
            if (customers.get(i).getId() == id){
                customers.remove(i);
                System.out.println("costumer deleted");
                return;
            }
        }
        System.out.println("costumer not found");


    }

    public void loginAdmin(String name, int id) {


    }
}

