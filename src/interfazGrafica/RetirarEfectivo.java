package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import CodigoFuente.Banco;
import CodigoFuente.Cliente;
import CodigoFuente.Movimiento;
import CodigoFuente.UltimosMovimientos;
import Exceptions.ValorInvalido;

public class RetirarEfectivo extends JFrame {
	
	public  Cliente clienteActual;
	private JPanel contentPane;
	private int cuenta=0;
	private int monto=0;
	private UltimosMovimientos operaciones = new UltimosMovimientos();
	private JTextField textCampoMonto;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetirarEfectivo frame = new RetirarEfectivo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public RetirarEfectivo() {
		
		pasarDatosDeSeleccionarCuentaARetirarEfectivoCC();
		
		crearVentanaRetirarEfectivo();
		
		crearCampoDeTextoMonto();
		
		crearLblTotal();
		
		crearEImplementarBotonAceptar();
		
		CrearEImplementarBotonCancelar();
		
		pasarDatosDeSeleccionarCuentaARetirarEfectivoCC();
		
		crearEImplementarBotonCien();
		
		crearEImplementarBotonQuinientos();
		
		crearEImplementarBotonMil();
		
		crearFondo();
		
		
	}
	//post: creea la ventana  RetirarEfectivo
	public void crearVentanaRetirarEfectivo(){
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RetirarEfectivo.class.getResource("/imagen/mm.png")));
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}	
	//post: crea texto Total:
	public void crearLblTotal(){
		
		JLabel lblTotal = new JLabel("TOTAL :");
		lblTotal.setForeground(new Color(255, 102, 51));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTotal.setBackground(new Color(255, 102, 51));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(166, 11, 95, 22);
		contentPane.add(lblTotal);
	}
	//post: crea campo de texto Monto
	public void crearCampoDeTextoMonto(){
		textCampoMonto = new JTextField();
		textCampoMonto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textCampoMonto.setForeground(new Color(255, 102, 51));
		textCampoMonto.setBackground(Color.BLACK);
		textCampoMonto.setBounds(271, 11, 214, 22);
		contentPane.add(textCampoMonto);
		textCampoMonto.setColumns(10);
		textCampoMonto.setEnabled(false);
	}
	//post: crea el boton aceptar
	public void crearEImplementarBotonAceptar(){
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			//post: retira efectivo de la cuenta 
			public void actionPerformed(ActionEvent e) {		                   
				try {
					if(cuenta==0){
						
						retirarDeCuentaCorriente();
						Movimiento unMovimiento = new Movimiento(clienteActual,"Retiro Efectivo");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
						
						dispose();
						
					}else{
						retirarDeCajaDeAhorroEnPesos();
						Movimiento unMovimiento = new Movimiento(clienteActual,"Retiro Efectivo");
						operaciones.asigarCliente(clienteActual);
						operaciones.guardarMovimientoEnLista(unMovimiento);
						dispose();
					}
					
				} catch (ValorInvalido | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnAceptar.setBackground(new Color(255, 102, 51));
		btnAceptar.setBounds(103, 403, 108, 35);
		contentPane.add(btnAceptar);
	}
	//post: crea el fondo de la ventana
	public void crearFondo(){
		JLabel lbl1000 = new JLabel("");
		lbl1000.setIcon(new ImageIcon(RetirarEfectivo.class.getResource("/imagen/fnaticventana.jpg")));
		lbl1000.setBackground(Color.BLACK);
		lbl1000.setBounds(-150, 0, 844, 461);
		contentPane.add(lbl1000);
	}
	//post: crea el boton cancelar
	public void  CrearEImplementarBotonCancelar(){
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			//post: crea una ventana CuentaCorriente
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
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBackground(new Color(255, 102, 51));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnCancelar.setBounds(377, 403, 108, 35);
		contentPane.add(btnCancelar);
	}
		
	//post: crea el boton mil
	private void crearEImplementarBotonMil() {
		JButton btn1000 = new JButton("1000");
		btn1000.addActionListener(new ActionListener() {
			//post: suma el valor actual+1000 y lo coloca en el campo de texto
			public void actionPerformed(ActionEvent e) {
				int largo= textCampoMonto.getText().length();
				
				if (largo>0){
					 monto=pasarTextoANumero(textCampoMonto.getText()) + 1000;
					textCampoMonto.setText(monto+"");
				}else{	
					textCampoMonto.setText("1000");
					monto=pasarTextoANumero(textCampoMonto.getText());
				}
			}

		});
		btn1000.setForeground(Color.BLACK);
		btn1000.setBackground(new Color(255, 102, 51));
		btn1000.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btn1000.setBounds(10, 117, 135, 28);
		contentPane.add(btn1000);
	}
	//post: crea el boton quinientos
	private void crearEImplementarBotonQuinientos() {
		JButton btn500 = new JButton("500");
		btn500.setForeground(Color.BLACK);
		btn500.addActionListener(new ActionListener() {
			//post: suma el valor actual+500 y lo coloca en el campo de texto
			public void actionPerformed(ActionEvent e) {
				int largo= textCampoMonto.getText().length();
				
				if (largo>0){	
					monto=pasarTextoANumero(textCampoMonto.getText()) + 500;
					textCampoMonto.setText(monto+"");	
				}else{
					textCampoMonto.setText("500");
					monto=pasarTextoANumero(textCampoMonto.getText());

				}
				
			}
		});
		btn500.setForeground(Color.BLACK);
		btn500.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btn500.setBackground(new Color(255, 102, 51));
		btn500.setBounds(10, 161, 135, 28);
		contentPane.add(btn500);
	}
	//post: crea el boton cien
	private void crearEImplementarBotonCien() {
		JButton btn100 = new JButton("100");
		btn100.setForeground(Color.BLACK);
		btn100.addActionListener(new ActionListener() {
			//post: suma el valor actual+100 y lo coloca en el campo de texto
			public void actionPerformed(ActionEvent e) {
				int largo= textCampoMonto.getText().length();
				
				if (largo>0){
					monto=pasarTextoANumero(textCampoMonto.getText()) + 100;
					textCampoMonto.setText(monto+"");
				}else{
					textCampoMonto.setText("100");
					monto=pasarTextoANumero(textCampoMonto.getText());

				}
			}
		});
		btn100.setForeground(Color.BLACK);
		btn100.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btn100.setBackground(new Color(255, 102, 51));
		btn100.setBounds(10, 211, 135, 28);
		contentPane.add(btn100);
	}
	//post: transforma el texto de String a double
	public int pasarTextoANumero(String texto){
		if(texto.length()==0){
			JOptionPane.showMessageDialog(null, "Ingrese un monto para poder realizar la operacion");
		}
		int monto;
		monto=Integer.parseInt(texto);
		return monto;
	}
	//post: crea una ventana SeleccionarCuenta
	public void crearVentana(){
			SeleccionarCuenta unaVentana;
			try {
				unaVentana = new SeleccionarCuenta();
				unaVentana.setVisible(true);
				dispose();
			} catch (FileNotFoundException
					| ValorInvalido e) {
				e.printStackTrace();
			}
		}
	//post: guarda en clienteActual el ClienteActual de SeleccionarCuenta
	public  void pasarDatosDeSeleccionarCuentaARetirarEfectivoCC(){
		clienteActual=SeleccionarCuenta.clienteActual;
		operaciones=SeleccionarCuenta.operaciones;
	}
	//post: retira el valor monto y crea una ventana SeleccionarCuenta
	public void retirarDeCuentaCorriente() throws ValorInvalido, FileNotFoundException{
		if(clienteActual.obtenerTarjeta().obtenerCuenta().obtenerSaldo() < 0  ){
			clienteActual.retirarEfectivoDeCuentaCorrienteConDesubierto(monto);
			crearVentana();
		}else{
			clienteActual.retirarEfectivoDeCuentaCorriente(monto);
			crearVentana();
			
		}
		
	}
	//post: retira el valor monto y crea una ventana SeleccionarCuenta
	public void retirarDeCajaDeAhorroEnPesos() throws ValorInvalido, FileNotFoundException {
		clienteActual.retirarEfectivoDeCajaDeAhorroEnPesos(monto);
		crearVentana();
		
	}
	
	//cuenta ==0 accede a metodos de cuetna Corriente
	//cuenta !=1 accede a metodos de caja de ahorro en pesos
	
	//post: guarda el valor de cuenta como atributo
	public void asignarTipoDeCuenta(int cuenta){
		this.cuenta=cuenta;
		
	}
	
}
