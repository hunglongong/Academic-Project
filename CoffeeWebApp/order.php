<!-- Hung Long Ong - March 25, 2023 - ID: 991689997 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LL Coffee's Orders</title>
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body class="order">
<h1>Thank You. Your LL Coffee's Orders are:</h1>

<?php
// Check if the form has been submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Validate user input
    $num_coffees = filter_input(INPUT_POST, 'num_coffees', FILTER_VALIDATE_INT, array('options' => array('min_range' => 1, 'max_range' => 40)));
    $coffee_size = filter_input(INPUT_POST, 'coffee_size', FILTER_SANITIZE_STRING);
    $num_cream = filter_input(INPUT_POST, 'num_cream', FILTER_VALIDATE_INT, array('options' => array('min_range' => 0, 'max_range' => 15)));
    $num_sugar = filter_input(INPUT_POST, 'num_sugar', FILTER_VALIDATE_INT, array('options' => array('min_range' => 0, 'max_range' => 15)));

    // Handle cases where input is empty or invalid
    if($num_cream == ""){
        $num_cream = 0;
    }
    if($num_sugar == ""){
        $num_sugar = 0;
    }

    // Prices for different coffee sizes
    $coffee_sizes_priceList = array(
        "small" => 1.99,
        "medium" => 2.49,
        "large" => 2.99,
        "extra-large" => 3.49
    );

    // Calculate cost of order
    $price_per_coffee = $coffee_sizes_priceList[$coffee_size];
    $subtotal = $num_coffees * $price_per_coffee;
    $hst = 0.13 * $subtotal;
    $total = $subtotal + $hst;

    // Display order details
    echo "<p style='font-size: 22px'>You have ordered $num_coffees $coffee_size coffee(s) with $num_cream cream(s) and $num_sugar sugar packet(s).</p>";
    echo "<p style='font-size: 22px'>The total cost ($price_per_coffee x $num_coffees) + 13% HST = <b>$" . number_format($total, 2) . "</b>.</p>";

    // Display images for cream and sugar, and coffee size
    if ($num_coffees > 0) {
        echo "<p>Coffee:</p>";
        echo "<span class='coffee-section'>";
        for ($i = 0; $i < $num_coffees; $i++) {
            // Show different image based on coffee size
            if($coffee_size == "small"){
                echo '<img src="hero_cup.png" alt="small-cup" class="small">';
            }
            else if($coffee_size == "medium"){
                echo '<img src="hero_cup.png" alt="medium-cup" class="medium">';
            }
            else if($coffee_size == "large"){
                echo '<img src="hero_cup.png" alt="large-cup" class="large">';
            }
            else if($coffee_size == "extra-large"){
                echo '<img src="hero_cup.png" alt="extra-large-cup" class="extra-large">';
            }
        }
        echo "</span>";
    //display cream images
    if ($num_cream > 0) {
        echo "<p>Cream:</p>";
        echo "<span class='cream-section'>";
        for ($i = 0; $i < $num_cream; $i++) {
            echo '<img src="cream.png" alt="Cream" class="cream-image">';
        }
        echo "</span>";
    }
    //display sugar images
    if ($num_sugar > 0) {
        echo "<p>Sugar Packets:</p>";
        echo "<span class='sugar-section'>";
        for ($i = 0; $i < $num_sugar; $i++) {
            echo '<img src="sugar.png" alt="Sugar" class="sugar-image">';
        }
        echo "</span>";
    }
} else {
        // Redirect back to index.php if form not submitted
        header("Location: index.php");
        exit();
    }
}
?>
</body>
</html>