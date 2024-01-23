package it.juan.user.dao;

import it.juan.user.entity.Habitacion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class HabitacionesDAO implements HabitacionesDAOInterface{


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EntityManager entityManager;
    @Override
    public void anadirHabitacion(Habitacion habitacion) {

        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();

        currentSession.saveOrUpdate(habitacion);
        t.commit();
        currentSession.close();
    }

    @Override
    public void eliminarHabitacion(int idHabitacion) {

        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();
        //forma facil
        //User user=findById(id);
        //currentSession.delete(user);
        //otra forma utilizando sentencias HQL DE hibernate

        Query theQuery = currentSession.createQuery("delete from Habitacion u where idHabitacion IN (:idHabitacion)");

        theQuery.setParameter("idHabitacion", idHabitacion);
        theQuery.executeUpdate();
        t.commit();
        currentSession.close();
    }

    @Override
    public void modificarOcupacion(int idHabitacion) {

    }
}
