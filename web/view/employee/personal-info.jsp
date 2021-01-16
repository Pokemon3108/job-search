<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Personal info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="script" href="<c:url value="/js/personal-info-validation.js"/>">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body onload="selectCountry('${info.country.name}'); selectGender('${info.gender}')">

<c:import url="/view/headers/header-employee.jsp"/>

<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="pers_info" bundle="${ rb }"/></h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <form action="${pageContext.request.contextPath}/job/employee/changePersonalInfo" method="post" onsubmit="return validate(this);">

<%--                    <input type="hidden" name="page" value="${pageContext.request.servletPath}">--%>


                    <div class="form-group">
                        <label for="name"> <fmt:message key="name" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="name" value="${info.name}" name="name" required>
                    </div>


                    <div class="form-group">
                        <label for="surname"> <fmt:message key="surname" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="surname" value="${info.surname}" name="surname">
                    </div>

                    <div class="form-group">
                        <label for="birthday"><fmt:message key="birthday" bundle="${ rb }"/></label>
                        <input type="date" class="form-control" id="birthday" value="${info.birthday}" name="birthday">
                    </div>

                    <fmt:message key="gender" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="gender">
                        <c:forEach items="${genders}" var="genderArr">
                            <option name="gender"  value="${genderArr}">
                                <c:set var="g" value="${genderArr}" />
                                <fmt:message key="${fn:toLowerCase(g)}" bundle="${ rb }"/>
                            </option>
                        </c:forEach>
                    </select>

                    <fmt:message key="country" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="country">
                        <c:forEach items="${countries}" var="countryArr">
                            <option name="country" value="${countryArr.name}">${countryArr.name}</option>
                        </c:forEach>
                    </select>

                    <div class="form-group">
                        <label for="city"><fmt:message key="city" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="city" value="${info.city}" name="city">
                    </div>

                    <input type="hidden" name="id" value="${info.id}">

                    <div><input type="submit" class="btn btn-success" value="Save changes"/></div>
                </form>

            </div>
        </div>
    </div>
</div>

</body>

<script src=<c:url value="/js/personal-info-validation.js"/>>
</script>

</html>