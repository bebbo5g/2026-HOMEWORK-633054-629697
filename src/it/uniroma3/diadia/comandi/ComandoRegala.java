package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	private static final String NOME = "regala";
	private final String nomeAttrezzo;

	public ComandoRegala(String nomeAttrezzo, IO io) {
		super(io);
		this.nomeAttrezzo = nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			this.io.mostraMessaggio("Non c'è nessun personaggio a cui regalare qualcosa.");
			return;
		}
		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio("Cosa vuoi regalare?");
			return;
		}
		if (!partita.getGiocatore().getBorsa().hasAttrezzo(this.nomeAttrezzo)) {
			this.io.mostraMessaggio("Non hai " + this.nomeAttrezzo + " nella borsa.");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo);
		this.io.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));
	}
}
