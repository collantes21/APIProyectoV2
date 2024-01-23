package it.juan.user.dao;

import it.juan.user.entity.Hotel;

import java.util.List;

public interface HotelDAOInterface {

    public List<Hotel> findAll();

    public List<Hotel> findByLocalidad(String localidad);

    public List <Hotel> findByCategoria(String categoria);
    public void anadirHotel(Hotel hotel);
    public void deleteById(int idHotel);

}
