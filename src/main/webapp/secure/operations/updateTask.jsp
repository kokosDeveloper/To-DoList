<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование задач</title>
</head>
<body>
<c:if test="${not empty sessionScope.successUpdated}">
<div>${sessionScope.successUpdated}</div>
<c:remove var="successUpdated" scope="session" />
</c:if>
<c:forEach var="userTask" items="${userTasks}">
    <div>Название: ${userTask.title}</div>
    <div>Описание: ${userTask.description}</div>
    <div>Статус:
        <c:if test="${userTask.completed}">выполнена</c:if>
        <c:if test="${!userTask.completed}">не выполнена</c:if>
    </div>
    <form action="/secure/operations/update">
        <input type="hidden" name="_method" value="DELETE">
        <input type="hidden" name="id" value="${userTask.id}">
        <button type="submit">Удалить задачу</button>
    </form>
</c:forEach>
</body>
</html>