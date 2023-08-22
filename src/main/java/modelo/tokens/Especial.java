package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Especial implements Tkn {
    ESPACIO(" ", Color.PINK),
    TABULACION(" ", Color.PINK),
    SALTO_LINEA(" ", Color.PINK);

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
}
