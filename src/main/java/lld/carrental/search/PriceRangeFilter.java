package lld.carrental.search;

import lld.carrental.Car;

public class PriceRangeFilter implements SearchFilter{

    private long startPricePerDay;
    private long endPricePerDay;

    public PriceRangeFilter(long startPricePerDay, long endPricePerDay) {
        this.startPricePerDay = startPricePerDay;
        this.endPricePerDay = endPricePerDay;
    }

    @Override
    public boolean match(Car car) {
        long pricePerDay = car.getPricePerDay();
        return pricePerDay >= startPricePerDay
                && pricePerDay <= endPricePerDay;
    }
}
