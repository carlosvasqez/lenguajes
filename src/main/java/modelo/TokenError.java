package modelo;

import modelo.tokens.Tkn;

/**
 *
 * @author usuario
 */
public class TokenError extends Token {

    private final String mensaje;

    public TokenError(Tkn tokenEnum, String lexena, int fila, int columna, String mensaje) {
        super(tokenEnum, lexena, fila, columna);
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "ERROR {" + "mensaje=" + mensaje + " , error= " + super.getLexena() + ", fila=" + fila + ", columna=" + columna + '}';
    }

}
