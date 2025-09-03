package com.ritam.microevents.controller;

import com.ritam.microevents.model.*;
import com.ritam.microevents.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventRepository eventRepo;
    private final TagRepository tagRepo;
    private final UserRepository userRepo;
    private final RsvpRepository rsvpRepo;

    public EventController(EventRepository eventRepo, TagRepository tagRepo, UserRepository userRepo, RsvpRepository rsvpRepo) {
        this.eventRepo = eventRepo; this.tagRepo = tagRepo; this.userRepo = userRepo; this.rsvpRepo = rsvpRepo;
    }

    @GetMapping
    public String list(Model model, HttpSession session) {
        List<Event> events = eventRepo.findByEventTimeAfterOrderByEventTimeAsc(LocalDateTime.now().minusDays(1));
        model.addAttribute("events", events);
        model.addAttribute("username", session.getAttribute("username"));

        // suggestions (simple tag frequency)
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            UserAccount user = userRepo.findById(userId).orElse(null);
            if (user != null) {
                List<Rsvp> myRsvps = rsvpRepo.findByUser(user);
                Map<String, Long> tagCount = new HashMap<>();
                for (Rsvp r : myRsvps) {
                    if (r.getStatus() == Rsvp.Status.YES) {
                        for (Tag t : r.getEvent().getTags()) tagCount.merge(t.getName(), 1L, Long::sum);
                    }
                }
                // score upcoming events
                List<Event> suggestions = events.stream()
                        .filter(e -> !rsvpRepo.existsByUserAndEvent(user, e))
                        .sorted(Comparator.comparingInt(e -> - scoreByTags(e, tagCount)))
                        .limit(5)
                        .collect(Collectors.toList());
                model.addAttribute("suggestions", suggestions);
            }
        }

        return "events/list";
    }

    private int scoreByTags(Event e, Map<String, Long> tagCount) {
        int s = 0;
        for (Tag t : e.getTags()) s += tagCount.getOrDefault(t.getName(), 0L);
        return s;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam String eventTime, // We use ISO date-time format, e.g. 2025-08-25T18:00; note that T is the separator
                         @RequestParam String location,
                         @RequestParam(required=false) String tags) {

        Event e = new Event(title, description, LocalDateTime.parse(eventTime), location);
        if (tags != null && !tags.isBlank()) {
            String[] parts = tags.split(",");
            for (String raw : parts) {
                String name = raw.trim().toLowerCase();
                Tag tag = tagRepo.findByName(name).orElseGet(() -> tagRepo.save(new Tag(name)));
                e.getTags().add(tag);
            }
        }
        eventRepo.save(e);
        return "redirect:/events";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        Event e = eventRepo.findById(id).orElse(null);
        if (e == null) return "redirect:/events";
        model.addAttribute("event", e);
        model.addAttribute("goingCount", rsvpRepo.countByEventAndStatus(e, Rsvp.Status.YES));
        model.addAttribute("maybeCount", rsvpRepo.countByEventAndStatus(e, Rsvp.Status.MAYBE));
        model.addAttribute("username", session.getAttribute("username"));
        return "events/detail";
    }

    @PostMapping("/{id}/rsvp")
    public String rsvp(@PathVariable Long id, @RequestParam String status, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        UserAccount user = userRepo.findById(userId).orElse(null);
        Event event = eventRepo.findById(id).orElse(null);
        if (user == null || event == null) return "redirect:/events";

        if (!rsvpRepo.existsByUserAndEvent(user, event)) {
            Rsvp r = new Rsvp(user, event, Rsvp.Status.valueOf(status));
            rsvpRepo.save(r);
        }
        return "redirect:/events/" + id;
    }
}
