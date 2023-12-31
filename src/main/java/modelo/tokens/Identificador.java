package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum Identificador implements Tkn {
    IDENTIFICADOR("([\\w]|_)+(\\w|\\d)*", Color.BLACK);

    private final String patron;
    private final Color color;

    private Identificador(String patron, Color color) {
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
