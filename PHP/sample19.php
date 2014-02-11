<?php
/*
    Regular expression in php

    Simple regex

    Regex quick reference
    [abc]     A single character: a, b or c
    [^abc]     Any single character but a, b, or c
    [a-z]     Any single character in the range a-z
    [a-zA-Z]     Any single character in the range a-z or A-Z
    ^     Start of line
    $     End of line
    \A     Start of string
    \z     End of string
    .     Any single character
    \s     Any whitespace character
    \S     Any non-whitespace character
    \d     Any digit
    \D     Any non-digit
    \w     Any word character (letter, number, underscore)
    \W     Any non-word character
    \b     Any word boundary character
    (...)     Capture everything enclosed
    (a|b)     a or b
    a?     Zero or one of a
    a*     Zero or more of a
    a+     One or more of a
    a{3}     Exactly 3 of a
    a{3,}     3 or more of a
    a{3,6}     Between 3 and 6 of a

    options: i case insensitive m make dot match newlines x ignore whitespace in regex o perform #{...} substitutions only once
*/

    //define variables and set to empty values
    $nameErr = $emailErr = $genderErr = $websiteErr = "";
    $name = $email = $gender = $comment = $website = "";

    if($_SERVER["REQUEST_METHOD"] == "POST")
    {
        if (empty($_POST["name"]))
        {
            $nameErr = "Name is required";
        }
        else
        {
            $name = test_input($_POST["name"]);
            //check if name only contains letters and whitespace
            if (!preg_match("/^[a-zA-Z]*$/", $name))
            {
                $nameErr = "Only letters and white space allowed";
            }
        }

        if (empty($_POST["email"]))
        {
            $emailErr = "Email is required";
        }
        else
        {
            $email = test_input($_POST["email"])
            if (!preg_match("/([\w\-] + \@[\w\-]+\.[\w])/", $email))
        }

        if (empty($_POST["website"]))
        {
            $website = "";
        }
        else
        {
            $website = test_input($_POST["website"]);
            //check if URL address syntax is valid (this regular expression also allows dashes in the URL)
            if (!preg_match("/\b(?:(?:https?|ftp):\/\/|www\.)[-a-z0-9+&@#\/%?=~_|!:,.;]*[-a-z0-9+&@#\/%=~_|]/i",$website)
            {
                $websiteErr="Invalid URL";
            }
        }
    }

?>
