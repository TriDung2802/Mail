<%@ page import="java.util.List" %>
<%@ page import="controller.CartServlet.CartItem" %>

<!DOCTYPE html>
<html>
<head>

    <title>Your Cart</title>

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
            width: 1000px;
        }

        th, td {
            border: 1px solid #777;
            padding: 8px;
        }

        th {
            text-align: left;
        }

        .quantity {
            width: 160px;
        }

        .price {
            width: 80px;
        }

        .amount {
            width: 100px;
        }

        .button {
            width: 150px;
            text-align: center;
        }

        input[type="number"] {
            width: 45px;
        }

        button {
            padding: 4px 8px;
        }

        .bottom {
            margin-top: 20px;
        }

    </style>

</head>

<body>

<h2>Your cart</h2>

<%
    List<CartItem> cartItems =
            (List<CartItem>) request.getAttribute("cartItems");
%>

<table>

    <tr>
        <th class="quantity">Quantity</th>
        <th>Description</th>
        <th class="price">Price</th>
        <th class="amount">Amount</th>
        <th class="button"></th>
    </tr>

<%
    if (cartItems == null || cartItems.isEmpty()) {
%>

    <tr>
        <td colspan="5">
            Your cart is empty.
        </td>
    </tr>

<%
    } else {

        for (CartItem item : cartItems) {
%>

    <tr>

        <td>

            <form action="cart" method="get">

                <input type="hidden"
                       name="action"
                       value="update">

                <input type="hidden"
                       name="id"
                       value="<%= item.product.id %>">

                <input type="number"
                       name="quantity"
                       value="<%= item.quantity %>"
                       min="1">

                <button type="submit">
                    Update
                </button>

            </form>

        </td>

        <td>
            <%= item.product.description %>
        </td>

        <td>
            $<%= String.format("%.2f", item.product.price) %>
        </td>

        <td>
            $<%= String.format("%.2f", item.getAmount()) %>
        </td>
        <td>
            <a href="cart?action=remove&id=<%= item.product.id %>">
                <button type="button">
                    Remove Item
                </button>
            </a>
        </td>
    </tr>
<%
        }
    }
%>

</table>

<br>

<%
    if (cartItems != null && !cartItems.isEmpty()) {
%>

<p>
    <b>To change the quantity</b>,
    enter the new quantity and click on the Update button.
</p>

<%
    }
%>

<div class="bottom">

    <a href="index.jsp">
        <button>Continue Shopping</button>
    </a>
    &nbsp;
    <a href="cart?action=checkout">
        <button>Checkout</button>
    </a>

</div>
</body>
</html>