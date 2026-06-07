package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala implements Comando {
	private static final String NOME = "regala";
	private final String nomeAttrezzo;
	private final IO io;

	public ComandoRegala(String nomeAttrezzo, IO io) {
		this.nomeAttrezzo = nomeAttrezzo;
		this.io = io;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio("Quale attrezzo vuoi regalare?");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo);
		if (attrezzo == null) {
			this.io.mostraMessaggio("Non hai " + this.nomeAttrezzo + " nella borsa.");
			return;
		}
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		this.io.mostraMessaggio("Hai regalato " + attrezzo.getNome() + "!");
	}
}
