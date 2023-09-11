package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BloqueoDeTarjeta extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BloqueoDeTarjeta frame = new BloqueoDeTarjeta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BloqueoDeTarjeta() {
		
		crearVentanaBloqueoDeTarjeta();
		
		crearEImplementarBotonSalir();
		
		crearTextoSuTarjetaAhSidoBloqueada();
		
		establecerFondo();
	}
	//post:crea la ventana bloque de tarjeta
	public void crearVentanaBloqueoDeTarjeta(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(BloqueoDeTarjeta.class.getResource("/imagen/mm.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post:crea el boton salir
	public void crearEImplementarBotonSalir(){
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			//Post: el programa se cierra.
			public void actionPerformed(ActionEvent e) {
				dispose();
	
			}
		});
		btnSalir.setBackground(new Color(255, 204, 0));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnSalir.setBounds(241, 398, 104, 23);
		contentPane.add(btnSalir);
	}
	//post: crea leabel de tarjeta bloqueada
	public void crearTextoSuTarjetaAhSidoBloqueada(){
		JLabel lblSuTarjetaAhSidoBloqueada = new JLabel("Su tarjeta ah sido bloqueada");
		lblSuTarjetaAhSidoBloqueada.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuTarjetaAhSidoBloqueada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblSuTarjetaAhSidoBloqueada.setForeground(new Color(255, 102, 51));
		lblSuTarjetaAhSidoBloqueada.setBounds(113, 45, 364, 48);
		contentPane.add(lblSuTarjetaAhSidoBloqueada);
	}
	//post: crea el fondo de la ventana
	public void establecerFondo(){
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BloqueoDeTarjeta.class.getResource("/imagen/fnatic1.jpg")));
		lblFondo.setBounds(-144, -61, 802, 523);
		contentPane.add(lblFondo);
	}
}
