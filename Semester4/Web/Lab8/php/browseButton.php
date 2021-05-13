<?php
	header("Access-Control-Allow-Origin: *");
	$toSelect =[];
	parse_str($_SERVER['QUERY_STRING'],$toSelect);

	$con = mysqli_connect("localhost", "root","","lab7");
	if (!$con) {
		die('Could not connect: ' . mysqli_error());
	}

	if($toSelect['category']!=""){
		$result = mysqli_query($con, "SELECT * FROM sites where category='".$toSelect['category']."' LIMIT 4 OFFSET ".($toSelect["page"]-1)*4);
    }else{
		$result = mysqli_query($con, "SELECT * FROM sites LIMIT 4 OFFSET ".($toSelect["page"]-1)*4);
    }
	$arraySend = [];
	while($row =  mysqli_fetch_array($result))
	{
		$obj = (object)["Url"=>$row["url"],"Description"=>$row["description"],"Category"=>$row["category"]];
		array_push($arraySend, $obj);
	}
	echo json_encode(array_values($arraySend));
	mysqli_close($con);
