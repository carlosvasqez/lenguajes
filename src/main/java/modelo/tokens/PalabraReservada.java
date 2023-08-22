package modelo.tokens;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum PalabraReservada implements Tkn {
    //AND("AND PATRON", new Color(128, 0, 128)),
    AS("as", new Color(128, 0, 128)),
    ASSET("asset", new Color(128, 0, 128)),
    BREAK("break", new Color(128, 0, 128)),
    CLASS("class", new Color(128, 0, 128)),
    CONTINUE("continue", new Color(128, 0, 128)),
    DEF("def", new Color(128, 0, 128)),
    DEL("del", new Color(128, 0, 128)),
    ELIF("elif", new Color(128, 0, 128)),
    ELSE("else", new Color(128, 0, 128)),
    EXCEPT("except", new Color(128, 0, 128)),
    //FALSE("AND PATRON", new Color(128, 0, 128)),
    FINALLY("finally", new Color(128, 0, 128)),
    FOR("for", new Color(128, 0, 128)),
    FROM("from", new Color(128, 0, 128)),
    GLOBAL("global", new Color(128, 0, 128)),
    IF("if", new Color(128, 0, 128)),
    IMPORT("import", new Color(128, 0, 128)),
    IN("in", new Color(128, 0, 128)),
    IS("is", new Color(128, 0, 128)),
    LAMBDA("lambda", new Color(128, 0, 128)),
    NONE("none", new Color(128, 0, 128)),
    NONLOCAL("nonlocal", new Color(128, 0, 128)),
    //NOT("AND PATRON", new Color(128, 0, 128)),
    //OR("AND PATRON", new Color(128, 0, 128)),
    PASS("pass", new Color(128, 0, 128)),
    RAISE("raise", new Color(128, 0, 128)),
    RETURN("return", new Color(128, 0, 128)),
    //TRUE("AND PATRON", new Color(128, 0, 128)),
    TRY("try", new Color(128, 0, 128)),
    WHILE("while", new Color(128, 0, 128)),
    WITH("with", new Color(128, 0, 128)),
    YIELD("yield", new Color(128, 0, 128));

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

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [ " + super.toString() + " ]";
    }
}
