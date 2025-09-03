package com.ritam.microevents.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rsvp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserAccount user;

    @ManyToOne(optional = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    private Status status = Status.YES;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status { YES, NO, MAYBE }

    public Rsvp() {}
    public Rsvp(UserAccount user, Event event, Status status) { this.user = user; this.event = event; this.status = status; }

    public Long getId() { return id; }
    public UserAccount getUser() { return user; }
    public Event getEvent() { return event; }
    public Status getStatus() { return status; }
}
