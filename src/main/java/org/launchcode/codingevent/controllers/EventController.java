package org.launchcode.codingevent.controllers;

import org.launchcode.codingevent.data.EventCategoryRepository;
import org.launchcode.codingevent.data.EventRepository;
import org.launchcode.codingevent.models.Event;
import org.launchcode.codingevent.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayEvents(@RequestParam(required=false) Integer categoryId, Model model) {
        if(categoryId == null) {
            model.addAttribute("title","All Event");
            model.addAttribute("events", eventRepository.findAll());
        }else{
            Optional<EventCategory> result=eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            }else{
                EventCategory category=result.get();
                model.addAttribute("title","Events in Category:"+category.getName());
                model.addAttribute("events",category.getEvents());
            }

        }
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
