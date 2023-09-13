package Lectores;
import CodigoFuente.Cliente;


import Exceptions.ValorInvalido;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LectorDeClientes {
	List<Cliente> clientes=new LinkedList<Cliente>();
	String cuitEnTxt="vacio";
	String aliasEnTxt="vacio";
	
	//post: lee un archivo de texto y guarda los valores en una lista
	public void lectorDeClientes() throws FileNotFoundException, ValorInvalido{	
		try{
			BufferedReader lector = new BufferedReader(new FileReader("clientes.txt"));
			try{
				String linea = lector.readLine();
				while(linea != null){
					linea = linea.trim();
					if(!linea.isEmpty()){
						String[] datos;
						datos = linea.split(",");
						
						 cuitEnTxt =datos[0];
						 aliasEnTxt= datos[1];
											
					}
					Cliente unCliente= new Cliente(cuitEnTxt,aliasEnTxt);
					clientes.add(unCliente);
					linea = lector.readLine();
				}
			}finally{
				lector.close();
			}
		}catch(FileNotFoundException e){
			throw new FileNotFoundException("No se encontro el archivo");
		}catch(IOException e){
			e.printStackTrace();
		}
	
	}
	//post devuelve la lista de clientes
	public  List<Cliente> obtenerListasDeClientes(){
		return clientes;
	}

}
