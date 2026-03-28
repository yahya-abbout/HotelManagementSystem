import java.util.ArrayList;
import java.util.List;

public class Customer {
    String name;
    private String LastName;
    private int id;
    int plan;
    private double TotalPrice;

    private List<String> bookingHistory = new ArrayList<>();


    public Customer(String name, String LastName, int id, int plan, double TotalPrice) {
        this.name = name;
        this.LastName = LastName;
        this.id = id;
        this.plan = plan;
        this.TotalPrice = TotalPrice;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return LastName;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void addBooking(String bookingDetails) {
        bookingHistory.add(bookingDetails);
    }

    public List<String> getBookingHistory() {
        return bookingHistory;
    }
}
