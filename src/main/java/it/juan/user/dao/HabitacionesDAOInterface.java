package it.juan.user.dao;

import it.juan.user.entity.Habitacion;

public interface HabitacionesDAOInterface {


    public void anadirHabitacion(Habitacion habitacion);

    public void eliminarHabitacion(int idHabitacion);

    public void modificarOcupacion(int idHabitacion);
}
