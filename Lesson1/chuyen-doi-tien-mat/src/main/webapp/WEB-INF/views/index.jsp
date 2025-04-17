<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chuyển đổi tiền tệ</title>
</head>
<body>
<h2>Chuyển đổi từ USD sang VNĐ</h2>
<form action="convert" method="post">
    <label>Tỉ giá (VNĐ/USD): </label>
    <input type="number" step="0.01" name="rate" required><br><br>
    <label>Số USD: </label>
    <input type="number" step="0.01" name="usd" required><br><br>
    <button type="submit">Chuyển đổi</button>
</form>
</body>
</html>
