package Libreria;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws UsuarioInvalidoException, PrestamoInvalidoException, UsuarioRepetidoException, LibroNoDisponibleException, UsuarioSancionadoException, Exception {
    Scanner in = new Scanner (System.in);
    int resp;
    System.out.println("1. Registrar nuevo usuario");
    System.out.println("2. Realizar prestamo de libro");
    System.out.println("3. Devolver libro");
    System.out.println("4. Consultar estado de usuario");
    System.out.println("5. Mostrar prestamos activos");
    System.out.println("6. Mostrar usuarios sancionados");
    System.out.println("7. Actualizar sanciones");
    System.out.println("8. Salir");
    resp = in.nextInt();
    if(resp==1){
      String [] arrayFecha = new String[3];

      String nombre;
      String email;
      String numero;
      String fecha;
      int dia = 0;
      int mes = 0;
      int anio = 0;

      System.out.println("Escribe el nombre de usuario");
      nombre = in.nextLine();
      in.nextLine();
      System.out.println("Escribe el email");
      email = in.nextLine();
      System.out.println("Escribe el numero de usuario");
      numero = in.nextLine();
      System.out.println("Escribe la fecha de nacimiento");
      fecha = in.nextLine();
      arrayFecha = fecha.split("/");
      arrayFecha[0] = String.valueOf(dia);
      arrayFecha[1] = String.valueOf(mes);
      arrayFecha[2] = String.valueOf(anio);

      Usuario nuevo = new Usuario(nombre, email, numero, LocalDate.of(anio, mes, dia));


    }
  }
}