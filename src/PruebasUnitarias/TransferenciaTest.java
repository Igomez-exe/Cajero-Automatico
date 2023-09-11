package PruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import CodigoFuente.Transferencia;

public class TransferenciaTest {

	@Test
	public void probarQueExisteUnaTransferencia() {
		Transferencia transferencia = new Transferencia();

	}

	@Test
	public void probarQueLosValoresDeLaTransferenciaSeanLosCorrectos() {
		Transferencia transferencia = new Transferencia(50000,2000);
	    assertEquals(50000,transferencia.obtenerSaldoDeQuienTransfiere(),0.00);
	    assertEquals(2000,transferencia.obtenerSaldoDeQuienResiveLaTransferencia(),0.00);
	}

}
