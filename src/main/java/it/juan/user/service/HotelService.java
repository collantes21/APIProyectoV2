package it.juan.user.service;


import it.juan.user.dao.HabitacionesDAO;
import it.juan.user.dao.HabitacionesDAOInterface;
import it.juan.user.dao.HotelDAO;
import it.juan.user.dao.HotelDAOInterface;
import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HotelService implements HotelServiceInterface {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HotelDAOInterface hotelDAO;
    private HabitacionesDAOInterface habitacionesDAO;

    @Override
    public List<Hotel> findAll() {
        List<Hotel> listHoteles= hotelDAO.findAll();
        return listHoteles;
    }

    @Override
    public Hotel findByLocalidad(String localidad) {
        return null;
    }

    @Override
    public Hotel findByCategoria(String categoria) {
        return null;
    }

    @Override
    public void anadirHotel(Hotel hotel) {

    }
    @Override
    public void deleteById(int idHotel) {

    }

    @Override
    public void anadirHabitacion(Habitacion habitacion) {

    }

    @Override
    public void eliminarHabitacion(int idHabitacion) {

    }

    @Override
    public void modificarOcupacion(int idHabitacion) {

    }
}
