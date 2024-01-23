package it.juan.user.dao;

import it.juan.user.entity.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
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

        Query<Hotel> theQuery = currentSession.createQuery("SELECT u from Hotel u WHERE U.categoria= :categoria", Hotel.class);

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
    public void deleteById(int idHotel) {

        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();


        Query theQuery = currentSession.createQuery("delete from Hotel u where idHotel IN (:idHotel)");

        theQuery.setParameter("idHotel", idHotel);
        theQuery.executeUpdate();
        t.commit();
        currentSession.close();
    }
}
