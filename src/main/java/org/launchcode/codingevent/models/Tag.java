package org.launchcode.codingevent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    @NotBlank(message = "Tag Name is required.")
    private String name;
    @ManyToMany(mappedBy = "tags")
    private final List<Event> events=new ArrayList<>();
    public Tag(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public Tag(String name) {
        this.name = name;
    }
}
