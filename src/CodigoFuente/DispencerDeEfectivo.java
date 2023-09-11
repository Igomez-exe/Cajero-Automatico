package CodigoFuente;

import Exceptions.ValorInvalido;

public class DispencerDeEfectivo {
	private Billete billeteDeMil;
	private Billete billeteDeQuinientos;
	private Billete billeteDeCien;
	
	public DispencerDeEfectivo(){
		billeteDeMil=new Billete(1000);
		billeteDeQuinientos= new Billete(500);
		billeteDeCien=new Billete(100);
	}
	//pre: monto debe ser mayor a 0
	//post: descuenta al saldo el monto solicitado
	public void retirarEfectivo(int monto) throws ValorInvalido{
		int montoRestante=monto;
		while(montoRestante>=billeteDeMil.obtenerValorDelBillete()){
			if(billeteDeMil.obtenerCantidadDeBilletes()>=1){
				montoRestante-=1000;
				billeteDeMil.utilizarUnBillete();	
			}else{
				throw new ValorInvalido("No Hay Mas Billetes {1000}");
			}
			
		}
		while(montoRestante>=billeteDeQuinientos.obtenerValorDelBillete()){
			if(billeteDeQuinientos.obtenerCantidadDeBilletes()>=1){
				montoRestante-=500;
				billeteDeQuinientos.utilizarUnBillete();	
			}else{
				throw new ValorInvalido("No Hay Mas Billetes  {500}");
			}
			
		}
		while(montoRestante>=billeteDeCien.obtenerValorDelBillete()){
			if(billeteDeCien.obtenerCantidadDeBilletes()>=1){
				montoRestante-=100;
				billeteDeCien.utilizarUnBillete();	
			}else{
				throw new ValorInvalido("No Hay Mas Billetes {100}");
			}
			
		}
	}

}
