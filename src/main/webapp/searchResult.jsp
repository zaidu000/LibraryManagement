<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="java.util.List" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("searchResults");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Results</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="mb-4">Search Results</h2>

            <% if (error != null) {%>
            <div class="alert alert-danger"><%= error%></div>
            <% } %>

            <% if (books != null && !books.isEmpty()) { %>
            <table class="table table-striped">
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
                    <% for (Book book : books) {%>
                    <tr>
                        <td><%= book.getName()%></td>
                        <td><%= book.getAuthor()%></td>
                        <td><%= book.getEdition()%></td>
                        <td><%= book.getQuantity()%></td>
                        <td><%= book.getParkingSlot()%></td>
                        <td>
                            <% if (book.getQuantity() > 0) {%>
                            <form action="StudentIssueBookServlet" method="post">
                                <input type="hidden" name="bookId" value="<%= book.getBookId()%>">
                                <input type="hidden" name="membershipNo" value="<%= session.getAttribute("membershipNo") %>">
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
            <% } else if (books != null) { %>
            <div class="alert alert-warning">No matching books found.</div>
            <% } else { %>
            <div class="alert alert-info">Please use the search form first.</div>
            <% }%>

            <a href="searchBook.jsp" class="btn btn-primary mt-3">Back to Search</a>
            <a href="studentDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
        </div>
    </body>
</html>
