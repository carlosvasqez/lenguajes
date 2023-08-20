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
import vista.SelectorDeArchivo;

/**
 *
 * @author usuario
 */
public class Cargador {

    public void cargarArchivoEn(JTextPane textPane, SelectorDeArchivo selector) {
	Escritor escritor = new Escritor(textPane);
	int eleccion = selector.showOpenDialog(null);
	if (eleccion == JFileChooser.APPROVE_OPTION) {
	    File file = selector.getSelectedFile();
	    try (FileInputStream fis = new FileInputStream(file);
		 InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		 BufferedReader br = new BufferedReader(isr)) {

		escritor.limpiar();

		String linea;
		while ((linea = br.readLine()) != null) {
		    escritor.escribir(linea);
		}
	    } catch (FileNotFoundException ex) {
		Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

    }
    

}
