package CodigoFuente;

import interfazGrafica.SeleccionarCuenta;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import Exceptions.CuentaInexistente;
import Exceptions.ValorInvalido;


public abstract class Banco {

	static List<Cliente> listaDeClientes = new LinkedList<Cliente>();
	Cliente clienteActual;
	Cliente unCliente;
	Transferencia unaTransferencia;
	Cuenta cuentaEnDolares;
	private boolean ocurrioOtraOperacion;
	private double saldoAnteriorARealizarLaTransrefencia;
	private double saldoAnteriorARecivirLaTransferencia;

	
	//pre: montoATransferir debe ser mayor a 0.
	//post: transfiere el monto desde la cuenta corriente a la cuanta con el alias asociado.
	public void transferirMontoConCuentaCorriente(String alias,
			double montoATransferir) throws ValorInvalido, CuentaInexistente {
		verificarMonto(montoATransferir);
		pasarDatos();
		boolean encontrado=false;

		Iterator<Cliente> iterador = listaDeClientes.iterator();
		while (iterador.hasNext()) {
			unCliente = iterador.next();

			if (unCliente.obtenerAlias().equals(alias)) {
				encontrado=true;
				if (unCliente.obtenerTarjeta().obtenerCuenta().obtenerTipo()
						.equals("02")) {

					saldoAnteriorARealizarLaTransrefencia = clienteActual
							.obtenerTarjeta().obtenerCuenta().obtenerSaldo();

					if (saldoAnteriorARealizarLaTransrefencia
							- montoATransferir < clienteActual.obtenerTarjeta()
							.obtenerCuenta().obtenerDescubierto()) {
						JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
						throw new ValorInvalido("Saldo Insuficiente");
					} else {
						clienteActual.obtenerTarjeta().obtenerCuenta()
								.restarMontoAlSaldo(montoATransferir);

						saldoAnteriorARecivirLaTransferencia = unCliente
								.obtenerTarjeta().obtenerCuenta()
								.obtenerSaldo();
						unCliente.obtenerTarjeta().obtenerCuenta()
								.agregarMontoAlSaldo(montoATransferir);

						unaTransferencia = new Transferencia(
								saldoAnteriorARealizarLaTransrefencia,
								saldoAnteriorARecivirLaTransferencia);
							ocurrioOtraOperacion=false;
					}
				} else {
					throw new CuentaInexistente("esta cuenta no es de tipo 02");
				}
			} 
		}
		if(!encontrado){
			throw new CuentaInexistente("La cuenta " + alias + " no existe");
		}

	}
	
	public void transferirMontoConDescubiertoCuentaCorriente(String alias,
			double montoATransferir) throws ValorInvalido,
			 CuentaInexistente {
		verificarMonto(montoATransferir);
		pasarDatos();

		Iterator<Cliente> iterador = listaDeClientes.iterator();
		
		while (iterador.hasNext()) {
			unCliente = iterador.next();
			
			if (unCliente.obtenerAlias().equals(alias)) {
				if (unCliente.obtenerTarjeta().obtenerCuenta().obtenerTipo()
						.equals("02")) {

					saldoAnteriorARealizarLaTransrefencia = clienteActual
							.obtenerTarjeta().obtenerCuenta().obtenerDescubierto();
					if (saldoAnteriorARealizarLaTransrefencia
							- montoATransferir < 0) {
						JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
						throw new ValorInvalido("Saldo Insuficiente");					
					}else{
						clienteActual.obtenerTarjeta().obtenerCuenta().restarMontoAlDescubierto(montoATransferir);
						
						saldoAnteriorARecivirLaTransferencia = unCliente.obtenerTarjeta().obtenerCuenta().obtenerSaldo();
						unCliente.obtenerTarjeta().obtenerCuenta().agregarMontoAlSaldo(montoATransferir);
						unaTransferencia = new Transferencia(saldoAnteriorARealizarLaTransrefencia,saldoAnteriorARecivirLaTransferencia);
					    ocurrioOtraOperacion=false;
					}
				} else {
					throw new CuentaInexistente("esta cuenta no es de tipo 02");
				}
			}	
		}
		
		
	}
	//pre: montoATransferir debe ser mayor a 0.
	//post: transfiere el monto desde la caja de ahorro en pesos a la cuanta con el alias asociado.
	public void transferirMontoConCajaDeAhorroEnPesos(String alias,
			double montoATransferir) throws ValorInvalido,
			 CuentaInexistente {
		verificarMonto(montoATransferir);
		pasarDatos();
		boolean encontrado=false;

		Iterator<Cliente> iterador = listaDeClientes.iterator();
		while (iterador.hasNext()) {
			unCliente = iterador.next();

			if (unCliente.obtenerAlias().equals(alias)) {
				encontrado=true;
				if (unCliente.obtenerTarjeta().obtenerCuenta().obtenerTipo()
						.equals("01")) {

					saldoAnteriorARealizarLaTransrefencia = clienteActual
							.obtenerTarjeta().obtenerCuenta().obtenerSaldo();
					
					if (saldoAnteriorARealizarLaTransrefencia - montoATransferir < 0.0) {
						JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
						throw new ValorInvalido("Saldo Insuficiente");
					} else {
						
						clienteActual.obtenerTarjeta().obtenerCuenta()
						.restarMontoAlSaldo(montoATransferir);
						
						saldoAnteriorARecivirLaTransferencia = unCliente
								.obtenerTarjeta().obtenerCuenta().obtenerSaldo();
						unCliente.obtenerTarjeta().obtenerCuenta()
						.agregarMontoAlSaldo(montoATransferir);
						
						unaTransferencia = new Transferencia(
								saldoAnteriorARealizarLaTransrefencia,
								saldoAnteriorARecivirLaTransferencia);
							ocurrioOtraOperacion=false;
					}
				} else {
					throw new CuentaInexistente("esta cuenta no es de tipo 02");
				}
			}
		}
		if(!encontrado){
			throw new CuentaInexistente("La cuenta �" + alias + "� no existe");
		}
		
	}
	//post: cancela la ultima transaccion realizada.
	public void CancelarTransaccion() {

		saldoAnteriorARecivirLaTransferencia = unaTransferencia
				.obtenerSaldoDeQuienResiveLaTransferencia();
		saldoAnteriorARealizarLaTransrefencia = unaTransferencia
				.obtenerSaldoDeQuienTransfiere();
		clienteActual.obtenerTarjeta().obtenerCuenta()
				.actualizarMonto(saldoAnteriorARealizarLaTransrefencia);
		unCliente.obtenerTarjeta().obtenerCuenta()
				.actualizarMonto(saldoAnteriorARecivirLaTransferencia);
	}
	
	//pre: monto debe ser mayor a 0
	//post: resta el monto a su saldo correspondiente a caja de ahorro en pesos.
	public void retirarEfectivoDeCajaDeAhorroEnPesos(double monto)
			throws ValorInvalido {
		verificarMonto(monto);
		pasarDatos();

		if (clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo()
				- monto < 0.00) {
			
			throw new ValorInvalido("Saldo Insuficiente");
		} else {
			clienteActual.obtenerTarjeta().obtenerCuenta()
					.restarMontoAlSaldo(monto);
			ocurrioOtraOperacion=true;
		}

	}
	//pre: monto debe ser mayor a 0
	//post: resta el monto a su saldo correspondiente a cuenta corriente.
	public void retirarEfectivoDeCuentaCorriente(double monto)
			throws ValorInvalido{
		verificarMonto(monto);
		pasarDatos();

		if (clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo()
				- monto < clienteActual.obtenerTarjeta().obtenerCuenta()
				.obtenerDescubierto()) {
			
			JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
			throw new ValorInvalido("Saldo Insuficiente");

		} else {
			clienteActual.obtenerTarjeta().obtenerCuenta()
					.restarMontoAlSaldo(monto);
			ocurrioOtraOperacion=true;
		}
	}
	
	public void retirarEfectivoDeCuentaCorrienteConDesubierto(double monto) throws ValorInvalido{
		verificarMonto(monto);
		pasarDatos();
		
		if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerDescubierto()-monto <0
				){
			throw new ValorInvalido("Saldo Insuficiente");
		}else {
			clienteActual.obtenerTarjeta().obtenerCuenta()
			.restarMontoAlDescubierto(monto);
			ocurrioOtraOperacion=true;
}
		
		
	}
	//pre:cantidadDeDolares debe ser mayor a 0.
	//post:resta al saldo de Cuenta corriente el resultado de la operatoria con cantidadDeDolares.
	public void comprarDolaresConCuentaCorriente(
			double cantidadDeDolares) throws ValorInvalido,
			 CuentaInexistente {
		verificarMonto(cantidadDeDolares);
		pasarDatos();

		
			double costoDeDolares = (cantidadDeDolares * 120);
			double impuestoPais = (costoDeDolares * 0.30);
			double saldoADescontar = costoDeDolares + impuestoPais;

			if (clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo()
					- saldoADescontar < clienteActual.obtenerTarjeta()
					.obtenerCuenta().obtenerDescubierto()) {
				
				throw new ValorInvalido("Saldo insuficiente");
			} else {
				clienteActual.obtenerTarjeta().obtenerCuenta()
						.restarMontoAlSaldo(saldoADescontar);
				obtenerCuentaDeDolares();
				cuentaEnDolares.agregarMontoAlSaldo(cantidadDeDolares);
				ocurrioOtraOperacion=true;
			}
	}
	
	public void comprarDolaresConDescubiertoDeCuentaCorriente(double cantidadDeDolares) throws ValorInvalido, CuentaInexistente{
		verificarMonto(cantidadDeDolares);
		pasarDatos();
		
		double costoDeDolares = (cantidadDeDolares * 120);
		double impuestoPais = (costoDeDolares * 0.30);
		double descubiertoADescontar = costoDeDolares + impuestoPais;
		
		if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerDescubierto() - descubiertoADescontar < 0){
			throw new ValorInvalido("Saldo insuficiente");
		}else {
			clienteActual.obtenerTarjeta().obtenerCuenta()
			.restarMontoAlDescubierto(descubiertoADescontar);
			obtenerCuentaDeDolares();
			cuentaEnDolares.agregarMontoAlSaldo(cantidadDeDolares);
			ocurrioOtraOperacion=true;
		}
	}
	//pre:cantidadDeDolares debe ser mayor a 0.
	//post:resta al saldo de Caja de ahorro en pesos el resultado de la operatoria con cantidadDeDolares.
	public void comprarDolaresConCajaDeAhorroEnPesos(double cantidadDeDolares) throws ValorInvalido,
			 CuentaInexistente {
		verificarMonto(cantidadDeDolares);
		pasarDatos();
	
			double costoDeDolares = (cantidadDeDolares * 120);
			double impuestoPais = (costoDeDolares * 0.30);
			double saldoADescontar = costoDeDolares + impuestoPais;

			if (clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo()
					- saldoADescontar < 0.00) {
				
				throw new ValorInvalido("Saldo insuficiente");
			} else {
				clienteActual.obtenerTarjeta().obtenerCuenta()
						.restarMontoAlSaldo(saldoADescontar);
				obtenerCuentaDeDolares();
				cuentaEnDolares.agregarMontoAlSaldo(cantidadDeDolares);
				ocurrioOtraOperacion=true;
			
			}
		
	
	}
	//pre: monto debe ser mayor a 0.
	//post:deposita en la caja de ahorro en pesos el monto especificado.
	public void depositarEnCajaDeAhorroEnPesos(double monto) throws ValorInvalido{
        verificarMonto(monto);
        pasarDatos();

        clienteActual.obtenerTarjeta().obtenerCuenta().agregarMontoAlSaldo(monto);
        ocurrioOtraOperacion=true;

    }
	//pre: monto debe ser mayor a 0.
	//post:deposita en la cuenta corriente el monto especificado.
    public void depositarEnCuentaCorriente(double monto) throws ValorInvalido{
        verificarMonto(monto);
        pasarDatos();

        clienteActual.obtenerTarjeta().obtenerCuenta().agregarMontoAlSaldo(monto);
        ocurrioOtraOperacion=true;

    }
    //pre: monto debe ser mayor a 0.
  	//post:deposita en la caja de ahorro en dolares el monto especificado.
    public void depositarEnCajaDeAhorroEnDolares(double monto) throws ValorInvalido{
        verificarMonto(monto);
        pasarDatos();

        clienteActual.obtenerTarjeta().obtenerCuenta().agregarMontoAlSaldo(monto);
        ocurrioOtraOperacion=true;
    }

	
    //post verifica si el monto es menor a 0
	public void verificarMonto(double monto) throws ValorInvalido {
		if (monto < 0) {
			throw new ValorInvalido("El monto No puede ser negarivo");
		}
	}
	//post:se le asigna un valor a los atributos
	private void pasarDatos() {
		clienteActual = SeleccionarCuenta.clienteActual;
		listaDeClientes = SeleccionarCuenta.listaDeClientes;

	}
	//pre:alias debe estar en la base de datos.
	//post: guarda el cliene como atributo.
	public void buscarClientePorAlias(String alias) {
		Iterator<Cliente> iterador = listaDeClientes.iterator();
		boolean encontrado = false;
		while (!encontrado && iterador.hasNext()) {
			unCliente = iterador.next();

			if (unCliente.obtenerTarjeta().obtenerCuenta().equals(alias)) {
				encontrado = true;
			}
		}
	}
	// post devuelve un valor booleano para saber si hubo o no otra operacion
	public boolean verificarSiOcurrioOtraOperacion(){
		return ocurrioOtraOperacion;
	}
	//post:
	public void obtenerCuentaDeDolares() throws CuentaInexistente{
		pasarDatos();
		
		String cuit= clienteActual.obtenerCuit();
		Iterator<Cliente> iterador = listaDeClientes.iterator();
		
		while (iterador.hasNext()) {
			unCliente = iterador.next();
			if(cuit.equals(unCliente.obtenerTarjeta().obtenerCuit())){
				if(unCliente.obtenerTarjeta().obtenerCuenta().obtenerTipo().equals("03")){
					cuentaEnDolares=unCliente.obtenerTarjeta().obtenerCuenta();
				}
				
			}
			
		} 
		if(cuentaEnDolares==null){
			throw new CuentaInexistente("No posee una caja de ahorro en dolares");
		}
		
	}

}
