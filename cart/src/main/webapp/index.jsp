<!DOCTYPE html>
<html>
<head>
    <title>MY ONLINE STORE</title>

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
            width: 650px;
        }

        th, td {
            border: 1px solid gray;
            padding: 8px;
        }

        th {
            text-align: center;
        }

        .price {
            width: 80px;
            text-align: center;
        }

        .button {
            width: 120px;
            text-align: center;
        }

        button {
            padding: 3px 8px;
        }
    </style>
</head>

<body>

<h2>CD list</h2>

<table>

    <tr>
        <th>Description</th>
        <th class="price">Price</th>
        <th class="button"></th>
    </tr>

    <tr>
        <td>86 (the band) - True Life Songs and Pictures</td>
        <td class="price">$14.95</td>
        <td class="button">
            <a href="cart?action=add&id=P1">
                <button>Add To Cart</button>
            </a>
        </td>
    </tr>

    <tr>
        <td>Paddlefoot - The first CD</td>
        <td class="price">$12.95</td>
        <td class="button">
            <a href="cart?action=add&id=P2">
                <button>Add To Cart</button>
            </a>
        </td>
    </tr>

    <tr>
        <td>Paddlefoot - The second CD</td>
        <td class="price">$14.95</td>
        <td class="button">
            <a href="cart?action=add&id=P3">
                <button>Add To Cart</button>
            </a>
        </td>
    </tr>

    <tr>
        <td>Joe Rut - Genuine Wood Grained Finish</td>
        <td class="price">$14.95</td>
        <td class="button">
            <a href="cart?action=add&id=P4">
                <button>Add To Cart</button>
            </a>
        </td>
    </tr>

</table>

</body>
</html>