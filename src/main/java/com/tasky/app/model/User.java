package com.tasky.app.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this(0, username, email, password);
    }

    public int getId() { return id;};
    public void setId(int id) {this.id = id;};

    public String getUsername() {return this.username;};
    public void setUsername(String username){this.username = username;};

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "com.tasky.app.model.User{id=" + id + ", username='" + username + "', email='" + email + "'}";
    }
}
