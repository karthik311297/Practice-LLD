package lld.carrental.reservation;

import lld.carrental.Car;
import lld.carrental.CarRepository;
import lld.carrental.Customer;
import lld.carrental.payment.PaymentService;
import lld.carrental.exception.CarNotAvailableException;
import lld.carrental.exception.PaymentFailedException;

import java.time.LocalDateTime;

public class ReservationService {

    private CarRepository carRepository;
    private PaymentService paymentService;

    public ReservationService(CarRepository carRepository, PaymentService paymentService) {
        this.carRepository = carRepository;
        this.paymentService = paymentService;
    }

    public Reservation checkout(Car car, LocalDateTime startDate, LocalDateTime endDate, Customer customer) throws CarNotAvailableException {
        if (!carRepository.isCarAvailableBetweenDates(car, startDate, endDate)) throw new CarNotAvailableException();
        return new Reservation(car, customer, startDate, endDate);
    }

    public synchronized void confirmReservationWithPayment(Reservation reservation) throws CarNotAvailableException, PaymentFailedException
    {
        if (!carRepository.isCarAvailableBetweenDates(reservation.getCar(), reservation.getStartDate(), reservation.getEndDate()))
        {
            throw new CarNotAvailableException();
        }
        long amountToPay = reservation.getTotalAmount();
        paymentService.pay(amountToPay);
        carRepository.markCarAsReservedForForDays(reservation.getCar(), reservation.getStartDate(), reservation.getEndDate());
        reservation.setStatus(ReservationStatus.PAYMENT_COMPLETED);
    }

    public void returnCar(Reservation reservation) {
        carRepository.markCarAsFreeForDays(reservation.getCar(), reservation.getStartDate(), reservation.getEndDate());
    }
}
