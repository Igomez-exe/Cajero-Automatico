package Lectores;

import CodigoFuente.Tarjeta;
import Exceptions.ValorInvalido;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LectorDeTarjetas {

	List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();
	String numeroDeTarjeta;
	String numeroPin;
	String cuit;

	//post: lee un archivo de texto y guarda los valores en una lista
	public void lectorDeTarjeta() throws FileNotFoundException,
			ValorInvalido {

		try {
			BufferedReader lector = new BufferedReader(new FileReader("tarjetas.txt"));
			try {
				String linea = lector.readLine();
				while (linea != null) {
					linea = linea.trim();
					if (!linea.isEmpty()) {
						String[] datos;
						datos = linea.split(",");
						numeroDeTarjeta = datos[0];
						numeroPin = datos[1];
						cuit = datos[2];
					}
					Tarjeta unaTarjeta = new Tarjeta(cuit, numeroDeTarjeta,numeroPin);
					tarjetas.add(unaTarjeta);
					//System.out.println(linea);
					linea = lector.readLine();
				}
			} finally {
				lector.close();
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se encontro el archivo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//post devulve una lista de tarjetas
	public List<Tarjeta> obtenerListaDeTarjetas(){
		return tarjetas;
	}
	
}
