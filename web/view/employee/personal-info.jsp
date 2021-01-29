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

<c:import url="/view/imports/header-employee.jsp"/>

<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="persInfo" bundle="${ rb }"/></h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <form action="${pageContext.request.contextPath}/job/employee/changePersonalInfo" method="post"
                      onsubmit="return validate(this);">

                    <fmt:message key="required_field" bundle="${ rb }"/>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="nameError"></div>
                    <div class="alert alert-danger my-sm-3 " role="alert" id="nameLengthError"></div>

                    <c:if test='${invalidName==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidName" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="name"> <fmt:message key="name" bundle="${ rb }"/> *</label>
                        <input type="text" class="form-control" id="name" value="${info.name}" name="name" required>
                    </div>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="surnameError"></div>
                    <div class="alert alert-danger my-sm-3 " role="alert" id="surnameLengthError"></div>

                    <c:if test='${invalidSurname==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidSurname" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="surname"> <fmt:message key="surname" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="surname" value="${info.surname}" name="surname">
                    </div>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="birthdayError"></div>

                    <c:if test='${invalidBirthday==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidBirthday" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="birthday"><fmt:message key="birthday" bundle="${ rb }"/></label>
                        <input type="date" class="form-control" id="birthday" value="${info.birthday}" name="birthday">
                    </div>

                    <fmt:message key="gender" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="gender">
                        <c:forEach items="${genders}" var="genderArr">
                            <c:if test="${info.gender==genderArr}">
                                <option name="gender" value="${genderArr}" selected>
                                    <c:set var="g" value="${genderArr}"/>
                                    <fmt:message key="${fn:toLowerCase(g)}" bundle="${ rb }"/>
                                </option>
                            </c:if>
                            <c:if test="${info.gender!=genderArr}">
                                <option name="gender" value="${genderArr}">
                                    <c:set var="g" value="${genderArr}"/>
                                    <fmt:message key="${fn:toLowerCase(g)}" bundle="${ rb }"/>
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>


                    <fmt:message key="country" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="country">
                        <c:forEach items="${countries}" var="countryArr">
                            <c:if test="${info.country.id==countryArr.id}">
                                <option name="country" value="${countryArr.id}" selected>${countryArr.name}</option>
                            </c:if>
                            <c:if test="${info.country.name!=countryArr.name}">
                                <option name="country" value="${countryArr.id}">${countryArr.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="cityError"></div>
                    <div class="alert alert-danger my-sm-3 " role="alert" id="cityLengthError"></div>

                    <c:if test='${invalidCity==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidCity" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="city"><fmt:message key="city" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="city" value="${info.city}" name="city">
                    </div>

                    <input type="hidden" name="id" value="${info.id}">

                    <div>
                        <input type="submit" class="btn btn-success" value=
                                <fmt:message key="saveChange"
                                             bundle="${ rb }"/>/>


                    </div>

                </form>

                <button class="btn btn-secondary mt-1"
                        onclick="window.location.href = '${pageContext.request.contextPath}/job/employee/resume';">
                    <fmt:message key="cancel" bundle="${ rb }"/>
                </button>


            </div>
        </div>
    </div>
</div>
<c:import url="/view/imports/footer.jsp"/>
</body>

<script src=<c:url value="/js/personal-info-validation.js"/>>
</script>

</html>
