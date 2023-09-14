package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.io.FileNotFoundException;

import javax.swing.SwingConstants;

import CodigoFuente.Cliente;
import Exceptions.ValorInvalido;


public class CuentaCorriente extends JFrame {
	public Cliente clienteActual;
	private JPanel contentPane;

	public CuentaCorriente() {
		
		pasarDeSeleccionarCuentaACuentaCorriente();
		
		crearVentanaCuentaCorriente();
		
		crearEImplementarBotonDepositar();
		
		crearBotonTransferir();
		
		crearBotonEliminarTransferencia();
		
		crearEImplementarBotonUltimosMovimientos();
		
		crearEImplementarBotonAtras();
		
		crearEImplementarBotonObtenerSaldo();
		
		crearEImplementarBotonComprarDolares();
		
		crearEImplementarBotonRetirarEfectivo();
		
		crearTextoSeleccioneUnaOperacion();
		
		crearTextoRetirarEfectivo();
		
		crearTextoDeposito();
		
		establecerFondo();
	}
	//post: crea la ventana de CuentaCorriente
	public void crearVentanaCuentaCorriente(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(CuentaCorriente.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el boton depositar
	public void crearEImplementarBotonDepositar(){
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			//post: crea una ventana RanuraDeDeposito
			public void actionPerformed(ActionEvent e) {
				RanuraDeDeposito nuevaVentana= new RanuraDeDeposito();
				nuevaVentana.setVisible(true);
				dispose();
			}
		});
		btnDepositar.setBounds(10, 402, 350, 47);
		btnDepositar.setIcon(new ImageIcon(CajaDeAhorroEnPesos.class.getResource("/imagen/depositarSobres.png")));
		contentPane.add(btnDepositar);
	}
	//post: crea el boton Transferir
	public void crearBotonTransferir(){
		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			//post: crea una ventana Transferir
			public void actionPerformed(ActionEvent e) {
				Transferir nuevaVentana = new Transferir();
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
	//post: crea el boton eliminar transferencia
	public void crearBotonEliminarTransferencia(){
		JButton btnEliminarTransferencia = new JButton("Eliminar Transferencia");
		btnEliminarTransferencia.addActionListener(new ActionListener() {
			//post: elimina la ultima transferencia realizada
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
		btnEliminarTransferencia.setBounds(403, 183, 166, 23);
		contentPane.add(btnEliminarTransferencia);
		
	}
	public void crearEImplementarBotonUltimosMovimientos(){
	}
	//post: crea el boton Atras
	public void crearEImplementarBotonAtras(){
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.addActionListener(new ActionListener() {
			//post: crea  la ventana SeleccionarCuenta
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
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setBackground(new Color(255, 102, 51));
		btnAtras.setBounds(480, 408, 89, 23);
		contentPane.add(btnAtras);
	}
	public void crearEImplementarBotonObtenerSaldo(){
		JButton btnObtenerSaldo = new JButton("Obtener Saldo");
		btnObtenerSaldo.addActionListener(new ActionListener() {
			//post: devuevlve el saldo del cliente
			public void actionPerformed(ActionEvent e) {
				double saldo = clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo();
				double descubierto = clienteActual.obtenerTarjeta().obtenerCuenta().obtenerDescubierto();
				saldo=  Math.round(saldo * 100);
				saldo=saldo/100;
				JOptionPane.showMessageDialog(null,"Usted posee $"+saldo+" en su cuenta \n con un descubierto de: " + descubierto);
			}
		});
		btnObtenerSaldo.setForeground(Color.BLACK);
		btnObtenerSaldo.setBackground(new Color(255, 102, 51));
		btnObtenerSaldo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnObtenerSaldo.setBounds(398, 138, 171, 23);
		contentPane.add(btnObtenerSaldo);
	}
	//post: crea el boton comprar dolares
	public void crearEImplementarBotonComprarDolares(){
		JButton btnComprarDolares = new JButton("Comprar Dolares");
		btnComprarDolares.addActionListener(new ActionListener() {
			//post: crea la ventana comprarDolares
			public void actionPerformed(ActionEvent e) {
				ComprarDolares nuevaVentana= new ComprarDolares();
				nuevaVentana.setVisible(true);
				dispose();
				
			}
		});
		btnComprarDolares.setForeground(Color.BLACK);
		btnComprarDolares.setBackground(new Color(255, 102, 51));
		btnComprarDolares.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnComprarDolares.setBounds(10, 125, 161, 23);
		contentPane.add(btnComprarDolares);
	}
	//post: crea el boton retirar efectivo
	public void crearEImplementarBotonRetirarEfectivo(){
		JButton btnRetirarEfectivo = new JButton("Retirar Efectivo");
		btnRetirarEfectivo.addActionListener(new ActionListener() {
			//post: crea la ventana Retirar efectivo
			public void actionPerformed(ActionEvent e) {
				RetirarEfectivo a = new RetirarEfectivo();
				a.setVisible(true);
				ocultar();
			}
		});
		btnRetirarEfectivo.setBounds(388, 280, 170, 64);
		btnRetirarEfectivo.setIcon(new ImageIcon(CajaDeAhorroEnPesos.class.getResource("/imagen/dispensadorDeDinero.png")));
		contentPane.add(btnRetirarEfectivo);
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
		lblRetirarEfectivo.setBounds(373, 246, 196, 35);
		contentPane.add(lblRetirarEfectivo);
	}
	//post: crea el titulo seleccione operacion
	public void crearTextoSeleccioneUnaOperacion(){
		JLabel lblTitulo = new JLabel("Seleccione Una Operacion");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 102, 51));
		lblTitulo.setBounds(148, 35, 287, 23);
		contentPane.add(lblTitulo);
	}
	//post: crea el boton obtener saldo
	//post: crea el fondo de la ventana
	public void establecerFondo(){
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CuentaCorriente.class.getResource("/imagen/fondo_2.jpg")));
		lblFondo.setBounds(-132, -38, 880, 500);
		contentPane.add(lblFondo);
	}
	//post: guarda en clienteActual el clienteActual de SeleccionarCuenta
	public void pasarDeSeleccionarCuentaACuentaCorriente(){
		clienteActual=SeleccionarCuenta.clienteActual;
	}
	//post: oculta la ventana Actual
	public void ocultar(){
		this.setVisible(false);
	}
}
