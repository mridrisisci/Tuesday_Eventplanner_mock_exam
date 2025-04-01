package app.routes;

import app.config.HibernateConfig;
import app.controllers.TicketController;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.controllers.SecurityController;
import app.enums.Roles;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes
{
    private final TicketController ticketController;
    private final SecurityController securityController;
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    public Routes(TicketController ticketController, SecurityController securityController)
    {
        this.ticketController = ticketController;
        this.securityController = securityController;
    }

    public EndpointGroup getRoutes()
    {
        return () -> {
            path("ticket", ticketRoutes());
            path("auth", authRoutes());
            path("protected", protectedRoutes());
        };
    }

    private  EndpointGroup ticketRoutes()
    {
        return () -> {
            get("/all", ticketController::getAll);
            post("/create", ticketController::create);
            get("/{id}", ticketController::getById);
            put("/{id}", ticketController::update);
            delete("/{id}", ticketController::delete);
            post("/populate", ctx -> ticketController.populateDB(emf));
        };
    }

    private  EndpointGroup authRoutes()
    {
        return () -> {
            get("/test", ctx->ctx.json(jsonMapper.createObjectNode().put("msg",  "Hello from Open")), Roles.ANYONE);
            get("/healthcheck", securityController::healthCheck, Roles.ANYONE);
            post("/login", securityController::login, Roles.ANYONE);
            post("/register", securityController::register, Roles.ANYONE);
            get("/verify", securityController::verify , Roles.ANYONE);
            get("/tokenlifespan", securityController::timeToLive , Roles.ANYONE);
        };
    }

    private  EndpointGroup protectedRoutes()
    {
        return () -> {
            get("/user_demo",(ctx)->ctx.json(jsonMapper.createObjectNode().put("msg",  "Hello from USER Protected")), Roles.USER);
            get("/admin_demo",(ctx)->ctx.json(jsonMapper.createObjectNode().put("msg",  "Hello from ADMIN Protected")), Roles.ADMIN);
        };
    }

}
