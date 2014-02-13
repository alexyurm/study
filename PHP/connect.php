<?php

//Create connection
$con=mysqli_connect("localhost", "root", "mysql", "predictify");

//Check connection
if (mysqli_connect_errno())
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
else
{
   $result = mysqli_query($con, "SELECT * FROM offers");
   while($row = mysqli_fetch_array($result))
   {
      echo $row['name'] . " " . $row['value'];
      echo "<br>";
   }

   //echo "Successfully connected to MySQL";
}

mysqli_close($con);

?>
