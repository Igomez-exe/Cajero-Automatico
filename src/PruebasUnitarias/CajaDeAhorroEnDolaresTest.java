package PruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import CodigoFuente.CajaDeAhorroEnDolares;
import CodigoFuente.Cuenta;
import Exceptions.ValorInvalido;
public class CajaDeAhorroEnDolaresTest {

	// REVISAR TEST CON SLASH 

	@Test
	public void probarQueSePuedeCrearUnaCuenta()throws  ValorInvalido {
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("01", "asd",200.00);
	}
	
	// Test DE  TIPO

	@Test (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConTipoMenorA2Digitos()throws  ValorInvalido {
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("1", "laguna.arbol",200.00);
	}
	@Test  (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConTipoMayorA2Digitos()throws  ValorInvalido {
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("60000", "PokemonGO",200.00);
	}
	
	@Test  (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConTipoMayorA03() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("99","AbCdE",200.00);
	}
	@Test  (expected=ValorInvalido.class)
		public void probarQueNoSePuedeCrearUnaCuentaConTipoMenorA012Digitos() throws  ValorInvalido{
			Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("00","FelizCumplePocho",200.00);
		}
	
	
	
	// Test DE ALIAS
	
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConUnAliasCon1digito() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("02","0",200.00);
	}
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConUnAliasConDigitosRepetidos() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorrro = new CajaDeAhorroEnDolares("02","         ",200.00);
	}
	
	@Test  (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConAliasMayorA20Digitos() throws ValorInvalido{
		Cuenta nuevaCajaDeAhorrro = new CajaDeAhorroEnDolares("02","papaJuegoAlDoomConMama",200.00);
	}
	
	// Test De Saldo
	
	
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConSaldoIgualA0() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorrro = new CajaDeAhorroEnDolares("02","papaJuegoAlDiablo2",0.00);
	}
	
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConSaldoMuyGrande() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorrro = new CajaDeAhorroEnDolares("02","papaJuegoAlDiablo2",99999999999999999999999999999999999.00);
	}

	@Test   (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConSaldoMenorA0() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("02","papaJuegoAlDiablo2",-5.00);
	}
	
	// Test de Obtener Informacion
	
	
	
	@Test
	public void probarQueDevuelveElAliasIndicado() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorroDeDolares= new CajaDeAhorroEnDolares ("01","karpinchio",1500.00);
		assertEquals("karpinchio",nuevaCajaDeAhorroDeDolares.obtenerAlias());
	}
	@Test
	public void probarQUeDevuelveElTipo() throws ValorInvalido{
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("01","sd",4000);
		
		assertEquals("01",nuevaCajaDeAhorroDeDolares.obtenerTipo());
	}
	@Test
	public void probarQUeDevuelveElSaldo() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorroDeDolares = new CajaDeAhorroEnDolares("01","EkisDe",100);
		
		assertEquals(100,nuevaCajaDeAhorroDeDolares.obtenerSaldo(),0.1);
	}
	

}
