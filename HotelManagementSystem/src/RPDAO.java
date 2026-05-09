import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RPDAO {

    public RPDAO() {
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getInt("room_number"),
                        rs.getInt("plan_id"),
                        rs.getBoolean("is_available")
                ));
            }
        }
        return rooms;
    }

    public List<Room> getAvailableRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE is_available = true";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getInt("room_number"),
                        rs.getInt("plan_id"),
                        rs.getBoolean("is_available")
                ));
            }

            return rooms;
        }
    }

    public void updateAvailability(int room_id, boolean is_available) throws SQLException {
        String query = "UPDATE rooms SET is_available = ? WHERE room_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, is_available);
            stmt.setInt(2, room_id);
            stmt.executeUpdate();
        }
    }

    public List<Room> getRoomsByPlanId(int plan_id) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms WHERE plan_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, plan_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getInt("room_number"),
                        rs.getInt("plan_id"),
                        rs.getBoolean("is_available")
                ));
            }
        }
        return rooms;
    }


    public List<Plan> getAllPlans() throws SQLException {
        List<Plan> plans = new ArrayList<>();
        String query = "SELECT * FROM plans";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                plans.add(new Plan(
                        rs.getInt("plan_id"),
                        rs.getString("plan_name"),
                        rs.getBoolean("breakfast_included"),
                        rs.getBoolean("gym_access"),
                        rs.getBoolean("pool_access"),
                        rs.getDouble("price_per_night")
                ));
            }
        }
        return plans;
    }


    public void printPlans(List<Plan> plans) {
        System.out.println("\n--- Available Plans ---");
        for (Plan p : plans) {
            System.out.println(
                    "ID: "        + p.getPlanId()
                            + " | Name: "   + p.getPlanName()
                            + " | Price/night: $" + p.getPricePerNight()
                            + " | Breakfast: " + (p.isBreakfastIncluded() ? "Yes" : "No")
                            + " | Gym: "    + (p.hasGymAccess()  ? "Yes" : "No")
                            + " | Pool: "   + (p.hasPoolAccess() ? "Yes" : "No")
            );
        }
        System.out.println("-----------------------");
    }


    public void printAvailableRooms(List<Room> rooms) {
        System.out.println("\n--- Available Rooms ---");
        for (Room r : rooms) {
            System.out.println(
                    "Room ID: "     + r.getRoomId()
                            + " | Room No: "  + r.getRoomNumber()
                            + " | Plan ID: "  + r.getPlanId()
            );
        }
        System.out.println("-----------------------");
    }
}