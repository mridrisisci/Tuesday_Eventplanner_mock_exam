package app.entities;

import app.dto.EventDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private Integer price;

    public Event(EventDTO eventDTO)
    {
        this.name = eventDTO.getName();
        this.location = eventDTO.getLocation();
        this.price = eventDTO.getPrice();
    }
}
