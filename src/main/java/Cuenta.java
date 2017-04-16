
public class Cuenta {
	private String nombre;
	private Integer periodo;
	private double valor;
	
	public Cuenta(String _nombre, int anio, double _valor) {
		nombre = _nombre;
		periodo = anio;
		valor = _valor;
	}
	public String nombre() { return nombre; }
	public Integer periodo() { return periodo; }
	public Double valor() { return valor; }
}
