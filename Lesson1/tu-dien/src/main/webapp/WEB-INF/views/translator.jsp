<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Kết quả tra từ</title>
</head>
<body>
<h2>Kết quả tra từ</h2>
<p><strong>Từ:</strong> ${word}</p>
<p><strong>Ý nghĩa:</strong> ${result}</p>
<br>
<a href="${pageContext.request.contextPath}/dictionary">⇦ Quay lại tra từ khác</a>
</body>
</html>