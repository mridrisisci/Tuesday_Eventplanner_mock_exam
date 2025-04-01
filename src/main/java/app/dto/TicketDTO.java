package app.dto;

import app.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO
{
    private Integer id;
    private LocalDate purchaseDate;
    private Double price;
    private Integer seatNumber;

    public TicketDTO(Ticket ticket)
    {
        this.id = ticket.getId();
        this.purchaseDate = ticket.getPurchseDate();
        this.price = ticket.getPrice();
        this.seatNumber = ticket.getSeatNumber();
    }
}
