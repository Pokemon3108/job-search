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
                <button value="${currentPage-1}" name="currentPage" >
                    <li>&#10094</li>
                </button>

            </c:if>

            <c:if test="${currentPage>2}">
                <button value="1" name="currentPage" >
                    <li>1</li>
                </button>
            </c:if>

            <c:if test="${currentPage>3}">
                <button>
                    <li>...</li>
                </button>

            </c:if>

            <c:if test="${currentPage>1}">
                <button value="${currentPage-1}" name="currentPage" >
                    <li>${currentPage-1}</li>
                </button>
            </c:if>

            <button class="is-active" value="${currentPage}" name="currentPage" >
                <li>${currentPage}</li>
            </button>

            <c:if test="${currentPage < maxPage}">
                <button value="${currentPage+1}" name="currentPage" >
                    <li>${currentPage+1}</li>
                </button>
            </c:if>

            <c:if test="${currentPage<maxPage-2}">
                <button>
                    <li>...</li>
                </button>
            </c:if>

            <c:if test="${currentPage<maxPage-1}">
                <button value="${maxPage}" name="currentPage" >
                    <li>${maxPage}</li>
                </button>
            </c:if>

            <c:if test="${currentPage<maxPage}">
                <button value="${currentPage+1}" name="currentPage" >
                    <li>&#10095</li>
                </button>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
