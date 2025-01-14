package lld.carrental;

import lld.carrental.payment.PaymentService;
import lld.carrental.reservation.Reservation;
import lld.carrental.reservation.ReservationService;
import lld.carrental.search.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RentalDemo {
    /*
    The car rental system should allow customers to browse and reserve available cars for specific dates.
    Each car should have details such as make, model, year, license plate number, and rental price per day.
    Customers should be able to search for cars based on various criteria, such as car type, price range, and availability.
    The system should handle reservations, including creating, modifying, and canceling reservations.
    The system should keep track of the availability of cars and update their status accordingly.
    The system should handle customer information, including name, contact details, and driver's license information.
    The system should handle payment processing for reservations.
    The system should be able to handle concurrent reservations and ensure data consistency.
     */

    /*
        Car - Name, Model, Type, Numberplate, PricePerDay, availableDays[]

        Type -
            SEDAN
            SUV
            HATCHBACK

        Customer -
            name
            phno
            licensenumber

        CarRepository -
            findCar(SearchFilter[])
            markCarAsReservedForForDays(Car car, days[])
            markCarAsFreeForDays((Car car, days[]))
            isCarAvailableOnDays(days[])

        SearchFilter
            match(Car)
        extended by -
            TypeFilter, PriceRangeFilter, AvailabilityFilter

        ReservationService
            Reservation checkout(Car car, days[])
            synchronized confirmReservationWithPayment(Reservation) (check for stock again before Initiating payment)
                completes the reservation and then updates the availability of the car
            returnCar(Reservation r)

        PaymentService
            pay(int amount)

        CarNotAvailableException

        NoCarFoundException

        PaymentFailedException

        Reservation
            Car
            Customer
            days[]
            totalAmount
            ReservationStatus

        ReservationStatus
            INITIATED
            PAYMENT_COMPLETED

     */

    public static void main(String[] args) throws Exception {
        Customer customer = new Customer("K1", "909", "7KA98");
        // CarRepository, PaymentService, ReservationService will have single instance
        CarRepository carRepository = new CarRepository();
        PaymentService paymentService = new PaymentService();
        ReservationService reservationService = new ReservationService(carRepository, paymentService);
        Car c1 = new Car("Tata nexon", 2020, CarType.HATCHBACK, "KA04", 800);
        Car c2 = new Car("Swift Dzire", 2018, CarType.SEDAN, "KA05", 700);
        Car c3 = new Car("XUV 700", 2022, CarType.SUV, "KA06", 1100);
        Car c4 = new Car("Audi Q7", 2020, CarType.SUV, "KA08", 1500);
        carRepository.addCars(Arrays.asList(c1, c2, c3, c4));

        Car car = carRepository.findCar(new SearchFilter[]{new CarTypeFilter(CarType.SUV), new PriceRangeFilter(1200, 1800)}, SearchCondition.AND);
        Reservation reservation1 =
                reservationService.checkout(car,
                        LocalDateTime.of(2024, Month.DECEMBER, 10, 0, 0), LocalDateTime.of(2024, Month.DECEMBER, 14, 0, 0), customer);
        reservationService.confirmReservationWithPayment(reservation1);
        Reservation reservation2 =
                reservationService.checkout(car,
                        LocalDateTime.of(2024, Month.DECEMBER, 4, 0, 0), LocalDateTime.of(2024, Month.DECEMBER, 9, 0, 0), customer);
        reservationService.confirmReservationWithPayment(reservation2);

        try {
            Car car2 = carRepository.findCar(new SearchFilter[]{new CarAvailabilityFilter(carRepository,
                    LocalDateTime.of(2024, Month.DECEMBER, 7, 0, 0), LocalDateTime.of(2024, Month.DECEMBER, 12, 0, 0)),
                    new PriceRangeFilter(1200, 1800)}, SearchCondition.AND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Reservation reservation3 = null;
        try {
            reservation3 = reservationService.checkout(car,
                    LocalDateTime.of(2024, Month.DECEMBER, 7, 0, 0), LocalDateTime.of(2024, Month.DECEMBER, 12, 0, 0), customer);
            reservationService.confirmReservationWithPayment(reservation3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        reservationService.returnCar(reservation1);
        reservationService.returnCar(reservation2);
        reservation3 =
                reservationService.checkout(car,
                        LocalDateTime.of(2024, Month.DECEMBER, 7, 0, 0), LocalDateTime.of(2024, Month.DECEMBER, 12, 0, 0), customer);
        reservationService.confirmReservationWithPayment(reservation3);
        reservationService.returnCar(reservation3);

        // Testing multiple threads scenario when booking same dates
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            es.submit(() ->
            {
                try {
                    Reservation reservation =
                            reservationService.checkout(car,
                                    LocalDateTime.of(2024, Month.DECEMBER, 10, 0, 0), LocalDateTime.of(2024, Month.DECEMBER, 14, 0, 0), customer);
                    Thread.sleep(2000);
                    reservationService.confirmReservationWithPayment(reservation);
                    System.out.println("Thread " + Thread.currentThread().getId() + " : " + "Successful");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Thread " + Thread.currentThread().getId() + " : Failed with message - " + e.getMessage());
                }
            });
        }
        es.shutdown();
    }

}
