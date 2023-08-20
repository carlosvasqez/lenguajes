package modelo;

/**
 *
 * @author usuario
 */
public class Token {

    private final TokenEnum tokenEnum;
    private final String lexena;
    private final int fila;
    private final int columna;

    public Token(TokenEnum tokenEnum, String lexena, int fila, int columna) {
	this.tokenEnum = tokenEnum;
	this.lexena = lexena;
	this.fila = fila;
	this.columna = columna;
    }

    @Override
    public String toString() {
	return "Token{" + "token=" + tokenEnum + ", lexena=" + lexena + ", fila=" + fila + ", columna=" + columna + '}';
    }

}
