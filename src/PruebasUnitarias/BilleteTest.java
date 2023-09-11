package PruebasUnitarias;

import static org.junit.Assert.*;
import CodigoFuente.Billete;
import org.junit.Test;

import CodigoFuente.Billete;
import Exceptions.ValorInvalido;

public class BilleteTest {

	@Test
	public void CrearUnBilleteDe10()throws ValorInvalido{
		Billete unBillete = new Billete(10);
	    assertEquals(10, unBillete.obtenerValorDelBillete());
	}
	@Test
	public void CrearUnBilleteDe500()throws ValorInvalido{
		Billete unBillete = new Billete(500);
	    assertEquals(500, unBillete.obtenerValorDelBillete());
	}
	@Test
	public void CrearUnBilleteDeCien()throws ValorInvalido{
		Billete unBillete = new Billete(100);
	    assertEquals(100, unBillete.obtenerValorDelBillete());
	}
	@Test
	public void CrearUnBilleteDeMil()throws ValorInvalido{
		Billete unBillete = new Billete(1000);
	    assertEquals(1000, unBillete.obtenerValorDelBillete());
	}
	@Test
	public void SoloSeCreanQuinientosBilletesCincuenta() throws ValorInvalido{
		Billete unBillete = new Billete(50);
		assertEquals(500, unBillete.obtenerCantidadDeBilletes());
	}
	@Test
	public void SoloSeCreanQuinientosBilletesDeMil() throws ValorInvalido{
		Billete unBillete = new Billete(1000);
		assertEquals(500, unBillete.obtenerCantidadDeBilletes());
	}
	@Test
	public void SoloSeCreanQuinientosBilletesDeCien() throws ValorInvalido{
		Billete unBillete = new Billete(100);
		assertEquals(500, unBillete.obtenerCantidadDeBilletes());
	}
	@Test
	public void ObtenerCantidadDeBilletes()throws ValorInvalido{
		Billete unBillete = new Billete(50);
		unBillete.utilizarUnBillete();
		assertEquals(499, unBillete.obtenerCantidadDeBilletes());
	}

}
