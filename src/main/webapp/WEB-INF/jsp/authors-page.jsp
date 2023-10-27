<%--
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
    <link href="../style.css" rel="stylesheet"/>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/home">Home</a>
</header>
<table>
    <thead>
    <tr>
        <th>Author Id</th>
        <th>Full Name</th>
        <th>Birth Date</th>
        <th>Death Date</th>
        <th>Age</th>
        <th>Picture Link</th>
        <th>Biography In Short</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${authors}" var="author">
        <tr>
                <%--
                authorId
                fullName
                birthDate
                deathDate
                age
                pictureLink
                biographyInShort
                --%>
            <td>${author.authorId        }</td>
            <td>${author.fullName        }</td>
            <td>${author.birthDate       }</td>
            <td>${author.deathDate       }</td>
            <td>${author.age             }</td>
            <td><img src="${author.pictureLink     }" alt="${author.fullName        } photo"></td>
            <td>${author.biographyInShort}</td>
            <td>
                <a href="${pageContext.request.contextPath}/authors/view-literature/${author.authorId}">
                    List of Literature
                </a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
