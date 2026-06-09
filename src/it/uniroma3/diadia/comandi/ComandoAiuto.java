package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda", "inventario",
			"regala", "interagisci", "saluta" };

	@Override
	public void esegui(Partita partita, IO io) {
		StringBuilder sb = new StringBuilder();
		for (String cmd : elencoComandi)
			sb.append(cmd).append(" ");
		io.mostraMessaggio(sb.toString());
	}
}