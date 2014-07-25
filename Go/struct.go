/*
A struct is a collection of fields.
(And a type declaration does what you'd expect.)
*/

package main
import "fmt"

type Vertex struct {
    X int
    Y int
}

func main() {
    v := Vertex{1,2}
    v.X = 4 //Struct fields are accessed using a dot
    fmt.Println(v.X)
}
