<?php

/* The following example shows how to display different strings with the echo command (also notice that the strings can contain HTML markup)
   There are two basic ways to get output: echo and print.
   
   1) echo - can output one or more strings;
   2) print - can only output one string, and returns always1
*/



$txt1="Learn PHP";
$txt2="W3Schools.com";
$cars=array("Volvo", "BMW", "Toyota");

echo $txt1;
echo "<br>";
echo "Study PHP at $txt2";
echo "<br>";
echo "My car is a {$cars[0]}";

?>
