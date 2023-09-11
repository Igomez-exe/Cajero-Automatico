package CodigoFuente;

public class Transferencia {
	double saldoDeQuienTransfiere;
	double saldoDeQuienResiveLaTransaccion;
	
	
	public Transferencia(){
		
	}
	
	public Transferencia(double quienTransfiere, double quienResiveLaTransferencia){
		saldoDeQuienTransfiere=quienTransfiere;
		saldoDeQuienResiveLaTransaccion=quienResiveLaTransferencia;
	}
	//post:devuelve el valor de saldoDeQuienTransfiere
	public double obtenerSaldoDeQuienTransfiere(){
		return saldoDeQuienTransfiere;
	}
	//post:devuelve el valor de saldoDeQuienResiveLaTransaccion
	public double obtenerSaldoDeQuienResiveLaTransferencia(){
		return saldoDeQuienResiveLaTransaccion;
	}
}
