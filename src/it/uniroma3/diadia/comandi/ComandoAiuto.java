package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	final private String nome = "aiuto";
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda" };
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
		StringBuilder msg_aiuto = new StringBuilder();

		for (int i = 0; i < elencoComandi.length; i++) {
			msg_aiuto.append(elencoComandi[i]);

			if (i < elencoComandi.length - 1) {
				msg_aiuto.append(" ");
			}
		}

		this.io.mostraMessaggio(msg_aiuto.toString());
	}

}
