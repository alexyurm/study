<!DOCTYPE html>
<html>
<body>

<?php

$x=5; //global scope

function myTest()
{
   $y=10;//local scope
   echo "<p>Test variables inside the function:<p>";
   echo "Variable x is: $x";
   echo "<br>";
   echo "Variable y is: $y";
}

myTest();
echo "<p>Test variables outside the function:<p>";
echo "Variable x is: $x";
echo "<br>";
echo "Variable y is: $y";
?>

</body>
</html>
