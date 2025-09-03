package com.ritam.microevents.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDateTime eventTime;
    private String location;

    @ManyToMany
    @JoinTable(name = "event_tags",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public Event() {}
    public Event(String title, String description, LocalDateTime eventTime, String location) {
        this.title = title; this.description = description; this.eventTime = eventTime; this.location = location;
    }

    // getters & setters (generated)
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getEventTime() { return eventTime; }
    public void setEventTime(LocalDateTime eventTime) { this.eventTime = eventTime; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }
}
