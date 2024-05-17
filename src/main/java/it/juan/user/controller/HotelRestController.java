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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//Indiciamos que es un controlador rest
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api")
@Validated


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
                .setExpiration(new Date(System.currentTimeMillis() + 9000000))
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
            @ApiResponse(responseCode = "200", description = "Se registra el hotel", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "El hotel no pudo ser registrado, compruebe los datos introducidos", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping("/insertar_hotel")
    public ResponseEntity<?> addHotel(@Valid @RequestBody Hotel hotel) {
        hotelService.anadirHotel(hotel);
        return ResponseEntity.ok(hotel);
    }


//    public ResponseEntity<?> addHotel(@Valid @RequestBody Hotel hotel) {
//        try {
//            hotelService.anadirHotel(hotel);
//            return ResponseEntity.ok(hotel);
//        } catch (Exception e) {
//            // Registrar el error para su análisis
//            System.err.println("Error al añadir el hotel" + e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el hotel");
//        }
//    }


//    public Hotel addUser(@RequestBody Hotel hotel) {
//
//        hotelService.anadirHotel(hotel);
//
//        return hotel;
//    }

//    @Operation(summary = "Registrar un nuevo hotel")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Se registra el hotel", content = @Content(schema = @Schema(implementation = Hotel.class))),
//            @ApiResponse(responseCode = "400", description = "El hotel no pudo ser registrado, no se puede proporcionar el id_Hotel", content = @Content(schema = @Schema(implementation = Response.class)))
//    })
//    @PostMapping("/insertar_hotel")
//    public ResponseEntity<?> addUser(@RequestBody Hotel hotel) {
//        // Verificar si se proporciona un id_Hotel
//        if (hotel.getIdHotel() != null || !hotel.getIdHotel().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede proporcionar el id_Hotel al registrar un nuevo hotel");
//        }
//
//        // Si no se proporciona el id_Hotel, agregar el hotel
//        hotelService.anadirHotel(hotel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
//    }




    @Operation(summary = "Lista de hoteles por categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron hoteles para la categoría especificada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_hotel_categoria/{categoria}")
    public ResponseEntity<?> buscarHotelesPorCategoria(@PathVariable Integer categoria) {
        List<Hotel> hoteles = hotelService.findByCategoria(categoria);
        if (hoteles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La categoria indicada no existe ");
        }
        return ResponseEntity.ok(hoteles);
    }



    @Operation(summary = "Lista de hoteles por localidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "La localidad indicada no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_hotel_localidad/{localidad}")
    public ResponseEntity<?> buscarHotelesPorLocalidad(@PathVariable String localidad) {
        List<Hotel> hoteles = hotelService.findByLocalidad(localidad);
        if (hoteles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La localidad indicada no existe ");
        }
        return ResponseEntity.ok(hoteles);
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
    public ResponseEntity<?> anadirHabitacion(@Valid @RequestBody Habitacion habitacion) {
        try {
            Habitacion habitacionAgregada = hotelService.anadirHabitacion(habitacion);
            return ResponseEntity.ok(habitacionAgregada);
        } catch (Exception e) {
            // Registrar el error para su análisis
            System.err.println("Error al añadir la habitación: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la habitación");
        }
    }

//    public Habitacion anadirHabitacion(@RequestBody Habitacion habitacion) {
//
//        return hotelService.anadirHabitacion(habitacion);
//    }


//    public ResponseEntity<?> anadirHabitacion(@Valid @RequestBody Habitacion habitacion) {
//        try {
//            Habitacion habitacionAgregada = hotelService.anadirHabitacion(habitacion);
//            return ResponseEntity.ok(habitacionAgregada);
//        } catch (HttpMessageNotReadableException e) {
//            throw e; // Permitir que HabitacionExceptionHandler maneje esta excepción
//        } catch (MethodArgumentTypeMismatchException e) {
//            throw e; // Permitir que HabitacionExceptionHandler maneje esta excepción
//        } catch (Exception e) {
//            // Registrar el error para su análisis
//            System.err.println("Error al añadir la habitación: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la habitación");
//        }
//    }








    @Operation(summary = "Elimina una habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la habitacion", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/eliminar_habitacion/{id_Habitacion}")
    public String eliminarHabitacion(@PathVariable int id_Habitacion) {
        hotelService.eliminarHabitacion(id_Habitacion);

        return "Se elimina la habitacion";
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


    @Operation(summary = "Modifica la habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de habitaciones", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No hay habitaciones para esas condiciones", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/listar_tamano_precio")
    public List<Habitacion> habitaciones_Tamano_Precio(
            @RequestParam double capacidad_Minima,
            @RequestParam double capacidad_Maxima,
            @RequestParam double precio_Minimo,
            @RequestParam double precio_Maximo)
    {
        return hotelService.habitaciones_Tamano_Precio(capacidad_Minima, capacidad_Maxima, precio_Minimo, precio_Maximo);
    }

    @Operation(summary = "Elimina un hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el hotel", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "El hotel no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })

    @DeleteMapping("borrar_hotel/{idHotel}")
    public String deteteHotel(@PathVariable int idHotel) {

        Hotel hotel = hotelService.findById(idHotel);

        if(hotel == null) {
            throw new RuntimeException("Hotel id not found -"+idHotel);
        }

        hotelService.deleteById(idHotel);

        //Esto método, recibira el id de un hotel por URL y se borrará de la bd.
        return "Hotel con id "+idHotel+ " borrado";
    }

}
