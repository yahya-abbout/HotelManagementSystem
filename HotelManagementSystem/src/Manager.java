import java.util.ArrayList;

public class Manager {

 ArrayList<Customer> customers;
 ArrayList<Employee> employees;


//  Creating a constructor and adding the arraylists
// so the class can inherit data from the arraylist in the other class

    public Manager(ArrayList<Customer> customers, ArrayList<Employee> employees) {
        this.customers = customers;
        this.employees = employees;
    }


// Calculating total revenue for one month


    public double getTotalRevenue(ArrayList<Customer> customers){
        double TotalRevenue = 0;

        for(Customer c: customers){
            TotalRevenue += c.getTotalPrice();
        }

        return TotalRevenue;

    }
    public void showTotalRevenue(){

        double revenue = getTotalRevenue(customers);
        System.out.println("The total revenue is: " + revenue + "$");
    }


    // Calculating total expenses for one month

    public double getTotalExpenses(ArrayList<Employee> employees){
        double TotalExpenses = 0;
        double EmployeesExpenses=0;

        for(Employee e : employees){
            EmployeesExpenses = e.getSalary();
        }
        double maintenance = 3500.99;
        return EmployeesExpenses+maintenance;
    }
    public void showTotalExpenses(){
        double expenses = getTotalExpenses(employees);
        System.out.println("The total amount paid this month: " + expenses + "$");
    }
    // this methode gives if we close the month with a profit or loss or tie!
    public void getSummary(){

        double MonthSummary = getTotalRevenue(customers) - getTotalExpenses(employees);

        if (MonthSummary>0){
            System.out.println("Win: " + MonthSummary + "$");

        } else if (MonthSummary<0) {
            System.out.println("Loss: " + MonthSummary + "$");

        } else {
            System.out.println("Head to Head");
        }
    }
    // Add HR
    public void AddEmployee(String name, int phone, int age, int id, double salary) {
        employees.add(new Employee(name, phone, age, id, salary));
    }
}