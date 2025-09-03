package com.ritam.microevents.repository;

import com.ritam.microevents.model.Event;
import com.ritam.microevents.model.Rsvp;
import com.ritam.microevents.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RsvpRepository extends JpaRepository<Rsvp, Long> {
    long countByEventAndStatus(Event event, Rsvp.Status status);
    List<Rsvp> findByUser(UserAccount user);
    boolean existsByUserAndEvent(UserAccount user, Event event);
}
