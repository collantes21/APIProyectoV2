package it.juan.user.service;


import it.juan.user.dao.HabitacionesDAOInterface;
import it.juan.user.dao.HotelDAOInterface;
import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements HotelServiceInterface {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HotelDAOInterface hotelDAO;
    @Autowired
    private HabitacionesDAOInterface habitacionesDAO;

//    public HotelService(HabitacionesDAOInterface habitacionesDAO) {
//        this.habitacionesDAO = habitacionesDAO;
//    }


    @Override
    public List<Hotel> findAll() {
        List<Hotel> listHoteles= hotelDAO.findAll();
        return listHoteles;
    }

    @Override
    public List<Hotel> findByLocalidad(String localidad) {

        return hotelDAO.findByLocalidad(localidad);
    }

    @Override
    public List<Hotel> findByCategoria(String categoria) {

        return hotelDAO.findByCategoria(categoria);
    }

    @Override
    public void anadirHotel(Hotel hotel) {
        hotelDAO.anadirHotel(hotel);
    }
    @Override
    public void deleteById(int id_Hotel) {
        hotelDAO.deleteById(id_Hotel);
    }



    ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void anadirHabitacion(Habitacion habitacion) {
        if (habitacion==null)
            System.out.println("nada1");
            habitacionesDAO.anadirHabitacion(habitacion);
    }

    @Override
    public void eliminarHabitacion(int id_Habitacion) {
        habitacionesDAO.eliminarHabitacion(id_Habitacion);
    }

    @Override
    public void modificarOcupacion(int id_Habitacion) {
        habitacionesDAO.modificarOcupacion(id_Habitacion);
    }

    @Override
    public List<Habitacion> habitaciones_Tamano_Precio(double capacidad_Minima, double capacidad_Maxima, double precio_Minimo, double precio_Maximo) {
        return habitacionesDAO.habitaciones_Tamano_Precio(capacidad_Minima, capacidad_Maxima, precio_Minimo, precio_Maximo);
    }


}
