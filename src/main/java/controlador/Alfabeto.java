package controlador;

/**
 *
 * @author usuario
 */
public enum Alfabeto {

    GUION_BAJO('_'),
    SUMA('+'),
    RESTA('-'),
    DIVISION('/'),
    MODULO('%'),
    MULTIPLICACION('*'),
    IGUAL('='),
    MAYOR_QUE('>'),
    MENOR_QUE('<'),
    ADMIRACION('!'),
    PARENTESIS_A('('),
    PARENTESIS_C(')'),
    LLAVE_A('{'),
    LLAVE_C('}'),
    CORCHETE_A('['),
    CORCHETE_C(']'),
    COMA(','),
    PUNTO_COMA(';'),
    DOS_PUNTOS(':'),
    PUNTO('.'),
    ESPACIO(' '),
    TABULACION('\t'),
    SALTO_LINEA('\n');

    private final char simbolo;

    private Alfabeto(char simbolo) {
	this.simbolo = simbolo;
    }

    public char getSimbolo() {
	return simbolo;
    }
}
