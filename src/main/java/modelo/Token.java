package modelo;

/**
 *
 * @author usuario
 */
public class Token {

    private TokenEnum tokenEnum;
    private String lexena;
    private int fila;
    private int columna;

    public Token(TokenEnum tokenEnum, String lexena, int fila, int columna) {
	this.tokenEnum = tokenEnum;
	this.lexena = lexena;
	this.fila = fila;
	this.columna = columna;

    }

    @Override
    public String toString() {
	return "lexena=" + lexena + ", fila=" + fila + ", columna=" + columna + '}';
    }

}
