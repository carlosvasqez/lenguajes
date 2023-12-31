
package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vista.Gui;
import vista.SelectorDeArchivo;

/**
 *
 * @author usuario
 */
public class Cargador {

    private final Gui gui;

    public Cargador(Gui gui) {
	this.gui = gui;
    }

    public void cargarArchivoEn(SelectorDeArchivo selector) {
	Escritor escritor = new Escritor(gui.getTextPaneEditor());

	int eleccion = selector.showOpenDialog(null);

	if (eleccion == JFileChooser.APPROVE_OPTION) {

	    File file = selector.getSelectedFile();

	    try (FileInputStream fis = new FileInputStream(file);
		 InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		 BufferedReader br = new BufferedReader(isr)) {

		gui.getLimpiador().limpiarTodo();

		String linea;
		while ((linea = br.readLine()) != null) {
		    escritor.escribirLinea(linea);
		}
	    } catch (FileNotFoundException ex) {
		//Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
		JOptionPane.showMessageDialog(gui, "archivo no seleccionado correctamente, vuelva a intentar");
	    } catch (IOException ex) {
		//Logger.getLogger(Cargador.class.getName()).log(Level.SEVERE, null, ex);
		JOptionPane.showMessageDialog(gui, "error al cargar el archivo, vuelva a intentar");
	    }
	}

    }

}
