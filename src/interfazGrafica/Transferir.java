package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Toolkit;

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

public class Transferir extends JFrame {

	private JPanel contentPane;
	private JTextField textAlias;
	public Cliente clienteActual;
	private JTextField textMonto;
	private String alias="";
	private UltimosMovimientos operaciones = new UltimosMovimientos();
	private double monto=0;
	private int cuenta=0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transferir frame = new Transferir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Transferir() {
		pasarDatosDeSeleccionarCuentaATransferir();
		crearVentana();
		crearCampoDeTextoDelAlias();
		crearIngreseAliasDeDestinoLbl();
		crearEjemplo();
		crearMontoLbl();
		crearCampoDeTextoMonto();
		crearEjemploDeMonto();
		crearBotonAceptar();
		crearBotonCancelar();
		crearFondo();
		
	}
	//post: crea el fondo de pantalla
	public void  crearFondo(){
		JLabel lblFondo = new JLabel("");
		lblFondo.setBackground(Color.BLACK);
		lblFondo.setIcon(new ImageIcon(Transferir.class.getResource("/imagen/fondo_2.jpg")));
		lblFondo.setBounds(-152, 0, 803, 462);
		contentPane.add(lblFondo);	
	}
	//post crea una ventana
	public void crearVentana(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(Transferir.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post : crea el campo de texto del alias
	public void crearCampoDeTextoDelAlias(){
		textAlias = new JTextField();
		textAlias.setFont(new Font("Tahoma", Font.BOLD, 15));
		textAlias.setForeground(new Color(255, 102, 51));
		textAlias.setBackground(Color.BLACK);
		textAlias.setBounds(347, 122, 237, 20);
		contentPane.add(textAlias);
		textAlias.setColumns(10);
		
		
	}
	//post: crea el leable de alias de destino
	public void crearIngreseAliasDeDestinoLbl(){
		JLabel lbAlias = new JLabel("Ingrese el alias del Destinatario:");
		lbAlias.setForeground(new Color(255, 102, 51));
		lbAlias.setBackground(Color.WHITE);
		lbAlias.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lbAlias.setHorizontalAlignment(SwingConstants.CENTER);
		lbAlias.setBounds(10, 110, 338, 43);
		contentPane.add(lbAlias);
		
	}
	//post crea un ejemplo del alias
	public  void crearEjemplo(){
		JLabel lblEjemplo = new JLabel("Ej: lapiz.zelda.asado\r\n");
		lblEjemplo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEjemplo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEjemplo.setForeground(Color.WHITE);
		lblEjemplo.setBounds(331, 154, 186, 31);
		contentPane.add(lblEjemplo);
	}
	//post crear un leabel del monto
	public void crearMontoLbl(){

		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setForeground(new Color(255, 102, 51));
		lblMonto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblMonto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonto.setBounds(10, 240, 91, 20);
		contentPane.add(lblMonto);
	}
	//post: crea un campo de Texto del monto
	public void crearCampoDeTextoMonto(){
		textMonto = new JTextField();
		textMonto.setFont(new Font("Tahoma", Font.BOLD, 15));
		textMonto.setForeground(new Color(255, 102, 51));
		textMonto.setBackground(Color.BLACK);
		textMonto.setBounds(105, 240, 186, 24);
		contentPane.add(textMonto);
		textMonto.setColumns(10);
		
		
	}
	//post: crea un ejemplo del monto a ingresar
	public void crearEjemploDeMonto(){

		JLabel lblEjemploMonto = new JLabel("Ej: 575\r\n");
		lblEjemploMonto.setHorizontalAlignment(SwingConstants.LEFT);
		lblEjemploMonto.setForeground(Color.WHITE);
		lblEjemploMonto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEjemploMonto.setBounds(104, 274, 65, 31);
		contentPane.add(lblEjemploMonto);
	}
	//post:crea el boton aceptar
	public void crearBotonAceptar(){
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			//post: realiza una transferencia
			public void actionPerformed(ActionEvent e) {
				try {
					if(cuenta==0){
						alias=textAlias.getText();
						if(alias.length()==0){
							JOptionPane.showMessageDialog(null, "Por favor ingrese un alias para continuar con la operacion");
						}
						monto=pasarTextoANumero(textMonto.getText());
					
						transferirDineroDesdeCuentaCorriente(alias,monto);
						Movimiento unMovimiento = new Movimiento(clienteActual,"Transferencia");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
						
				}else{
					alias=textAlias.getText();
					if(alias.length()==0){
						JOptionPane.showMessageDialog(null, "Por favor ingrese un alias para continuar con la operacion");
					}
					monto=pasarTextoANumero(textMonto.getText());
				
					transferirDineroDesdeCajaDeAhorroEnPesos(alias,pasarTextoANumero(textMonto.getText()));
					Movimiento unMovimiento = new Movimiento(clienteActual,"Transferencia");
					operaciones.asigarCliente(clienteActual);
					operaciones.guardarMovimientoEnLista(unMovimiento);
					
					}
				} catch (ValorInvalido
						|CuentaInexistente | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(255, 102, 51));
		btnAceptar.setBounds(83, 404, 114, 36);
		contentPane.add(btnAceptar);
	}
	//post crea el boton cancelar
	public void crearBotonCancelar(){
		JButton btnCancelar = new JButton("CANCELAR\r\n");
		btnCancelar.addActionListener(new ActionListener() {
			//post: vuelve a la ventana anterior
			public void actionPerformed(ActionEvent e) {
				if(cuenta==0){
					CuentaCorriente nuevaVentana= new CuentaCorriente();
					nuevaVentana.setVisible(true);
					dispose();
				}else{
					CajaDeAhorroEnPesos ventana=new CajaDeAhorroEnPesos();
					ventana.setVisible(true);
					dispose();
				}
			}
		});
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBackground(new Color(255, 102, 51));
		btnCancelar.setBounds(395, 404, 114, 36);
		contentPane.add(btnCancelar);
	}
	//Post: guarda en cliente actual el valor del cliente actual de la clase Seleccionar Cuenta
	public  void pasarDatosDeSeleccionarCuentaARetirarEfectivoCC(){
		clienteActual=SeleccionarCuenta.clienteActual;
	}
	//post: cambia String a double el texto indicado
	public double pasarTextoANumero(String texto){
		if(texto.length()==0){
			JOptionPane.showMessageDialog(null, "Ingrese un monto para poder realizar la operacion");
		}
		monto=Double.parseDouble(texto);
		return monto;
	}
	//pre: alias debe tener menos de 20 caracteres y cantidad debe ser mayor a 0
	//post: transfiere dinero desde la cuenta corriente a la cuenta con el alias designado 
	public void transferirDineroDesdeCuentaCorriente(String alias,double cantidad) throws ValorInvalido, FileNotFoundException, CuentaInexistente{
		
		if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo() < 0){
			clienteActual.transferirMontoConDescubiertoCuentaCorriente(alias, cantidad);
			SeleccionarCuenta nuevaVentana;
			nuevaVentana = new SeleccionarCuenta();
			nuevaVentana.setVisible(true);
			dispose();
		}else{
			clienteActual.transferirMontoConCuentaCorriente(alias, cantidad);
			SeleccionarCuenta nuevaVentana;
			nuevaVentana = new SeleccionarCuenta();
			nuevaVentana.setVisible(true);
			dispose();
		}
	}
	//pre: alias debe tener menos de 20 caracteres y cantidad debe ser mayor a 0
		//post: transfiere dinero desde la caja de ahorro en pesos a la cuenta con el alias designado 
	public void transferirDineroDesdeCajaDeAhorroEnPesos(String alias,double cantidad) throws ValorInvalido, FileNotFoundException, CuentaInexistente{
		
		clienteActual.transferirMontoConCajaDeAhorroEnPesos(alias, cantidad);
		SeleccionarCuenta nuevaVentana;
		nuevaVentana = new SeleccionarCuenta();
		nuevaVentana.setVisible(true);
		dispose();
	}
	//cuenta=0 accede a metodos de cuetna Corriente
	//cuenta=1 accede a metodos de caja de ahorro en pesos
	public void asignarTipoDeCenta(int cuenta){
		this.cuenta=cuenta;
		
	}
	// guarda en el cliente actual el clienteActual de SeleccionarCuenta
	// y guarda como atributo operaciones de SeleccionarCUenta
	public void pasarDatosDeSeleccionarCuentaATransferir(){
		clienteActual=SeleccionarCuenta.clienteActual;
		operaciones=SeleccionarCuenta.operaciones;
	}
}
