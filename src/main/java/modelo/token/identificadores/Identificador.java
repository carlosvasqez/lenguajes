package modelo.token.identificadores;

/**
 *
 * @author usuario
 */
public enum Identificador {
    GUION_BAJO('_');

    private final char simbolo;

    private Identificador(char simbolo) {
	this.simbolo = simbolo;
    }

    public char getSimbolo() {
	return simbolo;
    }

}
