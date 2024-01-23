package it.juan.user.service;

import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;

import java.util.List;

public interface HotelServiceInterface {


    public List<Hotel> findAll();

    public Hotel findByLocalidad(String localidad);

    public Hotel findByCategoria(String categoria);
    public void anadirHotel(Hotel hotel);
    public void deleteById(int idHotel);

    public void anadirHabitacion(Habitacion habitacion);

    public void eliminarHabitacion(int idHabitacion);

    public void modificarOcupacion(int idHabitacion);
}
