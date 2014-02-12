<!DOCTYPE html>
<html>
<body>

<form method="post" action="<?php echo $_SERVER['PHP_SELF'];?>">
Name: <input type="text" name="fname">
<input type="submit">
</form>

<?php
/*
PHP $_REQUEST is used to collect data after submitting an HTML form.

The example below shows a form within an input field and a submit button.
When a user submits the data by clicking on "Submit", the form data is sent
to the file specified in the action attribute of the <form> tag. In this example
, we point to this file itself for processing form data. If you with to use another
PHP file to process form data, replace that with the filename of your choice. Then,
we can use the super global variable $_REQUEST to collect the value of the input field.

*/


$name = $_REQUEST['fname'];
echo $name;
?>

</body>
</html>
