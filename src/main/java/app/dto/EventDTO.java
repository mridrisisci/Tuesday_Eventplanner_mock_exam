package app.dto;

import app.entities.Event;
import app.enums.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO
{
    private Integer id;
    private String name;
    private String location;
    private Integer price;
    private EventCategory category;

    public EventDTO(Event event)
    {
        this.name = event.getName();
        this.location = event.getLocation();
        this.price = event.getPrice();
        this.category = event.getCategory();
    }

}
