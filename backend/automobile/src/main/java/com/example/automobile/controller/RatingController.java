package com.example.automobile.controller;

import com.example.automobile.entity.Rating;
import com.example.automobile.repository.RatingRepository;
import com.example.automobile.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "http://localhost:3000")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @PostMapping("/{vehicleId}")
    public Rating createRating(@PathVariable Long vehicleId, @RequestBody Rating rating) {
        return vehicleRepository.findById(vehicleId).map(vehicle -> {
            rating.setVehicle(vehicle);
            return ratingRepository.save(rating);
        }).orElseThrow(() -> new RuntimeException("Vehicle not found with id " + vehicleId));
    }
}
