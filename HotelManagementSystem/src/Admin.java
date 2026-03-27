import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;


public class Admin {

    HashMap<Integer, Customer> customers = new HashMap<>();
    HashSet<Integer> usedIds = new HashSet<>();
    Queue<Customer> waitingList = new LinkedList<>();


    public Admin() {
    }

    
    public void AddCostumer(String name, String LastName, int id, int plan, double TotalPrice) {

        
        if (usedIds.contains(id)) {
            System.out.println("Error: Customer ID " + id + " already exists!");
            return;
        }

        Customer newCustomer = new Customer(name, LastName, id, plan, TotalPrice);
        customers.put(id, newCustomer);
        usedIds.add(id);
        newCustomer.addBooking("Plan " + plan + " - $" + TotalPrice);
        System.out.println("Customer added successfully.");
    }

  
    public void DeleteCostumer(int id) {

        
        if (customers.containsKey(id)) {
            customers.remove(id);
            usedIds.remove(id);
            System.out.println("Customer deleted.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void addToWaitingList(Customer c) {
        waitingList.offer(c);
        System.out.println(c.name + " added to waiting list. Position: " + waitingList.size());
    }

    public void serveNextWaiting() {
        Customer next = waitingList.poll();
        if (next != null) {
            System.out.println("Serving next customer: " + next.name);
            AddCostumer(next.name, next.getLastName(), next.getId(), next.plan, next.getTotalPrice());
        } else {
            System.out.println("Waiting list is empty.");
        }
    }

    public void loginAdmin(String name, int id) {}
}
