package it.uniroma3.diadia.comandi;

import java.util.LinkedHashSet;
import java.util.Set;

import it.uniroma3.diadia.IO;

public abstract class AbstractComando implements Comando {

	private static final String PREFISSO = "Comando";
	private static final String[] ESCLUSI = { "NonValido" };

	private static Set<String> nomiComandi = new LinkedHashSet<>();

	protected final IO io;

	public AbstractComando(IO io) {
		this.io = io;
		registra(this.getClass().getSimpleName());
	}

	private static void registra(String nomeClasse) {
		if (!nomeClasse.startsWith(PREFISSO))
			return;
		String nomeComando = nomeClasse.substring(PREFISSO.length()).toLowerCase();
		for (String escluso : ESCLUSI)
			if (escluso.equalsIgnoreCase(nomeComando))
				return;
		nomiComandi.add(nomeComando);
	}

	public static Set<String> getNomiComandi() {
		return nomiComandi;
	}

	@Override
	public String getParametro() {
		return null;
	}
}
