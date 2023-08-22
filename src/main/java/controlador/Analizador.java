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
import modelo.TokenEnum;
import modelo.TokenError;

/**
 *
 * @author usuario
 */
public class Analizador {

    private List<Token> listaTokens;

    private final Map<String, TokenEnum> diccionario;
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

	diccionario.put("+", TokenEnum.ARITMETICO);
	diccionario.put("-", TokenEnum.ARITMETICO);
	diccionario.put("*", TokenEnum.ARITMETICO);
	diccionario.put("%", TokenEnum.ARITMETICO);
	diccionario.put("**", TokenEnum.ARITMETICO);
	diccionario.put("/", TokenEnum.ARITMETICO);
	diccionario.put("//", TokenEnum.ARITMETICO);

	diccionario.put(">", TokenEnum.COMPARACION);
	diccionario.put("<", TokenEnum.COMPARACION);
	diccionario.put("==", TokenEnum.COMPARACION);
	diccionario.put("!=", TokenEnum.COMPARACION);
	diccionario.put(">=", TokenEnum.COMPARACION);
	diccionario.put("<=", TokenEnum.COMPARACION);

	diccionario.put("and", TokenEnum.LOGICO);
	diccionario.put("or", TokenEnum.LOGICO);
	diccionario.put("not", TokenEnum.LOGICO);

	diccionario.put("=", TokenEnum.ASIGNACION);
	diccionario.put("+=", TokenEnum.ASIGNACION);
	diccionario.put("-=", TokenEnum.ASIGNACION);
	diccionario.put("*=", TokenEnum.ASIGNACION);
	diccionario.put("%=", TokenEnum.ASIGNACION);
	diccionario.put("**=", TokenEnum.ASIGNACION);
	diccionario.put("/=", TokenEnum.ASIGNACION);
	diccionario.put("//=", TokenEnum.ASIGNACION);

	//diccionario.put("and", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("as", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("assert", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("break", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("class", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("continue", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("def", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("del", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("elif", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("else", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("except", TokenEnum.PALABRA_RESERVADA);
	//diccionario.put("False", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("finally", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("for", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("from", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("global", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("if", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("import", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("in", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("is", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("lambda", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("None", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("nonlocal", TokenEnum.PALABRA_RESERVADA);
	//diccionario.put("not", TokenEnum.PALABRA_RESERVADA);
	//diccionario.put("or", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("pass", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("raise", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("return", TokenEnum.PALABRA_RESERVADA);
	//diccionario.put("True", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("try", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("while", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("with", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("yield", TokenEnum.PALABRA_RESERVADA);

	diccionario.put("True", TokenEnum.CONSTANTE);
	diccionario.put("False", TokenEnum.CONSTANTE);

	//diccionario.put("#", TokenEnum.COMENTARIO);
	diccionario.put("(", TokenEnum.OTRO);
	diccionario.put(")", TokenEnum.OTRO);
	diccionario.put("{", TokenEnum.OTRO);
	diccionario.put("}", TokenEnum.OTRO);
	diccionario.put("[", TokenEnum.OTRO);
	diccionario.put("]", TokenEnum.OTRO);
	diccionario.put(",", TokenEnum.OTRO);
	diccionario.put(";", TokenEnum.OTRO);
	diccionario.put(":", TokenEnum.OTRO);

	diccionario.put(" ", TokenEnum.ESPECIAL);
	diccionario.put("\n", TokenEnum.ESPECIAL);
	diccionario.put("\t", TokenEnum.ESPECIAL);

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
	Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna - str.length());
	listaTokens.add(token);
    }

    private void crearTokenV(int fila, int columna) {
	if (esEntero()) {
	    Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esDecimal()) {
	    Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esCadena()) {
	    Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esComentario()) {
	    Token token = new Token(TokenEnum.COMENTARIO, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else if (esIdentificador()) {
	    Token token = new Token(TokenEnum.IDENTIFICADOR, str, fila, columna - str.length());
	    listaTokens.add(token);
	} else {
	    generarError("no es entero, ni decimal, ni cadena, ni comentario, ni identificador", fila, columna);
	}

    }

    private void generarError(String mensaje, int fila, int columna) {
	Token tokenError = new TokenError(TokenEnum.ERROR, str, fila, columna - str.length());
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
		|| caracter == AlfabetoChar.DOBLE_COMILLA.getSimbolo();
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
	return Character.isAlphabetic(str.charAt(0)) || str.charAt(0) == '_';
    }
}
