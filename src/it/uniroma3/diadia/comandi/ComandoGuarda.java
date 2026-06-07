package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	final private String nome = "guarda";

	public ComandoGuarda(IO io) {
		super(io);
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

}
