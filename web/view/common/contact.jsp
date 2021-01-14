<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Contact</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.role eq 'EMPLOYEE'}">
        <c:import url="/view/headers/header-employee.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role eq 'EMPLOYER'}">
        <c:import url="/view/headers/header-employer.jsp"/>
    </c:when>
</c:choose>


<div class="container">
    <h1 class="text-center my-sm-3">Contact</h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-4">

                <form action="${pageContext.request.contextPath}/job/contact" method="post">

                    <div class="form-group">
                        <label for="number"> <fmt:message key="phone" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="number" value="${telephone}" name="number"
                               pattern="+{12}\d">
                    </div>

                    <div class="form-group">
                        <label for="email"><fmt:message key="email" bundle="${ rb }"/></label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                               value="${email}"
                               name="email"
                               placeholder=<fmt:message key="email" bundle="${ rb }"/>
                                       required>
                    </div>

                    <div class="form-group">
                        <label for="skype">Skype</label>
                        <input type="text" class="form-control" id="skype" value="${skype}" name="skype">
                    </div>

                    <div><input type="submit" class="btn btn-success" value="Save changes"/></div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
