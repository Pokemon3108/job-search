<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>

<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <link rel="stylesheet" href="<c:url value="/css/header.css"/>" type="text/css">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">

        <%--    ${pageContext.request.servletPath}--%>

        <div class="container header-container">

            <a class="navbar-brand" href="#">Negotium</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/job/employee/vacancies">
                            <h5><fmt:message key="vacancies" bundle="${ rb }"/></h5>
                        </a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/job/employee/myVacancies">
                            <h5><fmt:message key="my_vacancies" bundle="${ rb }"/></h5>
                        </a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/job/employee/resume">
                            <h5><fmt:message key="resume" bundle="${ rb }"/></h5>
                        </a>
                    </li>
                </ul>

                <form action="${pageContext.request.contextPath}/job/auth/logout" method="post">
                    <input type="submit" class="btn btn-success" value=<fmt:message key="logout" bundle="${ rb }" /> />
                </form>

            </div>
        </div>

<%--        <form action="${pageContext.request.contextPath}/job/delete" method="post">--%>
            <button type="button" class="btn btn-danger mr-1" data-toggle="modal" data-target="#deleteModel">
                <fmt:message key="delete_acc" bundle="${ rb }"/>
            </button>
<%--        </form>--%>

            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModal">
                <fmt:message key="change_lan" bundle="${ rb }"/>
            </button>

    </nav>
</header>

<c:import url="/view/headers/localization.jsp"/>
<c:import url="/view/headers/delete-acc.jsp"/>



</body>

<script src=<c:url value="/js/language.js"/>>
</script>

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
