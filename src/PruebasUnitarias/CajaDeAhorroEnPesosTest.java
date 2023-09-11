package PruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import CodigoFuente.CajaDeAhorroEnPesos;
import CodigoFuente.Cuenta;
import Exceptions.ValorInvalido;

public class CajaDeAhorroEnPesosTest {
	
	
	// REVISAR TEST CON SLASH 

	@Test
	public void probarQueSePuedeCrearUnaCuenta()throws ValorInvalido {
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("01", "papaJugoAlDoomConher",200.00);
	}
	
	// Test DE  TIPO

	@Test (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConTipoMenorA2Digitos()throws  ValorInvalido {
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("1", "papaJugoAlDoom",200.00);
	}
	@Test  (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConTipoMayorA2Digitos()throws  ValorInvalido {
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("100", "papaJugoAlDoom",200.00);
	}
	
	//@Test  
	public void probarQueNoSePuedeCrearUnaCuentaConTipoMayorA032Digitos() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("99","papaJuegoAlDiablo2",200.00);
	}
	//@Test  
		public void probarQueNoSePuedeCrearUnaCuentaConTipoMenorA012Digitos() throws ValorInvalido{
			Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("00","papaJuegoAlDiablo2",200.00);
		}
	
	
	
	// Test DE ALIAS
	
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConUnAliasCon1digito() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("02","p",200.00);
	}
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConUnAliasConDigitosRepetidos() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("02","pppppppppppp",200.00);
	
	}
	@Test  (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConAliasMayorA20Digitos() throws ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("02","papaJuegoAlDoomConMama",200.00);
	}
	
	// Test De Saldo
	
	
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConSaldoIgualA0() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("02","papaJuegoAlDiablo2",0.00);
	}
	
	@Test  
	public void probarQueSePuedeCrearUnaCuentaConSaldoMuyGrande() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("02","papaJuegoAlDiablo2",99999999999999999999999999999999999999.00);
	}

	@Test   (expected=ValorInvalido.class)
	public void probarQueNoSePuedeCrearUnaCuentaConSaldoMenorA0() throws ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("02","papaJuegoAlDiablo2",-5.00);
	}
	
	// Test de Obtener Informacion
	
	
	
	@Test
	public void probarQueDevuelveElAliasIndicado() throws ValorInvalido{
		Cuenta nuevaCajaDeAhorro= new CajaDeAhorroEnPesos ("01","mamaHaceAsado",1500.00);
		assertEquals("mamaHaceAsado",nuevaCajaDeAhorro.obtenerAlias());
	}
	@Test
	public void probarQUeDevuelveElTipo() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("01","League02",4000);
		
		assertEquals("01",nuevaCajaDeAhorro.obtenerTipo());
	}
	@Test
	public void probarQUeDevuelveElSaldo() throws  ValorInvalido{
		Cuenta nuevaCajaDeAhorro = new CajaDeAhorroEnPesos("01","LeagueOfWizzard",920000);
		
		assertEquals(920000,nuevaCajaDeAhorro.obtenerSaldo(),0.1);
	}
	
}
