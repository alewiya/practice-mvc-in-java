package org.launchcode.codingevent.models;

//import javax.persistence.Entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Size(max = 500, message = "Description too long!")
    private String description;
    @NotBlank(message = "Email is required.")
    @Email(message = "invalid format")
    private String email;


    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;
    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();
    public Event() {
    }

    public Event(String name, String description, String email, EventCategory eventCategory) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.eventCategory = eventCategory;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public List<Tag> getTags() {
        return tags;
    }
public void addTag(Tag tag){
        this.tags.add(tag);
}
    @Override
    public String toString() {
        return name;
    }
}


