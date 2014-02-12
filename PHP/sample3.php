<?php
/*
   PHP also stores all global variables in an array called $GLOBALS[index]. The index holds the name of the variable.
   This array is also accessible from within functions and can be used to update global variables directly.
*/

$x=5;
$y=10;

function myTest()
{
   $GLOBALS['y']=$GLOBALS['x']+$GLOBALS['y'];
}

myTest();
echo $y;

?>
