package solucaoMonitores;

import enums.EstadoFilosofo;

public class Filosofo implements Runnable{

	private int id;
	private EstadoFilosofo estado;
		
	public Filosofo(int id) {
		this.id=id;
		this.estado = EstadoFilosofo.PENSANDO;
		new Thread((Runnable) this, "filosofo: " + id).start();
	}
		
	public int getId() {
		return this.id;
	}
		
	public EstadoFilosofo getEstado() {
		return this.estado;
	}
	
	public void setEstado(EstadoFilosofo estado) {
		this.estado = estado;
	}
	
	private void pensar() {
		this.setEstado(EstadoFilosofo.PENSANDO);
		System.out.println(this.toString());
			
	}
		
	private void ficarComFome() {
		this.setEstado(EstadoFilosofo.COM_FOME);
		System.out.println(this.toString());
	}

	private void comer() {
		this.setEstado(EstadoFilosofo.COMENDO);
		System.out.println(this.toString());
	}

	@Override
	public void run() {
		System.out.println("Iniciando Filosofo: " + getId());
			while(true) {
			
			try {
				this.pensar();
				Thread.sleep(1000);
				
				this.ficarComFome();
				SolucaoMonitores.pega_talher(this.id);
				this.comer();
				Thread.sleep(1000);
				
				System.out.println("O filosofo: " + getId() + " TERMINOU DE COMER");
				SolucaoMonitores.larga_talher(this.id);
				} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
		
	@Override
	public String toString() {
		return "O filosofo: " + getId() + " está " + getEstado();
	}
			
}
