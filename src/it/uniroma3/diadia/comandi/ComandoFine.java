package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	final private String nome = "fine";

	public ComandoFine(IO io) {
		super(io);
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
	}

}
