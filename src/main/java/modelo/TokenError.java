package modelo;

/**
 *
 * @author usuario
 */
public class TokenError extends Token {

    private String mensaje;

    public TokenError(TokenEnum tokenEnum, String lexena, int fila, int columna) {
	super(tokenEnum, lexena, fila, columna);
    }

    public void setMensaje(String mensaje) {
	this.mensaje = mensaje;
    }

    @Override
    public String toString() {
	return "ERROR {" + "mensaje=" + mensaje + ", fila=" + fila + ", columna=" + columna + '}';
    }

}
