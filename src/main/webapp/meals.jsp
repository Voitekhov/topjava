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
    <meta charset="UTF-8">
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>

    <title>Title</title>
</head>
<body>
<c:forEach var="meal" items="${meal_list}">
    <table>
        <c:if test="${meal.isExcess()==false}">
        <tr class="excess">
            </c:if>
            <c:if test="${meal.isExcess()}">
        <tr class="normal">
            </c:if>
            <td><c:out value="${meal.getDescription()}"/></td>
            <td><c:out value="${meal.getCalories()}"/></td>
            <td><c:out value="${meal.getFormattedDateTime()}"/></td>
            <td><a href="meals?action=update&id=${meal.getId()}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.getId()}">Delete</a></td>
        </tr>
    </table>
</c:forEach>
<td><a href="meals?action=create">Create</a></td>
</body>
</html>
