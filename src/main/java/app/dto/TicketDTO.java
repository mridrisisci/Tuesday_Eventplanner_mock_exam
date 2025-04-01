package app.dto;

import app.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO
{
    private Integer id;
    private Integer eventId;
    private LocalDate purchaseDate;
    private Double price;
    private Integer seatNumber;

    public TicketDTO(Ticket ticket)
    {
        this.id = ticket.getId();
        this.eventId = ticket.getEventId();
        this.purchaseDate = ticket.getPurchseDate();
        this.price = ticket.getPrice();
        this.seatNumber = ticket.getSeatNumber();
    }
}
