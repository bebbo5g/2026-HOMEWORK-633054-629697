package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

public abstract class AbstractComando implements Comando {

	protected final IO io;

	public AbstractComando(IO io) {
		this.io = io;
	}

	@Override
	public String getParametro() {
		return null;
	}
}
