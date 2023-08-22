package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Aritmetico implements Tkn {
    SUMA("\\+", new Color(135, 206, 235)),
    RESTA("-", new Color(135, 206, 235)),
    EXPONENTE("\\*{1,2}", new Color(135, 206, 235)),
    DIVISON("\\/{1,2}", new Color(135, 206, 235)),
    MODULO("%", new Color(135, 206, 235)),
    MULTIPLICACION("\\*", new Color(135, 206, 235));

    private final String patron;
    private final Color color;

    private Aritmetico(String patron, Color color) {
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

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [ " + super.toString() + " ]";
    }

}
