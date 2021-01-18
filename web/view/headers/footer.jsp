<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Title</title>
    <link rel="script" href="<c:url value="/js/language.js"/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>

<footer class="bg-light text-center text-lg-start">

    <div class="text-center p-3" style="background-color: #40E0D0">
        <c:forEach items="${languages}" var="lang">
            <a class="text-dark"  href="${pageContext.request.contextPath}/job/changeLocale?lang=${lang}" > <fmt:message key="${lang.viewName}" bundle="${ rb }"/></a>
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
