package PruebasUnitarias;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import Exceptions.ValorInvalido;
import Lectores.LectorDeCuenta;

public class LectorDeCuentaTest {

	@Test
	public void test() throws FileNotFoundException, ValorInvalido {
		LectorDeCuenta a = new LectorDeCuenta();
		a.lectorDatosDeCuentas();
		
	}

}
