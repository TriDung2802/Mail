<%@ page import="java.util.List" %>
<%@ page import="controller.CartServlet.CartItem" %>

<!DOCTYPE html>

<html>
<head>
<title>Checkout</title>

<style>

    body {
        font-family: Arial;
        margin: 30px;
    }

    h2 {
        color: teal;
    }

    table {
        border-collapse: collapse;
        width: 700px;
    }

    th, td {
        border: 1px solid gray;
        padding: 8px;
    }

    th {
        text-align: left;
    }

    .right {
        text-align: right;
    }

    .actions {
        margin-top: 20px;
    }

    .actions button,
    .actions input[type="submit"] {
        padding: 8px 16px;
        margin-right: 10px;
        font-size: 15px;
        cursor: pointer;
    }

    .payment {
        background-color: teal;
        color: white;
        border: none;
    }

</style>
</head>

<body>

<h2>Checkout</h2>

<%
List<CartItem> cartItems =
(List<CartItem>) request.getAttribute("cartItems");

Double total =
        (Double) request.getAttribute("total");

// L?u t?ng ti?n vào session ?? PaymentServlet s? d?ng
session.setAttribute("total", total);

// Ki?m tra ng??i dùng ?ã ??ng nh?p ch?a
Object user = session.getAttribute("user");

%>

<table>


<tr>
    <th>Description</th>
    <th>Quantity</th>
    <th>Amount</th>
</tr>
<%
for (CartItem item : cartItems) {
%>
<tr>

    <td>
        <%= item.product.description %>
    </td>

    <td>
        <%= item.quantity %>
    </td>

    <td>
        $<%= String.format("%.2f", item.getAmount()) %>
    </td>

</tr>

<%
}
%>

<tr>

    <td colspan="2" class="right">
        <b>Total:</b>
    </td>

    <td>
        <b>
            $<%= String.format("%.2f", total) %>
        </b>
    </td>

</tr>

</table>

<div class="actions">

<%
if (user == null) {
%>

<!-- Ch?a ??ng nh?p -->

<a href="login.jsp">
    <button>Login</button>
</a>

<a href="register.jsp">
    <button>Register</button>
</a>

<%
} else {
%>



<a href="logout">
    <button>Log out</button>
</a>

<%
}
%>

<a href="index.jsp">
    <button>Continue Shopping</button>
</a>
<!-- Thanh toán -->

<form action="payment" method="post" style="display:inline;">
    <input type="submit"
           value="Payment"
           class="payment">
</form>

</div>

</body>
</html>
