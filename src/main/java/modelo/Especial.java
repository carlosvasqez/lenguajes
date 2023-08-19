package modelo;

/**
 *
 * @author usuario
 */
public enum Especial {
    ESPACIO(' '),
    TABULACION('\t'),
    SALTO_LINEA('\n');

    private final char simbolo;

    private Especial(char simbolo) {
	this.simbolo = simbolo;
    }

    public char getSimbolo() {
	return simbolo;
    }

}
