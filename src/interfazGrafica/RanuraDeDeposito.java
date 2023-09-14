package interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CodigoFuente.Cliente;
import CodigoFuente.Movimiento;
import CodigoFuente.UltimosMovimientos;
import Exceptions.ValorInvalido;

public class RanuraDeDeposito extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMonto;
	private JRadioButton radioBoton;
	private JButton botonAceptar;
	private JButton botonAtras;
	private UltimosMovimientos operaciones = new UltimosMovimientos();
	private int cuenta = 0;
	private double monto = 0;
	public  Cliente clienteActual;

	//si es 0 abre la CC
	//si es 1 abre la CAP
	//si es 2 abre la CAUSD

	public RanuraDeDeposito() {
		
		crearVentana();
		crearRadioBotonDepositar();
		crearTextFieldParaDepositar();
		crearBotonAceptar();
		crearEImplementarBotonAtras();
		crearFondo();
		
	}
	//post: crea la ventana
	public void crearVentana(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(ComprarDolares.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el fondo de la ventana
	public void crearFondo(){
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBackground(Color.BLACK);
		lblFondo.setIcon(new ImageIcon(ComprarDolares.class.getResource("/imagen/fondo_2.jpg")));
		lblFondo.setBounds(-144, 0, 803, 462);
		contentPane.add(lblFondo);	
	}
	//post: crea el boton Depositar
	public void crearRadioBotonDepositar(){
		radioBoton = new JRadioButton("Depositar");
		radioBoton.addActionListener(new ActionListener() {
			int par=1;
			//post: activa el campo te texto de monto y el boton aceptar
			public void actionPerformed(ActionEvent e) {
				par+=1;
				if(par%2==0){
					textFieldMonto.setEnabled(true);
					botonAceptar.setEnabled(true);
				}else{
					textFieldMonto.setEnabled(false);
					botonAceptar.setEnabled(false);
				}
			}
		});
		radioBoton.setForeground(Color.BLACK);
		radioBoton.setFont(new Font("Tahoma", Font.BOLD, 12));
		radioBoton.setBackground(new Color(255, 102, 0));
		radioBoton.setBounds(49,142,140,31);
		contentPane.add(radioBoton);
		
		
	}
	//post: crea campo de texto Depositar
	public void crearTextFieldParaDepositar(){
		
		textFieldMonto = new JTextField();
		textFieldMonto.setEnabled(false);
		textFieldMonto.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldMonto.setForeground(new Color(255, 102, 51));
		textFieldMonto.setBackground(Color.BLACK);
		textFieldMonto.setBounds(50, 210, 258, 26);
		textFieldMonto.setColumns(10);
		contentPane.add(textFieldMonto);

	}
	//post: crea el boton aceptar
	public void crearBotonAceptar(){
		
		botonAceptar = new JButton("ACEPTAR");
		botonAceptar.addActionListener(new ActionListener() {
			//post: deposita el monto
			public void actionPerformed(ActionEvent e) {
				try {
					pasarDeStringANumero(textFieldMonto.getText());
					if(cuenta==0){
						pasarDeStringANumero(textFieldMonto.getText());
						depositarEnCuentaCorriente();
						Movimiento unMovimiento = new Movimiento(clienteActual,"Deposito");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
						dispose();
					}
					else if(cuenta==1){
						pasarDeStringANumero(textFieldMonto.getText());
						depositarEnCajaDeAhorroEnPesos();
						Movimiento unMovimiento = new Movimiento(clienteActual,"Deposito");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
						
						dispose();
						
					}else{
						pasarDeStringANumero(textFieldMonto.getText());
						depositarEnCajaDeAhorroEnDolares();
						Movimiento unMovimiento = new Movimiento(clienteActual,"Deposito");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
	
						dispose();
					}
				} catch ( ValorInvalido | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		botonAceptar.setEnabled(false);
		botonAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
		botonAceptar.setForeground(Color.BLACK);
		botonAceptar.setBackground(new Color(255, 102, 0));
		botonAceptar.setBounds(50, 374, 104, 29);
		contentPane.add(botonAceptar);
	}
	//post: crea boton atras
	public void crearEImplementarBotonAtras(){
		botonAtras = new JButton("ATRAS");
		botonAtras.addActionListener(new ActionListener() {
			//post: crea una ventana CuentaCorriente
			public void actionPerformed(ActionEvent e) {
				
				if(cuenta ==0){
					CuentaCorriente ventanaCuentaCorriente = new CuentaCorriente();
					ventanaCuentaCorriente.setVisible(true);
					dispose();
					
				}else if(cuenta == 1){
					CajaDeAhorroEnPesos ventanaCajaDeAhorroEnPesos = new CajaDeAhorroEnPesos();
					ventanaCajaDeAhorroEnPesos.setVisible(true);
					dispose();
				}else if(cuenta ==2){
					CajaDeAhorroEnDolares ventanaCajaDeAhorroEnDolares = new CajaDeAhorroEnDolares();
					ventanaCajaDeAhorroEnDolares.setVisible(true);
					dispose();
				}
			}
		});
		botonAtras.setFont(new Font("Tahoma", Font.BOLD, 12));
		botonAtras.setForeground(Color.BLACK);
		botonAtras.setBackground(new Color(255, 102, 0));
		botonAtras.setBounds(465, 372, 104, 31);
		contentPane.add(botonAtras);
	}
	//post: depostia en cuenta corriente el monto
	public void depositarEnCuentaCorriente() throws ValorInvalido, FileNotFoundException {
		pasarDatos();
		clienteActual.depositarEnCuentaCorriente(monto);
		SeleccionarCuenta nuevaVentana;
		nuevaVentana = new SeleccionarCuenta();
		nuevaVentana.setVisible(true);
		dispose();
		
	}
	//post:  deposita en caja de ahorro en pesos el monto
	public void depositarEnCajaDeAhorroEnPesos() throws ValorInvalido, FileNotFoundException {
		pasarDatos();
		clienteActual.depositarEnCajaDeAhorroEnPesos(monto);
		SeleccionarCuenta nuevaVentana;
		nuevaVentana = new SeleccionarCuenta();
		nuevaVentana.setVisible(true);
		dispose();
		
	}
	//post: deposita en caja de ahorroe en dolares el monto
	public void depositarEnCajaDeAhorroEnDolares() throws ValorInvalido, FileNotFoundException {
		pasarDatos();
		clienteActual.depositarEnCajaDeAhorroEnDolares(monto);
		SeleccionarCuenta nuevaVentana;
		nuevaVentana = new SeleccionarCuenta();
		nuevaVentana.setVisible(true);
		dispose();
		
	}
	//post: convierte el texto de string a double 
	public void pasarDeStringANumero(String texto) throws ValorInvalido{
		if(texto.length()==0){
		JOptionPane.showMessageDialog(null, "Ingrese un monto para seguir con la operacion");
		throw new ValorInvalido("Ingrese un monto para seguir con la operacion");
		}
		monto=Double.parseDouble(texto);
	}

	//si cuenta == 0 opera con la CC
	//si cuenta== 1 opera la CAP
	//si cuenta!=0 y !=1  opera la CAUSD
	
	//post: guarda el valor de cuenta como atributo
	public void asignarTipoDeCuenta(int cuenta){
		this.cuenta = cuenta;
	}
	//post: guarda en clienteActual el ClienteActual de SeleccionarCuenta
	public void pasarDatos(){
		clienteActual=SeleccionarCuenta.clienteActual;
		operaciones=SeleccionarCuenta.operaciones;
	}
}
