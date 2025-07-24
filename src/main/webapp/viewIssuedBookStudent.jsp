<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    List<Map<String, String>> issuedBooks = (List<Map<String, String>>) request.getAttribute("issuedBooks");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Issued Books</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="mb-4">My Issued Books</h2>

            <% if (error != null) {%>
            <div class="alert alert-danger"><%= error%></div>
            <% } else if (issuedBooks == null || issuedBooks.isEmpty()) { %>
            <div class="alert alert-info">No books issued.</div>
            <% } else { %>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Book Name</th>
                        <th>Author</th>
                        <th>Issue Date</th>
                        <th>Due Date</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map<String, String> book : issuedBooks) {%>
                    <tr>
                        <td><%= book.get("name")%></td>
                        <td><%= book.get("author")%></td>
                        <td><%= book.get("issueDate")%></td>
                        <td><%= book.get("dueDate")%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% }%>

            <a href="studentDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
        </div>
    </body>
</html>
