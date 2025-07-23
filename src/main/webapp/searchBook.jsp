<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="java.util.List" %>
<%
    List<Book> results = (List<Book>) request.getAttribute("searchResults");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Book</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="mb-4">Search Book</h2>

            <form action="SearchBookServlet" method="post" class="mb-4">
                <div class="input-group">
                    <input type="text" name="keyword" class="form-control" placeholder="Enter book title or author" required>
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </form>

            <% if (error != null) {%>
            <div class="alert alert-danger"><%= error%></div>
            <% } %>

            <% if (results != null && !results.isEmpty()) { %>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Book Name</th>
                        <th>Author</th>
                        <th>Edition</th>
                        <th>Quantity</th>
                        <th>Parking Slot</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Book book : results) {%>
                    <tr>
                        <td><%= book.getName()%></td>
                        <td><%= book.getAuthor()%></td>
                        <td><%= book.getEdition()%></td>
                        <td><%= book.getQuantity()%></td>
                        <td><%= book.getParkingSlot()%></td>
                        <td>
                            <% if (book.getQuantity() > 0) {%>
                            <form action="IssueBookServlet" method="post">
                                <input type="hidden" name="bookId" value="<%= book.getBookId()%>">
                                <button type="submit" class="btn btn-success btn-sm">Issue</button>
                            </form>
                            <% } else { %>
                            <span class="text-danger">Not Available</span>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else if (results != null) { %>
            <div class="alert alert-warning">No books found for the given keyword.</div>
            <% }%>

            <a href="studentDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
        </div>
    </body>
</html>
