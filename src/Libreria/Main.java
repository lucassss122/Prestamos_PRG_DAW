package Libreria;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws UsuarioInvalidoException, PrestamoInvalidoException, UsuarioRepetidoException, LibroNoDisponibleException, UsuarioSancionadoException, Exception {
    try {
      Usuario u1 = new Usuario("lucas", "lucassegovia@gmail.com", "SOC25123", LocalDate.of(2007, 12, 27));
      System.out.println(u1);
      Prestamo p1 = new Prestamo("SOR1234", u1, "Armagedon", LocalDate.of(2026, 2, 1));
      System.out.println(p1);
      System.out.println();
      GestorBiblioteca g2 = new GestorBiblioteca();
      g2.registrarUsuario(u1);

    } catch (UsuarioInvalidoException uie) {
      System.out.println(uie.getMessage());
    } catch (PrestamoInvalidoException pie) {
      System.out.println(pie.getMessage());
    }
    try {
      Usuario u2 = new Usuario("Mario", "mariop@gmail.com", "SOC58123", LocalDate.of(2007, 12, 28));
      GestorBiblioteca g2 = new GestorBiblioteca();
      g2.registrarUsuario(u2);
    } catch (UsuarioRepetidoException ure) {
      System.out.println(ure.getMessage());
    }
    try {
      GestorBiblioteca g1 = new GestorBiblioteca();
      Usuario u2 = new Usuario("Mario", "mariop@gmail.com", "SOC58123", LocalDate.of(2007, 12, 28));
      g1.registrarUsuario(u2);
      System.out.println(g1);
      g1.registrarUsuario(u2);
      g1.registrarPrestamo("SOR2344", "Pajagedon", LocalDate.of(2026, 1, 3), u2);
      g1.registrarPrestamo("SOR2344", "Pajagedon", LocalDate.of(2026, 2, 3), u2);

    } catch (PrestamoInvalidoException pie) {
      System.out.println(pie.getMessage());
    } catch (UsuarioRepetidoException ure) {
      System.out.println(ure.getMessage());
    }
    try {
      GestorBiblioteca g2 = new GestorBiblioteca();
      Usuario u3 = new Usuario("Mario", "mariop@gmail.com", "SOC58123", LocalDate.of(2007, 12, 28));
      g2.registrarPrestamo("SOR2346", "Pajagedon", LocalDate.of(2026, 1, 3), u3);
      g2.registrarPrestamo("SOR2346", "Pajagedon", LocalDate.of(2026, 1, 3), u3);

    } catch (PrestamoInvalidoException pie) {
      System.out.println(pie.getMessage());
    } catch (UsuarioSancionadoException use){
      System.out.println(use.getMessage());
    } catch (LibroNoDisponibleException lnde){
      System.out.println(lnde.getMessage());
    }

  }
}