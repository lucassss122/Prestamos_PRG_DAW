package Libreria;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class GestorBiblioteca {
  private static final int MAX_USUARIOS = 50;
  private static final int MAX_PRESTAMOS = 200;
  Usuario[] usuarios = new Usuario[MAX_USUARIOS];
  Prestamo[] prestamos = new Prestamo[MAX_PRESTAMOS];
  String [] libros = new String[100];
  int numeroUsuarios = 0;
  int numeroPrestamos = 0;

  public Usuario[] getUsuarios() {
    return usuarios;
  }

  public GestorBiblioteca() {
    super();
  }

  public void registrarUsuario(Usuario usuario) throws UsuarioRepetidoException {
    for (int i = 0; i < numeroUsuarios; i++) {
      if (usuarios[i].equals(usuario)) {
        throw new UsuarioRepetidoException("El usuario ya esta registrado");
      }
    }
    System.out.println("Usuario registrado");
      usuarios[numeroUsuarios] = usuario;
      numeroUsuarios++;
  }


  public Prestamo registrarPrestamo(String codigo, String titulo, LocalDate f1, Usuario u1) throws Exception {
    boolean repetido = false;
    for (int i = 0; i < numeroPrestamos; i++) {
      if (prestamos[i].getCodigoLibro().equals(codigo)) {
        repetido = true;
        break;
      }
    }
    if (!codigo.matches("[A-Z]{3}[0-9]{4}")){
      throw new Exception("El codigo es incorrecto");
    } else if (!f1.isAfter(LocalDate.now())) {
      throw new PrestamoInvalidoException("La fecha introducida es incorrecta");
    } else if (repetido) {
      throw new LibroNoDisponibleException("El libro no se encuentra disponible");
    } else if (u1.estaSancionado()) {
      throw new UsuarioSancionadoException("El usuario introducido esta sancionado");
    }
    System.out.println("Prestamo registrado");
    Prestamo nuevo = new Prestamo(codigo, u1, titulo, f1);
    prestamos[numeroPrestamos] = nuevo;
    numeroPrestamos++;
    return nuevo;
  }
    @Override
    public String toString(){
      return Arrays.toString(usuarios);
  }

  public boolean devolverLibro(String codigo, LocalDate f1) throws PrestamoInvalidoException {
    boolean prestado = false;
    Prestamo encontrado = null;
    for (int i = 0; i < numeroPrestamos; i++) {
      if (prestamos[i].getCodigoLibro().equals(codigo) && prestamos[i].getFechaDevolucionReal() == null) {
        encontrado = prestamos[i];
        break;
      }
    }
    if(encontrado == null){
      return false;
    }
    //}registrarDevolucion(f1);
    if(f1.isBefore(encontrado.getFechaDevolucionPrevista())){
      throw new PrestamoInvalidoException("La fecha introducida es incorrecta");
    }
    else{
      int diasRetraso = (int) ChronoUnit.DAYS.between(f1,encontrado.getFechaDevolucionPrevista());
      encontrado.getSocio().sancionar(diasRetraso, f1);
    }
    return true;
  }
}



