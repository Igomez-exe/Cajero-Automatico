package CodigoFuente;


import Exceptions.ValorInvalido;

 public class CajaDeAhorroEnPesos extends Cuenta {
	
	public CajaDeAhorroEnPesos(String tipo,String alias,double saldo) throws  ValorInvalido{
		super(tipo,alias,saldo);
	}
	

}
