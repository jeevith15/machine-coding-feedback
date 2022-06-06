import java.util.Vector;

public class ParkingLot {
    private String parkingLotName;
    Vector<Floor> floors;

    public ParkingLot(String parkingLotName, int floorCount, int slotsPerEachFloor){
       this.parkingLotName =parkingLotName;
       floors = new Vector<>(floorCount);
       for(int i=0;i<floorCount;i++){
           floors.add(i,new Floor(i,slotsPerEachFloor));
       }
        System.out.println("Created parking lot with "+floorCount+" floors and "+ slotsPerEachFloor+" slots per floor");
    }

    public void displayFreeCount(String vehicleType){
        for(int i=0;i< floors.size();i++){
            int freeCount = floors.get(i).getFreeCount(vehicleType);
            System.out.println("No. of free slots for "+vehicleType+" on floor "+(i+1)+": "+freeCount);
        }
    }

    public void displayFreeSlots(String vehicleType){
        for(int i=0;i< floors.size();i++){
            System.out.print("Free slots for " + vehicleType + " on Floor " + (i + 1) + ": ");
            Vector<Slot> freeSlots = floors.get(i).getFreeSlots(vehicleType);
            for(int j=0;j<freeSlots.size();j++){
                System.out.print(freeSlots.get(j).getSlotNumber()+", ");
            }
            System.out.println();
        }
    }

    public void displayOccupiedSlots(String vehicleType){
        for(int i=0;i< floors.size();i++){
            System.out.print("Occupied slots for " + vehicleType + " on Floor " + (i + 1) + ": ");
            Vector<Slot> occupiedSlots = floors.get(i).getOccupiedSlots(vehicleType);
            for(int j=0;j<occupiedSlots.size();j++){
                System.out.print(occupiedSlots.get(j).getSlotNumber()+", ");
            }
          System.out.println();
        }
    }

    public void parkVehicle(Vehicle vehicle){
        for(int i=0;i< floors.size();i++){
            Slot vehicleSlot = floors.get(i).parkVehicle(vehicle);
            if(vehicleSlot!=null){
                System.out.println("Parked Vehicle. Ticket ID: " + parkingLotName + vehicleSlot.generateTicket());
                return;
            }
        }

        System.out.println("Parking Lot Full");
        return;
    }

    public void unparkVehicle(String ticket){
        String[] input = ticket.split("_");
        int floorNo = Integer.parseInt(input[1])-1;
        int slotNo = Integer.parseInt(input[2])-1;

        if(input[0].equals(parkingLotName) && floorNo < floors.size() && slotNo < floors.get(0).getSlots().size()){
            Vehicle unParkVehilcle = floors.get(floorNo).unparkVehicle(slotNo);
            if(unParkVehilcle!=null){
                System.out.println("Unparked vehicle with Registration Number: " + unParkVehilcle.getRegistrationNumber() + " and Color "
                        + unParkVehilcle.getColor());
                return;
            }
        }
        System.out.println("Invalid Ticket");
        return;
    }

}
