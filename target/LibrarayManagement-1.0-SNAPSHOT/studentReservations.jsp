<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%
    ResultSet rs = (ResultSet) request.getAttribute("reservations");
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Reservations</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="mb-4">My Reserved Books</h2>

            <% if (message != null) {%>
            <div class="alert alert-success"><%= message%></div>
            <% } %>
            <% if (error != null) {%>
            <div class="alert alert-danger"><%= error%></div>
            <% } %>

            <% if (rs != null) { %>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Book ID</th>
                        <th>Book Name</th>
                        <th>Reserved Date</th>
                        <th>Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getInt("bookId")%></td>
                        <td><%= rs.getString("name")%></td>
                        <td><%= rs.getDate("reservedDate")%></td>
                        <td>
                             <form action="CancelReservationServlet" method="get" onsubmit="return confirm('Are you sure you want to cancel this reservation?');">
                                <input type="hidden" name="bookId" value="<%= rs.getInt("bookId") %>">
                                <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                        rs.close(); // Always close ResultSet when done
                    %>
                </tbody>
            </table>
            <% } else { %>
            <div class="alert alert-info">You have no active reservations.</div>
            <% }%>

            <a href="studentDashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
        </div>
    </body>
</html>
