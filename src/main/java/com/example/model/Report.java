package com.example.model;

import java.util.List;

public class Report {

        private int totalCalories;
        private List<FoodIntake> meals;

        public Report() {}

        public int getTotalCalories() {
            return totalCalories;
        }

        public void setTotalCalories(int totalCalories) {
            this.totalCalories = totalCalories;
        }

        public List<FoodIntake> getMeals() {
            return meals;
        }

        public void setMeals(List<FoodIntake> meals) {
            this.meals = meals;
        }
}
