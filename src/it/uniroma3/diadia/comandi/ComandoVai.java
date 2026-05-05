package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	final private String nome = "vai";
	final private String direzione;
	final private IO io;

	public ComandoVai(String direzione, IO io) {
		this.direzione = direzione;
		this.io = io;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public void esegui(Partita partita) {
		if (direzione == null) {
			this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			this.io.mostraMessaggio("Dove vuoi andare ?");
			return;

		}

		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);

		if (prossimaStanza == null) {
			this.io.mostraMessaggio("Direzione inesistente");
			return;
		}

		partita.setStanzaCorrente(prossimaStanza);

		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.io.mostraMessaggio("CFU rimantente: " + partita.getGiocatore().getCfu());

	}

}
