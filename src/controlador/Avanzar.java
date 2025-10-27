package controlador;

import javax.swing.JProgressBar;

public class Avanzar extends Thread {
	public JProgressBar barra;
	private boolean vivo;
	
	public Avanzar(JProgressBar JPB) {
		this.barra = JPB;
		this.vivo = false;
	}
	
	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean alive) {
		this.vivo = alive;
	}

	@Override
	public void run() {
		this.vivo = true;
		barra.setStringPainted(true);
		
		for (int i = barra.getMinimum(); i <= barra.getMaximum(); i++) {
			this.barra.setValue(i);
			
			try {				
				Thread.sleep(150);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}