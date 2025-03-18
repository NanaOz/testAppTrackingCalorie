package com.example.controller;

import com.example.model.Meal;
import com.example.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    @Autowired
    private MealRepository mealRepository;

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        return ResponseEntity.ok(mealRepository.save(meal));
    }

}
