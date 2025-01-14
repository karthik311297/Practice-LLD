package lld.carrental.search;

import lld.carrental.Car;

public interface SearchFilter {
    boolean match(Car car);
}
