package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional()
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User u = em.getReference(User.class, userId);
            meal.setUser(u);
            em.persist(meal);
            return meal;
        } else if (em.createNamedQuery(Meal.UPDATE)
                .setParameter("id", meal.getId())
                .setParameter("user_id", userId)
                .setParameter("calories", meal.getCalories())
                .setParameter("description", meal.getDescription())
                .setParameter("dateTime", meal.getDateTime())
                .executeUpdate() != 0) {
            return meal;
        }
        return null;

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return (Meal) em.createNamedQuery(Meal.GET)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.BETWEEN)
                .setParameter("user_id", userId)
                .setParameter("start", startDateTime)
                .setParameter("finish", endDateTime)
                .getResultList();
    }
}