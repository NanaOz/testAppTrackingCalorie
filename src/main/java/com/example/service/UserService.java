package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        validateUser(user);
        user.setDailyCalories(calculateCalories(user));
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    private void validateUser(User user) {
        if (user.getWeight() < 20 || user.getWeight() > 200) {
            throw new IllegalArgumentException("Недопустимый вес");
        }
        if (user.getHeight() < 100 || user.getHeight() > 250) {
            throw new IllegalArgumentException("Недопустимый рост");
        }
    }

    private double calculateCalories(User user) {
        double bmr;
        if (user.getGoal().equals("мужчина")) {
            bmr = 66 + (6.2 * user.getWeight()) + (12.7 * user.getHeight()) - (6.8 * user.getAge());
        } else {
            bmr = 655 + (4.35 * user.getWeight()) + (4.7 * user.getHeight()) - (4.7 * user.getAge());
        }

        double activityFactor = getActivityFactor(user.getGoal());
        double goalFactor = getGoalFactor(user.getGoal());
        return bmr * activityFactor * goalFactor;
    }

    private double getActivityFactor(String goal) {
        switch (goal) {
            case "малая":
                return 1.2;
            case "средняя":
                return 1.55;
            case "высокая":
                return 1.725;
            default:
                return 1.0;
        }
    }

    private double getGoalFactor(String goal) {
        switch (goal) {
            case "похудение":
                return 0.8;
            case "поддержание":
                return 1.0;
            case "набор массы":
                return 1.2;
            default:
                return 1.0;
        }
    }
}

