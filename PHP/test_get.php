<html>
<body>


<?php

/*
This program is associated with sample16.php to demo the usage of PHP $_GET:

PHP $_GET can also be used to collect form data after submitting an HTML form with
method="get". $_GET can also collect data sent in the URL. Assume we have an HTML
page that contains a hyberlink with the parameters:

<a href="test_get.php?subject=PHP&web=W3schools.com">Test $GET</a>

When a user clicks on the link "Test $GET", the parameters "subject" and "web" is sent to "test_get.php",
 and you can then access their values in "test_get.php" with $_GET.
*/

echo "Study " . $_GET['subject'] . " at " . $_GET['web'];
?>
