<!DOCTYPE html>
<html>
    <head>
        <title>Admin Registration</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Admin Registration</h2>
            <form action="RegisterServlet" method="post">
                <div class="mb-3">
                    <label>Name:</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label>Email:</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label>Role:</label>
                    <input type="text" name="role" class="form-control" value="admin" readonly>
                </div>
                <div class="mb-3">
                    <label>Password:</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label>Address:</label>
                    <input type="text" name="address" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label>Library Name:</label>
                    <input type="text" name="libraryName" class="form-control" required>
                </div>
                <!-- Membership number is generated server-side -->
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
    </body>
</html>