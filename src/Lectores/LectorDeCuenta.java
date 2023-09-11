package Lectores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import CodigoFuente.CajaDeAhorroEnDolares;
import CodigoFuente.CajaDeAhorroEnPesos;
import CodigoFuente.Cuenta;
import CodigoFuente.CuentaCorriente;
import Exceptions.ValorInvalido;

public class LectorDeCuenta {

	List<Cuenta> cuentas = new LinkedList<Cuenta>();
	double descubierto = 0;
	double saldo;
	String tipo;
	String alias;
	Cuenta nuevaCuenta;

	//post: lee un archivo de texto y guarda los valores en una lista
	public void lectorDatosDeCuentas() throws FileNotFoundException,
			 ValorInvalido {

		try {
			BufferedReader lector = new BufferedReader(new FileReader("cuentas.txt"));
			try {
				String linea = lector.readLine();
				while (linea != null) {
					linea = linea.trim();
					if (!linea.isEmpty()) {
						String[] datos;
						datos = linea.split(",");
						 tipo = datos[0];
						 alias = datos[1];

						 if (datos.length == 4) {
                             saldo =Double.parseDouble( datos[2]);
                             descubierto =Double.parseDouble( datos[3]);
                        } else {
                             saldo = Double.parseDouble( datos[2]);
                        }
					}

					if (tipo.equals("02")) {
						nuevaCuenta = new CuentaCorriente(tipo, alias, saldo,
								descubierto);
					} else if (tipo.equals("01")) {
						nuevaCuenta = new CajaDeAhorroEnPesos(tipo, alias, saldo);
					} else if (tipo.equals("03")) {
						nuevaCuenta = new CajaDeAhorroEnDolares(tipo, alias, saldo);
					}
					cuentas.add(nuevaCuenta);
					
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
	//post: devuelve una lista de cuentas
	public List<Cuenta> obtenerListaDeCuentas(){
		return cuentas;
	}
}
