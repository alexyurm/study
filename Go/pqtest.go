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
    //"strings"
)

func main() {
    db, err := sql.Open("postgres", "host=/var/run/postgresql user=aioptify dbname=aioptify password=gocanadago sslmode=disable")

    defer db.Close()
    //defer cleaup(db)

    if err != nil {
        fmt.Println("cannot open aioptify db!!\n")
    } else {
        fmt.Println("Successfully open aioptify db\n")
        //rows, err := db.Query("SELECT * from advertisers");
        rows, err := db.Query("SELECT * from advertisers");

        if err != nil {
            log.Fatal(err)        
        }

        for rows.Next() {
            var username, email, passwd string
            var id int

            if err := rows.Scan(&username, &email, &passwd, &id); err != nil {
                log.Fatal(err)    
            }
            fmt.Printf("the username is %s\n", username)
            fmt.Printf("the email is %s\n", email)
            fmt.Printf("the passwd is %s\n", passwd)
            fmt.Printf("the id is %d\n", id)
        }
    }
}
