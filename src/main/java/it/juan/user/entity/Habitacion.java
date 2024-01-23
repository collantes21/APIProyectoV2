package it.juan.user.entity;


import javax.persistence.*;

@Entity
@Table(name="Habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idHabitacion")
    private int idHabitacion;


    @Column(name="idHotel")
    private int idHotel;

    @Column(name="capacidad")
    private double precioNoche;

    @Column(name="incluyeDesayuno")
    private boolean incluyeDesayuno;

    @Column(name="ocupada")
    private boolean ocupada;


    public Habitacion() {
    }

    public Habitacion(int idHabitacion, int idHotel, double precioNoche, boolean incluyeDesayuno, boolean ocupada) {
        this.idHabitacion = idHabitacion;
        this.idHotel = idHotel;
        this.precioNoche = precioNoche;
        this.incluyeDesayuno = incluyeDesayuno;
        this.ocupada = ocupada;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public boolean isIncluyeDesayuno() {
        return incluyeDesayuno;
    }

    public void setIncluyeDesayuno(boolean incluyeDesayuno) {
        this.incluyeDesayuno = incluyeDesayuno;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
