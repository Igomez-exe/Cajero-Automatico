package PruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CodigoFuente.Tarjeta;
import Exceptions.ValorInvalido;

public class TarjetaTest {
	@Test
	public void sePuedeCrearUnaTarjeta()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "12345678", "1234");
	}

	// test con cuit

	@Test(expected = ValorInvalido.class)
	public void noSePuedeCrearUnaTarjetaConCuitMayorA11Digitos()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("123456789123", "12345678", "1234");
	}

	@Test(expected = ValorInvalido.class)
	public void noSePuedeCrearUnaTarjetaConCuitMenorA11Digitos()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("1234567891", "12345678", "1234");
	}

	// test con numerosDeTarjeta

	@Test(expected = ValorInvalido.class)
	public void noSePuedeCrearUnaTarjetaConUnNumeroDeTarjetaMayorA8Digitos()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "12345678910", "1234");
	}

	@Test(expected = ValorInvalido.class)
	public void noSePuedeCrearUnaTarjetaConUnNumeroDeTarjetaMenorA8Digitos()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "123", "1234");
	}

	// Test con pin

	@Test(expected = ValorInvalido.class)
	public void noSePuedeCrearUnaTarjetaConPinMayorA4Digitos()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "12345678", "12345678");
	}

	@Test(expected = ValorInvalido.class)
	public void noSePuedeCrearUnaTarjetaConPinMenorA4Digitos()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "12345678", "1");
	}

	// OBTENER CUIT

	@Test
	public void sePuedeObtenerElCuitDeLaTarjeta()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "12345678", "1234");
		assertEquals("12345678911", unaTarjeta.obtenerCuit());
	}

	// OBTENER numeroDeTarjeta

	@Test
	public void sePuedeObtenerElNumeroDeTarjetaDeLaTarjeta()
			throws ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "12345678", "1234");
		assertEquals("12345678", unaTarjeta.obtenerNumeroDeTarjeta());
	}

	// OBTENER numeroDepin

	@Test
	public void sePuedeObtenerElPinDeLaTarjeta() throws ValorInvalido{
		Tarjeta unaTarjeta = new Tarjeta("12345678911", "12345678", "1234");
		assertEquals("1234", unaTarjeta.obtenerPinDeTarjeta());
	}

}
