package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import controlador.Avanzar;

import javax.swing.JProgressBar;

public class Panel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir, btnAvanzar1, btnAvanzar2, btnALaVez;
	private JProgressBar pbAvance1, pbAvance2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel frame = new Panel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Panel() {
		setTitle("Barras de prograso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(
						Panel.this,
						"¿Seguro que quieres salir?",
						"SALIR",
						JOptionPane.YES_NO_OPTION);
				
				if (respuesta == 0) System.exit(EXIT_ON_CLOSE); 
			}
		});
		btnSalir.setBounds(457, 249, 98, 62);
		contentPane.add(btnSalir);
		
		btnAvanzar1 = new JButton("Avanzar");
		btnAvanzar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread AvanzarBarra1 = new Avanzar(pbAvance1);
				AvanzarBarra1.start();
				btnAvanzar1.setEnabled(false);
				btnALaVez.setEnabled(false);
				
				if (AvanzarBarra1.isAlive()) {
					btnAvanzar1.setEnabled(false);
					btnALaVez.setEnabled(false);
				} else {
					btnAvanzar1.setEnabled(true);
					btnALaVez.setEnabled(true);
				}
			}
		});
		btnAvanzar1.setBounds(10, 11, 89, 36);
		contentPane.add(btnAvanzar1);
		
		btnAvanzar2 = new JButton("Avanzar");
		btnAvanzar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread AvanzarBarra2 = new Avanzar(pbAvance2);
				AvanzarBarra2.start();
				btnAvanzar2.setEnabled(false);
				btnALaVez.setEnabled(false);
				
				if (AvanzarBarra2.isAlive()) {
					btnAvanzar2.setEnabled(false);
					btnALaVez.setEnabled(false);
				} else {
					btnAvanzar2.setEnabled(true);
					btnALaVez.setEnabled(true);
				}
			}
		});
		btnAvanzar2.setBounds(10, 85, 89, 36);
		contentPane.add(btnAvanzar2);
		
		pbAvance1 = new JProgressBar();
		pbAvance1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Random random = new Random();
				
				int max = 256; // Rango [0, 256)
				
				int red = random.nextInt(max);
				int green = random.nextInt(max);
				int blue = random.nextInt(max);

				pbAvance1.setForeground(new Color(red, green, blue));
			}
		});
		pbAvance1.setBounds(109, 11, 446, 36);
		contentPane.add(pbAvance1);
		
		pbAvance2 = new JProgressBar();
		pbAvance2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Random random = new Random();
				
				int max = 256; // Rango [0, 256)
				
				int red = random.nextInt(max);
				int green = random.nextInt(max);
				int blue = random.nextInt(max);
				
				pbAvance2.setForeground(new Color(red, green, blue));
			}
		});
		pbAvance2.setBounds(109, 85, 446, 36);
		contentPane.add(pbAvance2);
		
		btnALaVez = new JButton("A la vez");
		btnALaVez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread AvanzarBarra1 = new Avanzar(pbAvance1);
				Thread AvanzarBarra2 = new Avanzar(pbAvance2);
				
				AvanzarBarra1.start();
				AvanzarBarra2.start();
				
				if (AvanzarBarra1.isAlive() && AvanzarBarra2.isAlive()) {
					btnAvanzar1.setEnabled(false);
					btnAvanzar2.setEnabled(false);
					btnALaVez.setEnabled(false);
				} else {
					btnAvanzar1.setEnabled(true);
					btnAvanzar2.setEnabled(true);
					btnALaVez.setEnabled(true);
				}
			}
		});
		btnALaVez.setBounds(10, 152, 89, 36);
		contentPane.add(btnALaVez);
	}
}
