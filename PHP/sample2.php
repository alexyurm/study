<?php
//Usage: show how to use global keyword to access a global variable from within a function.

$x=5;
$y=10;

function myTest()
{
   global $x, $y;
   $y=$x+$y; //Should output 15
}

myTest();
echo $y;
?>
