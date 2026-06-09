package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {
	private boolean finita;
	public Labirinto lab;
	public Giocatore giocatore;

	public Partita() {
		this.lab = Labirinto.newBuilder().addStanzaIniziale("Atrio").addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.nord).getLabirinto();
		this.finita = false;
		this.giocatore = new Giocatore();
	}

	public Partita(Labirinto labirinto) {
		this.lab = labirinto;
		this.finita = false;
		this.giocatore = new Giocatore();
	}

	public void setLabirinto(Labirinto labi) {
		this.lab = labi;
	}

	public boolean vinta() {
		return this.lab.getStanzaCorrente() == this.lab.getStanzaVincente();
	}

	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}
}
