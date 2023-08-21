package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author usuario
 */
public class TabuladorListener implements KeyListener {

    private final JTextPane textPane;
    private final int espacios;

    public TabuladorListener(JTextPane textPane, int espacios) {
	this.textPane = textPane;
	this.espacios = espacios;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_TAB) {
	    e.consume(); // Evita que el JTextPane maneje la tabulación por defecto
	    Document document = textPane.getDocument();
	    try {
		document.insertString(textPane.getCaretPosition(), " ".repeat(espacios), null);
	    } catch (BadLocationException ex) {
		Logger.getLogger(TabuladorListener.class.getName()).log(Level.SEVERE, null, ex);
	    }

	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void insertarTabulacion() {

    }

}
