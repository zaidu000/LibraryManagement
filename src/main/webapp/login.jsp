<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Login</h2>
            <form action="LoginServlet" method="post">
                <div class="mb-3">
                    <label>Membership Number:</label><input type="text" name="membershipNo" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label>Password:</label><input type="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-success">LogIn</button>
            </form>
        </div>
    </body>
</html>