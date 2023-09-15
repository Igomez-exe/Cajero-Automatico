package CajeroAutomatico;

import interfazGrafica.VentanaInicio;

import java.awt.EventQueue;


public class Atm {
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
}
