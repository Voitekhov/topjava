package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealRepositoryInMemorry implements MealRepository {
    @Override
    public Meal get(int id) {
        return MealsUtil.MEALS.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(int id) {
        MealsUtil.MEALS.remove(get(id));
    }

    @Override
    public void save(Integer id, Meal meal) {
        if (id == null) {
            id = ++MealsUtil.ID_COUNTER;
            MealsUtil.MEALS.add(new Meal(id, meal.getDateTime(),
                    meal.getDescription(), meal.getCalories()));
        } else {
            int index = MealsUtil.MEALS.indexOf(get(id));
            MealsUtil.MEALS.set(index, meal);
            //delete(id);
        }

    }

}
