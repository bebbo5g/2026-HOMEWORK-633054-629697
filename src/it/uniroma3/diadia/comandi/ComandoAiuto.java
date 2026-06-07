package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	final private String nome = "aiuto";
	static final String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda", "regala" };

	public ComandoAiuto(IO io) {
		super(io);
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void esegui(Partita partita) {
		StringBuilder sb = new StringBuilder();
		for (String cmd : elencoComandi)
			sb.append(cmd).append(" ");
		this.io.mostraMessaggio(sb.toString().trim());
	}

}
