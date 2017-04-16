import java.util.List;

public class Empresa {
	private String nombre;
	private List<Cuenta> cuentas;
	
	public Empresa(String _nombre, List<Cuenta> _cuentas) {
		nombre = _nombre;
		cuentas = _cuentas;
	}
	public Cuenta getCuenta(String nombre, Integer periodo) {
		return cuentas.stream().
				filter(
						unaCuenta -> 
						unaCuenta.nombre().equals(nombre) && 
						unaCuenta.periodo().equals(periodo)).
				findFirst().get();
	}
	
	public Boolean esMejorQue(Empresa otraEmpresa) {
		return true;
	}
	
	public String nombre() {
		return nombre;
	}
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
	public boolean seLlama(String unNombre) { return nombre.equals(unNombre); }
}
