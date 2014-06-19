<?php
$object = new User;

print_r($object);

$object->name = "Joe";
$object->password = "mypass";

print_r($object);

$object->save_user();

$object2 = new User("Alex", "dadada");
print_r($object2->get_password());

User::pwd_string();

class User
{
    const ENGLISH = 0;
    const SPANISH = 1;
    const FRENCH = 2;
    const GERMAN = 3;

    public $name, $password;
        
    function save_user()
    {
        echo "Save User code goes here";
    }

    function __construct($parm1, $parm2)
    {
        $this->name = $parm1;
        $this->password = $parm2;
    }

    function __destruct()
    {
        // Destruct code goes here
    }

    function get_password()
    {
       return $this->password;
    }

    static function pwd_string()
    {
        echo "Please enter your password";
    }

    static function lookup()
    {
        echo self::SPANISH;
    }
}
?>
