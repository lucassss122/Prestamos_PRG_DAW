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

  public LocalDate getFechaPrestamo() {
    return fechaPrestamo;
  }

  public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
    this.fechaDevolucionReal = fechaDevolucionReal;
  }

  public LocalDate getFechaDevolucionReal() {
    return fechaDevolucionReal;
  }

  public LocalDate getFechaDevolucionPrevista() {
    return fechaDevolucionPrevista;
  }

  public Usuario getSocio() {
    return socio;
  }

  public String getCodigoLibro() {
    return codigoLibro;
  }

  public Prestamo(String codigo, Usuario usuario, String titulo, LocalDate fecha) throws PrestamoInvalidoException {
    if (codigo.matches("[A-Z]{3}[0-9]{4}")) {
      this.codigoLibro = codigo;
    } else {
      throw new PrestamoInvalidoException("El codigo introducido no es correcto ");
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

  public int calcularDiasRetraso() {
    if (fechaDevolucionReal != null) {
      int dias = (int) ChronoUnit.DAYS.between(fechaDevolucionPrevista, fechaDevolucionReal);
      if (dias<0){
        return 0;
      }
      return dias;
    }
    else{
      return (int) ChronoUnit.DAYS.between(fechaPrestamo, LocalDate.now());
      }
    }

    public boolean estaRetrasado(){
    if(LocalDate.now().isAfter(fechaDevolucionPrevista)) {
      return true;
    }
      else{
        return false;
      }
    }

    @Override
    public String toString(){
    return "Libro: "+tituloLibro+", Codigo: "+codigoLibro+"\n"+"Prestamo solicitado por: "+socio.getNombre()+"\n"+"Fecha del prestamo: " +
        fechaPrestamo+", Fecha devolucion prevista: "+fechaDevolucionPrevista+", Fecha de devolucion real: "+fechaDevolucionReal;
    }
  }


