package com.example.controller;

import com.example.model.FoodIntake;
import com.example.model.User;
import com.example.service.FoodIntakeService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private FoodIntakeService foodIntakeService;

    @GetMapping("/daily/{userId}")
    public ResponseEntity<?> getDailyReport(@PathVariable Long userId, @RequestParam LocalDate date) {
        double totalCalories = foodIntakeService.getTotalCalories(userId, date);
        User user = new UserService().findById(userId);
        boolean isUnderLimit = totalCalories <= user.getDailyCalories();
        // Возвращаем информацию об отчете
        return ResponseEntity.ok(Map.of(
                "totalCalories", totalCalories,
                "isUnderLimit", isUnderLimit
        ));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getIntakesHistory(@PathVariable Long userId) {
        List<FoodIntake> intakes = foodIntakeService.getIntakesHistory(userId);
        return ResponseEntity.ok(intakes);
    }
}

