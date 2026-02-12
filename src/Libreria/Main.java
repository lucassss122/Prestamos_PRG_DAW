package Libreria;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws UsuarioInvalidoException, PrestamoInvalidoException {
    try {
      Usuario u1 = new Usuario("lucas", "lucassegovia@gmail.com", "SOC25123", LocalDate.of(2026, 12, 27));
      System.out.println(u1);
      Prestamo p1 = new Prestamo("SOR1234", u1, "Armagedon", LocalDate.of(2026,2,1) );
      System.out.println(p1);
      System.out.println();
      GestorBiblioteca g1 = new GestorBiblioteca();
      g1.registrarUsuario(u1);
      System.out.println(Arrays.toString(g1.usuarios));
      g1.registrarPrestamo("SOR2344","Pajagedon", LocalDate.of(2026,1,3),u1);
    }catch (UsuarioInvalidoException uie){
      System.out.println(uie.getMessage());
    } catch (PrestamoInvalidoException pie) {
      System.out.println(pie.getMessage());
    }

  }
}