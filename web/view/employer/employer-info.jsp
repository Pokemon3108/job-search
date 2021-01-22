<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">--%>
</head>
<body>

<c:import url="/view/headers/header-employer.jsp"/>

<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="persInfo" bundle="${ rb }"/></h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <form action="${pageContext.request.contextPath}/job/employer/changeInfo" method="post">


                    <c:if test='${invalidCompany==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidCompany" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="companyName"> <fmt:message key="companyName" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="companyName" value="${employer.companyName}"
                               name="companyName" minlength="3" maxlength="50" required>
                    </div>


                    <fmt:message key="country" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="country">
                        <c:forEach items="${countries}" var="countryArr">
                            <c:if test="${employer.country.id==countryArr.id}">
                                <option name="country" value="${countryArr.id}" selected>${countryArr.name}</option>
                            </c:if>
                            <c:if test="${employer.country.name!=countryArr.name}">
                                <option name="country" value="${countryArr.id}">${countryArr.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <c:if test='${invalidCity==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidCity" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="city"><fmt:message key="city" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="city" maxlength="100" value="${employer.city}" name="city">
                    </div>

                    <input type="hidden" name="id" value="${employer.id}">

                    <div>
                        <input type="submit" class="btn btn-success mt-2" value=
                                <fmt:message key="saveChange"
                                             bundle="${ rb }"/>/>


                    </div>

                </form>

                <button class="btn btn-secondary mt-1"
                        onclick="window.location.href = '${pageContext.request.contextPath}/job/employer/home';">
                    <fmt:message key="cancel" bundle="${ rb }"/>
                </button>


            </div>
        </div>
    </div>
</div>


</body>
</html>
