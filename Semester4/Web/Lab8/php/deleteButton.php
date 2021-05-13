<?php 
    header("Access-Control-Allow-Headers: Content-Type");
	header("Access-Control-Allow-Origin: *");
	header("Access-Control-Allow-Methods: PUT");

    $postdata = file_get_contents("php://input");
	$postdata = json_decode($postdata);
    if(isset($postdata->Category)){

        $con = mysqli_connect("localhost","root","","lab7");
		if (!$con) {
		    die('Could not connect: ' . mysql_error());
			}

        $sql = "DELETE FROM sites WHERE category='".$postdata->Category."'";
        
        mysqli_query($con,$sql);
		if(mysqli_affected_rows($con)>0)
			echo json_encode("Record deleted successfully!");
		else
			echo json_encode("Error!");
          
        mysqli_close($con);
    }
    else{
        echo json_encode("You have to fill all of the textboxes below!");
    }
?>
