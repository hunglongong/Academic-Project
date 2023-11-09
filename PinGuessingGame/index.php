<?php
session_start();
/*Hung Long Ong - April 03, 2023 - ID: 991689997 */
$userInput1 = "";
$userInput2 = "";
$userInput3 = "";
$readOnly1 = false;
$readOnly2 = false;
$readOnly3 = false;

if (!isset($_SESSION['randomPw'])) {
    $randomPw = array();
    for ($i = 0; $i < 3; $i++) {
        $randomPw[$i] = rand(0, 9);
    }
    $_SESSION['randomPw'] = $randomPw;
}
//check if submitted or not
    if (isset($_POST['guessPw'])) {
        $guessPw = $_POST['guessPw'];
        $correctCount = 0;
//check for number of correct guess 0 to 3
        for ($i = 0; $i < 3; $i++) {
            if ($guessPw[$i] == $_SESSION['randomPw'][$i]) {
                $correctCount++;
            }
        }
//if guess all 3 digits correct, case open
        if ($correctCount == 3) {
            $message = "Correct Password, you have unlocked the briefcase!";
            $briefcaseImage = "open.png";
        } else {
            $message = "Incorrect Password, $correctCount out of 3 digits are correct!";
            $briefcaseImage = "close.jpg";
        }
    } else {
        $message = "Enter the 3-digit password to unlock the briefcase. Combination: " . implode("-", $_SESSION['randomPw']);
        $briefcaseImage = "close.jpg";
    }

    if (isset($_SESSION['randomPw']) && isset($_POST['guessPw'])) {
        if ($_SESSION['randomPw'][0] == $_POST['guessPw'][0]) {
            $userInput1 = $_SESSION['randomPw'][0];
            $readOnly1 = true;
        }
        if ($_SESSION['randomPw'][1] == $_POST['guessPw'][1]) {
            $userInput2 = $_SESSION['randomPw'][1];
            $readOnly2 = true;
        }
        if ($_SESSION['randomPw'][2] == $_POST['guessPw'][2]) {
            $userInput3 = $_SESSION['randomPw'][2];
            $readOnly3 = true;
        }
    }
//when button click, all input fields will be enabled and renew to blank also keep the session resume so password code don't change
    if(isset($_POST['btnClose'])){
        $userInput1 = "";
        $userInput2 = "";
        $userInput3 = "";
        $readOnly1 = false;
        $readOnly2 = false;
        $readOnly3 = false;
        $briefcaseImage = "close.jpg";
        $message = "Briefcase CLOSED. Same combination password already provided";
}
?>

<!DOCTYPE html>
<html lang="eng">
<head>
    <title>Unlock the Briefcase</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div id="container">
    <h1 class="msg"><?php echo $message; ?></h1>
    <form method="POST">
        <input type="text" name="guessPw[]" class="userIP" maxlength="1" size="1" pattern="[0-9]" required value="<?php echo $userInput1; ?>" <?php if($readOnly1) echo 'readonly'; ?>>
        <input type="text" name="guessPw[]" class="userIP" maxlength="1" size="1" pattern="[0-9]" required value="<?php echo $userInput2; ?>" <?php if($readOnly2) echo 'readonly'; ?>>
        <input type="text" name="guessPw[]" class="userIP" maxlength="1" size="1" pattern="[0-9]" required value="<?php echo $userInput3; ?>" <?php if($readOnly3) echo 'readonly'; ?>>
        <br>
        <input type="submit" value="Unlock" class="btn">
        <?php if ($correctCount == 3) echo '<button class="btn" name="btnClose">Close Briefcase</button>'; ?>
    </form>
    <img alt="briefcase" src="<?php echo $briefcaseImage; ?>" class="briefcaseIM">
</div>
</body>
</html>
