package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Constante implements Tkn {
    ENTERO("-?\\d+", Color.ORANGE),
    DECIMAL("-?\\d+(\\.\\d+)?", Color.ORANGE),
    CADENA("['\"]([^'\"]*)['\"]", Color.ORANGE),
    TRUE("True", Color.ORANGE),
    FALSE("False", Color.ORANGE);

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
    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [ " + super.toString() + " ]";
    }
}
