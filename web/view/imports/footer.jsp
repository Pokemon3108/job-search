<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<footer class="text-center text-lg-start">

    <div class="text-center p-3" style="background-color: #40E0D0">
        <c:forEach items="${languages}" var="lang">
            <a class="text-dark font-weight-bold" onclick="changeLangByLink('${lang}');" href="#" > <fmt:message key="${lang.viewName}" bundle="${ rb }"/></a>
        </c:forEach>

        <p>
            © 2020 Copyright: Darya Zalevskaya
        </p>
    </div>
</footer>

<script src=<c:url value="/js/language.js"/>>
</script>

</body>
</html>
