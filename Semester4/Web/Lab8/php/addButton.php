<?php 

    header("Access-Control-Allow-Headers: Content-Type");
	header("Access-Control-Allow-Origin: *");

    $postdata = file_get_contents("php://input");
	$postdata = json_decode($postdata);

    if(isset($postdata->Url) && isset($postdata->Description) && isset($postdata->Category)){

        $con = mysqli_connect("localhost","root","","lab7");
		if (!$con) {
		    die('Could not connect: ' . mysql_error());
			}

        $sql = "INSERT INTO sites(url, description, category) VALUES ('" .$postdata->Url. "','" .$postdata->Description. "','" .$postdata->Category. "');";
        
        $result = mysqli_query($con, $sql);
        if ($result>0) {
            echo json_encode("New record added successfully");
        } else {
            echo json_encode("Error!");
        }
        mysqli_close($con);
    }
    else{
        echo json_encode("You have to fill all of the textboxes below!");
    }
?>