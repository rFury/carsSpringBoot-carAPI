package com.group.cars.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.group.cars.Entities.Car;
import com.group.cars.Entities.Image;
import com.group.cars.Repositories.CarRepository;
import com.group.cars.Repositories.ImageRepository;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private carServices carService;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        return imageRepository.save(
            Image.builder()
                 .name(file.getOriginalFilename())
                 .type(file.getContentType())
                 .image(file.getBytes())
                 .build()
        );
    }

    @Override
    public Image getImageDetails(Long id) throws IOException {
        Optional<Image> dbImageOpt = imageRepository.findById(id);
        if (dbImageOpt.isPresent()) {
            Image dbImage = dbImageOpt.get();
            return Image.builder()
                        .idImage(dbImage.getIdImage())
                        .name(dbImage.getName())
                        .type(dbImage.getType())
                        .image(dbImage.getImage())
                        .build();
        } else {
            throw new IOException("Image not found with id: " + id);
        }
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException {
        Optional<Image> dbImageOpt = imageRepository.findById(id);
        if (dbImageOpt.isPresent()) {
            Image dbImage = dbImageOpt.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(dbImage.getType()))
                    .body(dbImage.getImage());
        } else {
            throw new IOException("Image not found with id: " + id);
        }
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image uploadImageForCar(MultipartFile file, Long carId) throws IOException {
        Optional<Car> carOpt = carRepository.findById(carId);
        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            Image image = Image.builder()
                               .name(file.getOriginalFilename())
                               .type(file.getContentType())
                               .image(file.getBytes())
                               .car(car)
                               .build();
            return imageRepository.save(image);
        } else {
            throw new IOException("Car not found with id: " + carId);
        }
    }

    @Override
    public List<Image> getImagesByCar(Long carId) {
        Optional<Car> carOpt = carRepository.findById(carId);
            return carOpt.get().getImages();
        
    }
}
