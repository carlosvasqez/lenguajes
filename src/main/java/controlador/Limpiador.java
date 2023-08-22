package controlador;

import javax.swing.text.MutableAttributeSet;
import vista.Gui;

/**
 *
 * @author usuario
 */
public class Limpiador {
    
    private final Gui gui;
    
    public Limpiador(Gui gui) {
	this.gui = gui;
    }
    
    public void limpiarTodo() {
	//limpiar editor
	gui.getTextPaneEditor().setText("");
	MutableAttributeSet mas = gui.getTextPaneEditor().getInputAttributes();
	mas.removeAttributes(mas);
	//limpiar output
	gui.getTextPaneOutput().setText("");
	//limpiar reporte
	gui.getReporteGUI().getModeloTabla().limpiarTabla();
	gui.repaint();
	gui.validate();
    }
    
}
