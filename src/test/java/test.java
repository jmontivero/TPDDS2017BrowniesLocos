import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class test {
	public Empresa empresaFB;
	public Empresa empresaGG;
	@Before
	public void setUp() {
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(new Cuenta("EBITDA",2017,(double) 800));
		cuentas.add(new Cuenta("FDS",2017,(double) 566));
		cuentas.add(new Cuenta("Free Cash Flow",2017,(double) 896));
		cuentas.add(new Cuenta("FDS",2016,(double) 75));
		empresaFB = new Empresa("Facebook",cuentas);
		cuentas = new ArrayList<Cuenta>();
		cuentas.add(new Cuenta("EBITDA",2017,(double) 9875));
		cuentas.add(new Cuenta("FDS",2017,(double) 354));
		cuentas.add(new Cuenta("Free Cash Flow",2017,(double) 456));
		cuentas.add(new Cuenta("Ingreso Neto en Operaciones Discontinuas",2017,(double) 6541));
		cuentas.add(new Cuenta("Ingreso Neto en Operaciones Continuas",2017,(double) 7541));
		empresaGG = new Empresa("Google",cuentas);
	}
	
	@Test
	public void testConstructorCuenta() {
		Cuenta cuenta = new Cuenta("FDS",2017, 356.0);
		assertEquals(356.0, cuenta.valor(), 1);
		assertEquals("FDS", cuenta.nombre());
		assertEquals(2017,(int) cuenta.periodo());
	}
	
	@Test
	public void testFacebookFDSEn2017es566() {
		assertEquals(566.0, empresaFB.getCuenta("FDS", 2017).valor() , 1);
	}
	
	@Test
	public void testComparadorDeCuentasBusquedaCuentaOK() {
		ComparadorDeCuentas comparador = new ComparadorDeCuentas();
		comparador.agregarEmpresa(empresaFB);
		assertEquals(75, comparador.getCuenta("Facebook", "FDS", 2016).valor(), 1);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testComparadorDeCuentasBusquedaCuentaInexistente() {
		ComparadorDeCuentas comparador = new ComparadorDeCuentas();
		comparador.agregarEmpresa(empresaFB);
		comparador.getCuenta("Facebook", "CuentaInexistente", 2016);
	}
	
	@Test
	public void testComparadorDeCuentasAgregarCuentaAEmpresaExistente() {
		ComparadorDeCuentas comparador = new ComparadorDeCuentas();
		comparador.agregarEmpresa(empresaFB);
		comparador.agregarCuenta("Facebook", new Cuenta("EBITDA",2016,800));
		assertEquals(800, comparador.getCuenta("Facebook", "EBITDA", 2016).valor(), 1);
	}
	
	@Test
	public void testComparadorDeCuentasAgregarCuentaAEmpresaInexistente() {
		ComparadorDeCuentas comparador = new ComparadorDeCuentas();
		comparador.agregarCuenta("Facebook", new Cuenta("EBITDA",2016,800));
		assertEquals(800, comparador.getCuenta("Facebook", "EBITDA", 2016).valor(), 1);
	}
	
	@Test
	public void testArchivoTXTStringToCuenta() {
		assertEquals(new Cuenta("FAS",2017,600).valor(),new ArchivoTXT().stringToCuenta("asd-FAS-2017-600").valor());
		assertEquals(new Cuenta("FAS",2017,600).nombre(),new ArchivoTXT().stringToCuenta("asd-FAS-2017-600").nombre());
		assertEquals(new Cuenta("FAS",2017,600).periodo(),new ArchivoTXT().stringToCuenta("asd-FAS-2017-600").periodo());
		assertEquals("asd",new ArchivoTXT().getEmpresaString("asd-FAS-2017-600"));

	}
	
	@Test
	public void testComparadorConArchivoTXT() {
		ComparadorDeCuentas comparador = new ComparadorDeCuentas(new ArchivoTXT());
		comparador.cargarCuentas("/home/javier/workspace/TPDDS2017BrowniesLocos/data/cuentas.dat");
		assertEquals(566, comparador.getCuenta("Facebook", "FDS", 2017).valor(),1);
		assertEquals(653, comparador.getCuenta("Microsoft","FDS",2017).valor(),1);
	}
}
