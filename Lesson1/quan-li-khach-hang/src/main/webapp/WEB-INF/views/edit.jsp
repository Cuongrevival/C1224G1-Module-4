<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        form {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        /* Tạo style cho các trường input */
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        input[type="text"]:focus, input[type="email"]:focus {
            border-color: #4CAF50;
            outline: none;
        }

        /* Tạo kiểu cho nút submit */
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        /* Tạo kiểu cho các thông báo lỗi */
        .error {
            color: red;
            font-size: 14px;
            margin-bottom: 10px;
        }
    </style>
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
