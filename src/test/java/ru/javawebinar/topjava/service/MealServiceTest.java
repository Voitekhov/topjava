package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(MEAL_LANCH.getId(), USER_ID);
        assertThat(meal).isEqualTo(MEAL_LANCH);

    }

    @Test
    public void delete() {
        mealService.delete(MEAL_LANCH.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.delete(MEAL_LANCH.getId(), USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
    }

    @Test
    public void getAll() {
        List<Meal> list = mealService.getAll(USER_ID);
        assertThat(list).isEqualTo(Arrays.asList(MEAL_BREAKFAST, MEAL_LANCH, MEAL_DINNER, MEAL_ADMIN));
    }

    @Test
    public void update() {
        //IF update meal with the same datetime
        Meal updated = getUpdated();
        mealService.update(getUpdated(), USER_ID);
        assertThat(mealService.get(MEAL_LANCH.getId(), USER_ID)).isEqualTo(updated);

    }

    @Test
    public void create() {
        Meal created = mealService.create(getNew(), USER_ID);
        int createdId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(createdId);
        assertThat(mealService.get(createdId,USER_ID)).isEqualTo(newMeal);
    }
}