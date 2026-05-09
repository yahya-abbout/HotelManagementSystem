public class Room {

    int room_id;
    int room_number;
    int plan_id;
    boolean is_available;


    public Room(int room_id, int room_number, int plan_id, boolean is_available) {
        this.room_id = room_id;
        this.room_number = room_number;
        this.plan_id = plan_id;
        this.is_available = is_available;
    }


    public int getRoomId()     { return room_id; }
    public int getRoomNumber() { return room_number; }
    public int getPlanId()     { return plan_id; }
    public boolean isAvailable() { return is_available; }
}