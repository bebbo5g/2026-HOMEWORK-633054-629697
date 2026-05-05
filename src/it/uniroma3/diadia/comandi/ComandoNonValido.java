package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	final private String nome = "non-valido";
	final private String cmd;
	final private IO io;

	public ComandoNonValido(String cmd, IO io) {
		this.cmd = cmd;
		this.io = io;
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
