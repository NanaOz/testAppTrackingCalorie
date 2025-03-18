package com.example.service;

import com.example.model.FoodIntake;
import com.example.model.Meal;
import com.example.repository.FoodIntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodIntakeService {
    @Autowired
    private FoodIntakeRepository foodIntakeRepository;

    public double getTotalCalories(Long userId, LocalDate date) {
        List<FoodIntake> intakes = foodIntakeRepository.findByUser_IdAndDate(userId, date);
        return intakes.stream()
                .flatMap(intake -> intake.getMeals().stream())
                .mapToDouble(Meal::getCalories)
                .sum();
    }

    public List<FoodIntake> getIntakesByDate(Long userId, LocalDate date) {
        return foodIntakeRepository.findByUser_IdAndDate(userId, date);
    }

    public List<FoodIntake> getIntakesHistory(Long userId) {
        return foodIntakeRepository.findByUser_Id(userId);
    }
}

