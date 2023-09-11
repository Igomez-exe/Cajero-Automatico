package CodigoFuente;


import Exceptions.ValorInvalido;

public class CajaDeAhorroEnDolares extends Cuenta {
	
	public CajaDeAhorroEnDolares(String tipo ,String alias,double saldo) throws ValorInvalido{
		super(tipo,alias,saldo);
	}

}
