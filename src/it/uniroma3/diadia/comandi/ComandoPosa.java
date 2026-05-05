package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {
	final private String nome = "posa";
	final private String nomeAttrezzo;
	final private IO io;

	public ComandoPosa(String nomeAttrezzo, IO io) {
		this.nomeAttrezzo = nomeAttrezzo;
		this.io = io;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio("Non hai scritto nulla!");
			return;
		}

		Borsa borsa = partita.getGiocatore().borsa;

		if (!borsa.hasAttrezzo(this.nomeAttrezzo)) {
			this.io.mostraMessaggio("L'attrezzo " + this.nomeAttrezzo + " non è presente in borsa!");
			return;
		}

		Attrezzo attrezzo = borsa.getAttrezzo(this.nomeAttrezzo);

		if (partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			borsa.removeAttrezzo(attrezzo);
			this.io.mostraMessaggio(
					"Attrezzo" + attrezzo.getNome() + " posato in stanza: " + partita.getStanzaCorrente().getNome());
			this.io.mostraMessaggio("Peso attuale borsa: " + borsa.getPeso() + "kg/" + borsa.getPesoMax() + "kg");
		} else {
			this.io.mostraMessaggio("Stanza piena di attrezzi, IMPOSSIBILE POSARE!");
		}

	}

}
