package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import CodigoFuente.Cliente;
import CodigoFuente.Tarjeta;
import Exceptions.AccesoBloqueado;
import Exceptions.ValorInvalido;
import Lectores.LectorDeClientes;
import Lectores.LectorDeTarjetas;

import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class VentanaValidacionDeTarjeta extends JFrame {

	private JPanel contentPane;
	private JLabel lblInserteSuTarjeta;
	private JLabel labelInserteSuPin;


	public JTextField textFieldNumeroDeTarjeta;
	public  JPasswordField passwordFieldPin;
	public JButton btnAceptar = new JButton("ACEPTAR");
	
	public static Cliente unCliente;
	public static String numeroDeTarjeta;
	public static List<Cliente> listaDeClientes = new LinkedList<Cliente>();
	public  String pinDeLaTarjeta;

	public int contador;
	public boolean teclearNumeros = false;


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaValidacionDeTarjeta frame = new VentanaValidacionDeTarjeta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @throws CantidadDeDigitosIncorrectosException
	 * @throws FileNotFoundException
	 * @throws ValorInvalido
	 */
	public VentanaValidacionDeTarjeta() throws FileNotFoundException,  
	ValorInvalido{
		
		pasarDatosDeInicioAValidacionDeTarjeta();

		crearVentanaValidacionDeTarjeta();

		crearEImplementarBotonAceptar();

		crearEImplementarBotonAceptarPin();

		crearTextFieldTarjeta();

		crearPasswordFieldPin();

		crearTextoInsertarNumTarjeta();

		crearTextoInsertarNumPin();

		crearBotonesTeclado();
		
		establecerFondo();

	}
	//post: crea una ventana validacion de tarjeta
	private void crearVentanaValidacionDeTarjeta() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VentanaValidacionDeTarjeta.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el fondo de la ventana
	private void establecerFondo() {
		JLabel label = new JLabel("");
		label.setBounds(-105, -56, 888, 544);
		label.setIcon(new ImageIcon(VentanaValidacionDeTarjeta.class
				.getResource("/imagen/Fondo_2.jpg")));
		contentPane.add(label);

	}
	//post: crea el boton aceptar
	private void crearEImplementarBotonAceptar() {

		btnAceptar.addActionListener(new ActionListener() {
			//post: verifica si el numero de tarjeta esta en la lista.
			// si existe se guarda el cliente como atributo
			public void actionPerformed(ActionEvent e) {
				Iterator<Cliente> iterador = listaDeClientes.iterator();

				String tarjetaNumero = textFieldNumeroDeTarjeta.getText();
				boolean seEncontro = false;

				while (iterador.hasNext() && !seEncontro) {
					
					unCliente = iterador.next();
					if (tarjetaNumero.equals(unCliente.obtenerTarjeta().obtenerNumeroDeTarjeta())) {
						passwordFieldPin.setEnabled(true);
						labelInserteSuPin.setEnabled(true);
						textFieldNumeroDeTarjeta.setEnabled(false);
						lblInserteSuTarjeta.setEnabled(false);
						teclearNumeros = true;
						numeroDeTarjeta = textFieldNumeroDeTarjeta.getText();
						seEncontro = true;
					}
				}
				if (!seEncontro) {
					try {
						verifiCarQueElCampoNoEsteVacio(textFieldNumeroDeTarjeta.getText());
						JOptionPane.showMessageDialog(null, "NO SE ENCONTRO LA TARJETA BUSCADA");
					} catch (ValorInvalido e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btnAceptar.setForeground(new Color(255, 102, 0));
		btnAceptar.setBackground(new Color(0, 0, 0));
		btnAceptar.setBounds(447, 44, 95, 20);
		contentPane.add(btnAceptar);
	}
	//post: crea el boton aceptarPin
	private void crearEImplementarBotonAceptarPin() throws FileNotFoundException, ValorInvalido {
		JButton btnAceptarDos = new JButton("ACEPTAR");
		btnAceptarDos.addActionListener(new ActionListener() {
			//post: verifica  si el pin corresponde al numero de tarjeta
			public void actionPerformed(ActionEvent e) {
				
				pinDeLaTarjeta = passwordFieldPin.getText();

				if (pinDeLaTarjeta.equals(unCliente.obtenerTarjeta().obtenerPinDeTarjeta())) {

					crearVentana();

				} else {
					contador++;
					if (contador < 3) {
						DatosIncorrectos ventanaDatosIncorrectos = new DatosIncorrectos();
						ventanaDatosIncorrectos.setVisible(true);
					} else {
						dispose();
						BloqueoDeTarjeta nuevoBloqueo = new BloqueoDeTarjeta();
						nuevoBloqueo.setVisible(true);
						
						try {
							throw new AccesoBloqueado(
									"3 Intentos Fallidos,su tarjeta ah sido bloqueada ,por favor contacte con un reprecentante del"
									+ " Banco para Reiniciar el ATM");
						} catch (AccesoBloqueado e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnAceptarDos.setBackground(new Color(0, 0, 0));
		btnAceptarDos.setForeground(new Color(255, 102, 0));
		btnAceptarDos.setBounds(447, 109, 95, 20);
		contentPane.add(btnAceptarDos);
	}
	//post: crea el campo de texto para numero de tarjeta
	private void crearTextFieldTarjeta() {
		textFieldNumeroDeTarjeta = new JTextField();
		textFieldNumeroDeTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNumeroDeTarjeta.setForeground(new Color(255, 102, 0));
		textFieldNumeroDeTarjeta.setBackground(new Color(0, 0, 0));
		textFieldNumeroDeTarjeta.setBounds(225, 44, 179, 20);
		contentPane.add(textFieldNumeroDeTarjeta);
		textFieldNumeroDeTarjeta.setColumns(10);
	}
	//post: crea im campo de texto para pin
	private void crearPasswordFieldPin() {
		passwordFieldPin = new JPasswordField();
		passwordFieldPin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldPin.setForeground(new Color(255, 102, 0));
		passwordFieldPin.setBackground(new Color(0, 0, 0));
		passwordFieldPin.setBounds(263, 105, 80, 20);
		contentPane.add(passwordFieldPin);
		passwordFieldPin.setEnabled(false);

	}
	//post: crea el texto numero de su tarjeta
	private void crearTextoInsertarNumTarjeta() {
		lblInserteSuTarjeta = new JLabel("Numero de su tarjeta: ");
		lblInserteSuTarjeta.setBackground(new Color(0, 0, 0));
		lblInserteSuTarjeta.setFont(new Font("NSimSun", Font.BOLD, 18));
		lblInserteSuTarjeta.setForeground(new Color(255, 102, 0));
		lblInserteSuTarjeta.setBounds(10, 33, 333, 40);
		contentPane.add(lblInserteSuTarjeta);
	}
	//post: crea el texto insertar pin
	private void crearTextoInsertarNumPin() {
		labelInserteSuPin = new JLabel("Numero pin de su tarjeta: ");
		labelInserteSuPin.setBackground(new Color(0, 0, 0));
		labelInserteSuPin.setFont(new Font("NSimSun", Font.BOLD, 18));
		labelInserteSuPin.setForeground(new Color(255, 102, 0));
		labelInserteSuPin.setBounds(10, 109, 363, 14);
		contentPane.add(labelInserteSuPin);
		labelInserteSuPin.setEnabled(false);
	}
	//post: crea los botones Del Teclado Digital
	private void crearBotonesTeclado() {

		crearEImplementarBotonUno();

		crearEImplementarBotonDos();

		crearEImplementarBotonTres();

		crearEImplementarBotonCuatro();

		crearEImplementarBotonCinco();

		crearEImplementarBotonSeis();

		crearEImplementarBotonSiete();

		crearEImplementarBotonOcho();

		crearEImplementarBotonNueve();

		crearEImplementarBotonCero();

	}
	//post: crea el boton 1
	private void crearEImplementarBotonUno() {

		JButton botonTecladoUno = new JButton("1");

		botonTecladoUno.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '1');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '1');
				}
			}

		});
		botonTecladoUno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoUno.setForeground(new Color(255, 102, 0));
		botonTecladoUno.setBackground(new Color(0, 0, 0));
		botonTecladoUno.setBounds(42, 205, 60, 40);
		contentPane.add(botonTecladoUno);
	}
	//post: crea el boton 2
	private void crearEImplementarBotonDos() {

		JButton botonTecladoDos = new JButton("2");
		botonTecladoDos.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '2');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '2');
				}
			}

		});
		botonTecladoDos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoDos.setForeground(new Color(255, 102, 0));
		botonTecladoDos.setBackground(new Color(0, 0, 0));
		botonTecladoDos.setBounds(127, 205, 60, 40);
		contentPane.add(botonTecladoDos);

	}
	//post: crea el boton 3
	private void crearEImplementarBotonTres() {

		JButton botonTecladoTres = new JButton("3");
		botonTecladoTres.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '3');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '3');
				}
			}
		});
		botonTecladoTres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoTres.setForeground(new Color(255, 102, 0));
		botonTecladoTres.setBackground(new Color(0, 0, 0));
		botonTecladoTres.setBounds(213, 205, 60, 40);
		contentPane.add(botonTecladoTres);

	}
	//post: crea el boton 4
	private void crearEImplementarBotonCuatro() {
		JButton botonTecladoCuatro = new JButton("4");
		botonTecladoCuatro.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '4');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '4');
				}
			}
		});
		botonTecladoCuatro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoCuatro.setForeground(new Color(255, 102, 0));
		botonTecladoCuatro.setBackground(new Color(0, 0, 0));
		botonTecladoCuatro.setBounds(42, 256, 60, 40);
		contentPane.add(botonTecladoCuatro);
	}
	//post: crea el boton 5
	private void crearEImplementarBotonCinco() {
		JButton botonTecladoCinco = new JButton("5");
		botonTecladoCinco.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '5');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '5');
				}

			}
		});
		botonTecladoCinco.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoCinco.setForeground(new Color(255, 102, 0));
		botonTecladoCinco.setBackground(new Color(0, 0, 0));
		botonTecladoCinco.setBounds(127, 256, 60, 40);
		contentPane.add(botonTecladoCinco);
	}
	//post: crea el boton 6
	private void crearEImplementarBotonSeis() {
		JButton botonTecladoSeis = new JButton("6");
		botonTecladoSeis.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '6');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '6');
				}
			}
		});
		botonTecladoSeis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoSeis.setForeground(new Color(255, 102, 0));
		botonTecladoSeis.setBackground(new Color(0, 0, 0));
		botonTecladoSeis.setBounds(213, 256, 60, 40);
		contentPane.add(botonTecladoSeis);
	}
	//post: crea el boton 7
	private void crearEImplementarBotonSiete() {
		JButton botonTecladoSiete = new JButton("7");
		botonTecladoSiete.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '7');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '7');
				}
			}
		});
		botonTecladoSiete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoSiete.setBackground(new Color(0, 0, 0));
		botonTecladoSiete.setForeground(new Color(255, 102, 0));
		botonTecladoSiete.setBounds(42, 307, 60, 40);
		contentPane.add(botonTecladoSiete);
	}
	//post: crea el boton 8
	private void crearEImplementarBotonOcho() {
		JButton botonTecladoOcho = new JButton("8");
		botonTecladoOcho.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '8');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '8');
				}
			}
		});
		botonTecladoOcho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoOcho.setBackground(new Color(0, 0, 0));
		botonTecladoOcho.setForeground(new Color(255, 102, 0));
		botonTecladoOcho.setBounds(127, 307, 60, 40);
		contentPane.add(botonTecladoOcho);
	}
	//post: crea el boton 9
	private void crearEImplementarBotonNueve() {
		JButton botonTecladoNueve = new JButton("9");
		botonTecladoNueve.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '9');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '9');
				}
			}
		});
		botonTecladoNueve.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoNueve.setForeground(new Color(255, 102, 0));
		botonTecladoNueve.setBackground(new Color(0, 0, 0));
		botonTecladoNueve.setBounds(213, 307, 60, 40);
		contentPane.add(botonTecladoNueve);
	}
	//post: crea el boton0
	private void crearEImplementarBotonCero() {
		JButton botonTecladoCero = new JButton("0");
		botonTecladoCero.addActionListener(new ActionListener() {
			//post: obtiene el campo de texto y le suma un valor
			public void actionPerformed(ActionEvent e) {
				if (teclearNumeros) {
					passwordFieldPin.setText(passwordFieldPin.getText() + '0');
				} else if (!teclearNumeros) {
					textFieldNumeroDeTarjeta.setText(textFieldNumeroDeTarjeta
							.getText() + '0');
				}
			}
		});
		botonTecladoCero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		botonTecladoCero.setBackground(new Color(0, 0, 0));
		botonTecladoCero.setForeground(new Color(255, 102, 0));
		botonTecladoCero.setBounds(42, 358, 147, 37);
		contentPane.add(botonTecladoCero);
	}
	//post: oculta esta ventana
	private void ocultar() {
		this.setVisible(false);
	}
	
	//post: crea una ventana de SeleccionarCuenta
	public void crearVentana() {

		SeleccionarCuenta unaVentana;
		try {
			unaVentana = new SeleccionarCuenta();
			unaVentana.setVisible(true);
			ocultar();
		} catch (FileNotFoundException 
				| ValorInvalido e) {

			e.printStackTrace();
		}
	}
	//post: guarda en listaDeClientes la listaDeClientes de VentanaInicio
	public void pasarDatosDeInicioAValidacionDeTarjeta(){
		listaDeClientes=VentanaInicio.listaDeClientes;
	}
	//post: verifica que campo no este vacio
	public void verifiCarQueElCampoNoEsteVacio(String campo) throws ValorInvalido{
		if(campo.length()==0){
			JOptionPane.showMessageDialog(null, "Debe completar los campos para continuar");
			throw new ValorInvalido("Debe completar los campos para continuar");
			
		}
	}
	
			
}
