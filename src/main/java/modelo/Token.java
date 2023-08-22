package modelo;

import modelo.tokens.Tkn;

/**
 *
 * @author usuario
 */
public class Token {

    private final Tkn tokenEnum;
    private final String lexena;
    protected final int fila;
    protected final int columna;
    private final String patron="xd";

    public Token(Tkn tokenEnum, String lexena, int fila, int columna) {
	this.tokenEnum = tokenEnum;
	this.lexena = lexena;
	this.fila = fila;
	this.columna = columna;
    }

    public Tkn getTokenEnum() {
	return tokenEnum;
    }

    public String getLexena() {
	return lexena;
    }

    public int getFila() {
	return fila;
    }

    public int getColumna() {
	return columna;
    }

    public String getPatron() {
	return patron;
    }

    @Override
    public String toString() {
	if (" ".equalsIgnoreCase(lexena)) {
	    return "ESPACIO {" + " fila= " + fila + " , columna= " + columna + '}';
	} else if ("\t".equalsIgnoreCase(lexena)) {
	    return "TABULACION {" + " fila= " + fila + " , columna= " + columna + '}';
	} else if ("\n".equalsIgnoreCase(lexena)) {
	    return "SALTO_LINEA {" + " fila= " + fila + " , columna= " + columna + '}';
	} else {
	    return "TOKEN {" + " tipo= " + tokenEnum + " , lexema= " + lexena + " , fila= " + fila + " , columna= " + columna + '}';
	}
    }

}
