package controlador;

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

/**
 *
 * @author usuario
 */
public class Analizador {

    private Map<String, TokenEnum> diccionario;
    private List<Token> listaTokens;
    private JTextPane textPaneEditor;

    public Analizador(JTextPane textPaneEditor) {

	this.textPaneEditor = textPaneEditor;
	this.diccionario = new HashMap<>();
	this.listaTokens = new ArrayList<>();

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

	diccionario.put("#", TokenEnum.COMENTARIO);

	diccionario.put("(", TokenEnum.OTRO);
	diccionario.put(")", TokenEnum.OTRO);
	diccionario.put("{", TokenEnum.OTRO);
	diccionario.put("}", TokenEnum.OTRO);
	diccionario.put("[", TokenEnum.OTRO);
	diccionario.put("]", TokenEnum.OTRO);
	diccionario.put(",", TokenEnum.OTRO);
	diccionario.put(";", TokenEnum.OTRO);
	diccionario.put(":", TokenEnum.OTRO);

	diccionario.putIfAbsent(" ", TokenEnum.ESPECIAL);
	diccionario.putIfAbsent("\n", TokenEnum.ESPECIAL);
	diccionario.putIfAbsent("\t", TokenEnum.ESPECIAL);

    }

    public void analizar() {
	StyledDocument doc = textPaneEditor.getStyledDocument();
	Element element = doc.getDefaultRootElement();
	for (int i = 0; i < element.getElementCount(); i++) {
	    Element linea = element.getElement(i);
	    int lineaInicio = linea.getStartOffset();
	    int lineaFinal = linea.getEndOffset();
	    try {
		String contenido = doc.getText(lineaInicio, lineaFinal - lineaInicio);
		buscarTokensEn(i + 1, contenido);
	    } catch (BadLocationException ex) {
		Logger.getLogger(Analizador.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	mostrarEnPantalla();
    }

    private void mostrarEnPantalla() {
	textPaneEditor.setText("");
	for (Token listaToken : listaTokens) {
	    System.out.println(listaToken);
	}
    }

    private String str = "";

    private void buscarTokensEn(int fila, String contenido) {
	str = "";
	int columna = 1;
	char[] caracteres = contenido.toCharArray();
	for (char caracter : caracteres) {
	    //
	    //
	    if (esEspacio(caracter)) {
		str += caracter;
		validarStr(fila, columna);
		columna++;
	    } else if (esTabulacion(caracter)) {
		str += caracter;
		validarStr(fila, columna);
		columna++;
	    } else if (esSaltoDeLinea(caracter)) {
		str += caracter;
		validarStr(fila, columna);
		columna++;
	    } else if (esInicioCadena(caracter)) {
		str += caracter;
		columna++;
	    } else {
		str += caracter;
		columna++;
	    }
	}
    }

    private void validarStr(int fila, int columna) {
	if (!str.isBlank()) {
	    if (diccionario.containsKey(str)) {
		//operadores y palabras reservadas.(hashmap)
		crearTokenC(fila, columna);
	    } else {
		//identificadores, numero.(metodos)
		crearTokenV(fila, columna);
	    }
	} else if (str.isBlank()) {//tiene \t o \n o espacios
	    if (diccionario.containsKey(str)) {
		crearTokenEspecial(fila, columna);
	    }
	}
    }

    private boolean esInicioCadena(char caracter) {
	return caracter == Alfabeto.COMILLA.getSimbolo()
		|| caracter == Alfabeto.DOBLE_COMILLA.getSimbolo();
    }

    private void crearTokenEspecial(int fila, int columna) {
	Token token = new Token(TokenEnum.ESPECIAL, str, fila, columna);
	listaTokens.add(token);
    }

    private void crearSaltoDeLinea() {

    }

    private void crearTokenC(int fila, int columna) {
	Token token = new Token(diccionario.get(str), str, fila, columna);
	listaTokens.add(token);
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
	    if (str.startsWith("+") || str.startsWith("-.") || str.startsWith(".")) {
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

    private void crearTokenV(int fila, int columna) {
	if (esEntero()) {
	    Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna);
	    listaTokens.add(token);
	} else if (esDecimal()) {
	    Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna);
	    listaTokens.add(token);
	} else if (esCadena()) {
	    Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna);
	    listaTokens.add(token);
	}
    }

    private void generarError(int fila, int columna) {

    }

    private boolean esEspacio(char caracter) {
	return caracter == Alfabeto.ESPACIO.getSimbolo();
    }

    private boolean esSaltoDeLinea(char caracter) {
	return caracter == Alfabeto.SALTO_LINEA.getSimbolo();
    }

    private boolean esTabulacion(char caracter) {
	return caracter == Alfabeto.TABULACION.getSimbolo();
    }

}