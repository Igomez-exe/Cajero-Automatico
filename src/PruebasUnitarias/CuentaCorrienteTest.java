package PruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import CodigoFuente.Cuenta;
import CodigoFuente.CuentaCorriente;
import Exceptions.ValorInvalido;
public class CuentaCorrienteTest {
	// REVISAR TEST CON SLASH 

		@Test
		public void probarQueSePuedeCrearUnaCuenta()throws  ValorInvalido {
			Cuenta nuevaCuenta = new CuentaCorriente("01", "hermosaLuna",5000.00);
		}
		
		// Test DE  TIPO

		@Test (expected=ValorInvalido.class)
		public void probarQueNoSePuedeCrearUnaCuentaConTipoMenorA2Digitos()throws  ValorInvalido {
			Cuenta nuevaCuenta = new CuentaCorriente("1", "jjj",200.00,-400);
		}
		@Test  (expected=ValorInvalido.class)
		public void probarQueNoSePuedeCrearUnaCuentaConTipoMayorA2Digitos()throws  ValorInvalido {
			Cuenta nuevaCuenta = new CuentaCorriente("60000", "lit",240.00,-6000);
		}
		
		//@Test  
		public void probarQueNoSePuedeCrearUnaCuentaConTipoMayorA03() throws  ValorInvalido{
			Cuenta nuevaCuenta = new CuentaCorriente("64","AbCdE",200.00,-800);
		}
		//@Test  
			public void probarQueNoSePuedeCrearUnaCuentaConTipoMenorA012Digitos() throws  ValorInvalido{
				Cuenta nuevaCuenta = new CuentaCorriente("00","FelizCumplePocho",200.00,-700);
			}
		
		
		
		// Test DE ALIAS
		
		@Test  
		public void probarQueSePuedeCrearUnaCuentaConUnAliasCon1digito() throws  ValorInvalido{
			Cuenta nuevaCuenta = new CuentaCorriente("02","0",200.00);
		}
		@Test  
		public void probarQueSePuedeCrearUnaCuentaConUnAliasConDigitosRepetidos() throws  ValorInvalido{
			Cuenta nuevaCuenta = new CuentaCorriente("02","         ",200.00);
		}
		
		@Test  (expected= ValorInvalido.class)
		public void probarQueNoSePuedeCrearUnaCuentaConAliasMayorA20Digitos() throws  ValorInvalido{
			Cuenta nuevaCajaDeAhorrro = new CuentaCorriente("02","papaJuegoAlDoomConMama",200.00,-55);
		}
		
		// Test De Saldo
		
		
		@Test  
		public void probarQueSePuedeCrearUnaCuentaConSaldoIgualA0() throws  ValorInvalido{
			Cuenta nuevaCajaDeAhorrro = new CuentaCorriente("02","papaJuegoAlDiablo2",0.00,55);
		}
		
		@Test  
		public void probarQueSePuedeCrearUnaCuentaConSaldoMuyGrande() throws  ValorInvalido{
			Cuenta nuevaCajaDeAhorrro = new CuentaCorriente("02","papaJuegoAlDiablo2",99999999999999999999999999999999999.00);
		}

		
		
		
		
		// Test De Descubierto
		
		
		
		@Test
		public void probarQueSeCreaUnaCuentaCorrienteConUnDescubiertoGigantesco() throws  ValorInvalido{
			Cuenta nuevaCuenta= new CuentaCorriente ("01","a",-1500.00,999999999);
		}
		@Test (expected=ValorInvalido.class)
		public void probarQueNoSeCreaUnaCuentaCorrienteConUnDescubiertomayorA0() throws  ValorInvalido{
			Cuenta nuevaCuenta= new CuentaCorriente ("01","a",1500.00,1);
		}
		
		
		
		// Test de Obtener Informacion
		
		
		@Test
		public void probarQueDevuelveElAliasIndicado() throws ValorInvalido{
			Cuenta nuevaCuenta= new CuentaCorriente ("01","LA-LE-LI-LO-LU",1500.00);
			assertEquals("LA-LE-LI-LO-LU",nuevaCuenta.obtenerAlias());
		}
		@Test
		public void probarQUeDevuelveElTipo() throws  ValorInvalido{
			Cuenta nuevaCuenta = new CuentaCorriente("03","LosAngeles",-4000,55);
			
			assertEquals("03",nuevaCuenta.obtenerTipo());
		}
		@Test
		public void probarQUeDevuelveElSaldo() throws  ValorInvalido{
			Cuenta nuevaCuenta = new CuentaCorriente("01","Eskere",100);
			
			assertEquals(100,nuevaCuenta.obtenerSaldo(),0.1);
		}
		
		@Test
		public void probarQueSeObtieneElDescubiertoIndicado() throws  ValorInvalido{
			Cuenta nuevaCuenta= new CuentaCorriente ("01","a",-1500.00,999999999);
			assertEquals(999999999,nuevaCuenta.obtenerDescubierto(),0.1);
		}
		

}
