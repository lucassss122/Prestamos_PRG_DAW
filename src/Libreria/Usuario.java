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
      throw new UsuarioInvalidoException("Usuario invalido");
    }
    if (email.matches("SOC+[0-9]{5}")) {
      this.numeroSocio = numero;
    }
    else {
      throw new UsuarioInvalidoException("Usuario invalido");
    }
    this.fechaRegistro = fechaRegistro;
  }
}
