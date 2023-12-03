<%--
  Created by IntelliJ IDEA.
  User: maksy
  Date: 28.10.2023
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="application/javascript" src="${pageContext.request.contextPath}/resources/js/add-js-scripts.js"></script>
    <title>User ${user.userId}</title>
</head>
<body>

<div id="user-profile" class="item-rows">
    <div class="container">
        <image-container>
            <img src="${user.profilePictureLink}" alt="${user.firstName} ${user.lastName} photo"/>
            <input type="checkbox" id="info">
            <label for = "info" class="main-info">
                Додаткова інформація
            </label>
            <div class="additional-info">
                <p>E-mail: ${user.email}</p>

                <p>Activated: ${user.activated}</p>
                <p>Locked: ${user.locked}</p>
                <p>Phone: ${user.phone}</p>
                <p>Role: ${user.role}</p>
            </div>
        </image-container>
        <h3>First name: ${user.firstName}</h3>
        <h3>Second name: ${user.lastName}</h3>
    </div>
</div>

</body>
</html>
