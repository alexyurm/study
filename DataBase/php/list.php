<?php
    //list.php: Show the user the available gift and the gifts in
    //their shopping list.

    //Include the database
    require_once("db.php");
    
    //Check if the user login
    logincheck();
    
    //Show the user the gifts
    //
    //Parameter
    //(1) an open connection to the DBMS
    //(2) Whether to show the available gifts or the current user's
    //shopping list
    
    //Define constants for use when calling showgifts
    define("SHOW_UNRESERVED_GIFTS",             0);
    define("SHOW_GIFTS_RESERVED_BY_THIS_USER",  1);
    
    function showgifts($connection, $show_user_selection)
    {
        //See whether there are any gifts in the system
        $query = "SELECT * FROM gifts";
        
        //Run the query through the connection
        if (($result = @mysqli_query($connection, $query)) == FALSE)
        {
            showerror($connection);
        }
        
        //Check whether any gifts were found
        if (@mysqli_num_rows($result) == 0)
        {
            //No; print notice
            echo "\n<h3><font color=\"red\">" .
                    "There are no gifts described in the system!</font></h3>";
        }
        else
        {
            //Yes, display the gifts
            // If we're showing the available gifts, then set up
            // a query to show all unreserved gifts (where username IS NULL)
            if ($show_user_selection == SHOW_UNRESERVED_GIFTS)
            {
                $query = "SELECT * FROM gifts WHERE username IS NULL".
                    "ORDER BY description";
            }
            else
            {
                //Otherwise setup up a query to show all gifts reserved by
                //this user
                $query = "SELECT * FROM gifts WHERE username = '".
                        $_SESSION['username']."' ORDER BY description";
            }
            
            //Run the query through the connection
            if (($result = @mysqli_query($connection, $query)) == FALSE)
            {
                showerror($connection);
            }
            
            //Did we get back any rows?
            if (@mysqli_num_rows($result) == 0)
            {
                //No data was returned from the query.
                //Show an appropriate message
                if ($show_user_selection == SHOW_UNRESERVED_GIFTS)
                {
                    echo "\n<h3><font color = \"red\"> No gifts left! </font></h3>";
                }
                else
                {
                    echo "\n<h3><font color = \"red\"> Your Basket is empty! </font></h3>";
                }
            }
            else
            {
                //Yes, so show the gifts as tables
                echo "\n<table boarder=1 width=100%>";
                
                //Create some headings for the table
                echo "\n<tr>" .
                    "\n\t<th>Quantity</th>" .
                    "\n\t<th>Gift</th>" .
                    "\n\t<th>Colour</th>" .
                    "\n\t<th>Available From</th>" .
                    "\n\t<th>Price</th>" .
                    "\n\t<th>Action</th>" .
                    "\n</tr>";
                
                //Fetch each database table row of the results
                while($row = mysqli_fetch_array($result))
                {
                    //Display the gift data as a table row
                    // Display the gift data as a table row
                    echo "\n<tr>" .
                    "\n\t<td>{$row["quantity"]}</td>" .
                    "\n\t<td>{$row["description"]}</td>" .
                    "\n\t<td>{$row["color"]}</td>" .
                    "\n\t<td>{$row["shop"]}</td>" .
                    "\n\t<td>{$row["price"]}</td>";
                
                    // Are we showing the list of gifts reserved by the
                    // user?
                    if ($show_user_selection == SHOW_UNRESERVED_GIFTS)
                    {
                        // No. So set up an embedded link that the user can click
                        // to add the gift to their shopping list by running
                        // action.php with action=add
                        echo "\n\t<td><a href=\"action.php?action=add&" .
                        "gift_id={$row["gift_id"]}\">".
                        "Add to Shopping List</a></td>";
                    }
                    else
                    {
                        //Yes. So set up an embedded link the user can click
                        //to remove the gift to their shopping list by running
                        //action.php with action=remove
                        echo "\n\t<td><a href=\"action.php?action=remove@" .
                                "gift_id={$row["gift_id"]}\">".
                                "Remove from shopping list</a></td>";
                    }
                }
                echo "\n</table>";
            }
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
    <body bgcolor='LIGHTBLUE'>

    <?php
        // Show a logout link
        echo "<a href='logout.php'>Logout</a>";
        // Check whether the user is Jack or Jill (username is 'jack' or
        // 'jill'); if so, show a link to the gift editing page.
        if($_SESSION['username']=="jack" || $_SESSION['username']=="jill")
        {
            echo " | <a href='edit.php'>Edit gifts</a>";
        }
        
        // Connect to the MySQL DBMS and use the wedding database -
        // credentials are in the file db.php
        if(!($connection= @mysqli_connect(
                $DB_hostname, $DB_username, $DB_password, $DB_databasename)))
        {
            showerror($connection);
        }
        
        // Pre-process the message data for security
        if(count($_GET))
        {
            $message = clean($_GET["message"], 128);
        }
        // If there's a message to show, output it
        if (!empty($message))
        {
            echo "\n<h3><font color=\"red\"><em>".
            urldecode($message)."</em></font></h3>";
        }
        
        echo "\n<h3>Here are some gift suggestions</h3>";
        // Show the gifts that are still unreserved
        //showgifts($connection, SHOW_UNRESERVED_GIFTS);
        
        echo "\n<h3>Your Shopping List</h3>";
        // Show the gifts that have been reserved by this user
        //showgifts($connection, SHOW_GIFTS_RESERVED_BY_THIS_USER);

?>
</body>
</html>





    
