package it.juan.user.dao;

import it.juan.user.entity.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class HotelDAO implements HotelDAOInterface{

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Hotel> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Hotel> theQuery = currentSession.createQuery("SELECT u from Hotel u", Hotel.class);

        List<Hotel> users = theQuery.getResultList();

        return users;
    }

    @Override
    public Hotel findByLocalidad(String localidad) {
        Session currentSession = entityManager.unwrap(Session.class);

        Hotel hotel = currentSession.get(Hotel.class, localidad);

        return hotel;
    }

    @Override
    public Hotel findByCategoria(String categoria) {
        Session currentSession = entityManager.unwrap(Session.class);

        Hotel hotel = currentSession.get(Hotel.class, categoria);

        return hotel;
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
