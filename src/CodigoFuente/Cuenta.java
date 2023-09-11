package CodigoFuente;

import Exceptions.ValorInvalido;

public class Cuenta {

	private String tipo;
	private double saldo;
	private String alias;
	private double descubierto;

	// 01 CajaDeAhorroEnPesos
	// 02 CuentaCorriente
	// 03 CajaDeAhorroEnDolares

	public Cuenta(String tipo, String alias, double saldo, double descubierto)
			throws  ValorInvalido {
		verificarConDescubierto(tipo, alias, saldo,descubierto);
		verificarDescubiertoMayorACero(descubierto);
		
		

	}

	public Cuenta(String tipo, String alias, double saldo)
			throws ValorInvalido {
		verificar(tipo, alias, saldo);
	}
	//pre: tipo debe contener 2 caracteres ,tipo debe ser menor a 20 caracteres y saldo debe ser mayor a 0
	//post:verifica si se cumplen las precondiciones ,de ser asi guarda los valores como atributos
	public void verificar(String tipo, String alias, double saldo)
			throws ValorInvalido {

		if (tipo.length() != 2 || alias.length() > 20 || saldo<0.00) {
			throw new ValorInvalido(
					"la cantidad de digitos de *tipo* debe se igual a 2 "
							+ "y la cantidad de digitos de *alias* debe ser mayor a 1 y menor a 20");
		}
		if(tipo.equals("01") || tipo.equals("02") || tipo.equals("03")){
			
			this.tipo = tipo;
			this.saldo = saldo;
			this.alias = alias;
		}else{
			throw new ValorInvalido("Los Unicos Tipos posibles son 01,02 y 03");
			
		}
	}
	//pre: tipo debe contener 2 caracteres ,tipo debe ser menor a 20 caracteres, saldo debe ser mayor a 0 y descubierto debe ser menor a 0
	//post:verifica si se cumplen las precondiciones ,de ser asi guarda los valores como atributos
	public void verificarConDescubierto(String tipo, String alias, double saldo,double descubierto) throws ValorInvalido{

		if (tipo.length() != 2 || alias.length() > 20 ){ 
			throw new ValorInvalido(
					"la cantidad de digitos de *tipo* debe se igual a 2 "
							+ "y la cantidad de digitos de *alias* debe ser mayor a 1 y menor a 20");
		}
		if(saldo>descubierto ){
			throw new ValorInvalido("Saldo debe ser menor al descubierto");
		}
		if(tipo.equals("01") || tipo.equals("02") || tipo.equals("03")){
			
			this.tipo = tipo;
			this.saldo = saldo;
			this.alias = alias;
		}else{
			throw new ValorInvalido("Los Unicos Tipos posibles son 01,02 y 03");
			
		}
	}
		
		
	//pre:descubierto debe ser menor a 0.
	//post: si descubierto es menor a 0 el valor se guarda como atributo
	public void  verificarDescubiertoMayorACero(double descubierto) throws ValorInvalido{
		if(descubierto<0.00){
			throw new ValorInvalido("El Descubierto Debe ser mayor a 0 ");
		}else{
			this.descubierto=descubierto;
		}
		
	}
	//post:devuelve el alias de la cuenta
	public String obtenerAlias() {
		return alias;
	}
	//post:devuelve el tipo de la cuenta
	public String obtenerTipo() {
		return tipo;
	}
	//post:devuelve el saldo de la cuenta
	public double obtenerSaldo() {
		return saldo;
	}
	//post:devuelve el descubierto de la cuenta
	public double obtenerDescubierto(){
		return descubierto;
	}
	//pre:monto>0
	//post: suma al saldo el monto
	public void agregarMontoAlSaldo(double monto){
		saldo=saldo+monto;
	}
	//pre:monto>0
	//post:resta al saldo el monto
	public void restarMontoAlSaldo(double monto){
		saldo=saldo-monto;
	}
	//pre:monto >0
	//post: cambia el saldo por el valor de monto
	public void actualizarMonto(double monto){
		saldo=monto;
	}
	
	public void restarMontoAlDescubierto(double monto){
		descubierto = descubierto - monto;
	}
}
