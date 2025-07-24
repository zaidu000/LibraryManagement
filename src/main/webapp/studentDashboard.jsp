<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    response.setHeader("Pragma", "no-cache"); 
    response.setDateHeader("Expires", 0); 
    String studentName = (String) session.getAttribute("name");
    if (studentName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Dashboard</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            .btn-section .btn {
                width: 250px;
                margin: 10px;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Library Student</a>
                <div class="collapse navbar-collapse justify-content-end">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="LogoutServlet">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h2 class="text-center">Welcome, <%= studentName%> 📚</h2>

            <div class="d-flex flex-wrap justify-content-center btn-section mt-5">
                <a href="ViewIssuedBookStudentServlet" class="btn btn-primary">📄 View,♻ Renew & 📤 Return book</a>
                <a href="searchBook.jsp" class="btn btn-success">🔍 Search,📥 Issue and 📄Reserve Books</a>
                <a href="ViewReservationServlet" class="btn btn-info">My Reservations</a>
            </div>
        </div>
    </body>
</html>
