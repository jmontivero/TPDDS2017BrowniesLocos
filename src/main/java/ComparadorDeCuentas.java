import java.util.ArrayList;
import java.util.List;

public class ComparadorDeCuentas {
	private List<Empresa> empresas;
	private ConseguidorDeCuentas repoDatos;
	
	public ComparadorDeCuentas() {
		empresas = new ArrayList<Empresa>();
	}
	public ComparadorDeCuentas(ConseguidorDeCuentas repo) {
		empresas = new ArrayList<Empresa>();
		repoDatos = repo;
	}
	public Empresa dondeInvertir() {
		return new Empresa("ASD", new ArrayList<Cuenta>());
	}
	
	public void cargarCuentas(String path) {
		repoDatos.cargarCuentas(this, path);
	}
	
	public Cuenta getCuenta(String empresa, String cuenta, Integer periodo) {
		return empresas.stream().
				filter(unaEmpresa -> unaEmpresa.nombre().equals(empresa)).
				findFirst().get().
				getCuenta(cuenta, periodo);
	}
	
	public void agregarEmpresa(Empresa empresa) {
		empresas.add(empresa);
	}
	
	public void agregarCuenta(String empresa, Cuenta cuenta) {
		if(this.hayEmpresa(empresa))
			empresas.stream().
				filter(unaEmpresa -> unaEmpresa.seLlama(empresa)).
				forEach(unaEmpresa -> unaEmpresa.agregarCuenta(cuenta));
		else {
			empresas.add(new Empresa(empresa,new ArrayList<Cuenta>()));
			this.agregarCuenta(empresa,cuenta);
		}
	}
	
	private boolean hayEmpresa(String empresa) {
		return empresas.stream().
				anyMatch(unaEmpresa -> unaEmpresa.seLlama(empresa));
	}
}
