package modelo.token.operadores;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Logico {
    Y("and"),
    O("or"),
    NEGACION("not");

    private final String simbolo;
    private final Color color;

    private Logico(String simbolo) {
	this.simbolo = simbolo;
	this.color = Color.CYAN;
    }
}
