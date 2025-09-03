package com.ritam.microevents.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password; // Storing in plaintext for dev only; we would hash with e.g. BCrypt in a real app

    public UserAccount() {}
    public UserAccount(String username, String password) { this.username = username; this.password = password; }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
