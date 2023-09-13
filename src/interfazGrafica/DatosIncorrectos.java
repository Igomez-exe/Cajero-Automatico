package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class DatosIncorrectos extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatosIncorrectos frame = new DatosIncorrectos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public DatosIncorrectos() {
		
		crearVentanaDatosIncorrectos();
		
		crearTextoDatosIncorrectos();
		
		crearTextoIntenteNuevamente();
		
		crearEImplementarBotonOk();
		
		establecerFondo();
	
	}
	//post: crea ventana Datos Incorrectos
	public void crearVentanaDatosIncorrectos(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosIncorrectos.class.getResource("/imagen/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
	}
	//post: crea el texto DatosIncorrectos
	public void crearTextoDatosIncorrectos(){
		JLabel lblTituloDatosincorrectos = new JLabel("Datos Incorrectos");
		lblTituloDatosincorrectos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblTituloDatosincorrectos.setBounds(165, 11, 255, 104);
		lblTituloDatosincorrectos.setForeground(new Color(255, 102, 51));
		lblTituloDatosincorrectos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTituloDatosincorrectos);
	}
	//post: crea el texto Intente Nuevamente
	public void crearTextoIntenteNuevamente(){
		JLabel lblTituloIntenteNuevamente = new JLabel("Intente Nuevamente\r\n");
		lblTituloIntenteNuevamente.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloIntenteNuevamente.setForeground(new Color(255, 102, 51));
		lblTituloIntenteNuevamente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblTituloIntenteNuevamente.setBounds(144, 97, 294, 55);
		contentPane.add(lblTituloIntenteNuevamente);
	}
	//post: crea el Boton OK
	public void crearEImplementarBotonOk(){
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			//post: cierra la ventana
			public void actionPerformed(ActionEvent e) {			
				dispose();
			}
		});
		btnOk.setBackground(new Color(255, 204, 0));
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBounds(241, 188, 99, 23);
		contentPane.add(btnOk);
	}
	//post: crea el fondo de la ventana
	public void establecerFondo(){
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(DatosIncorrectos.class.getResource("/imagen/fondo_1.jpg")));
		lblFondo.setBounds(-146, 11, 786, 561);
		contentPane.add(lblFondo);
	}
}
