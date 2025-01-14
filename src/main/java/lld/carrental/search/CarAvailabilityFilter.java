package lld.carrental.search;

import lld.carrental.Car;
import lld.carrental.CarRepository;

import java.time.LocalDateTime;

public class CarAvailabilityFilter implements SearchFilter {

    private CarRepository carRepository;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CarAvailabilityFilter(CarRepository carRepository, LocalDateTime startDate, LocalDateTime endDate) {
        this.carRepository = carRepository;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean match(Car car) {
        return carRepository.isCarAvailableBetweenDates(car, startDate, endDate);
    }
}
