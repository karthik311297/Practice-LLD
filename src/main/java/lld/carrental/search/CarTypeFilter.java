package lld.carrental.search;

import lld.carrental.Car;
import lld.carrental.CarType;

public class CarTypeFilter implements SearchFilter {

    private CarType carType;

    public CarTypeFilter(CarType carType) {
        this.carType = carType;
    }

    @Override
    public boolean match(Car car) {
        return car.getCarType() == carType;
    }
}
