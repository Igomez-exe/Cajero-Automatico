package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import CodigoFuente.Banco;
import CodigoFuente.Cliente;
import CodigoFuente.Cuenta;
import CodigoFuente.Movimiento;
import CodigoFuente.Tarjeta;
import CodigoFuente.UltimosMovimientos;
import Exceptions.ValorInvalido;
import Lectores.LectorDeCuenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SeleccionarCuenta extends JFrame {
	public static List<Cliente> listaDeClientes = new LinkedList<Cliente>();
	public static Cliente clienteActual;
	private JPanel contentPane;
	public static UltimosMovimientos operaciones = new UltimosMovimientos();
	private int transacciones=0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarCuenta frame = new SeleccionarCuenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @throws ValorInvalido
	 * @throws CantidadDeDigitosIncorrectosException
	 * @throws FileNotFoundException
	 */
	
	public SeleccionarCuenta() throws FileNotFoundException,
			 ValorInvalido {
		pasarDatosDeValidacionDeTarjetaASeleccionarCuenta();
		
		clienteActual();
		
		crearVentanaSeleccionarCuenta();
		
		crearBotonIngresarOtraTarjeta();

		crearEImplementarBotonCajaDeAhorroEnPesos();

		crearEImplementarBotonCajaDeAhorroEnDolares();

		crearEImplementarBotonCuentaCorriente();

		crearTextoSeleccioneTipoDeCuenta();
		
		cearEImplementarBotonUltimosMovimientos();

		establecerFondo();
	}
	//post: crea la ventana Seleccionar Cuenta
	public void crearVentanaSeleccionarCuenta(){
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				SeleccionarCuenta.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el boton UltimosMovimientos
	public void  cearEImplementarBotonUltimosMovimientos(){
		JButton btnUltimosMovimientos = new JButton("Ultimos Movimientos\r\n");
		btnUltimosMovimientos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println(operaciones.obtenerRegistro());
				try {
					operaciones.escribirTxt(clienteActual.obtenerCuit());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUltimosMovimientos.setForeground(Color.BLACK);
		btnUltimosMovimientos.setBackground(new Color(255, 102, 51));
		btnUltimosMovimientos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnUltimosMovimientos.setBounds(392, 177, 174, 23);
		contentPane.add(btnUltimosMovimientos);
	}
	//post: crea el boton Caja de ahorro en pesos
	public void crearEImplementarBotonCajaDeAhorroEnPesos(){
		JButton btnCajaDeAhorrosEnPesos = new JButton("Caja De Ahorro En Pesos");
		CajaDeAhorroEnPesos ventanaCajaDeAhorroEnPesos = new CajaDeAhorroEnPesos();
		btnCajaDeAhorrosEnPesos.addActionListener(new ActionListener() {
			//post: verifica si el cliente tiene cuenta de tipo 01
			public void actionPerformed(ActionEvent e) {
				
				//VERIFICAR QUE SEA DE TIPO 01
				if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerTipo().equals("01")){
					ventanaCajaDeAhorroEnPesos.setVisible(true);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "La tarjeta ingresada no posee Caja de ahorro en pesos");
				}				
			}
		});
		btnCajaDeAhorrosEnPesos.setForeground(Color.BLACK);
		btnCajaDeAhorrosEnPesos.setBackground(new Color(255, 102, 51));
		btnCajaDeAhorrosEnPesos.setFont(new Font("Tahoma", Font.BOLD| Font.ITALIC, 10));
		btnCajaDeAhorrosEnPesos.setBounds(20, 235, 174, 23);
		contentPane.add(btnCajaDeAhorrosEnPesos);
	}
	public void crearBotonIngresarOtraTarjeta(){
		JButton btnIngresarOtraTarjeta = new JButton("Ingresar Otra Tarjeta\r\n");
		btnIngresarOtraTarjeta.addActionListener(new ActionListener() {
			VentanaValidacionDeTarjeta nuevaVentana ;
			public void actionPerformed(ActionEvent e) {
				try {
					nuevaVentana= new VentanaValidacionDeTarjeta();
					nuevaVentana.setVisible(true);
					dispose();
				} catch (FileNotFoundException
						| ValorInvalido e1) {
					e1.printStackTrace();
				}
			}
		});
		btnIngresarOtraTarjeta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnIngresarOtraTarjeta.setBackground(new Color(255, 102, 51));
		btnIngresarOtraTarjeta.setForeground(Color.BLACK);
		btnIngresarOtraTarjeta.setBounds(390, 235, 176, 23);
		contentPane.add(btnIngresarOtraTarjeta);
		
	}
	//post: crea el boton caja de ahorro en dolares
	public void crearEImplementarBotonCajaDeAhorroEnDolares(){
		JButton btnCajaDeAhorroEnDolares = new JButton(
				"Caja De Ahorros En Dolares");
		CajaDeAhorroEnDolares ventanaCajaDeAhorroEnDolares = new CajaDeAhorroEnDolares();
		btnCajaDeAhorroEnDolares.addActionListener(new ActionListener() {
			//post: verifica si el cliente tiene cuenta de tipo 03
			public void actionPerformed(ActionEvent e) {
				
				
				//VERIFICAR Q SEA DE TIPO 03
				if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerTipo().equals("03")){
					ventanaCajaDeAhorroEnDolares.setVisible(true);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "La tarjeta ingresada no posee Caja de ahorro en dolares");
				}			
				
			}
		});
		btnCajaDeAhorroEnDolares.setForeground(Color.BLACK);
		btnCajaDeAhorroEnDolares.setBackground(new Color(255, 102, 51));
		btnCajaDeAhorroEnDolares.setFont(new Font("Tahoma", Font.BOLD
				| Font.ITALIC, 10));
		btnCajaDeAhorroEnDolares.setBounds(20, 293, 174, 23);
		contentPane.add(btnCajaDeAhorroEnDolares);
	}
	//post: crea el boton cuenta corriente
	public void crearEImplementarBotonCuentaCorriente(){
		JButton btnCuentaCorriente = new JButton("Cuenta Corriente");
		CuentaCorriente ventanaCuentaCorriente = new CuentaCorriente();
		btnCuentaCorriente.addActionListener(new ActionListener() {
			//post: verifica si el cliente tiene cuenta de tipo 02
			public void actionPerformed(ActionEvent e) {
				
				
				// verificarQUE SEA DE TIPO 02
				if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerTipo().equals("02")){
					ventanaCuentaCorriente.setVisible(true);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "La tarjeta ingresada no posee cuentaCorriente");
					
				}

			}
		});
		btnCuentaCorriente.setBackground(new Color(255, 102, 51));
		btnCuentaCorriente.setForeground(Color.BLACK);
		btnCuentaCorriente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC,
				10));
		btnCuentaCorriente.setBounds(20, 177, 174, 23);
		contentPane.add(btnCuentaCorriente);
	}
	//post: crea el texto selecciono tipo de cuenta
	public void crearTextoSeleccioneTipoDeCuenta(){
		JLabel lblTitulo = new JLabel("Seleccione Su Tipo De Cuenta\r\n");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitulo.setBackground(Color.ORANGE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 102, 51));
		lblTitulo.setBounds(117, 21, 362, 38);
		contentPane.add(lblTitulo);
	}
	
	
	//post: crea el fondo de la ventana
	public void establecerFondo(){
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setHorizontalAlignment(SwingConstants.LEFT);
		lblFondo.setIcon(new ImageIcon(SeleccionarCuenta.class
				.getResource("/imagen/fondo_2.jpg")));
		lblFondo.setBounds(-145, 0, 772, 478);
		contentPane.add(lblFondo);
	}
	//post: guarda el cliente actual como atributo
	public void clienteActual() throws FileNotFoundException, ValorInvalido{
		Iterator<Cliente> iterador =listaDeClientes.iterator();
		 Cliente cliente;
		boolean encontrado=false;
		while(!encontrado&& iterador.hasNext()){
			cliente=iterador.next();
			if(cliente.obtenerTarjeta().obtenerNumeroDeTarjeta().equals(VentanaValidacionDeTarjeta.numeroDeTarjeta)){
				encontrado=true;
				clienteActual= cliente;
			}
		}
	}
	//post: guarda en listaDeClientes la listaDeClientes de ventanaValidacionDeTarjeta
	public void pasarDatosDeValidacionDeTarjetaASeleccionarCuenta(){
		if(transacciones==0){
			transacciones+=1;
			listaDeClientes=VentanaValidacionDeTarjeta.listaDeClientes;	
		}
	}
}
