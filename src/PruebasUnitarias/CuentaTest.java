package PruebasUnitarias;


import org.junit.Test;

import CodigoFuente.CajaDeAhorroEnDolares;
import CodigoFuente.CajaDeAhorroEnPesos;
import CodigoFuente.Cuenta;
import CodigoFuente.CuentaCorriente;
import Exceptions.ValorInvalido;

public class CuentaTest {
	
	// Test Tipo

	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido01()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnDolares("001","isla.pez.arbol",12000.03);
	}
	
	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido02()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("002","isla.pez.arbol",12000.03,50000);
	}
	
	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido03()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnDolares("003","isla.pez.arbol",12000.03);
	}

	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido04()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("2","isla.pez.arbol",12000.03,50000);
	}

	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido05()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("00952","isla.pez.arbol",12000.03,50000);
	}
	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido06()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("99999952","isla.pez.arbol",12000.03,50000);
	}
	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido07()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("a5sd","isla.pez.arbol",12000.03,50000);
	}
	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido08()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("sd","isla.pez.arbol",12000.03,50000);
	}
	@Test(expected = ValorInvalido.class)
	public void cuentaTipoInvalido09()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("k9","isla.pez.arbol",12000.03,50000);
	}

	// Tests Alias
	
	
	@Test
	public void crearCuentaConStringNumerico()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("01","154.87521.698",12000.03);
	}
	@Test
	public void crearCuentaConStringAlfabetico()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("01","isla.pez",12000.03);
	}
	@Test
	public void crearCuentaConStringAlfabeticoDe20Caracteres()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("01","isla.pez.abcdqwe.lp�",12000.03);
	}
	@Test
	public void crearCuentaConStringAlfabeticoDe20CaracteresIguales()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("01","....................",12000.03);
	}
	
	@Test(expected = ValorInvalido.class)
	public void cuentaAliasInvalido()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("01","isla.pez.arbol.piso.aire",12000.03);
	}
	@Test(expected = ValorInvalido.class)
	public void cuentaAliasInvalido02()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("01","is054la.pez.arbol.piso.aire",12000.03);
	}
	@Test(expected = ValorInvalido.class)
	public void cuentaAliasInvalido03()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("01","15487521698.123.6548.78",12000.03);
	}
	
	
	
	// Test Saldo
	
	@Test(expected = ValorInvalido.class)
	public void cuentaCorrienteConSaldoMayorAlDescubierto()throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("02","sol.monte.valle",3021.90,2000.00);
	}
	
	@Test
	public void cuentaCorrienteConSaldoNegativoYDescubiertoPositivo() throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("02","sol.monte.valle",-621.90,6000.00);
	}
	
	@Test
	public void cuentaCorrienteConSaldoPositovoYDescubiertoPositivo() throws  ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("02","sol.monte.valle",4300,6000.00);
	}
	
	@Test(expected = ValorInvalido.class)
	public void cuentaCorrienteConDescubiertoNegativo() throws  ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("02","sol.monte.valle",4300,-6000.00);
	}
	
	@Test (expected = ValorInvalido.class)
	public void cuentaCorrienteConDescubiertoNegativoYSaldoMenorAlDescubierto() throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("02", "sol.aaa.aaa",-200,-100);
	}
	
	@Test
	public void cuentaCorrienteConSaldoPositivoYSinDescubierto() throws ValorInvalido{
		Cuenta unaCuenta = new CuentaCorriente("02","sol.monte.valle",700.00);
	}
	
	
	
	@Test
	public void cuentaCajaDeAhorroEnPesosConSaldoPositivo()throws ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("02","sol.monte.valle",5200.35);
	}
	
	@Test (expected=ValorInvalido.class)
	public void cuentaCajaDeAhorroEnPesosConSaldoNegativo() throws  ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("02","sol.monte.valle",-621.90);
	}
	
	@Test
	public void cuentaCajaDeAhorroEnPesosConSaldoInicialDe0Pesos() throws  ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnPesos("02","sol.monte.valle",0.00);
	}
	
	
	
	@Test
	public void cuentaCajaDeAhorroEnDolaresConSaldoInicialPositivo() throws  ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnDolares("02","sol.monte.valle",6875.65);
	}
	
	@Test(expected = ValorInvalido.class)
	public void cuentaCajaDeAhorroEnDolaresConSaldoInicialNegativo() throws  ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnDolares("02","sol.monte.valle",-8.00);
	}
	
	@Test
	public void cuentaCajaDeAhorroEnDolaresConSaldoInicialEn0() throws  ValorInvalido{
		Cuenta unaCuenta = new CajaDeAhorroEnDolares("02","sol.monte.valle",0);
	}


}
