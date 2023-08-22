package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Comparacion implements Tkn {
    DOBLE_IGUAL("\\==", new Color(135, 206, 235)),
    DIFERENTE("\\!= ", new Color(135, 206, 235)),
    MAYOR_QUE("\\>", new Color(135, 206, 235)),
    MENOR_QUE("\\<", new Color(135, 206, 235)),
    MAYOR_IGUAL_QUE("\\>=", new Color(135, 206, 235)),
    MENOR_IGUAL_QUE("\\<=", new Color(135, 206, 235)),;

    private final String patron;
    private final Color color;

    private Comparacion(String patron, Color color) {
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
