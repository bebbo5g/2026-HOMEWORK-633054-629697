package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	final private String nome = "guarda";
	final private IO io;

	public ComandoGuarda(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

}
