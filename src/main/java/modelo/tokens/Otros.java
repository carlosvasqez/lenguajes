package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Otros implements Tkn {
    PUNTO("\\.", new Color(0, 128, 0)),
    PARENTESIS_A("\\(", new Color(0, 128, 0)),
    PARENTESIS_C("\\)", new Color(0, 128, 0)),
    LLAVE_A("\\{", new Color(0, 128, 0)),
    LLAVE_C("\\}", new Color(0, 128, 0)),
    CORCHETES_A("\\[", new Color(0, 128, 0)),
    CORCHETES_C("\\]", new Color(0, 128, 0)),
    COMA(",", new Color(0, 128, 0)),
    PUNTO_Y_COMA(";", new Color(0, 128, 0)),
    DOS_PUNTOS(":", new Color(0, 128, 0));

    private final String patron;
    private final Color color;

    private Otros(String patron, Color color) {
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
