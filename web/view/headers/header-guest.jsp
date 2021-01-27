<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>

<head>
    <title></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>" type="text/css">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">

        <div class="header-container">

            <div class="collapse navbar-collapse" id="show-vac">
                <a class="navbar-brand home" href="${pageContext.request.contextPath}/">
                    <h3>Negotium</h3>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=1">
                            <h5><fmt:message key="vacancies" bundle="${ rb }"/></h5>
                        </a>
                    </li>
                </ul>

                <form action="${pageContext.request.contextPath}/job/registration" method="get">
                    <button type="submit" class="btn btn-success mr-1 ml-5">
                        <fmt:message key="register" bundle="${ rb }"/>
                    </button>
                </form>

                <form action="${pageContext.request.contextPath}/job/login" method="get">
                    <button type="submit" class="btn btn-success mr-1 ">
                        <fmt:message key="login" bundle="${ rb }"/>
                    </button>
                </form>

                <button type="button" class="btn lang-button" data-toggle="modal" data-target="#exampleModal">
                    <fmt:message key="changeLan" bundle="${ rb }"/>
                </button>

            </div>
        </div>

    </nav>
</header>

<c:import url="/view/headers/localization.jsp"/>

</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</html>
