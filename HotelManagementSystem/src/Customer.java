public class Customer {
    String name;
    private String LastName;
    private int id;
    int plan;
    private double TotalPrice;


    public Customer(String name, String LastName, int id, int plan, double TotalPrice) {
        this.name = name;
        this.LastName = LastName;
        this.id = id;
        this.plan = plan;
        this.TotalPrice = TotalPrice;
    }
    public int getId(){
        return id;
    }
    public String getLastName(){
        return LastName;
    }
    public double getTotalPrice(){
        return TotalPrice;
    }

}
