package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import modelo.Token;

/**
 *
 * @author usuario
 */
public class GeneradorGrafico {

    private final String carpeta;

    public GeneradorGrafico(String carpeta) {
        this.carpeta = "./" + "graficosAnalizador/" + carpeta + "/";
    }

    public void ejecutar(Token token) {
        String lexema = token.getLexena();
        String tipoToken = token.getTokenEnum().toString();
        String graficoNombre = token.getTokenEnum().toString() + token.hashCode();
        String nombreArchivo = String.valueOf(graficoNombre);

        //
        //
        //CREA CARPETA
        Path carpetaPath = Paths.get(carpeta);
        try {
            Files.createDirectories(carpetaPath);
        } catch (IOException e) {
            //si ya existe la carpeta no se crea
            e.printStackTrace();
        }

        //
        //
        //CAMBIA DOBLE COMILLA POR COMILLA SIMPLE POR ERROR AL GENERAR GRAFICO
        char caracterAntiguo = '\"';
        char caracterNuevo = '\'';
        StringBuilder builder = new StringBuilder();
        for (char c : lexema.toCharArray()) {
            if (c == caracterAntiguo) {
                builder.append(caracterNuevo);
            } else {
                builder.append(c);
            }
        }
        String nuevoLexema = builder.toString();

        //
        //
        //CREA ARCHIVO E IMPRIMIRLO
        String pathCompletoArchivo = carpeta + nombreArchivo + ".dot";
        String stringGrapgviz = convertirTokenAGrafico(nuevoLexema, tipoToken);//el string de archivo .dot
        File archivo = new File(pathCompletoArchivo);
        try (FileWriter fileWriter = new FileWriter(archivo); PrintWriter printWriter = new PrintWriter(fileWriter);) {
            printWriter.print(stringGrapgviz);

        } catch (Exception e) {
            //e.printStackTrace();
        }

        //
        //
        //EJECUTA COMANDO EN CONSOLA PARA GENERAR EL GRAFICO
        String pathCompletoImagen = carpeta + nombreArchivo + ".png";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tpng", "-o", pathCompletoImagen, pathCompletoArchivo);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();

            //
            //si no hay error al generar la imagen entonces se asigna al toen el path de su imagen
            token.setPathGrafico(pathCompletoImagen);
            //
            //
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    private String convertirTokenAGrafico(String lexema, String tipoToken) {
        StringBuilder digraph = new StringBuilder();

        // Encabezado del gráfico DOT
        digraph.append("digraph grafico {\n");
        digraph.append("rankdir=LR;\n");

        //titulo
        if (tipoToken != null && !tipoToken.isEmpty()) {
            digraph.append("label=\"" + tipoToken + "\";\n");
        }

        char[] characters = lexema.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            String nodeName = "node" + i;
            digraph.append(nodeName + " [label=\"" + characters[i] + "\"];\n");

            // Conecta nodos 
            if (i < characters.length - 1) {
                String nextNodeName = "node" + (i + 1);
                digraph.append(nodeName + " -> " + nextNodeName + ";\n");
            }
        }

        //ultimo nodo circular
        if (characters.length > 0) {
            String lastNodeName = "node" + (characters.length - 1);
            digraph.append(lastNodeName + " [shape=doublecircle];\n");
        }

        digraph.append("}\n");

        return digraph.toString();
    }

}
