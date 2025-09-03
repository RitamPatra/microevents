package com.ritam.microevents.bootstrap;

import com.ritam.microevents.model.*;
import com.ritam.microevents.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final EventRepository eventRepo;
    private final TagRepository tagRepo;
    private final UserRepository userRepo;
    private final RsvpRepository rsvpRepo;

    public DataLoader(EventRepository eventRepo, TagRepository tagRepo, UserRepository userRepo, RsvpRepository rsvpRepo) {
        this.eventRepo = eventRepo; this.tagRepo = tagRepo; this.userRepo = userRepo; this.rsvpRepo = rsvpRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Tag ai = tagRepo.save(new Tag("ai"));
        Tag study = tagRepo.save(new Tag("study"));
        Tag coffee = tagRepo.save(new Tag("coffee"));

        Event e1 = new Event("AI coffee chat", "Discuss small AI projects", LocalDateTime.now().plusDays(1).withHour(18).withMinute(0), "Cafe");
        e1.getTags().add(ai); e1.getTags().add(coffee);
        eventRepo.save(e1);

        Event e2 = new Event("Evening study group - Algorithms", "Problem solving session", LocalDateTime.now().plusDays(2).withHour(19).withMinute(0), "Library Room 2");
        e2.getTags().add(study);
        eventRepo.save(e2);

        // sample user
        UserAccount u = userRepo.save(new UserAccount("ritam", "password"));
        // sample RSVP: alice yes to e1
        rsvpRepo.save(new Rsvp(u, e1, Rsvp.Status.YES));
    }
}

