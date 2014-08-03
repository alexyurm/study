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

func intArrayToPgArray(intArray []int) string {
    
    if len(intArray) == 0 {
        return "{}"
    } else {
        var pgArray string
        pgArray += "{"

        for i := range intArray {
            pgArray += strconv.Itoa(intArray[i])
            pgArray += ","
        }

        size := len(pgArray)
        pgArray = pgArray[:size-1]
        pgArray += "}"

        return pgArray
    }
}

func stringArrayToPgArray(strArray []string) string {
     if len(strArray) == 0 {
        return "{}"
    } else {
        var pgArray string
        pgArray += "{"

        for i := range strArray {
            pgArray += strArray[i]
            pgArray += ","
        }

        size := len(pgArray)
        pgArray = pgArray[:size-1]
        pgArray += "}"

        return pgArray
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
            var ints, strs sql.NullString
            var m_bytes []byte

            if err := rows.Scan(&a.id, &a.email, &a.password, &a.username, &ints, &strs, &m_bytes); err != nil {
                log.Fatal(err)
            }

            if strs.Valid {
                a.strs = pgArrayToStringArray(strs.String)
            }

            if ints.Valid {
                a.ints = pgArrayToIntArray(ints.String)
            }
            
            if len(m_bytes) != 0 {
                err := json.Unmarshal(m_bytes, &a.msg)
                if err != nil {
                    log.Fatal(err)
                }
            }

            advertisers = append(advertisers, a)
        }

        //Part2: insert data to a table
        var b Advertiser
        b.email = "heidiminmin@gmail.com"
        b.password = "123823"
        b.username = "heidi"
        b.ints = append(b.ints, 444)
        b.ints = append(b.ints, 555)
        b.strs = append(b.strs, "eee")
        b.strs = append(b.strs, "fff")
        //Name string
        //Body string
        //Time int64
        b.msg.Name = "Heidi"
        b.msg.Body = "good bye!"
        b.msg.Time = 12345

        stmt, err := db.Prepare("INSERT INTO advertisers(email,password,username, ints, strings, ext) VALUES($1,$2,$3,$4,$5,$6)")
        if err != nil {
            log.Fatal(err)
        }
        
        bytes, err := json.Marshal(b.msg)
        
        if err != nil {
            log.Fatal(err)
        }

        res, err := stmt.Exec(b.email, b.password, b.username, intArrayToPgArray(b.ints), stringArrayToPgArray(b.strs), bytes)

        if err != nil {
            log.Fatal(err)
        } else {
            fmt.Println(res)
        }
    }
}
