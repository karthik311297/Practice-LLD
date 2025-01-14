package lld.carrental;

import lld.carrental.exception.NoCarFoundException;
import lld.carrental.search.SearchCondition;
import lld.carrental.search.SearchFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarRepository {

    public final List<Car> cars;

    public CarRepository() {
        cars = new ArrayList<>();
    }

    public synchronized void addCars(List<Car> carList) {
        cars.addAll(carList);
    }

    public Car findCar(SearchFilter[] searchFilters, SearchCondition searchCondition) throws NoCarFoundException {
        for (Car c : cars) {
            if (doesMatchSearchCriteria(c, searchFilters, searchCondition)) return c;
        }
        throw new NoCarFoundException();
    }

    private boolean doesMatchSearchCriteria(Car car, SearchFilter[] searchFilters, SearchCondition searchCondition) {
        if (searchCondition == SearchCondition.OR) {
            for (SearchFilter s : searchFilters) {
                if (s.match(car)) return true;
            }
            return false;
        } else if (searchCondition == SearchCondition.AND) {
            for (SearchFilter s : searchFilters) {
                if (!s.match(car)) return false;
            }
            return true;
        } else {
            return searchFilters[0].match(car);
        }
    }

    public synchronized void markCarAsReservedForForDays(Car car, LocalDateTime startDate, LocalDateTime endDate)
    {
        if (isCarAvailableBetweenDates(car, startDate, endDate)) {
            LinkedList<Interval> bookedDays = car.getBookedDays();
            Interval e = new Interval(startDate, endDate);
            if (bookedDays.isEmpty()) {
                bookedDays.add(e);
                return;
            }
            int indexToInsert = -1;
            for (int i = 0; i < bookedDays.size(); i++) {
                Interval cur = bookedDays.get(i);
                if (endDate.isBefore(cur.getStartDate())) {
                    indexToInsert = i;
                    break;
                }
            }
            if (indexToInsert != -1) {
                bookedDays.add(indexToInsert, e);
            } else {
                bookedDays.addLast(e);
            }
        }
    }

    public synchronized void markCarAsFreeForDays(Car car, LocalDateTime startDate, LocalDateTime endDate) {
        LinkedList<Interval> bookedDays = car.getBookedDays();
        Interval e = new Interval(startDate, endDate);
        bookedDays.remove(e);
    }

    public boolean isCarAvailableBetweenDates(Car car, LocalDateTime startDate, LocalDateTime endDate) {
        LinkedList<Interval> bookedDays = car.getBookedDays();
        Interval cur = new Interval(startDate, endDate);
        if (bookedDays.isEmpty()) {
            return true;
        }
        if (cur.getEndDate().isBefore(bookedDays.get(0).getStartDate())) return true;
        for (int i = 1; i < bookedDays.size(); i++) {
            Interval next = bookedDays.get(i);
            Interval prev = bookedDays.get(i - 1);
            if (next.getStartDate().isAfter(cur.getEndDate()) && cur.getStartDate().isAfter(prev.getEndDate())) return true;
        }
        if (cur.getStartDate().isAfter(bookedDays.get(bookedDays.size() - 1).getEndDate())) return true;
        return false;
    }
}
