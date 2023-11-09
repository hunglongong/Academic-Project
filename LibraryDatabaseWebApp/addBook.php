<!-- Hung Long Ong - April 9, 2023 - ID: 991689997 -->
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="main.css"/>
    <title>Add Books</title>
</head>
<body>

<h1>Insert Page</h1>

<a href="viewBook.php">Go To View Page &#8594;</a><br>

<h2>Add Book</h2>

<form action="addBookdb.php" method="POST">
    <table id="tableForm">
        <tr>
            <td>Book Name:</td>
            <td>
                <input type="text" name="bookName" required>
            </td>
        </tr>
        <tr>
            <td>Book Price:</td>
            <td>
                <input type="text" name="bookPrice" required pattern="[0-9]+(\.[0-9]+)?">
            </td>
        </tr>
        <tr>
            <td>Publish Year:</td>
            <td>
                <input type="text" name="yearPublished" required pattern="[0-9]{1,}">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add Book">
            </td>
        </tr>
    </table>
</form>

<?php
//validation for fields
if (isset($error)) {
    echo "<p>$error</p>";
}
// Check if 'result' variable exists in URI.
if(isset($_REQUEST['result'])) {

    // Check if value of Result is Success
    if ($_REQUEST['result'] == 'success') {
        echo "<p> Book Added Successfully! </p>";
    } // Check if value of Result is Success
    else if ($_REQUEST['result'] == 'fail') {
        echo "<p> Book Not Added. Please Try Again! </p>";
    }
}
?>
</body>
</html>
