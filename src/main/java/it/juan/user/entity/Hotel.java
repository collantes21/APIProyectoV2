package it.juan.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Hotel")
public class Hotel {

    @Schema(description = "Id hotel", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Hotel")
    private Integer idHotel;

    @Schema(description = "Nombre hotel", example = "Hotel String", required = true)
    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @Schema(description = "Descripcion hotel", example = "descripcion", required = false)
    @Column(name = "descripcion")
    private String descripcion;

    @Schema(description = "Categoria hotel", example = "5", required = true)
    @Min(value = 1, message = "La categoría debe ser al menos 1")
    @Max(value = 5, message = "La categoría no puede ser mayor a 5")
    @NotNull(message = "La categoría es obligatoria")
    @Column(name = "categoria")
    private Integer categoria;

    @Schema(description = "Tiene piscina", example = "true", required = true)
    @NotNull(message = "El campo tiene_piscina es obligatorio")
    @Column(name = "tiene_piscina")
    private Boolean tiene_piscina;

    @Schema(description = "Localidad hotel", example = "Ciudad", required = true)
    @NotBlank(message = "La localidad es obligatoria")
    @Column(name = "localidad")
    private String localidad;

//    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Habitacion> habitaciones;



    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public boolean isTiene_piscina() {
        return tiene_piscina;
    }

    public void setTiene_piscina(boolean tiene_piscina) {
        this.tiene_piscina = tiene_piscina;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tiene_piscina=" + tiene_piscina +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
