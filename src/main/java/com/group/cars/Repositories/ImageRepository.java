package com.group.cars.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.group.cars.Entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
