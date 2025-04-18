<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Các gia vị bạn đã chọn:</h2>
<ul>
  <c:forEach var="c" items="${selectedCondiments}">
    <li>${c}</li>
  </c:forEach>
</ul>
</body>
</html>
