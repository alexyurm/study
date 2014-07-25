package main

import "fmt"

type Vertex struct {
    X int
    Y int
}

var (
    p = Vertex{1,2} //has type Vertex
    q = &Vertex{1,2} //has type *Vertex
    r = Vertex{X:1} //Y:0 is implicit. You can list just a subset of fields
    //using the Name: syntax.(And the order of named fields is irrlevant.)
    s = Vertex{} //X:0 and Y:0 
)

func main() {
    fmt.Println(p, q, r, s)
}
