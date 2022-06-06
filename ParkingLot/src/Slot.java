public class Slot {
    private int slotNumber;
    private int floorNumber;
    private String slotType;
    private Vehicle vehicle;
    private  boolean isBooked;

    public Slot(int slotNumber,int floorNumber, String slotType){
        this.slotNumber = slotNumber;
        this.floorNumber = floorNumber;
        this.slotType = slotType;
        this.isBooked = false;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getSlotType() {
        return slotType;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public  String generateTicket(){
        return "_"+ (floorNumber+1) +"_"+(slotNumber+1);
    }

    public Vehicle freeSlot(){
        setBooked(false);
        Vehicle freedVehicle = this.vehicle;
        setVehicle(null);
        return freedVehicle;
    }

}
