package interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import CodigoFuente.Cliente;
import Exceptions.ValorInvalido;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class CajaDeAhorroEnDolares extends JFrame {

	public Cliente clienteActual;
	private JPanel contentPane;


	public CajaDeAhorroEnDolares() {
		
		pasarDeSeleccionarCajaDeAhorroEnDolares();
		
		crearVentanaCajaDeAhorroEnDolares();
		
		crearEImplementarBotonUltimosMovimientos();
		
		crearEImplementarBotonAtras();
		
		crearEImplementarBotonObtenerSaldo();
		
		crearEImplementarBotonDepositar();
		
		crearTextoSeleccioneUnaOperacion();
		
		crearTextoDeposito();
		
		establecerFondo();
	}
	//post: crea la ventana caja de ahorro en dolares
	public void crearVentanaCajaDeAhorroEnDolares(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(CajaDeAhorroEnDolares.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el boton Ultimos movimientos
	public void crearEImplementarBotonUltimosMovimientos(){
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
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAtras.setBackground(new Color(255, 102, 51));
		btnAtras.setBounds(466, 413, 89, 23);
		contentPane.add(btnAtras);
	}
	//post: crea el boton de obtener saldo
	public void crearEImplementarBotonObtenerSaldo(){
		JButton btnObtenerSaldo = new JButton("Obtener Saldo");
		btnObtenerSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double saldo= clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo();
				saldo=  Math.round(saldo * 100);
				saldo=saldo/100;
				JOptionPane.showMessageDialog(null,"Usted posee U$D "+saldo+" Dolares en su cuenta");	
			}
		});
		btnObtenerSaldo.setForeground(Color.BLACK);
		btnObtenerSaldo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnObtenerSaldo.setBackground(new Color(255, 102, 51));
		btnObtenerSaldo.setBounds(415, 124, 155, 23);
		contentPane.add(btnObtenerSaldo);
	}
	
	
	//post: crea el boton depositar
	public void crearEImplementarBotonDepositar(){
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			//post crea una nueva ventana ranuraDeDeposito
			public void actionPerformed(ActionEvent e) {
				RanuraDeDeposito nuevaVentana = new RanuraDeDeposito();
				nuevaVentana.asignarTipoDeCuenta(2);
				nuevaVentana.setVisible(true);
				dispose();
				
				
			}
		});
		
		btnDepositar.setBounds(10, 402, 350, 47);
		btnDepositar.setIcon(new ImageIcon(CajaDeAhorroEnPesos.class.getResource("/imagen/depositarSobres.png")));
		contentPane.add(btnDepositar);
	}
	//post: crea el texti deposito
		public void crearTextoDeposito(){
			JLabel lblDeposito = new JLabel("Deposito:");
			lblDeposito.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblDeposito.setForeground(new Color(255, 102, 51));
			lblDeposito.setBounds(10, 356, 106, 35);
			contentPane.add(lblDeposito);
		}
	//post: crea un texto selecciona una operacion
	public void crearTextoSeleccioneUnaOperacion(){

		JLabel lblTitulo = new JLabel("Seleccione Una Operacion");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 102, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitulo.setBounds(142, 28, 287, 23);
		contentPane.add(lblTitulo);
	}
	//post: crea el fondo de la ventana
	public void establecerFondo(){
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CajaDeAhorroEnDolares.class.getResource("/imagen/fondo_2.jpg")));
		lblNewLabel.setBounds(-158, 0, 780, 462);
		contentPane.add(lblNewLabel);
	}
	//post: guarda en clienteActual el ClienteActual de SeleccionarCuenta
	public void pasarDeSeleccionarCajaDeAhorroEnDolares(){
		clienteActual=SeleccionarCuenta.clienteActual;
	}

}
