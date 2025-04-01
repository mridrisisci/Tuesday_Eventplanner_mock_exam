package app.entities;

import app.dto.TicketDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "purchase_date")
    private LocalDate purchseDate;

    private Double price;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @ToString.Exclude
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ToString.Exclude
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Ticket(TicketDTO ticketDTO)
    {
        this.purchseDate = ticketDTO.getPurchaseDate();
        this.price = ticketDTO.getPrice();
        this.seatNumber = ticketDTO.getSeatNumber();
    }

}
