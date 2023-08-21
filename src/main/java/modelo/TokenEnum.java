package modelo;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum TokenEnum {
    IDENTIFICADOR(Color.WHITE),
    ARITMETICO(Color.CYAN),
    COMPARACION(Color.CYAN),
    LOGICO(Color.CYAN),
    ASIGNACION(Color.CYAN),
    PALABRA_RESERVADA(new Color(128, 0, 128)),//morado
    CONSTANTE(Color.ORANGE),
    COMENTARIO(Color.GRAY),
    OTRO(Color.GREEN),
    ESPECIAL(Color.PINK),
    ERROR(new Color(255, 51, 255));

    private final Color color;

    private TokenEnum(Color color) {
	this.color = color;
    }

    public Color getColor() {
	return color;
    }

}
