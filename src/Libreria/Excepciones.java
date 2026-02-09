package Libreria;

public class Excepciones {
  Exception UsuarioInvalidoException = new Exception("El usuario introducido no es correcto");
  Exception UsuarioSancionadoException = new Exception("El usuario ha sido sancionado");
  Exception PrestamoInvalidoException = new Exception("El prestamo es invalido");
  Exception LibroNoDisponibleException = new Exception("El libro no se encuentra dispobible");
}
