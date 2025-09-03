package com.ritam.microevents.repository;

import com.ritam.microevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventTimeAfterOrderByEventTimeAsc(LocalDateTime time);
}
