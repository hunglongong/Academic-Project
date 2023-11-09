<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
// Check if all required fields are filled
    if (empty($_POST['bookName']) || empty($_POST['bookPrice']) || empty($_POST['yearPublished'])) {
        $error = "Please fill all the required fields.";
        header("Location: addBook.php?error=$error");
        exit();
    }
    else {
//store database information in variables
        $host = 'localhost';
        $username = 'ongh_admin';
        $password = '+z&E&70am+)^';
        $dbname = 'ongh_MyDatabase';

        $conn = mysqli_connect($host, $username, $password, $dbname);
        if (empty($conn)) {
            die ("CONNECTION FAILED: " . mysqli_connect_error());
        }

//read the values from the form submitted
        $bookName = $_REQUEST['bookName'];
        $bookPrice = $_REQUEST['bookPrice'];
        $yearPublished = $_REQUEST['yearPublished'];

//query to insert into tbLibrary table
        $query = "Insert into tbLibrary (bookName, bookPrice, yearPublished) values ('$bookName', '$bookPrice', '$yearPublished');";
        $result = mysqli_query($conn, $query);

        if ($result > 0) {

            // redirect back to addBook.php page, along with the query string 'result'
            header("Location:addBook.php?result=success");
        } else {
            // redirect back to addBook.php page, along with the query string 'result'
            header("Location:addBook.php?result=fail");
        }
    }
}
?>
