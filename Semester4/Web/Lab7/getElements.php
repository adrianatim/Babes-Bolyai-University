<?php
session_start();
require_once "config.php";
$maxRecords = 4;

    if (isset($_GET['pageNo'])) {
        $pageNo = $_GET['pageNo'];
    } else {
        $pageNo = 1;
    }

    $NoOfRowsSql = "SELECT COUNT(*) FROM sites";
    $NoOfRows = mysqli_fetch_array($link->query($NoOfRowsSql))[0];
    $NoOfPages = ceil($NoOfRows / $maxRecords);

    if($pageNo > $NoOfPages)
        $pageNo = 1;
    if($pageNo < 1)
        $pageNo = $NoOfPages;

    $from = ($pageNo - 1) * $maxRecords;
    $query = "SELECT url, description, category FROM sites ORDER BY category DESC LIMIT " .$from .', '. $maxRecords . ';';
    $result = $link->query($query);

    if(mysqli_num_rows($result) > 0){
        echo "<table>";
        echo "<thead>";
        echo "<th>Url</th>";
        echo "<th>Description</th>";
        echo "<th>Category</th>";
        echo "</thead>";
        echo "<tbody>";
        while ($row = mysqli_fetch_array($result)){
            echo "<tr>";
            echo "<th>".$row['url']."</th>";
            echo "<th>".$row['description']."</th>";
            echo "<th>".$row['category']."</th>";
            echo "</tr>";
        }
        echo "</tbody>";
        echo "</table>";
    }
    mysqli_close($link);
?>