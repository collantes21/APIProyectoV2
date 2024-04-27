package it.juan.user.controller;

import com.mysql.cj.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.juan.user.dao.HabitacionesDAOInterface;
import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;
import it.juan.user.entity.User;
import it.juan.user.service.HotelService;
import it.juan.user.service.HotelServiceInterface;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


//Indiciamos que es un controlador rest
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api")


public class HotelRestController {


    @Autowired
    private HotelServiceInterface hotelService;


    @PostMapping("user")
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) {

        if ((username.equals("juan")) && (password.equals("juan"))) {
            System.out.println("Me crea el token");
            String token = getJWTToken(username);
            User user = new User();
            user.setUsuario(username);
            user.setPassword(password);
            user.setToken(token);

            return user;
        } else

            return null;
    }




    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }



    @Operation(summary = "Obtiene el listado de hoteles")
    //	@ApiResponses: Permite documentar la forma en que una operación concreta responde,
    // teniendo en cuenta las posibles respuestas en caso de error
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado hoteles",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hotel.class)))),
    })
    @GetMapping("/hoteles")
    public List<Hotel> findAll(){
        //retornará todos los usuarios
        return hotelService.findAll();
    }


    @Operation(summary = "Registrar un nuevo hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el hotel", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "El hotel no pudo ser registrado, compruebe los datos introducidos", content = @Content(schema = @Schema(implementation = Response.class)))
    @PostMapping("/insertar_hotel")
    public Hotel addUser(@RequestBody Hotel hotel) {

        hotelService.anadirHotel(hotel);

        return hotel;
    }


    @Operation(summary = "Lista de hoteles por categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron hoteles para la categoría especificada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_hotel_categoria/{categoria}")
    public List<Hotel> findByCategoria(@PathVariable String categoria) {
        return hotelService.findByCategoria(categoria);
    }



    @Operation(summary = "Lista de hoteles por localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron hoteles para la localidad especificada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_hotel_localidad/{localidad}")
    public List<Hotel> findByLocalidad(@PathVariable String localidad) {
        return (List<Hotel>) hotelService.findByLocalidad(localidad);
    }



    @DeleteMapping("/eliminar_hotel/{id_Hotel}")
    public void deleteById(@PathVariable int id_Hotel) {
        hotelService.deleteById(id_Hotel);
    }


    @Operation(summary = "Inserta una habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La habitacion se añadio correctamente", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La habitacion no pudo añadirse", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping("/insertar_habitacion")
    public void anadirHabitacion(@RequestBody Habitacion habitacion) {
        hotelService.anadirHabitacion(habitacion);
    }


    @Operation(summary = "Elimina una habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la habitacion", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/eliminar_habitacion/{id_Habitacion}")
    public void eliminarHabitacion(@PathVariable int id_Habitacion) {
        hotelService.eliminarHabitacion(id_Habitacion);
    }

    @Operation(summary = "Modifica la habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habitacion modificada", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/modificar_ocupacion/{id_Habitacion}")
    public void modificarOcupacion(@PathVariable int id_Habitacion) {
        hotelService.modificarOcupacion(id_Habitacion);
    }


    @GetMapping("/listar_tamano_precio")
    public List<Habitacion> habitaciones_Tamano_Precio(
            @RequestParam double capacidad_Minima,
            @RequestParam double capacidad_Maxima,
            @RequestParam double precio_Minimo,
            @RequestParam double precio_Maximo)
    {
        return hotelService.habitaciones_Tamano_Precio(capacidad_Minima, capacidad_Maxima, precio_Minimo, precio_Maximo);
    }

}
