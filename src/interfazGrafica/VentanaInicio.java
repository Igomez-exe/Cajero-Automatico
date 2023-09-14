package interfazGrafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import CodigoFuente.Cliente;
import CodigoFuente.Cuenta;
import CodigoFuente.Tarjeta;
import Exceptions.ValorInvalido;
import Lectores.LectorDeClientes;
import Lectores.LectorDeCuenta;
import Lectores.LectorDeTarjetas;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	
	public static List<Cliente> listaDeClientes = new LinkedList<Cliente>();
	private  List<Tarjeta> listaDeTarjetas = new LinkedList<Tarjeta>();
	private List<Cuenta> listaDeCuentas = new LinkedList<Cuenta>();
	
	private final Action action = new SwingAction();

	
	public VentanaInicio() throws FileNotFoundException, ValorInvalido {
		crearListaClientes();
		crearListaTarjetas();
		crearListasCuentas();
		agregarTarjetasALosClientes();	
		agregarCuentasATarjetas();
		
		crearVentanaInicio();
		crearLetreroBienvenido();
		crearEImplementarBotonContinuar();
		establecerFondo();

	}
	//post: crea  ventana
	private void crearVentanaInicio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VentanaInicio.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el texto bienvenido
	private void crearLetreroBienvenido() {
		JLabel lblTitulo = new JLabel("!!BIENVENIDO!!\r\n");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setIgnoreRepaint(true);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(227, 38, 157, 29);
		contentPane.add(lblTitulo);
	}
	//post: crea el fonto de la ventana
	private void establecerFondo() {

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(-18, -99, 643, 551);
		lblFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondo.setIcon(new ImageIcon(VentanaInicio.class
				.getResource("/imagen/fondo_1.jpg")));
		contentPane.add(lblFondo);
	}
	//post: crea el boton Continuar
	private void crearEImplementarBotonContinuar() throws FileNotFoundException,  ValorInvalido {
		JButton btnContinuar = new JButton("continuar");
		btnContinuar.setForeground(new Color(0, 0, 0));
		btnContinuar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnContinuar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContinuar.setAction(action);
		btnContinuar.addActionListener(new ActionListener() {
			//post: crea una ventana VentanaValidacionDeTarjeta
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaValidacionDeTarjeta nuevaVentana;
					nuevaVentana = new VentanaValidacionDeTarjeta();
					nuevaVentana.setVisible(true);
					dispose();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ValorInvalido e1) {
				
					e1.printStackTrace();
				}	
			}
		});
		btnContinuar.setBackground(new Color(204, 153, 0));
		btnContinuar.setBounds(242, 300, 117, 28);
		contentPane.add(btnContinuar);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Continuar\r\n");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
	//post: lee y guarda en listaDeClientes los datos obtenidos del nuevoLectorDeClientes
	private void crearListaClientes() throws FileNotFoundException, ValorInvalido{
		LectorDeClientes nuevoLectorDeClientes = new LectorDeClientes();
		nuevoLectorDeClientes.lectorDeClientes();
		listaDeClientes = nuevoLectorDeClientes.obtenerListasDeClientes();
	}
	//post: lee y guarda en listaDeClientes los datos obtenidos del nuevoLectorDeClientes
	private void crearListaTarjetas() throws FileNotFoundException, ValorInvalido{
		LectorDeTarjetas nuevoLectorDeTarjetas = new LectorDeTarjetas();
		nuevoLectorDeTarjetas.lectorDeTarjeta();
		listaDeTarjetas = nuevoLectorDeTarjetas.obtenerListaDeTarjetas();
	}
	//post: lee y guarda en listaDeClientes los datos obtenidos del nuevoLectorDeClientes
	public void crearListasCuentas() throws FileNotFoundException, ValorInvalido{
		LectorDeCuenta unLector = new LectorDeCuenta();
		unLector.lectorDatosDeCuentas();
		listaDeCuentas = unLector.obtenerListaDeCuentas();
	}
	//post: agrega  tarjetas  a sus clientes correspondientes
	public void agregarTarjetasALosClientes() {
		Iterator<Cliente> iteraClientes = listaDeClientes.iterator();
		Iterator<Tarjeta> iteraTarjetas = listaDeTarjetas.iterator();

		while (iteraClientes.hasNext() && iteraTarjetas.hasNext()) {
			Tarjeta tarjeta = iteraTarjetas.next();
			Cliente cliente = iteraClientes.next();

			if (cliente.obtenerCuit().equals(tarjeta.obtenerCuit())) {
				cliente.asignarTarjeta(tarjeta);
			}
		}
	}
	//post: agrega las cuentas  a las tarjetas correspondientes
	public void agregarCuentasATarjetas() {
		Iterator<Cliente> iteraClientes = listaDeClientes.iterator();
		Iterator<Cuenta> iteraCuentas = listaDeCuentas.iterator();

		while (iteraClientes.hasNext() && iteraCuentas.hasNext()) {
			Cliente nuevoCliente = iteraClientes.next();
			Cuenta nuevaCuenta = iteraCuentas.next();

			if (nuevoCliente.obtenerAlias().equals(nuevaCuenta.obtenerAlias())) {
				nuevoCliente.obtenerTarjeta().asignarCuenta(nuevaCuenta);
			}

		}
	
	}
	
}
