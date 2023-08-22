package modelo.tokens;

/**
 *
 * @author usuario
 */
public enum Aritmetico {
    SUMA("+"),
    RESTA("-"),
    MULTIPLICACION("*"),
    DIVISON("patro divison"),
    EXPONENTE("patron exponente"),
    MODULO("patron modulo");
    private String patron;

    private Aritmetico(String patron) {
	this.patron = patron;
    }

    public String getPatron() {
	return patron;
    }

}
