import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Stack;


public class HR {

    HashMap<Integer, Employee> employees = new HashMap<>();
    TreeMap<Double, Employee> salaryRanking = new TreeMap<>();
    Stack<String> hrActionLog = new Stack<>();


    public HR() {}

    public void AddEmployee(String name, int phone, int age, int id, double salary) {

        if (employees.containsKey(id)) {
            System.out.println("Error: ID " + id + " already exists!");
            return;
        }
        Employee newEmployee = new Employee(name, phone, age, id, salary);
        employees.put(id, newEmployee);
        salaryRanking.put(salary, newEmployee);
        hrActionLog.push("ADDED: " + name + " | ID: " + id + " | Salary: " + salary);

        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO employees (first_name, phone, age, id, salary, department) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, phone);
            stmt.setInt(3, age);
            stmt.setInt(4, id);
            stmt.setDouble(5, salary);
            stmt.setString(6, newEmployee.getDepartment());
            stmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void DeleteEmployee(int id) {

        if (employees.containsKey(id)) {
            Employee removed = employees.get(id);
            employees.remove(id);
            salaryRanking.remove(removed.getSalary());
            hrActionLog.push("DELETED: " + removed.name + " ID: " + id);

            try {
                Connection conn = DatabaseConnection.getConnection();
                String query = "DELETE FROM employees WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Employee removed.");
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void editEmployee(int id, String newName, double newSalary) {

        Employee e = employees.get(id);
        if (e != null) {
            double oldSalary = e.getSalary();
            e.setName(newName);
            e.setSalary(newSalary);
            salaryRanking.remove(oldSalary);
            salaryRanking.put(newSalary, e);
            hrActionLog.push("EDITED: ID " + id + " | New name: " + newName + " | New salary: " + newSalary);

            try {
                Connection conn = DatabaseConnection.getConnection();
                String query = "UPDATE employees SET first_name = ?, salary = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, newName);
                stmt.setDouble(2, newSalary);
                stmt.setInt(3, id);
                stmt.executeUpdate();
                System.out.println("Employee updated.");
            } catch (SQLException ex) {
                System.out.println("Database error: " + ex.getMessage());
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void countEmployees() {
        System.out.println("The Hotel has " + employees.size() + " Employees");
    }

    public void CalculateSalaries() {
        double total = 0;
        for (Employee e : employees.values()) {
            total += e.getSalary();
        }
        System.out.println("Total salaries is: " + total);
    }

    public void showHighestPaidEmployee() {
        if (salaryRanking.isEmpty()) {
            System.out.println("No employees.");
            return;
        }
        Employee top = salaryRanking.lastEntry().getValue();
        System.out.println("Highest paid: " + top.name + " | Salary: $" + salaryRanking.lastKey());
    }

    public void showLastAction() {
        if (hrActionLog.isEmpty()) {
            System.out.println("No actions recorded yet.");
        } else {
            System.out.println("Last HR action: " + hrActionLog.peek());
        }
    }

    public void showAllActions() {
        if (hrActionLog.isEmpty()) {
            System.out.println("No actions recorded.");
            return;
        }
        System.out.println("--- HR Action Log ---");
        for (int i = hrActionLog.size() - 1; i >= 0; i--) {
            System.out.println(hrActionLog.get(i));
        }
    }
}
