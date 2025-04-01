package app.entities;

import app.dto.EventDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

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
    private String category;

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
