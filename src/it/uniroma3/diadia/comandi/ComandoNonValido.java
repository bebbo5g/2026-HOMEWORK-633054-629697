package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	final private String nome = "non-valido";
	final private String cmd;

	public ComandoNonValido(String cmd, IO io) {
		super(io);
		this.cmd = cmd;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.cmd;
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando \"" + this.cmd + "\" non valido");
	}

}
