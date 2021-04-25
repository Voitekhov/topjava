package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, ConcurrentHashMap<Integer, Meal>> repository =
            new ConcurrentHashMap<>();
    private final AtomicInteger mealCounter = new AtomicInteger(0);

    {
        repository.put(1, new ConcurrentHashMap<>());
        repository.put(2, new ConcurrentHashMap<>());
        repository.put(3, new ConcurrentHashMap<>());
        for (Meal m : MealsUtil.meals) {
            save(m, 1);
        }
    }


    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(mealCounter.incrementAndGet());
            meal.setUserId(userId);
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        } else {
            Map<Integer, Meal> map = containedMeal(meal.getId(), userId);
            if (map != null) {
                map.merge(meal.getId(), meal, (meal1, meal2) -> meal2);
                return meal;
            }
        }
        return null;
        // handle case: update, but not present in storage
        //return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> map = containedMeal(id, userId);
        if (map != null) {
            return map.get(id);
        }
        throw new NotFoundException("Meal with id:" + id + " not found ");
    }

    @Override
    public List<Meal> getAll(int userId) {
        ArrayList<Meal> listOfmeal = new ArrayList<>(repository.get(userId).values());
        listOfmeal.sort((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()) * -1);
        if (listOfmeal.size() > 0) {
            return listOfmeal;
        }
        return null;


    }

    private Map<Integer, Meal> containedMeal(int mealId, int userId) {
        Map<Integer, Meal> map = repository.get(userId);
        if (repository.get(userId).containsKey(mealId)) {
            return map;
        }
        return null;

    }
}

