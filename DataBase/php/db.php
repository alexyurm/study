<?php
    //These are the DBMS credentials and the database name
    $DB_hostname = "localhost";
    $DB_username= "root";
    $DB_password= "mysql";
    $DB_databasename= "wedding";
    
    //Show an error and stop the script
    function showerror($connection)
    {
        // Was there an error during connection?
        if (mysqli_connect_errno())
        {
            die("Error " . mysqli_connect_errno($connection) .
            " : " .mysqli_connect_error($connection));
        }
        else
        {
            //No; display the error information for the active connection
            die("Error " .mysqli_errno($connection) . ": "
                         .mysqli_error($connection));
        }
    }

    //Secure the user data by escaping characters and shortening the 
    //input string
    function clean($input, $maxlength)
    {
        //Access the MySQL connection from outside this function.
        global $connection;

        //Limit the length of the string
        $input = substr($input, 0, $maxlength);

        //Escape semicolons and (if magic quotes are off) single and 
        //double quotes
        if (get_magic_quotes_gpc())
        {
            $input = stripslashes($input);
        }
        
        $input = mysqli_real_escape_string($connection, $input);

        return $input;
    }

    //Check if the user is logged in. If not, send them to the login
    //page
    function logincheck()
    {
        session_start();
        
        if (empty($_SESSION['username']))
        {
            //redirect to the login page
            header("Location: index.php");
            exit;
        }
    }
?>
