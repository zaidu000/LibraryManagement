<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<%
    List<Map<String, String>> issuedBooks = (List<Map<String, String>>) request.getAttribute("issuedBooks");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Issued Books</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Issued Books</h2>

    <% if (issuedBooks != null && !issuedBooks.isEmpty()) { %>
        <table class="table table-bordered table-striped mt-3">
            <thead class="table-dark">
                <tr>
                    <th>Book Name</th>
                    <th>Student Name</th>
                    <th>Issue Date</th>
                </tr>
            </thead>
            <tbody>
                <% for (Map<String, String> book : issuedBooks) { %>
                    <tr>
                        <td><%= book.get("bookName") %></td>
                        <td><%= book.get("studentName") %></td>
                        <td><%= book.get("issueDate") %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <div class="alert alert-warning mt-3">No issued books found.</div>
    <% } %>

    <a href="adminDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
</body>
</html>
