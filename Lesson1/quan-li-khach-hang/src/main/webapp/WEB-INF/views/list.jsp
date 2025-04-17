<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>

<h2>Customer List</h2>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="customer" items="${customerList}">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.address}</td>
            <td>
                <a href="${pageContext.request.contextPath}/customers/edit?id=${customer.id}">Edit</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
