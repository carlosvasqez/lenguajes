package modelo;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum TokenEnum {
    ESPECIAL(Color.PINK),
    IDENTIFICADOR(Color.WHITE),
    ARITMETICO(Color.CYAN),
    COMPARACION(Color.CYAN),
    LOGICO(Color.CYAN),
    ASIGNACION(Color.CYAN),
    PALABRA_RESERVADA(new Color(102, 0, 153)),//morado
    CONSTANTE(Color.ORANGE),
    COMENTARIO(Color.GRAY),
    OTRO(Color.GREEN);

    private final Color color;

    private TokenEnum(Color color) {
	this.color = color;
    }

    public Color getColor() {
	return color;
    }

}
