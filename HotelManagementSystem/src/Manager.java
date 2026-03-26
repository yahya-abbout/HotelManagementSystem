// BUG FIX: Removed duplicate/broken import block and the broken class declaration
// 'public class Manager import java.util.HashMap; ...' — only one clean import section and class declaration kept.
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Manager {

    HashMap<Integer, Customer> customers;
    HashMap<Integer, Employee> employees;
    LinkedHashMap<String, Double> monthlyRevenueHistory = new LinkedHashMap<>();
    Map<String, Double> expenseBreakdown = new HashMap<>();


    public Manager(HashMap<Integer, Customer> customers, HashMap<Integer, Employee> employees) {
        this.customers = customers;
        this.employees = employees;
        expenseBreakdown.put("Maintenance", 3500.99);
        expenseBreakdown.put("Utilities", 1200.0);
        expenseBreakdown.put("Supplies", 800.0);
    }


    public double getTotalRevenue(HashMap<Integer, Customer> customers) {
        double TotalRevenue = 0;
        for (Customer c : customers.values()) {
            TotalRevenue += c.getTotalPrice();
        }
        return TotalRevenue;
    }

    public void showTotalRevenue() {
        double revenue = getTotalRevenue(customers);
        System.out.println("The total revenue is: " + revenue + "$");
        String currentMonth = java.time.LocalDate.now().getMonth().toString();
        monthlyRevenueHistory.put(currentMonth, revenue);
    }

    // BUG FIX: 'for (Employee e : employees)' was iterating over a HashMap directly,
    // which is not iterable. Changed to 'employees.values()' to iterate over Employee objects.
    public double getTotalExpenses(HashMap<Integer, Employee> employees) {
        double employeesExpenses = 0;
        for (Employee e : employees.values()) {
            employeesExpenses += e.getSalary();
        }
        double otherExpenses = 0;
        for (double amount : expenseBreakdown.values()) {
            otherExpenses += amount;
        }
        return employeesExpenses + otherExpenses;
    }

    public void showTotalExpenses() {
        double expenses = getTotalExpenses(employees);
        System.out.println("The total amount paid this month: " + expenses + "$");
        System.out.println("***Expense Breakdown***");
        for (Map.Entry<String, Double> entry : expenseBreakdown.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    public void getSummary() {
        double MonthSummary = getTotalRevenue(customers) - getTotalExpenses(employees);
        if (MonthSummary > 0) {
            System.out.println("Win: " + MonthSummary + "$");
        } else if (MonthSummary < 0) {
            System.out.println("Loss: " + MonthSummary + "$");
        } else {
            System.out.println("Head to Head");
        }
        // BUG FIX: 'result' variable was declared but never used — kept it as is
        // since removing it would change the original code structure.
        String result = MonthSummary > 0 ? "Profit" : MonthSummary < 0 ? "Loss" : "Break Even";
        monthlyRevenueHistory.put("Summary", MonthSummary);
    }

    public void AddEmployee(String name, int phone, int age, int id, double salary) {
        employees.put(id, new Employee(name, phone, age, id, salary));
    }

    public void showRevenueHistory() {
        if (monthlyRevenueHistory.isEmpty()) {
            System.out.println("No revenue history yet.");
            return;
        }
        System.out.println("***Monthly Revenue History***");
        for (Map.Entry<String, Double> entry : monthlyRevenueHistory.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}
