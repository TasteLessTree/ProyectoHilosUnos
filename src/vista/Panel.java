package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import controlador.Avanzar;
import modelo.BarraProgreso;

import javax.swing.JProgressBar;

public class Panel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir, btnAvanzar1, btnAvanzar2, btnALaVez;
	private JProgressBar pbAvance1, pbAvance2;
	private Avanzar AvanzarBarra1 = null, AvanzarBarra2 = null;

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
				try {
					if (Objects.isNull(AvanzarBarra1) || !AvanzarBarra1.isAlive()) {
						AvanzarBarra1 = new Avanzar(pbAvance1);
						AvanzarBarra1.start();
					} else {
						JOptionPane.showMessageDialog(Panel.this,
								"El proceso \"Barra 1\" ya está en ejecución");
					}
				} catch (IllegalThreadStateException ex) {
					AvanzarBarra1 = null;
					System.out.println("Error al crear barra1: " + ex.getMessage());
				}
			}
		});
		btnAvanzar1.setBounds(10, 11, 89, 36);
		contentPane.add(btnAvanzar1);
		
		btnAvanzar2 = new JButton("Avanzar");
		btnAvanzar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Objects.isNull(AvanzarBarra2) || !AvanzarBarra2.isAlive()) {
						AvanzarBarra2 = new Avanzar(pbAvance2);
						AvanzarBarra2.start();
					} else {
						JOptionPane.showMessageDialog(Panel.this,
								"El proceso \"Barra 2\" ya está en ejecución");
					}
				} catch (IllegalThreadStateException ex) {
					AvanzarBarra2 = null;
					System.out.println("Error al crear barra2: " + ex.getMessage());
				}
			}
		});
		btnAvanzar2.setBounds(10, 85, 89, 36);
		contentPane.add(btnAvanzar2);
		
		pbAvance1 = new JProgressBar();
		pbAvance1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cambiarColor(pbAvance1);
				
				if (pbAvance1.getValue() == BarraProgreso.MAX) {
					JOptionPane.showMessageDialog(Panel.this,
							"El proceso 1 ha finalizado");
				}
			}
		});
		pbAvance1.setBounds(109, 11, 446, 36);
		contentPane.add(pbAvance1);
		
		pbAvance2 = new JProgressBar();
		pbAvance2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cambiarColor(pbAvance2);
				
				if (pbAvance2.getValue() == BarraProgreso.MAX) {
					JOptionPane.showMessageDialog(Panel.this,
							"El proceso 2 ha finalizado");
				}
			}
		});
		pbAvance2.setBounds(109, 85, 446, 36);
		contentPane.add(pbAvance2);
		
		btnALaVez = new JButton("A la vez");
		btnALaVez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((Objects.isNull(AvanzarBarra1) || !AvanzarBarra1.isAlive()) &&
							(Objects.isNull(AvanzarBarra2) || !AvanzarBarra2.isAlive())) {
						AvanzarBarra1 = new Avanzar(pbAvance1);
						AvanzarBarra2 = new Avanzar(pbAvance2);
						
						AvanzarBarra1.start();
						AvanzarBarra2.start();
					} else {
						JOptionPane.showMessageDialog(Panel.this,
								"Ambos procesos ya está en ejecución");
					}
				} catch (IllegalThreadStateException ex) {
					AvanzarBarra1 = null;
					AvanzarBarra2 = null;
					System.out.println("Error al crear ambas barras: " + ex.getMessage());
				}
			}
		});
		btnALaVez.setBounds(10, 152, 89, 36);
		contentPane.add(btnALaVez);
	}
	
	// Cambiar el color de una barra de progreso
	private void cambiarColor(JProgressBar jpb) {
		Random random = new Random();
		
		int max = 256; // Rango [0, 256)
		
		int red = random.nextInt(max);
		int green = random.nextInt(max);
		int blue = random.nextInt(max);
		
		jpb.setForeground(new Color(red, green, blue));
	}
}
