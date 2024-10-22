package com.group.cars.Crtlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.group.cars.Entities.Car;
import com.group.cars.Entities.Family_Group;
import com.group.cars.Services.carServices;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarRestController {
    @Autowired
    carServices carServices;

    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getAllCars() {
        return carServices.getAllCars();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Car getCarById(@PathVariable("id") Long id) {
        Car car = carServices.getCar(id);
        return car;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Car createCar(@RequestBody Car Car) {
        return carServices.saveCar(Car);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Car updateCar(@RequestBody Car Car) {
        return carServices.updateCar(Car);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable("id") Long id) {
        carServices.deleteCarById(id);
    }

    @RequestMapping(value = "/FamilyGroups", method=RequestMethod.GET)
    public List<Family_Group> getAllFG() {
        return carServices.getAllFG();
    }
    

    @RequestMapping(value = "/FamilyGroup/{idFG}", method = RequestMethod.GET)
    public List<Car> getCarsByFGId(@PathVariable("idFG") Long idFG) {
        return carServices.findByFamilyGroupId(idFG);
    }

    @RequestMapping(value = "/FamilyGroups", method = RequestMethod.POST)
    public Family_Group createCar(@RequestBody Family_Group FG) {
        return carServices.saveFG(FG);
    }

    

}
