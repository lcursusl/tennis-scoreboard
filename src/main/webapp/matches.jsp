<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="${pageContext.request.contextPath}/images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>

        <form method="get" action="${pageContext.request.contextPath}/matches">
            <div class="input-container">
                <input class="input-filter"
                       name="filter_by_player_name"
                       placeholder="Filter by name"
                       type="text"
                       value="${param.filter_by_player_name}"/>
                <div>
                    <button type="submit" class="btn-filter">Search</button>
                    <a href="${pageContext.request.contextPath}/matches">
                        <button type="button" class="btn-filter">Reset Filter</button>
                    </a>
                </div>
            </div>
        </form>

        <c:if test="${not empty error}">
            <div class="error-message" style="color: red; margin: 20px 0;">
                Error: ${error}
            </div>
        </c:if>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:choose>
                <c:when test="${not empty matches}">
                    <c:forEach var="match" items="${matches}">
                        <tr>
                            <td>${match.player1.name}</td>
                            <td>${match.player2.name}</td>
                            <td><span class="winner-name-td">${match.winner.name}</span></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="3" style="text-align: center; padding: 40px;">
                            No matches found
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>

        <c:if test="${totalPages > 1}">
            <div class="pagination">
                    <%-- Previous page --%>
                <c:if test="${currentPage > 1}">
                    <a class="prev"
                       href="?page=${currentPage - 1}<c:if test='${not empty param.filter_by_player_name}'>&filter_by_player_name=${param.filter_by_player_name}</c:if>">
                        &lt;
                    </a>
                </c:if>

                    <%-- Page numbers --%>
                <c:forEach begin="1" end="${totalPages}" var="pageNum">
                    <c:choose>
                        <c:when test="${pageNum == currentPage}">
                            <span class="num-page current">${pageNum}</span>
                        </c:when>
                        <c:otherwise>
                            <a class="num-page"
                               href="?page=${pageNum}<c:if test='${not empty param.filter_by_player_name}'>&filter_by_player_name=${param.filter_by_player_name}</c:if>">
                                    ${pageNum}
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                    <%-- Next page --%>
                <c:if test="${currentPage < totalPages}">
                    <a class="next"
                       href="?page=${currentPage + 1}<c:if test='${not empty param.filter_by_player_name}'>&filter_by_player_name=${param.filter_by_player_name}</c:if>">
                        &gt;
                    </a>
                </c:if>
            </div>
        </c:if>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from
            <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.
        </p>
    </div>
</footer>
</body>
</html>