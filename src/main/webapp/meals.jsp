<%--
  Created by IntelliJ IDEA.
  User: voite
  Date: 19.04.2021
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="meal" items="${meal_list}">
    <table>
        <tr>
            <td><c:out value="${meal.getDescription()}"/></td>
            <td><c:out value="${meal.getCalories()}"/></td>
            <td><c:out value="${meal.getFormattedDateTime()}"/></td>
    </table>
</c:forEach>
</body>
</html>
