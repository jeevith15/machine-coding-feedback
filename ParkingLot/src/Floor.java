import java.util.Vector;

public class Floor {
    private int floorNumber;
    private Vector<Slot> slots;

    public Floor(int floorNumber, int slotCount) {
        this.floorNumber = floorNumber;
        this.slots = new Vector<Slot>(slotCount);

        for (int i = 0; i < Math.min(slotCount, 1); i++) {
            slots.add(i, new Slot(i, floorNumber, "TRUCK"));
        }

        for (int i = 1; i < Math.min(slotCount, 3); i++) {
            slots.add(i, new Slot(i, floorNumber, "BIKE"));
        }

        for (int i = 3; i < slotCount; i++) {
            slots.add(i, new Slot(i, floorNumber, "CAR"));
        }
    }

    public Vector<Slot> getSlots() {
        return slots;
    }

    public int getFreeCount(String vehicleType){
            int freeCount=0;
            for (int i = 0;i<slots.size();i++){
                if(slots.get(i).isBooked() == false && slots.get(i).getSlotType()==vehicleType){
                    freeCount++;
                }
            }
            return freeCount;
        }

        public Vector<Slot> getFreeSlots(String vehicleType){
            Vector<Slot> freeSlots = new Vector<>();
            for(int i=0;i<slots.size();i++){
                if(slots.get(i).isBooked() == false && slots.get(i).getSlotType()==vehicleType){
                    freeSlots.add(slots.get(i));
                }
            }
            return freeSlots;
        }


    public Vector<Slot> getOccupiedSlots(String vehicleType){
        Vector<Slot> occupiedSlots = new Vector<>();
        for(int i=0;i<slots.size();i++){
            if(slots.get(i).isBooked() == true && slots.get(i).getSlotType()==vehicleType){
                occupiedSlots.add(slots.get(i));
            }
        }
        return occupiedSlots;
    }

    public Slot parkVehicle(Vehicle vehicle){
        for (int i=0;i<slots.size();i++){
            if(slots.get(i).isBooked() == false && slots.get(i).getSlotType() == vehicle.getVehicleType()){
                slots.get(i).setBooked(true);
                slots.get(i).setVehicle(vehicle);
                return slots.get(i);
            }
        }
        return null;
    }

    public Vehicle unparkVehicle(int slotNumber){
        if(slots.get(slotNumber).isBooked()){
            Vehicle vehicle = slots.get(slotNumber).freeSlot();
            return vehicle;
        }
        return null;
    }


}
