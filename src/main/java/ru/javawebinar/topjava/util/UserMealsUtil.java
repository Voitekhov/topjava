package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(0, 0),
                LocalTime.of(21, 0), 2005);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> daysMap = new HashMap();
        List<UserMealWithExcess> excessList = new ArrayList<>();
        for (UserMeal u : meals) {
            daysMap.merge(u.getDateTime().toLocalDate(), u.getCalories(), (oldV, newV) -> oldV + newV);
        }
        for (UserMeal u : meals) {
            if (TimeUtil.isBetweenHalfOpen(u.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean excessed = false;
                int dayCalories = daysMap.get(u.getDateTime().toLocalDate());
                if (dayCalories < caloriesPerDay) {
                    excessed = true;
                }
                excessList.add(new UserMealWithExcess(u.getDateTime(), u.getDescription(),
                        u.getCalories(), excessed));
            }
        }
        return excessList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> excessList = new ArrayList<>();

        final Map<LocalDate, Integer> daysMap = meals.stream().
                collect(Collectors.toMap(k -> k.getDateTime().toLocalDate(),
                        v -> v.getCalories(),
                        (v1, v2) -> v1 + v2));
        meals.stream()
                .filter(m -> TimeUtil.isBetweenHalfOpen(m.getDateTime().toLocalTime(),
                        startTime, endTime))
                .forEach(m -> excessList.add(new UserMealWithExcess(m.getDateTime()
                        , m.getDescription(), m.getCalories(),
                        daysMap.get(m.getDateTime().toLocalDate()) < caloriesPerDay)));
        return excessList;
    }
}
