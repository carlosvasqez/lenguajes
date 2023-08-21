package controlador;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;

/**
 *
 * @author usuario
 */
public class CursorListener implements CaretListener {

    private JTextPane textPane;
    private JLabel labelFila;
    private JLabel labelColumna;

    public CursorListener(JTextPane textPane, JLabel labelFila, JLabel labelColumna) {
	this.textPane = textPane;
	this.labelFila = labelFila;
	this.labelColumna = labelColumna;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
	int dot = e.getDot(); // Posición actual del cursor
	int fila, columna;
	StyledDocument doc = textPane.getStyledDocument();
	Element root = doc.getDefaultRootElement();

	fila = root.getElementIndex(dot) + 1; // Filas comienzan en 0
	Element lineElem = root.getElement(fila - 1);
	columna = dot - lineElem.getStartOffset() + 1; // Columnas comienzan en 0
	labelFila.setText("Fila: " + fila);
	labelColumna.setText("Columna: " + columna);
    }

}
