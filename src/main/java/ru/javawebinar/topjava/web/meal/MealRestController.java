package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public class MealRestController {
    private MealService service;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public List<Meal> getAll(int userId) {
        log.info("getAll meal", userId);
        return service.getAll(userId);
    }

    public Meal get(int id, int userid) {
        log.info("get meal {}", id, userid);
        return service.get(id, userid);
    }

    public Meal create(Meal meal, int userId) {
        log.info("create meal {}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete meal {}", id, userId);
        service.delete(id, userId);
    }

    public void update(Meal meal, int id, int userId) {
        log.info("update {} with id={}", meal, userId);
        assureIdConsistent(meal, userId);
        service.update(meal, id, userId);
    }


}