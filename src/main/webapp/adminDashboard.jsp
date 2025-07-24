<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String adminName = (String) session.getAttribute("name");
    if (adminName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            .btn-section .btn {
                width: 200px;
                margin: 10px;
            }
        </style>
    </head>
    <body>

        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Library Admin</a>
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
            <h2 class="text-center">Welcome, <%= adminName%> 👋</h2>

            <!-- Buttons Section -->
            <div class="d-flex flex-wrap justify-content-center btn-section mt-5">
                <a href="addBook.jsp" class="btn btn-primary">Add Book</a>
                <a href="ViewBookServlet" class="btn btn-warning">Perform Actions</a>
<!--                <a href="ViewBookServlet" class="btn btn-danger">Delete Book</a>
                <a href="ViewBookServlet" class="btn btn-warning">Update/Edit Book</a>-->
                <a href="ViewIssuedBookServlet" class="btn btn-info">View Issued Books</a>
            </div>
        </div>

    </body>
</html>
