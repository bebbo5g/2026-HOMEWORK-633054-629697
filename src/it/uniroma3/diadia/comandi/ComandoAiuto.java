package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	final private String nome = "aiuto";
	static final String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda", "regala" };
	final private IO io;

	public ComandoAiuto(IO io) {
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
		StringBuilder sb = new StringBuilder();
		for (String cmd : elencoComandi)
			sb.append(cmd).append(" ");
		this.io.mostraMessaggio(sb.toString().trim());
	}

}
