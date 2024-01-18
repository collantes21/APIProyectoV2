package it.juan.user.service;

import it.juan.user.entity.Hotel;

import java.util.List;

public interface HotelServiceInterface {


    public List<Hotel> findAll();

    public Hotel findByLocalidad(String localidad);

    public Hotel findByCategoria(String categoria);
    public void anadirHotel(Hotel hotel);
}
