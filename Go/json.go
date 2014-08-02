package main
import (
    "fmt"
    "encoding/json"
    "log"
)

type Message struct {
    Name string
    Body string
    Time int64
}

func main() {
    m := Message{"Alice", "Hello", 1294706395881547000}
    b, err := json.Marshal(m) //Marshal returns the JSON encoding of m

    
    //
    //b == []byte(`{"Name":"Alice","Body":"Hello","Time":1294706395881547000}`)
    
    if err != nil {
        log.Fatal(err)
    } else {
        fmt.Println(b)
    }
}
