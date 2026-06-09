package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		Stanza stanzaCorrente = partita.lab.getStanzaCorrente();
		if (this.getParametro() == null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		Direzione direzione;
		try {
			direzione = Direzione.valueOf(this.getParametro());
		} catch (IllegalArgumentException e) {
			io.mostraMessaggio("Direzione non valida: " + this.getParametro());
			return;
		}
		Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		if (prossimaStanza == stanzaCorrente) {
			io.mostraMessaggio("La direzione " + direzione + " è bloccata!");
			return;
		}
		partita.lab.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.lab.getStanzaCorrente().getNome());
		partita.giocatore.setCfu(partita.giocatore.getCfu() - 1);
	}
}