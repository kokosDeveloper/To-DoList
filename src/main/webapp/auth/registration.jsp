<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
    <form action="/auth/registration" method="POST">
        <div>
        <label>Логин:
            <input name="login" />
        </label>
        </div>
        <div>
        <label>Пароль:
             <input name="password" />
        </label>
        </div>
        <div>
        <button type="submit">Зарегистрироваться</button>
        </div>
        </form>
        <c:forEach var="loginErrorMessage" items="${loginErrorMessages}">
        <div>
                ${loginErrorMessage}
        </div>
        </c:forEach>
        <c:forEach var="passwordErrorMessage" items="${passwordErrorMessages}">
        <div>
                ${passwordErrorMessage}
        </div>
        </c:forEach>

</body>
</html>