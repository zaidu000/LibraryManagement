<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.model.Book" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    //System.out.println(books);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Books</title>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>

        <div class="container mt-4">
            <h2 class="mb-3">List of Books</h2>

            <%
                if (books == null || books.isEmpty()) {
            %>
            <div class="alert alert-warning">No books available.</div>
            <%
            } else {
            %>

            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Edition</th>
                        <th>Quantity</th>
                        <th>Parking Slot</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Book book : books) {%>
                    <tr>
                        <td><%= book.getBookId()%></td>
                        <td><%= book.getName()%></td>
                        <td><%= book.getAuthor()%></td>
                        <td><%= book.getEdition()%></td>
                        <td><%= book.getQuantity()%></td>
                        <td><%= book.getParkingSlot()%></td>
                        <td>
                            <a href="DeleteBookServlet?bookId=<%= book.getBookId()%>"
                               onclick="return confirm('Are you sure you want to delete this book?');"
                               class="btn btn-danger btn-sm">Delete</a>
                            <a href="GetBookForEditServlet?bookId=<%= book.getBookId()%>" class="btn btn-warning btn-sm">Edit</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <a href="adminDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
            <%
                }
            %>
        </div>

    </body>
</html>
