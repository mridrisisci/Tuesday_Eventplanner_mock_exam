package app.entities;

import app.dto.EventDTO;
import app.enums.EventCategory;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
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

    @Enumerated(EnumType.STRING)
    private EventCategory category;

    @ToString.Exclude
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonManagedReference
    private Set<Ticket> tickets = new HashSet<>();

    public Event(EventDTO eventDTO)
    {
        this.name = eventDTO.getName();
        this.location = eventDTO.getLocation();
        this.price = eventDTO.getPrice();
        this.category = eventDTO.getCategory();
    }

}
