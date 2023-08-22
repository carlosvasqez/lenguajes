package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum PalabraReservada implements Tkn {
    //AND("AND PATRON", new Color(128, 0, 128)),
    AS(" ", new Color(128, 0, 128)),
    ASSET(" ", new Color(128, 0, 128)),
    BREAK(" ", new Color(128, 0, 128)),
    CLASS(" ", new Color(128, 0, 128)),
    CONTINUE(" ", new Color(128, 0, 128)),
    DEF(" ", new Color(128, 0, 128)),
    DEL(" ", new Color(128, 0, 128)),
    ELIF(" ", new Color(128, 0, 128)),
    ELSE(" ", new Color(128, 0, 128)),
    EXCEPT(" ", new Color(128, 0, 128)),
    //FALSE("AND PATRON", new Color(128, 0, 128)),
    FINALLY(" ", new Color(128, 0, 128)),
    FOR(" ", new Color(128, 0, 128)),
    FROM(" ", new Color(128, 0, 128)),
    GLOBAL(" ", new Color(128, 0, 128)),
    IF(" ", new Color(128, 0, 128)),
    IMPORT(" ", new Color(128, 0, 128)),
    IN(" ", new Color(128, 0, 128)),
    IS(" ", new Color(128, 0, 128)),
    LAMBDA(" ", new Color(128, 0, 128)),
    NONE(" ", new Color(128, 0, 128)),
    NONLOCAL(" ", new Color(128, 0, 128)),
    //NOT("AND PATRON", new Color(128, 0, 128)),
    //OR("AND PATRON", new Color(128, 0, 128)),
    PASS(" ", new Color(128, 0, 128)),
    RAISE(" ", new Color(128, 0, 128)),
    RETURN(" ", new Color(128, 0, 128)),
    //TRUE("AND PATRON", new Color(128, 0, 128)),
    TRY(" ", new Color(128, 0, 128)),
    WHILE(" ", new Color(128, 0, 128)),
    WITH(" ", new Color(128, 0, 128)),
    YIELD(" ", new Color(128, 0, 128));
    
    private final String patron;
    private final Color color;

    private PalabraReservada(String patron, Color color) {
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
