package modelo;

import modelo.tokens.Tkn;

/**
 *
 * @author usuario
 */
public class TokenError extends Token {

    private String mensaje;

    public TokenError(Tkn tokenEnum, String lexena, int fila, int columna) {
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
