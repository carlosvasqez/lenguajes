package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author usuario
 */
public class Escritor {

    private boolean esPrimeraLinea = true;
    private final JTextPane textPane;

    public Escritor(JTextPane textPane) {
	this.textPane = textPane;
    }

    public void limpiar() {
	textPane.setText("");
    }

    public void escribir(String line) {
	StyledDocument doc = textPane.getStyledDocument();
	try {
	    if (esPrimeraLinea) {
		doc.insertString(0, line, null);
		esPrimeraLinea = false;
	    } else {
		doc.insertString(doc.getLength(), "\n" + line, null);
	    }
	} catch (BadLocationException ex) {
	    Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
	}

    }
}
