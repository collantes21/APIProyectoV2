package it.juan.user.entity;


import javax.persistence.*;

@Entity
@Table(name="Habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_Habitacion")
    private int id_Habitacion;

    @ManyToOne
    @JoinColumn(name = "id_Hotel")
    private Hotel hotel;

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

    public Habitacion(int id_Habitacion, Hotel hotel, double capacidad, double precio_Noche, Boolean incluye_Desayuno, Boolean ocupada) {
        this.id_Habitacion = id_Habitacion;
        this.hotel = hotel;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
                ", hotel=" + hotel +
                ", capacidad=" + capacidad +
                ", precio_Noche=" + precio_Noche +
                ", incluye_Desayuno=" + incluye_Desayuno +
                ", ocupada=" + ocupada +
                '}';
    }
}
