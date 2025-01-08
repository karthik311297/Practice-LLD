package lld.parking;

public class ParkDemo {
    /*
        enum VehicleType
            TWO, FOUR, EIGHT

        Vehicle
            number
            VehicleType

        abstract Slot
            VehicleType
            price
            vehicleParked
            vehicleParkedTime
            getPrice()
            getVehicleType()
        Extended by :
            TwoWheelerSlot, FourWheelerSlot, EightWheelerSlot

        ParkingLot
            List<ParkingFloor>

        ParkingFloor
            Map<VehicleType, List<Slot>>
            displayStats(VehicleType)

        ParkingLotManager
            Slot park() - find free parking spot using Parking lot which would check in each parking floor and then mark the slot as not free
            unpark(Slot, currentTime)  -  frees up the parking slot

     */
}
