package PruebasUnitarias;

import org.junit.Test;

import CodigoFuente.DispencerDeEfectivo;
import Exceptions.ValorInvalido;

public class DispencerDeEfectivoTest {
	

	@Test (expected=ValorInvalido.class)
	public void probarQueSeAgotaronLosBilletesDe1000() throws ValorInvalido  {
		DispencerDeEfectivo a = new DispencerDeEfectivo();
		for(int i = 0; i<=500;i++){
			a.retirarEfectivo(1000);
		}

	}

	@Test (expected=ValorInvalido.class)
	public void probarQueSeAgotaronLosBilletesDe100() throws  ValorInvalido  {
		DispencerDeEfectivo a = new DispencerDeEfectivo();
		for(int i = 0; i<=500;i++){
			a.retirarEfectivo(100);
		}
	}

	@Test (expected=ValorInvalido.class)
	public void probarQueSeAgotaronLosBilletesDe500() throws ValorInvalido {
		DispencerDeEfectivo a = new DispencerDeEfectivo();
		for(int i = 0; i<=500;i++){
			a.retirarEfectivo(500);
		}
	}
	
	
	@Test
	public void probarQueNoSeAgotaronLosBilletesDe1000() throws ValorInvalido  {
		DispencerDeEfectivo a = new DispencerDeEfectivo();
		for(int i = 0; i<=5;i++){
			a.retirarEfectivo(1000);
		}

	}

}
