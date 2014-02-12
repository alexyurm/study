<!DOCTYPE HTML>
<html>
<head>
<style>
.error {color: #FF0000;}
</style>
</head>
<body>


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
            $email = test_input($_POST["email"]);
            if (!preg_match("/([\w\-]+\@[\w\-]+\.[\w\-]+)/", $email))
            {
               $emailError="Invalid email format";
            }
        }

        if (empty($_POST["website"]))
        {
            $website = "";
        }
        else
        {
            $website = test_input($_POST["website"]);
            //check if URL address syntax is valid (this regular expression also allows dashes in the URL)
            if (!preg_match("/\b(?:(?:https?|ftp):\/\/|www\.)[-a-z0-9+&@#\/%?=~_|!:,.;]*[-a-z0-9+&@#\/%=~_|]/i",$website))
            {
                $websiteErr="Invalid URL";
            }
        }

        if (empty($_POST["comment"]))
        {
           $comment = "";
        }
        else
        {
           $comment = test_input($_POST["comment"]);
        }
         
        if (empty($_POST["gender"])) 
        {
           $genderErr = "Gender is required";
        }
        else
        {
           $gender = test_input($_POST["gender"]);
        }
    }

function test_input($data)
{
    $data = trim($data); //??what is the trim function doing?
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
?> 

<h2>PHP Form Validation Example</h2>
<p><span class="error">* required field.</span></p>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>"> 
   Name: <input type="text" name="name">
   <span class="error">* <?php echo $nameErr;?></span>
   <br><br>
   E-mail: <input type="text" name="email">
   <span class="error">* <?php echo $emailErr;?></span>
   <br><br>
   Website: <input type="text" name="website">
   <span class="error"><?php echo $websiteErr;?></span>
   <br><br>
   Comment: <textarea name="comment" rows="5" cols="40"></textarea>
   <br><br>
   Gender:
   <input type="radio" name="gender" value="female">Female
   <input type="radio" name="gender" value="male">Male
   <span class="error">* <?php echo $genderErr;?></span>
   <br><br>
   <input type="submit" name="submit" value="Submit"> 
</form>

<?php
echo "<h2>Your Input:</h2>";
echo $name;
echo "<br>";
echo $email;
echo "<br>";
echo $website;
echo "<br>";
echo $comment;
echo "<br>";
echo $gender;
?>

</body>
</html>
