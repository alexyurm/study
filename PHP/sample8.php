<?php
/*
An object is a data type stores data and information on how to process that data.
In PHP, an object must be explicitly declared with the keyword "class".




*/

class Car
{
   var $color;
   function Car($color="green")
   {
      $this->color = $color;
   }

   function what_color()
   {
      return $this->color;
   }
}

function print_vars($obj)
{
   foreach (get_object_vars($obj) as $prop => $val) //??
   {
      echo "\t$prop = $val\n";
   }
}

// instantiate one object
$herbie = new Car("white");

//show herbie properties
echo "\herbie: Properties\n";
print_vars($herbie);

?>
