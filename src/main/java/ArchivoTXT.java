import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoTXT implements ConseguidorDeCuentas {

	@Override
	public void cargarCuentas(ComparadorDeCuentas comparador, String path) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			String linea;
			while((linea = reader.readLine()) != null) {
				comparador.agregarCuenta(this.getEmpresaString(linea),this.stringToCuenta(linea));
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Cuenta stringToCuenta(String cadena) {
		cadena = cadena.substring(cadena.indexOf('-')+1);
		String nombre, periodo, valor;
		nombre = cadena.substring(0,cadena.indexOf('-'));
		cadena = cadena.substring(cadena.indexOf('-')+1);
		periodo = cadena.substring(0,cadena.indexOf('-'));
		cadena = cadena.substring(cadena.indexOf('-')+1);
		valor = cadena;
		return new Cuenta(nombre,Integer.parseInt(periodo),Double.parseDouble(valor));
	}
	public String getEmpresaString(String cadena) {
		return cadena.substring(0,cadena.indexOf('-'));
	}

}
