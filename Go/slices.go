package main

import "fmt"

//Slices: A slice points to an array of values and also includes a length
//[]T is a slice with elements of type T.
func main() {
    p := []int{2, 3, 5, 7, 11, 13}
    fmt.Println("p == ", p)

    for i :=0; i < len(p); i++ {
        fmt.Printf("p[%d] == %d\n", i, p[i])
    }

    /*
        Slices can be re-sliced, creating a new slice value that points
        to the same array

        The expression

        s[lo:hi]

        evaluates a slice of the elements from lo through hi-1, inclusive

        Thus

        s[lo:lo] is empty and

        s[lo:lo+1] has one element.
    */
    fmt.Println("p[1:4] == ", p[1:4])

    // missing low index implies 0
    fmt.Println("p[:3] ==", p[:3])

    // missing high index implies len(s)
    fmt.Println("p[4:] ==", p[4:])

    /*
        Slices are created with the make function. It works by allocating a zeroed array and returning a slice
        that refers to that array:

        a :=make([]int, 5) //len(a)=5

        To specify a capacity, pass a third argument to make:

        b := make([]int, 0, 5) //len(b), cap(b)=5

        b = b[:cap(b)] //len(b)=5, cap(b)=5
        b = b[1:] //len(b)=4, cap(b)=4
    */

    a := make([]int, 5)
    printSlice("a", a)
    b := make([]int, 0, 5)
    printSlice("b", b)
    c := b[:2]
    printSlice("c", c)
    d := c[2:5]
    printSlice("d", d)

    func printSlice(s stringm x[]int) {
        fmt.Printf("%s len=%d cap=%d %v\n", s, len(x), cap(x), x)
    }
}
