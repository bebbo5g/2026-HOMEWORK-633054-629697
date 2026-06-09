package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Proprieta;

public class Giocatore {

	private int cfu;
	public Borsa borsa;

	public Giocatore() {
		this.cfu = Proprieta.getCfuIniziali();
		this.borsa = new Borsa();
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public int getCfu() {
		return this.cfu;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}
}
