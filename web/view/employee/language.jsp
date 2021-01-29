<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Language</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<c:import url="/view/imports/header-employee.jsp"/>


<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="foreignLanguage" bundle="${ rb }"/></h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <form action="${pageContext.request.contextPath}/job/employee/changeLanguage" method="post">

                    <fmt:message key="lang" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="language">
                        <c:forEach items="${allLanguages}" var="langArr">
                            <c:if test="${userLanguage.id==langArr.id}">
                                <option name="language" value="${langArr.id}" selected>${langArr.name}</option>
                            </c:if>
                            <c:if test="${language.id!=langArr.id}">
                                <option name="language" value="${langArr.id}">${langArr.name}</option>
                            </c:if>>
                        </c:forEach>
                    </select>


                    <fmt:message key="level" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="level">
                        <c:forEach items="${levels}" var="levelArr">
                            <c:if test="${userLanguage.level==levelArr}">
                                <option name="level" value="${levelArr}" selected>${levelArr}</option>
                            </c:if>
                            <c:if test="${userLanguage.level!=levelArr}">
                                <option name="level" value="${levelArr}">${levelArr}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <input type="hidden" name="lang_id" value="${userLanguage.id}">

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
</html>
