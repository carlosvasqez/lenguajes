package modelo.token.otros;

/**
 *
 * @author usuario
 */
public enum Otro {
    PARENTESIS_A('('),
    PARENTESIS_C(')'),
    PUNTO('.');

    private final char simbolo;

    private Otro(char simbolo) {
	this.simbolo = simbolo;
    }

    public char getSimbolo() {
	return simbolo;
    }

}
