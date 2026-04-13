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
        String sql = "SELECT * FROM rooms";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("roomId"),
                        rs.getInt("roomNumber"),
                        rs.getInt("plan_id"), rs.getBoolean("isAvailable")
                ));
            }
        }
        return rooms;
    }

    public List<Room> getAvailableRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE is_available = true";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
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
}
