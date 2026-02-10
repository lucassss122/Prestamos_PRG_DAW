package Libreria;


import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
  private String codigoLibro;
  private String tituloLibro;
  private Usuario socio;
  private LocalDate fechaPrestamo;
  private LocalDate fechaDevolucionPrevista;
  private LocalDate fechaDevolucionReal = null;

  public Prestamo(String codigo, Usuario usuario, String titulo, LocalDate fecha) throws PrestamoInvalidoException {
    if (codigo.matches("[A-Z]{3}+[0-9]{4}")) {
      this.codigoLibro = codigo;
    } else {
      throw new PrestamoInvalidoException("El codigo de usuario introducido no es correcto");
    }
    this.socio = usuario;

    if (titulo == null) {
      throw new PrestamoInvalidoException("El titulo esta vacio");
    }

    this.tituloLibro = titulo;

    if (fecha == null || fecha.isAfter(LocalDate.now())) {
      throw new PrestamoInvalidoException("La fecha de prestamo introducida es incorrecta");
    } else {
      this.fechaPrestamo = fecha;
    }

    this.fechaDevolucionPrevista = fechaPrestamo.plusDays(14);
  }

  public void registrarDevolucion(LocalDate fecha) throws PrestamoInvalidoException {
    if (fecha == null || fecha.isBefore(fechaPrestamo)) {
      throw new PrestamoInvalidoException("La fecha introducida es incorrecta");
    } else {
      this.fechaDevolucionReal = fecha;
    }
  }
/*
  public int calcularDiasRetraso() {
    if (fechaDevolucionReal != null) {
      return (int) ChronoUnit.DAYS.between(fechaDevolucionPrevista, fechaDevolucionReal);
      if (fechaDevolucionReal.isBefore(fechaDevolucionPrevista)) {

      } else {
        return (int) ChronoUnit.DAYS.between(fechaDevolucionPrevista, LocalDate.now());
      }
    }
  }
  */
}

