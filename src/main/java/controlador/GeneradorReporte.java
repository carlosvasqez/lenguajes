package controlador;

import javax.swing.JTextPane;
import vista.ReporteGUI;

/**
 *
 * @author usuario
 */
public class GeneradorReporte implements Ejecutable {

    private final JTextPane textPaneEditor;
    private final JTextPane textPaneOutput;

    public GeneradorReporte(JTextPane textPaneEditor, JTextPane textPaneOutput) {
	this.textPaneEditor = textPaneEditor;
	this.textPaneOutput = textPaneOutput;
    }

    @Override
    public void realizar() {
	ReporteGUI reporteGUI = new ReporteGUI(null, true);
	reporteGUI.setVisible(true);
	
    }

}
