package it.juan.user.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@AllArgsConstructor
@Entity
@Table(name="Hotel")
public class Hotel {

    @Schema(description = "Id hotel", example = "1", required = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_Hotel")
    private int idHotel;

    @Schema(description = "Nombre hotel", example = "Hotel String", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="nombre")
    private String nombre;

    @Schema(description = "Descripcion hotel", example = "descripcion", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="descripcion")
    private String descripcion;

    @Schema(description = "Categoria hotel", example = "5", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="categoria")
    private String categoria;

    @Schema(description = "Tiene piscina", example = "True", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="tiene_piscina")
    private Boolean tiene_piscina;

    @Schema(description = "Localidad hotel", example = "Ciudad", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="localidad")
    private String localidad;

//    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Habitacion> habitaciones;

    public Hotel() {
    }

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
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
