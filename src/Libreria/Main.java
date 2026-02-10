package Libreria;

import java.time.LocalDate;

public class Main {
  public static void main(String[] args) throws UsuarioInvalidoException {
    try {
      Usuario u1 = new Usuario("lucas", "lucassegovia@gmail.com", "SOC25123", LocalDate.of(2026, 12, 27));
      u1.sancionar(20, LocalDate.now());
      System.out.println(u1);
    }catch (UsuarioInvalidoException uie){
      System.out.println("El usuario introducido no es valido");
    }

  }
}