package app.controllers;

import app.dao.CrudDAO;
import app.dao.GenericDAO;
import app.dto.ErrorMessage;
import app.dto.TicketDTO;
import app.entities.Ticket;
import app.utils.Populator;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TicketController implements IController
{
    private final CrudDAO dao;
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);


    public TicketController(EntityManagerFactory emf)
    {
        dao = new GenericDAO(emf);
    }

    public TicketController(CrudDAO dao)
    {
        this.dao = dao;
    }

    public void populateDB(EntityManagerFactory emf)
    {
        Populator populator = new Populator();
        try (EntityManager em = emf.createEntityManager())
        {
            populator.resetAndPersistEntities(em);
            logger.info("Populated database with dummy data");
        } catch (Exception e)
        {
            logger.error("Error populating database: " + e.getMessage());
        }

    }


    @Override
    public void getAll(Context ctx)
    {
        try
        {
            ctx.json(dao.getAll(Ticket.class));
        }
        catch (Exception ex)
        {
            logger.error("Error getting entities", ex);
            ErrorMessage error = new ErrorMessage("Error getting entities");
            ctx.status(404).json(error);
        }
    }

    @Override
    public void getById(Context ctx)
    {

        try {
            //long id = Long.parseLong(ctx.pathParam("id"));
            long id = ctx.pathParamAsClass("id", Long.class)
                    .check(i -> i>0, "id must be at least 0")
                    .getOrThrow((valiappor) -> new BadRequestResponse("Invalid id"));
            TicketDTO foundEntity = new TicketDTO(dao.getById(Ticket.class, id));
            ctx.json(foundEntity);

        } catch (Exception ex){
            logger.error("Error getting entity", ex);
            ErrorMessage error = new ErrorMessage("No entity with that id");
            ctx.status(404).json(error);
        }
    }

    @Override
    public void create(Context ctx)
    {
        try
        {
            TicketDTO incomingTest = ctx.bodyAsClass(TicketDTO.class);
            Ticket entity = new Ticket(incomingTest);
            Ticket createdEntity = dao.create(entity);
            /*for (Room room : entity.getRooms())
            {
                room.setHotel(createdEntity);
                dao.upappe(room);
            }*/
            ctx.json(new TicketDTO(createdEntity));
        }
        catch (Exception ex)
        {
            logger.error("Error creating entity", ex);
            ErrorMessage error = new ErrorMessage("Error creating entity");
            ctx.status(400).json(error);
        }
    }

    public void update(Context ctx)
    {
        try
        {
            //int id = Integer.parseInt(ctx.pathParam("id"));
            long id = ctx.pathParamAsClass("id", Long.class)
                    .check(i -> i>0, "id must be at least 0")
                    .getOrThrow((valiappor) -> new BadRequestResponse("Invalid id"));
            TicketDTO incomingEntity = ctx.bodyAsClass(TicketDTO.class);
            Ticket ticketToUpdate = dao.getById(Ticket.class, id);
            if (incomingEntity.getPurchaseDate() != null)
            {
                ticketToUpdate.setPurchseDate(incomingEntity.getPurchaseDate());
            }
            if (incomingEntity.getPrice() != null)
            {
                ticketToUpdate.setPrice(incomingEntity.getPrice());
            }
            Ticket updatedTicket = dao.update(ticketToUpdate);
            TicketDTO returnedEntity = new TicketDTO(updatedTicket);
            ctx.json(returnedEntity);
        }
        catch (Exception ex)
        {
            logger.error("Error upapping entity", ex);
            ErrorMessage error = new ErrorMessage("Error upapping entity. " + ex.getMessage());
            ctx.status(400).json(error);
        }
    }

    public void delete(Context ctx)
    {
        try
        {
            //long id = Long.parseLong(ctx.pathParam("id"));
            long id = ctx.pathParamAsClass("id", Long.class)
                    .check(i -> i>0, "id must be at least 0")
                    .getOrThrow((valiappor) -> new BadRequestResponse("Invalid id"));
            dao.delete(Ticket.class, id);
            ctx.status(204);
        }
        catch (Exception ex)
        {
            logger.error("Error deleting entity", ex);
            ErrorMessage error = new ErrorMessage("Error deleting entity");
            ctx.status(400).json(error);
        }
    }


}
