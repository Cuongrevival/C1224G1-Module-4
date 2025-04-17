<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>

<h2>Edit Customer</h2>

<form action="${pageContext.request.contextPath}/customers/update" method="post">
    <input type="hidden" name="id" value="${customer.id}" />

    <label for="name">Name:</label><br/>
    <input type="text" id="name" name="name" value="${customer.name}" required/><br/><br/>

    <label for="email">Email:</label><br/>
    <input type="email" id="email" name="email" value="${customer.email}" required/><br/><br/>

    <label for="address">Address:</label><br/>
    <input type="text" id="address" name="address" value="${customer.address}" required/><br/><br/>

    <input type="submit" value="Update"/>
    <a href="${pageContext.request.contextPath}/customers">Cancel</a>
</form>

</body>
</html>
