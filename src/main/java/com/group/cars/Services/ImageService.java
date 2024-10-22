package com.group.cars.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.group.cars.Entities.Image;

public interface ImageService {

    // Upload an image and return the saved Image entity
    Image uploadImage(MultipartFile file) throws IOException;

    // Get image details by its ID
    Image getImageDetails(Long id) throws IOException;

    // Retrieve the image as a byte array by its ID
    ResponseEntity<byte[]> getImage(Long id) throws IOException;

    // Delete an image by its ID
    void deleteImage(Long id);

    // Upload an image for a specific car
    Image uploadImageForCar(MultipartFile file, Long carId) throws IOException;

    // Get a list of images associated with a specific car
    List<Image> getImagesByCar(Long carId);
}
