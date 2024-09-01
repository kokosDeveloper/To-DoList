<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление задачи</title>
</head>
<body>
<form action="/secure/operations/add" method="POST">
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
</body>
</html>