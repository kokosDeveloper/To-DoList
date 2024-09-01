<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/secure/tasks" method="POST">
    <div>
        <label>Название задачи
            <input name="title"/>
        </label>
    </div>
    <div>
        <label>Описание задачи
            <input name="description"/>
        </label>
    </div>
    <div>
        <label>Выполнено
           <!-- Скрытое поле для значения false -->

                       <input type="checkbox" name="completed" value="true"/>
        </label>
    </div>
    <div>
        <button type="submit">Сохранить задачу</button>
    </div>
</form>

<c:forEach var="userTask" items="${userTasks}">
    <div>Название: ${userTask.title}</div>
    <div>Описание: ${userTask.description}</div>
    <div>Статус:
        <c:if test="${userTask.completed}">выполнена</c:if>
        <c:if test="${!userTask.completed}">не выполнена</c:if>
    </div>
    <form>
        <input type="hidden" name="_method" value="DELETE">
        <input type="hidden" name="id" value="${userTask.id}">
        <button type="submit">Удалить задачу</button>
    </form>
</c:forEach>

</body>
</html>