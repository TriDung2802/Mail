<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html>
<head>
    <title>Login</title>
<style>
    body {
        font-family: Arial;
        margin: 30px;
    }

    h2 {
        color: teal;
    }

    .form-box {
        width: 350px;
    }

    input[type="text"] {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
        margin-top: 5px;
        margin-bottom: 15px;
    }

    input[type="submit"] {
        padding: 6px 15px;
    }

    .error {
        color: red;
    }
</style>
</head>

<body>

<h2>Login</h2>

<form action="login" method="post">
<label>Email:</label><br>
<input type="text" name="email" required>
<label>Password:</label><br>
<input type="text" name="password" required>

<br>

<input type="submit" value="Login">
</form>

<br>

<a href="register.jsp">Don't have an account? Register</a>

</body>
</html>
