package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Errorr implements Tkn {
    ERROR(" ", Color.RED);

    private final String patron;
    private final Color color;

    private Errorr(String patron, Color color) {
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
