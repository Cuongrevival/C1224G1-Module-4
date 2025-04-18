<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Máy Tính Đơn Giản</title>
</head>
<body>
<h2>Máy Tính Đơn Giản</h2>
<form action="calculate" method="post">
  <input type="number" name="a" step="any" required />
  <input type="number" name="b" step="any" required />
  <button type="submit" name="operator" value="+">Addition(+)</button>
  <button type="submit" name="operator" value="-">Subtraction(-)</button>
  <button type="submit" name="operator" value="*">Multiply(*)</button>
  <button type="submit" name="operator" value="/">Division(/)</button>
</form>

<c:if test="${not empty error}">
  <p style="color: red;">${error}</p>
</c:if>

<c:if test="${not empty result}">
  <h3>Kết quả:</h3>
  <p>${a} ${operator} ${b} = ${result}</p>
</c:if>
</body>
</html>
