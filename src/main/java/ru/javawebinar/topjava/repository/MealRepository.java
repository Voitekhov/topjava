package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

public interface MealRepository {
    public Meal get(int id);

    public void delete(int id);

    public void save(Integer id, Meal meal);



}
