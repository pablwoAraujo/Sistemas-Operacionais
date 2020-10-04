package solucaoMonitores;

import enums.EstadoFilosofo;

public class SolucaoMonitores {
	
	public static int quantidade = 5;
	public static Filosofo[] filosofos = new Filosofo[quantidade];

	public static int esq(int i) {
		return (i + 1) % quantidade;
	}
	
	public static int dir(int i) {
		return (i - 1 + quantidade) % quantidade;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < quantidade; i++)
			filosofos[i] = new Filosofo(i);
	}

	public static void pega_talher(int id) throws InterruptedException {
		
		filosofos[id].setEstado(EstadoFilosofo.COM_FOME);
		if(filosofos[esq(id)].getEstado() != EstadoFilosofo.COMENDO && filosofos[dir(id)].getEstado() != EstadoFilosofo.COMENDO) {
			filosofos[id].setEstado(EstadoFilosofo.COMENDO);
		}else synchronized (filosofos[id]){
			filosofos[id].wait();
		}
		
	}

	public static void larga_talher(int id) throws InterruptedException {
		
		filosofos[id].setEstado(EstadoFilosofo.PENSANDO);
		
		if (filosofos[dir(id)].getEstado() == EstadoFilosofo.COM_FOME && filosofos[dir(dir(id))].getEstado() != EstadoFilosofo.COMENDO) {
			filosofos[dir(id)].setEstado(EstadoFilosofo.COMENDO);
			synchronized (filosofos[dir(id)]) {
				filosofos[dir(id)].notify();
			}
		}
		
		if (filosofos[esq(id)].getEstado() == EstadoFilosofo.COM_FOME && filosofos[esq(esq(id))].getEstado() != EstadoFilosofo.COMENDO) {
			filosofos[esq(id)].setEstado(EstadoFilosofo.COMENDO);
			synchronized (filosofos[esq(id)]) {
				filosofos[esq(id)].notify();
			}
		}
	}
	
}
