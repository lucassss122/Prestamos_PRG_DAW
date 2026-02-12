package Libreria;

import java.security.PublicKey;

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
}
