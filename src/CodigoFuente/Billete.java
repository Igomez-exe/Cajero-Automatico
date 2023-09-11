package CodigoFuente;

public class Billete {
	
	private int valorDelBillete;
	private int cantidadDeBilletes=500;


	
	public Billete(int valorDelBillete){
		this.valorDelBillete=valorDelBillete;
	}
	//post: devuelve el valor de valorDelBillete
	public int obtenerValorDelBillete(){
		return valorDelBillete;
	}
	//post: devuelve el valor de cantidadDeBilletes
	public int obtenerCantidadDeBilletes(){
		return cantidadDeBilletes;
	}
	//post : resta 1 billete de cantidadDeBilletes.
	public void utilizarUnBillete(){
		cantidadDeBilletes-=1;
	}
}
