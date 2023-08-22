package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Comentario implements Tkn {
    COMENTARIO("#(.*)", Color.GRAY);

    private final String patron;
    private final Color color;

    private Comentario(String patron, Color color) {
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
