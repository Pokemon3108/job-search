<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Skills</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<c:import url="/view/headers/header-employee.jsp"/>

<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="describeYourself" bundle="${ rb }"/></h1>

    <form action="${pageContext.request.contextPath}/job/employee/changeSkills" method="post">

        <div class="alert alert-info" role="alert">
            <fmt:message key="length1000" bundle="${ rb }"/>
        </div>

        <c:if test='${invalidSkills==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="longSkills" bundle="${ rb }"/>
            </p>
        </c:if>

        <div class="form-group">
            <label for="skills"><fmt:message key="importantSkills" bundle="${ rb }"/></label>
            <textarea class="form-control" id="skills" rows="3" name="skills" maxlength="1000">${skills}</textarea>
        </div>

        <div>
            <input type="submit" class="btn btn-success" value=
                    <fmt:message key="saveChange" bundle="${ rb }"/>/>
        </div>
    </form>

    <button class="btn btn-secondary mt-1"
            onclick="window.location.href = '${pageContext.request.contextPath}/job/employee/resume';">
        <fmt:message key="cancel" bundle="${ rb }"/>
    </button>

</div>

</body>

</html>
