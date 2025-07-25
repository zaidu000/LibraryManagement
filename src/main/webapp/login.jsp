<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5" style="max-width: 500px;">
        <h2 class="text-center mb-4">Login</h2>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="alert alert-danger" role="alert">
                <%= error %>
            </div>
        <%
            }
        %>

        <form action="LoginServlet" method="post">
            <div class="mb-3">
                <label>Membership Number:</label>
                <input type="text" name="membershipNo" class="form-control" required readonly onfocus="this.removeAttribute('readonly');">
            </div>
            <div class="mb-3">
                <label>Password:</label>
                <input type="password" name="password" class="form-control" required readonly onfocus="this.removeAttribute('readonly');">
            </div>
            <div class="mb-3">
                <label>Role:</label>
                <select name="role" class="form-control" required>
                    <option value="" selected disabled>Choose Role</option>
                    <option value="admin">Admin</option>
                    <option value="student">Student</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success w-100">Log In</button>
        </form>
    </div>
</body>
</html>
