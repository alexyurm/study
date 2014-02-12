<?php
/*
Comparison Operators:

== Equal: True if $x is equal to $y
=== identical: True if $x is equal to $y, and they are of the same type
<>: not equal
!==: not identical: True if $x is not equal to $y, or they are not of the same type
*/

$x=100; 
$y="100";

var_dump($x == $y);
echo "<br>";
var_dump($x === $y);
echo "<br>";
var_dump($x != $y);
echo "<br>";
var_dump($x !== $y);
echo "<br>";

$a=50;
$b=90;

var_dump($a > $b);
echo "<br>";
var_dump($a < $b);
?>
