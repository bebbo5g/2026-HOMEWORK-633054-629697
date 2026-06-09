package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio = partita.lab.getStanzaCorrente().getPersonaggio();
		if (personaggio != null)
			io.mostraMessaggio(personaggio.saluta());
		else
			io.mostraMessaggio("Non c'è nessuno da salutare!");
	}
}