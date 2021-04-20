<%--
  Created by IntelliJ IDEA.
  User: voite
  Date: 19.04.2021
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>DateTime:</dt>
        <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>

</body>
</html>
