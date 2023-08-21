package vista;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author usuario
 */
public class SelectorDeArchivo extends JFileChooser {

    public SelectorDeArchivo() {
	FileNameExtensionFilter filtro = new FileNameExtensionFilter("archivo .txt o .py", "txt", "py");
	File directorioActual = new File(".");
	super.setCurrentDirectory(directorioActual);
	super.setAcceptAllFileFilterUsed(false);
	super.setDialogTitle("Seleccionar archivo");
	super.setFileFilter(filtro);
	super.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }
}
