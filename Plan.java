{

    int planId;
    String planName;
    boolean BreakfastIncluded;
    boolean gymAccess;
    boolean poolAccess;
    private double PricePerNight;

    public Plan(int planId, String planName, boolean BreakfastIncluded, boolean gymAccess, boolean poolAccess,double PricePerNight){
        this.planId=planId;
        this.planName=planName;
        this.BreakfastIncluded=BreakfastIncluded;
        this.gymAccess=gymAccess;
        this.poolAccess=poolAccess;
        this.PricePerNight=PricePerNight;
    }


    public double getPricePerNight(){
        return PricePerNight;
    }
}
