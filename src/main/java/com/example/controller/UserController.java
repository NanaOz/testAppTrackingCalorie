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
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FoodIntakeService foodIntakeService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<?> getDailyReport(@PathVariable Long id, @RequestParam LocalDate date) {
        double totalCalories = foodIntakeService.getTotalCalories(id, date);
        User user = userService.findById(id);
        boolean isUnderLimit = totalCalories <= user.getDailyCalories();
        return ResponseEntity.ok(Map.of(
                "totalCalories", totalCalories,
                "isUnderLimit", isUnderLimit
        ));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<?> getIntakesHistory(@PathVariable Long id) {
        List<FoodIntake> intakes = foodIntakeService.getIntakesHistory(id);
        return ResponseEntity.ok(intakes);
    }

}
