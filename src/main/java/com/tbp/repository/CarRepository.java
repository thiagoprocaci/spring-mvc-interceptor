package com.tbp.repository;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CarRepository {

    static Map<Integer, Car> carMap = new HashMap<>();
    static Integer count = 0;

    static {
        Car car = new Car("Chevrolet", 1998, "Red", "Monza");
        carMap.put(count, car);
        count++;
    }

    public void save(Car car) {
        if(car.getId() == null) {
            car.setId(count);
            count++;
        }
        carMap.put(car.getId(), car);
    }

    public List<Car> findAll() {
       return new ArrayList<>(carMap.values()) ;
    }





}
