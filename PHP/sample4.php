<?php
   /*Sometimes we don't want a local variable not to be deleted. To do this, use the static keyword when you first
      declare the variable:
   */

function myTest()
{
   static $x=0;
   echo $x;
   $x++;
}

myTest();
echo "<br>";
myTest();
echo "<br>";
myTest(); //Each time the function is called, that variable will still have the information it contained from the last time the function was called.
echo "<br>";
   
?>
