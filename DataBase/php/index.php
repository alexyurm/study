<?php
    //index.php: Show the user the login screen for the application, or
    //log in a user with correct authentication details.
    
    //Include database parameters and related functions
    require_once("db.php");

    // Connect to the MySQL DBMS and use the wedding database -
    // credentials are in the file db.php
    if(!($connection= @ mysqli_connect(
        $DB_hostname, $DB_username, $DB_password, $DB_databasename)))
    {
        showerror($connection);
    }

    //Pre-process the authentication data from the form for security
    //and assign the username and password to local variables
    if (count($_POST))
    {
        $username = clean($_POST["username"], 30);
        $password = clean($_POST["password"], 30);
    }

    //Pre-process the message data for security
    if(count($_GET))
    {
        $message = clean($_GET["message"], 128);
    }

    //if no username or password has been entered, or there's a message
    //to display, show the login page.
    if (empty($username) || 
        empty($password) ||
        isset($message) )
    {
        ?>
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
        
        <html>
        <head>
        <title>Jack and Jill's Wedding Gift Registry</title>
        </head>
        <body bgcolor='LIGHTBLUE'>
            <h2>Jack and Jill's Wedding Gift Registry</h2>
            <?php
                // If an error message, show it...
                if (isset($message))
                {
                    echo "<h3><font color=\"red\">{$message}</font></h3>";
                }
            ?>
            (if you've not logged in before, make up a username and password)
                <form action="<?php echo $_SERVER["PHP_SELF"];?>" method="POST">
                <br />Please enter a username:
                    <input type="text" name="username"
                    value="<?php if(isset($_POST['username']))
                                    echo $_POST['username'];?>" />
            <br />Please enter a password:
                <input type="password" name="password" />
            <br /><input type="submit" value="Log in">
            </form>
            <br />
            <?php require "disclaimer"; ?>
        </body>
        </html>
        <?php
    }
    else
    {
        //Check that the username and password are each at least four
        //characters long
        if ( (strlen($username)<4) ||
             (strlen($password)<4)
           )
        {
            //they are not; create an error message and redirect
            //the browser to the index page to display the message
            $message = "Please choose a username and password that are ".
            "at least four characters long";
            header("Location: index.php?message=" . urlencode($message));
        }

        //Create a query to find any rows that match provided username
        $query = "SELECT username, password FROM users WHERE username = '$username'";
        
        // Run the query through the connection
        if (($result = @ mysqli_query($connection, $query))==FALSE)
        {
            showerror($connection);
        }

        // Were there any matching rows?
        if (mysqli_num_rows($result) == 0)
        {
            // No, so insert the new username and password into the table
            $query = "INSERT INTO users SET username = '$username', password='".
            crypt($password, substr($username, 0, 2))."'";

            // Run the query through the connection
            if (($result = @ mysqli_query($connection, $query))==FALSE)
            {
                showerror($connection);
            }
        }
        else
        {
            //Yes, so check that the supplied password is correct
            //Fetch the matching row
            
            //If we don't get exactly one answer, then we have a problem
            for($matchrows=0; ($tmprow = @ mysqli_fetch_array($result));$matchrows++)
            {
                $row=$tmprow;
            }

            if ($matchrows != 1)
            {
                die("We've just experienced a technical problem - " . 
                "please notify the administrator.");
            }

            //Does the user-supplied password match the password in the table?
            if (crypt($password, substr($username, 0, 2)) != $row["password"])
            {
                //No, so redirect the browser to the login page with a
                //message
                $message = "This user exits, but the password is incorrect. ".
                "Choose another username, or fix the password.";
                header("Location: index.php?message=" . urlencode($message));
                exit;
            }
        }

        // Everything went OK. Start a session, store the username in a
        // session variable, and redirect the browser to the gift list
        // page with a welcome message.
        session_start();
        $_SESSION['username'] = $username;
        $message = "Welcome {$_SESSION['username']}! Please select gift suggestions".
        " from the list to add to your shopping list!";
        header("Location: list.php?message=" . urlencode($message));
        exit;
    }
?>
