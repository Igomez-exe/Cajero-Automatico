package CodigoFuente;


import Exceptions.ValorInvalido;

public class CuentaCorriente extends Cuenta{
	
	public CuentaCorriente(String tipo,String alias ,double saldo ,double descubierto) throws ValorInvalido{
		super(tipo,alias,saldo,descubierto);
	
	}
	
	public CuentaCorriente(String tipo,String alias ,double saldo ) throws ValorInvalido{
		super(tipo,alias,saldo);
	
	}
	
}
