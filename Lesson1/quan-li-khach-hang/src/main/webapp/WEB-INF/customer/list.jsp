<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    table {
        border: 1px solid #000;
    }

    th, td {
        border: 1px dotted #555;
    }
</style>
<table>
    <caption>Customers List</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${customerInfo}">
        <tr>
            <td>
                <c:out value="${c.getKey()}"/>
            </td>
            <td>
                <a>${c.getValue()}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>