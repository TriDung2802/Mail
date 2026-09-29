<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>PaymentSuccessful</title>

<style>
    body {
        font-family: Arial;
        margin: 30px;
    }

    h2 {
        color: teal;
    }

    label {
        display: inline-block;
        width: 100px;
        font-weight: bold;
    }

    .info {
        margin-bottom: 8px;
    }

    .total {
        font-size: 18px;
        margin-top: 20px;
    }

    input[type="submit"] {
        padding: 6px 15px;
    }
</style>

</head>

<body>

<h2>Thanks for your purchase!</h2>

<p>Your payment was successful.</p>

<div class="info">
    <label>First Name:</label>
    <span>${user.firstName}</span>
</div>

<div class="info">
    <label>Last Name:</label>
    <span>${user.lastName}</span>
</div>

<div class="info">
    <label>Email:</label>
    <span>${user.email}</span>
</div>

<div class="total">
    <b>Total payment:</b>
    $<%= String.format("%.2f",
            (Double) request.getAttribute("total")) %>
</div>

<br>

<form action="index.jsp" method="get">
    <input type="submit" value="Return to Store">
</form>

</body>
</html>
