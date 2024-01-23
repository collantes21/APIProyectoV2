package it.juan.user.dao;

import it.juan.user.entity.Hotel;

import java.util.List;

public interface HotelDAOInterface {

    public List<Hotel> findAll();

    public Hotel findByLocalidad(String localidad);

    public Hotel findByCategoria(String categoria);
    public void anadirHotel(Hotel hotel);
    public void deleteById(int idHotel);

}
