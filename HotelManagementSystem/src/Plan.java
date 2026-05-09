public class Plan {

    int plan_id;
    String plan_name;
    boolean breakfast_included;
    boolean gym_access;
    boolean pool_access;
    private double price_per_night;

    // CHANGED: updated constructor parameter names to match the new field names
    public Plan(int plan_id, String plan_name, boolean breakfast_included,
                boolean gym_access, boolean pool_access, double price_per_night) {
        this.plan_id           = plan_id;
        this.plan_name         = plan_name;
        this.breakfast_included = breakfast_included;
        this.gym_access        = gym_access;
        this.pool_access       = pool_access;
        this.price_per_night   = price_per_night;
    }


    public double getPricePerNight() {
        return price_per_night;
    }


    public int    getPlanId()            { return plan_id; }
    public String getPlanName()          { return plan_name; }
    public boolean isBreakfastIncluded() { return breakfast_included; }
    public boolean hasGymAccess()        { return gym_access; }
    public boolean hasPoolAccess()       { return pool_access; }
}