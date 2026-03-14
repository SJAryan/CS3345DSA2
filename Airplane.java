public class Airplane {
    private int id;
    private int arrivalTime;
    private boolean isLanding; 

    public Airplane(int id, int arrivalTime, boolean isLanding) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.isLanding = isLanding;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getId() {
        return id;
    }
    
    public boolean isLanding() {
        return isLanding;
    }
}