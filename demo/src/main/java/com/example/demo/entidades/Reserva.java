package com.example.demo.entidades;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreserva")
    private Long idReserva;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fechainicio")
    private LocalDate fechaInicio;

    @Column(name = "fechasalida")
    private LocalDate fechaSalida;

    @Column(name = "costo")
    private double costo;

    @Column(name = "formapago")
    private String formaPago;

    @ManyToOne
    @JoinColumn(name = "idlugar")
    private Lugar lugar;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(String nombre, LocalDate fechaInicio, LocalDate fechaSalida, double costo, String formaPago, Usuario usuario, Lugar lugar) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaSalida = fechaSalida;
        this.costo = costo;
        this.formaPago = formaPago;
        this.usuario = usuario;
        this.lugar = lugar;
    }
    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
