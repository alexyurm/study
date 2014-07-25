package main
import "fmt"

//A Tour of Go
//The new function
//The expression new(T) allocates a zeroed T value
//and returns a pointer to it.
type Vertex struct {
    X, Y int
}

func main() {
    v := new(Vertex)
    fmt.Println(v)
    v.X, v.Y = 11, 9
    fmt.Println(v)
}
