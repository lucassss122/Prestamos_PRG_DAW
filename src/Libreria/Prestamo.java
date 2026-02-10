package Libreria;


import java.time.LocalDate;

public class Prestamo {
  private String codigoLibro;
  private String tituloLibro;
  private Usuario socio;
  private LocalDate fechaPrestamo;
  private LocalDate fechaDevolucionPrevista;
  private LocalDate fechaDevolucionReal = null;

  public Prestamo(String codigo, Usuario usuario, String titulo, LocalDate fecha) throws PrestamoInvalidoException{
    if(codigo.matches("SOC+[0-9]{5}")){
        this.codigoLibro=codigo;
    }
    else{
        throw new PrestamoInvalidoException("EL usuario es invalido");
    }
    this.socio = usuario;

    this.tituloLibro = titulo;
    this.fechaPrestamo = fecha;
  }
}
