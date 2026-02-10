package Libreria;
import java.time.*;

public class Usuario {
  private String nombre;
  private String email;
  private String numeroSocio;
  private LocalDate fechaRegistro;
  private boolean sancionado;
  private LocalDate fechaFinSancion;

  public Usuario(String nombre, String email, String numero, LocalDate fechaRegistro) throws UsuarioInvalidoException{
    this.nombre = nombre;
    if (email.matches(".*@.*\\..*")) {
      this.email = email;
    }
    else{
      throw new UsuarioInvalidoException("Email invalido");
    }
    if (numero.matches("SOC+[0-9]{5}")) {
      this.numeroSocio = numero;
    }
    else {
      throw new UsuarioInvalidoException("Codigo de usuario invalido");
    }
    this.fechaRegistro = fechaRegistro;
  }

  public void sancionar(int dias, LocalDate fecha_san){
    LocalDate fecha_fin = fecha_san.plusDays((long)dias);
    System.out.println(fecha_fin);
    this.sancionado = true;
  }

  public void levantarSancion(){
    sancionado = false;
  }

  public boolean estaSancionado(){
    return sancionado;
  }

  @Override
  public String toString(){
    return "Nombre del usuario: "+this.nombre+" Email: "+this.email+" Fecha de registro: "+
        this.fechaRegistro+" Numero de usuario: "+this.numeroSocio;
  }
}
