package com.example.controller;

import com.example.model.FoodIntake;
import com.example.repository.FoodIntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food-intakes")
public class FoodIntakeController {
    @Autowired
    private FoodIntakeRepository foodIntakeRepository;

    @PostMapping
    public ResponseEntity<FoodIntake> createFoodIntake(@RequestBody FoodIntake foodIntake) {
        return ResponseEntity.ok(foodIntakeRepository.save(foodIntake));
    }
}
