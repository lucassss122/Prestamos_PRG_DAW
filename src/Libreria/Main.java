package Libreria;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws UsuarioInvalidoException, PrestamoInvalidoException, UsuarioRepetidoException, LibroNoDisponibleException, UsuarioSancionadoException,NumberFormatException, Exception {
    Scanner in = new Scanner (System.in);
    int resp;
    GestorBiblioteca g1 = new GestorBiblioteca();
    do{
      System.out.println();
      System.out.println("---GESTOR DE BIBLIOTECA---");
      System.out.println();
    System.out.println("1. Registrar nuevo usuario");
    System.out.println("2. Realizar prestamo de libro");
    System.out.println("3. Devolver libro");
    System.out.println("4. Consultar estado de usuario");
    System.out.println("5. Mostrar prestamos activos");
    System.out.println("6. Mostrar usuarios sancionados");
    System.out.println("7. Actualizar sanciones");
    System.out.println("8. Salir");
    resp = Integer.parseInt(in.nextLine());
      try {
        if (resp == 1) {
          String[] arrayFecha = new String[3];
          String nombre;
          String email;
          String numero;
          String fecha;
          int dia = 0;
          int mes = 0;
          int anio = 0;

          System.out.println("Escribe el nombre de usuario");
          nombre = in.nextLine();
          System.out.println("Escribe el email");
          email = in.nextLine();
          System.out.println("Escribe el numero de usuario");
          numero = in.nextLine();
          System.out.println("Escribe la fecha de nacimiento");
          fecha = in.nextLine();
          arrayFecha = fecha.split("/");
          dia = Integer.parseInt(arrayFecha[0]);
          mes = Integer.parseInt(arrayFecha[1]);
          anio = Integer.parseInt(arrayFecha[2]);
          Usuario nuevo = new Usuario(nombre, email, numero, LocalDate.of(anio, mes, dia));
          g1.registrarUsuario(nuevo);
          System.out.println();

        } else if (resp == 2) {
          String[] arrayFecha;
          int dia;
          int mes;
          int anio;

          String codigo;
          String titulo;
          String fecha;

          Usuario u1 = g1.usuarios[g1.numeroUsuarios - 1];

          System.out.println("Escribe el codigo del libro");
          codigo = in.nextLine();
          System.out.println("Escribe el titulo del libro");
          titulo = in.nextLine();
          System.out.println("Escribe la fecha de inicio del prestamo");
          fecha = in.nextLine();
          arrayFecha = fecha.split("/");
          dia = Integer.parseInt(arrayFecha[0]);
          mes = Integer.parseInt(arrayFecha[1]);
          anio = Integer.parseInt(arrayFecha[2]);
          Prestamo nuevo = new Prestamo(codigo, u1, titulo, LocalDate.of(anio, mes, dia));
          g1.registrarPrestamo(codigo, titulo, LocalDate.of(anio, mes, dia), u1);
          System.out.println("Fecha de devolucion prevista " + nuevo.getFechaDevolucionPrevista());
          System.out.println();
        } else if (resp == 3) {
          String[] arrayFecha;
          int dia;
          int mes;
          int anio;

          String codigo;
          String fecha;

          System.out.println("Escribe el codigo del libro a devolver");
          codigo = in.nextLine();
          System.out.println("Escribe la fecha de devolucion");
          fecha = in.nextLine();

          arrayFecha = fecha.split("/");
          dia = Integer.parseInt(arrayFecha[0]);
          mes = Integer.parseInt(arrayFecha[1]);
          anio = Integer.parseInt(arrayFecha[2]);

          for (int i = 0; i < g1.numeroPrestamos; i++) {
            g1.devolverLibro(codigo, LocalDate.of(anio, mes, dia));
            if (codigo.equals(g1.prestamos[i].getCodigoLibro())) {
              System.out.println("Devolucion registrada con " + g1.prestamos[i].calcularDiasRetraso() + " dias de retraso");
              if (g1.prestamos[i].calcularDiasRetraso() > 0) {
                System.out.println("Usuario sancionado por " + g1.prestamos[i].calcularDiasRetraso() + " dias");
                g1.usuarios[g1.numeroUsuarios - 1].sancionar(g1.prestamos[i].calcularDiasRetraso(), LocalDate.now());
              }
            }
          }
          g1.quitarPrestamo(codigo);
          System.out.println();
        } else if (resp == 4) {
          String codigo;
          System.out.println("Introduce el codigo de usuario");
          codigo = in.nextLine();
          for (int i = 0; i < g1.numeroUsuarios; i++) {
            if (codigo.matches(g1.usuarios[i].getNumeroSocio())) {
              System.out.println(g1.usuarios[i]);
            }
          }
          System.out.println();
        } else if (resp == 5) {
          System.out.println(Arrays.toString(g1.prestamos));
          System.out.println();
        } else if (resp == 6) {
          g1.mostrarSancionados();
          System.out.println();
        } else if (resp == 7) {
          String codigo;
          System.out.println("Escribe el codigo de usuario para eliminar la sancion");
          codigo = in.nextLine();
          for (int i = 0; i < g1.numeroUsuarios; i++) {
            if (codigo.matches(g1.usuarios[i].getNumeroSocio())) {
              g1.usuarios[i].levantarSancion();
              System.out.println("La sancion ha sido suprimida");
            }
          }
          System.out.println();
        }
      } catch (NumberFormatException nfe){
        System.out.println("Introduce un numero del 1 al 8");
      } catch (Exception e){
        System.out.println(e.getMessage());
        System.out.println();
      }

    }while (resp != 8);
  }
}