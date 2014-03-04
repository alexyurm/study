<?php
   //Log out of the system by ending the session and load the main
   //page

   session_start();
   session_destroy();

   //Redirect to the main page
   $message = "Thank you for using this system - you have now logged out.";
   header("Location: index.php?message=" . urlencode($message));
?>

