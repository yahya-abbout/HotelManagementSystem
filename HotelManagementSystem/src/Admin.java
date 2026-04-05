{

    HashMap<Integer, Customer> customers = new HashMap<>();
    HashSet<Integer> usedIds = new HashSet<>();
    Queue<Customer> waitingList = new LinkedList<>();


    public Admin() {
    }

    public void AddCostumer(String name, String LastName, int id, int plan, double TotalPrice) {

        Customer NewCustomer = new Customer(name, LastName, id, plan, TotalPrice);

      if(customers.size()>=12) {
          System.out.println("Hotel is fully booked. Adding to waiting list..");
          addToWaitingList(NewCustomer);
          return;
      }

        if (usedIds.contains(id)) {
            System.out.println("Error: Customer ID " + id + " already exists!");
            return;
        }

        Customer newCustomer = new Customer(name, LastName, id, plan, TotalPrice);
        customers.put(id, newCustomer);
        usedIds.add(id);
        newCustomer.addBooking("Plan " + plan + " : $" + TotalPrice);

        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO customers (first_name, last_name, id, plan, total_price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, LastName);
            stmt.setInt(3, id);
            stmt.setInt(4, plan);
            stmt.setDouble(5, TotalPrice);
            stmt.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void DeleteCostumer(int id) {

        if (customers.containsKey(id)) {
            customers.remove(id);
            usedIds.remove(id);

            try {
                Connection conn = DatabaseConnection.getConnection();
                String query = "DELETE FROM customers WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Customer deleted.");
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
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

    public void showWaitingList(){
        if (waitingList!=null){
            for (Customer c : waitingList){
                System.out.println(c.name);
            }
        }
    }
}
