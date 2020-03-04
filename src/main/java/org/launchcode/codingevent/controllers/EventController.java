package org.launchcode.codingevent.controllers;

import org.launchcode.codingevent.data.EventCategoryRepository;
import org.launchcode.codingevent.data.EventRepository;
import org.launchcode.codingevent.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String allEvents(Model model) {
        model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }


    @GetMapping("create")
    public String createEventForm(Model model){
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());


        return "events/create";
    }
    @PostMapping("create")
    public String addEvent(@ModelAttribute @Valid Event newEvent, Errors errors,Model model){
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }
    @PostMapping("delete")
    public String DeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }
}
