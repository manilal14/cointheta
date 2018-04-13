<?php 
	
	include "db_config1.php";
	$conn = new mysqli($DB_HOST,$DB_USER,$DB_PASS,$DB_NAME);
	
	if($conn)
	{

		$json = $_POST['usersJsonArray'];
		$json_array = json_decode($json,true);

		for($i=0; $i<count($json_array);$i++)
		{
			$user_id = $json_array[$i]["user_id"];
			$name = $json_array[$i]["name"];
			$contact_no = $json_array[$i]["contact_no"];
			$address = $json_array[$i]["address"];

			$sql = "INSERT INTO users(user_id,name,contact_no,address) values('$user_id','$name','$contact_no','$address')";

			if(mysqli_query($conn,$sql))
			{
				echo json_encode(array('response'=>'CSV file content uploaded  uccessfully'));
			}
			
		}


	}

	else
	{
		echo json_encode(array('response'=>'connection Failed'));
	}

mysqli_close($conn);
	
?>