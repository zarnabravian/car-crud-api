package com.org.task4.repository;

import java.util.List;

import com.org.task4.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
  List<Car> findByModel(String model);

  List<Car> findByMakeContaining(String make);
}
