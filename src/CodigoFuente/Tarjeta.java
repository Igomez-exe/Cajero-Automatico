package CodigoFuente;

import java.util.LinkedList;
import java.util.List;


import Exceptions.ValorInvalido;

public class Tarjeta {
	private String pinDeTarjeta;
	private String numeroDeTarjeta;
	private String cuit;
	private Cuenta cuenta;
	
	public Tarjeta(String cuit,String numeroDeTarjeta,String pinDeTarjeta) throws ValorInvalido{
		if(cuit.length()!=11 || numeroDeTarjeta.length()!=8 || pinDeTarjeta.length()!=4){
			throw new ValorInvalido(" Los digitos Deben ser cuit=11,numeroDeTarjeta=8,pinDeTarjeta=4");
		}
		this.pinDeTarjeta=pinDeTarjeta;
		this.numeroDeTarjeta=numeroDeTarjeta;
		this.cuit=cuit;
	}
	//post:devuelve el pinDeLaTarjeta
	public String obtenerPinDeTarjeta(){
		return pinDeTarjeta;
	}
	//post:devuelve elnumeroDeTarjeta de la tarjeta
	public String obtenerNumeroDeTarjeta(){
		return numeroDeTarjeta;
	}
	//post:devuelve el cuit de la tarjeta
	public String obtenerCuit(){
		return cuit;
	}
	//post :guarda una cuenta como atributo
	public void asignarCuenta(Cuenta unaCuenta){
			cuenta=unaCuenta;
	}
	//post:devuelve el valor de pingDeTarjeta
	public Cuenta obtenerCuenta(){
		return cuenta;
	}

}
