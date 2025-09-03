package com.ritam.microevents.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Event> events = new HashSet<>();

    public Tag() {}
    public Tag(String name) { this.name = name; }

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

