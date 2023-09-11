package PruebasUnitarias;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import CodigoFuente.Cliente;
import CodigoFuente.Cuenta;
import CodigoFuente.Movimiento;
import CodigoFuente.Tarjeta;
import Exceptions.ValorInvalido;

public class MovimientossTest {

	@Test
	public void crearClaseMovimientoss() throws IOException, ValorInvalido {
		Cliente unCliente = new Cliente("12345678901", "alisDs");
		Movimiento nuevoMovimento = new Movimiento(unCliente,"deposito");
	}
	@Test
	public void devuelveElDiaDeUnMovimiento() throws IOException, ValorInvalido {
		Cliente unCliente = new Cliente("12345678901", "alisDs");
		Movimiento nuevoMovimento = new Movimiento(unCliente,"deposito");
		String dia =nuevoMovimento.obtenerDia();
		System.out.println(dia);
		assertEquals(dia,nuevoMovimento.obtenerDia());
	}
	@Test
	public void devuelveElMesDeUnMovimiento() throws IOException, ValorInvalido {
		Cliente unCliente = new Cliente("12345678901", "alisDs");
		Movimiento nuevoMovimento = new Movimiento(unCliente,"deposito");
		String mes =nuevoMovimento.obtenerMes();
		System.out.println(mes);
		assertEquals(mes,nuevoMovimento.obtenerMes());
	}
	@Test
	public void devuelveElAnioDeUnMovimiento() throws IOException, ValorInvalido{
		Cliente unCliente = new Cliente("12345678901", "alisDs");
		Movimiento nuevoMovimento = new Movimiento(unCliente,"deposito");
		String anio =nuevoMovimento.obtenerAnio();
		System.out.println(anio);
		assertEquals(anio,nuevoMovimento.obtenerAnio());
	}
	@Test
	public void devuelveElMovimiento() throws IOException,ValorInvalido {
		Cliente unCliente = new Cliente("12345678901", "alisDs");
		Movimiento nuevoMovimento = new Movimiento(unCliente,"deposito");
		String operacion =nuevoMovimento.obtenerOperacion();
		System.out.println(operacion);
		assertEquals("deposito",operacion);
	}
	@Test
	public void devuelveElSaldoDeUnMovimiento() throws IOException, ValorInvalido {
		Cliente unCliente = new Cliente("12345678901", "alisDs");
		Tarjeta unaTarjeta= new Tarjeta("27102551236","12345678","1234");
		Cuenta unaCuenta= new Cuenta("01","isla.pez.arbol",12000.03);
		unaTarjeta.asignarCuenta(unaCuenta);
		unCliente.asignarTarjeta(unaTarjeta);
		
		Movimiento nuevoMovimento = new Movimiento(unCliente,"deposito");
		double saldo= unCliente.obtenerTarjeta().obtenerCuenta().obtenerSaldo();
		assertEquals(12000.03,saldo,0.01);
	}
	@Test
	public void devuelveElAliasDeUnMovimiento() throws IOException, ValorInvalido {
		Cliente unCliente = new Cliente("12345678901", "alisDs");
		Tarjeta unaTarjeta= new Tarjeta("27102551236","12345678","1234");
		Cuenta unaCuenta= new Cuenta("01","isla.pez.arbol",12000.03);
		unaTarjeta.asignarCuenta(unaCuenta);
		unCliente.asignarTarjeta(unaTarjeta);
		
		Movimiento nuevoMovimento = new Movimiento(unCliente,"deposito");
		String alias= unCliente.obtenerAlias();
		assertEquals("alisDs",alias);
	}
}
