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

import javax.swing.JTextField;
import javax.swing.JButton;

import CodigoFuente.Cliente;
import CodigoFuente.Movimiento;
import CodigoFuente.UltimosMovimientos;
import Exceptions.CuentaInexistente;
import Exceptions.ValorInvalido;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ComprarDolares extends JFrame {

	private JPanel contentPane;
	private JTextField textMonto;
	private Cliente clienteActual;
	private UltimosMovimientos operaciones = new UltimosMovimientos();
	private double monto;
	private int cuenta = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComprarDolares frame = new ComprarDolares();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ComprarDolares() {
		pasarDatos();
		crearVentana();
		crearBotonAceptar();
		crearBotonCancelar();
		crearTextoDeMonto();
		crearMontoLbl();
		crearFondoLbl();
	}
	//post: crea el fondo de la ventana
	public void crearFondoLbl() {

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ComprarDolares.class
				.getResource("/imagen/fnaticventana.jpg")));
		lblFondo.setBounds(-151, 0, 783, 500);
		contentPane.add(lblFondo);
	}
	//post: crea el texto 
	public void crearMontoLbl() {
		JLabel lblMonto = new JLabel("Monto :");
		lblMonto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMonto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonto.setForeground(new Color(255, 102, 51));
		lblMonto.setBounds(23, 99, 101, 42);
		contentPane.add(lblMonto);
	}
	//post: crea la ventana
	public void crearVentana() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ComprarDolares.class.getResource("/imagen/mm.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea boton Aceptar
	public void crearBotonAceptar() {
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			//post: realiza la compra de dolares
			public void actionPerformed(ActionEvent e) {
				try {
					if (cuenta == 0) {
						pasarDeStringADouble(textMonto.getText());
						comprarDolaresConCuentaCorriente(monto);
						Movimiento unMovimiento = new Movimiento(clienteActual,"Compro dolares");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
						dispose();

					} else {
						pasarDeStringADouble(textMonto.getText());
						comprarDolaresConCajaDeAhorro(monto);
						Movimiento unMovimiento = new Movimiento(clienteActual,"Compro dolares");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
						dispose();
					}
				} catch (ValorInvalido |   IOException | CuentaInexistente e1) {
					e1.printStackTrace();

				}finally{
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
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAceptar.setBackground(new Color(255, 102, 51));
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setBounds(91, 421, 109, 30);
		contentPane.add(btnAceptar);
	}
	//post: crea el boton cancelar
	public void crearBotonCancelar() {

		JButton btnCancelar = new JButton("CANCELAR\r\n");
		btnCancelar.addActionListener(new ActionListener() {
			//post: crea la ventana Cuenta Corriente
			public void actionPerformed(ActionEvent e) {
				if(cuenta==0){
					CuentaCorriente nuevaVentana= new CuentaCorriente();
					nuevaVentana.setVisible(true);
					dispose();					
				}else{
					CajaDeAhorroEnPesos ventana= new CajaDeAhorroEnPesos();
					ventana.setVisible(true);
					dispose();
				}
				
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(255, 102, 51));
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBounds(377, 421, 109, 30);
		contentPane.add(btnCancelar);
	}
	//post: crea campo de texto del monto
	public void crearTextoDeMonto() {
		textMonto = new JTextField();
		textMonto.setFont(new Font("Tahoma", Font.BOLD, 15));
		textMonto.setForeground(new Color(255, 102, 51));
		textMonto.setBackground(Color.BLACK);
		textMonto.setBounds(122, 112, 185, 20);
		contentPane.add(textMonto);
		textMonto.setColumns(10);
	}
	//post: pasa el texto de tipo String a double
	public void pasarDeStringADouble(String texto) throws ValorInvalido {
		if(texto.length()==0){
			JOptionPane.showMessageDialog(null, "El monto esta vacio asigne un valor para continuar con al operacion");
			throw new ValorInvalido("El monto esta vacio asigne un valor para continuar con al operacion");
		}
		monto = Double.parseDouble(texto);
	}
	//post: guarda en clienteActual el ClienteActual de SeleccionarCuenta
	public void pasarDatos() {
		clienteActual = SeleccionarCuenta.clienteActual;
		operaciones=SeleccionarCuenta.operaciones;
	}
	//post: guarda el valor de cuenta como atributo
	public void asignarQueCuentaUsar(int cuenta){
		this.cuenta=cuenta;
		
	}
	//post: compra dolares con la CuentaCorriente
	public void  comprarDolaresConCuentaCorriente(double monto) throws ValorInvalido,  FileNotFoundException, CuentaInexistente{
		if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo() < 0){
			clienteActual.comprarDolaresConDescubiertoDeCuentaCorriente(monto);
	
		}else{
			clienteActual.comprarDolaresConCuentaCorriente(monto);
				
		}
	}
	//post: compra dolares con la cajaDeAhorro
	public void  comprarDolaresConCajaDeAhorro(double monto) throws ValorInvalido, FileNotFoundException,  CuentaInexistente{
		clienteActual.comprarDolaresConCajaDeAhorroEnPesos(monto);
	
	}
}
