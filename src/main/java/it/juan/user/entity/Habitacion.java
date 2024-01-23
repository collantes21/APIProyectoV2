package it.juan.user.entity;


import javax.persistence.*;

@Entity
@Table(name="Habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_Habitacion")
    private int id_Habitacion;

    @Column(name = "idHotel")
    private int id_Hotel;

    @Column(name="capacidad")
    private double capacidad;

    @Column(name="precioNoche")
    private double precio_Noche;

    @Column(name="incluyeDesayuno")
    private Boolean incluye_Desayuno;

    @Column(name="ocupada")
    private Boolean ocupada;

    public Habitacion() {
    }

    public Habitacion(int id_Habitacion, int id_Hotel, double capacidad, double precio_Noche, Boolean incluye_Desayuno, Boolean ocupada) {
        this.id_Habitacion = id_Habitacion;
        this.id_Hotel = id_Hotel;
        this.capacidad = capacidad;
        this.precio_Noche = precio_Noche;
        this.incluye_Desayuno = incluye_Desayuno;
        this.ocupada = ocupada;
    }

    public int getId_Habitacion() {
        return id_Habitacion;
    }

    public void setId_Habitacion(int id_Habitacion) {
        this.id_Habitacion = id_Habitacion;
    }

    public int getId_Hotel() {
        return id_Hotel;
    }

    public void setId_Hotel(int id_Hotel) {
        this.id_Hotel = id_Hotel;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
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
                ", id_Hotel=" + id_Hotel +
                ", capacidad=" + capacidad +
                ", precio_Noche=" + precio_Noche +
                ", incluye_Desayuno=" + incluye_Desayuno +
                ", ocupada=" + ocupada +
                '}';
    }
}
