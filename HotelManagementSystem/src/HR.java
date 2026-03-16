import java.util.ArrayList;

public class HR {

     ArrayList<Employee> employees;   // removed static

    public HR(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void AddEmployee(String name, int phone, int age, int id, double salary) {
        employees.add(new Employee(name, phone, age, id, salary));
    }

    public void DeleteEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId2() == id) {
                employees.remove(i);
                System.out.println("Employee removed");
                return;
            }
        }
        System.out.println("Employee not found");
    }

    public void editEmployee(int id, String newName, double newSalary) {
        for (Employee e : employees) {
            if (e.getId2() == id) {
                e.setName(newName);
                e.setSalary(newSalary);
                System.out.println("Employee updated");
                return;
            }
        }
    }

    public void countEmployees() {
        System.out.println("The Hotel has " + employees.size() + " Employees");
    }

    public void CalculateSalaries() {
        double total = 0;
        for (Employee e : employees) {
            total += e.getSalary();
        }
        System.out.println("Total salaries is: " + total);
    }
}
