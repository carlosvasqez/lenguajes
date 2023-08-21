package modelo;

/**
 *
 * @author usuario
 */
public class Token {

    private final TokenEnum tokenEnum;
    private final String lexena;
    protected final int fila;
    protected final int columna;

    public Token(TokenEnum tokenEnum, String lexena, int fila, int columna) {
	this.tokenEnum = tokenEnum;
	this.lexena = lexena;
	this.fila = fila;
	this.columna = columna;
    }

    public TokenEnum getTokenEnum() {
	return tokenEnum;
    }

    public String getLexena() {
	return lexena;
    }

    @Override
    public String toString() {
	if (" ".equalsIgnoreCase(lexena)) {
	    System.out.println("ddddddddddddddd");
	    return "ESPACIO {" + " fila=" + fila + " , columna=" + columna + '}';
	} else if ("\t".equalsIgnoreCase(lexena)) {
	    return "TABULACION {" + " fila=" + fila + " , columna=" + columna + '}';
	} else if ("\n".equalsIgnoreCase(lexena)) {
	    return "SALTO_LINEA {" + " fila=" + fila + " , columna=" + columna + '}';
	} else {
	    return "TOKEN {" + " tipo=" + tokenEnum + " , lexema=" + lexena + " , fila=" + fila + " , columna=" + columna + '}';
	}
    }

}
