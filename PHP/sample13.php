<?php

/*
Sort Array in Ascending Order, According to Key - ksort()
*/

$age = array("Peters"=>"35", "Ben"=>"37", "Joe"=>"43");
ksort($age);

foreach($age as $x=>$x_value) 
   {
   echo "Key=" . $x . ", value=" . $x_value;
   echo "<br>";
   }
?>
