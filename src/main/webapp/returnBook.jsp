<%@ page import="java.util.*" %>
<%@ page session="true" %>

<div class="container mt-5">
    <h2>Return Issued Book</h2>

    <% String error = (String) request.getAttribute("error");
        String message = (String) request.getAttribute("message");
        if (error != null) {%>
    <div class="alert alert-danger"><%= error%></div>
    <% } else if (message != null) {%>
    <div class="alert alert-success"><%= message%></div>
    <% }%>

    <form method="post" action="ReturnBookServlet">
        <div class="form-group">
            <label for="id">Issued Book ID:</label>
            <input type="number" class="form-control" name="id" id="id" required>
        </div>
        <button type="submit" class="btn btn-danger mt-3">Return Book</button>
    </form>
</div>