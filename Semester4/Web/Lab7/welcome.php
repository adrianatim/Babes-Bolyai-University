<?php

session_start();
 
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: lab7.php");
    exit;
}
?>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body{ font: 14px sans-serif; text-align: center; }
        .button {
            border: none;
            color: white;
            padding: 16px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            transition-duration: 0.4s;
            cursor: pointer;
        }

        .button1 {
            background-color: white; 
            color: black; 
            border: 2px solid #a88beb;
        }

        .button1:hover {
            background-color: #a88beb;
            color: white;
        }
    </style>
</head>
<body>
    <h1 class="my-5">Hi, <b><?php echo htmlspecialchars($_SESSION["username"]); ?></b>. Welcome to our site.</h1>
    <div style="font-size: 16px"> Here you can ADD, UPDATE or REMOVE a Record!</div>
    <div style="font-size: 16px"> Or you can browse between Records!</div>
    <br>
    <div>
    <a href="./addButton.php" class="button button1">ADD</a>
    <a href="./updateButton.php" class="button button1">UPDATE</a>
    <a href="./deleteButton.php" class="button button1">REMOVE</a>
    </div>
    <br>

    <div>
    <a href="./browseButton.php" class="button button1">BROWSE</a>
    </div>
    <br><br>

    <h2 style="color: #a88beb">Enjoy!</h2>
    <p style='font-size:30px;'>&#128515;</p>
    
</body>
</html>
