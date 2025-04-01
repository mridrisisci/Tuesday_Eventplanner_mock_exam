package app.utils;


import jakarta.persistence.EntityManager;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Populator
{

    // initialize instance variables here

    public Populator()
    {
        // initialize dummy objects here
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
