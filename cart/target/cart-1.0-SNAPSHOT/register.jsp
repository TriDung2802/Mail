
<!DOCTYPE html>
<html>
<head>

    <title>Register</title>

    <style>

        body {
            font-family: Arial;
            margin: 30px;
        }

        h2 {
            color: teal;
        }

        .form-group {
            margin-bottom: 12px;
        }

        label {
            display: inline-block;
            width: 120px;
        }

        input {
            width: 250px;
            padding: 6px;
        }

        button {
            padding: 7px 15px;
            margin-top: 10px;
            cursor: pointer;
        }

    </style>

</head>

<body>

<h2>Register</h2>

<form action="register" method="post">

    <div class="form-group">
        <label>First Name:</label>
        <input type="text" name="firstName" required>
    </div>

    <div class="form-group">
        <label>Last Name:</label>
        <input type="text" name="lastName" required>
    </div>

    <div class="form-group">
        <label>Email:</label>
        <input type="email" name="email" required>
    </div>

    <div class="form-group">
        <label>Password:</label>
        <input type="password" name="password" required>
    </div>

    <div class="form-group">
        <label>Confirm Password:</label>
        <input type="password" name="confirmPassword" required>
    </div>

    <button type="submit">Register</button>

</form>

<br>

<a href="checkout.jsp">
    <button>Return Checkout</button>
</a>

</body>
</html>