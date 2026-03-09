public class Employee {
    String name;
    private int phone;
    int age;
    private int id;
    private double salary;

    public Employee(String name, int phone,int age, int id, double salary) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.id = id;
        this.salary = salary;
    }


    public int getPhone(){
        return phone;
    }
    public int getId2(){
        return id;
    }
    public double getSalary(){
        return salary;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
