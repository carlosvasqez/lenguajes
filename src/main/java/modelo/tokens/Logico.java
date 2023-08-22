package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Logico implements Tkn {
    Y(" ", new Color(135, 206, 235)),
    O(" ", new Color(135, 206, 235)),
    NEGACION(" ", new Color(135, 206, 235));
    private final String patron;
    private final Color color;

    private Logico(String patron, Color color) {
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
