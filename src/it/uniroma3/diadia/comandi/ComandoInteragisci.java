package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String NOME = "interagisci";
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";

	public ComandoInteragisci(IO io) {
		super(io);
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null)
			this.io.mostraMessaggio(personaggio.agisci(partita));
		else
			this.io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
}
