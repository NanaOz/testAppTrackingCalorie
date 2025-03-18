package com.example.repository;

import com.example.model.FoodIntake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeRepository extends JpaRepository<FoodIntake, Long> {
    List<FoodIntake> findByUser_Id(Long userId);
    List<FoodIntake> findByUser_IdAndDate(Long userId, LocalDate date);
}
