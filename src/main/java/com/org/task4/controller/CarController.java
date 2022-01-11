package com.org.task4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.org.task4.model.Car;
import com.org.task4.repository.CarRepository;
import com.org.task4.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class CarController {

	@Autowired
	CarRepository carRepository;

	@Autowired
	CarService carService;

	/**
	 *
	 * @param make
	 * @return ResponseEntity<Car>
	 */
	@GetMapping("/cars")
	public ResponseEntity<List<Car>> getAllCars(@RequestParam(required = false) String make) {
		try {
			List<Car> cars = carService.getAllCars(make);

			if (cars.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cars, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 *
	 * @param id
	 * @return ResponseEntity<Car>
	 */
	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable("id") long id) {
		Optional<Car> carData = carService.getCarById(id);

		if (carData.isPresent()) {
			return new ResponseEntity<>(carData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	/**
	 *
	 * @param car
	 * @return ResponseEntity<Car>
	 */
	@PostMapping("/cars")
	public ResponseEntity<Car> addCar(@RequestBody Car car) {
		try {
			Car carDetails =  carService.saveCar(car);
			return new ResponseEntity<>(car, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(  HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 *
	 * @param id
	 * @param car
	 * @return ResponseEntity<Car>
	 */
	@PutMapping("/cars/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable("id") long id, @RequestBody Car car) {
		Car _car = carService.updateCar(id,car);

		if (_car != null ) {

			return new ResponseEntity<>(_car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 *
	 * @param id
	 * @return ResponseEntity<HttpStatus>
	 */
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") long id) {
		try {
			boolean flag = carService.deleteCarById(id);
			if(flag)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping("/cars")
	public ResponseEntity<HttpStatus> deleteAllCars() {
		try {
			boolean flag = carService.deleteAllCars();
			if(flag)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
