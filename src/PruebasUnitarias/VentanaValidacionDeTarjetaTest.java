package PruebasUnitarias;

import static org.junit.Assert.*;
import interfazGrafica.VentanaInicio;
import interfazGrafica.VentanaValidacionDeTarjeta;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import org.junit.Test;

import CodigoFuente.Cliente;
import CodigoFuente.Tarjeta;
import Exceptions.ValorInvalido;

public class VentanaValidacionDeTarjetaTest {
	
	
	
	
	@Test(expected = ValorInvalido.class)
	public void agregarTarjetaIncorrecta() throws FileNotFoundException, ValorInvalido {
		Tarjeta unaTarjeta = new Tarjeta("45367890123","111111111","1234");
		Cliente unCliente = new Cliente("12345678901", "hola.chau.luego");
		unCliente.asignarTarjeta(unaTarjeta);
	
		
	}
	
	@Test
	public void agregarTarjetaCorrecta() throws FileNotFoundException, ValorInvalido{
		 Tarjeta unaTarjeta = new Tarjeta("12345678901","12345678" ,"1234");
		 
		 Cliente unCliente = new Cliente("12345678901", "hola.chau.luego");
		
		 unCliente.asignarTarjeta(unaTarjeta);
	}
	
	@Test(expected = ValorInvalido.class)
	public void agregarTarjetaCorrectaPeroPinIncorrecto() throws FileNotFoundException, ValorInvalido{
		 Tarjeta unaTarjeta  = new Tarjeta("12345678901","12345678","123123123");
		 Cliente unCliente = new Cliente("12345678901", "hola.chau.luego");
		 unCliente.asignarTarjeta(unaTarjeta);



	}
	
	@Test(expected = ValorInvalido.class)
	public void agregarTarjetaConCuitInvalido() throws ValorInvalido{
		Tarjeta unaTarjeta = new Tarjeta("1","12345678","1234");
		Cliente unCliente = new Cliente("12345678901", "hola.chau.luego");
		 unCliente.asignarTarjeta(unaTarjeta);

	}

}
