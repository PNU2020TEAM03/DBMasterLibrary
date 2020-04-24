package com.dbmasterlibrary;

public class Connection {
    private String username;
    private String pw;
    private String URL;

    public Connection(String name, String pw, String URL) {
        this.username = username;
        this.pw = pw;
        this.URL = URL;

        this.connect();
    }

    public void connect() {

    }
}
