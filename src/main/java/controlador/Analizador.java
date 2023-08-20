package controlador;

import modelo.Alfabeto;
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

    private final Map<String, TokenEnum> diccionario;
    private final List<Token> listaTokens;
    private final JTextPane textPaneEditor;
    private final JTextPane textPaneOutput;
    private String str = "";

    public Analizador(JTextPane textPaneEditor, JTextPane textPaneOutput) {

	this.textPaneEditor = textPaneEditor;
	this.textPaneOutput = textPaneOutput;
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

	diccionario.put(" ", TokenEnum.ESPECIAL);
	diccionario.putIfAbsent("\n", TokenEnum.ESPECIAL);
	diccionario.put("\t", TokenEnum.ESPECIAL);

    }

    public void analizar() {
	Escritor escritor = new Escritor(textPaneOutput);
	escritor.limpiar();
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
	mostrarAnalisis();
    }

    private void mostrarAnalisis() {
	Escritor escritor = new Escritor(textPaneOutput);
	for (Token listaToken : listaTokens) {
	    escritor.escribir(listaToken.toString() + "\n");
	}
    }

    private void buscarTokensEn(int fila, String contenido) {
	str = "";
	int columna = 1;
	char[] caracteres = contenido.toCharArray();
	for (char caracter : caracteres) {
	    if (esEspacio(caracter)) {
		validarStr(caracter, fila, columna);
		columna++;
		str = "";
	    } else if (esTabulacion(caracter)) {
		validarStr(caracter, fila, columna);
		columna++;
		str = "";
	    } else if (esSaltoDeLinea(caracter)) {
		validarStr(caracter, fila, columna);
		str = "";
	    } else if (esSimboloCadena(caracter)) {
		//si ya inicio o va empezar
		if (estaComenzando()) {
		    str += caracter;
		    columna++;
		} else {
		    //cerrar cadena y verificar que inicie con el mismo simbolo
		    str += caracter;
		    if (esCadenaValida(caracter)) {
			//se compara el caracter de cierre con el de inicio
			crearTokenCadena(fila, columna);
			columna++;
		    } else {
			//signo de inicio diferente al de cierre
			generarError(fila, columna);
			columna++;
		    }
		    str = "";
		}
	    } else if (Character.isAlphabetic(caracter)) {
		str += caracter;
		columna++;
	    } else if (Character.isDigit(caracter)) {
		str += caracter;
	    } else {
		//si no es numero ni letra, ni cadena, ni espacio, ni tab ni salto
		str += caracter;
	    }
	}
    }

    private boolean estaComenzando() {
	return str.isEmpty();
    }

    private boolean esCadenaValida(char caracter) {
	return caracter == str.charAt(0);
    }

    private void validarStr(char caracter, int fila, int columna) {
	if (!str.isBlank()) {
	    if (str.charAt(0) == '\"' || str.charAt(0) == '\'') {
		str += caracter;
	    } else {
		if (diccionario.containsKey(str)) {///////////////////////////////////////
		    //operadores y palabras reservadas.(hashmap)
		    crearToken(fila, columna);
		} else {
		    //identificadores, numero.(metodos)
		    crearTokenV(fila, columna);
		}
		if (caracter == Alfabeto.ESPACIO.getSimbolo()) {
		    crearTokenEspacio(fila, columna);
		} else if (caracter == Alfabeto.TABULACION.getSimbolo()) {
		    crearTokenTab(fila, columna);
		} else if (caracter == Alfabeto.SALTO_LINEA.getSimbolo()) {
		    crearTokenSalto(fila, columna);
		}
	    }
	}

    }

    private void crearToken(int fila, int columna) {
	Token token = new Token(diccionario.get(str), str, fila, columna);
	listaTokens.add(token);
    }

    private void crearTokenEspacio(int fila, int columna) {
	Token token = new Token(diccionario.get(" "), "espacio", fila, columna);
	listaTokens.add(token);
    }

    private void crearTokenTab(int fila, int columna) {
	Token token = new Token(diccionario.get("\t"), "tabulacion", fila, columna);
	System.out.println("sssssssssssss");
	listaTokens.add(token);
    }

    private void crearTokenSalto(int fila, int columna) {
	Token token = new Token(diccionario.get("\n"), "salto de linea", fila, columna);
	System.out.println("zzzz");
	listaTokens.add(token);
    }

    private void crearTokenCadena(int fila, int columna) {
	Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna);
	listaTokens.add(token);
    }

    private void crearTokenEntero(int fila, int columna) {
	Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna);
	listaTokens.add(token);
    }

    private void crearTokenDecimal(int fila, int columna) {
	Token token = new Token(TokenEnum.CONSTANTE, str, fila, columna);
	listaTokens.add(token);
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
	} else if (esIdentificador()) {
	    
	} else {
	    generarError(fila, columna);
	}
    }

    private void generarError(int fila, int columna) {
	System.out.println("error " + fila + " : " + (columna - str.length()));
    }

    private boolean esIdentificador(){
	return true;
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

    private boolean esEspacio(char caracter) {
	return caracter == Alfabeto.ESPACIO.getSimbolo();
    }

    private boolean esSaltoDeLinea(char caracter) {
	return caracter == Alfabeto.SALTO_LINEA.getSimbolo();
    }

    private boolean esTabulacion(char caracter) {
	return caracter == Alfabeto.TABULACION.getSimbolo();
    }

    private boolean esSimboloCadena(char caracter) {
	return caracter == Alfabeto.COMILLA.getSimbolo()
		|| caracter == Alfabeto.DOBLE_COMILLA.getSimbolo();
    }

}
