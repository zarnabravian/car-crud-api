package com.org.task4.service;

import com.org.task4.model.Car;
import com.org.task4.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarService {

@Autowired
CarRepository carRepository;

    /**
     *
     * @param make
     * @return List<Car>
     */
    public List<Car> getAllCars(String make)
    {
        List<Car> cars = new ArrayList<Car>();

        if (make == null)
            carRepository.findAll().forEach(cars::add);
        else
            carRepository.findByMakeContaining(make).forEach(cars::add);

        return cars;
    }

    /**
     *
     * @param id
     * @return Optional<Car>
     */
    public Optional<Car> getCarById(long id)
    {
        List<Car> cars = new ArrayList<Car>();

        return carRepository.findById(id);

    }

    /**
     *
     * @param car
     * @return Car
     */
    public Car saveCar(Car car) {
        Car carDetails = null;
        if ( car != null )
            car = carRepository.save(new Car(car.getMake(), car.getModel(), car.getYear()));
        return car;
    }

    /**
     *
     * @param id
     * @param car
     * @return Car
     */
    public Car updateCar(long id, Car car) {

        Optional<Car> carData = carRepository.findById(id);

        if (carData.isPresent()) {
            Car _car = carData.get();
            _car.setMake(car.getMake());
            _car.setModel(car.getModel());
            _car.setYear(car.getYear());

            return carRepository.save(_car);
        } else
            return null;
    }

    /**
     *
     * @param id
     * @return boolean
     */
    public boolean deleteCarById(long id) {

        try {
            carRepository.deleteAll();
            carRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @return boolean
     */
    public boolean deleteAllCars() {

        try {
            carRepository.deleteAll();
            carRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
