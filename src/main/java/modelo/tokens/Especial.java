package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Especial implements Tkn {
    ESPACIO(" ", Color.PINK),
    TABULACION("\\t", Color.PINK),
    SALTO_LINEA("\\n", Color.PINK);

    private final String patron;
    private final Color color;

    private Especial(String patron, Color color) {
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
