package controlador;

import java.util.List;
import modelo.Token;
import modelo.TokenEnum;
import vista.Gui;

/**
 *
 * @author usuario
 */
public class GeneradorReporte {

    private final Gui gui;

    public GeneradorReporte(Gui gui) {
	this.gui = gui;
    }

    public void generarReporte() {
	//agregar nuevas filas
	int contadorLogs = 0;
	List<Token> listaTokens = gui.getAnalizador().getListaTokens();
	for (Token listaToken : listaTokens) {
	    if (!listaToken.getLexena().isBlank()) {
		if (listaToken.getTokenEnum() != TokenEnum.ERROR) {

		    contadorLogs++;

		    //crear fila
		    String token = listaToken.getTokenEnum().toString();
		    String patron = listaToken.getPatron();
		    String lexema = listaToken.getLexena();
		    String fila = String.valueOf(listaToken.getFila());
		    String columna = String.valueOf(listaToken.getColumna());
		    String boton = "Mostrar Grafico";
		    String[] filaTabla = {token, patron, lexema, fila, columna, boton};

		    //agregar fila a la tabla
		    gui.getReporteGUI().getModeloTabla().agregarFila(filaTabla);
		}

	    }
	}
    }

}
