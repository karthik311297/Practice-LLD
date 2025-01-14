package lld.carrental.reservation;

import lld.carrental.Car;
import lld.carrental.Customer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private long id;
    private Car car;
    private Customer customer;
    private long totalAmount;
    private ReservationStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Reservation(Car car, Customer customer,
                       LocalDateTime startDate, LocalDateTime endDate) {
        this.car = car;
        this.customer = customer;
        this.status = ReservationStatus.INITIATED;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = Duration.between(startDate,
                endDate.plus(Duration.of(1, ChronoUnit.DAYS))).toDays() * car.getPricePerDay();
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
