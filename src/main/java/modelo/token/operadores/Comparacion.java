package modelo.token.operadores;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Comparacion {
    IGUAL('='),
    MAYOR_QUE('>'),
    MENOR_QUE('<'),
    ADMIRACION('!');

    private final char simbolo;
    private final Color color;

    private Comparacion(char simbolo) {
	this.simbolo = simbolo;
	this.color = Color.CYAN;
    }

    public char getSimbolo() {
	return simbolo;
    }

}
