package PruebasUnitarias;

import java.io.IOException;

import org.junit.Test;

import CodigoFuente.Cliente;
import CodigoFuente.Cuenta;
import CodigoFuente.Tarjeta;
import Exceptions.ValorInvalido;

public class VentanaInicioTest {
	//Cliente clienteActual;

	//@Before
	private void crearClienteUno() throws  ValorInvalido{
		Cuenta a =new Cuenta("01", "isla.pez.arbol",12000);
		Tarjeta ad= new Tarjeta("27102551236", "12345678","1234");
		Cliente unCliente = new Cliente("27102551236", "isla.pez.arbol");
		ad.asignarCuenta(a);
		unCliente.asignarTarjeta(ad);
		Cliente clienteActual = unCliente;

	}
	private void crearClienteDos() throws  ValorInvalido{
		Cuenta a =new Cuenta("05", "isla.pez.arbol",12000);
		Tarjeta ad= new Tarjeta("27102551236", "12345678","1234");
		Cliente unCliente = new Cliente("27102551236", "isla.pez.arbol");
		ad.asignarCuenta(a);
		unCliente.asignarTarjeta(ad);
		Cliente clienteActual = unCliente;
	}
	@Test
	public void probarCrearClienteCorrecto() throws IOException, ValorInvalido {
		//Movimientoss a = new Movimientoss(clienteActual);
		crearClienteUno();
		//clienteActual.comprarDolaresConCajaDeAhorroEnPesos(10);
		
	}
	
	@Test (expected = ValorInvalido.class)
	public void probarCrearClienteIncorrecto() throws  ValorInvalido{
		crearClienteDos();
	}

}
