import java.io.*;
import java.util.Scanner;

public class DummyDriver  {

    public static void main(String[] args) throws IOException{
        Scanner sc  = new Scanner(System.in);
        String[] input = new String[4];
        ParkingLot parkingLot = null;
        input[0] = "create_parking_lot";
        input[1] =  "PR1234";
        input[2] = "2";
        input[3] = "6";

            switch (input[0]){
                case "create_parking_lot":
                    parkingLot = new ParkingLot(input[1],Integer.parseInt(input[2]),Integer.parseInt(input[3]));
                    break;
                case "display":
                    switch (input[1]){
                        case "free_count":
                            if(parkingLot!=null){
                                parkingLot.displayFreeCount(input[2]);
                            }
                            break;

                        case "free_slots":
                            if(parkingLot!=null){
                                parkingLot.displayFreeSlots(input[2]);
                            }
                            break;

                        case "occupied_slots":
                            if(parkingLot!=null){
                                parkingLot.displayOccupiedSlots(input[2]);
                            }
                            break;

                        default:
                            System.out.println("Invalid input. Try again");
                            break;
                    }
                    break;

                case "park_vehicle":
                    Vehicle vehicle = new Vehicle(input[2], input[3], input[1] );
                    if(parkingLot!=null){
                        parkingLot.parkVehicle(vehicle);
                    }
                    break;

                case "unpark_vehicle":
                    if(parkingLot!=null){
                        parkingLot.unparkVehicle(input[1]);
                    }
                    break;

                default:
                    System.out.println("Invalid input. Try again");
                    break;
            }

            parkingLot.displayFreeCount("CAR");
        }
    }


