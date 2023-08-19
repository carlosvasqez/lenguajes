package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import modelo.Especial;
import modelo.Token;
import modelo.TokenEnum;
import modelo.token.identificadores.Identificador;
import modelo.token.operadores.Aritmetico;
import modelo.token.operadores.Comparacion;

/**
 *
 * @author usuario
 */
public class Analizador {

    private Map<String, TokenEnum> diccionario;
    private List<Token> listaTokens;
    private JTextPane textPaneEditor;
    private Set<Character> alfabeto;

    public Analizador(JTextPane textPaneEditor) {
	//alfabeto
	alfabeto = new HashSet<>();
	alfabeto.add('+');
	alfabeto.add('-');
	alfabeto.add('*');
	alfabeto.add('/');
	alfabeto.add('%');

	alfabeto.add('>');
	alfabeto.add('<');
	alfabeto.add('=');
	alfabeto.add('!');

	alfabeto.add('\n');
	alfabeto.add('\t');
	alfabeto.add(' ');

	alfabeto.add('\'');
	alfabeto.add('\"');
	alfabeto.add('#');

	alfabeto.add('(');
	alfabeto.add(')');
	alfabeto.add('{');
	alfabeto.add('}');
	alfabeto.add('[');
	alfabeto.add(']');

	alfabeto.add(',');
	alfabeto.add(';');
	alfabeto.add(':');
	alfabeto.add('.');

	this.textPaneEditor = textPaneEditor;
	this.diccionario = new HashMap<>();
	this.listaTokens = new ArrayList<>();

	///
	diccionario.put("+", TokenEnum.ARITMETICO);
	diccionario.put("-", TokenEnum.ARITMETICO);
	diccionario.put("**", TokenEnum.ARITMETICO);
	diccionario.put("/", TokenEnum.ARITMETICO);
	diccionario.put("//", TokenEnum.ARITMETICO);
	diccionario.put("%", TokenEnum.ARITMETICO);
	diccionario.put("*", TokenEnum.ARITMETICO);

	diccionario.put("==", TokenEnum.COMPARACION);
	diccionario.put("!=", TokenEnum.COMPARACION);
	diccionario.put(">", TokenEnum.COMPARACION);
	diccionario.put("<", TokenEnum.COMPARACION);
	diccionario.put(">=", TokenEnum.COMPARACION);
	diccionario.put("<=", TokenEnum.COMPARACION);

	diccionario.put("=", TokenEnum.ASIGNACION);

	diccionario.put("and", TokenEnum.PALABRA_RESERVADA);
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
	diccionario.put("not", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("or", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("pass", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("raise", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("return", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("True", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("try", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("while", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("with", TokenEnum.PALABRA_RESERVADA);
	diccionario.put("yield", TokenEnum.PALABRA_RESERVADA);

	diccionario.put("True", TokenEnum.CONSTANTE);
	diccionario.put("False", TokenEnum.CONSTANTE);

	diccionario.put("(", TokenEnum.OTRO);
	diccionario.put(")", TokenEnum.OTRO);
	diccionario.put("{", TokenEnum.OTRO);
	diccionario.put("}", TokenEnum.OTRO);
	diccionario.put("[", TokenEnum.OTRO);
	diccionario.put("]", TokenEnum.OTRO);
	diccionario.put(",", TokenEnum.OTRO);
	diccionario.put(";", TokenEnum.OTRO);
	diccionario.put(":", TokenEnum.OTRO);
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
    }

    private void verificarSuma(char c) {

    }

//    private void verificarAritmetico(char c, String temporal) {
//	if (c == Aritmetico.SUMA.getSimbolo()
//		|| c == Aritmetico.RESTA.getSimbolo()
//		|| c == Aritmetico.MULTIPLICACION.getSimbolo()
//		|| c == Aritmetico.MODULO.getSimbolo()) {
//
//	    temporal = temporal.concat(String.valueOf(c));
//	    // crearToken(String.valueOf(c), fila, columna);
//
//	} else {
//
//	}
//    }
//
//    private void verificarEspecial(char c, String temporal) {
//	if (c == Especial.ESPACIO.getSimbolo()) {
//
//	    temporal = temporal.concat(" ");
//
//	} else if (c == Especial.SALTO_LINEA.getSimbolo()) {
//
//	} else if (c == Especial.TABULACION.getSimbolo()) {
//
//	}
//    }
//
//    private boolean esSimboloCombinableDoble(char c) {
//	return c == Aritmetico.SUMA.getSimbolo()
//		|| c == Comparacion.IGUAL.getSimbolo()
//		|| c == Aritmetico.RESTA.getSimbolo()
//		|| c == Aritmetico.MULTIPLICACION.getSimbolo()
//		|| c == Aritmetico.DIVISION.getSimbolo()
//		|| c == Comparacion.MAYOR_QUE.getSimbolo()
//		|| c == Comparacion.MENOR_QUE.getSimbolo()
//		|| c == Comparacion.ADMIRACION.getSimbolo();
//    }
//
//    private boolean sePuedeCombinarDoble(char c, String temporal) {
//	return temporal.length() == 1
//		&& esSimboloCombinableDoble(temporal.charAt(0));
//    }
//
//    private boolean esIdentificador(char caracter, String str) {
//	return str.length() >= 0
//		&& (Character.isAlphabetic(caracter) || caracter == Identificador.GUION_BAJO.getSimbolo())
//		&& str.contains(new StringBuilder(" "));
//    }
//
//    private boolean esEspecial(char c) {
//	return c == Especial.ESPACIO.getSimbolo()
//		|| c == Especial.SALTO_LINEA.getSimbolo()
//		|| c == Especial.TABULACION.getSimbolo();
//    }
    private boolean esEspacio(char caracter) {
	return caracter == Especial.ESPACIO.getSimbolo();
    }

    private boolean esToken(String str) {
	return !str.isBlank();
    }

    private boolean esSaltoDeLinea(char caracter) {
	return caracter == Especial.SALTO_LINEA.getSimbolo();
    }

    private void buscarTokensEn(int fila, String contenido) {
	String str = "";
	int columna = 1;
	char[] caracteres = contenido.toCharArray();
	for (char caracter : caracteres) {
	    //
	    //
	    if (esEspacio(caracter)) {
		if (esToken(str)) {
		    crearToken(str, fila, columna);
		} else {
		    generarError();
		}
	    } else if (esSaltoDeLinea(caracter)) {
		
	    }

//	    if (esIdentificador(caracter, str)) {
//		str += caracter;
//		//el identificador token se crea cuando
//		//se obtiene un espacio, salto de liea etc.
//	    } else if (esEspecial(caracter)) {
//
//	    } else if (esSimboloCombinableDoble(caracter)) {
//		if (str.isEmpty()) {
//		    str += caracter;
//		} else {
//
//		}
//		if (sePuedeCombinarDoble(caracter, str)) {
//		    str += caracter;
//		} else {
//
//		}
//		str = str.concat(String.valueOf(caracter));
//	    }
	}
    }

    private void crearToken(String str, int fila, int columna) {
	//verificar si coicide con algun elemento del lenguaje
	if (diccionario.containsKey(str)) {//key:int value=PALABRA_RESERVADA
	    Token token = new Token(diccionario.get(str), str, fila, columna);
	    listaTokens.add(token);
	} else {
	    generarError();
	}
    }

    private void generarError() {

    }

}
