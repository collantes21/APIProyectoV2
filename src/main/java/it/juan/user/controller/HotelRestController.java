package it.juan.user.controller;

import it.juan.user.entity.Hotel;
import it.juan.user.service.HotelService;
import it.juan.user.service.HotelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Indiciamos que es un controlador rest
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api")


public class HotelRestController {


    @Autowired
    private HotelServiceInterface hotelService;

    @GetMapping("/hoteles")
    public List<Hotel> findAll(){
        //retornará todos los usuarios
        return hotelService.findAll();
    }

    @PostMapping("/insertar_hotel")
    public Hotel addUser(@RequestBody Hotel hotel) {

        hotelService.anadirHotel(hotel);

        return hotel;

    }

    @GetMapping("buscar_hotel_categoria/{categoria}")
    public List<Hotel> findByCategoria(@PathVariable String categoria) {
        return hotelService.findByCategoria(categoria);
    }

    @GetMapping("buscar_hotel_localidad/{localidad}")
    public List<Hotel> findByLocalidad(@PathVariable String localidad) {
        return (List<Hotel>) hotelService.findByLocalidad(localidad);
    }


}
