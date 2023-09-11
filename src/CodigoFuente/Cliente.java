package CodigoFuente;

import java.util.LinkedList;
import java.util.List;


import Exceptions.ValorInvalido;

public class Cliente extends Banco {
	
	private String cuit;
	private String alias;
	private Tarjeta  unaTarjeta;
	
	public Cliente(String cuit,String alias) throws ValorInvalido{
		super();
		
		if(cuit.length()!=11 || alias.length()>20 ){
			throw new ValorInvalido("Cuit debe tener 11 dijitos y el alias debe tener entre 1 y 20 digitos");
		}else{
			this.alias=alias;
			this.cuit=cuit;
		}
		
	}
	//post:guarda una tarjeta pasada como parametro como atributo
	public void asignarTarjeta(Tarjeta tarjeta){
		unaTarjeta=tarjeta;
	}
	// post: devuelve unaTarjeta
	public Tarjeta obtenerTarjeta(){
		return unaTarjeta;
	}
	//post devuelve el alias del cliente
	public String obtenerAlias(){
		return alias;
	}
	//post:devuelve el cuit del cliente
	public String obtenerCuit(){
		return cuit;
	}
}