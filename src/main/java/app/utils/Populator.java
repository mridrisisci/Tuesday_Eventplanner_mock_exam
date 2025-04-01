package app.utils;


import app.entities.Event;
import app.entities.Ticket;
import app.entities.User;
import app.enums.EventCategory;
import jakarta.persistence.EntityManager;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Populator
{

    // declare instance variables here
    private Event event1;
    private Event event2;
    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;
    private Ticket ticket4;
    private Ticket ticket5;
    private Ticket ticket6;
    private User user1;
    private User user2;

    public Populator()
    {
        // initialize dummy objects here
        event1 = Event.builder()
            .name("Crazy concert")
            .price(200)
            .category(EventCategory.CONCERT)
            .location("Golden Avenue")
            .build();

        event2 = Event.builder()
            .name("Quiet conference")
            .price(5000)
            .category(EventCategory.CONFERENCE)
            .location("Pentagon")
            .build();

        ticket1 = Ticket.builder()
            .seatNumber(44)
            .purchseDate(LocalDate.now())
            .price(50.0)
            .build();

        ticket2 = Ticket.builder()
            .seatNumber(90)
            .purchseDate(LocalDate.now())
            .price(50.0)
            .build();

        ticket3 = Ticket.builder()
            .seatNumber(50)
            .purchseDate(LocalDate.now())
            .price(45.0)
            .build();

        ticket4 = Ticket.builder()
            .seatNumber(77)
            .purchseDate(LocalDate.now())
            .price(45.0)
            .build();

        ticket5 = Ticket.builder()
            .seatNumber(22)
            .purchseDate(LocalDate.now())
            .price(100.0)
            .build();

        ticket6 = Ticket.builder()
            .seatNumber(33)
            .purchseDate(LocalDate.now())
            .price(100.0)
            .build();

        user1 = User.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john.doe@example.com")
            .phone(22993840)
            .build();

        user2 = User.builder()
            .firstName("Jane")
            .lastName("Doe")
            .email("jane.doe@example.com")
            .phone(93283374)
            .build();

    }




    public Map<String, Object> getEntites()
    {
        Map<String, Object> entities = new HashMap<>();
        entities.put("event1", event1);
        entities.put("event2", event2);
        entities.put("ticket1", ticket1);
        entities.put("ticket2", ticket2);
        entities.put("ticket3", ticket3);
        entities.put("ticket4", ticket4);
        entities.put("ticket5", ticket5);
        entities.put("ticket6", ticket6);
        entities.put("user1", user1);
        entities.put("user2", user2);
        return entities;
    }

    public Map<String, Ticket> getTickets()
    {
        Map<String, Ticket> tickets = new HashMap<>();
        tickets.put("ticket1", ticket1);
        tickets.put("ticket2", ticket2);
        tickets.put("ticket3", ticket3);
        tickets.put("ticket4", ticket4);
        tickets.put("ticket5", ticket5);
        tickets.put("ticket6", ticket6);
        return tickets;
    }

    public Map<String, Event> getEvents()
    {
        Map<String, Event> events = new HashMap<>();
        events.put("event1", event1);
        events.put("event2", event2);
        return events;
    }

    public Map<String, User> getUsers()
    {
        Map<String, User> users = new HashMap<>();
        users.put("user1", user1);
        users.put("user2", user2);
        return users;
    }

    public void resetAndPersistEntities(EntityManager em)
    {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Ticket").executeUpdate();
        em.createQuery("DELETE FROM Event").executeUpdate();
        //em.createQuery("DELETE FROM User").executeUpdate();
        for (Event entity : getEvents().values())
        {
            em.persist(entity);
        }
        for (Ticket entity : getTickets().values())
        {
            em.persist(entity);
        }
        // ensures bi-directional mapping
        event1.addTicket(ticket1);
        event1.addTicket(ticket2);
        event1.addTicket(ticket3);

        event2.addTicket(ticket4);
        event2.addTicket(ticket5);
        event2.addTicket(ticket6);


        // ensures bi-directional mapping is updated to DB
        event1 = em.merge(event1);
        event2 = em.merge(event2);
        em.getTransaction().commit();
    }
}
