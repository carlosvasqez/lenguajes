package controlador;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import modelo.Token;
import modelo.TokenEnum;

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

    public void escribirLinea(String linea) {
	if (esPrimeraLinea) {
	    escribirAlInicio(linea);
	    esPrimeraLinea = false;
	} else {
	    escribirAlFinal(linea);
	}

    }

    public void escribirAlInicio(String linea) {
	StyledDocument doc = textPane.getStyledDocument();
	try {
	    doc.insertString(0, linea, null);
	} catch (BadLocationException ex) {
	    Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void escribirAlFinal(String linea) {
	StyledDocument doc = textPane.getStyledDocument();
	try {
	    doc.insertString(doc.getLength(), "\n" + linea, null);
	} catch (BadLocationException ex) {
	    Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
	}

    }

    public void colorear(List<Token> listaTokens) {
	StyledDocument doc = textPane.getStyledDocument();
	for (Token listaToken : listaTokens) {
	    String lexema = listaToken.getLexena();
	    
	    if (!lexema.equalsIgnoreCase(" ")
		    || !lexema.equalsIgnoreCase("\t")
		    || !lexema.equalsIgnoreCase("\n")) {

		//si no es espacio, tab, o salto de linea, entonces aplicar color
		SimpleAttributeSet estilo = new SimpleAttributeSet();

		//StyleConstants.setFontSize(estilo, 16);
		StyleConstants.setBold(estilo, true);
		if (listaToken.getTokenEnum() == TokenEnum.ERROR) {
		    StyleConstants.setUnderline(estilo, true);
		}
		StyleConstants.setForeground(estilo, listaToken.getTokenEnum().getColor());

		try {
		    doc.insertString(doc.getLength(), lexema, estilo);
		} catch (BadLocationException ex) {
		    Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
		}

	    } else {

		try {
		    //es espacio o tab o cadena entonces no se le agrega color
		    doc.insertString(doc.getLength(), lexema, null);
		} catch (BadLocationException ex) {
		    Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
		}

	    }
	}

    }

}
