package CodigoFuente;

import interfazGrafica.SeleccionarCuenta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UltimosMovimientos {
	private HashMap<String, List<Movimiento>> registro = new HashMap<String, List<Movimiento>>();
	private List<Movimiento> listaDeMovimientos = new LinkedList<Movimiento>();
	private Cliente clienteActual=null;
	private Cliente otroCliente;
	private String cuit;

	public UltimosMovimientos(){
		
	}

	public void escribirTxt(String cuit) throws IOException {
		 List<Movimiento> unaLista = new LinkedList<Movimiento>();
		 unaLista=registro.get(cuit);
		File file = new File("Ticket De Transacciones.txt");
		FileWriter escritor = new FileWriter(file);
		PrintWriter impresor = new PrintWriter(escritor);

		Iterator<Movimiento> iterador = unaLista.iterator();

		while (iterador.hasNext()) {
			Movimiento unMovimiento= iterador.next();
			
			impresor.println("Año: "+unMovimiento.obtenerAnio());
			impresor.println("Mes: "+unMovimiento.obtenerMes());
			impresor.println("Dia: "+unMovimiento.obtenerDia());
			impresor.println("Cuenta: " +unMovimiento.obtenerAlias());
			impresor.println("Operacion: " +unMovimiento.obtenerOperacion());
			impresor.println("Saldo Actual: "+unMovimiento.obtenerSaldo());
			impresor.println("");
		}
			impresor.close();
	}

	public void guardarMovimientoEnLista(Movimiento unMovimiento) {
		if(clienteActual.obtenerCuit().equals(cuit)){	
			listaDeMovimientos.add(unMovimiento);
			bancoDeRegistro(clienteActual.obtenerCuit());	
		}else{
			clienteActual=otroCliente;
			listaDeMovimientos= new LinkedList<Movimiento>();
			guardarMovimientoEnLista(unMovimiento);
		}
	}

	public void bancoDeRegistro(String cuit) {
		registro.put(cuit, listaDeMovimientos);
		
	}
	public void asigarCliente(Cliente unCliente){
		if(clienteActual==null){
			clienteActual=unCliente;
			cuit=clienteActual.obtenerCuit();
		}else{
			otroCliente=unCliente;
			cuit=otroCliente.obtenerCuit();
		}
	}
	public List<Movimiento> obtenerUnaLista(String cuit){
		return registro.get(cuit);
	}
	public HashMap<String,List<Movimiento>> obtenerRegistro(){
		return registro;
	}
	
}