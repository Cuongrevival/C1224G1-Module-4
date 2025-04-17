<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Từ điển Anh - Việt</title>
</head>
<body>
<h2>Tra từ điển Anh - Việt</h2>
<form action="${pageContext.request.contextPath}/search" method="post">
    <label for="word">Nhập từ tiếng Anh:</label>
    <input type="text" id="word" name="word" required />
    <button type="submit">Tra từ</button>
</form>
</body>
</html>
