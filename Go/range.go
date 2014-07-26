package main
import "fmt"

var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}


func main() {
    for i, v := range pow {
        fmt.Printf("2**%d = %d\n", i, v)
    }

    pow2 := make([]int, 10)
    for i := range pow2 {
        pow2[i] = 1<<uint(i)
    }

    //You can skip the index or value by assigning to _.
    //If you only want the index, drop the ", value" entirely
    //for _, value := range pow2 {
    //    fmt.Printf("%d\n", value);
    for index := range pow2 {
        fmt.Printf("%d\n", index);
    }
}
