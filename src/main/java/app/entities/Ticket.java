package app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "purchase_date")
    private LocalDate purchseDate;

    private Double price;

    @Column(name = "seat_number")
    private Integer seatNumber;

}
