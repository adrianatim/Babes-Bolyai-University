<?php 
    session_start();
    require_once "config.php";

    if(isset($_GET['DELETE'])){
        $category = $_GET['category'];
        if(empty($category)){
            $emptyTextBoxes ="<br><p style='color: red'>You have to fill all of the text boxes below!";
            echo $emptyTextBoxes;
        }

        $sql = "DELETE FROM sites WHERE category='$category'";
        if ($link->query($sql) === TRUE && empty($emptyTextBoxes)) {
            echo "<br><p style='color: #a88beb'>New record deleted successfully<p>";
        } else {
            echo "Error: " . $sql . "<br>" . $link->error;
        }
          
        $link->close();
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>deleteButton</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body{ font: 17px sans-serif; text-align: center; }
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
    <p>Fill the labels below with the date you want to delete from your database.</p>
    <form action="deleteButton.php" method="GET">
    <div>
    Category:<br> <input type="VARCHAR" name="category"><br><br>
    </div>
    <input type="submit" class="button button1" name="DELETE" value="DELETE">
    </form>
</body>
</html>
