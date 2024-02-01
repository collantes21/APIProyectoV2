package it.juan.user.dao;

import it.juan.user.entity.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class HotelDAO implements HotelDAOInterface{

//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Hotel> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Hotel> theQuery = currentSession.createQuery("SELECT u from Hotel u", Hotel.class);

        List<Hotel> hoteles = theQuery.getResultList();

        return hoteles;
    }

    @Override
    public List<Hotel> findByLocalidad(String localidad) {

        Session currentSession = entityManager.unwrap(Session.class);

        // Usamos HQL (Hibernate Query Language) para realizar la consulta
        Query<Hotel> theQuery = currentSession.createQuery("SELECT h FROM Hotel h WHERE h.localidad = :localidad", Hotel.class);
        theQuery.setParameter("localidad", localidad);

        List<Hotel> hoteles = theQuery.getResultList();

        return hoteles;
    }

    @Override
    public List<Hotel> findByCategoria(String categoria) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Hotel> theQuery = currentSession.createQuery("SELECT h from Hotel h WHERE h.categoria= :categoria", Hotel.class);

        theQuery.setParameter("categoria", categoria);

        List<Hotel> hoteles = theQuery.getResultList();

        return hoteles;
    }


    @Override
    public void anadirHotel(Hotel hotel) {

        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();

        currentSession.saveOrUpdate(hotel);
        t.commit();
        currentSession.close();
    }

    @Override
    public void deleteById(int id_Hotel) {

        Session currentSession = entityManager.unwrap(Session.class);
        Transaction transaction = currentSession.beginTransaction();

        try {
            Hotel hotel = currentSession.get(Hotel.class, id_Hotel);

            // Verifique se o hotel existe
            if (hotel != null) {
                // As habitaciones associadas serão excluídas devido à configuração de cascata
                currentSession.delete(hotel);
            } else {
                // Lide com o caso em que o hotel com o ID especificado não é encontrado
                throw new EntityNotFoundException("Hotel with ID " + id_Hotel + " not found");
            }

            transaction.commit();
        } catch (Exception e) {
            // Lide com exceções, registre ou relance se necessário
            transaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }
}
