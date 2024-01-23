package it.juan.user.service;

import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;

import java.util.List;

public interface HotelServiceInterface {


    public List<Hotel> findAll();

    public List<Hotel> findByLocalidad(String localidad);

    public List<Hotel> findByCategoria(String categoria);
    public void anadirHotel(Hotel hotel);
    public void deleteById(int id_Hotel);

    public void anadirHabitacion(Habitacion habitacion);

    public void eliminarHabitacion(int id_Habitacion);

    public void modificarOcupacion(int id_Habitacion);

    public List<Habitacion> habitaciones_Tamano_Precio(double capacidad_Minima, double capacidad_Maxima, double precio_Minimo, double precio_Maximo);
}
