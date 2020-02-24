package org.launchcode.codingevent.controllers;

import org.launchcode.codingevent.data.EventData;
import org.launchcode.codingevent.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {
    @GetMapping
    public String allEvents(Model model) {
        model.addAttribute("events",EventData.getAll());
        return "events/index";
    }


    @GetMapping("create")
    public String createEventForm(){

        return "events/create";
    }
    @PostMapping("create")
    public String addEvent(@ModelAttribute Event newEvent){
        EventData.add(newEvent);
        return "redirect:";
    }
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }
    @PostMapping("delete")
    public String DeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:";
    }
}
