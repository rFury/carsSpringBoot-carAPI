package com.group.cars.Entities.Projection;

import org.springframework.data.rest.core.config.Projection;

import com.group.cars.Entities.Car;

@Projection(name = "carModel", types = { Car.class })
public interface CarProjection {

    public String getCarModel();
    
}
