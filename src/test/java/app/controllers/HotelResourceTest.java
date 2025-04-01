package app.controllers;

import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.dto.EventDTO;
import app.entities.Event;
import app.entities.Ticket;
import app.routes.Routes;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HotelResourceTest
{

    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    final ObjectMapper objectMapper = new ObjectMapper();
    Event event1, event2;
    final Logger logger = LoggerFactory.getLogger(HotelResourceTest.class.getName());


    @BeforeAll
    static void setUpAll()
    {
        TicketController ticketController = new TicketController(emf);
        SecurityController securityController = new SecurityController(emf);
        EventController eventController = new EventController(emf);
        Routes routes = new Routes(ticketController, securityController, eventController);
        ApplicationConfig
                .getInstance()
                .initiateServer()
                .setRoute(routes.getRoutes())
                .handleException()
                .setApiExceptionHandling()
                .checkSecurityRoles()
                .startServer(7078);
        RestAssured.baseURI = "http://localhost:7078/api";
    }

    @BeforeEach
    void setUp()
    {
        try (EntityManager em = emf.createEntityManager())
        {
            //TestEntity[] entities = EntityPopulator.populate(genericDAO);
            event1 = new Event("TestEntityA");
            event2 = new Event("TestEntityB");
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Event ").executeUpdate();
            em.createQuery("DELETE FROM Ticket ").executeUpdate();

            em.persist(event1);
            em.persist(event2);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            logger.error("Error setting up test", e);
        }
    }

    @Test
    void getAll()
    {
        given().when().get("/hotel").then().statusCode(200).body("size()", equalTo(2));
    }

    @Test
    void getById()
    {
        given().when().get("/hotel/" + event2.getId()).then().statusCode(200).body("id", equalTo(event2.getId().intValue()));
    }

    @Test
    void create()
    {
        Event entity = new Event("Bronze Test 1337");
        Ticket ticket = new Ticket(1);
        entity.addTicket(ticket);
        try
        {
            String json = objectMapper.writeValueAsString(new EventDTO(entity));
            given().when()
                    .contentType("application/json")
                    .accept("application/json")
                    .body(json)
                    .post("/event")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo(entity.getName()))
                    //.body("address", equalTo(entity.getAddress()));
                    //.body("rooms.size()", equalTo(1));
        } catch (JsonProcessingException e)
        {
            logger.error("Error creating event", e);
            fail();
        }
    }

    @Test
    void upappe()
    {
        Hotel entity = new Hotel("New entity2");
        try
        {
            String json = objectMapper.writeValueAsString(new HotelDTO(entity));
            given().when()
                    .contentType("application/json")
                    .accept("application/json")
                    .body(json)
                    .put("/hotel/" + t1.getId()) // double check id
                    .then()
                    .statusCode(200)
                    .body("name", equalTo("New entity2"));
        } catch (JsonProcessingException e)
        {
            logger.error("Error upapping hotel", e);
            fail();
        }
    }

    @Test
    void delete()
    {
        given().when()
                .delete("/hotel/" + t1.getId())
                .then()
                .statusCode(204);
    }
}