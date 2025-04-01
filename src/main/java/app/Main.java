package app;

import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.controllers.EventController;
import app.controllers.SecurityController;
import app.controllers.TicketController;
import app.routes.Routes;
import jakarta.persistence.EntityManagerFactory;


public class Main
{
    private final static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();


    public static void main(String[] args)
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
                .startServer(7070);
    }
}