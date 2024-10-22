package com.group.cars.Crtlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.group.cars.Entities.Car;
import com.group.cars.Entities.Image;
import com.group.cars.Services.ImageService;
import com.group.cars.Services.carServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private carServices carService;

	@PostMapping("/uploadFS/{id}")
	public ResponseEntity<String> uploadImageToFS(@RequestParam("image") MultipartFile file,
			@PathVariable("id") Long id) throws IOException {
		Car car = carService.getCar(id);
		if (car != null) {
			car.setImagePath(id + ".jpg");
			Files.write(Paths.get(System.getProperty("user.home") + "/Pictures/Spring/" + car.getImagePath()),
					file.getBytes());
			carService.saveCar(car);
			return ResponseEntity.ok("Image uploaded successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
		}
	}

	@GetMapping(value = "/loadfromFS/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageFromFS(@PathVariable("id") Long id) throws IOException {
		Car car = carService.getCar(id);
		if (car != null) {
			return Files.readAllBytes(
					Paths.get(System.getProperty("user.home") + "/Pictures/Spring/" + car.getImagePath()));
		}
		return null; // Return null or throw an appropriate exception if the car is not found
	}

	@PostMapping("/upload")
	public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		return imageService.uploadImage(file);
	}

	@PostMapping("/uploadImageCar/{carId}")
	public Image uploadImageForCar(@RequestParam("image") MultipartFile file, @PathVariable("carId") Long carId)
			throws IOException {
		return imageService.uploadImageForCar(file, carId);
	}

	@GetMapping("/getImagesCar/{carId}")
	public List<Image> getImagesForCar(@PathVariable("carId") Long carId) {
		return imageService.getImagesByCar(carId);
	}

	@GetMapping("/get/info/{id}")
	public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
		return imageService.getImageDetails(id);
	}

	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
		return imageService.getImage(id);
	}

	// Delete an image
	@DeleteMapping("/delete/{id}")
	public void deleteImage(@PathVariable("id") Long id) {
		imageService.deleteImage(id);
	}

	// Update an image
	@PutMapping("/update")
	public Image updateImage(@RequestParam("image") MultipartFile file) throws IOException {
		return imageService.uploadImage(file);
	}
}
