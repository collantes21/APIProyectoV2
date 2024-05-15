package it.juan.user.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Entity
@Table(name="Habitacion")
public class Habitacion {

    @Schema(description = "Id habitacion", example = "1", required = true)
    @Id
    @Column(name = "id_Habitacion")
    private int id_Habitacion;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_Hotel", nullable = false)


    @Schema(description = "id_Hotel", example = "7", required = true)
    @NotBlank
    @Column(name="id_Hotel")
    private int id_Hotel;

    @Schema(description = "capacidad de la habitacion", example = "200.0", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="capacidad")
    private int capacidad;

    @Schema(description = "Precio noche", example = "150.0", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="precioNoche")
    private double precio_Noche;

    @Schema(description = "Incluye desayuno", example = "True", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="incluyeDesayuno")
    private Boolean incluye_Desayuno;

    @Schema(description = "Habitacion ocupada", example = "False", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column(name="ocupada")
    private Boolean ocupada;

    public Habitacion() {
    }

    public int getId_Habitacion() {
        return id_Habitacion;
    }

    public void setId_Habitacion(int id_Habitacion) {
        this.id_Habitacion = id_Habitacion;
    }


    public int getId_hotel() {
        return id_Hotel;
    }

    public void setId_hotel( int id_hotel) {
        this.id_Hotel = id_hotel;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio_Noche() {
        return precio_Noche;
    }

    public void setPrecio_Noche(double precio_Noche) {
        this.precio_Noche = precio_Noche;
    }

    public Boolean getIncluye_Desayuno() {
        return incluye_Desayuno;
    }

    public void setIncluye_Desayuno(Boolean incluye_Desayuno) {
        this.incluye_Desayuno = incluye_Desayuno;
    }

    public Boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(Boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id_Habitacion=" + id_Habitacion +
                "id_Hotel=" + id_Hotel +
                ", capacidad=" + capacidad +
                ", precio_Noche=" + precio_Noche +
                ", incluye_Desayuno=" + incluye_Desayuno +
                ", ocupada=" + ocupada +
                '}';
    }
}
