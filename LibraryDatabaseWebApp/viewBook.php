<!-- Hung Long Ong - April 9, 2023 - ID: 991689997 -->
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="main.css"/>
    <title>View Books</title>
</head>
<body>

<h1>View Page</h1>

<a href="addBook.php">Go To Insert Page &#8594;</a><br>

<h2>View Books</h2>
<form action="" method="POST">
    <input type="text" name="search" placeholder="Search book name...">
    <input type="submit" name="submit" value="Search">
</form>

<table id="books">
    <tr>
        <th>ISBN</th>
        <th>Book Name</th>
        <th>Book Price</th>
        <th>Year Published</th>
    </tr>
    <?php
    //store database information in variables
        $host = 'localhost';
        $username = 'ongh_admin';
        $password = '+z&E&70am+)^';
        $dbname = 'ongh_MyDatabase';

        $conn = mysqli_connect($host, $username, $password, $dbname);
        if(empty($conn)) {die ("CONNECTION FAILED: " . mysqli_connect_error());}

        //query to select data from Library Table
        if(isset($_POST['submit'])) {
        $search_term = mysqli_real_escape_string($conn, $_POST['search']);
        $query = "SELECT * FROM tbLibrary WHERE bookName LIKE '%$search_term%'";
        } else {
        $query = "SELECT * FROM tbLibrary";
        }

        $result = mysqli_query($conn, $query); //execute query

        if(mysqli_num_rows($result) > 0){
            $i = 1;
            while ($row = mysqli_fetch_assoc($result)){
                echo "<tr>";
                echo "<td>$i</td>";
                echo "<td>" . $row['bookName'] . "</td>";
                echo "<td>" . $row['bookPrice'] . "</td>";
                echo "<td>" . $row['yearPublished'] . "</td>";
                echo "<tr>";
                $i++;
            }
        }
        else{
            echo "No matching records found!";
        }
    ?>

</table>

</body>
</html>
