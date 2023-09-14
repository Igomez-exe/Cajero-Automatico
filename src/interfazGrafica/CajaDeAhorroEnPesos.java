package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.io.FileNotFoundException;

import javax.swing.SwingConstants;

import CodigoFuente.Cliente;
import Exceptions.ValorInvalido;


public class CajaDeAhorroEnPesos extends JFrame {
	public Cliente clienteActual;
	private JPanel contentPane;

	public CajaDeAhorroEnPesos() {
		
		pasarDatosDeSeleccionarCuentaACajaDeAhorroEnPesos();
		
		crearVentanaCajaDeAhorroEnPesos();
		
		crearEImplementarBotonDepositar();
		
		crearBotonEliminarTransferencia();
		
		crearBotonTransferir();
		
		crearEImplementarBotonObtenerSaldo();
		
		crearEImplementarBotonAtras();
		
		crearEImplementarBotonComprarDolares();
		
		crearEImplementarBotonRetirarEfectivo();
		
		crearTextoSeleccioneUnaOperacion();
		
		crearTextoRetirarEfectivo();
		
		crearTextoDeposito();
		
		establecerFondo();
	}
	//post: crea la ventana de caja de ahorro en pesos
	public void crearVentanaCajaDeAhorroEnPesos(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(CajaDeAhorroEnPesos.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el boton depositar
	public void crearEImplementarBotonDepositar(){
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			//post: crea una nueva ventana de RanuraDeDeposito
			public void actionPerformed(ActionEvent e) {
				RanuraDeDeposito nuevaVentana = new RanuraDeDeposito();
				nuevaVentana.asignarTipoDeCuenta(1);
				nuevaVentana.setVisible(true);
				dispose();
			}
		});
		btnDepositar.setBounds(10, 402, 350, 47);
		btnDepositar.setIcon(new ImageIcon(CajaDeAhorroEnPesos.class.getResource("/imagen/depositarSobres.png")));

		contentPane.add(btnDepositar);
	}
	//post: crea el boton eliminar transferencia
	public void crearBotonEliminarTransferencia(){

		JButton btnEliminarTransferencia = new JButton("Elimintar Transferencia");
		btnEliminarTransferencia.addActionListener(new ActionListener() {
			//post: cancela la ultima transferencia realizada
			public void actionPerformed(ActionEvent e) {
				if(!clienteActual.verificarSiOcurrioOtraOperacion()){
						clienteActual.CancelarTransaccion();
					
					JOptionPane.showMessageDialog(null,"Su ultima Transaccion Fue Cancelada");
				}else{
					JOptionPane.showMessageDialog(null, "No se puede cancelar su ultima la transferencia debido a que realizo otra operacion");
				}
			}
		});
		btnEliminarTransferencia.setForeground(Color.BLACK);
		btnEliminarTransferencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnEliminarTransferencia.setBackground(new Color(255, 102, 51));
		btnEliminarTransferencia.setBounds(402, 168, 171, 23);
		contentPane.add(btnEliminarTransferencia);
		
	}
	//post: crea el boton transferir
	public void crearBotonTransferir(){
		
		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			//post: crea una nueva ventana Transferir
			public void actionPerformed(ActionEvent e) {
				Transferir nuevaVentana = new Transferir();
				nuevaVentana.asignarTipoDeCenta(1);
				nuevaVentana.setVisible(true);
				dispose();
				
			}
		});
		btnTransferir.setForeground(Color.BLACK);
		btnTransferir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnTransferir.setBackground(new Color(255, 102, 51));
		btnTransferir.setBounds(10, 168, 161, 23);
		contentPane.add(btnTransferir);
		
	}
	
	//post: crea el boton obtener saldo
	public void crearEImplementarBotonObtenerSaldo(){
		JButton btnObtenerSaldo = new JButton("Obtener Saldo");
		btnObtenerSaldo.addActionListener(new ActionListener() {
			//post: devuelve el saldo del cliente
			public void actionPerformed(ActionEvent e) {
				double saldo = clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo();
				saldo=  Math.round(saldo * 100);
				saldo=saldo/100;
				JOptionPane.showMessageDialog(null,"Usted posee $"+saldo+" en su cuenta");
			}
		});
		btnObtenerSaldo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnObtenerSaldo.setBackground(new Color(255, 102, 51));
		btnObtenerSaldo.setForeground(Color.BLACK);
		btnObtenerSaldo.setBounds(402, 125, 171, 23);
		contentPane.add(btnObtenerSaldo);
	}
	//post: crea el boton atras
	public void crearEImplementarBotonAtras(){
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			//post: vuelve a la ventana anterior
			public void actionPerformed(ActionEvent e) {
				SeleccionarCuenta nuevaVentana;
				try {
					nuevaVentana = new SeleccionarCuenta();
					nuevaVentana.setVisible(true);
					dispose();
				} catch (FileNotFoundException
						| ValorInvalido e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setBackground(new Color(255, 102, 51));
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAtras.setBounds(466, 413, 89, 23);
		contentPane.add(btnAtras);
	}
	//post: crea el  boton comprar dolares
	public void crearEImplementarBotonComprarDolares(){
		JButton btnComprarDolares = new JButton("Comprar Dolares");
		btnComprarDolares.addActionListener(new ActionListener() {
			//post: crea una ventana ComprarDolares
			public void actionPerformed(ActionEvent e) {
				ComprarDolares nuevaVentana= new ComprarDolares();
				nuevaVentana.asignarQueCuentaUsar(1);
				nuevaVentana.setVisible(true);
				dispose();		
			}
		});
		btnComprarDolares.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnComprarDolares.setForeground(Color.BLACK);
		btnComprarDolares.setBackground(new Color(255, 102, 51));
		btnComprarDolares.setBounds(10, 125, 161, 23);
		contentPane.add(btnComprarDolares);
	}
	
	//post: crea el boton retirar efectivo
	public void crearEImplementarBotonRetirarEfectivo(){
		JButton btnRetirarEfectivo = new JButton();
		btnRetirarEfectivo.addActionListener(new ActionListener() {
			//post: crea una ventana RetirarEfeCtivoCC
			public void actionPerformed(ActionEvent e) {
				RetirarEfectivo nuevaVentana= new RetirarEfectivo();
				nuevaVentana.asignarTipoDeCuenta(1);
				nuevaVentana.setVisible(true);
				ocultar();
			}
		});
		btnRetirarEfectivo.setBounds(386, 276, 184, 60);
		btnRetirarEfectivo.setIcon(new ImageIcon(CajaDeAhorroEnPesos.class.getResource("/imagen/dispensadorDeDinero.png")));
		contentPane.add(btnRetirarEfectivo);
	}
	
	//post: crea el texto seleccione una operacion
		public void crearTextoSeleccioneUnaOperacion(){
			JLabel lblTitulo = new JLabel("Seleccione Una Operacion");
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setForeground(new Color(255, 102, 51));
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblTitulo.setBounds(143, 26, 287, 23);
			contentPane.add(lblTitulo);
		}
	//post: crea el texti deposito
	public void crearTextoDeposito(){
		JLabel lblDeposito = new JLabel("Deposito:");
		lblDeposito.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDeposito.setForeground(new Color(255, 102, 51));
		lblDeposito.setBounds(10, 356, 106, 35);
		contentPane.add(lblDeposito);
}
	//post: crea el texti Retirar Efectivo
	public void crearTextoRetirarEfectivo(){
		JLabel lblRetirarEfectivo = new JLabel("Retirar Efectivo :");
		lblRetirarEfectivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRetirarEfectivo.setForeground(new Color(255, 102, 51));
		lblRetirarEfectivo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblRetirarEfectivo.setBounds(377, 237, 196, 35);
		contentPane.add(lblRetirarEfectivo);
}	
	
	
	//post: crea el fondo de la ventana 
	public void establecerFondo(){
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CajaDeAhorroEnPesos.class.getResource("/imagen/fondo_2.jpg")));
		lblFondo.setBounds(-157, 0, 880, 462);
		contentPane.add(lblFondo);
	}
	//post: guarda en clienteActual el ClienteActual de SeleccionarCuenta
	public void pasarDatosDeSeleccionarCuentaACajaDeAhorroEnPesos(){
		clienteActual=SeleccionarCuenta.clienteActual;
	}
	//post: hace invicible a la ventana
	public void ocultar(){
		this.setVisible(false);
	}

}
