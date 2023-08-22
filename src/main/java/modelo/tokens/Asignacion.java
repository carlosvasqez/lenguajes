package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Asignacion implements Tkn {
    IGUAL(" ", new Color(135, 206, 235)),
    MAS_IGUAL(" ", new Color(135, 206, 235)),
    MENOS_IGUAL(" ", new Color(135, 206, 235)),
    POR_IGUAL(" ", new Color(135, 206, 235)),
    MODULO_IGUAL(" ", new Color(135, 206, 235)),
    POTENCIA_IGUAL(" ", new Color(135, 206, 235)),
    DIVISION_IGUAL(" ", new Color(135, 206, 235)),
    DOBLE_DIVISION_IGUAL(" ", new Color(135, 206, 235));

    private final String patron;
    private final Color color;

    private Asignacion(String patron, Color color) {
	this.patron = patron;
	this.color = color;
    }

    @Override
    public String getPatron() {
	return patron;
    }

    @Override
    public Color getColor() {
	return color;
    }

}
