<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Мои задачи</title>
    <style>
        /* Стиль для заголовка и кнопок */
        .main-container {
            text-align: center; /* Центрирование текста и элементов внутри контейнера */
            margin-bottom: 20px; /* Отступ снизу от заголовка и кнопок до задач */
        }
        .main-title {
            text-align: center; /* Центрирование текста */
            font-size: 36px; /* Размер шрифта */
            font-weight: bold; /* Жирный шрифт */
            margin-bottom: 20px; /* Отступ снизу */
        }
        /* Контейнер для задач */
        .task-container {
            width: 50%; /* Ширина контейнера задач */
            margin: 0 auto; /* Центрирование по горизонтали */
            text-align: left; /* Выравнивание текста внутри контейнера */
            border: 1px solid #ccc; /* Граница контейнера */
            padding: 20px; /* Отступы внутри контейнера */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Тень для красоты */
            margin-bottom: 20px; /* Отступ снизу между задачами */
            border-radius: 8px; /* Закругленные углы для рамок */
        }

        /* Стиль для выполненных задач */
        .task-completed {
            background-color: #d4edda; /* Светло-зеленый фон для выполненных задач */
            border-color: #c3e6cb; /* Зеленая рамка */
        }

        /* Стиль для невыполненных задач */
        .task-not-completed {
            background-color: #f8d7da; /* Светло-красный фон для невыполненных задач */
            border-color: #f5c6cb; /* Красная рамка */
        }

        .button-container {
            display: flex; /* Использование flexbox для кнопок */
            justify-content: center; /* Центрирование кнопок по горизонтали */
            gap: 10px; /* Расстояние между кнопками */
            margin-bottom: 20px; /* Отступ снизу после кнопок */
        }

        .success-message {
            color: green; /* Цвет сообщения об успехе */
            font-weight: bold; /* Жирный текст */
            margin-bottom: 20px; /* Отступ снизу */
        }
        .status-label {
                    font-weight: bold;
                }
        .radio-buttons {
                    margin-top: 10px;
                }
    </style>
</head>
<body>

<div class="main-container">
    <h1 class="main-title">Мои задачи</h1>

    <c:if test="${not empty sessionScope.successAdded}">
        <div class="success-message">${sessionScope.successAdded}</div>
        <c:remove var="successAdded" scope="session" />
    </c:if>
    <c:if test="${not empty sessionScope.successUpdated}">
            <div class="success-message">${sessionScope.successUpdated}</div>
            <c:remove var="successUpdated" scope="session" />
        </c:if>

    <div class="button-container">
        <form action="/secure/operations/add" method="GET">
            <button type="submit">Добавить задачу</button>
        </form>

        <form action="/secure/operations/update" method="GET">
            <button type="submit">Редактировать задачи</button>
        </form>
    </div>
</div>

<div class="task-list">
    <c:forEach var="userTask" items="${userTasks}">
        <c:choose>
            <c:when test="${userTask.completed}">
                <div class="task-container task-completed">
            </c:when>
            <c:otherwise>
                <div class="task-container task-not-completed">
            </c:otherwise>
        </c:choose>
            <div>Название: ${userTask.title}</div>
            <div>Описание: ${userTask.description}</div>
            <div class="radio-buttons">
                            <form action="/secure/operations/update" method="POST">
                                <input type="hidden" name="taskId" value="${userTask.id}"/>
                                <label class="status-label">Статус:</label>
                                <label>
                                    <input type="radio" name="completed" value="true" ${userTask.completed ? 'checked' : ''}
                                           onclick="this.form.submit();"/> Выполнено
                                </label>
                                <label>
                                    <input type="radio" name="completed" value="false" ${!userTask.completed ? 'checked' : ''}
                                           onclick="this.form.submit();"/> Не выполнено
                                </label>
                            </form>
                        </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
