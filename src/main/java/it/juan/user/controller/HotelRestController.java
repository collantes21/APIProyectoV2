package it.juan.user.controller;

import it.juan.user.entity.Hotel;
import it.juan.user.service.HotelService;
import it.juan.user.service.HotelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
