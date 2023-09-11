package PruebasUnitarias;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import CodigoFuente.Cliente;
import CodigoFuente.Cuenta;
import CodigoFuente.Movimiento;
import CodigoFuente.Tarjeta;
import CodigoFuente.UltimosMovimientos;
import Exceptions.ValorInvalido;

public class UltimosMovimientosTest {

	@Test
	public void crearUltimosMovimientos () throws ValorInvalido {
		Cliente unCliente = new Cliente("27102551236", "Spartan");
		Tarjeta unaTarjeta= new Tarjeta("27102551236","98765432","4567");
		Cuenta unaCuenta= new Cuenta("01","isla.pez.arbol",12000.03);
		unaTarjeta.asignarCuenta(unaCuenta);
		unCliente.asignarTarjeta(unaTarjeta);
		UltimosMovimientos a = new UltimosMovimientos();
		
	}

	@Test
	public void devolverUnaLista () throws  ValorInvalido, IOException {
		Cliente unCliente = new Cliente("27102551236", "Spartan");
		Tarjeta unaTarjeta= new Tarjeta("27102551236","98765432","4567");
		Cuenta unaCuenta= new Cuenta("01","isla.pez.arbol",12000.03);
		unaTarjeta.asignarCuenta(unaCuenta);
		unCliente.asignarTarjeta(unaTarjeta);
		Movimiento unaOperacion= new Movimiento(unCliente,"comprar 23 dolares");
		Movimiento unaOperacion2= new Movimiento(unCliente,"deposite");
		UltimosMovimientos a = new UltimosMovimientos();
		a.asigarCliente(unCliente);
		a.guardarMovimientoEnLista(unaOperacion);
		a.guardarMovimientoEnLista(unaOperacion2);

		System.out.println(a.obtenerUnaLista(unCliente.obtenerCuit()));
		System.out.println("------------------------");
	}
	@Test
	public void devolverUnRegistros () throws  ValorInvalido, IOException {
		Cliente unCliente = new Cliente("27102551236", "Spartan");
		Tarjeta unaTarjeta= new Tarjeta("27102551236","98765432","4567");
		Cuenta unaCuenta= new Cuenta("01","isla.pez.arbol",12000.03);
		unaTarjeta.asignarCuenta(unaCuenta);
		unCliente.asignarTarjeta(unaTarjeta);
		
		Cliente otroCliente = new Cliente("11111111111", "GOWO");
		Tarjeta otraTarjeta= new Tarjeta("11111111111","98765432","4567");
		Cuenta  otraCuenta= new Cuenta("01","asd",12000.03);
		otraTarjeta.asignarCuenta(otraCuenta);
		otroCliente.asignarTarjeta(otraTarjeta);
		
		UltimosMovimientos a = new UltimosMovimientos();
		a.asigarCliente(unCliente);
		Movimiento unaOperacion= new Movimiento(unCliente,"comprar 23 dolares");
		a.guardarMovimientoEnLista(unaOperacion);
		Movimiento unaOperacion2= new Movimiento(otroCliente,"deposite");
		a.asigarCliente(otroCliente);
		a.guardarMovimientoEnLista(unaOperacion2);
		a.escribirTxt(unCliente.obtenerCuit());
		System.out.println(a.obtenerRegistro());
	}
	@Test
	public void devolverUnRegistroCon1Cliente () throws  ValorInvalido, IOException {
		Cliente unCliente = new Cliente("27102551236", "Spartan");
		Tarjeta unaTarjeta= new Tarjeta("27102551236","98765432","4567");
		Cuenta unaCuenta= new Cuenta("01","isla.pez.arbol",12000.03);
		unaTarjeta.asignarCuenta(unaCuenta);
		unCliente.asignarTarjeta(unaTarjeta);
		
		
		
		UltimosMovimientos a = new UltimosMovimientos();
		a.asigarCliente(unCliente);
		Movimiento unaOperacion= new Movimiento(unCliente,"comprar 23 dolares");
		a.guardarMovimientoEnLista(unaOperacion);
		Movimiento unaOperacion2= new Movimiento(unCliente,"deposite");
		a.asigarCliente(unCliente);
		a.guardarMovimientoEnLista(unaOperacion2);
		a.escribirTxt(unCliente.obtenerCuit());
		System.out.println(a.obtenerRegistro());
	}
	

}
