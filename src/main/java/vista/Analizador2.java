package vista;

import controlador.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private int posicionActual;
    private String texto;
    String carpeta;

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
        crearCarpetaGraficos();
        fila = 1;
        columna = 1;
        posicionActual = 0;
        //textPaneEditor.setContentType("text/plain;charset=UTF-8");
        texto = textPaneEditor.getText();
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

    private void crearCarpetaGraficos() {
        //crear carpeta segun la hora y fecha
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        carpeta = fechaHoraActual.format(formatter);
    }

    public List<Token> getListaTokens() {
        return listaTokens;
    }

    private void generarAnalisis() {
        buscarTokens();
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

    public void buscarTokens() {
        while (posicionActual < texto.length()) {
            char caracterActual = texto.charAt(posicionActual);

            if (Character.isDigit(caracterActual)) {
                //<editor-fold defaultstate="collapsed" desc="entero o decimal">
                StringBuilder numero = new StringBuilder();
                boolean esDecimal = false;
                while (posicionActual < texto.length() && (Character.isDigit(texto.charAt(posicionActual)) || texto.charAt(posicionActual) == '.')) {
                    char caracterSiguiente = texto.charAt(posicionActual);
                    if (caracterSiguiente == '.') {
                        if (esDecimal) {
                            break;
                        }
                        esDecimal = true;
                    }
                    numero.append(caracterSiguiente);
                    posicionActual++;
                    columna++;
                }
                if (esDecimal) {
                    //es decimal
                    crearToken(Constante.DECIMAL, numero.toString());
                } else {
                    //es entero
                    crearToken(Constante.ENTERO, numero.toString());
                }
                //</editor-fold>
            } else if (Character.isLetter(caracterActual) || caracterActual == '_') {
                //<editor-fold defaultstate="collapsed" desc="identificador o palabra reservada">
                StringBuilder identificador = new StringBuilder();
                while (posicionActual < texto.length()
                        && (Character.isLetterOrDigit(texto.charAt(posicionActual))
                        || texto.charAt(posicionActual) == '_')) {
                    identificador.append(texto.charAt(posicionActual));
                    posicionActual++;
                    columna++;
                }
                // Verificar si es una palabra reservada o identificador
                String identificadorString = identificador.toString();
                Tkn tkn = diccionarioPalabraReservada.get(identificadorString);
                if (tkn != null) {
                    //es palabra reservada
                    crearToken(tkn, identificadorString);
                } else {
                    if (identificador.length() == 1 && identificador.charAt(0) == '_') {
                        //es solo un guion bajo _
                        crearTokenError(Errorr.ERROR, identificadorString, "es solo un gion bajo");
                    } else {
                        //es identificador
                        crearToken(Identificador.IDENTIFICADOR, identificadorString);
                    }
                }
                //</editor-fold>
            } else if (Character.isWhitespace(caracterActual)) {
                //<editor-fold defaultstate="collapsed" desc="espacio, tab o next line">
                posicionActual++;
                if (caracterActual == '\n') {
                    //es salto de linea
                    columna++;
                    crearToken(Especial.SALTO_LINEA, "\n");
                    columna = 1;
                    fila++;
                }
                if (caracterActual == '\t') {
                    //es tabulacion
                    crearToken(Especial.TABULACION, "\t");
                    ///////////////////////////////////////////////////pendiente por definir el tab
                }
                if (caracterActual == ' ') {
                    //es espacio
                    columna++;
                    crearToken(Especial.ESPACIO, " ");
                }
                //</editor-fold>
            } else if (caracterActual == '"') {
                //<editor-fold defaultstate="collapsed" desc=" cadena " ">
                StringBuilder cadenaDobleComilla = new StringBuilder();
                posicionActual++;
                columna++;
                while (posicionActual < texto.length() && texto.charAt(posicionActual) != '"') {
                    cadenaDobleComilla.append(texto.charAt(posicionActual));
                    if (texto.charAt(posicionActual) == '\r') {
                        posicionActual++;
                        columna++;
                        break;
                    }
                    posicionActual++;
                    columna++;
                }
                if (posicionActual < texto.length() && texto.charAt(posicionActual) == '"') {
                    //es cadena completa
                    posicionActual++;
                    columna++;
                    crearToken(Constante.CADENA, "\"" + cadenaDobleComilla.toString() + "\"");
                } else {
                    //error de cadena no cerrada al final del documento
                    crearTokenError(Errorr.ERROR, "\"" + cadenaDobleComilla.toString(), "cadena no cerrada");
                    //fila++;
                    columna = 1;
                }
                //</editor-fold>
            } else if (caracterActual == '\'') {
                //<editor-fold defaultstate="collapsed" desc="cadena ' ">
                StringBuilder cadenaComilla = new StringBuilder();
                posicionActual++;
                columna++;
                while (posicionActual < texto.length() && texto.charAt(posicionActual) != '\''
                        && texto.charAt(posicionActual) != '\r') {
                    cadenaComilla.append(texto.charAt(posicionActual));
                    if (texto.charAt(posicionActual) == '\n') {
                        posicionActual++;
                        columna++;
                        break;
                    }
                    posicionActual++;
                    columna++;
                }
                if (posicionActual < texto.length() && texto.charAt(posicionActual) == '\'') {
                    //es cadena completa
                    posicionActual++;
                    columna++;
                    crearToken(Constante.CADENA, "\'" + cadenaComilla.toString() + "\'");
                } else {
                    //error de cadena no cerrada
                    crearTokenError(Errorr.ERROR, "\'" + cadenaComilla.toString(), "cadena no cerrada");
                    //fila++;
                    columna = 1;
                }
                //</editor-fold>
            } else if (caracterActual == '+') {
                //<editor-fold defaultstate="collapsed" desc="+ y +=">
                int siguientePosicion = posicionActual + 1;
                if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '=') {
                    //es una asignacion de suma +=
                    columna++;
                    columna++;
                    crearToken(Asignacion.MAS_IGUAL, String.valueOf(caracterActual) + "=");
                    posicionActual += 2;
                } else {
                    //es una suma +
                    columna++;
                    crearToken(Aritmetico.SUMA, String.valueOf(caracterActual));
                    posicionActual += 1;
                }
                //</editor-fold>
            } else if (caracterActual == '-') {
                //<editor-fold defaultstate="collapsed" desc="- -=">
                int siguientePosicion = posicionActual + 1;
                if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '=') {
                    //es una asignacion de resta -=
                    columna++;
                    columna++;
                    crearToken(Asignacion.MENOS_IGUAL, String.valueOf(caracterActual) + "=");
                    posicionActual += 2;
                } else {
                    //es una resta -
                    columna++;
                    crearToken(Aritmetico.RESTA, String.valueOf(caracterActual));
                    posicionActual += 1;
                }
                //</editor-fold>
            } else if (caracterActual == '*') {
                //<editor-fold defaultstate="collapsed" desc="* *= ** **= ">
                int siguientePosicion = posicionActual + 1;
                if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '*') {
                    int nextPos2 = posicionActual + 2;
                    if (nextPos2 < texto.length() && texto.charAt(nextPos2) == '=') {
                        //es una asignacion de potencia **=
                        columna++;
                        columna++;
                        columna++;
                        char nextChar = texto.charAt(siguientePosicion);
                        crearToken(Asignacion.POTENCIA_IGUAL, String.valueOf(caracterActual) + String.valueOf(nextChar) + "=");
                        posicionActual += 3;
                    } else {
                        //es una potencia **
                        columna++;
                        columna++;
                        crearToken(Aritmetico.EXPONENTE, "**");
                        posicionActual += 2;
                    }
                } else if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '=') {
                    //es una asignacion de multiplicacion *=
                    columna++;
                    columna++;
                    char nextChar = texto.charAt(siguientePosicion);
                    crearToken(Asignacion.POR_IGUAL, String.valueOf(caracterActual) + String.valueOf(nextChar));
                    posicionActual += 2;
                } else {
                    // Es una multiplicación simple *
                    columna++;
                    crearToken(Aritmetico.MULTIPLICACION, "*");
                    posicionActual += 1;
                }
                //</editor-fold>
            } else if (caracterActual == '/') {
                //<editor-fold defaultstate="collapsed" desc="/ /= // //=">
                int siguientePosicion = posicionActual + 1;
                if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '/') {
                    int nextPos2 = posicionActual + 2;
                    if (nextPos2 < texto.length() && texto.charAt(nextPos2) == '=') {
                        //es una asignacion de divison //=
                        columna++;
                        columna++;
                        columna++;
                        char nextChar = texto.charAt(siguientePosicion);
                        crearToken(Asignacion.DOBLE_DIVISION_IGUAL, String.valueOf(caracterActual) + String.valueOf(nextChar) + "=");
                        posicionActual += 3;
                    } else {
                        //es una division simple //
                        columna++;
                        columna++;
                        crearToken(Aritmetico.DIVISON, "//");
                        posicionActual += 2;
                    }
                } else if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '=') {
                    //es una asignacion de de divison  /=
                    columna++;
                    columna++;
                    char caracterSiguiente = texto.charAt(siguientePosicion);
                    crearToken(Asignacion.DIVISION_IGUAL, String.valueOf(caracterActual) + String.valueOf(caracterSiguiente));
                    posicionActual += 2;
                } else {
                    // Es una division simple /
                    columna++;
                    crearToken(Aritmetico.DIVISON, "/");
                    posicionActual += 1;
                }
                //</editor-fold>
            } else if (caracterActual == '%') {
                //<editor-fold defaultstate="collapsed" desc="%">
                // Es modulo
                columna++;
                crearToken(Aritmetico.MODULO, "%");
                posicionActual++;
                //</editor-fold>
            } else if (caracterActual == '<' || caracterActual == '>') {
                //<editor-fold defaultstate="collapsed" desc="< > <= >=">
                int siguientePosicion = posicionActual + 1;
                if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '=') {
                    //es menor igual <= o  mayor igual >=
                    columna++;
                    columna++;
                    String caracterActualString = String.valueOf(caracterActual);
                    crearToken(diccionarioSimboloOperador.get(caracterActualString + "="), caracterActualString + "=");
                    posicionActual += 2;
                } else {
                    //es menor < o mayor >
                    columna++;
                    String caracterActualString = String.valueOf(caracterActual);
                    crearToken(diccionarioSimboloOperador.get(caracterActualString), caracterActualString);
                    posicionActual++;
                }
                //</editor-fold>
            } else if (caracterActual == '=') {
                //<editor-fold defaultstate="collapsed" desc="= ==">
                int siguientePosicion = posicionActual + 1;
                if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '=') {
                    // es igual comparacion == 
                    columna++;
                    columna++;
                    crearToken(Comparacion.DOBLE_IGUAL, "==");
                    posicionActual += 2;
                } else {
                    //es igual asignacion =
                    columna++;
                    crearToken(Asignacion.IGUAL, "=");
                    posicionActual++;
                }
                //</editor-fold>
            } else if (caracterActual == '!') {
                //<editor-fold defaultstate="collapsed" desc="! !=">
                // Verificar operador de desigualdad (!=)
                int siguientePosicion = posicionActual + 1;
                if (siguientePosicion < texto.length() && texto.charAt(siguientePosicion) == '=') {
                    //es diferente !=
                    columna++;
                    columna++;
                    crearToken(Comparacion.DIFERENTE, "!=");
                    posicionActual += 2;
                } else {
                    //es signo de admiracion ! o error
                    columna++;
                    crearTokenError(Errorr.ERROR, String.valueOf(caracterActual), "es solo un signo de exclamacion");
                    posicionActual++;
                }
                //</editor-fold>
            } else if (caracterActual == '#') {
                //<editor-fold defaultstate="collapsed" desc="comentario #">
                StringBuilder comentario = new StringBuilder();
                posicionActual++;
                columna++;
                while (posicionActual < texto.length() && texto.charAt(posicionActual) != '\n' && texto.charAt(posicionActual) != '\r') {
                    comentario.append(texto.charAt(posicionActual));
                    columna++;
                    posicionActual++;
                }
                //es comentario
                crearToken(Comentario.COMENTARIO, "#" + comentario.toString());
                //</editor-fold>
            } else {
                //<editor-fold defaultstate="collapsed" desc="simbolo fuera del alfabeto">
                //otro simbolo fuera del alfabeto
                columna++;
                String caracterActualString = String.valueOf(caracterActual);
                if (diccionarioSimboloAgrupacion.containsKey(caracterActualString)) {
                    crearToken(diccionarioSimboloAgrupacion.get(caracterActualString), caracterActualString);
                } else if (diccionarioSimboloPuntuacion.containsKey(caracterActualString)) {
                    crearToken(diccionarioSimboloPuntuacion.get(caracterActualString), caracterActualString);
                } else {
                    crearTokenError(Errorr.ERROR, caracterActualString, "simbolo fuera del alfabeto " + caracterActualString);
                }
                posicionActual++;
                //</editor-fold>
            }
        }
    }

    private void crearToken(Tkn tkn, String lexema) {
        Token token = new Token(tkn, lexema, fila, columna - lexema.length());
        if (tkn != Especial.ESPACIO && tkn != Especial.TABULACION && tkn != Especial.SALTO_LINEA) {
            GeneradorGrafico generadorGrafico = new GeneradorGrafico(carpeta);
            generadorGrafico.ejecutar(token);
        }
        System.out.println(token);
        listaTokens.add(token);
    }

    private void crearTokenError(Tkn tkn, String lexema, String mensaje) {
        Token token = new TokenError(tkn, lexema, fila, columna - lexema.length(), mensaje);
        System.out.println(token);
        listaTokens.add(token);
    }

}
