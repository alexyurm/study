<!DOCTYPE HTML>
<html>
 <head>
  <title>Jack and Jill's Wedding Gift Registry</title>
 </head>
<body bgcolor='LIGHTBLUE'>
<h2>Jack and Jill's Wedding Gift Registry</h2>
(if you've not logged in before, make up a username and password)
 <!--- <form action="process.php" method="POST"> --->
 <form action="<?php echo $_SERVER["PHP_SELF"];?>" method="POST">
 <br />Please enter a username: <input type="text"
 name="username" />
 <br />Please enter a password: <input type="password" name="password" />
 <br /><input type="submit" value="Log in" />
 </form>
</html>
