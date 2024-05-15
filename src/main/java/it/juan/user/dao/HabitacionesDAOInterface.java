package it.juan.user.dao;

import it.juan.user.entity.Habitacion;

import java.util.List;

public interface HabitacionesDAOInterface {


    public Habitacion anadirHabitacion(Habitacion habitacion);

    public void eliminarHabitacion(int idHabitacion);

    public void modificarOcupacion(int idHabitacion);

    public List<Habitacion> habitaciones_Tamano_Precio(double capacidad_Minima, double capacidad_Maxima, double precio_Minimo, double precio_Maximo);


}
