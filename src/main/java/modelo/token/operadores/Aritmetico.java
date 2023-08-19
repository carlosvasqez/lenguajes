package modelo.token.operadores;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Aritmetico {
    SUMA('+'),
    RESTA('-'),
    DIVISION('/'),
    MODULO('%'),
    MULTIPLICACION('*');

    private final char simbolo;
    private final Color color;

    private Aritmetico(char simbolo) {
	this.simbolo = simbolo;
	this.color = Color.CYAN;
    }

    public char getSimbolo() {
	return simbolo;
    }

}
