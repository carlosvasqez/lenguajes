package modelo;

/**
 *
 * @author usuario
 */
public enum AlfabetoChar {

    GUION_BAJO('_'),
    //
    CRUZ('+'),
    GUION('-'),
    ASTERISCO('*'),
    DIAGONAL('/'),
    PORCENTAJE('%'),
    MAYOR_QUE('>'),
    MENOR_QUE('<'),
    IGUAL('='),
    EXCLAMACION('!'),
    //
    PARENTESIS_A('('),
    PARENTESIS_C(')'),
    LLAVE_A('{'),
    LLAVE_C('}'),
    CORCHETE_A('['),
    CORCHETE_C(']'),
    //
    COMA(','),
    PUNTO('.'),
    PUNTO_COMA(';'),
    DOS_PUNTOS(':'),
    //
    ESPACIO(' '),
    TABULACION('\t'),
    SALTO_LINEA('\n'),
    //
    COMILLA('\''),
    COMILLA_DOBLE('\"'),
    NUMERAL('#');

    private final char simbolo;

    private AlfabetoChar(char simbolo) {
	this.simbolo = simbolo;
    }

    public char getSimbolo() {
	return simbolo;
    }

    public String getSimboloString() {
	return String.valueOf(simbolo);
    }
}
