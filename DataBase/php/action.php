<?php
   //action.php: Add or remove a gift from the user's shopping list

   //Include database parameters and related functions
   require_once("db.php");

   //Check if the user logged in
   //(this also starts the session)
   logincheck();
   
   // Secure the user data
   if(count($_GET))
   {
      //Connect to the MySQL DBMS and use the wedding database - credentials
      //are in the file db.php
      if (!($connection= @mysqli_connect(
         $DB_hostname, $DB_username, $DB_password, $DB_databasename)))
         showerror($connection);
      
      $gift_id = clean($_GET['gift_id'], 5);
      $action = clean($_GET['action'], 6);

      //Is this action something we know about?
      if ($action != "add" && $action != "remove")
      {
         //No, it's not; perhaps someone's trying to manipulate the
         //URL query string?
         die("Unknown action:".$action);
         
         
      }
   }

?>
