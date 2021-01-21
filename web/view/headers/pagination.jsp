<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagination</title>
    <link rel="stylesheet" href="<c:url value="/css/pagination.css"/>" type="text/css">
</head>
<body>


<div class="container">
    <div class="pagination p1">
        <ul>
            <c:if test="${currentPage>1}">
                <a href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=${currentPage-1}">
                    <li>&#10094</li>
                </a>
            </c:if>

            <c:if test="${currentPage>2}">
                <a href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=1">
                    <li>1</li>
                </a>
            </c:if>

            <c:if test="${currentPage>3}">
                <a>
                    <li>...</li>
                </a>
            </c:if>

            <c:if test="${currentPage>1}">
                <a href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=${currentPage-1}">
                    <li>${currentPage-1}</li>
                </a>
            </c:if>


            <a class="is-active"
               href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=${currentPage}">
                <li>${currentPage}</li>
            </a>

            <c:if test="${currentPage < maxPage}">
                <a href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=${currentPage+1}">
                    <li>${currentPage+1}</li>
                </a>
            </c:if>

            <c:if test="${currentPage<maxPage-2}">
                <a>
                    <li>...</li>
                </a>
            </c:if>

            <c:if test="${currentPage<maxPage-1}">
                <a href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=${maxPage}">
                    <li>${maxPage}</li>
                </a>
            </c:if>

            <c:if test="${currentPage<maxPage}">
                <a href="${pageContext.request.contextPath}/job/showAllVacancies?currentPage=${currentPage+1}">
                    <li>&#10095</li>
                </a>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
