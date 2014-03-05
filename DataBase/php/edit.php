<?php
    //edit.php: Show the user the available gifts and the gifts in
    //their shopping list

    //Include database parameters and related functions
    require_once("db.php");
    
    //Check if the user is logged in
    //(this also starts the session)
    logincheck();

    //Check that the user Jack or Jill (username is 'jack' or 
    //'jill'); other users are not allowed to edit the gifts.
    if ( ($_SESSION['username'] != 'jack') && ($_SESSION['username'] != "jill"))
    {   
        $message = "You are not authorized to edit the gift details. Please ".
        "select gift suggestions from the list to add to your shopping list!";
        header("Location: list.php?message=".urlencode($message));
        exit;
    }

    //connect to the MySQL DBMS and use the wedding database - crenditials are
    //in the file db.php
    if(!($connection= @mysqli_connect(
    $DB_hostname, $DB_username, $DB_password, $DB_databasename)))
    {
        showerror($connection);
    }

    //See if we've arrived here after clicking the delete link
    if(count($_GET) && (clean($_GET['action'], 10)=='delete'))
    {
        //$message=$query;
        //header("Location: list.php?message=".urlencode($message));
        //Yes; compose a query to delete the specified gift from the
        //gifts table
        $query = "DELETE FROM gifts WHERE gift_id=".clean($_GET['gift_id'], 10);
        
        // Run the query through the connection
        if (($result = @mysqli_query($connection, $query))==FALSE)
        {
            showerror($connection);
        }
    }
    elseif(isset($_POST['update']))
    {
        //Check if a new item needs to be inserted
        if (
            isset($_POST["quantity"][0]) &&
            isset($_POST["description"][0]) &&
            isset($_POST["color"][0]) &&
            isset($_POST["shop"][0]) &&
            isset($_POST["price"][0])
        )
        {
            $gift_id = 0;
            update_or_insert_gift_data($connection, $gift_id);
        }

        //Define an SQL query to list the gift IDs in the database
        $query = "SELECT gift_id FROM gifts";
        
        // Run the query through the connection
        if (($result = @mysqli_query($connection, $query))==FALSE)
        {
            showerror($connection);
        }
        
        //Process the submitted data for each gift ID in the database
        while($row = @mysqli_fetch_array($result))
        {            
            $gift_id = $row["gift_id"];

            //Update an existing gift if there is corresponding data
            //submitted from the form
            if (
                isset($_POST["quantity"][$gift_id]) &&
                isset($_POST["description"][$gift_id]) &&
                isset($_POST["color"][$gift_id]) &&
                isset($_POST["shop"][$gift_id]) &&
                isset($_POST["price"][$gift_id])
                )
            {
                update_or_insert_gift_data($connection, $gift_id);
            }
        }
    } 

    //Update the data for a gift with the specified gift ID; for a
    //gift ID of 0, add a new gift to the database.
    function update_or_insert_gift_data($connection, $gift_id)
    {
        //Extract the data items for the gift attributes from the $_POST array
        $quantity = clean($_POST["quantity"][$gift_id], 5);
        $description= clean($_POST["description"][$gift_id], 255);
        $color= clean($_POST["color"][$gift_id], 30);
        $shop= clean($_POST["shop"][$gift_id], 10);
        $price= clean($_POST["price"][$gift_id], 5);
    
        //If the gift_id is 0, this is a new gift, so set the
        //gift_id to be empty; MySQL will automatically assign a 
        //unique gift_id to the new gift.
        if($gift_id==0)
        {
            $gift_id='';
        }

        //If any of the attributes are empty, don't update the database
        if(
            !strlen($quantity) ||
            !strlen($description) ||
            !strlen($color) ||
            !strlen($shop) ||
            !strlen($price)
        )
        {
            //If this isn't the blank row for optionally adding a new gift,
            //or if it is the blank row and the user has actually typed
            //something in, display an error message.
            if (!(empty($gift_id)) ||
               (strlen($quantity.
                      $description.
                      $color.
                      $shop.
                      $price) == 0))
            {   
                echo "<font color='red'>".
                    "There must be no empty fields - not updating:<br />".
                    "([$quantity], [$description], [$color], [$shop], [$price])".
                    "<br /></font>";
            }
        }
        else
        {
            //Add or update the gifts table
            $query = "REPLACE INTO gifts ".
            "(gift_id, description,shop,quantity,color,price,username) values (".
            "'$gift_id', '$description', '$shop', $quantity,
                '$color', '$price', NULL)";

            //Run the query through the connection
            if (@mysqli_query($connection, $query) == FALSE)
            {
                showerror($connection);
            }
        }
    }

    //Show the user the gifts for editing
    //
    //Parameters:
    //(1) An open $connection to the DBMS
    function showgiftsforedit($connection)
    { 
       //Create an HTML form pointing to the script
       echo "\n<form action='{$_SERVER["PHP_SELF"]}' method='POST'>";
       //Create an HTML table to neatly arrange the form inputs
       echo "\n<table border='1'>";

       //Create the table headings
       echo "\n<tr>" .
            "\n\t<th bgcolor='LIGHTGREEN'>ID</th>" .
            "\n\t<th bgcolor='LIGHTGREEN'>Description</th>" .
            "\n\t<th bgcolor='LIGHTGREEN'>Quantity</th>" .
            "\n\t<th bgcolor='LIGHTGREEN'>Color</th>" .
            "\n\t<th bgcolor='LIGHTGREEN'>Available from</th>" .
            "\n\t<th bgcolor='LIGHTGREEN'>Price</th>" .
            "\n\t<th bgcolor='LIGHTGREEN'>Delete?</th>" .
            "\n</tr>";
    
       // Create a SQL query to list the gifts in the database
       $query = "SELECT * FROM gifts ORDER BY description";
       
       // Run the query through the connection
       if (($result = @mysqli_query($connection, $query))==FALSE)
       {
           showerror($connection);
       }
       
       //Check if we found any gifts
       if(!mysqli_num_rows($result))
       {
           // No; display a notice
           echo "\n\t<tr><td colspan='7' align='center'>".
           "There are no gifts in the database</td></tr>";
       }
       else
       {
           //Yes, fetch the gift details a row at a time
           while($row = @mysqli_fetch_array($result))
           {
               //Compose the data for this gift into a row of form inputs
               //in the table
               //Add a delete link in the last column of the row.
               echo "\n<tr>" .
                    "\n\t<td>{$row["gift_id"]}</td>" .
                    "\n\t<td><input name='description[{$row['gift_id']}]' ".
                        "value='{$row["description"]}' size = '60' /></td>".
                    "\n\t<td><input name='quantity[{$row['gift_id']}]' ".
                        "value='{$row["quantity"]}' /></td>".
                    "\n\t<td><input name='color[{$row['gift_id']}]' ".
                        "value='{$row["color"]}' /></td>".
                    "\n\t<td><input name='shop[{$row['gift_id']}]' ".
                        "value='{$row["shop"]}' size='30' /></td>".
                    "\n\t<td><input name='price[{$row['gift_id']}]' ".
                        "value='{$row["price"]}' /></td>".
                    "\n\t<td><a href='{$_SERVER['PHP_SELF']}?".
                        "action=delete&gift_id={$row["gift_id"]}'>Delete</a></td>".
                    "\n</tr>";
           }
           
           //Display a row with blank form inputs to allow a gift to be added
           //the index of gift_id starts from '1', so we can reserve '0' as the index to insert new item
           
           echo "\n<tr><td>New Item</td>" .
                "\n\t<td><input name='description[0]' size='60' /></td>".
                "\n\t<td><input name='quantity[0]' /></td>".
                "\n\t<td><input name='color[0]' /></td>".
                "\n\t<td><input name='shop[0]' size='30' /></td>".
                "\n\t<td><input name='price[0]' /></td>".
                "\n</tr>";
           
           //End the table
           echo "\n</table>";
           
           //Display a submit button and end the form
           echo "\n<input name='update' type='submit' value='Update data' />";
           echo "</form>";
        }
    }
?>

<!DOCTYPE HTML PUBLIC
"-//W3C//DTD HTML 4.0 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Jack and Jill's Wedding Gift Registry</title>
</head>
<body bgcolor="LIGHTBLUE">

<?php
    // Show a logout link and a link to the main page
    echo "<a href='logout.php'>Logout</a> | <a href='list.php'>Gift list</a>";    
    
    echo "\n<h3>Gift editing page</h3>";
    
    // Show the existing gifts for editing
    showgiftsforedit($connection);
?>
</body>
</html>




    
