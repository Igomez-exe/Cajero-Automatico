package CodigoFuente;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Movimiento {
	private Calendar fecha = new GregorianCalendar();
	private Cliente clienteActual;
	private String movimiento;
	private String dia;
	private String mes;
	private String anio;
	
	public Movimiento(Cliente unCliente,String movimiento) throws IOException {
		clienteActual=unCliente;
		this.movimiento=movimiento;
		obtenerDia();
		obtenerMes();
		obtenerAnio();
	}
	public String obtenerDia(){
		
		dia= fecha.get(Calendar.DAY_OF_MONTH)+"";
		return dia;
	}
	public String obtenerMes(){
	
		mes= (fecha.get(Calendar.MONTH)+1)+"";
		return mes;
	}
	public String obtenerAnio(){
		
		anio=fecha.get(Calendar.YEAR)+"";
		return anio;
	}
	
	public String obtenerAlias(){
		return clienteActual.obtenerTarjeta().obtenerCuenta().obtenerAlias();
	}
	public String obtenerSaldo(){
		return clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo()+"";
	}
	public String obtenerOperacion(){
		return movimiento;
	}
	
}

