package controlador;

import javax.swing.JProgressBar;

public class Avanzar extends Thread {
	public JProgressBar barra;
	private boolean avanzar;
	
	public Avanzar(JProgressBar JPB) {
		this.barra = JPB;
		this.avanzar = false;
	}
	
	public boolean isVivo() {
		return avanzar;
	}

	public void setVivo(boolean alive) {
		this.avanzar = alive;
	}

	@Override
	public void run() {
		avanzar = true;
		barra.setStringPainted(true);
		
		for (int i = barra.getMinimum(); i <= barra.getMaximum(); i++) {
			this.barra.setValue(i);
			
			try {				
				Thread.sleep(150);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		avanzar = false;
	}
}