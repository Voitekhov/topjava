package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;

    public static final Meal MEAL_BREAKFAST = new Meal(MEAL_ID, LocalDateTime.of(2020, 04, 30, 0,
            0), "breakfast", 1000);
    public static final Meal MEAL_LANCH = new Meal(MEAL_ID + 1, LocalDateTime.of(2020, 04, 30, 0, 0),
            "Launch", 1500);
    public static final Meal MEAL_DINNER = new Meal(MEAL_ID + 2, LocalDateTime.of(2020, 06, 30, 0,
            0),
            "Launch", 1000);
    public static final Meal MEAL_ADMIN = new Meal(MEAL_ID + 3, LocalDateTime.of(2010, 11, 23, 0, 0),
            "Admin Launch", 1300);


    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "NEW MEAL", 777);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_LANCH);
        updated.setCalories(888);
        updated.setDescription("Updated");
        updated.setDateTime(LocalDateTime.of(2019, 10, 10, 0, 0));
        return updated;
    }

    /*public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("user", "roles").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }*/
}


