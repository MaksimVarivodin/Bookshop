<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="petprojects.bookshop.models.literatureinfrastructure.AuthorModel" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: maksy
  Date: 26.10.2023
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>View Authors</title>
    <meta charset="UTF-8">
    <script type="application/javascript" src="${pageContext.request.contextPath}/resources/js/add-js-scripts.js"></script>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/main-page">Home</a>
</header>

<div class="item-rows">
    <%
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy").withLocale(Locale.of("uk", "UA"));
    %>
    <c:forEach items="${authors}" var="author">
        <%
            LocalDate birth = ((AuthorModel) pageContext.getAttribute("author")).getBirthDate();
            LocalDate death = ((AuthorModel) pageContext.getAttribute("author")).getDeathDate();
        %>
        <div class="container" id="${author.authorId}">
            <image-container>
                <img src="${author.pictureLink     }" alt="${author.fullName        } photo"/>
                <input type="checkbox" id="info${author.authorId}" class="info">
                <label for="info${author.authorId}" class="main-info">
                    Біографія
                </label>
                <div class="additional-info">
<%--        "12-23-34" --%>
                    <p>Народився: <%= birth.format(formatter)%>
                    </p>
                    <p>Помер: <%=death.format(formatter)%>
                    </p>
                    <p>Прожив: ${author.age             } років</p>
                    <p>Біографія: ${author.biographyInShort}</p>
                </div>

            </image-container>
            <p>
                    ${author.fullName        }
            </p>

            <div>
                <a href="${pageContext.request.contextPath}/authors/${author.authorId}">
                    List of Literature
                </a>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
