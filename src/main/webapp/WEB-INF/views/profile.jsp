<%@ page import="com.example.be_study.yugyeong.dto.UserProfileRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
</head>
<body>

<h1>User Profile</h1>

<p>
    Name: <span><%= ((UserProfileRequest) request.getAttribute("profile")).getUserName() %></span>
</p>

<p>
    Phone Number: <span><%= ((UserProfileRequest) request.getAttribute("profile")).getUserPhoneNumber() %></span>
</p>

<p>
    Age: <span><%= ((UserProfileRequest) request.getAttribute("profile")).getUserAge() %></span>
</p>

</body>
</html>
