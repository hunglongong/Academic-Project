<!-- Hung Long Ong - March 25, 2023 - ID: 991689997 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LL Coffee's Orders</title>
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body class="indexPage">
<h1>Welcome to LL Coffee!</h1>
<form action="order.php" method="post">
    <label id="labelFormHeader">You can order here &#8595;</label><br><br>
    <label for="num_coffees">Number of Coffees:</label>
    <input type="number" id="num_coffees" name="num_coffees" min="1" max="40" required><br><br>

    <label for="coffee_size">Size of Coffee:</label>
    <select id="coffee_size" name="coffee_size" required>
        <option value="small">Small</option>
        <option value="medium">Medium</option>
        <option value="large">Large</option>
        <option value="extra-large">Extra Large</option>
    </select><br><br>

    <label for="num_cream">Number of Cream:</label>
    <input type="number" id="num_cream" name="num_cream" min="0" max="15" value=""><br><br>

    <label for="num_sugar">Number of Sugar Packets:</label>
    <input type="number" id="num_sugar" name="num_sugar" min="0" max="15" value=""><br><br>

    <button type="submit">Order Coffee</button>
</form>
</body>
</html>
