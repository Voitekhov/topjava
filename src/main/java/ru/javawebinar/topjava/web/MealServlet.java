package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryInMemorry;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {
    MealRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new MealRepositoryInMemorry();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        int id = req.getParameter("id") == null ? -1 : Integer.parseInt(req.getParameter("id"));

        System.out.println(action);
        System.out.println(id);
        switch (action == null ? "all" : action) {
            case "update":
            case "create":
                Meal meal = repository.get(id);
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
                break;
            case "delete":
                repository.delete(id);
                resp.sendRedirect("meals");
                break;
            case "all":
            default:
                System.out.println("ALL!");
                req.setAttribute("meal_list",
                        MealsUtil.filteredByStreams(MealsUtil.MEALS,
                                LocalTime.MIN, LocalTime.MAX, 2005));
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = req.getParameter("id") == "" ? null : Integer.parseInt(req.getParameter(
                    "id"));

            String description = req.getParameter("description");
            LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
            int calories = Integer.parseInt(req.getParameter("calories"));
            Meal meal;
            if (id == null) {
                meal = new Meal(dateTime, description, calories);
            } else {
                meal = new Meal(id, dateTime, description, calories);
            }
            repository.save(id, meal);

            resp.sendRedirect("meals");
        } catch (Exception e) {

        }

    }
}
