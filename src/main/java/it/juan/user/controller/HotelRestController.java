package it.juan.user.controller;

import it.juan.user.dao.HabitacionesDAOInterface;
import it.juan.user.entity.Habitacion;
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

    @DeleteMapping("/eliminar_hotel/{id_Hotel}")
    public void deleteById(@PathVariable int id_Hotel) {
        hotelService.deleteById(id_Hotel);
    }

    @PostMapping("/insertar_habitacion")
    public void anadirHabitacion(@RequestBody Habitacion habitacion) {
        hotelService.anadirHabitacion(habitacion);
    }

    @DeleteMapping("/eliminar_habitacion/{id_Habitacion}")
    public void eliminarHabitacion(@PathVariable int id_Habitacion) {
        hotelService.eliminarHabitacion(id_Habitacion);
    }


    @PutMapping("/modificar_ocupacion/{id_Habitacion}")
    public void modificarOcupacion(@PathVariable int id_Habitacion) {
        hotelService.modificarOcupacion(id_Habitacion);
    }

    @GetMapping("/listar_tamano_precio")
    public List<Habitacion> habitaciones_Tamano_Precio(@RequestParam double capacidad_Minima, @RequestParam double capacidad_Maxima, @RequestParam double precio_Minimo, @RequestParam double precio_Maximo) {
        return hotelService.habitaciones_Tamano_Precio(capacidad_Minima, capacidad_Maxima, precio_Minimo, precio_Maximo);
    }

}
