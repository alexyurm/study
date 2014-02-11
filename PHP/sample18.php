<!DOCTYPE HTML>
<html>
<head>
<style>
.error {color: #FF0000;}
</style>
</head>
<body>

<?php
// define variables and set to empty values
$name = $email = $gender = $comment =$website = "";

if ($_SERVER["REQUEST_METHOD"] == "POST")
{
    /*
    $name = test_input($_POST["name"]);
    $email= test_input($_POST["email"]);
    $website= test_input($_POST["website"]);
    $comment= test_input($_POST["comment"]);
    $gender= test_input($_POST["gender"]);
    */

    if (empty($_POST["name"]))
    {
        $nameErr = "Name is required";
    }
    else
    {
        $name = test_input($_POST["name"]);
    }

    if (empty($_POST["email"]))
    {$emailErr = "Email is required";}
    else
    {$email = test_input($_POST["email"]);}

    if (empty($_POST["website"]))
      {$website = "";}
    else
      {$website = test_input($_POST["website"]);}

    if (empty($_POST["comment"]))
      {$comment = "";}
    else
      {$comment = test_input($_POST["comment"]);}

    if (empty($_POST["gender"]))
      {$genderErr = "Gender is required";}
    else
      {$gender = test_input($_POST["gender"]);}
}

function test_input($data)
{
    $data = trim($data); //??what is the trim function doing?
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
?>

<h2>Form Validation Example</h2>
<!--    htmlspecialchars() function converts special characters to HTML entities. This means that it will replace HTML characters 
        like < and > with $lt; and $gt. This prevents attackers from exploiting the code by injecting HTML or Javascript code 
        (Cross-site Scripting attacks) in forms. 

        Cross-site scripting (XSS) is a type of computer security vulnerability typically found in Web applications. XSS enables attackers 
        to inject client-side script into Web pages viewed by other users.

        1) Security tip#1: The $_SERVER["PHP_SELF"] variable can be used by hackers!

            If PHP_SELF is used in your page then a user can enter a slash (/) and then some Cross Site Scripting (XSS) commands to execute.

            Consider that a user enters the following URL in the address bar:

            http://www.example.com/test_form.php/%22%3E%3Cscript%3Ealert('hacked')%3C/script%3E

            In this case, the above code will be translated to:

            <form method="post" action="test_form.php"/><script>alert('hacked')</script>

            This code adds a script tag and an alert command. And when the page loads, the JavaScript code will be executed (the user will 
            see an alert box). This is just a simple and harmless example how the PHP_SELF variable can be exploited.

            Be aware of that any JavaScript code can be added inside the <script> tag! A hacker can redirect the user to a file on another server, 
            and that file can hold malicious code that can alter the global variables or submit the form to another address to save the user data, for example.

        2) $_SERVER["PHP_SELF"] exploits can be avoided by using the htmlspecialchars() function.

            The form code should look like this:

            <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">

            The htmlspecialchars() function converts special characters to HTML entities. Now if the user tries to exploit the PHP_SELF variable, it will 
            result in the following output:

            <form method="post" action="test_form.php/&quot;&gt;&lt;script&gt;alert('hacked')&lt;/script&gt;">
            The exploit attempt fails, and no harm is done!

        3) We will also do two more things when the user submits the form:

            Strip unnecessary characters (extra space, tab, newline) from the user input data (with the PHP trim() function)
            Remove backslashes (\) from the user input data (with the PHP stripslashes() function)
-->
<form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
<p><span class="error">* required field.</span></p>
Name: <input type="text" name="name">
<span class="error">* <?php echo $nameErr;?></span>
<br><br>
E-mail: <input type="text" name="email">
<span class="error">* <?php echo $emailErr;?></span>
<br><br>
Website: <input type="text" name="website">
<span class="error"><?php echo $websiteErr;?></span>
<br><br>
Comment: <textarea name="comment" rows="5" cols="40"></textarea><br><br>
Gender:
<input type="radio" name="gender" value="female">Female
<input type="radio" name="gender" value="male">Male
<span class="error">* <?php echo $genderErr;?></span>
<br><br>
<input type="submit">
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
