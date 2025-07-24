<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Issue Status</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="mb-4">Book Issue Result</h2>

            <%
                String message = (String) request.getAttribute("message");
                String error = (String) request.getAttribute("error");
            %>

            <% if (message != null) {%>
            <div class="alert alert-success"><%= message%></div>
            <% } else if (error != null) {%>
            <div class="alert alert-danger"><%= error%></div>
            <% } else { %>
            <div class="alert alert-warning">No information available.</div>
            <% }%>

            <a href="studentDashboard.jsp" class="btn btn-primary mt-3">Back to Dashboard</a>
            <a href="searchBook.jsp" class="btn btn-secondary mt-3">Search More Books</a>
        </div>
    </body>
</html>