package controlador;

import java.util.List;
import modelo.Token;
import modelo.TokenError;
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
		if (!(listaToken instanceof TokenError)) {

		    contadorLogs++;
		    gui.getReporteGUI().getModeloTabla().agregarFila(listaToken);

		}

	    }
	}
    }

}
