package modelo;

public class BarraProgreso {
	private static final int MIN = 0;
	private static final int MAX = 100;
	
	private String nombre;
	private int min;
	private int max;
	private int valorActual;
	
	public BarraProgreso(String nombre) {
		this.nombre = nombre;
		this.min = this.valorActual = MIN;
		this.max = MAX;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getValorActual() {
		return valorActual;
	}

	public void setValorActual(int valorActual) {
		this.valorActual = valorActual;
	}
}