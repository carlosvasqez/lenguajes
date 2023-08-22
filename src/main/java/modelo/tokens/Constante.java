package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Constante implements Tkn {
    ENTERO(" ", Color.ORANGE),
    DECIMAL(" ", Color.ORANGE),
    CADENA(" ", Color.ORANGE),
    TRUE(" ", Color.ORANGE),
    FALSE(" ", Color.ORANGE);

    private final String patron;
    private final Color color;

    private Constante(String patron, Color color) {
	this.patron = patron;
	this.color = color;
    }

    @Override
    public Color getColor() {
	return color;
    }

    @Override
    public String getPatron() {
	return patron;
    }

}
