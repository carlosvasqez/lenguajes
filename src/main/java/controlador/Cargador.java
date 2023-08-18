package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import vista.SelectorDeArchivo;

/**
 *
 * @author usuario
 */
public class Cargador {
    
    private final SelectorDeArchivo selector = new SelectorDeArchivo();
    private final JTextPane textPaneEditor;
    
    public Cargador(JTextPane textPaneEditor) {
	this.textPaneEditor = textPaneEditor;
    }
    
    public void cargarArchivo() {
	int eleccion = selector.showOpenDialog(null);
	if (eleccion == JFileChooser.APPROVE_OPTION) {
	    File file = selector.getSelectedFile();
	    try (FileInputStream fis = new FileInputStream(file);
		 InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		 BufferedReader br = new BufferedReader(isr)) {
		
		limpiar();
		
		String line;
		while ((line = br.readLine()) != null) {
		    escribir(line);
		}
	    } catch (FileNotFoundException ex) {
		Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
    }
    
    private boolean esPrimeraLinea = true;
    
    private void limpiar() {
	textPaneEditor.setText("");
    }
    
    private void escribir(String line) {
	StyledDocument doc = textPaneEditor.getStyledDocument();
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
