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
        <title>Add Book</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            .form-container {
                max-width: 600px;
                margin: auto;
                margin-top: 40px;
            }
        </style>
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Library Admin</a>
                <div class="collapse navbar-collapse justify-content-end">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="adminDashboard.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="LogoutServlet">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Form Section -->
        <div class="container form-container">
            <h2 class="text-center mb-4">📚 Add New Book</h2>

            <!-- Success/Error Message -->
            <%
                String message = (String) request.getAttribute("message");
                String error = (String) request.getAttribute("error");
                if (message != null) {
            %>
            <div class="alert alert-success"><%= message%></div>
            <% } else if (error != null) {%>
            <div class="alert alert-danger"><%= error%></div>
            <% }%>

            <form action="AddBookServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">Book Name</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Author</label>
                    <input type="text" name="author" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Edition</label>
                    <input type="text" name="edition" class="form-control">
                </div>
                <div class="mb-3">
                    <label class="form-label">Quantity</label>
                    <input type="number" name="quantity" class="form-control" required min="1">
                </div>
                <div class="mb-3">
                    <label class="form-label">Parking Slot</label>
                    <input type="text" name="parkingSlot" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">➕ Add Book</button>
            </form>
        </div>

    </body>
</html>
