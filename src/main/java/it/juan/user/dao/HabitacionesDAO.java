package it.juan.user.dao;

import it.juan.user.entity.Habitacion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class HabitacionesDAO implements HabitacionesDAOInterface{


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EntityManager entityManager;
    @Override
    public Habitacion anadirHabitacion(Habitacion habitacion) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = null;
        try {
            t = currentSession.beginTransaction();
            currentSession.save(habitacion);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace(); // Imprime la excepción para depuración
            throw e; // Lanza la excepción para que sea manejada en el controlador
        } finally {
            currentSession.close();
        }
        return habitacion;
    }

    @Override
    public void eliminarHabitacion(int id_Habitacion) {

        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();

        Query theQuery = currentSession.createQuery("delete from Habitacion h where id_Habitacion IN (:id_Habitacion)");

        theQuery.setParameter("id_Habitacion", id_Habitacion);
        theQuery.executeUpdate();
        t.commit();
        currentSession.close();
    }

    @Override
    public void modificarOcupacion(int id_Habitacion) {

        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();

        // Primero obtenemos el estado actual de la habitación
        Query<Habitacion> query = currentSession.createQuery("from Habitacion h where h.id_Habitacion = :id_Habitacion", Habitacion.class);
        query.setParameter("id_Habitacion", id_Habitacion);
        Habitacion habitacion = query.uniqueResult();

        if (habitacion != null) {
            // Invertimos el valor de 'ocupada'
            boolean nuevoEstado = !habitacion.getOcupada();

            // Actualizamos el valor de 'ocupada'
            Query updateQuery = currentSession.createQuery("update Habitacion h set h.ocupada = :nuevoEstado where h.id_Habitacion = :id_Habitacion");
            updateQuery.setParameter("nuevoEstado", nuevoEstado);
            updateQuery.setParameter("id_Habitacion", id_Habitacion);
            updateQuery.executeUpdate();
        }

        t.commit();
        currentSession.close();
    }

    @Override
    public List<Habitacion> habitaciones_Tamano_Precio(double capacidad_Minima, double capacidad_Maxima, double precio_Minimo, double precio_Maximo) {
        Session currentSession = entityManager.unwrap(Session.class);

        String hql = "FROM Habitacion h WHERE h.capacidad BETWEEN :capacidad_Minima AND :capacidad_Maxima " +
                "AND h.precio_Noche BETWEEN :precio_Minimo AND :precio_Maximo AND h.ocupada = false";

        Query<Habitacion> query = currentSession.createQuery(hql, Habitacion.class);
        query.setParameter("capacidad_Minima", capacidad_Minima);
        query.setParameter("capacidad_Maxima", capacidad_Maxima);
        query.setParameter("precio_Minimo", precio_Minimo);
        query.setParameter("precio_Maximo", precio_Maximo);



        return query.getResultList();
    }
}
