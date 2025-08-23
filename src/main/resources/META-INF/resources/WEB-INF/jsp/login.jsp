<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <div class = "container">
        <h1>Welcome User</h1>
        <form action="/login" method="post">
            Name: <input type="text" name="username" placeholder="Username" required>
            Password: <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Submit">
        </form>
        <pre>${errorMessage}</pre>
    </div>
</body>
</html>