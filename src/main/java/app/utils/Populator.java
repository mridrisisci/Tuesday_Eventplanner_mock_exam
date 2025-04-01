package app.utils;


import app.entities.Event;
import app.entities.Ticket;
import app.entities.User;
import app.enums.EventCategory;
import jakarta.persistence.EntityManager;
import lombok.Getter;
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
            .id(1)
            .seatNumber(44)
            .price(50.0)
            .build();

        ticket2 = Ticket.builder()
            .id(2)
            .seatNumber(90)
            .price(50.0)
            .build();

        ticket3 = Ticket.builder()
            .id(3)
            .seatNumber(50)
            .price(45.0)
            .build();

        ticket4 = Ticket.builder()
            .id(4)
            .seatNumber(77)
            .price(45.0)
            .build();

        ticket5 = Ticket.builder()
            .id(5)
            .seatNumber(22)
            .price(100.0)
            .build();

        ticket6 = Ticket.builder()
            .id(6)
            .seatNumber(33)
            .price(100.0)
            .build();

        user1 = User.builder()
            .id(1)
            .firstName("John")
            .lastName("Doe")
            .email("john.doe@example.com")
            .phone(22993840)
            .build();

        user2 = User.builder()
            .id(2)
            .firstName("Jane")
            .lastName("Doe")
            .email("jane.doe@example.com")
            .phone(93283374)
            .build();

    }




    public Map<String, Object> getEntites()
    {
        Map<String, Object> entities = new HashMap<>();
        return entities;
    }

    public void resetAndPersistEntities(EntityManager em)
    {
        em.getTransaction().begin();
        //em.createQuery("DELETE FROM Trip").executeUpdate();
        //em.createQuery("DELETE FROM Guide").executeUpdate();
        for (Object entity : getEntites().values())
        {
            em.persist(entity);
        }
        // ensures bi-directional mapping
        /*guide1.addTrip(trip1);
        guide1.addTrip(trip2);
        guide1.addTrip(trip3);
        guide2.addTrip(trip4);
        guide2.addTrip(trip5);
        guide2.addTrip(trip6);*/
        /*for (Trip entity : getTrips().values())
        {
            em.persist(entity);
        }*/
        // ensures bi-directional mapping is updated to DB
        //guide1 = em.merge(guide1);
        //guide2 = em.merge(guide2);
        em.getTransaction().commit();
    }
}
