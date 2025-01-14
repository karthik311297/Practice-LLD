package lld.carrental;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Car {
    private String name;
    private int modelYear;
    private CarType carType;
    private String numberPlate;
    private long pricePerDay;
    LinkedList<Interval> bookedDays;

    public Car(String name, int modelYear, CarType carType, String numberPlate, long pricePerDay) {
        this.name = name;
        this.modelYear = modelYear;
        this.carType = carType;
        this.numberPlate = numberPlate;
        this.pricePerDay = pricePerDay;
        this.bookedDays = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int getModelYear() {
        return modelYear;
    }

    public CarType getCarType() {
        return carType;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public long getPricePerDay() {
        return pricePerDay;
    }

    public LinkedList<Interval> getBookedDays() {
        return bookedDays;
    }
}
