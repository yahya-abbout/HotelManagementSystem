import java.util.Stack;

public class Employee {

    String name;
    private int phone;
    int age;
    private int id;
    private double salary;
    private String department;
    String role;

    private Stack<String> activityLog = new Stack<>();

    public Employee(String name, int phone, int age, int id, double salary) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.id = id;
        this.salary = salary;
        this.department = null;
        // department updates in another method.
    }

    public int getPhone() {
        return phone;
    }

    public int getId2() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
