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
<%--                <a href="${pageContext.request.contextPath}${paginationPath}${currentPage-1}">--%>
<%--                    <li>&#10094</li>--%>
<%--                </a>--%>
<%--                <button type="submit"><li>&#10094</li>--%>
<%--                <input type="hidden" name="currentPage" value="${currentPage-1}">--%>
<%--                <a href="${pageContext.request.contextPath}${paginationPath}" onclick="document.getElementById('formFilter').submit(); return false;">--%>
<%--                    <li>&#10094</li>--%>
<%--                </a>--%>

                <button value="${currentPage-1}" name="currentPage" >
                    <li>&#10094</li>
                </button>

            </c:if>

            <c:if test="${currentPage>2}">
<%--                <a href="${pageContext.request.contextPath}${paginationPath}1">--%>
<%--                    <li>1</li>--%>
<%--                </a>--%>
<%--                    <button type="submit"><li>1</li>--%>
<%--                <input type="hidden" name="currentPage" value="1">--%>
<%--                <a href="${pageContext.request.contextPath}${paginationPath}" onclick="document.getElementById('formFilter').submit(); return false;">--%>
<%--                    <li>1</li>--%>
<%--                </a>--%>

                <button value="1" name="currentPage" >
                    <li>1</li>
                </button>
            </c:if>

            <c:if test="${currentPage>3}">
                <a>
                    <li>...</li>
                </a>
<%--                        <button type="submit"><li>...</li>--%>

            </c:if>

            <c:if test="${currentPage>1}">
<%--                <a href="${pageContext.request.contextPath}${paginationPath}${currentPage-1}">--%>
<%--                    <li>${currentPage-1}</li>--%>
<%--                </a>--%>
<%--                            <button type="submit"><li>${currentPage-1}</li>--%>
<%--                <input type="hidden" name="currentPage" value="${currentPage-1}">--%>
<%--                                <a href="${pageContext.request.contextPath}${paginationPath}" onclick="document.getElementById('formFilter').submit(); return false;">--%>
<%--                                    <li>${currentPage-1}</li>--%>
<%--                                </a>--%>

                <button value="${currentPage-1}" name="currentPage" >
                    <li>${currentPage-1}</li>
                </button>
            </c:if>


<%--            <a class="is-active"--%>
<%--               href="${pageContext.request.contextPath}${paginationPath}${currentPage}">--%>
<%--                <li>${currentPage}</li>--%>
<%--            </a>--%>
<%--            <input type="hidden" name="currentPage" value="${currentPage}">--%>
<%--            <a class="is-active" href="${pageContext.request.contextPath}${paginationPath}" onclick="document.getElementById('formFilter').submit(); return false;">--%>
<%--                <li>${currentPage}</li>--%>
<%--            </a>--%>
<%--                                <button type="submit"><li>${currentPage}</li>--%>


            <button class="is-active" value="${currentPage}" name="currentPage" >
                <li>${currentPage}</li>
            </button>

            <c:if test="${currentPage < maxPage}">
<%--                <a href="${pageContext.request.contextPath}${paginationPath}${currentPage+1}">--%>
<%--                    <li>${currentPage+1}</li>--%>
<%--                </a>--%>
<%--                                    <button type="submit"><li>${currentPage+1}</li>--%>
<%--                <input type="hidden" name="currentPage" value="${currentPage+1}">--%>
<%--                <a href="${pageContext.request.contextPath}${paginationPath}${currentPage+1}" onclick="document.getElementById('formFilter').submit(); return false;">--%>
<%--                    <li>${currentPage+1}</li>--%>
<%--                </a>--%>

                <button value="${currentPage+1}" name="currentPage" >
                    <li>${currentPage+1}</li>
                </button>
            </c:if>

            <c:if test="${currentPage<maxPage-2}">
                <a>
                    <li>...</li>
                </a>
<%--                                        <button type="submit"><li>...</li>--%>
            </c:if>

            <c:if test="${currentPage<maxPage-1}">
<%--                <a href="${pageContext.request.contextPath}${paginationPath}${maxPage}">--%>
<%--                    <li>${maxPage}</li>--%>
<%--                </a>--%>
<%--                <input type="hidden" name="currentPage" value="${maxPage}">--%>
<%--                <a href="${pageContext.request.contextPath}${paginationPath}" onclick="document.getElementById('formFilter').submit(); return false;">--%>
<%--                    <li>${maxPage}</li>--%>
<%--                </a>--%>

                <button value="${maxPage}" name="currentPage" >
                    <li>${maxPage}</li>
                </button>
            </c:if>

            <c:if test="${currentPage<maxPage}">
<%--                <a href="${pageContext.request.contextPath}${paginationPath}${currentPage+1}">--%>
<%--                    <li>&#10095</li>--%>
<%--                </a>--%>
<%--                <input type="hidden" name="currentPage" value="${currentPage+1}">--%>
<%--                <a href="${pageContext.request.contextPath}${paginationPath}" onclick="document.getElementById('formFilter').submit(); return false;">--%>
<%--                    <li>&#10095</li>--%>
<%--                </a>--%>

                <button value="${currentPage+1}" name="currentPage" >
                    <li>&#10095</li>
                </button>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
