package controlador;

import modelo.AlfabetoChar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import modelo.Token;
import modelo.TokenError;
import modelo.tokens.Aritmetico;
import modelo.tokens.Asignacion;
import modelo.tokens.Comentario;
import modelo.tokens.Comparacion;
import modelo.tokens.Constante;
import modelo.tokens.Errorr;
import modelo.tokens.Especial;
import modelo.tokens.Identificador;
import modelo.tokens.Logico;
import modelo.tokens.Otros;
import modelo.tokens.PalabraReservada;
import modelo.tokens.Tkn;

/**
 *
 * @author usuario
 */
public class Analizador {

    private List<Token> listaTokens;

    private final Map<String, Tkn> diccionario;
    private final JTextPane textPaneEditor;
    private final JTextPane textPaneOutput;
    private final GeneradorReporte generadorReporte;
    private final Limpiador limpiador;
    private String str = "";

    public Analizador(JTextPane textPaneEditor, JTextPane textPaneOutput, GeneradorReporte generadorReporte, Limpiador limpiador) {

	this.listaTokens = new ArrayList<>();

	this.textPaneEditor = textPaneEditor;
	this.textPaneOutput = textPaneOutput;
	this.generadorReporte = generadorReporte;
	this.limpiador = limpiador;
	this.diccionario = new HashMap<>();

	diccionario.put("+", Aritmetico.SUMA);
	diccionario.put("-", Aritmetico.RESTA);
	diccionario.put("**", Aritmetico.EXPONENTE);
	diccionario.put("/", Aritmetico.DIVISON);
	diccionario.put("//", Aritmetico.DIVISON);
	diccionario.put("%", Aritmetico.MODULO);
	diccionario.put("*", Aritmetico.MULTIPLICACION);

	diccionario.put("==", Comparacion.DOBLE_IGUAL);
	diccionario.put("!=", Comparacion.DIFERENTE);
	diccionario.put(">", Comparacion.MAYOR_QUE);
	diccionario.put("<", Comparacion.MENOR_QUE);
	diccionario.put(">=", Comparacion.MAYOR_IGUAL_QUE);
	diccionario.put("<=", Comparacion.MENOR_IGUAL_QUE);

	diccionario.put("and", Logico.Y);
	diccionario.put("or", Logico.O);
	diccionario.put("not", Logico.NEGACION);

	diccionario.put("=", Asignacion.IGUAL);
	diccionario.put("+=", Asignacion.MAS_IGUAL);
	diccionario.put("-=", Asignacion.MENOS_IGUAL);
	diccionario.put("*=", Asignacion.POR_IGUAL);
	diccionario.put("%=", Asignacion.MODULO_IGUAL);
	diccionario.put("**=", Asignacion.POTENCIA_IGUAL);
	diccionario.put("/=", Asignacion.DIVISION_IGUAL);
	diccionario.put("//=", Asignacion.DOBLE_DIVISION_IGUAL);

	//diccionario.put("and", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("as", PalabraReservada.AS);
	diccionario.put("assert", PalabraReservada.ASSET);
	diccionario.put("break", PalabraReservada.BREAK);
	diccionario.put("class", PalabraReservada.CLASS);
	diccionario.put("continue", PalabraReservada.CONTINUE);
	diccionario.put("def", PalabraReservada.DEF);
	diccionario.put("del", PalabraReservada.DEL);
	diccionario.put("elif", PalabraReservada.ELIF);
	diccionario.put("else", PalabraReservada.ELSE);
	diccionario.put("except", PalabraReservada.EXCEPT);
	//diccionario.put("False", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("finally", PalabraReservada.FINALLY);
	diccionario.put("for", PalabraReservada.FOR);
	diccionario.put("from", PalabraReservada.FROM);
	diccionario.put("global", PalabraReservada.GLOBAL);
	diccionario.put("if", PalabraReservada.IF);
	diccionario.put("import", PalabraReservada.IMPORT);
	diccionario.put("in", PalabraReservada.IN);
	diccionario.put("is", PalabraReservada.IS);
	diccionario.put("lambda", PalabraReservada.LAMBDA);
	diccionario.put("None", PalabraReservada.NONE);
	diccionario.put("nonlocal", PalabraReservada.NONLOCAL);
	//diccionario.put("not", TokenEnum.PALABRA_RESERVADA);
	//diccionario.put("or", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("pass", PalabraReservada.PASS);
	diccionario.put("raise", PalabraReservada.RAISE);
	diccionario.put("return", PalabraReservada.RETURN);
	//diccionario.put("True", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("try", PalabraReservada.TRY);
	diccionario.put("while", PalabraReservada.WHILE);
	diccionario.put("with", PalabraReservada.WITH);
	diccionario.put("yield", PalabraReservada.YIELD);

	diccionario.put("True", Constante.TRUE);
	diccionario.put("False", Constante.FALSE);

	diccionario.put("#", Comentario.COMENTARIO);

	
	//diccionario.put(".", Otros.PUNTO);
	diccionario.put("(", Otros.PARENTESIS_A);
	diccionario.put(")", Otros.PARENTESIS_C);
	diccionario.put("{", Otros.LLAVE_A);
	diccionario.put("}", Otros.LLAVE_C);
	diccionario.put("[", Otros.CORCHETES_A);
	diccionario.put("]", Otros.CORCHETES_C);
	diccionario.put(",", Otros.COMA);
	diccionario.put(";", Otros.PUNTO_Y_COMA);
	diccionario.put(":", Otros.DOS_PUNTOS);

	diccionario.put(" ", Especial.ESPACIO);
	diccionario.put("\n", Especial.SALTO_LINEA);
	diccionario.put("\t", Especial.TABULACION);



    }

    public void realizar() {
	//reiniciar los tokens porque al ser el mismo objeto analizador para
	//todo el programa entonces los tokens solo se acumulan aunque se limpie la pantalla
	listaTokens = new ArrayList<>();
	generarAnalisis();
	//antes de colorear se debe limpiar ya que al colorear se escribe basado en los tokens
	limpiador.limpiarTodo();
	//antes de colorear se borra el ultimo token que es el del bug \n antes de mostrar el output
//	int ultimoIndice = listaTokens.size() - 1;
//	listaTokens.remove(ultimoIndice);
	mostrarColoreado();

	mostrarOutput();
	generadorReporte.generarReporte();
    }

    public List<Token> getListaTokens() {
	return listaTokens;
    }

    private void generarAnalisis() {
	StyledDocument doc = textPaneEditor.getStyledDocument();
	Element element = doc.getDefaultRootElement();

	for (int i = 0; i < element.getElementCount(); i++) {
	    Element linea = element.getElement(i);
	    int inicioLinea = linea.getStartOffset();
	    int finalLinea = linea.getEndOffset();
	    try {
		String contenido = doc.getText(inicioLinea, finalLinea - inicioLinea);
		buscarTokensEn(i + 1, contenido);
	    } catch (BadLocationException ex) {
		Logger.getLogger(Analizador.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    private void mostrarOutput() {
	int contadorLogs = 0;
	Escritor escritor = new Escritor(textPaneOutput);
	for (Token listaToken : listaTokens) {
	    if (!listaToken.getLexena().isBlank()) {
		contadorLogs++;
		escritor.escribirLinea(contadorLogs + " . " + listaToken.toString());
	    }
	}
    }

    private void mostrarColoreado() {
	Escritor escritor = new Escritor(textPaneEditor);
	escritor.colorear(listaTokens);
    }

    private void buscarTokensEn(int fila, String contenido) {
	str = "";
	int columna = 1;
	char[] caracteres = contenido.toCharArray();
	for (char caracter : caracteres) {
	    if (esEspacio(caracter)) {
		validarStr(caracter, fila, columna);
		columna++;
		//str = "";
	    } else if (esTabulacion(caracter)) {
		validarStr(caracter, fila, columna);
		columna++;
		str = "";
	    } else if (esSaltoDeLinea(caracter)) {
		validarStr(caracter, fila, columna);
		str = "";
	    } else if (esSimboloCadena(caracter)) {/////////////////////////////////////////////////////
		if (estaComenzando()) {
		    str += caracter;
		    columna++;
		} else {
		    //cerrar cadena y verificar que inicie con el mismo simbolo
		    str += caracter;
		    if (esCadenaValida(caracter)) {
			//se compara el caracter de cierre con el de inicio
			columna++;
			crearTokenCadena(fila, columna);
			str = "";
		    } else {
			//signo de inicio diferente al de cierre
			generarError("string con diferentes agrupadores", fila, columna);
			columna++;
		    }
		    str = "";
		}
	    } else if (esSimboloNumeral(caracter)) {//////////////////////////////////////////////////////////////
		str += caracter;
		columna++;
	    } else if (Character.isAlphabetic(caracter)) {
		str += caracter;
		//aqui modificar
		columna++;
	    } else if (Character.isDigit(caracter)) {
		str += caracter;
		columna++;
	    } else {
		//si no es numero ni letra, ni cadena, ni espacio, ni tab ni salto
		str += caracter;
		columna++;
	    }
	}
    }

    private boolean estaComenzando() {
	return str.isEmpty();
    }

    private boolean esCadenaValida(char caracter) {
	return caracter == str.charAt(0);
    }

    //validar solo se aplica en los 3 especiales
    private void validarStr(char caracter, int fila, int columna) {
	if (!str.isBlank()) {
	    if (str.charAt(0) == '\"' || str.charAt(0) == '\'') {
		if (caracter == AlfabetoChar.ESPACIO.getSimbolo()) {
		    str += caracter;
		} else if (caracter == AlfabetoChar.TABULACION.getSimbolo()) {
		    str += caracter;
		} else if (caracter == AlfabetoChar.SALTO_LINEA.getSimbolo()) {
		    //verificar si se ha cerrado la cadena cuando hay salto de linea,sino generar un error
		    //("cadena no cerrada", fila, columna);
		    crearTokenV(fila, columna);
		}
	    } else if (str.charAt(0) == '#') {
		if (caracter == AlfabetoChar.ESPACIO.getSimbolo()) {
		    str += caracter;
		} else if (caracter == AlfabetoChar.TABULACION.getSimbolo()) {
		    str += caracter;
		} else if (caracter == AlfabetoChar.SALTO_LINEA.getSimbolo()) {
		    crearTokenV(fila, columna);//crear token comentario
		    crearTokenSalto(fila, columna);//crear seguidamente el token salto de linea
		}
	    } else {
		if (diccionario.containsKey(str)) {///////////////////////////////////////
		    //operadores y palabras reservadas.(hashmap)
		    crearToken(fila, columna);
		} else {
		    //identificadores, numero.(metodos), comentarios
		    crearTokenV(fila, columna);
		}
		if (caracter == AlfabetoChar.ESPACIO.getSimbolo()) {
		    crearTokenEspacio(fila, columna);
		    str = "";
		} else if (caracter == AlfabetoChar.TABULACION.getSimbolo()) {
		    crearTokenTab(fila, columna);
		} else if (caracter == AlfabetoChar.SALTO_LINEA.getSimbolo()) {
		    crearTokenSalto(fila, columna);
		}
	    }
	} else {//se ejecuta cuando se ha encontrado una cadena anteriormente y ahora hay un especial
	    if (caracter == AlfabetoChar.ESPACIO.getSimbolo()) {
		crearTokenEspacio(fila, columna);
	    } else if (caracter == AlfabetoChar.TABULACION.getSimbolo()) {
		crearTokenTab(fila, columna);
	    } else if (caracter == AlfabetoChar.SALTO_LINEA.getSimbolo()) {
		crearTokenSalto(fila, columna);
	    }
	    str = "";
	}

    }

    private void crearToken(int fila, int columna) {
	Token token = new Token(diccionario.get(str), str, fila, columna - str.length());
	listaTokens.add(token);
    }

    private void crearTokenEspacio(int fila, int columna) {
	Token token = new Token(diccionario.get(" "), " ", fila, columna);
	listaTokens.add(token);
    }

    private void crearTokenTab(int fila, int columna) {
	Token token = new Token(diccionario.get("\t"), "\t", fila, columna - String.valueOf("\t").length());
	listaTokens.add(token);
    }

    private void crearTokenSalto(int fila, int columna) {
	Token token = new Token(diccionario.get("\n"), "\n", fila, columna);
	listaTokens.add(token);
    }

    private void crearTokenCadena(int fila, int columna) {
	Token token = new Token(Constante.CADENA, str, fila, columna - str.length());
	listaTokens.add(token);
    }

    private void crearTokenV(int fila, int columna) {
	if (esEntero()) {
	    Token token = new Token(Constante.ENTERO, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esDecimal()) {
	    Token token = new Token(Constante.DECIMAL, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esCadena()) {
	    Token token = new Token(Constante.CADENA, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esComentario()) {
	    Token token = new Token(Comentario.COMENTARIO, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esIdentificador()) {
	    Token token = new Token(Identificador.IDENTIFICADOR, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else {
	    generarError("no es entero, ni decimal, ni cadena, ni comentario, ni identificador", fila, columna);
	}

    }

    private void generarError(String mensaje, int fila, int columna) {
	Token tokenError = new TokenError(Errorr.ERROR, str, fila, columna - str.length());
	listaTokens.add(tokenError);
	((TokenError) tokenError).setMensaje(mensaje);
    }

    private boolean esEspacio(char caracter) {
	return caracter == AlfabetoChar.ESPACIO.getSimbolo();
    }

    private boolean esSaltoDeLinea(char caracter) {
	return caracter == AlfabetoChar.SALTO_LINEA.getSimbolo();
    }

    private boolean esTabulacion(char caracter) {
	return caracter == AlfabetoChar.TABULACION.getSimbolo();
    }

    private boolean esSimboloCadena(char caracter) {
	return caracter == AlfabetoChar.COMILLA.getSimbolo()
		|| caracter == AlfabetoChar.COMILLA_DOBLE.getSimbolo();
    }

    private boolean esSimboloNumeral(char caracter) {
	return caracter == AlfabetoChar.NUMERAL.getSimbolo();
    }

    private boolean esEntero() {
	try {
	    Integer.valueOf(str);
	    if (str.startsWith("+")) {
		return false;
	    }
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    private boolean esDecimal() {
	try {
	    Double.valueOf(str);
	    if (str.startsWith("+")
		    || str.startsWith("-.")
		    || str.startsWith(".")
		    || Character.isAlphabetic(str.charAt(str.length() - 1))) {
		return false;
	    }
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    private boolean esCadena() {
	return (str.charAt(0) == '\'' && str.charAt(str.length() - 1) == '\'')
		|| (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"');
    }

    private boolean esComentario() {
	return str.charAt(0) == '#';
    }

    private boolean esIdentificador() {
	return cumpleComIdentificador();
    }

    private boolean cumpleComIdentificador() {
	// Verificar si la cadena tiene al menos una letra o guión bajo
	boolean contieneLetraOGuionBajo = false;

	for (int i = 0; i < str.length(); i++) {
	    char c = str.charAt(i);
	    if (Character.isLetter(c) || c == '_') {
		contieneLetraOGuionBajo = true;
		break;
	    }
	}

	// Verificar si no hay números al inicio
	boolean noNumerosAlInicio = true;
	for (int i = 0; i < str.length(); i++) {
	    char c = str.charAt(i);
	    if (Character.isDigit(c)) {
		noNumerosAlInicio = false;
		break;
	    } else if (!Character.isWhitespace(c)) {
		break;
	    }
	}

	// Verificar si no contiene caracteres especiales
	boolean noContieneCaracteresEspeciales = true;
	for (int i = 0; i < str.length(); i++) {
	    char c = str.charAt(i);
	    if (!(Character.isLetterOrDigit(c) || c == '_')) {
		noContieneCaracteresEspeciales = false;
		break;
	    }
	}

	// Verificar si la cadena no consiste solo en un guión bajo
	boolean noEsSoloGuionBajo = !str.equals("_");

	// Retornar true si cumple todas las condiciones
	return contieneLetraOGuionBajo
		&& noNumerosAlInicio
		&& noContieneCaracteresEspeciales
		&& noEsSoloGuionBajo;
    }
}
