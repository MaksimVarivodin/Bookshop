<%--
  Created by IntelliJ IDEA.
  User: maksy
  Date: 27.10.2023
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Literature</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/home">Home</a>
<h2>
    Literature By ${author.fullName}
</h2>
<table>
    <thead>
    <tr>
        <th>Pages</th>
        <th>Words</th>
        <th>Title</th>
        <th>Price</th>
        <th>Description</th>
        <th>Book Cover</th>
        <th>Genre</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${literature}" var="book">
        <tr>
                <%--
                    literatureId
                    pages
                    words
                    title
                    price
                    description
                    pictureLink
                    author
                    genre
                    --%>
            <td>${book.pages      }</td>
            <td>${book.words      }</td>
            <td>${book.title      }</td>
            <td>${book.price      }</td>
            <td>${book.description}</td>
            <td>
                <img src="${book.bookCoverLink}" alt="${book.title      } photo"/>
            </td>
            <td>${book.genre.genreName      }</td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>
