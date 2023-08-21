package controlador;

import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import modelo.Token;

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

    public void colorear(List<Token> listaTokens) {
	StyledDocument doc = textPane.getStyledDocument();
	String text = textPane.getText();
	Style estilo = textPane.addStyle("", null);
	
	
	
	for (Token listaToken : listaTokens) {

	    StyleConstants.setForeground(estilo, listaToken.getTokenEnum().getColor());
	    //StyleConstants.setBackground(estilo, Color.white);

	    try {
		doc.insertString(doc.getLength(), listaToken.getLexena(), null);
	    } catch (BadLocationException ex) {
		Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    //doc.insertString(doc.getLength(), "Game of Thrones ", style);
	    StyleConstants.setForeground(estilo, Color.yellow);
	    StyleConstants.setBackground(estilo, Color.gray);
	    //doc.insertString(doc.getLength(), "Season 8", style);
	}
    }
}
