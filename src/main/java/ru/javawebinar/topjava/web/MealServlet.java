package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("meal_list",
                MealsUtil.filteredByStreams(MealsUtil.MEALS,
                        LocalTime.of(10, 0), LocalTime.of(23, 0), 2005));
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
