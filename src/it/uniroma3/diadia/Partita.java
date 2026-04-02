package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	// private Stanza stanzaCorrente;

	private boolean finita;
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private Giocatore giocatore;

	public Partita() {
		this.labirinto = new Labirinto();
		this.stanzaCorrente = this.labirinto.iniziale;
		this.finita = false;

		this.giocatore = new Giocatore();
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == this.labirinto.finale;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return (this.finita || this.vinta() || this.giocatore.getCfu() == 0);
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		if (stanzaCorrente == null) {
			// TODO: throw not Null Exception
			return;
		}
		// Non fare niente se la stanza non viene cambiata
		if (stanzaCorrente == this.stanzaCorrente) {
			return;
		}

		this.stanzaCorrente = stanzaCorrente;

		this.giocatore.setCfu(this.giocatore.getCfu() - 1);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
}
