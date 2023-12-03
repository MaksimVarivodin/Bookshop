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
    <meta charset="UTF-8">
    <script type="application/javascript" src="${pageContext.request.contextPath}/resources/js/add-js-scripts.js"></script>

</head>
<body>
<a href="${pageContext.request.contextPath}/main-page">Home</a>
<h2>
    Literature By
    <a href="${pageContext.request.contextPath}/authors#${author.authorId}">${author.fullName}</a>
</h2>
<div class="item-rows">
    <c:forEach items="${literature}" var="book">
        <div class="container">
                <image-container>
                <img src="${book.bookCoverLink}" alt="${book.title      } photo"/>
                <input type="checkbox" id="info${book.literatureId}" class="info">
                <label for="info${book.literatureId}" class="main-info">
                    Відомості
                </label>
                <div class="additional-info">
                    <p>
                        Жанр: ${book.genre.genreName      }

                    </p>
                    <p>
                        Слів: ${book.words      }

                    </p>

                    <p>
                        Сторінок: ${book.pages      }

                    </p>

                    <p>
                            ${book.description}
                    </p>
                </div>
            </image-container>
            <h3>${book.price      } ₴</h3>

            <h4>${book.title      }</h4>
            <div class="buy-button"> Купити</div>
        </div>
    </c:forEach>
</div>

</body>

</html>
