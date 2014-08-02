package main

import (
    
    //Package db is a pure Go Postgres driver for the database/sql package.
    //In most cases clients will use the database/sql package instead of using this package directly.
    "database/sql"
    //"errors"
    "fmt"
    _ "github.com/lib/pq"
    "log"
    //"os"
    //"regexp"
    "strings"
    "strconv"
    "encoding/json"
)

type Message struct {
    Name string
    Body string
    Time int64
}

type Advertiser struct {
    id int
    email string
    password string
    username string
    ints []int
    strs []string
    msg Message
}

func pgArrayToIntArray(pgArray string) []int {
    strArray := strings.Split(strings.Trim(pgArray, "{}"), ",")
    
    if (len(strArray) == 0) {
        return nil
    } else {
        intArray := make([]int, len(strArray))
        var e error
        for i := range strArray {
            intArray[i], e = strconv.Atoi(strArray[i])
        }

        if (e != nil) {
            log.Fatal(e)
        }
        
        return intArray
    }
}

func pgArrayToStringArray(pgArray string) []string {
    strArray := strings.Split(strings.Trim(pgArray, "{}"), ",")
    if (len(strArray) == 0) {
        return nil
    } else {
        return strArray
    }
}

func main() {
    db, err := sql.Open("postgres", "host=/var/run/postgresql user=aioptify password=gocanadago dbname=aioptify sslmode=disable")
    defer db.Close()

    if err != nil {
        fmt.Println("cannot open aioptify db!!\n")
        log.Fatal(err)
    } else {
        //Part1: fetch data from a table
        rows, err := db.Query("SELECT id, email, password, username, ints, strings, ext from advertisers");

        if err != nil {
            log.Fatal(err)        
        }

        var advertisers []Advertiser

        for rows.Next() {
            var a Advertiser
            var ints, strs string
            var m_bytes []byte

            if err := rows.Scan(&a.id, &a.email, &a.password, &a.username, &ints, &strs, &m_bytes); err != nil {
                log.Fatal(err)    
            }

            a.strs = pgArrayToStringArray(strs)
            a.ints = pgArrayToIntArray(ints)
            json.Unmarshal(m_bytes, &a.msg)

            advertisers = append(advertisers, a)

            fmt.Println(advertisers[0])
        }

        //Part2: insert data to a table
        var b Advertiser
        b.email = "heidiminmin@gmail.com"
        b.password = "1238"
        b.username = "heidi"
        b.ints = append(b.ints, 444)
        b.ints = append(b.ints, 555)
        b.strs = append(b.strs, "eee")
        b.strs = append(b.strs, "fff")
        
        result, err := db.Exec("INSERT INTO advertisers (email, password, username) VALUES (?, ?, ?)",
        if err != nil {
            log.Fatal(err)
        } else {
            fmt.Println(result)
        }
    }
}
