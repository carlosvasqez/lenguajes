package vista;

import controlador.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTextPane;
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
public class Analizador2 {

    private List<Token> listaTokens;
    private int fila;
    private int columna;
    private int currentPos;
    private String input;

    private final Map<String, Tkn> diccionarioPalabraReservada;
    private final Map<String, Tkn> diccionarioSimboloOperador;
    private final Map<String, Tkn> diccionarioSimboloLogico;
    private final Map<String, Tkn> diccionarioSimboloCadena;
    private final Map<String, Tkn> diccionarioSimboloComentario;
    private final Map<String, Tkn> diccionarioSimboloAgrupacion;
    private final Map<String, Tkn> diccionarioSimboloPuntuacion;
    private final Map<String, Tkn> diccionarioEspecial;
    private final JTextPane textPaneEditor;
    private final JTextPane textPaneOutput;
    private final GeneradorReporte generadorReporte;
    private final Limpiador limpiador;

    public Analizador2(JTextPane textPaneEditor, JTextPane textPaneOutput, GeneradorReporte generadorReporte, Limpiador limpiador) {

	this.listaTokens = new ArrayList<>();

	this.textPaneEditor = textPaneEditor;
	this.textPaneOutput = textPaneOutput;
	this.generadorReporte = generadorReporte;
	this.limpiador = limpiador;

	this.diccionarioPalabraReservada = new HashMap<>();
	this.diccionarioSimboloOperador = new HashMap<>();
	this.diccionarioSimboloLogico = new HashMap<>();
	this.diccionarioSimboloCadena = new HashMap<>();
	this.diccionarioSimboloComentario = new HashMap<>();
	this.diccionarioSimboloAgrupacion = new HashMap<>();
	this.diccionarioSimboloPuntuacion = new HashMap<>();
	this.diccionarioEspecial = new HashMap<>();

	diccionarioSimboloOperador.put("+", Aritmetico.SUMA);
	diccionarioSimboloOperador.put("-", Aritmetico.RESTA);
	diccionarioSimboloOperador.put("**", Aritmetico.EXPONENTE);
	diccionarioSimboloOperador.put("/", Aritmetico.DIVISON);
	diccionarioSimboloOperador.put("//", Aritmetico.DIVISON);
	diccionarioSimboloOperador.put("%", Aritmetico.MODULO);
	diccionarioSimboloOperador.put("*", Aritmetico.MULTIPLICACION);
	diccionarioSimboloOperador.put("==", Comparacion.DOBLE_IGUAL);
	diccionarioSimboloOperador.put("!=", Comparacion.DIFERENTE);
	diccionarioSimboloOperador.put(">", Comparacion.MAYOR_QUE);
	diccionarioSimboloOperador.put("<", Comparacion.MENOR_QUE);
	diccionarioSimboloOperador.put(">=", Comparacion.MAYOR_IGUAL_QUE);
	diccionarioSimboloOperador.put("<=", Comparacion.MENOR_IGUAL_QUE);

	diccionarioPalabraReservada.put("and", Logico.Y);
	diccionarioPalabraReservada.put("or", Logico.O);
	diccionarioPalabraReservada.put("not", Logico.NEGACION);

	diccionarioSimboloOperador.put("=", Asignacion.IGUAL);
	diccionarioSimboloOperador.put("+=", Asignacion.MAS_IGUAL);
	diccionarioSimboloOperador.put("-=", Asignacion.MENOS_IGUAL);
	diccionarioSimboloOperador.put("*=", Asignacion.POR_IGUAL);
	diccionarioSimboloOperador.put("%=", Asignacion.MODULO_IGUAL);
	diccionarioSimboloOperador.put("**=", Asignacion.POTENCIA_IGUAL);
	diccionarioSimboloOperador.put("/=", Asignacion.DIVISION_IGUAL);
	diccionarioSimboloOperador.put("//=", Asignacion.DOBLE_DIVISION_IGUAL);

	//diccionario.put("and", TokenEnum.PALABRA_RESERVADA);
	diccionarioPalabraReservada.put("as", PalabraReservada.AS);
	diccionarioPalabraReservada.put("assert", PalabraReservada.ASSET);
	diccionarioPalabraReservada.put("break", PalabraReservada.BREAK);
	diccionarioPalabraReservada.put("class", PalabraReservada.CLASS);
	diccionarioPalabraReservada.put("continue", PalabraReservada.CONTINUE);
	diccionarioPalabraReservada.put("def", PalabraReservada.DEF);
	diccionarioPalabraReservada.put("del", PalabraReservada.DEL);
	diccionarioPalabraReservada.put("elif", PalabraReservada.ELIF);
	diccionarioPalabraReservada.put("else", PalabraReservada.ELSE);
	diccionarioPalabraReservada.put("except", PalabraReservada.EXCEPT);
	//diccionario.put("False", TokenEnum.PALABRA_RESERVADA);
	diccionarioPalabraReservada.put("finally", PalabraReservada.FINALLY);
	diccionarioPalabraReservada.put("for", PalabraReservada.FOR);
	diccionarioPalabraReservada.put("from", PalabraReservada.FROM);
	diccionarioPalabraReservada.put("global", PalabraReservada.GLOBAL);
	diccionarioPalabraReservada.put("if", PalabraReservada.IF);
	diccionarioPalabraReservada.put("import", PalabraReservada.IMPORT);
	diccionarioPalabraReservada.put("in", PalabraReservada.IN);
	diccionarioPalabraReservada.put("is", PalabraReservada.IS);
	diccionarioPalabraReservada.put("lambda", PalabraReservada.LAMBDA);
	diccionarioPalabraReservada.put("None", PalabraReservada.NONE);
	diccionarioPalabraReservada.put("nonlocal", PalabraReservada.NONLOCAL);
	//diccionario.put("not", TokenEnum.PALABRA_RESERVADA);
	//diccionario.put("or", TokenEnum.PALABRA_RESERVADA);
	diccionarioPalabraReservada.put("pass", PalabraReservada.PASS);
	diccionarioPalabraReservada.put("raise", PalabraReservada.RAISE);
	diccionarioPalabraReservada.put("return", PalabraReservada.RETURN);
	//diccionario.put("True", TokenEnum.PALABRA_RESERVADA);
	diccionarioPalabraReservada.put("try", PalabraReservada.TRY);
	diccionarioPalabraReservada.put("while", PalabraReservada.WHILE);
	diccionarioPalabraReservada.put("with", PalabraReservada.WITH);
	diccionarioPalabraReservada.put("yield", PalabraReservada.YIELD);

	diccionarioPalabraReservada.put("True", Constante.TRUE);
	diccionarioPalabraReservada.put("False", Constante.FALSE);

	//diccionarioSimboloComentario.put("#", Comentario.COMENTARIO);
	//diccionario.put(".", Otros.PUNTO);
	diccionarioSimboloAgrupacion.put("(", Otros.PARENTESIS_A);
	diccionarioSimboloAgrupacion.put(")", Otros.PARENTESIS_C);
	diccionarioSimboloAgrupacion.put("{", Otros.LLAVE_A);
	diccionarioSimboloAgrupacion.put("}", Otros.LLAVE_C);
	diccionarioSimboloAgrupacion.put("[", Otros.CORCHETES_A);
	diccionarioSimboloAgrupacion.put("]", Otros.CORCHETES_C);

	diccionarioSimboloPuntuacion.put(",", Otros.COMA);
	diccionarioSimboloPuntuacion.put(";", Otros.PUNTO_Y_COMA);
	diccionarioSimboloPuntuacion.put(":", Otros.DOS_PUNTOS);
	diccionarioSimboloPuntuacion.put(".", Otros.PUNTO);

	//diccionarioSimboloCadena.put("\'", Constante.COMILLA);
	//diccionarioSimboloCadena.put("\"", Constante.COMILLA_DOBLE);
	//diccionarioEspecial.put(" ", Especial.ESPACIO);
	//diccionarioEspecial.put("\n", Especial.SALTO_LINEA);
	//diccionarioEspecial.put("\t", Especial.TABULACION);
    }

    public void realizar() {
	//reiniciar los tokens porque al ser el mismo objeto analizador para
	//todo el programa entonces los tokens solo se acumulan aunque se limpie la pantalla
	listaTokens = new ArrayList<>();
	fila = 1;
	columna = 1;
	currentPos = 0;
	//textPaneEditor.setContentType("text/plain;charset=UTF-8");
	input = textPaneEditor.getText();
//	for (char c : input.toCharArray()) {
//	    System.out.println((int) c);
//	}
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
	tokenize();
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

    public void tokenize() {
	while (currentPos < input.length()) {
	    char currentChar = input.charAt(currentPos);

	    if (Character.isDigit(currentChar)) {
		//<editor-fold defaultstate="collapsed" desc="entero o decimal">
		StringBuilder number = new StringBuilder();
		boolean isDecimal = false;
		while (currentPos < input.length() && (Character.isDigit(input.charAt(currentPos)) || input.charAt(currentPos) == '.')) {
		    char nextChar = input.charAt(currentPos);
		    if (nextChar == '.') {
			if (isDecimal) {
			    break;
			}
			isDecimal = true;
		    }
		    number.append(nextChar);
		    currentPos++;
		    columna++;
		}
		if (isDecimal) {
		    //es decimal
		    addToken(Constante.DECIMAL, number.toString());
		} else {
		    //es entero
		    addToken(Constante.ENTERO, number.toString());
		}
		//</editor-fold>
	    } else if (Character.isLetter(currentChar) || currentChar == '_') {
		//<editor-fold defaultstate="collapsed" desc="identificador o palabra reservada">
		StringBuilder identifier = new StringBuilder();
		while (currentPos < input.length()
			&& (Character.isLetterOrDigit(input.charAt(currentPos))
			|| input.charAt(currentPos) == '_')) {
		    identifier.append(input.charAt(currentPos));
		    currentPos++;
		    columna++;
		}
		// Verificar si es una palabra reservada o identificador
		String identifierStr = identifier.toString();
		Tkn tkn = diccionarioPalabraReservada.get(identifierStr);
		if (tkn != null) {
		    //es palabra reservada
		    addToken(tkn, identifierStr);
		} else {
		    if (identifier.length() == 1 && identifier.charAt(0) == '_') {
			//es solo un guion bajo _
			addTokenError(Errorr.ERROR, identifierStr, "es solo un gion bajo");
		    } else {
			//es identificador
			addToken(Identificador.IDENTIFICADOR, identifierStr);
		    }
		}
		//</editor-fold>
	    } else if (Character.isWhitespace(currentChar)) {
		//<editor-fold defaultstate="collapsed" desc="espacio, tab o next line">
		currentPos++;
		if (currentChar == '\n') {
		    //es salto de linea
		    columna++;
		    addToken(Especial.SALTO_LINEA, "\n");
		    columna = 1;
		}
		if (currentChar == '\t') {
		    //es tabulacion
		    addToken(Especial.TABULACION, "\t");
		    ///////////////////////////////////////////////////pendiente por definir el tab
		}
		if (currentChar == ' ') {
		    //es espacio
		    columna++;
		    addToken(Especial.ESPACIO, " ");
		}
		//</editor-fold>
	    } else if (currentChar == '"') {
		//<editor-fold defaultstate="collapsed" desc=" cadena " ">
		StringBuilder string = new StringBuilder();
		currentPos++;
		columna++;
		while (currentPos < input.length() && input.charAt(currentPos) != '"') {
		    string.append(input.charAt(currentPos));
		    if (input.charAt(currentPos) == '\n') {
			currentPos++;
			break;
		    }
		    currentPos++;
		    columna++;
		}
		if (currentPos < input.length() && input.charAt(currentPos) == '"') {
		    //es cadena completa
		    currentPos++;
		    columna++;
		    addToken(Constante.CADENA, "\"" + string.toString() + "\"");
		}  else if (currentPos < input.length() && input.charAt(currentPos) != '"') {
		    //error de cadena no cerrada al final del documento
		    System.out.println("ssssssssssss");
		    addTokenError(Errorr.ERROR, "\"" + string.toString(), "cadena no cerrada al final del archivo");
		}
		//</editor-fold>
	    } else if (currentChar == '\'') {
		//<editor-fold defaultstate="collapsed" desc="cadena ' ">
		StringBuilder string = new StringBuilder();
		currentPos++;
		columna++;
		while (currentPos < input.length() && input.charAt(currentPos) != '\'' && input.charAt(currentPos) != '\n') {
		    string.append(input.charAt(currentPos));
		    currentPos++;
		    columna++;
		}
		if (currentPos < input.length() && input.charAt(currentPos) == '\'') {
		    //es cadena completa
		    currentPos++;
		    columna++;
		    addToken(Constante.CADENA, "\'" + string.toString() + "\'");
		} else if (currentPos < input.length() && input.charAt(currentPos) == '\n') {
		    //es error de cadena no cerrada
		    currentPos++;
		    columna++;
		    addTokenError(Errorr.ERROR, "\'" + string.toString(), "cadena no cerrada");
		} else if (currentPos == input.length()) {
		    //error de cadena no cerrada al final del documento
		    addTokenError(Errorr.ERROR, "\'" + string.toString(), "cadena no cerrada al final del archivo");
		}
		//</editor-fold>
	    } else if (currentChar == '+') {
		//<editor-fold defaultstate="collapsed" desc="+ y +=">
		int nextPos = currentPos + 1;
		if (nextPos < input.length() && input.charAt(nextPos) == '=') {
		    //es una asignacion de suma +=
		    columna++;
		    columna++;
		    addToken(Asignacion.MAS_IGUAL, String.valueOf(currentChar) + "=");
		    currentPos += 2;
		} else {
		    //es una suma +
		    columna++;
		    addToken(Aritmetico.SUMA, String.valueOf(currentChar));
		    currentPos += 1;
		}
		//</editor-fold>
	    } else if (currentChar == '-') {
		//<editor-fold defaultstate="collapsed" desc="- -=">
		int nextPos = currentPos + 1;
		if (nextPos < input.length() && input.charAt(nextPos) == '=') {
		    //es una asignacion de resta -=
		    columna++;
		    columna++;
		    addToken(Asignacion.MENOS_IGUAL, String.valueOf(currentChar) + "=");
		    currentPos += 2;
		} else {
		    //es una resta -
		    columna++;
		    addToken(Aritmetico.RESTA, String.valueOf(currentChar));
		    currentPos += 1;
		}
		//</editor-fold>
	    } else if (currentChar == '*') {
		//<editor-fold defaultstate="collapsed" desc="* *= ** **= ">
		int nextPos = currentPos + 1;
		if (nextPos < input.length() && input.charAt(nextPos) == '*') {
		    int nextPos2 = currentPos + 2;
		    if (nextPos2 < input.length() && input.charAt(nextPos2) == '=') {
			//es una asignacion de potencia **=
			char nextChar = input.charAt(nextPos);
			addToken(Asignacion.POTENCIA_IGUAL, String.valueOf(currentChar) + String.valueOf(nextChar) + "=");
			currentPos += 3;
		    } else {
			//es una potencia **
			addToken(Aritmetico.EXPONENTE, "**");
			currentPos += 2;
		    }
		} else if (nextPos < input.length() && input.charAt(nextPos) == '=') {
		    //es una asignacion de multiplicacion *=
		    char nextChar = input.charAt(nextPos);
		    addToken(Asignacion.POR_IGUAL, String.valueOf(currentChar) + String.valueOf(nextChar));
		    currentPos += 2;
		} else {
		    // Es una multiplicación simple *
		    addToken(Aritmetico.MULTIPLICACION, "*");
		    currentPos += 1;
		}
		//</editor-fold>
	    } else if (currentChar == '/') {
		//<editor-fold defaultstate="collapsed" desc="/ /= // //=">
		int nextPos = currentPos + 1;
		if (nextPos < input.length() && input.charAt(nextPos) == '/') {
		    int nextPos2 = currentPos + 2;
		    if (nextPos2 < input.length() && input.charAt(nextPos2) == '=') {
			//es una asignacion de divison //=
			char nextChar = input.charAt(nextPos);
			addToken(Asignacion.DOBLE_DIVISION_IGUAL, String.valueOf(currentChar) + String.valueOf(nextChar) + "=");
			currentPos += 3;
		    } else {
			//es una division simple //
			addToken(Aritmetico.DIVISON, "//");
			currentPos += 2;
		    }
		} else if (nextPos < input.length() && input.charAt(nextPos) == '=') {
		    //es una asignacion de de divison  /=
		    char nextChar = input.charAt(nextPos);
		    addToken(Asignacion.DIVISION_IGUAL, String.valueOf(currentChar) + String.valueOf(nextChar));
		    currentPos += 2;
		} else {
		    // Es una division simple /
		    addToken(Aritmetico.DIVISON, "/");
		    currentPos += 1;
		}
		//</editor-fold>
	    } else if (currentChar == '%') {
		//<editor-fold defaultstate="collapsed" desc="%">
		// Es modulo
		columna++;
		addToken(Aritmetico.MODULO, "%");
		currentPos++;
		//</editor-fold>
	    } else if (currentChar == '<' || currentChar == '>') {
		//<editor-fold defaultstate="collapsed" desc="< > <= >=">
		int nextPos = currentPos + 1;
		if (nextPos < input.length() && input.charAt(nextPos) == '=') {
		    //es menor igual <= o  mayor igual >=
		    columna++;
		    columna++;
		    String caracterActualString = String.valueOf(currentChar);
		    addToken(diccionarioSimboloOperador.get(caracterActualString + "="), caracterActualString + "=");
		    currentPos += 2;
		} else {
		    //es menor < o mayor >
		    columna++;
		    String caracterActualString = String.valueOf(currentChar);
		    addToken(diccionarioSimboloOperador.get(caracterActualString), caracterActualString);
		    currentPos++;
		}
		//</editor-fold>
	    } else if (currentChar == '=') {
		//<editor-fold defaultstate="collapsed" desc="= ==">
		int nextPos = currentPos + 1;
		if (nextPos < input.length() && input.charAt(nextPos) == '=') {
		    // es igual comparacion == 
		    columna++;
		    columna++;
		    addToken(Comparacion.DOBLE_IGUAL, "==");
		    currentPos += 2;
		} else {
		    //es igual asignacion =
		    columna++;
		    addToken(Asignacion.IGUAL, "=");
		    currentPos++;
		}
		//</editor-fold>
	    } else if (currentChar == '!') {
		//<editor-fold defaultstate="collapsed" desc="! !=">
		// Verificar operador de desigualdad (!=)
		int nextPos = currentPos + 1;
		if (nextPos < input.length() && input.charAt(nextPos) == '=') {
		    //es diferente !=
		    columna++;
		    columna++;
		    addToken(Comparacion.DIFERENTE, "!=");
		    currentPos += 2;
		} else {
		    //es signo de admiracion ! o error
		    columna++;
		    addTokenError(Errorr.ERROR, String.valueOf(currentChar), "es solo un signo de exclamacion");
		    currentPos++;
		}
		//</editor-fold>
	    } else if (currentChar == '#') {
		//<editor-fold defaultstate="collapsed" desc="comentario #">
		StringBuilder comentario = new StringBuilder();
		currentPos++;
		columna++;
		while (currentPos < input.length() && input.charAt(currentPos) != '\n' && input.charAt(currentPos) != '\r') {
		    comentario.append(input.charAt(currentPos));
		    System.out.println((int) input.charAt(currentPos));
		    columna++;
		    currentPos++;
		}
		//es comentario
		addToken(Comentario.COMENTARIO, "#" + comentario.toString());
		//</editor-fold>
	    } else {
		//<editor-fold defaultstate="collapsed" desc="simbolo fuera del alfabeto">
		//otro simbolo fuera del alfabeto
		columna++;
		String caracterActualString = String.valueOf(currentChar);
		if (diccionarioSimboloAgrupacion.containsKey(caracterActualString)) {
		    addToken(diccionarioSimboloAgrupacion.get(caracterActualString), caracterActualString);
		} else if (diccionarioSimboloPuntuacion.containsKey(caracterActualString)) {
		    addToken(diccionarioSimboloPuntuacion.get(caracterActualString), caracterActualString);
		} else {
		    addTokenError(Errorr.ERROR, caracterActualString, "simbolo fuera del alfabeto " + caracterActualString);
		}
		currentPos++;
		//</editor-fold>
	    }
	}
    }

    private void addToken(Tkn tkn, String lexema) {
	Token token = new Token(tkn, lexema, fila, columna - lexema.length());
	listaTokens.add(token);
    }

    private void addTokenError(Tkn tkn, String lexema, String mensaje) {
	Token token = new TokenError(tkn, lexema, fila, columna - lexema.length(), mensaje);
	listaTokens.add(token);
    }

}
