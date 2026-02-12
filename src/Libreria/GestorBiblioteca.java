package Libreria;

import java.security.PublicKey;
import java.time.LocalDate;

public class GestorBiblioteca {
  private static final int MAX_USUARIOS = 50;
  private static final int MAX_PRESTAMOS = 200;
  Usuario [] usuarios = new Usuario[MAX_USUARIOS];
  Prestamo [] prestamos = new Prestamo[MAX_PRESTAMOS];
  int numeroUsuarios = 0;
  int numeroPrestamos = 0;

  public GestorBiblioteca(){
  }

  public void registrarUsuario(Usuario usuario){
    usuarios[numeroUsuarios] = usuario;
  }

  public void registrarPrestamo(String codigo, String titulo, LocalDate f1, Usuario u1) throws PrestamoInvalidoException, LibroNoDisponibleException, UsuarioSancionadoException {
    boolean repetido = false;

    for (int i = 0; i < numeroPrestamos; i++) {
      if (prestamos[i].getCodigoLibro().equals(codigo)) {
        repetido = true;
        break;
      }
    }

    if (codigo.matches("[A-Z]{3}+[0-9]{4}") && titulo != null &&
        f1.isBefore(LocalDate.now()) && !u1.estaSancionado()) {

    }
      else if(f1.isAfter(LocalDate.now())){
        throw new PrestamoInvalidoException("La fecha introducida es incorrecta");
      }
      else if (repetido){
        throw new LibroNoDisponibleException("El libro no se encuentra disponible");
    }
      else if(u1.estaSancionado()){
        throw new UsuarioSancionadoException("El usuario introducido esta sancionado");
    }
    }
  }


