package modelo;

/**
 *
 * @author usuario
 */
public enum AlfabetoChar {

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
    SALTO_LINEA('\n'),
    COMILLA('\''),
    DOBLE_COMILLA('\"'),
    NUMERAL('#');

    private final char simbolo;

    private AlfabetoChar(char simbolo) {
	this.simbolo = simbolo;
    }

    public char getSimbolo() {
	return simbolo;
    }
}
