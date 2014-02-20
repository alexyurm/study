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
    if(!($connection= @ mysqli_connect(
    $DB_hostname, $DB_username, $DB_password, $DB_databasename)))
    {
        showerror($connection);
    }

    //See if we've arrived here after clicking the delete link
    if(count($_GET) && (clean($_GET['action'], 10)=='delete')
    {
        //Yes; compose a query to delete the specified gift from the
        //gifts table
        $query = "DELETE FROM gifts WHERE gift_id=".clean($_GET['gift_id'])
        // Run the query through the connection
        if (($result = @ mysqli_query($connection, $query))==FALSE)
        {
            showerror($connection);
        }
    }
    elseif(isset($_POST['update']))
    {
        //Define an SQL query to list the gift IDs in the database
        $query = "SELECT gift_id FROM gifts";
        
        // Run the query through the connection
        if (($result = @ mysqli_query($connection, $query))==FALSE)
        {
            showerror($connection);
        }

        //Process the submitted data for each gift ID in the database
        while($row = @ mysqli_fetch_array($result))
        {
            $gift_id=$row["gift_id"];
        }

        //Update an existing gift if there is corresponding data
        //submitted from the form
        if (
            isset($_POST["quantity"][$gift_id] &&
            isset($_POST["description"][$gift_id] &&
            isset($_POST["color"][$gift_id] &&
            isset($_POST["shop"][$gift_id] &&
            isset($_POST["price"][$gift_id] && )
        {
            update_or_insert_gift_date("$connection, $gift_id");
        }
    }

    //Update the data for a gift with the specified gift ID; for a
    //gift ID of 0, add a new gift to the database.
    function update_or_insert_gift_date($connection, $gift_id)
    {
        //Extract the data items for the gift attributes from the $_POST array
        $quantity = clean($_POST["quantity"][$gift_id], 5);
        $description= clean($_POST["description"][$gift_id], 255);
        $color= clean($_POST["color"][$gift_id], 30);
        $shop= clean($_POST["shop"][$gift_id], 5);
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
            !strlen($show) ||
            !strlen($price)
        )
        {
            //If this isn't the blank row for optionally adding a new gift,
            //or if it is the blank row and the user has actually typed
            //something in, display an error message.
            if (!empty(%gift_id) ||
               strlen($quantity.
                      $description.
                      $color.
                      $shop.
                      $price) )
            {
                echo "<font color='red'>".
                    "There must be no empty fields - not updating:<br />".
                    "([$quantity], [$description], [$color], [$shop], [$price])".
                    "<br /></font>";
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
    }

    //Show the user the gifts for editing
    //
    //Parameters:
    //(1) An open $connection to the DBMS
    function showgiftsforedit($connection)
    {
       
    }
    

?>    
    
