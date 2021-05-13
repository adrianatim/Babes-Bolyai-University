<?php 
    header("Access-Control-Allow-Headers: Content-Type");
	header("Access-Control-Allow-Origin: *");
	header("Access-Control-Allow-Methods: PUT");

	$postdata = file_get_contents("php://input");
	$postdata = json_decode($postdata);

    if(isset($postdata->Url) && isset($postdata->Description) && isset($postdata->Category)){

        $con = mysqli_connect("localhost","root","","lab7");
		if (!$con) {
		    die('Could not connect: ' . mysql_error());
			}

        $sql = "UPDATE sites SET url='" .$postdata->Url. "', description='" .$postdata->Description. "' WHERE category='" .$postdata->Category. "';";
        
        $result = mysqli_query($con, $sql);
        if ($result>0) {
            echo json_encode("Record updated successfully");
        } else {
            echo json_encode("Error!");
        }
        mysqli_close($con);
    }
    else{
        echo json_encode("You have to fill all of the textboxes below!");
    }
