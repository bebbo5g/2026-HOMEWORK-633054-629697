package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	private static final String NOME = "saluta";
	private static final String MESSAGGIO_NESSUNO = "Non c'è nessuno da salutare qui.";

	public ComandoSaluta(IO io) {
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
			this.io.mostraMessaggio(personaggio.saluta());
		else
			this.io.mostraMessaggio(MESSAGGIO_NESSUNO);
	}
}
